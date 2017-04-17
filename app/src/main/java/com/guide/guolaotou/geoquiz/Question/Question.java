package com.guide.guolaotou.geoquiz.Question;

/**
 * Created by Tom on 2017/4/15.
 */

public class Question {
    private  int mTextResId;
    private  boolean mAnswerTure;

    public Question(int textResId, boolean answerTure) {
        mTextResId = textResId;
        mAnswerTure = answerTure;
    }

    public int getmTextResId() {
        return mTextResId;
    }

    public void setmTextResId(int mTextResId) {
        this.mTextResId = mTextResId;
    }

    public boolean getmAnswerTure() {
        return mAnswerTure;
    }

    public void setmAnswerTure(boolean mAnswerTure) {
        this.mAnswerTure = mAnswerTure;
    }
}
