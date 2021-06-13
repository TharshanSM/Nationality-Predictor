package exammtit.sam.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

public class StartActivity extends AppCompatActivity {
//    SharedPreferences sharedPreferences;
    private static int SPALASH_SCREEN=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPrefs = getSharedPreferences("userSession", 0);
                SharedPreferences.Editor ed;

                Intent intent;
                if(!sharedPrefs.contains("Unm")){
                    intent = new Intent(StartActivity.this, RegisterActivity.class);
                }else{
                    Log.d("Tag", "SharedPreferences Exists");
                    Log.d("Tag", "SharedPreferences Unm: "+sharedPrefs.getString("Unm", null));
                    Log.d("Tag", "SharedPreferences Psw: "+sharedPrefs.getString("Psw", null));
                    intent = new Intent(StartActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        },SPALASH_SCREEN);

    }
}