package ao.co.isptec.aplm.phonestatereceiverapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MyPhoneReceiver extends BroadcastReceiver {

    private static final String TAG = "TAG_MyPhoneReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction() == TelephonyManager.ACTION_PHONE_STATE_CHANGED){
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Log.i(TAG, "State: " + state + ", Number: " + number);
        }
    }
}

/*public class MyPhoneReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Verifica se a ação é de mudança no estado do telefone
        if (intent.getAction().equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)) {
            // Obtenha o estado do telefone e o número que está chamando
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            // Verifique se o telefone está "tocando" (recebendo uma chamada)
            if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
                Log.i("MyPhoneReceiver", "Chamada recebida de: " + incomingNumber);
            }
        }
    }
}*/