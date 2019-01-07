package com.metacube.intermediatefirst.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class BackgroundService extends Service {

    private final ImageDownloadBinder imageDownloadBinder = new ImageDownloadBinder();
    private final Random mGenerator = new Random();

    public class ImageDownloadBinder extends Binder {
        public BackgroundService getImageDownloadService() {
            return BackgroundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return imageDownloadBinder;
    }

    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }

}
