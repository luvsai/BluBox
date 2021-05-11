package com.example.blubox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class Userdata extends AppCompatActivity {
        Button sub;

        String pn,ph,UID;
        private static final int ACTIVITYuserdet = 2 ,ACTIVITYbotnav =3;
        EditText etpn , etph;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_userdata);
            sub = findViewById(R.id.sub);
            etpn =  findViewById(R.id.etpn);
            etph = findViewById(R.id.etph);

            UID  = getIntent().getStringExtra("UID");


            sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pn = etpn.getText().toString();
                    ph = etph.getText().toString();
                    if (pn.isEmpty() || ph.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please fill the fields", Toast.LENGTH_SHORT).show();
                    } else {
                        userbio dat = new userbio(Userdata.this);
                        dat.save(ph, pn);

                        Intent intent = new Intent();
                        intent.putExtra("pn", pn);
                        intent.putExtra("ph", ph);
                        setResult(RESULT_OK, intent);
                        Userdata.this.finish();
                    }
                }
            });









    }
}