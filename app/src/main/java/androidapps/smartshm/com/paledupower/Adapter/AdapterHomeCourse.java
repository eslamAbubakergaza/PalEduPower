package androidapps.smartshm.com.paledupower.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import androidapps.smartshm.com.paledupower.Model.Course;
import androidapps.smartshm.com.paledupower.R;


/**
 * Created by Fedaa Lubbad on 11/26/2016.
 */

public class AdapterHomeCourse extends RecyclerView.Adapter<AdapterHomeCourse.MyViewHolder> {

    Context context;
    ArrayList<Course> courses;
    LayoutInflater inflater;
    OnItemClickListener onItemClickListener;


    public AdapterHomeCourse(Context context, ArrayList<Course> courses/*, ArrayList<String>texts*/) {
        this.context = context;
        // this.texts = texts;
        this.courses = courses;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_card_course, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


          //  Picasso.with(context).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQx4C8LJqGxMZ22rGu-E1G8w67xlpNVW0fm7D-j-SdE3BtEwfCNTQ").into(holder.imageView);
            holder.textViewCenterName.setText(courses.get(position).getNameCenter());
            holder.textViewHour.setText(courses.get(position).getHour());
            holder.textViewCourseName.setText(courses.get(position).getName());




    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewCenterName,textViewCourseName , textViewHour;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textViewHour = (TextView) itemView.findViewById(R.id.textViewHour);
            textViewCourseName = (TextView) itemView.findViewById(R.id.textViewCourseName);
            textViewCenterName = (TextView) itemView.findViewById(R.id.textViewCenterName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(courses.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickListener {

        void onItemClick(Course course);

    }





    @Override
    public int getItemViewType(int position) {
        return position;
    }


}

