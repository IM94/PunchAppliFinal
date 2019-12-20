package fr.imane.punchAppFinal.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

import fr.imane.punchAppFinal.PunchApi;
import fr.imane.punchAppFinal.model.Punchline;
import fr.imane.punchAppFinal.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.postListView);
        searchView = findViewById(R.id.searchView1);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getPosts();


    }


    private void getPosts(){

        //Creating a retrofit obkect
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PunchApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //creating the punchApi interface
        PunchApi punchApi = retrofit.create(PunchApi.class);

        //making the call object
        Call<List<Punchline>> call = punchApi.getPosts();

        call.enqueue(new Callback<List<Punchline>>() {
            @Override
            public void onResponse(@NonNull Call<List<Punchline>> call, @NonNull Response<List<Punchline>> response) {

                final List<Punchline> punchlineList = response.body();

                //Creating a String array for the Listview
                final String[] posts = new String[punchlineList.size()];

                //looping through all the posts
                for (int i = 0; i < punchlineList.size(); i++) {
                    posts[i] = punchlineList.get(i).getContent()+"\n Auteur : "+punchlineList.get(i).getAuthor();
                }

                //displaying the string array into listview
                final ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, posts);
                listView.setAdapter(adapter);


                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String text) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String text) {
                        adapter.getFilter().filter(text);
                        return false;
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Punchline>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
