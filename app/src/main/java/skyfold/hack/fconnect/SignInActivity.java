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
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {
    EditText emailId, password;
    Button btnSignIN;
    TextView tviewIN;
    ShimmerFrameLayout shimmerFrameLayout;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_in);
        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editText1);
        password = findViewById(R.id.editText2);
        btnSignIN = findViewById(R.id.button);
        tviewIN = findViewById(R.id.textView);
        shimmerFrameLayout = findViewById(R.id.shimmerFrameLayout);

        String text = "Not Register ? Sign UP here";
        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan fcblue = new ForegroundColorSpan(Color.BLUE);
        ss.setSpan(fcblue,14,27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tviewIN.setText(ss);



        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            FirebaseUser mUser = mFirebaseAuth.getCurrentUser();



            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
           //   if (mUser != null) {
             //     Toast.makeText(SignInActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                // Intent nex = new Intent(SignInActivity.this,testActivity.class);
             //    startActivity(nex);
           //        finish();
             }
         //     else if (!(mUser != null)){
        //           Toast.makeText(SignInActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
        //      }
         //   }
        };
        btnSignIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString().trim();
                String pwd = password.getText().toString().trim();


                if (email.isEmpty()) {
                    emailId.setError("Please enter Email");
                    emailId.requestFocus();
                }
                else if (pwd.isEmpty()) {
                    password.setError("Please enter Password");
                    password.requestFocus();
                }
                else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(SignInActivity.this, "Field are Empty",Toast.LENGTH_SHORT);

                }
                else if (!email.isEmpty() && pwd.isEmpty()) {
                    password.setError("Please enter Password");
                    password.requestFocus();
                }
                else if (email.isEmpty() && !pwd.isEmpty()) {
                    emailId.setError("Please enter Email");
                    emailId.requestFocus();
                }
                else if (!email.isEmpty() && !pwd.isEmpty()) {

                    mFirebaseAuth.signInWithEmailAndPassword(email,pwd)
                            .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(SignInActivity.this,"Login Error, Please Login Again", Toast.LENGTH_SHORT).show();

                            }
                            else {

                                Intent intHome = new Intent(SignInActivity.this,testActivity.class);
                                intHome.putExtra("username",email);
                                startActivity(intHome);
                                finish();

                            }
                        }
                    });

                }

            }
        });

       tviewIN.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intSignup = new Intent(SignInActivity.this, HomeActivity.class);
               startActivity(intSignup);
              return;


           }




       });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

}