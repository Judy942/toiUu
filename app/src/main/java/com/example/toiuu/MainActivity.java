package com.example.toiuu;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        long startTime = System.nanoTime();
        setContentView(R.layout.activity_main);
        long endTime = System.nanoTime();

        // Tính toán thời gian thực thi
        long duration = endTime - startTime; // Đơn vị: nanoseconds
        double durationMs = duration / 1_000_000.0; // Đổi sang milliseconds

        // In thời gian ra log
        Log.d("Performance", "setContentView thuc thi trong: " + durationMs + " ms");
        System.out.println( "setContentView thực thi trong: " + durationMs + " ms");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Data (100 items)
        List<String> itemList = new ArrayList<>();
        for (int i = 1; i <= 200; i++) {
            itemList.add("Item " + i);
        }

        // Adapter
        MyAdapter adapter = new MyAdapter(itemList);
        recyclerView.setAdapter(adapter);
    }
}