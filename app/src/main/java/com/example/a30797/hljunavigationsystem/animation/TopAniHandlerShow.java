package com.example.a30797.hljunavigationsystem.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.CardView;
import android.view.View;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class TopAniHandlerShow extends Handler {

    private CardView cardView;

    public TopAniHandlerShow(CardView cardView) {
        this.cardView = cardView;
    }

    public void handleMessage(){
        ObjectAnimator translationX = new ObjectAnimator().ofFloat(cardView,"translationX",-500,0);
        translationX.setDuration(1000);
        cardView.setVisibility(View.VISIBLE);
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
