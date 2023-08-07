package sg.edu.rp.c346.id22014114.mymovies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateMovie extends AppCompatActivity {

    Button cancel, update, delete;
    EditText title, genre, year;
    Spinner rating;
    Movie data;
    String stRating;
    TextView id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        id = findViewById(R.id.id);
        title = findViewById(R.id.title);
        genre = findViewById(R.id.genre);
        year = findViewById(R.id.year);
        cancel = findViewById(R.id.btnCancel);
        rating = findViewById(R.id.rating);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);

        Intent i = getIntent();
        data = (Movie) i.getSerializableExtra("data");

        id.setText(String.valueOf(data.getId()));
        title.setText(String.valueOf(data.getTitle()));
        genre.setText(String.valueOf(data.getGenre()));
        year.setText(String.valueOf(data.getYear()));

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
                Toast.makeText(UpdateMovie.this, "Selected: " + stRating, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where nothing is selected (optional)
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(UpdateMovie.this);
                data.setMovieTitle(title.getText().toString());
                data.setMovieGenre(genre.getText().toString());
                data.setMovieYear(Integer.valueOf(year.getText().toString()));
                data.setMovieRating(stRating);
                dbh.updateMovie(data);
                dbh.close();
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(UpdateMovie.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the movie\n" + " '" + String.valueOf(data.getTitle()) + "' " );

                myBuilder.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DBHelper db = new DBHelper(UpdateMovie.this);
                        db.deleteMovie(data.getId());
                        finish();

                    }
                });

                myBuilder.setPositiveButton("CANCEL", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(UpdateMovie.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes");

                myBuilder.setNegativeButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        onBackPressed();

                    }
                });

                myBuilder.setPositiveButton("DO NOT DISCARD", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }

        });

    }

}