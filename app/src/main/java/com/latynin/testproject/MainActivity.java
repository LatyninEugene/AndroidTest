package com.latynin.testproject;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView gas_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gas_anim = findViewById(R.id.anim_gas);
        Drawable dr = gas_anim.getDrawable();
        if(dr instanceof Animatable)((Animatable) dr).start();
    }
}
