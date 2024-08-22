package Plash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import Main.MainActivity;
import com.tmadecrochet.tmade.R;
import Helper.SharedPrefHelper;
import Services.SymbolResponse;

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

        try {
            String jsonFileContent = readFile("symbols.json");
            Gson gson = new Gson();
            SymbolResponse response = gson.fromJson(jsonFileContent, SymbolResponse.class);
            Log.i("response","response" + response);
            SharedPrefHelper.setSharedOBJECT(this,"symbol_response", response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }, 3000);
    }

    public String readFile(String fileName) throws IOException
    {
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(getAssets().open(fileName), StandardCharsets.UTF_8));

        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null)
        {
            content.append(line);
        }

        return content.toString();
    }
}