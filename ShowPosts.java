package com.example.liukaichi.twitter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class ShowPosts extends AppCompatActivity {
    private static final String TAG = "ShowPostActivity";

    //ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.ListView,StringArray);

    private Firebase myFireBaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_posts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
        ListView listView = (ListView) findViewById(R.id.listView);
        setContentView(listView);*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Firebase.setAndroidContext(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        myFireBaseRef = new Firebase("https://luminous-torch-6850.firebaseio.com/chatty");
        myFireBaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.d(TAG, "read " + snapshot.getValue()); // fail. //
                System.out.println(snapshot.getValue());


            }

            @Override
            public void onCancelled(FirebaseError error) {
            }

        });
        myFireBaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousKey) {
                Log.d(TAG, "child added: read " + snapshot.getValue());
                saveTheData(snapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    private void saveTheData(DataSnapshot snapshot) {
        Message newMessage = new Message();
        for (DataSnapshot messageSnapshot : snapshot.getChildren()) {
            Log.d(TAG, "message (key,value): " + messageSnapshot.getKey() + "," + messageSnapshot.getValue());
            newMessage.setValue(messageSnapshot.getKey(), (String) messageSnapshot.getValue());
        }
        Log.d(TAG, "the data as a message is " + newMessage.prettyString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_posts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
