package com.example.efrainmg90.userloginparse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends AppCompatActivity {
    EditText edtUser,edtPass,edtEmail;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUser = (EditText) findViewById(R.id.edt_username_reg);
        edtPass = (EditText) findViewById(R.id.edt_password_reg);
        edtEmail = (EditText) findViewById(R.id.edt_email_reg);
        btnSave = (Button) findViewById(R.id.btn_save_reg);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user,pass,email;
                user= edtUser.getText().toString();
                pass=edtPass.getText().toString();
                email= edtEmail.getText().toString();
                if(user.equals("")|| pass.equals("")|| email.equals(""))
                    Toast.makeText(RegisterActivity.this, "PorFavor ingrese los datos completos", Toast.LENGTH_SHORT).show();
                else
                    save(user,pass,email);
            }
        });
    }

    private void save(String username,String pass,String email){
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(pass);
        user.setEmail(email);

// other fields can be set just like with ParseObject
        user.put("phone", "650-253-0000");

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    // Hooray! Let them use the app now.
                } else {
                    Toast.makeText(RegisterActivity.this, "Error al registar: "+e, Toast.LENGTH_SHORT).show();
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        });
    }
}
