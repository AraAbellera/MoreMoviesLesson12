package sg.edu.rp.c346.id22014114.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class DisplayMovie extends AppCompatActivity {

    ArrayList<String> movies;
    ListView lv;
    Button btnpg13,back;
    ArrayList<Movie> al;
    CustomAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();
        al = new ArrayList<Movie>();
        adapter = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(adapter);
        Intent i = getIntent();
        DBHelper db = new DBHelper(DisplayMovie.this);
        al.clear();
        al.addAll(db.getMovies());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        lv = findViewById(R.id.lvDisplay);
        btnpg13 = findViewById(R.id.btnpg13);
        back = findViewById(R.id.btnback);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Movie data = al.get(position);
                Intent i = new Intent(DisplayMovie.this,
                        UpdateMovie.class);
                i.putExtra("ID", data);
                i.putExtra("Title", data);
                i.putExtra("Genre", data);
                i.putExtra("Year", data);
                i.putExtra("Rating", data);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnpg13.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
//                Intent i = new Intent(DisplaySong.this, EditSong.class);
//                startActivity(i);
                DBHelper dbh = new DBHelper(DisplayMovie.this);
                al.clear();
                String filterText = "PG13";
                al.addAll(dbh.getAllMovies(String.valueOf(filterText.length())));
                adapter.notifyDataSetChanged();

            }

        });



    }
}