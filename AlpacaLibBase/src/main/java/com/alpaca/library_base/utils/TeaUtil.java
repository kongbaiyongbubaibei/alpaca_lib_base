package com.alpaca.library_base.utils;

/**
 * Tea算法
 * 每次操作可以处理8个字节数据
 * KEY为16字节,应为包含4个int型数的int[]，一个int为4个字节
 * 加密解密轮数应为8的倍数，推荐加密轮数为64轮
 * */
public class TeaUtil {
    private final static int[] KEY = new int[]{//加密解密所用的KEY
            0x789f5645, 0xf68bd5a4,
            0x81963ffa, 0x458fac58
    };

//    private final static int[] KEY = GetKey();

    private static int[] GetKey(){
        String str = "ytedu";
        char[] arr = new char[str.length()];
        int[] arr2 = new int[str.length()];
        arr = str.toCharArray();
        for(int i=0; i<str.length(); i++){
            arr2[i] = arr[i] - 48;//数字0~9的ASCII码为48~57
        }
        return arr2;
    }


    //加密
    public static byte[] encrypt(byte[] content, int offset, int[] key, int times){//times为加密轮数
        int[] tempInt = byteToInt(content, offset);
        int y = tempInt[0], z = tempInt[1], sum = 0, i;
        int delta=0x9e3779b9; //这是算法标准给的值
        int a = key[0], b = key[1], c = key[2], d = key[3];

        for (i = 0; i < times; i++) {

            sum += delta;
            y += ((z<<4) + a) ^ (z + sum) ^ ((z>>5) + b);
            z += ((y<<4) + c) ^ (y + sum) ^ ((y>>5) + d);
        }
        tempInt[0]=y;
        tempInt[1]=z;
        return intToByte(tempInt, 0);
    }
    //byte[]型数据转成int[]型数据
    private static int[] byteToInt(byte[] content, int offset){

        int[] result = new int[content.length >> 2];//除以2的n次方 == 右移n位 即 content.length / 4 == content.length >> 2
        for(int i = 0, j = offset; j < content.length; i++, j += 4){
            result[i] = transform(content[j + 3]) | transform(content[j + 2]) << 8 |
                    transform(content[j + 1]) << 16 | (int)content[j] << 24;
        }
        return result;

    }
    //int[]型数据转成byte[]型数据
    private static byte[] intToByte(int[] content, int offset){
        byte[] result = new byte[content.length << 2];//乘以2的n次方 == 左移n位 即 content.length * 4 == content.length << 2
        for(int i = 0, j = offset; j < result.length; i++, j += 4){
            result[j + 3] = (byte)(content[i] & 0xff);
            result[j + 2] = (byte)((content[i] >> 8) & 0xff);
            result[j + 1] = (byte)((content[i] >> 16) & 0xff);
            result[j] = (byte)((content[i] >> 24) & 0xff);
        }
        return result;
    }
    //若某字节为负数则需将其转成无符号正数
    private static int transform(byte temp){
        int tempInt = (int)temp;
        if(tempInt < 0){
            tempInt += 256;
        }
        return tempInt;
    }

    //通过TEA算法加密信息
    public static byte[] encryptByTea(String info){
        byte[] temp = info.getBytes();
        int n = 8 - temp.length % 8;//若temp的位数不足8的倍数,需要填充的位数
        byte[] encryptStr = new byte[temp.length + n];
        encryptStr[0] = (byte)n;
        System.arraycopy(temp, 0, encryptStr, n, temp.length);
        byte[] result = new byte[encryptStr.length];
        for(int offset = 0; offset < result.length; offset += 8){
            byte[] tempEncrpt = TeaUtil.encrypt(encryptStr, offset, KEY, 32);
            System.arraycopy(tempEncrpt, 0, result, offset, 8);
        }
        return result;
    }
    //通过TEA算法解密信息


    //通过TEA算法加密信息
    public static String encrypt(String info){
        byte[] result = encryptByTea(info);
        return byteArrayToHexStr(result);
    }


    public static String byteArrayToHexStr(byte[] byteArray) {
        if (byteArray == null){
            return null;
        }
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[byteArray.length * 2];
        for (int j = 0; j < byteArray.length; j++) {
            int v = byteArray[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] hexStrToByteArray(String str)
    {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return new byte[0];
        }
        byte[] byteArray = new byte[str.length() / 2];
        for (int i = 0; i < byteArray.length; i++){
            String subStr = str.substring(2 * i, 2 * i + 2);
            byteArray[i] = ((byte)Integer.parseInt(subStr, 16));
        }
        return byteArray;
    }
}
