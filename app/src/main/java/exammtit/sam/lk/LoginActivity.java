package exammtit.sam.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText userName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = findViewById(R.id.txtLoginUsername);
        password = findViewById(R.id.txtLoginPassword);

    }

    public void Login(View view) {
        try {

            SharedPreferences sp1 = this.getSharedPreferences("userSession", 0);
            String Unm = sp1.getString("Unm", null);
            String Psw = sp1.getString("Psw", null);

            String loginUsername = userName.getText().toString().trim();
            String loginPassword = password.getText().toString().trim();

            Log.d("Tag", "Username: " + loginUsername);
            Log.d("Tag", "Password: " + loginPassword);

            if (Unm.equals(loginUsername) && Psw.equals(loginPassword)) {
                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, NationActivity.class);
                intent.putExtra("userName", Unm);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Invalid Password or Username", Toast.LENGTH_SHORT).show();
                userName.getText().clear();
                password.getText().clear();
            }

        } catch (Exception e) {
            Log.d("Tag", "Error: " + e.getMessage());
        }
    }

}