package appngo.myapp.com.appforngo;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.SecureRandom;

public class Main3Activity extends AppCompatActivity {
    TextView datetext, placetext, eventtime;
    Button yesbutton, nobutton;
    //DatabaseHandler dataBaseHandler = new DatabaseHandler(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        datetext = (TextView) findViewById(R.id.date_id);
        placetext = (TextView) findViewById(R.id.event_place_id);
        eventtime = (TextView) findViewById(R.id.event_time_id);
        yesbutton = (Button) findViewById(R.id.yes_id);
        nobutton = (Button) findViewById(R.id.no_id);



        Intent intent = getIntent();
        final String getDate = intent.getStringExtra("date");
        datetext.setText(getDate);
        final String getPlace = intent.getStringExtra("place");
        placetext.setText(getPlace);
        final String gettime = intent.getStringExtra("time");
        eventtime.setText(gettime);


        yesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main3Activity.this, Main4Activity.class);
                i.putExtra("date",getDate);
                i.putExtra("place",getPlace);
                i.putExtra("time", gettime);
                startActivity(i);
            }

        });
        nobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }

}

