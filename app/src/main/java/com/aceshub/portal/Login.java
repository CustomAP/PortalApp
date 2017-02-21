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
import android.widget.TextView;

public class Login extends AppCompatActivity {

    Button b1;
    TextView tv1, tv2;
    EditText et1, et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar));
        }
        //  setContentView(R.layout.activity_main);
        //Initialisation of objects should be done together at starting of activity
        tv1 = (TextView)findViewById(R.id.textView3);
        et1 = (EditText)findViewById(R.id.Username);
        et2 = (EditText)findViewById(R.id.Password);
        b1=(Button)findViewById(R.id.button4);




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
                Intent i = new Intent(Login.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

}