package a1138080fabflix.a211.a36.http52.fabflix;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

public class MovieList extends AppCompatActivity {

    EditText mEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        mEdit = (EditText)findViewById(R.id.keywords);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();
            //System.out.println(mEdit.getText().toString());
                try {
                    //establish network connection
                    MyHTTPConnection httpConnection = new MyHTTPConnection(getString(R.string.server));
                    httpConnection.setConnectTimeout(5000);
                    httpConnection.setReadTimeout(5000);
                    httpConnection.setHeader("Accept", "application/json");
                    httpConnection.setHeader("Content-type", "application/json");

                    //JSONObject userJSON = new JSONObject();
                    //userJSON.accumulate("title", mEdit.getText().toString());
                    //httpConnection.setRequestMessage(userJSON.toString());

                    //sends keywords EditText box to server
                    httpConnection.setRequestMessage(mEdit.getText().toString());

                    int responseCode = httpConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK){
                        //IMPORTANT: have server send true if sent userJSON is in customers table
                        JSONObject responseMessage = new JSONObject(httpConnection.getResponseMessage());
                        JSONArray jsonArray = responseMessage.getJSONArray("movie_list");

                        String[] movieArray = new String[jsonArray.length()]; //should just be an array of strings
                        for (int i = 0; i < jsonArray.length(); i++) {
                            //JSONObject title = jsonArray.getJSONObject(i);
                            //JSONMovieParser parser = new JSONMovieParser();
                            movieArray[i] = jsonArray.getString(i);
                        }

                        //makemovielist
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
