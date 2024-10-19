package ao.co.isptec.aplm.blocodenotas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReadNoteActivity extends AppCompatActivity {
    private TextView titleTextView;
    private TextView contentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_note);

        titleTextView = findViewById(R.id.titleTextView);
        contentTextView = findViewById(R.id.contentTextView);

        Note note = (Note) getIntent().getSerializableExtra("note");
        if (note != null) {
            titleTextView.setText(note.getTitle());
            contentTextView.setText(note.getContent());
        }

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());
    }
}