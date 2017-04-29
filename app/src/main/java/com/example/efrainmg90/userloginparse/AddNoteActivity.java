package com.example.efrainmg90.userloginparse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class AddNoteActivity extends AppCompatActivity {

    EditText textNote;
    Button buttonSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        textNote = (EditText) findViewById(R.id.edt_note_name);
        buttonSave = (Button) findViewById(R.id.btn_save_add_note);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(textNote.getText().toString());
            }
        });

    }


    private void save(String nota){
        ParseObject note = new ParseObject("Notes");
        note.put("name", nota);
        note.put("user",getUser());
        note.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                String message = "";
                if (e==null){
                    message = "Saved successfully";
                Intent intent = new Intent(AddNoteActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
                }
                else
                    message = "error: "+e;
                Toast.makeText(AddNoteActivity.this, "Status: "+message, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String getUser(){
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            return currentUser.getUsername();
        } else {
            // show the signup or login screen
            return "";
        }
    }
}
