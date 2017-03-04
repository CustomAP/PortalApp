package com.aceshub.portal;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aceshub.portal.database.helper.DatabaseHelper;
import com.aceshub.portal.server_connection.FacultySubjectMapping;
import com.aceshub.portal.server_connection.StudentSubjectMapping;


public class Login extends AppCompatActivity {

    Button b1;
    TextView tv1, tv2;
    EditText et1, et2;
    FacultySubjectMapping facultySubjectMapping;
    SQLiteDatabase sqLiteDatabase;
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

        //creating database
        sqLiteDatabase = new DatabaseHelper(getApplicationContext()).getWritableDatabase();

        facultySubjectMapping =  new FacultySubjectMapping("411402064", getApplicationContext());



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        //        facultySubjectMapping.run();
                Intent i = new Intent(Login.this, MainActivity.class);
                        startActivity(i);
                        finish();

                }
        });
    }
}