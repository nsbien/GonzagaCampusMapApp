package com.example.gonzagamapapp;

/**
 * Activity that allow users to message one another that are on the GU map app
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MessagingActivity extends AppCompatActivity {

    static final String TAG = "FirebaseFunTag";
    static final int SIGN_IN_REQUEST = 1;

    String userName = "Anonymous";
    List<ChatMessage> chatMessageList;
    ArrayAdapter<ChatMessage> arrayAdapter;
    ListView listView;

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mMessagesDatabaseReference;
    ChildEventListener mMessagesChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        Intent intent = getIntent();

        listView = (ListView) findViewById(R.id.listView);
        chatMessageList = new ArrayList<>();
        chatMessageList.add(new ChatMessage());
        arrayAdapter = new ArrayAdapter<ChatMessage>(
                this,
                android.R.layout.simple_list_item_1,
                chatMessageList
        );
        listView.setAdapter(arrayAdapter);

        setupFirebase();
    }

    private void setupFirebase() {
        // initialize the firebase references
        mFirebaseDatabase =
                FirebaseDatabase.getInstance();
        mMessagesDatabaseReference =
                mFirebaseDatabase.getReference()
                        .child("messages");
        mMessagesChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // called for each message already in our db
                // called for each new message add to our db
                // dataSnapshot stores the ChatMessage
                Log.d(TAG, "onChildAdded: " + s);
                ChatMessage chatMessage =
                        dataSnapshot.getValue(ChatMessage.class);
                // add it to our list and notify our adapter
                chatMessageList.add(chatMessage);
                arrayAdapter.notifyDataSetChanged();
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
            public void onCancelled(DatabaseError databaseError) {

            }


        };

        mMessagesDatabaseReference
                .addChildEventListener(mMessagesChildEventListener);
    }


    public void onSendButtonClick(View view) {
        Log.d(TAG, "onSendButtonClick: ");
        EditText editText = (EditText)
                findViewById(R.id.editText);
        String currText = editText.getText().toString();
        if (currText.isEmpty()) {
            Toast.makeText(this, "Please enter a message first", Toast.LENGTH_SHORT).show();
        }
        else {
            ChatMessage chatMessage = new
                    ChatMessage(userName,
                    currText);
            mMessagesDatabaseReference
                    .push()
                    .setValue(chatMessage);
            editText.setText("");

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_signout) {
            AuthUI.getInstance().signOut(this);
            chatMessageList.clear();
            arrayAdapter.notifyDataSetChanged();
            mMessagesDatabaseReference.removeEventListener(mMessagesChildEventListener);
        }
        return super.onOptionsItemSelected(item);
    }

}
