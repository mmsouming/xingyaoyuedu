package com.fancyfamily.primarylibrary.commentlibrary.util;

import android.app.Activity;
import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileCache {

    private File cacheDir;
    Context context;

    public FileCache(Context context) {
        this.context = context;
        if (android.os.Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir = new File(
                    android.os.Environment.getExternalStorageDirectory(),
                    "fancyfamily");
        else
            cacheDir = context.getCacheDir();
        if (!cacheDir.exists())
            cacheDir.mkdirs();
    }

    public FileCache(Context context, String dir) {

        if (android.os.Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir = new File(
                    android.os.Environment.getExternalStorageDirectory(),
                    "fancyfamily/" + dir);
        else
            cacheDir = context.getCacheDir();
        if (!cacheDir.exists())
            cacheDir.mkdirs();
    }

    public File getFile(String url) {

        String filename = String.valueOf(url.hashCode());
        File f = new File(cacheDir, filename);
        return f;

    }

    public void clear() {
        File[] files = cacheDir.listFiles();
        if (files == null)
            return;
        for (File f : files)
            f.delete();
    }

    public File getCacheDir() {

        return this.cacheDir;

    }

    /**
     * 获取Cache目录文件总共大小
     *
     * @param file
     * @return
     */
    public long getCacheSize(File file) {
        long size = 0;
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                size = size + getCacheSize(files[i]);
            } else {
                size = size + files[i].length();
            }
        }
        return size;
    }

    /**
     * 获取Cache目录文件总共大小(格式化)
     *
     * @param size
     * @return
     */
    public String formatCacheSize(File file) {
        if (file.exists()) {
            double Dsize = Double.valueOf(getCacheSize(file));
            double B = 1024;
            double K = 1048576;
            if (Dsize < K) {
                return Double.parseDouble(String.format("%.2f", Dsize / B))
                        + "K";
            } else {
                return Double.parseDouble(String.format("%.2f", Dsize / K))
                        + "M";
            }
        }
        return "";
    }

    /**
     * 删除cache目录下所有文件
     *
     * @param file
     * @return
     */
    public boolean deleteCache(File file) {
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                deleteCache(files[i]);
            } else {
                files[i].delete();
            }
        }
        return true;
    }

    public boolean deleteCache(String fileName) {
        File f = new File(cacheDir, fileName);
        f.delete();
        return true;
    }

    /**
     * 写入JSON 文件
     *
     * @param fileName
     * @param content
     */
    public void writeJsonFile(String fileName, String content) {
        writeJsonFile(fileName, content, false);
    }

    /**
     * 写入JSON 文件
     *
     * @param fileName
     * @param content
     */
    public void writeJsonFile(String fileName, String content,
                              boolean isAppend) {

        try {

            File f = new File(cacheDir, fileName);
            FileWriter fw = new FileWriter(f.getAbsolutePath(), isAppend);
            PrintWriter out = new PrintWriter(fw);
            out.write(content);
            out.println();
            fw.close();
            out.close();
        } catch (IOException e) {

            // e.printStackTrace();
        }

    }

    /**
     * 读取json文件
     *
     * @param fileName
     * @return
     */
    public String readJsonFile(String fileName) {
        String fileContentStr = "";
        try {

            File f = new File(cacheDir, fileName);
            FileInputStream inStream = new FileInputStream(f);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = inStream.read(buffer)) != -1) {
                stream.write(buffer, 0, length);
            }
            stream.close();
            inStream.close();
            fileContentStr = stream.toString();
        } catch (IOException e) {
            // e.printStackTrace();
        }
        return fileContentStr;
    }

    public List<File> saveBitMaps(List<String> sources) {
        List<File> targets = new ArrayList<File>();
        for (String file : sources) {
            if (!file.equals("")) {
                targets.add(saveBitmapFile(file));
            }
        }
        return targets;
    }

    public File saveBitmapFile(String souceFile) {

        long time = System.currentTimeMillis();
        CommonUtils.Tlog("ffpic", "开始"+time+"");

        File file = new File(cacheDir,
                MD5Util.getMd5(souceFile) +"."+ ImageUtil.readBitmapSuffix(souceFile));// 将要保存图片的路径
        try {
            ByteArrayOutputStream out = ImageUtil
                    .imgToStream((Activity) context, souceFile);
            CommonUtils.Tlog("ffpic","压缩后"+( System.currentTimeMillis()-time)+"");
            time = System.currentTimeMillis();

            if (out != null) {
                FileOutputStream bos = new FileOutputStream(file);

                out.writeTo(bos);
                bos.flush();
                bos.close();
                out.close();

            }
            CommonUtils.Tlog("ffpic","存贮"+( System.currentTimeMillis()-time)+"");
            time = System.currentTimeMillis();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
}