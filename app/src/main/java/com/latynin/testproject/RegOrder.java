package com.latynin.testproject;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RegOrder extends AppCompatActivity {

    LinearLayout ln;
    Animation anim;
    View gLine;
    boolean isActive;
    TextView tv;
    Context v;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_order);

        v = this.getApplicationContext();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        btn = findViewById(R.id.Entry);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegOrder.this, Main2Activity.class);
                startActivity(i);
            }
        });

        ln = findViewById(R.id.Desc);
        tv = findViewById(R.id.tv);
        ln.removeAllViews();
        gLine = findViewById(R.id.view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_in_regzac, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.question:
                if (!isActive){
                    ln.addView(tv);
                    ln.setVisibility(View.VISIBLE);
                    anim = AnimationUtils.loadAnimation(v, R.anim.size_swipe);
                    ln.startAnimation(anim);
                    gLine.setScaleY(0);
                    isActive = true;
                }else {
                    ln.setVisibility(View.INVISIBLE);
                    anim = AnimationUtils.loadAnimation(v, R.anim.size_swipe_off);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            ln.removeAllViews();
                            gLine.setScaleY(1);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    ln.startAnimation(anim);
                    isActive = false;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
