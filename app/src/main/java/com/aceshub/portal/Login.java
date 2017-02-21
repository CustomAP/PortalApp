package com.aceshub.portal;

import android.content.Intent;
import android.graphics.Typeface;
import android.icu.text.DisplayContext;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button b1;
    TextView tv1, tv2;
    EditText et1, et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //  setContentView(R.layout.activity_main);

        //Using custom fonts in "assets" directory

        //getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.bp_material_button_background));

        tv1 = (TextView)findViewById(R.id.textView3);
        Typeface tf1 = Typeface.createFromAsset(getAssets(), "Ubuntu-Regular.ttf");
        tv1.setTypeface(tf1);

        tv2 = (TextView)findViewById(R.id.textView4);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), "Raleway-Light.ttf");
        tv2.setTypeface(tf2);

        et1 = (EditText)findViewById(R.id.Username);
        Typeface tf3 = Typeface.createFromAsset(getAssets(), "Raleway-Light.ttf");
        et1.setTypeface(tf3);

        et2 = (EditText)findViewById(R.id.Password);
        Typeface tf4 = Typeface.createFromAsset(getAssets(), "Raleway-Light.ttf");
        et2.setTypeface(tf4);

        b1=(Button)findViewById(R.id.button4);
        Typeface tf5 = Typeface.createFromAsset(getAssets(), "Raleway-Light.ttf");
        b1.setTypeface(tf5);

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