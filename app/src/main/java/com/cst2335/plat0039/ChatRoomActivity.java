package com.cst2335.plat0039;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ChatRoomActivity extends AppCompatActivity {

    EditText ET;
    ListView LV;
    Button Send;
    Button Receive;
    List<Message> Msgs = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        ET=(EditText)findViewById(R.id.Msgs);
        LV=(ListView)findViewById(R.id.Chats);
        Receive=(Button)findViewById(R.id.Receive);
        Send=(Button)findViewById(R.id.Send);
        ListView myList = (ListView) findViewById(R.id.ListView);


        Receive.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View a){
                String sender = ET.getText().toString();
                Message keeper = new Message(sender);
                Msgs.add(keeper);
                ArrayAdapter<List> ab = new ArrayAdapter<List>(this,R.layout.activity_chat_room, Msgs);
                LV.setAdapter(ab);
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b){
                String receiver = ET.getText().toString();
                Message keeper = new Message(receiver);
                Msgs.add(keeper);
                ArrayAdapter<List> ab = new ArrayAdapter<List>(this,R.layout.activity_chat_room, Msgs);
                LV.setAdapter(ab);
            }
        });
    }

    @Override
    public View getView(int position, View convertView, ViewGRoup parent){

        LayoutInflater inflater = getLayoutInflater();
        View newView;
        TextView tView;
        if (messageList.get(position).getType().equals(MessageType.SEND)) {
            newView = inflater.inflate(R.layout.row_send, parent, false);
            tView = newView.findViewById(R.id.sendText);
        } else {
            newView = inflater.inflate(R.layout.row_receive, parent, false);
            tView = newView.findViewById(R.id.receiveText);
        }
        tView.setText(getItem(position).toString());
        EditText text = findViewById(R.id.chatText);
        text.setText("");

        //return it to be put in the table
        return newView;
    }

        class MyListAdapter extends BaseAdapter {
        private final ArrayList<String> elements = new ArrayList<>();
        @Override
        public int getCount(){
            return elements.size();
        }

        @Override
        public Object getItem(int i){
            int position = 0;
            return elements.get(position);
        }

        @Override
        public long getItemId(int i){
            Object position = null;
            return (long)position;
        }

        }

    }
