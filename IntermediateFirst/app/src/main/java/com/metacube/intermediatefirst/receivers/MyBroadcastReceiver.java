package com.metacube.intermediatefirst.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;

import com.metacube.intermediatefirst.listeners.SmsListener;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private static SmsListener smsListener;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {
        String messageBody = "";
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage message : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                messageBody = message.getMessageBody();
            }
        }
        smsListener.messageReceived(messageBody);
    }

    public static void bindListener(SmsListener listener) {
        smsListener = listener;
    }

}
