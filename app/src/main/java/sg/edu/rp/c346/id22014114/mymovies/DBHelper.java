package sg.edu.rp.c346.id22014114.mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "movies.db";
    private static final String TABLE_TASK = "Movie";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATING = "rating";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_TASK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_GENRE + " TEXT,"
                + COLUMN_YEAR + " INTEGER,"
                + COLUMN_RATING + " TEXT )";
        db.execSQL(createTableSql);
        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertMovie(String title, String genre, int year, String rating) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_GENRE, genre);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_RATING, rating);

        long result = db.insert(TABLE_TASK, null, values);
        db.close();
        Log.d("SQL Insert", "ID:" + result);
        return result;

    }

    public ArrayList<Movie> getMovies() {
        ArrayList<Movie> Movie = new ArrayList<Movie>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
        Cursor cursor = db.query(TABLE_TASK, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                Movie obj = new Movie(id, title, genre, year, rating);
                Movie.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Movie;
    }

    public ArrayList<Movie> getAllMovies(String keyword) {
        ArrayList<Movie> Movie = new ArrayList<Movie>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
        String condition = COLUMN_RATING + " Like ?";
        String[] args = {"%" + keyword + "%"};
        Cursor cursor = db.query(TABLE_TASK, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                Movie obj = new Movie(id, title, genre, year, rating);
                Movie.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Movie;
    }

    public int updateMovie(Movie title, Movie genre, Movie year, Movie rating){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title.getTitle());
        values.put(COLUMN_GENRE, genre.getGenre());
        values.put(COLUMN_YEAR, year.getYear());
        values.put(COLUMN_RATING, rating.getRating());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(title.getId())};
        int result = db.update(TABLE_TASK, values, condition, args);
        db.close();
        return result;
    }

    public int deleteMovie(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_TASK, condition, args);
        db.close();
        return result;
    }

    public int deleteNote(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_TASK, condition, args);
        db.close();
        return result;
    }
}
