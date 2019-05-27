package sg.edu.rp.c346.c302_p06_sakilaclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    private ListView lvCategories;
    ArrayList<Category> alCategories = new ArrayList<Category>();
    ArrayAdapter<Category> aaCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCategories = (ListView) findViewById(R.id.listViewCategories);
        aaCategories = new ArrayAdapter<Category>(this, android.R.layout.simple_list_item_1, alCategories);
        lvCategories.setAdapter(aaCategories);

		//TODO: Task 1: Consume getCategories.php using AsyncHttpClient
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2/C302_sakila/getCategories.php", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    for (int i=0 ; i<response.length(); i++) {
                        JSONObject category = (JSONObject) response.get(i);
                        Category c = new Category(category.getInt("category_id"), category.getString("name"));
                        alCategories.add(c);
                    }
                    aaCategories.notifyDataSetChanged();
                }
                catch (JSONException e) {

                }
            }
        });

        lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Category c = alCategories.get(i);  // Get the selected Category
                Intent intent = new Intent(getBaseContext(), ViewFilmsActivity.class );
                intent.putExtra("category", c);
                startActivity(intent);
            }
        });
    }
}
