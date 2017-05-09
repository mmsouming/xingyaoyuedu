package com.fancyfamily.primarylibrary.commentlibrary.ui.comment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

import java.io.Serializable;

/**
 * Created by DavidWang on 15/8/31.
 */
public class ImageInfo implements Serializable {

    public String url;
    public int type = 1; // 1 url 0 本地
    public float width;
    public float height;
    public String souceurl;
    public void setUrlandType(String url, int type) {
        this.souceurl = url;
        this.type = type;
        if (type == 1) {
            this.url = url;
            width = 1280;
            height = 720;
        } else {
            this.url = "file://" +url;
            Options opts = new Options();// 解析位图的附加条件
            opts.inJustDecodeBounds = true;// 不去解析真实的位图，只是获取这个位图的头文件信息
            Bitmap bitmap = BitmapFactory.decodeFile(url, opts);
            int bitmapWidth = opts.outWidth;
            int bitmapHeight = opts.outHeight;

            // 3.计算缩放比例
            int dx = bitmapWidth / 1280;
            int dy = bitmapHeight / 720;
            int scale = 1;
            if (dx > dy && dy > 1) {

                scale = dx;
            }

            if (dy > dx && dx > 1) {

                scale = dy;
            }
            width = bitmapWidth / scale;
            height = bitmapHeight / scale;

        }
    }

    @Override
    public String toString() {
        return "ImageInfo{" + "url='" + url + '\'' + ", width=" + width
                + ", height=" + height + '}';
    }
}
