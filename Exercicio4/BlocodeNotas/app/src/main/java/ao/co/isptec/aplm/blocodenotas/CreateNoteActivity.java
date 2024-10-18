package ao.co.isptec.aplm.blocodenotas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateNoteActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText contentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        titleEditText = findViewById(R.id.titleEditText);
        contentEditText = findViewById(R.id.contentEditText);

        Button okButton = findViewById(R.id.okButton);
        Button cancelButton = findViewById(R.id.cancelButton);

        okButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString();
            String content = contentEditText.getText().toString();
            Note newNote = new Note(title, content);
            Intent returnIntent = new Intent();
            returnIntent.putExtra("newNote", newNote);
            setResult(RESULT_OK, returnIntent);
            finish();
        });

        cancelButton.setOnClickListener(v -> finish());
    }
}