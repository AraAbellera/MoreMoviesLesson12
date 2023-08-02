package sg.edu.rp.c346.id22014114.mymovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Movie> movieList;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> objects) {

        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        movieList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.tvTitle);
        TextView tvGenre = rowView.findViewById(R.id.tvGenre);
        TextView tvYear = rowView.findViewById(R.id.tvYear);
        ImageView ivRating = rowView.findViewById(R.id.iv);

        // Obtain the Android Version information based on the position
        Movie currentItem = movieList.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setText(currentItem.getTitle());
        tvGenre.setText(currentItem.getGenre());
        tvYear.setText(String.valueOf(currentItem.getYear()));

        if (currentItem.getRating() == "G") {
            ivRating.setImageResource(R.drawable.g);
        } else if (currentItem.getRating() == "PG"){
            ivRating.setImageResource(R.drawable.pg);
        } else if (currentItem.getRating() == "PG13"){
            ivRating.setImageResource(R.drawable.pg13);
        } else if (currentItem.getRating() == "NC16"){
            ivRating.setImageResource(R.drawable.nc16);
        } else if (currentItem.getRating() == "M18"){
            ivRating.setImageResource(R.drawable.m18);
        } else if (currentItem.getRating() == "R21"){
            ivRating.setImageResource(R.drawable.r21);
        }

        return rowView;
    }

}
