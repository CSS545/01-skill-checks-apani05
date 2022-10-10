package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        next = (Button) findViewById(R.id.btn1);
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent navigation = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(navigation);
                finish();
            }
        });
    }
}