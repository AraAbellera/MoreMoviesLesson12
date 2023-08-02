package sg.edu.rp.c346.id22014114.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button insert, display;
    EditText title, genre, year;
    Spinner rating;
    String stRating;

    ArrayList<Movie> al;
    ArrayAdapter<Movie> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert = findViewById(R.id.btnInsert);
        display = findViewById(R.id.btnDisplay);
        title = findViewById(R.id.title);
        genre = findViewById(R.id.genre);
        year = findViewById(R.id.year);
        rating = findViewById(R.id.rating);

        al = new ArrayList<Movie>();
        aa = new ArrayAdapter<Movie>(this,
                android.R.layout.simple_list_item_1, al);

        rating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Handle item selection here
                switch (position){
                    case 0:
                        stRating = "G";
                        break;
                    case 1:
                        stRating = "PG";
                        break;
                    case 2:
                        stRating = "PG13";
                        break;
                    case 3:
                        stRating = "NC16";
                        break;
                    case 4:
                        stRating = "M18";
                        break;
                    case 5:
                        stRating = "R21";
                        break;

                }
                Toast.makeText(MainActivity.this, "Selected: " + stRating, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where nothing is selected (optional)
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper db = new DBHelper(MainActivity.this);

                String stTitle = String.valueOf(title.getText());
                String stGenre = String.valueOf(genre.getText());
                int iYear = Integer.valueOf(String.valueOf(year.getText()));
                db.insertMovie(stTitle, stGenre, iYear, stRating);
                Toast.makeText(MainActivity.this, "Movie successfully added", Toast.LENGTH_SHORT).show();
            }

        });


        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayMovie.class);
                startActivity(intent);
            }
        });


    }

}