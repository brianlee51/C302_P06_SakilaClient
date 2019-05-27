package sg.edu.rp.c346.c302_p06_sakilaclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ViewFilmsActivity extends AppCompatActivity {

    private ListView lvFilms;
    ArrayList<Film> alFilms = new ArrayList<Film>();
    ArrayAdapter<Film> aaFilms;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_films);
        tvTitle = findViewById(R.id.textViewTitle);
        lvFilms = findViewById(R.id.lvDisplay);
        aaFilms = new FilmAdapter(this, android.R.layout.simple_list_item_1, alFilms);
        lvFilms.setAdapter(aaFilms);

        Intent intentReceived = getIntent();
        Category category = (Category) intentReceived.getSerializableExtra("category");
        int id = category.getId();

        tvTitle.setText(category.getName());

        RequestParams params = new RequestParams();
        params.add("id", String.valueOf(id));
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2/C302_sakila/getFilmsByCategoryId.php", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    for (int i=0 ; i<response.length(); i++) {
                        JSONObject film = (JSONObject) response.get(i);
                        Film f = new Film(film.getInt("film_id"), film.getString("title")
                                , film.getInt("release_year"), film.getString("rating"));
                        alFilms.add(f);
                    }
                    aaFilms.notifyDataSetChanged();
                }
                catch (JSONException e) {

                }
            }
        });


    }
}
