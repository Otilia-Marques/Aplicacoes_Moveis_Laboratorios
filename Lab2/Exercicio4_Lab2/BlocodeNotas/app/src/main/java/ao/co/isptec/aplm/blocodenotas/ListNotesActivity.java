package ao.co.isptec.aplm.blocodenotas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListNotesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

        recyclerView = findViewById(R.id.recyclerView);
        notes = loadNotes(); // Carregar notas (pode ser de um banco de dados ou SharedPreferences)

        noteAdapter = new NoteAdapter(notes, this);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button newNoteButton = findViewById(R.id.newNoteButton);
        newNoteButton.setOnClickListener(v -> {
            Intent intent = new Intent(ListNotesActivity.this, CreateNoteActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    private List<Note> loadNotes() {
        return new ArrayList<>();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Note newNote = (Note) data.getSerializableExtra("newNote");
            notes.add(newNote);
            noteAdapter.notifyDataSetChanged(); // Atualiza a lista
        }
    }
}

// Adapter para o RecyclerView
class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> notes;
    private Context context;

    public NoteAdapter(List<Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.textView.setText(note.getTitle());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ReadNoteActivity.class);
            intent.putExtra("note", note);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}