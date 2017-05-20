package androidapps.smartshm.com.paledupower;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ShowCourseConter extends AppCompatActivity {

    public ImageView imageView;
    public TextView textViewCenterName,textViewCourseName , textViewHour,textViewDiscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_course_conter);

        Intent intent = getIntent();


        imageView = (ImageView)findViewById(R.id.imageView);
        textViewHour = (TextView)findViewById(R.id.textViewHour);
        textViewCourseName = (TextView)findViewById(R.id.textViewCourseName);
        textViewCenterName = (TextView)findViewById(R.id.textViewCenterName);
        textViewDiscription = (TextView)findViewById(R.id.textViewDiscription);

        textViewCenterName.setText(intent.getStringExtra("NameCenter"));
        textViewHour.setText(intent.getStringExtra("Hour"));
        textViewCourseName.setText(intent.getStringExtra("Name"));
        textViewDiscription.setText(intent.getStringExtra("Dic"));

        textViewHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogCountry(ShowCourseConter.this);
            }
        });




    }




    //////////////////////
    public void showDialogCountry(AppCompatActivity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_map);
        //TextView textViewTitel = (TextView) dialog.findViewById(R.id.text_pr_name);

        dialog.show();
    }
}
