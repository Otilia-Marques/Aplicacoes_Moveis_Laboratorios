package ao.co.isptec.aplm.servicesimple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Context;

import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Start the service
    public void startService(View view) {
        // Usando SharedPreferences para armazenar o contador
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        // Obtém o valor atual do contador, se não existir, começa com 0
        int count = sharedPref.getInt("service_count", 0);
        count++;  // Incrementa o contador

        // Salva o valor atualizado no SharedPreferences
        editor.putInt("service_count", count);
        editor.apply();

        // Agora inicie o serviço
        startService(new Intent(this, MyService.class));
    }

    // Stop the service
    public void stopService(View view) {
        stopService(new Intent(this, MyService.class));
    }
}