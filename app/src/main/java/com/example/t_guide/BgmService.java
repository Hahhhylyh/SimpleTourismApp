package com.example.t_guide;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class BgmService extends Service {

    private MediaPlayer bgm;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        bgm = MediaPlayer.create(getApplicationContext(), R.raw.bgm);
        bgm.setLooping(true);
        bgm.setVolume(20,20);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        bgm.start();
        return startId;
    }
    public void onStart(Intent intent, int startId) {
    }
    @Override
    public void onDestroy() {
        bgm.stop();
        bgm.release();
        super.onDestroy();
    }
    @Override
    public void onLowMemory() {
    }
}
