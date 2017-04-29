package com.example.efrainmg90.userloginparse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ListView listViewNotes;
    TextView textViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listViewNotes = (ListView) findViewById(R.id.list_view_notes);
        textViewTitle = (TextView) findViewById(R.id.txv_title_home);
        show();
        textViewTitle.setText("Bienvenido: "+getUser());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_new:
                Intent intentNew = new Intent(HomeActivity.this,AddNoteActivity.class);
                startActivity(intentNew);
                finish();
                break;
            case R.id.mnu_profile:
                break;
            case R.id.mnu_logout:
                ParseUser.logOut();
                Intent intent = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void show(){
        final List<String> listNotes = new ArrayList<String>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Notes");
        query.whereEqualTo("user", getUser());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> notesList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + notesList.size() + " scores");
                    for(int i = 0;i<notesList.size();i++){
                        ParseObject object = notesList.get(i);
                        listNotes.add(object.getString("name"));
                        Log.e("notas",object.getString("name"));
                    }
                   ArrayAdapter<String> adapter = new ArrayAdapter<String>(HomeActivity.this,android.R.layout.simple_list_item_1,listNotes);
                   listViewNotes.setAdapter(adapter);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
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
