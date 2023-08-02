package sg.edu.rp.c346.id22014114.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    Movie rID, rTitle, rGenre, rYear, rRating;
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

        rID = (Movie) i.getSerializableExtra("ID");
        id.setText(String.valueOf(rID.getId()));
        rTitle = (Movie) i.getSerializableExtra("Title");
        title.setText(rTitle.getTitle());
        rGenre = (Movie) i.getSerializableExtra("Genre");
        genre.setText(rGenre.getGenre());
        rYear = (Movie) i.getSerializableExtra("Year");
        year.setText(String.valueOf(rYear.getYear()));
        rRating = (Movie) i.getSerializableExtra("Rating");


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(UpdateMovie.this);

                String stTitle = String.valueOf(title.getText());
                String stGenre = String.valueOf(genre.getText());
                int iYear = Integer.valueOf(String.valueOf(year.getText()));
                String stRating = rating.toString();

                rTitle.setMovieTitle(stTitle);
                rGenre.setMovieGenre(stGenre);
                rYear.setMovieYear(iYear);
                rRating.setMovieRating(stRating);

                db.updateMovie(rTitle,rGenre,rYear,rRating);

                Toast.makeText(UpdateMovie.this, "Movie successfully updated", Toast.LENGTH_SHORT).show();
                db.close();
                finish();


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(UpdateMovie.this);
                db.deleteMovie(rID.getId());
                finish();
            }
        });

    }

}