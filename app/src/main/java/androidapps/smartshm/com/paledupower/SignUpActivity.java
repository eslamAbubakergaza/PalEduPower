package androidapps.smartshm.com.paledupower;


        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import org.json.JSONException;
        import org.json.JSONObject;

        import androidapps.smartshm.com.paledupower.helper.SQLiteHandler;
        import androidapps.smartshm.com.paledupower.helper.SessionManager;

public class SignUpActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;
    private EditText nameInput,emailInput,phoneInput,passwordInput,ageInput;
    private String email,name,phone,age;
    private String password;
    private Button signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nameInput = (EditText) findViewById(R.id.editText);
        passwordInput = (EditText) findViewById(R.id.editText8);
        ageInput = (EditText) findViewById(R.id.editText7);
        phoneInput = (EditText) findViewById(R.id.editText3);
        emailInput = (EditText) findViewById(R.id.editText2);




        signUpBtn = (Button) findViewById(R.id.button2);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=emailInput.getText().toString().trim();
                name=nameInput.getText().toString().trim();
                age=ageInput.getText().toString().trim();
                password=passwordInput.getText().toString().trim();
                phone=phoneInput.getText().toString().trim();

                // Check for empty data in the form
                if (!email.isEmpty() && !password.isEmpty()&&!age.isEmpty()&&!phone.isEmpty()&&!name.isEmpty()) {

                    new SignupTask().execute();
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {


            finish();
        }

    }

    private class SignupTask extends AsyncTask<Void, Void, Void> {
        String jsonStr="";
        ProgressDialog pdLoading = new ProgressDialog(SignUpActivity.this);
        String uid;
        JSONObject user;
        boolean error;


        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            jsonStr = sh.makeServiceCall(UrlHelperLinks.get_reg+"?email="+email+"&password="+password+"&name="+name+"&age="+age+"&phone="+phone);


            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    error=jsonObj.getBoolean("error");
                    if(!error){

                        uid = jsonObj.getString("uid");
                        user = jsonObj.getJSONObject("user");
                    }



                } catch (final JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            try {
                if(!error) {
                    session.setLogin(true);
                    String name = user.getString("name");
                    String email = user.getString("email");
                    String phone = user.getString("phone");
                    String age = user.getString("age");

                    String created_at = user.getString("created_at");

                    //Inserting row in users table
                    db.addUser(name, email, uid,phone,age, created_at);



                    finish();
                }
            } catch (final JSONException e) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

            }

        }
    }
}
