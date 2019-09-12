package com.itheima.loadbigimg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView lv;
    private int height;
    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        lv = (ImageView)findViewById(R.id.lv);

        //[1]获取手机的分辨率
        WindowManager wm= (WindowManager) getSystemService(WINDOW_SERVICE);
        //获取高
        height = wm.getDefaultDisplay().getHeight();
        //获取宽
        width = wm.getDefaultDisplay().getWidth();
        System.out.println("width:"+ width +"height:"+ height);



    }

    public void btn(View view) {

        //创建一个位图工厂的配置参数
        BitmapFactory.Options opts= new BitmapFactory.Options();
        //这个参数表示解码器不去真正的解析位图,但是还能够获取图片的宽和高信息
        opts.inJustDecodeBounds=true;
        BitmapFactory.decodeFile("/mnt/sdcard/tree.jpg",opts);

        //[2] 获取图片的宽和高
        int imgWidth = opts.outWidth;
        int imgHeight = opts.outHeight;
        System.out.println("图片的宽: "+imgWidth+"图片的高:  " + imgHeight);

        //[3]计算缩放比
        int scale=1;
        int scaleX=imgWidth/width;
        int scaleY= imgHeight/height;
        if (scaleX>scaleY&&scaleX>scale)
        {
            scale=scaleX;
        }
        else if (scaleY>scaleX&&scaleY>scale)
        {
            scale=scaleY;
        }
        System.out.println("缩放比为:"+scale);

        //[4] 按照缩放比显示
        opts.inSampleSize=scale;
        //[5] 按照缩放比解析位图
        opts.inJustDecodeBounds=false;
        Bitmap bitmap=BitmapFactory.decodeFile("/mnt/sdcard/tree.jpg",opts);
        //[6]把bitmap显示在lv上
        lv.setImageBitmap(bitmap);
//        System.out.println("bitmap "+bitmap);


    }
}
