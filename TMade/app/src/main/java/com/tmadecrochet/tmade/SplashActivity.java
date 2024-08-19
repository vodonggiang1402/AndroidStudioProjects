package com.tmadecrochet.tmade;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }, 3000);
    }

//    public loadData() {
//        try {
//            InputStream myJsonFile = getAssets().open("my_json_data.json");
//            int size = myJsonFile.available();
//            byte[] buffer = new byte[size];
//            myJsonFile.read(buffer);
//            myJsonFile.close();
//            String myJsonData = new String(buffer, "UTF-8");
//            MyResponseModel jsonResponse = gson.fromJson(myJsonData, MyResponseModel.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}