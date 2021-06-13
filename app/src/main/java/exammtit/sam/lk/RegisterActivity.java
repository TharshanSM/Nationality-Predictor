package exammtit.sam.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText txtUserName, txtPassword, txtConfimPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtUserName = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfimPassword = findViewById(R.id.txtConfirmPassword);
    }

    public void SignUp(View view) {
        try {

            String username = txtUserName.getText().toString();
            String password = txtPassword.getText().toString();

            if (txtUserName.length() == 0) {
                Log.d("Tag", "UserName Cannot be Empty");
                Toast.makeText(getApplicationContext(), "UserName Cannot be Empty", Toast.LENGTH_SHORT).show();
            } else if (txtPassword.length() == 0 || txtPassword.length() < 6) {
                Log.d("Tag", "Password Cannot be Empty and Length should be greater than 5");
                Toast.makeText(getApplicationContext(), "Password Cannot be Empty and Length should be greater than 5", Toast.LENGTH_SHORT).show();
            } else if (!txtPassword.getText().toString().equals(txtConfimPassword.getText().toString())) {
                Log.d("Tag", "Password MisMatch");
                Toast.makeText(getApplicationContext(), "Password MisMatch", Toast.LENGTH_SHORT).show();
            } else {

                Log.d("Tag", "Successful");
                Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();

                ////
                SharedPreferences sp = getSharedPreferences("userSession", 0);
                SharedPreferences.Editor Ed = sp.edit();
                Ed.putString("Unm", username);
                Ed.putString("Psw", password);
                Ed.commit();
                Log.d("Tag","Successfully saved sharedPrefrences");

                Intent intent = new Intent(RegisterActivity.this, NationActivity.class);
                intent.putExtra("userName", txtUserName.getText().toString());
                startActivity(intent);
                finish();

            }
        } catch (Exception e) {
            Log.d("Tag", "Error: " + e.getMessage());
        }
    }
}