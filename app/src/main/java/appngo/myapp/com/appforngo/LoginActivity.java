package appngo.myapp.com.appforngo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText fName, lName, userNam, passw;

    DatabaseHandler dba =new DatabaseHandler(LoginActivity.this);
    private Button register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        fName = (EditText) findViewById(R.id.first_id);
        lName = (EditText) findViewById(R.id.last_id);
        userNam = (EditText) findViewById(R.id.user_id);
        passw = (EditText) findViewById(R.id.pass_id);
        register_button = (Button) findViewById(R.id.submit_id);




        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((fName.getText().toString().isEmpty())  || (lName.getText().toString().isEmpty())
                   || (userNam.getText().toString().isEmpty())  || (passw.getText().toString().isEmpty()))
                {
                    Toast.makeText(getApplicationContext(), "Incomplete entries", Toast.LENGTH_LONG).show();
                }
                else{


                saveToDb();

                Intent i = new Intent(LoginActivity.this, StartActivity.class);
                startActivity(i);
                }

            }
        });
    }

    private void saveToDb() {

        userDetails user = new userDetails();
        user.setUsername(userNam.getText().toString().trim());
        user.setFirstname(fName.getText().toString().trim());
        user.setLastname(lName.getText().toString().trim());
        user.setPasswpord(passw.getText().toString().trim());

        dba.addUserDetails(user);
        dba.close();
        userNam.setText("");
        fName.setText("");
        lName.setText("");
        passw.setText("");
    }
}
