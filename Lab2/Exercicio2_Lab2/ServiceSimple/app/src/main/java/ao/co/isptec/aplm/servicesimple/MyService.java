package ao.co.isptec.aplm.servicesimple;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.Context;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Service was Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Acessa o SharedPreferences para obter o valor do contador
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int count = sharedPref.getInt("service_count", 0);

        // Exibe o valor do contador em um Toast
        Toast.makeText(this, "Service Started. Count: " + count, Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show();
    }
}