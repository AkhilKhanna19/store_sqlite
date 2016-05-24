package appngo.myapp.com.appforngo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    private EditText m_username, m_password;
    private Button login_button, regiser_button;
    DatabaseHandler dba;
    userDetails userd = new userDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        m_username = (EditText) findViewById(R.id.username_id);
        m_password = (EditText) findViewById(R.id.password_id);
        login_button = (Button) findViewById(R.id.login_id);
        regiser_button = (Button) findViewById(R.id.register_id);





        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dba = new DatabaseHandler(StartActivity.this);
                String username = m_username.getText().toString();
                String password = m_password.getText().toString();

                String user = dba.userInformation().getUsername();
                String name = dba.userInformation().getFirstname();
                Log.d("here is user", user);
                String password_m = dba.userInformation().getPasswpord();
                Log.d("here password", password_m);
                if((username.equals(user)) && (password.equals(password_m))){
                    Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(StartActivity.this, Main2Activity.class);
                    //intent.putExtra("firstname",name);
                    startActivity(intent);
                }
                else if (username.isEmpty()  ||  password.isEmpty())
                    Toast.makeText(getApplicationContext(), "Invalid entry", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "Invalid entry", Toast.LENGTH_LONG).show();

                }
        });

        regiser_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });






    }

}
