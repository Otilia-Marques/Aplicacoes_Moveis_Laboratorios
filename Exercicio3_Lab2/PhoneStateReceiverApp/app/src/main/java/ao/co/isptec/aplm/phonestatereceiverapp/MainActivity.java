package ao.co.isptec.aplm.phonestatereceiverapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG_MainActivity";
    private static final int PHONE_STATUS_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Solicitar permissões
        askPhonePermission();
    }

    private void askPhonePermission(){
        int hasPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if(hasPhonePermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_PHONE_STATE}, PHONE_STATUS_REQUEST_CODE);
        };
        hasPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG);
        if(hasPhonePermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_CALL_LOG}, PHONE_STATUS_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PHONE_STATUS_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "Permissão de estado do telefone concedida");
                } else {
                    Log.i(TAG, "Permissão de estado do telefone não concedida");
                }
                return;
            }
        }
    }
}