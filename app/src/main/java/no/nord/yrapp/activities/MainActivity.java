package no.nord.yrapp.activities;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.location.config.LocationParams;
import no.nord.yrapp.R;
import no.nord.yrapp.YrService;
import no.nord.yrapp.model.YrURL;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String BASE_URL = "http://studit.hinesna.no:3001/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SmartLocation.with(this).location()
                .oneFix()
                .config(LocationParams.NAVIGATION)
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        Log.d(TAG, location.getLatitude()+"");

                        Gson gson = new GsonBuilder()
                                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                                .create();

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create(gson))
                                .build();

                        YrService yrService = retrofit.create(YrService.class);
                        Call<YrURL> call = yrService.getYrURL(location.getLatitude(), location.getLongitude());

                        call.enqueue(new Callback<YrURL>() {
                            @Override
                            public void onResponse(Response<YrURL> response, Retrofit retrofit) {
                                if(response.isSuccess()) {
                                    Log.d(TAG, response.body().getUrl());
                                }
                                else{
                                    Log.d(TAG, response.message());
                                }
                            }
                            @Override
                            public void onFailure(Throwable t) {
                                Log.d(TAG, t.getMessage());
                            }
                        });



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

        if(id == R.id.action_search){
            Intent intent = new Intent(this, SearchActivity.class);
            startActivityForResult(intent, 1);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            TextView textView = (TextView) findViewById(R.id.hello);
            textView.setText(data.getExtras().getString("sted"));



        }
        catch(Exception ex){
            Log.d(TAG, "ingen plass kom tilbake!");
        }

    }
}
