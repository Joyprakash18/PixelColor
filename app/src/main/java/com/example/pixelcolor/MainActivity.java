package com.example.pixelcolor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button mCaptureButton;
    private Button mChangeColorButton;
    private ImageView mImageview;
    private int pixelColor;
    int[] pixelColorArray;
    ArrayList<Integer> color_vib = new ArrayList<Integer>();
    ArrayList<Integer> color_red = new ArrayList<Integer>();
    ArrayList<Integer> color_blue = new ArrayList<Integer>();
    ArrayList<Integer> color_green = new ArrayList<Integer>();
    ArrayList<Points> points = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCaptureButton = findViewById(R.id.pixelColorButton);
        mImageview = findViewById(R.id.imageView);
        mChangeColorButton = findViewById(R.id.changePixelColorButton);
        mCaptureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.sample2);
                for(int y=0; y<bmp.getHeight(); y=y+5){
                    for(int x=0; x<bmp.getWidth(); x=x+5){
                        pixelColor = bmp.getPixel(x,y);
                        int redValue = Color.red(pixelColor);
                        int blueValue = Color.blue(pixelColor);
                        int greenValue = Color.green(pixelColor);
                        color_vib.add(pixelColor);
                        color_red.add(redValue);
                        color_blue.add(blueValue);
                        color_green.add(greenValue);
                        Points point = new Points(x,y);
                        points.add(point);
                    }
                }
                Toast.makeText(MainActivity.this,"Done",Toast.LENGTH_LONG).show();
            }
        });
        mChangeColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.bio);
                Bitmap bitMap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());

                for(int i=0; i<bmp.getWidth(); i++){
                    for(int j=0; j<bmp.getHeight(); j++){
                        int p = bmp.getPixel(i, j);
                        if (i<1000) {
                            bitMap.setPixel(i, j, Color.rgb(0, 0, 255));
                        }else {
                            bitMap.setPixel(i, j, Color.rgb(Color.red(p), Color.green(p), Color.blue(p)));
                        }
                    }
                }
                mImageview.setImageBitmap(bitMap);
            }
        });
    }
}
