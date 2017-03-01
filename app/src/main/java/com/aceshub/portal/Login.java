package com.aceshub.portal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    Button b1;
    TextView tv1, tv2;
    EditText et1, et2;
    AQuery aQuery;
    String url = "http://10.10.1.6:9999/atten/index.php/welcome/first";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar2));
        }
        //  setContentView(R.layout.activity_main);
        //Initialisation of objects should be done together at starting of activity
        tv1 = (TextView)findViewById(R.id.textView3);
        et1 = (EditText)findViewById(R.id.Username);
        et2 = (EditText)findViewById(R.id.Password);
        b1=(Button)findViewById(R.id.button4);
        aQuery = new AQuery(getApplicationContext());



        //Using custom fonts in "assets" directory

        //getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.bp_material_button_background));


       /* Typeface tf1 = Typeface.createFromAsset(getAssets(), "Ubuntu-Regular.ttf");
        tv1.setTypeface(tf1);


        Typeface tf2 = Typeface.createFromAsset(getAssets(), "Raleway-Light.ttf");
        tv2.setTypeface(tf2);

        Typeface tf3 = Typeface.createFromAsset(getAssets(), "Raleway-Light.ttf");
        et1.setTypeface(tf3);

        Typeface tf4 = Typeface.createFromAsset(getAssets(), "Raleway-Light.ttf");
        et2.setTypeface(tf4);

        Typeface tf5 = Typeface.createFromAsset(getAssets(), "Raleway-Light.ttf");
        b1.setTypeface(tf5);*/

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et1.getText().toString();
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("id", "410902014");
                aQuery.ajax(url, params, JSONObject.class,new AjaxCallback<JSONObject>() {
                    @Override
                    public void callback(String url, JSONObject json, AjaxStatus status) {
                        if(json != null){
                            try {
                                int length = json.getInt("length");

                                String in = json.getString("ResultSet");

                                JSONObject reader = new JSONObject(in);
                                for(int i = 0; i < length;i++) {
                                    JSONObject obj = reader.getJSONObject(String.valueOf(i));
                                    int FID = obj.getInt("FID");
                                    String SubjectCode = obj.getString("SubjectCode");
                                    String SubjectTitle = obj.getString("SubjectTitle");
                                    int DivisionID = obj.getInt("DivisionID");
                                    String PicklistValueName = obj.getString("PicklistValueName");
                                    String BranchName = obj.getString("BranchName");
                                    int FacultySubjectMappingID = obj.getInt("FacultySubjectMappingID");
                                 //   Toast.makeText(Login.this, ""+FID, Toast.LENGTH_SHORT).show();
                                }
                                Intent i = new Intent(Login.this,MainActivity.class);
                                startActivity(i);
                                finish();
                            }catch(Exception e){e.printStackTrace();}
                        } else
                            Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}