package com.alpaca.library_base.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateUtils;

import com.alpaca.library_base.net.DownloadListener;
import com.alpaca.library_base.net.DownloadService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @文件名: DownloadUtil
 * @功能描述:
 * @Date : 2019/10/24
 * @email:
 * @修改记录:
 */
public class DownloadUtil {

    public static void download(String url, final String path, final DownloadListener downloadListener, boolean isDel) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://res.ytaxx.com/")
                //通过线程池获取一个线程，指定callback在子线程中运行。
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .build();

        DownloadService service = retrofit.create(DownloadService.class);

        Call<ResponseBody> call = service.download(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull final Response<ResponseBody> response) {
                //将Response写入到从磁盘中，详见下面分析
                //注意，这个方法是运行在子线程中的
                writeResponseToDisk(path, response, downloadListener, isDel);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable) {
                downloadListener.onFail("网络错误");
            }
        });
    }

    private static void writeResponseToDisk(String path, Response<ResponseBody> response, DownloadListener downloadListener, boolean isDel) {
        //从response获取输入流以及总大小
        writeFileFromIS(new File(path), response.body().byteStream(), response.body().contentLength(), downloadListener, isDel);
    }

    private static int sBufferSize = 2048;

    //将输入流写入文件
    private static void writeFileFromIS(File file, InputStream is, long totalLength, DownloadListener downloadListener, boolean isDel) {
        //开始下载
        downloadListener.onStart();

        //创建文件
        if (!file.exists()) {
            if (!file.getParentFile().exists())
                file.getParentFile().mkdir();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                downloadListener.onFail("createNewFile IOException");
            }
        } else {
            downloadListener.onExist();
            if (isDel) {
                //是否重新下载
                downloadListener.onFinish(file.getAbsolutePath());
                return;
            }
        }

        OutputStream os = null;
        long currentLength = 0;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            byte data[] = new byte[sBufferSize];
            int len;
            while ((len = is.read(data, 0, sBufferSize)) != -1) {
                os.write(data, 0, len);
                os.flush();
                currentLength += len;
                //计算当前下载进度
                downloadListener.onProgress((int) (100 * currentLength / totalLength));
            }
            //下载完成，并返回保存的文件路径
            downloadListener.onFinish(file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            downloadListener.onFail("IOException");
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //保存到本地
    public static void savePhoto(Context context, Bitmap bitmap) {
        File photoDir = new File(Environment.getExternalStorageDirectory(), "MeiTuanApp");
        if (!photoDir.exists()) {
            photoDir.mkdirs();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File photo = new File(photoDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(photo);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        updatePhotoMedia(photo, context);
    }

    public static void downloadPicture(String url, final DownloadListener downloadListener, boolean isDel) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://res.ytaxx.com/")
                //通过线程池获取一个线程，指定callback在子线程中运行。
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .build();

        DownloadService service = retrofit.create(DownloadService.class);

        Call<ResponseBody> call = service.download(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull final Response<ResponseBody> response) {
                //将Response写入到从磁盘中，详见下面分析
                //注意，这个方法是运行在子线程中的

                writeResponseToDisk(AppUtils.getPictureDri() + url.substring(url.lastIndexOf("/")) + ".jpg", response, downloadListener, isDel);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable) {
                downloadListener.onFail("网络错误");
            }
        });
    }


    //更新图库
    public static void updatePhotoMedia(File file, Context context) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            saveImageToGallery2(context, file);
        } else {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setData(Uri.fromFile(file));
            context.sendBroadcast(intent);
        }
//        ToastUtil.showToastLong(context, "成功保存到相册");
    }

    public static void saveImageToGallery2(Context context, File image) {
        Long mImageTime = System.currentTimeMillis();
        String imageDate = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date(mImageTime));
        String SCREENSHOT_FILE_NAME_TEMPLATE = "alpaca_%s.png";//图片名称，以"winetalk"+时间戳命名
        String mImageFileName = String.format(SCREENSHOT_FILE_NAME_TEMPLATE, imageDate);

        final ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES
                + File.separator + "image"); //Environment.DIRECTORY_SCREENSHOTS:截图,图库中显示的文件夹名。"dh"
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, mImageFileName);
        values.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
        values.put(MediaStore.MediaColumns.DATE_ADDED, mImageTime / 1000);
        values.put(MediaStore.MediaColumns.DATE_MODIFIED, mImageTime / 1000);
        values.put(MediaStore.MediaColumns.DATE_EXPIRES, (mImageTime + DateUtils.DAY_IN_MILLIS) / 1000);
        values.put(MediaStore.MediaColumns.IS_PENDING, 1);

        ContentResolver resolver = context.getContentResolver();
        final Uri uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        try {
            // First, write the actual data for our screenshot
            try (OutputStream out = resolver.openOutputStream(uri)) {
                FileInputStream fileInputStream = new FileInputStream(image);
                FileUtils.copyFile(fileInputStream, out);
                fileInputStream.close();
                out.close();
            }
            // Everything went well above, publish it!
            values.clear();
            values.put(MediaStore.MediaColumns.IS_PENDING, 0);
            values.putNull(MediaStore.MediaColumns.DATE_EXPIRES);
            resolver.update(uri, values, null, null);
        } catch (IOException e) {
            resolver.delete(uri, null, null);
        }
    }


    //更新图库
    public static void updatePhotoMedia(String path, Context context) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(new File(path)));
        context.sendBroadcast(intent);
//        ToastUtil.showToastLong(context, "成功保存到相册");
    }

    public static String isExits(String url) {
        File file = new File(AppUtils.getPictureDri() + url.substring(url.lastIndexOf("/")) + ".jpg");
        if (file.exists()) {
            return file.getPath();
        } else {
            return null;
        }
    }


}

