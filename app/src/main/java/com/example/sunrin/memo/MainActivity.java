package com.example.sunrin.memo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText text;
    ListView list;
    Adapter adapter;
    ArrayList<Data> item = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
        btn = findViewById(R.id.btn);
        text = findViewById(R.id.text);
        list = findViewById(R.id.list);

        adapter = new Adapter(item);
        list.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.add(new Data(text.getText().toString()));
                adapter.notifyDataSetChanged();
                saveData();
            }
        });



    }

    void loadData() {
        Gson gson = new Gson();
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String json = pref.getString("save", "");
        ArrayList<Data> shareditems;
        shareditems = gson.fromJson(json, new TypeToken<ArrayList<Data>>(){}.getType());
        if(shareditems != null)item.addAll(shareditems);
    }


    void saveData() { //items 안의 내용이 저장됨
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String json = new Gson().toJson(item);
        editor.putString("save", json);
        Log.d("asdf",json);
        editor.commit();
    }

}
