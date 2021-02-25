package com.cst2335.plat0039;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.BaseAdapter;
import android.view.ViewGroup;
import android.view.View;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.Arrays;

import android.widget.Button;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.Toast;

public class ChatRoomActivity extends AppCompatActivity {

    Button Send;
    Button Receive;
    ArrayList<Message> msgsArray = new ArrayList<>();
    SQLiteDatabase db;
    MyListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        Receive = (Button) findViewById(R.id.Receive);
        Send = (Button) findViewById(R.id.Send);
        ListView myList = (ListView) findViewById(R.id.Chats);


        EditText Msgs = findViewById(R.id.Msgs);
        loadMessagesFromDatabase();
        myAdapter = new MyListAdapter();
        myList.setAdapter(myAdapter = new MyListAdapter());

        myList.setOnItemClickListener((parent, view, position, id) -> {
            showMessage(position);
        });

        Send.setOnClickListener(click -> {
            String Sendz = Msgs.getText().toString();


            ContentValues messageValues = new ContentValues();
            messageValues.put(DBmessages.COL_MESSAGES, Sendz);
            messageValues.put(DBmessages.COL_SENDORRECEIVE, 1);

            long newID = db.insert(DBmessages.TABLE_NAME, null, messageValues);

            Message newMessage = new Message(Sendz, true, newID);

            msgsArray.add(newMessage);

            myAdapter.notifyDataSetChanged();

            Msgs.setText("");
            Toast.makeText(this, "Inserted item id:" + newID, Toast.LENGTH_LONG).show();
        });

        Receive.setOnClickListener(click -> {
            String Sendz = Msgs.getText().toString();
            ContentValues messageValues = new ContentValues();
            messageValues.put(DBmessages.COL_MESSAGES, Sendz);
            messageValues.put(DBmessages.COL_SENDORRECEIVE, 0);

            long newID = db.insert(DBmessages.TABLE_NAME, null, messageValues);

            Message newMessage = new Message(Sendz, false, newID);

            msgsArray.add(newMessage);

            myAdapter.notifyDataSetChanged();

            Msgs.setText("");
            Toast.makeText(this, "Inserted item id:" + newID, Toast.LENGTH_LONG).show();
        });

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                new AlertDialog.Builder(ChatRoomActivity.this)
                        .setTitle(getResources().getString(R.string.Delete))
                        .setMessage(getResources().getString(R.string.row_msg) + position + "\n" + getResources().getString(R.string.db_id) + id)
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                            msgsArray.remove(position);
                            myAdapter.notifyDataSetChanged();
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        });
    } //closes onCreate

    private void loadMessagesFromDatabase()
    {
        //get a database connection:
        DBmessages dbOpener = new DBmessages(this);
        db = dbOpener.getWritableDatabase(); //This calls onCreate() if you've never built the table before, or onUpgrade if the version here is newer


        // We want to get all of the columns. Look at MyOpener.java for the definitions:
        String [] columns = {DBmessages.COL_ID, DBmessages.COL_SENDORRECEIVE, DBmessages.COL_MESSAGES};
        //query all the results from the database:
        Cursor results = db.query(false, DBmessages.TABLE_NAME, columns, null, null, null, null, null, null);

        //Now the results object has rows of results that match the query.
        //find the column indices:
        int msgColumnIndex = results.getColumnIndex(DBmessages.COL_SENDORRECEIVE);
        //int nameColIndex = results.getColumnIndex(DBmessages.COL_NAME);
        int idColIndex = results.getColumnIndex(DBmessages.COL_ID);

        //ContentValues.put(DBmessages.COL_MESSAGES);
       // ContentValues.put(DBmessages.COL_SENDORRECEIVE);
        //iterate over the results, return true if there is a next item:
        while(results.moveToNext())
        {

            String email = results.getString(msgColumnIndex);
            long id = results.getLong(idColIndex);

            //add the new Contact to the array list:
            msgsArray.add(new Message(Send, Receive, id));
        }

        //At this point, the contactsList array has loaded every row from the cursor.
    }


    protected void showMessage(int position)
    {
        Message selectedMessage = msgsArray.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("You clicked on item #" + position)
                .setMessage("Do you want to delete this?")
                //.setView(message_view) //add the 3 edit texts showing the contact information
                .setPositiveButton("The selected row is ", (click, b) -> {
                    msgsArray.remove(position);
                    myAdapter.notifyDataSetChanged(); //the email and name have changed so rebuild the list
                })
                .setNegativeButton("The database id is ", (click, b) -> {
                })
                .setNeutralButton("dismiss", (click, b) -> { })
                .create().show();
    }

    protected void updateContact(Message m)
    {
        //Create a ContentValues object to represent a database row:
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(DBmessages.COL_SENDORRECEIVE, m.getMessage());
        updatedValues.put(DBmessages.COL_ID, m.getId());

        //now call the update function:
        db.update(DBmessages.TABLE_NAME, updatedValues, DBmessages.COL_ID + "= ?", new String[] {Long.toString(m.getId())});
    }

    protected void deleteContact(Message m)
    {
        db.delete(DBmessages.TABLE_NAME, DBmessages.COL_ID + "= ?", new String[] {Long.toString(m.getId())});
    }

    public class MyListAdapter extends BaseAdapter {

        @Override
        public Message getItem(int position) {
            return msgsArray.get(position);
        }

        @Override
        public long getItemId(int position) {
            return (long) position;
        }

        @Override
        public int getCount() {
            return msgsArray.size();
        }

        @Override
        public View getView(int position, View old, ViewGroup parent) {

            Message msg = (Message) getItem(position);
            LayoutInflater flater = getLayoutInflater();
            View viewz = old;


            if (msg.isSendType() ) {
                viewz = flater.inflate(R.layout.row_sender, parent, false);
            } else  {
                viewz = flater.inflate(R.layout.row_receiver, parent, false);
            }

            TextView TV = viewz.findViewById(R.id.Sender);
            TV.setText(msg.getMessage());
            return viewz;
        }
        private void printCursor(Cursor c, int version) {
            Log.i("printCursor", "DB version number: " + version
                    + "\nNumber of columns: "
                    + c.getColumnCount()
                    + "\nColumn Names: " + Arrays.toString(c.getColumnNames())
                    + "\nNumber of rows: " + c.getCount());
        }
    }
}
