package com.alpaca.library_base.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author chenzj
 * @Title: AESUtil
 * @Description: 类的描述 - Aes加密工具类
 * @date 2017/6/27 13:29
 * @email admin@chenzhongjin.cn
 */
public class AESUtil {

    static final String algorithmStr = "AES/ECB/PKCS5Padding";

    static private KeyGenerator mKeyGen;

    static private Cipher mCipher;

    static boolean isInit = false;

    private static void init() {
        init(128);
    }

    private static void init(int keySize) {
        try {
            mKeyGen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 初始化此密钥生成器，使其具有确定的密钥长度。
        mKeyGen.init(keySize); // 128位的AES加密
        try {
            // 生成一个实现指定转换的 Cipher 对象。
            mCipher = Cipher.getInstance(algorithmStr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        // 标识已经初始化过了的字段
        isInit = true;
    }

    private static byte[] genKey() {
        if (!isInit) {
            init();
        }
        // 首先 生成一个密钥(SecretKey),
        // 然后,通过这个秘钥,返回基本编码格式的密钥，如果此密钥不支持编码，则返回 null。
        return mKeyGen.generateKey().getEncoded();
    }

    private static byte[] encrypt(byte[] content, byte[] keyBytes) {
        byte[] encryptedText = null;
        if (!isInit) {
            init();
        }
        /**
         * 类 SecretKeySpec 可以使用此类来根据一个字节数组构造一个 SecretKey， 而无须通过一个（基于 provider
         * 的）SecretKeyFactory。 此类仅对能表示为一个字节数组并且没有任何与之相关联的钥参数的原始密钥有用
         * 构造方法根据给定的字节数组构造一个密钥。 此构造方法不检查给定的字节数组是否指定了一个算法的密钥。
         */
        Key key = new SecretKeySpec(keyBytes, "AES");
        try {
            // 用密钥初始化此 mCipher。
            mCipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
            // 按单部分操作加密或解密数据，或者结束一个多部分操作。(不知道神马意思)
            encryptedText = mCipher.doFinal(content);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return encryptedText;
    }

    private static byte[] encrypt(String content, String password) {
        try {
            byte[] keyStr = getKey(password);
            SecretKeySpec key = new SecretKeySpec(keyStr, "AES");
            Cipher cipher = Cipher.getInstance(algorithmStr);// algorithmStr
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// ʼ
            byte[] result = cipher.doFinal(byteContent);
            return result; //
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] decrypt(byte[] content, String password) {
        try {
            byte[] keyStr = getKey(password);
            SecretKeySpec key = new SecretKeySpec(keyStr, "AES");
            Cipher cipher = Cipher.getInstance(algorithmStr);// algorithmStr
            cipher.init(Cipher.DECRYPT_MODE, key);// ʼ
            byte[] result = cipher.doFinal(content);
            return result; //
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] getKey(String password) {
        byte[] rByte = null;
        if (password != null) {
            rByte = password.getBytes();
        } else {
            rByte = new byte[24];
        }
        return rByte;
    }

    /**
     * 将二进制转换成字符串
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        String encoded = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encoded = Base64.getEncoder().encodeToString(buf);
        }
        return encoded;
    }

    /**
     * 将字符串转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] decoded = new byte[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            decoded = Base64.getDecoder().decode(hexStr);
        }
        return decoded;
    }

    // 注意: 这里的password(秘钥必须是16位的)
    private static final String keyBytes = "validationrealna";

    /**
     * 加密
     */
    public static String encode(String content) {
        return encode(content, keyBytes);
    }

    public static String encode(String content, String keyStr) {
        // 加密之后的字节数组,转成16进制的字符串形式输出
        return parseByte2HexStr(encrypt(content, keyStr));
    }

    /**
     * 解密
     */
    public static String decode(String content) {
        return decode(content, keyBytes);
    }

    public static String decode(String content, String keyStr) {
        // 解密之前,先将输入的字符串按照16进制转成二进制的字节数组,作为待解密的内容输入
        byte[] b = decrypt(parseHexStr2Byte(content), keyStr);
        return new String(b);
    }
}
