package com.example.herethereproject.src.write;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;
import com.example.herethereproject.src.main.MainInterfaces.MainActivityView;

import java.util.ArrayList;

public class WriteActivity extends BaseActivity implements MainActivityView {

    public ArrayList<WriteItem> writeItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        RecyclerView recyclerView = findViewById(R.id.list_write);

        writeItems = new ArrayList<>();
        WriteItem writeItem = new WriteItem("asdfasdf", false);
        writeItems.add(writeItem);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        WriteAdapter writeAdapter = new WriteAdapter(writeItems);
        recyclerView.setAdapter(writeAdapter);
    }


    public void MainWriteOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_write_arrow:
                finish();
                break;
        }
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }
}
