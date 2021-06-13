package exammtit.sam.lk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NationActivity extends AppCompatActivity {
    TextView txtMessage;
    EditText txtName, txtAge;
    RadioButton rdoMale, rdoFemale;


    //username getting from intent
    String message;


    AlertDialog.Builder builder;

    //name predictor
    String Name;
    String URL = "https://api.nationalize.io/?name=";
    RequestQueue myqueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nation);
        txtName = findViewById(R.id.txtName);
        txtAge = findViewById(R.id.txtAge);
        rdoMale = findViewById(R.id.tdoMale);
        rdoFemale = findViewById(R.id.rdoFemale);

        //Get the details from intent
        txtMessage = findViewById(R.id.txtMessage);
        message = getIntent().getExtras().getString("userName");
        txtMessage.setText("Welcome: " + message);

        //API Queue
        myqueue = Volley.newRequestQueue(this);

        //alert dialog installation
        builder = new AlertDialog.Builder(this);
    }

    public void addPerson(View view) {
        try {
//            int age=Integer.parseInt(txtAge.getText().toString());
            String name=txtName.getText().toString().trim();
            if (name.length()==0) {
                Log.d("Tag", "Name Field Cannot be Empty");
                Toast.makeText(getApplicationContext(), "Name Field Cannot be Empty", Toast.LENGTH_SHORT).show();
            } else if (txtAge.length() == 0) {
                Log.d("Tag", "Age Field Cannot be Empty ");
                Toast.makeText(getApplicationContext(), "Age Field Cannot be Empty", Toast.LENGTH_SHORT).show();
            } else if (rdoMale.isChecked() == false && rdoFemale.isChecked() == false) {
                Log.d("Tag", "Select Gender");
                Toast.makeText(getApplicationContext(), "Select Gender", Toast.LENGTH_SHORT).show();
            } else {

                Name = txtName.getText().toString();
                int Age = Integer.parseInt(txtAge.getText().toString());
                String Gender = "";
                if (rdoMale.isChecked()) {
                    Gender = "Male";
                }
                if (rdoFemale.isChecked()) {
                    Gender = "Female";
                }
                Log.d("Tag", "Name:" + Name);
                Log.d("Tag", "Age: " + Age);
                Log.d("Tag", "Gender: " + Gender);

                //insert into array list
                Person obj = new Person(Name, Age, Gender);
                ArrayList<Person> list = new ArrayList<Person>();
                list.add(obj);

                //alert dialog
                builder
                        .setMessage("Successfully Inserted")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //clear the text field when data inserted
                                txtName.getText().clear();
                                txtAge.getText().clear();
                                rdoMale.setChecked(false);
                                rdoFemale.setChecked(false);
                            }
                        }).create().show();
            }
        } catch (Exception e) {
            Log.d("Tag", "Error: " + e.getMessage());
        }
    }


    public void PredicAPI(View view) {
        try {
            String updatedURL=URL+Name;
            Log.d("Tag","URL Link: "+updatedURL);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, updatedURL, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String APIname = response.getString("name");
                                JSONArray APIcountry = response.getJSONArray("country");
                                Log.d("Tag", "Name:" + APIname + "\tCountry: " + APIcountry);

                                //alert dialog
                                builder
                                        .setTitle("Prediction")
                                        .setMessage("Name: " + APIname + "\nCountry:" + APIcountry)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //empty activity

                                            }
                                        }).create().show();


                            } catch (JSONException jsonException) {
                                jsonException.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                        }
                    });
            myqueue.add(jsonObjectRequest);


        } catch (Exception e) {
            Log.d("Tag", "Error: " + e.getMessage());
        }
    }

}