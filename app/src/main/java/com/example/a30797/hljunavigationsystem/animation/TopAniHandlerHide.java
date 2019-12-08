package com.example.a30797.hljunavigationsystem.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.CardView;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class TopAniHandlerHide extends Handler {

    private CardView cardView;

    public TopAniHandlerHide(CardView cardView) {
        this.cardView = cardView;
    }

    public void handleMessage(){
        ObjectAnimator translationX = new ObjectAnimator().ofFloat(cardView,"translationX",0,-500);
        translationX.setDuration(200);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(translationX);
        animatorSet.start();
    }

    @Override
    public void publish(LogRecord record) {

    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {

    }

    public void sendMessage() {
        handleMessage();
    }
}
