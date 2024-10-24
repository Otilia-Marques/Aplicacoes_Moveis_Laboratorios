package ao.co.isptec.aplm.gravadordechamadas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.ContentValues;
import android.provider.CallLog;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.util.Date;

public class CallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Obtém o estado do telefone (chamando, tocando, etc.)
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

        if (state != null) {
            // Estado de chamada recebida
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                Log.d("CallReceiver", "Chamada recebida de: " + number);

                salvarChamada(context, number, CallLog.Calls.INCOMING_TYPE);
            }

            // Estado de chamada atendida
            else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                Log.d("CallReceiver", "Chamada atendida.");
            }

            // Estado de chamada finalizada
            else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                Log.d("CallReceiver", "Chamada finalizada.");
            }
        }
    }

    private void salvarChamada(Context context, String number, int tipoChamada) {
        // Verifica se o número não é nulo
        if (number != null) {
            // Cria um ContentValues para inserir os dados da chamada no Call Log
            ContentValues values = new ContentValues();
            values.put(CallLog.Calls.NUMBER, number);
            values.put(CallLog.Calls.DATE, new Date().getTime());
            values.put(CallLog.Calls.DURATION, 0);  // A duração da chamada será 0 inicialmente
            values.put(CallLog.Calls.TYPE, tipoChamada);  // Tipo de chamada (INCOMING/OUTGOING)
            values.put(CallLog.Calls.NEW, 1);  // Marca a chamada como nova

            // Insere os dados no Call Log
            context.getContentResolver().insert(CallLog.Calls.CONTENT_URI, values);
        }
    }
}