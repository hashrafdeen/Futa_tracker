package skyfold.hack.fconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class profile_activity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    private FirebaseUser mfirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textView = findViewById(R.id.textEmail);
        editText = findViewById(R.id.editText1);


    }

}
