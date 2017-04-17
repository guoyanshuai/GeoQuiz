package com.guide.guolaotou.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Cheat_Activity extends AppCompatActivity {

    private TextView prev_tv;
    private Button btn_Cheat_prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        initView();
        deal_with();
        Toast.makeText(Cheat_Activity.this, String.valueOf(Build.VERSION.SDK_INT), Toast.LENGTH_SHORT).show();

    }

    private void initView() {
        prev_tv = (TextView) findViewById(R.id.prev_tv);
        btn_Cheat_prev = (Button) findViewById(R.id.btn_Cheat_sure);
    }

    private void deal_with() {
        btn_Cheat_prev.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(final View view) {
                boolean prev = getIntent().getBooleanExtra("prev", false);
                int cx = btn_Cheat_prev.getWidth() / 2;
                int cy = btn_Cheat_prev.getHeight() / 2;
                float radius = btn_Cheat_prev.getWidth();
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
                    Animator animator = ViewAnimationUtils.createCircularReveal(btn_Cheat_prev, cx, cy, radius, 0);
                    animator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            btn_Cheat_prev.setVisibility(View.INVISIBLE);
                        }
                    });
                    animator.start();
                }
                else
                {
                    btn_Cheat_prev.setVisibility(View.INVISIBLE);
                }
                prev_tv.setText(String.valueOf(prev));
                Intent intent = new Intent();
                intent.putExtra("MainActivity", prev);
                setResult(RESULT_OK, intent);
              //  finish();

            }
        });
    }
}
