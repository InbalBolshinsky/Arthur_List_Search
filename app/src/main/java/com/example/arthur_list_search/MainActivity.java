package com.example.arthur_list_search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arthur_list_search.adapters.CustomeAdapter;
import com.example.arthur_list_search.classes.myData;
import com.example.arthur_list_search.models.Data;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CustomeAdapter customeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rvcon);
        EditText    etSearch      = findViewById(R.id.etSearch);

        // 1) Build your data list
        ArrayList<Data> arr = new ArrayList<>();
        for (int i = 0; i < myData.nameArray.length; i++) {
            arr.add(new Data(
                    myData.nameArray[i],
                    myData.versionArray[i],
                    myData.drawableArray[i],
                    myData.id_[i]
            ));
        }

        // 2) Set up adapter & RecyclerView
        customeAdapter = new CustomeAdapter(arr);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(customeAdapter);

        // 3) Wire up search filtering
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int a, int b, int c) {}
            @Override public void afterTextChanged(Editable s) {}
            @Override
            public void onTextChanged(CharSequence s, int a, int b, int c) {
                customeAdapter.getFilter().filter(s);
            }
        });
    }
}
