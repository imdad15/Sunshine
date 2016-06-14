package com.example.android.sunshine.app.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.android.sunshine.app.MainActivity;
import com.example.android.sunshine.app.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

public class RegistrationIntentService extends IntentService {
    private static final String TAG = RegistrationIntentService.class.getSimpleName();
    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        try {
            synchronized (TAG){
                InstanceID instanceID = InstanceID.getInstance(this);

                String senderID = getString(R.string.gcm_defaultSenderId);
                Log.d(TAG,"senderID->"+senderID);
                if(senderID.length()!=0){
                    String token = instanceID.getToken(senderID, GoogleCloudMessaging.INSTANCE_ID_SCOPE,
                            null);
                    sendRegistrationToServer(token);
                }
                sharedPreferences.edit().putBoolean(MainActivity.SENT_TOKEN_TO_SERVER,true).apply();
            }
        }catch (Exception e){
            Log.d(TAG,"Failed to complete token refresh",e);
            sharedPreferences.edit().putBoolean(MainActivity.SENT_TOKEN_TO_SERVER,false).apply();
        }
    }

    private void sendRegistrationToServer(String token){
        Log.d(TAG,"GCM Registration Token:"+token);
    }
}
