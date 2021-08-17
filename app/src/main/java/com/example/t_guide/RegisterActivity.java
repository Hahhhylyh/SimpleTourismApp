package com.example.t_guide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private DatabaseHelper myDb;

    private Button btnRegister, btnLogin;
    private EditText etEmail, etName, etPassword, etRePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDb = new DatabaseHelper(this);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister= findViewById(R.id.btnRegister);
        etEmail = findViewById(R.id.etEmail);
        etName = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etRePassword = findViewById(R.id.etConfirm);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                String email = etEmail.getText().toString();
                String name = etName.getText().toString();
                String pw = etPassword.getText().toString();
                String repw = etRePassword.getText().toString();

                //if any blank field
                if(email.equals("")||name.equals("")||pw.equals("")||repw.equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields !", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //if password = re-password, query database - check 'name' & 'email' existed or not
                    if (pw.equals(repw))
                    {
                        Boolean unique = myDb.checkUniqueness(email, name);

                        //if both not existed, then register success - direct to login page
                        if(!unique)
                        {
                            Boolean insert = myDb.addUser(email, name, pw);

                            if(insert)
                            {
                                Toast.makeText(RegisterActivity.this, "Register successfully ~", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(RegisterActivity.this, "Register failed !", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "Account existed ! You want to login ?", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Passwords are not matching !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}