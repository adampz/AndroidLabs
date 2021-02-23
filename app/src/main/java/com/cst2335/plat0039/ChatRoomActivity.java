package com.cst2335.plat0039;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.BaseAdapter;
import android.view.ViewGroup;
import android.view.View;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.EditText;
import java.util.ArrayList;
import android.widget.Button;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.ListView;

public class ChatRoomActivity extends AppCompatActivity {

    Button Send;
    Button Receive;
    ArrayList<Message> elements = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        Receive = (Button) findViewById(R.id.Receive);
        Send = (Button) findViewById(R.id.Send);
        ListView myList = (ListView) findViewById(R.id.Chats);
        MyListAdapter myAdapter;
        myList.setAdapter(myAdapter = new MyListAdapter());

        Receive.setOnClickListener(c -> {
            EditText msg = (EditText) findViewById(R.id.Msgs);
            String msgTxt = msg.getText().toString();
            Message msgCommit = new Message(msgTxt, false);
            elements.add(msgCommit);
            myAdapter.notifyDataSetChanged();
            msg.setText("");
        });

        Send.setOnClickListener(c -> {
            EditText msg = (EditText) findViewById(R.id.Msgs);
            String msgTxt = msg.getText().toString();
            Message messageCommit = new Message(msgTxt, true);
            elements.add(messageCommit);
            myAdapter.notifyDataSetChanged();
            msg.setText("");
        });

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(ChatRoomActivity.this)
                        .setTitle(getResources().getString(R.string.Delete))
                        .setMessage(getResources().getString(R.string.row_msg) + position + "\n" + getResources().getString(R.string.db_id) + id)
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                            elements.remove(position);
                            myAdapter.notifyDataSetChanged();
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        });
    }


    public class MyListAdapter extends BaseAdapter {

        @Override
        public Message getItem(int position) {
            return elements.get(position);
        }

        @Override
        public long getItemId(int position) {
            return (long) position;
        }

        @Override
        public int getCount() {
            return elements.size();
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
    }
}
