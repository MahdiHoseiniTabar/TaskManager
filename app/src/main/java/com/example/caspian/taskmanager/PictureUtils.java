package com.example.caspian.taskmanager;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

public class PictureUtils {
    public static Bitmap getScaleBitmap(String path, int destWith, int destHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        int srcWith = options.outWidth;
        int srcHight = options.outHeight;
        int samplSize = 1;
        if (srcHight > destHeight || srcWith > destWith) {
            float with = srcWith / destWith;
            float hight = srcHight / destHeight;
            samplSize = Math.round((hight > with) ? hight : with);
        }
        options = new BitmapFactory.Options();
        options.inSampleSize = samplSize;
        return BitmapFactory.decodeFile(path, options);
    }

    public static Bitmap getScaleBitmap(String path, Activity activity) {
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(point);
        int destWith = point.x;
        int destHeight = point.y;
        return getScaleBitmap(path, destWith, destHeight);
    }
}
