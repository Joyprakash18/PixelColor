package com.example.pixelcolor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button mCaptureButton;
    private Button mChangeColorButton;
    private ImageView mImageview;
    private ProgressBar mProgressBar;
    private int pixelColor;
    private EditText mXValue;
    private EditText mYValue;
    int[] pixelColorArray;
    ArrayList<Integer> color_vib = new ArrayList<Integer>();
    ArrayList<Integer> color_red = new ArrayList<Integer>();
    ArrayList<Integer> color_blue = new ArrayList<Integer>();
    ArrayList<Integer> color_green = new ArrayList<Integer>();
    ArrayList<Points> points = new ArrayList<>();
    ArrayList<com.example.pixelcolor.Color> colorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCaptureButton = findViewById(R.id.pixelColorButton);
        mImageview = findViewById(R.id.imageView);
        mChangeColorButton = findViewById(R.id.changePixelColorButton);
        mProgressBar = findViewById(R.id.progressBar);
        mXValue = findViewById(R.id.xValue);
        mYValue = findViewById(R.id.yValue);
        mCaptureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.sample4);
                for (int y = 0; y < bmp.getHeight(); y = y + 10) {
                    for (int x = 0; x < bmp.getWidth(); x = x + 10) {
                        pixelColor = bmp.getPixel(x, y);
                        int redValue = Color.red(pixelColor);
                        int blueValue = Color.blue(pixelColor);
                        int greenValue = Color.green(pixelColor);
                        color_vib.add(pixelColor);
                        String hex = String.format("#%02x%02x%02x", redValue, greenValue, blueValue);
                        com.example.pixelcolor.Color color = new com.example.pixelcolor.Color(redValue, greenValue, blueValue,hex);
                        colorList.add(color);
//                        color_red.add(redValue);
//                        color_blue.add(blueValue);
//                        color_green.add(greenValue);
                        Points point = new Points(x, y);
                        points.add(point);
                    }
                }
                Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_LONG).show();
            }
        });
        mChangeColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    @Override
                    public void run() {
                        changePixelColor();
                    }
                }.start();

//                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.bio);
//                Bitmap bitMap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
//
//                for(int i=0; i<bmp.getWidth(); i++){
//                    for(int j=0; j<bmp.getHeight(); j++){
//                        int p = bmp.getPixel(i, j);
//                        if (i<1000) {
//                            bitMap.setPixel(i, j, Color.rgb(0, 0, 255));
//                        }else {
//                            bitMap.setPixel(i, j, Color.rgb(Color.red(p), Color.green(p), Color.blue(p)));
//                        }
//                    }
//                }
//                mImageview.setImageBitmap(bitMap);
            }
        });

//        getSamePixelColor(0,0);
    }

    private void changePixelColor() {
        int x = 0;
        int y = 0;
        if (mXValue.getText().toString() != null && mYValue.getText().toString() != null) {
            x = Integer.parseInt(mXValue.getText().toString());
            y = Integer.parseInt(mYValue.getText().toString());
        }
        getSamePixelColor(x, y);
    }

    private void getSamePixelColor(int x, int y) {
//        mProgressBar.setVisibility(View.VISIBLE);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.sample4);
        int pixel = bmp.getPixel(x, y);
        int redValue = Color.red(pixel);
        int redValuemn = Color.red(pixel) - 5;
        int redValuemx = Color.red(pixel) + 5;
        int blueValue = Color.blue(pixel);
        int blueValuemn = Color.blue(pixel) - 5;
        int blueValuemx = Color.blue(pixel) + 5;
        int greenValue = Color.green(pixel);
        int greenValuemn = Color.green(pixel) - 5;
        int greenValuemx = Color.green(pixel) + 5;
        String hex = String.format("#%02x%02x%02x", redValue, greenValue, blueValue);
        com.example.pixelcolor.Color color = new com.example.pixelcolor.Color(redValue, greenValue, blueValue,hex);

        final Bitmap bitMap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
        for (int i = 0; i < bitMap.getWidth(); i++) {
            for (int j = 0; j < bitMap.getHeight(); j++) {
                int p = bmp.getPixel(i, j);
                int red = Color.red(p);
                int blue = Color.blue(p);
                int green = Color.green(p);
//                com.example.pixelcolor.Color color1 = new com.example.pixelcolor.Color(red, green, blue);
                if ((red >= redValuemn && red <= redValuemx) && (green >= greenValuemn && green <= greenValuemx) && (blue >= blueValuemn && blue <= blueValuemx)) {
                    bitMap.setPixel(i, j, Color.rgb(0, 0, 255));
                } else {
                    bitMap.setPixel(i, j, Color.rgb(Color.red(p), Color.green(p), Color.blue(p)));
                }
            }
        }
//        for (int i = 0; i <points.size() ; i++) {
//            int p = bmp.getPixel(points.get(i).getX(), points.get(i).getY());
//            if (color.equals(colorList.get(i))){
//                bitMap.setPixel(points.get(i).getX(), points.get(i).getY(), Color.rgb(0, 0, 255));
//            }else {
//                bitMap.setPixel(points.get(i).getX(), points.get(i).getY(), Color.rgb(Color.red(p), Color.green(p), Color.blue(p)));
//            }
//        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mImageview.setImageBitmap(bitMap);
            }
        });

//        mProgressBar.setVisibility(View.GONE);
    }
}
