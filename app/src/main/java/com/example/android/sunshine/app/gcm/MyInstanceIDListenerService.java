package com.example.android.sunshine.app.gcm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.android.gms.iid.InstanceIDListenerService;

public class MyInstanceIDListenerService extends InstanceIDListenerService {
    private static final String TAG = "MyInstanceIDLS";

    @Override
    public void onTokenRefresh(){
        Intent intent = new Intent(this,RegistrationIntentService.class);
        startService(intent);
    }
}
