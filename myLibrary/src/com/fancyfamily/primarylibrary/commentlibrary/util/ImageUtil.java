package com.fancyfamily.primarylibrary.commentlibrary.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.media.ExifInterface;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtil {

    private static Bitmap compressBmpFromBmp(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 100;
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        
        CommonUtils.Tlog("ffpic", "压缩前bit："+ image.getByteCount() / 1024f + "KB");
        CommonUtils.Tlog("ffpic", "压缩前baos：" + baos.toByteArray().length / 1024f + "KB");
        while (baos.toByteArray().length / 1024 > 100) {

            baos.reset();
            options -= 10;
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        CommonUtils.Tlog("ffpic", "压缩后baos：" + baos.toByteArray().length / 1024f + "KB");
        ByteArrayInputStream isBm = new ByteArrayInputStream(
                baos.toByteArray());
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        CommonUtils.Tlog("ffpic", "压缩后bit：" + bitmap.getByteCount() / 1024f + "KB");
        return bitmap;
    }

    public static ByteArrayOutputStream imgToStream(Activity context, String imgPath
    ) {
        ByteArrayOutputStream baos = null;
        if (imgPath != null && imgPath.length() > 0) {
            // 裁图
            long time = System.currentTimeMillis();
            CommonUtils.Tlog("ffpic", "裁图开始"+time+"");
            Bitmap bitmap = readBitmap(context, imgPath);
            CommonUtils.Tlog("ffpic","裁图结束"+( System.currentTimeMillis()-time)+"");
            time = System.currentTimeMillis();
            // 降质量
            CommonUtils.Tlog("ffpic","降质开始"+( System.currentTimeMillis()-time)+"");
            time = System.currentTimeMillis();
            baos = new ByteArrayOutputStream();
            int options = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            CommonUtils.Tlog("ffpic", "压缩前：" + baos.toByteArray().length / 1024f + "KB");
            while (baos.toByteArray().length / 1024 > 100) {

                baos.reset();
                options -= 20;
                bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
            }
            CommonUtils.Tlog("ffpic", "压缩后：" + baos.toByteArray().length / 1024f + "KB");
            CommonUtils.Tlog("ffpic","降质结束"+( System.currentTimeMillis()-time)+"");
            time = System.currentTimeMillis();
        }
        return baos;
    }

    public static Bitmap getSmallBitMap(Activity context, String imgPath
    ) {
        Bitmap bitmap = null;
        if (imgPath != null && imgPath.length() > 0) {
            // 裁图
            bitmap = readBitmap(context, imgPath);

            bitmap = compressBmpFromBmp(bitmap);
        }
        return bitmap;
    }

    /**
     *
     * @param imgPath
     * @param bitmap
     *            返回bitmap
     * @param imgFormat
     *            图片格式
     * @return
     */
    public static String imgToBase64(Activity context, String imgPath,
                                     Bitmap bitmap, String imgFormat) {
        if (imgPath != null && imgPath.length() > 0) {
            // 裁图
            bitmap = readBitmap(context, imgPath);
        }
        if (bitmap != null) {
            // 降质量
            bitmap = compressBmpFromBmp(bitmap);
        }
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

            out.flush();
            out.close();

            byte[] imgBytes = out.toByteArray();
            String result = Base64.encodeToString(imgBytes, Base64.DEFAULT);

            CommonUtils.Tlog("ffpic",
                    "base64编码后：" + result.getBytes().length / 1024f + "KB");
            return result;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static String readBitmapSuffix(String imgPath) {
        // BitmapFactory.Options opts = new Options();//解析位图的附加条件
        // opts.inJustDecodeBounds = true;//不去解析真实的位图，只是获取这个位图的头文件信息
        // Bitmap bitmap = BitmapFactory.decodeFile(imgPath, opts);
        // Log.v("fftime", opts.outMimeType);
        // return opts.outMimeType;
        File f = new File(imgPath);
        String fileName = f.getName();
        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
        CommonUtils.Tlog("ffpic", prefix);
        return prefix;
    }

    public static Bitmap readBitmap(Activity context, String imgPath) {
        // 1.得到屏幕的宽高信息
//        WindowManager wm = context.getWindowManager();
//        int screenWidth = wm.getDefaultDisplay().getWidth();
//        int screenHeight = wm.getDefaultDisplay().getHeight();
        return readBitmap(imgPath, 800, 800);
    }

    public static Bitmap readBitmap(String imgPath, int width, int height) {
        try {
            // 2.得到图片的宽高。
            BitmapFactory.Options opts = new Options();// 解析位图的附加条件
            opts.inJustDecodeBounds = true;// 不去解析真实的位图，只是获取这个位图的头文件信息
            Bitmap bitmap = BitmapFactory.decodeFile(imgPath, opts);
            int bitmapWidth = opts.outWidth;
            int bitmapHeight = opts.outHeight;
            CommonUtils.Tlog("ffpic", "图片宽高： " + bitmapWidth + "-" + bitmapHeight);
            // 3.计算缩放比例
            int dx = bitmapWidth / width;
            int dy = bitmapHeight / height;
            int scale = 1;
            if (dx > dy && dy > 1) {
                CommonUtils.Tlog("ffpic", "按照水平方法缩放,缩放比例：" + dx);
                scale = dx;
            }

            if (dy > dx && dx > 1) {
                CommonUtils.Tlog("ffpic", "按照垂直方法缩放,缩放比例：" + dy);
                scale = dy;
            }
            // 4.缩放加载图片到内存。
            opts.inSampleSize = (int) (scale);

            opts.inJustDecodeBounds = false;// 真正的去解析这个位图。
            Bitmap b = BitmapFactory.decodeFile(imgPath, opts);
            // 获取旋转角度
            int degree = ImageUtil.readPictureDegree(imgPath);
            if (degree > 0) {
                b = ImageUtil.rotaingImageView(degree, b);
            }
            CommonUtils.Tlog("ffpic", "压缩后宽：" + b.getWidth() + "   高：" + b.getHeight()
                    + "  degree : " + degree + "scale:" + scale);

            return b;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        }

    }

    /**
     *
     * @param base64Data
     * @param imgName
     * @param imgFormat
     *            图片格式
     */
    public static void base64ToBitmap(String base64Data, String imgName,
                                      String imgFormat) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        File myCaptureFile = new File("/sdcard/", imgName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myCaptureFile);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        boolean isTu = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        if (isTu) {
            // fos.notifyAll();
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            try {
                fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private static final int UPLOAD_IMAGE_MIN_SIDE = 600;
    private static final int UPLOAD_IMAGE_MAX_QUALITY = 70;
    private static final int UPLOAD_IMAGE_MAX_LEGHT = 70 * 1024;

    /**
     * 以最省内存的方式读取本地资源的图片
     *
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    /**
     * 拍照完之后图片矫正,覆盖原图
     */
    public static void redressImage(String path) {
        // 判断路径是否为空
        if (TextUtils.isEmpty(path)) {
            return;
        }
        File file = new File(path);
        // 判断文件是否存在
        if (!file.exists()) {
            return;
        }
        int degree = ImageUtil.readPictureDegree(path);
        // 判断是否需要旋转
        if (degree == 0) {
            return;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        Bitmap b = BitmapFactory.decodeFile(path, options);
        Bitmap bitMap = ImageUtil.rotaingImageView(degree, b);
        b.recycle();
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            bitMap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                    bitMap.recycle();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fileOutputStream = null;
            }
        }
    }

    /**
     * 旋转图片
     */
    private static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        // 旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path
     *            图片绝对路径
     * @return degree旋转的角度
     */
    private static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public static Bitmap getAssetsImage(Context mContext, String path) {
        AssetManager mngr = mContext.getAssets();
        InputStream in = null;

        try {
            in = mngr.open(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // BitmapFactory.Options options = new BitmapFactory.Options();
        // options.inSampleSize = chunks;
        Bitmap temp = BitmapFactory.decodeStream(in, null, null);
        return temp;
    }

    // 获得阴影图片的方法
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static Bitmap getShadowBitmap(Bitmap originalBitmap) {

        int shadow = 8;
        BlurMaskFilter blurFilter = new BlurMaskFilter(shadow,
                BlurMaskFilter.Blur.NORMAL);

        Paint shadowPaint = new Paint();
        shadowPaint.setAlpha(50);
        shadowPaint.setColor(Color.LTGRAY);
        shadowPaint.setMaskFilter(blurFilter);

        int[] offsetXY = new int[2];
        Bitmap shadowBitmap = originalBitmap.extractAlpha(shadowPaint,
                offsetXY);

        Bitmap shadowImage32 = shadowBitmap.copy(Bitmap.Config.ARGB_8888, true);
        if (!shadowImage32.isPremultiplied()) {
            shadowImage32.setPremultiplied(true);
        }
        Canvas c = new Canvas(shadowImage32);
        c.drawBitmap(originalBitmap, offsetXY[0] + 10, offsetXY[1] + 10, null);

        return shadowImage32;
    }

    // 获得圆角图片的方法
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    // 获得带倒影的图片方法
    public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
        final int reflectionGap = 4;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2,
                width, height / 2, matrix, false);

        Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
                (height + height / 2), Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmapWithReflection);
        canvas.drawBitmap(bitmap, 0, 0, null);
        Paint deafalutPaint = new Paint();
        canvas.drawRect(0, height, width, height + reflectionGap,
                deafalutPaint);

        canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
                bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
                0x00ffffff, TileMode.CLAMP);
        paint.setShader(shader);
        // Set the Transfer mode to be porter duff and destination in
        paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        // Draw a rectangle using the paint with our linear gradient
        canvas.drawRect(0, height, width,
                bitmapWithReflection.getHeight() + reflectionGap, paint);

        return bitmapWithReflection;
    }

}