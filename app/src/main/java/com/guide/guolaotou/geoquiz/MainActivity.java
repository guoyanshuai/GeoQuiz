package com.guide.guolaotou.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.guide.guolaotou.geoquiz.Question.Question;

public class MainActivity extends AppCompatActivity {

    private Button btn_sure;
    private Button btn_cancel;
    private Button btn_next;
    private Button btn_prev;
    private Button btn_cheat;
    private TextView tv_question;
    private Question question[] = {
            new Question(R.string.Q1, true),
            new Question(R.string.Q2, true),
            new Question(R.string.Q3, false),
            new Question(R.string.Q4, true),
            new Question(R.string.Q5, false),
            new Question(R.string.Q6, true),
            new Question(R.string.Q7, false),
            new Question(R.string.Q8, true),
            new Question(R.string.Q9, true),
    };
    private int mCurrentIndex = 0;
    private int index;
    public static final String TAG = "GeoQuiz";
    public static final String KEY_INDEX = "index";
    private int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: hhhh");
        initview();
        if (savedInstanceState != null)
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
        deal_with();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: ");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    private void initview() {
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_sure = (Button) findViewById(R.id.btn_sure);
        btn_next = (Button) findViewById(R.id.next_btn);
        tv_question = (TextView) findViewById(R.id.top_tv);
        btn_prev = (Button) findViewById(R.id.prev_btn);
        btn_cheat = (Button) findViewById(R.id.cheat_btn);
    }

    private void deal_with() {
        tv_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % question.length;
                UpdateQustion();
            }
        });
        index = question[mCurrentIndex].getmTextResId();
        tv_question.setText(index);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % question.length;
                UpdateQustion();
            }
        });
        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentIndex != 0)
                    mCurrentIndex = (mCurrentIndex - 1) % question.length;
                else {
                    mCurrentIndex = question.length - 1;

                }
                UpdateQustion();
            }
        });
        btn_cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Cheat_Activity.class);
                intent.putExtra("prev", question[mCurrentIndex].getmAnswerTure());
//                startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckAnswer(true);
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckAnswer(false);
            }
        });
    }

    private static boolean WasAnswer(Intent intet) {
        return intet.getBooleanExtra("MainActivity", false);
    }

    private void UpdateQustion() {
        index = question[mCurrentIndex].getmTextResId();
        tv_question.setText(index);
    }

    private void CheckAnswer(boolean checkanswer) {
        boolean answer = question[mCurrentIndex].getmAnswerTure();
        int messageResId = 0;
        if (checkanswer == answer) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: 我进来了!");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK)
            return;
        if (requestCode == REQUEST_CODE)
            if (data == null)
                return;
        boolean result = MainActivity.WasAnswer(data);
        CheckAnswer(result);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: 我进来了!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: 我进来了!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: 我进来了!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: 我进来了!");
    }
}
