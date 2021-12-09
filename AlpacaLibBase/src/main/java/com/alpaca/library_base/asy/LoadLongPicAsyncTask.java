package com.alpaca.library_base.asy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.alpaca.library_base.event.LoadLongPicEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class LoadLongPicAsyncTask extends AsyncTask<String, Void, ArrayList<Bitmap>> {

    @Override
    protected ArrayList<Bitmap> doInBackground(String... strings) {
        ArrayList<Bitmap> bitmapList = new ArrayList<>();
        if (TextUtils.isEmpty(strings[0])) {
            return bitmapList;
        }
        try {
            URL url=new URL(strings[0]);

            URLConnection connection=url.openConnection();

            connection.connect();

            InputStream is = connection.getInputStream();

            BitmapFactory.Options options = new BitmapFactory.Options();

            Bitmap bitmap = BitmapFactory.decodeStream(is,null,options);
            if (bitmap != null) {
                int index=bitmap.getHeight()/2000+1;
                for(int i=0;i<index;i++){
                    Bitmap bitmap3= Bitmap.createBitmap(bitmap,
                            0,
                            (int) ((double)bitmap.getHeight()/index*i),
                            bitmap.getWidth(),
                            (int) ((double) bitmap.getHeight()/index) - 1);//高度减1为了生成一个没有被回收的bitmap

                    bitmapList.add(bitmap3);

                }
                //释放bitmap 不然会造成内存泄漏
                bitmap.recycle();
                bitmap=null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmapList;
    }

    @Override
    protected void onPostExecute(ArrayList<Bitmap> bitmaps) {
        super.onPostExecute(bitmaps);
        EventBus.getDefault().post(new LoadLongPicEvent(bitmaps));
    }
}
