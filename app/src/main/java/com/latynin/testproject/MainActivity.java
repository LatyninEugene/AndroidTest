package com.latynin.testproject;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView gas_anim;
    Button regOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gas_anim = findViewById(R.id.anim_gas);
        Drawable dr = gas_anim.getDrawable();
        if(dr instanceof Animatable)((Animatable) dr).start();

        regOrder = findViewById(R.id.cr);
        regOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegOrder.class);
                startActivity(i);
            }
        });
    }
}
