package com.example.a30797.hljunavigationsystem.imageProcessing;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.example.a30797.hljunavigationsystem.activities.MainActivity;
import com.example.a30797.hljunavigationsystem.attractions.Scenic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ImageProcessing {

    public static Bitmap ChangeXY(Scenic scenic, Activity activity){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 6;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(),scenic.getImageId(),options);
        if (bitmap.getRowBytes() * bitmap.getHeight() > 1024 ){
            bitmap = compressImage(bitmap);
        }
        return bitmap;
    }

    private static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while ( baos.toByteArray().length / 1024>100) { //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 50;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    public static Bitmap ChangBitmapSize(Bitmap bitmap){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        //放大為屏幕的1/2大小
        float screenWidth  = MainActivity.mainActivity.getWindowManager().getDefaultDisplay().getWidth();		// 屏幕宽（像素，如：480px）
        float screenHeight = MainActivity.mainActivity.getWindowManager().getDefaultDisplay().getHeight();		// 屏幕高（像素，如：800p）

        float scaleWidth = screenWidth/25/width;
        float scaleHeight = screenWidth/25/width;
        // 取得想要缩放的matrix參數
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的圖片
        Bitmap newbm = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,true);
        return newbm;
    }

    /*
    动态设置图片大小
     */
    public static Bitmap ChangBitmapSize(Bitmap bitmap , float scaleWidth , float scaleHeight){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        //放大為屏幕的1/2大小
        float screenWidth  = MainActivity.mainActivity.getWindowManager().getDefaultDisplay().getWidth();		// 屏幕宽（像素，如：480px）
        float screenHeight = MainActivity.mainActivity.getWindowManager().getDefaultDisplay().getHeight();		// 屏幕高（像素，如：800p）

        scaleWidth = screenWidth/17/width;
        scaleHeight = screenWidth/17/width;
        // 取得想要缩放的matrix參數
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的圖片
        Bitmap newbm = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,true);
        return newbm;
    }

}
