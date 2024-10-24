package ao.co.isptec.aplm.threadexampleapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Thread workerThread;
    private boolean isRunning = false;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startButton);
        Button stopButton = findViewById(R.id.stopButton);

        startButton.setOnClickListener(v -> startCounting());
        stopButton.setOnClickListener(v -> stopCounting());
    }

    private void startCounting() {
        if (workerThread != null && workerThread.isAlive()) {
            return; // Thread já está a correr
        }

        counter = 0;
        isRunning = true;

        workerThread = new Thread(() -> {
            while (isRunning) {
                try {
                    Log.d("Counter", "Contador: " + counter);
                    counter++;
                    Thread.sleep(1000); // Pausa de 1 segundo
                } catch (InterruptedException e) {
                    Log.d("Thread", "Worker thread foi interrompida.");
                    return;
                }
            }
        });

        workerThread.start();
    }

    private void stopCounting() {
        isRunning = false;
        if (workerThread != null) {
            workerThread.interrupt();
        }
    }
}