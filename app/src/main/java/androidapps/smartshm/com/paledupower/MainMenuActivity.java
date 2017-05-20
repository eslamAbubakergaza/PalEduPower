package androidapps.smartshm.com.paledupower;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener{

    Button but1,but2,but3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        but1=(Button) findViewById(R.id.btn5);
        but2=(Button) findViewById(R.id.btn6);
        but3=(Button) findViewById(R.id.btn8);

        but1.setOnClickListener(this);
        but2.setOnClickListener(this);
        but3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn5:
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn6:
                 intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn8:
                 intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
