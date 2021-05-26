package skyfold.hack.fconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    EditText emailId, password, cmpassword;
    Button btnSignUP;
    TextView tview;
    ShimmerFrameLayout shimmerFrameLayout;
    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editText1);
        password = findViewById(R.id.editText2);
        btnSignUP = findViewById(R.id.button);
        tview = findViewById(R.id.textView);
        cmpassword = findViewById(R.id.editText3);
        shimmerFrameLayout = findViewById(R.id.shimmerFrameLayout);

        String text = "Already have an account? Login here";
        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan fcblue = new ForegroundColorSpan(Color.BLUE);
        ss.setSpan(fcblue,24,35,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tview.setText(ss);

        btnSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String email = emailId.getText().toString().trim();
                    String pwd = password.getText().toString().trim();
                    String cmpwd = cmpassword.getText().toString().trim();

                    if (email.isEmpty()) {
                        emailId.setError("Please enter Email");
                        emailId.requestFocus();
                    }
                    else if (pwd.isEmpty()) {
                        password.setError("Please enter Password");
                        password.requestFocus();
                    }
                    else if (cmpwd.isEmpty()) {
                        cmpassword.setError("Please enter Confirm Password");
                        cmpassword.requestFocus();
                    }
                    else if (email.isEmpty() && pwd.isEmpty()) {
                        Toast.makeText(HomeActivity.this, "Field are Empty",Toast.LENGTH_SHORT);


                    }
                    else if (!email.isEmpty() && pwd.isEmpty()) {
                        password.setError("Please enter Password");
                        password.requestFocus();
                    }
                    else if (email.isEmpty() && !pwd.isEmpty()) {
                        emailId.setError("Please enter Email");
                        emailId.requestFocus();
                    }
                    else if (cmpwd != null && !cmpwd.equals(pwd)) {
                        cmpassword.setError("Password don't match");
                        cmpassword.requestFocus();

                    }


                    else if (!email.isEmpty() && !pwd.isEmpty()) {
                        mFirebaseAuth.createUserWithEmailAndPassword(email,pwd)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){

                                    Toast.makeText(HomeActivity.this, "Successful Registration",Toast.LENGTH_SHORT);
                                    startActivity(new Intent(HomeActivity.this, SignInActivity.class));
                                    finish();

                                }
                                else {
                                    Toast.makeText(HomeActivity.this, "SignUP Unsuccessful, Please try again",Toast.LENGTH_SHORT);


                                }

                            }
                        });
                    }
                    else {
                        Toast.makeText(HomeActivity.this, "Error Occurred!",Toast.LENGTH_SHORT);

                    }
                }
            });

        tview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"Please Sign in here ",Toast.LENGTH_SHORT);
                Intent i = new Intent(HomeActivity.this , SignInActivity.class);
                startActivity(i);


            }
        });


        }


    }

