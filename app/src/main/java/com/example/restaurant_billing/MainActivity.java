package com.example.restaurant_billing;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothHidDeviceAppSdpSettings;
import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lv;
    String[] item = {"Panner 65","Roti","Cashewnut curry","Dosa","Idly","Chapati","Upma","Biryani","Chicken Lollipop","Chicken 65"};
    String[] price = {"11","22","33","44","55","66","77","88","99","100"};
    Intent in;
    Button b;
    HashMap<Integer,Integer> cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cart = new HashMap<>();
        lv = findViewById(R.id.itemslist);
        List<Map<String,String>> menu = new ArrayList<>();

        for(int i=0;i<item.length;i++){
            HashMap<String,String> m = new HashMap<>();
            m.put("item",item[i]);
            m.put("price","$ "+price[i]);
            menu.add(m);
        }

        String[] entry = {"item","price"};
        SimpleAdapter adap = new SimpleAdapter(this, menu, android.R.layout.simple_list_item_2,entry,new int[]{android.R.id.text1,android.R.id.text2});

        lv.setAdapter(adap);
        lv.setOnItemClickListener(this);
        in = new Intent(this,Bill.class);
        b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                in.putExtra("selected",cart);
                startActivity(in);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        StringBuilder sb = new StringBuilder();
        sb.append(item[i]);
        //sb.append(price[i]);
        cart.put(i,i);
        Toast.makeText(this, sb.toString()+" added to cart ", Toast.LENGTH_SHORT).show();
    }
}