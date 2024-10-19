package ao.co.isptec.aplm.listadetarefas;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> taskList;
    private ArrayAdapter<String> adapter;
    private ListView listViewTasks;
    private EditText editTextTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa os componentes
        editTextTask = findViewById(R.id.editTextTask);
        listViewTasks = findViewById(R.id.listViewTasks);

        // Inicializa a lista de tarefas e o adaptador
        taskList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);

        // Associa o adaptador ao ListView
        listViewTasks.setAdapter(adapter);

        // Captura o evento da tecla "Enter" no EditText
        editTextTask.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // Verifica se a tecla pressionada foi "Enter"
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Adiciona o texto à lista e atualiza o ListView
                    String task = editTextTask.getText().toString();
                    if (!task.isEmpty()) {
                        taskList.add(0, task);  // Adiciona no topo da lista
                        adapter.notifyDataSetChanged();  // Notifica o adaptador sobre a mudança
                        editTextTask.setText("");  // Limpa o campo de texto
                    }
                    return true;
                }
                return false;
            }
        });
    }
}