package appngo.myapp.com.appforngo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {

    DatabaseHandler dba;
    private CheckBox mathc, scic, hindc, compc, engc, geoc;
    private Button submitbutton;
    private EditText editText;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        dba = new DatabaseHandler(Main4Activity.this);
        mathc = (CheckBox) findViewById(R.id.mat_id);
        scic = (CheckBox) findViewById(R.id.sci_id);
        hindc = (CheckBox) findViewById(R.id.hin_id);
        compc = (CheckBox) findViewById(R.id.com_id);
        engc = (CheckBox) findViewById(R.id.eng_id);
        geoc = (CheckBox) findViewById(R.id.geo_id);
        submitbutton = (Button) findViewById(R.id.sub_id);
        Bundle extras = getIntent().getExtras();
        final String m_date = extras.getString("date");
        final String m_place = extras.getString("place");
        final String m_time = extras.getString("time");

        //String textmessage = "Dear User, your booking details are "
        //+ m_place + " on " + m_date + " betweem " + m_time + " . kindly attend.";


        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dba.addData(m_date, m_place, m_time);

                if (mathc.isChecked()) {
                    String mat = "Maths";
                    dba.addSubjects(mat);
                }
                if (scic.isChecked()) {
                    String sci = "Science";
                    dba.addSubjects(sci);
                }
                if (hindc.isChecked()) {
                    String hin = "Hindi";
                    dba.addSubjects(hin);
                }
                if (engc.isChecked()) {
                    String eng = "English";
                    dba.addSubjects(eng);
                }
                if (geoc.isChecked()) {
                    String geo = "Geography";
                    dba.addSubjects(geo);
                }
                if (compc.isChecked()) {
                    String com = "Computer";
                    dba.addSubjects(com);
                }

                dba.close();

                showDialog();
                Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_LONG).show();


            }
        });
    }

    private void showDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(Main4Activity.this);
        View promptView = layoutInflater.inflate(R.layout.dialog_box, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Main4Activity.this);
        alertDialogBuilder.setView(promptView);

        editText = (EditText) promptView.findViewById(R.id.phn_id);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        sendSMSMessage();
                        Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_LONG).show();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private void sendSMSMessage() {
        Log.d("Send SMS", "");
        String phoneNo = editText.getText().toString();
        String message = "Hi there app is successfull";


        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}


