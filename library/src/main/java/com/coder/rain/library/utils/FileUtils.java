package com.coder.rain.library.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

/**
 * Describe : 文件相关助手类
 * Email:baossrain99@163.com
 * Created by Rain on 17-5-10.
 */
public class FileUtils {

    private static final String TAG = "FileUtils";

/*
    //获取app内部存储文件
    //路径为/data/data/package_name/files
    File filesDir = context.getFilesDir();
    //获取app内部存储文件
    //路径为/data/data/package_name/cache
    File cacheDir = context.getCacheDir();

    //检查外部存储状态
    Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    //获取外部存储的私有文件
    //路径为sdcard/Android/date/package_name/
    File externalCacheDir = context.getExternalCacheDir();
    File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

    //获取外部公共存储目录
    File externalStorageDirectory = Environment.getExternalStorageDirectory();
    //需要带有一个特定的参数来指定这些public的文件类型
    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);*/


    /**
     * 默认保存图片的路径
     *
     * @param context
     * @return
     */
    public static String getFilePath(Context context) {
        return getFilePath(context, "/data/images/");
    }

    /**
     * 保存文件的路径
     *
     * @param context
     * @param dir
     * @return
     */
    public static String getFilePath(Context context, String dir) {
        String path = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String appName = context.getPackageName();
            path = Environment.getExternalStorageDirectory() + "/" + appName + dir;
        }
        if (TextUtils.isEmpty(path))
            context.getCacheDir().getPath();
        existsFolder(path);
        return path;
    }

    /**
     * 判断文件夹是否存在,不存在则创建
     *
     * @param path
     */
    public static void existsFolder(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 文件保存
     *
     * @param context  上下文
     * @param filePath 文件路径
     */
    public static void saveFile(Context context, String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete(); // 删除原图片
        }
        String dir;
        if (!file.isFile()) {
            dir = filePath.substring(0, filePath.lastIndexOf("/"));
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                if (!dirFile.mkdirs()) {
                    return;
                }
            }
            FileOutputStream outputStream = null;
            try {
                file.createNewFile();
                outputStream = new FileOutputStream(file);
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.flush();
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 文件转byte[]数组
     *
     * @param filePath
     * @return
     */
    public static byte[] FileToByteArray(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * Bitmap转byte[]数组
     *
     * @param bmp
     * @param needRecycle
     * @return
     */
    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * InputStream转化为byte[]数组
     *
     * @param is
     * @return
     */
    public static byte[] inputStreamToByte(InputStream is) {
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            int ch;
            while ((ch = is.read()) != -1) {
                byteStream.write(ch);
            }
            byte imgData[] = byteStream.toByteArray();
            byteStream.close();
            return imgData;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @return
     * @将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     */
    public static String imageToBase64(Bitmap bitmap) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] appicon = baos.toByteArray();// 转为byte数组
        return Base64.encodeToString(appicon, Base64.DEFAULT);
    }

    /**
     * 获取指定文件夹内录音文件
     * @param fileAbsolutePath
     * @return
     */
    public static Vector<File> getSoundsFile(String fileAbsolutePath) {
        Vector<File> vector = new Vector<>();
        File file = new File(fileAbsolutePath);
        File[] subFile = file.listFiles();
        for (int i = 0; i < subFile.length; i++) {
            //判断是否为文件夹
            if (!subFile[i].isDirectory()) {
                //判断是否是录音文件
                String fileName = subFile[i].getName();
                if (fileName.trim().toLowerCase().endsWith(".amr"))
                    vector.add(subFile[i]);
            }
        }
        return vector;
    }

    /**
     * 获取制定文件夹内照片文件
     * @param fileAbsolutePath
     * @return
     */
    public static Vector<File> getImageFile(String fileAbsolutePath) {
        Vector<File> vector = new Vector<>();
        File file = new File(fileAbsolutePath);
        File[] subFile = file.listFiles();
        for (int i = 0; i < subFile.length; i++) {
            //判断是否为文件夹
            if (!subFile[i].isDirectory()) {
                //判断是否是图片
                String fileName = subFile[i].getName();
                if (fileName.trim().toLowerCase().endsWith(".jpg"))
                    vector.add(subFile[i]);
            }
        }
        return vector;
    }

}

/*
 *   ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *     ┃　　　┃
 *     ┃　　　┃
 *     ┃　　　┗━━━┓
 *     ┃　　　　　　　┣┓
 *     ┃　　　　　　　┏┛
 *     ┗┓┓┏━┳┓┏┛
 *       ┃┫┫　┃┫┫
 *       ┗┻┛　┗┻┛
 *        神兽保佑
 *        代码无BUG!
 */
