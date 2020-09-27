package com.example.restaurant_billing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bill extends AppCompatActivity {

    String[] item = {"Panner 65","Roti","Cashewnut curry","Dosa","Idly","Chapati","Upma","Biryani","Chicken Lollipop","Chicken 65"};
    String[] price = {"11","22","33","44","55","66","77","88","99","100"};
    LinearLayout ll;
    Button bt;
    TextView tv;
    HashMap<Integer,Integer> quan;
    static int sum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        bt = findViewById(R.id.tot);
        tv = findViewById(R.id.tv1);
        Bundle b = getIntent().getExtras();
        final HashMap<Integer,Integer> cart = (HashMap<Integer, Integer>) b.getSerializable("selected");
        ll = findViewById(R.id.ll1);
        quan = new HashMap<>();
        final HashMap<Integer,EditText> h = new HashMap<Integer, EditText>();
        for(int i : cart.keySet()){
            LinearLayout ll2 = new LinearLayout(this);
            TextView t = new TextView(this);
            t.setText(item[i]+"         "+"$"+price[i]);
            EditText ed = new EditText(this);
            ed.setHint("Quantity");
            ed.setText("1");
            h.put(i,ed);

            ll2.addView(t);
            ll2.addView(ed);
            ll.addView(ll2);
        }

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sum =0;
                for(int j: cart.keySet()){
                    sum = sum + (Integer.valueOf(h.get(j).getText().toString()))*Integer.valueOf(price[j]);
                }
                tv.setText("Bill Total: "+String.valueOf(sum));
            }
        });


    }
}