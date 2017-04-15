package com.guide.guolaotou.geoquiz;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        deal_with();
    }

    private void initview() {
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_sure = (Button) findViewById(R.id.btn_sure);
        btn_next = (Button) findViewById(R.id.next_btn);
        tv_question = (TextView) findViewById(R.id.top_tv);
        btn_prev = (Button) findViewById(R.id.prev_btn);
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
                    mCurrentIndex = question.length-1;

                }
                UpdateQustion();
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

    private void UpdateQustion() {
        //mCurrentIndex = (mCurrentIndex + 1) % question.length;
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

}
