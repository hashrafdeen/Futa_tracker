package skyfold.hack.fconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseUser;


public class chat_activity extends AppCompatActivity {
    ImageButton imageButton;
    EditText editText;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_activity);

        imageButton = findViewById(R.id.SendButton);
        editText = findViewById(R.id.EditField);
        listView = findViewById(R.id.chatPage);


    }

}

