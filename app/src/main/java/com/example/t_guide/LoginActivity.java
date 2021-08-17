package com.example.t_guide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private DatabaseHelper myDb;
    SharedPreferences pref;

    private Button btnLogin, btnRegister;
    private EditText etName, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDb = new DatabaseHelper(this);
        pref = this.getSharedPreferences("user_details", 0);

        etName = findViewById(R.id.etUsername);
        etPassword = findViewById((R.id.etPassword));
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister= findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String pw = etPassword.getText().toString();

                //if any blank field
                if(name.equals("")||pw.equals(""))
                {
                    Toast.makeText(LoginActivity.this,"Please enter all the fields !", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //check credential - if return value > 0 ? create session + direct to Home page : login failed
                    int user_id = myDb.checkCredential(name, pw);
                    if(user_id > 0)
                    {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("uid", String.valueOf(user_id));
                        editor.putString("username", name);
                        editor.commit();
                        Toast.makeText(LoginActivity.this, "Signed in successfully ~",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Invalid credentials, authentication failed !",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}