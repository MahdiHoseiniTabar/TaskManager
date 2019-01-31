package com.example.caspian.taskmanager;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;

public class PictureUtils {
    public static Bitmap getScaleBitmap(String path, int destWith, int destHeight) {
       /* BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        int srcWith = options.outWidth;
        int srcHight = options.outHeight;
        int samplSize = 1;
        if (srcHight > destHeight || srcWith > destWith) {
            float with = (srcWith / destWith)*10;
            float hight = (srcHight / destHeight)*10;
            samplSize = Math.round((hight > with) ? hight : with);
        }
        options = new BitmapFactory.Options();
        options.inSampleSize = samplSize;*/

        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(path),destWith,destHeight,false);
        return  Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
    }

    public static Bitmap getScaleBitmap(String path, Activity activity) {
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(point);
        int destWith = point.x;
        int destHeight = point.y;
        return getScaleBitmap(path, destWith, destHeight);
    }
}
