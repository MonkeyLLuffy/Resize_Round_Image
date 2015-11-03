package com.example.liuqun.test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    private int miniWidth = 80;
    private ImageView imageView;
    private TextView textView1,textView2;
    private SeekBar resize,round;
    private Matrix matrix = new Matrix();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView1 = (TextView) findViewById(R.id.textview1);
        textView2 = (TextView) findViewById(R.id.textview2);
        resize = (SeekBar) findViewById(R.id.resize);
        round = (SeekBar) findViewById(R.id.round);
        resize.setOnSeekBarChangeListener(this);
        round.setOnSeekBarChangeListener(this);
        resize.setMax(displayMetrics.widthPixels - miniWidth);

    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    if (seekBar.getId() == R.id.resize){
        int newWidth = progress+miniWidth;
        int newHeight = (int) (newWidth * 3 / 4);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(newWidth,newHeight));
        textView1.setText("new Width:"+newWidth+", new Height:"+newHeight);
    } else   if (seekBar.getId() == R.id.round) {
            Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.luffy)).getBitmap();
            matrix.setRotate(progress);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            imageView.setImageBitmap(bitmap);
            textView2.setText(progress + "度");
        }
    }





    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
