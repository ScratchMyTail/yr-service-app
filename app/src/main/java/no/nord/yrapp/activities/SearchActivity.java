package no.nord.yrapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import no.nord.yrapp.R;
import no.nord.yrapp.SearchAdapter;
import no.nord.yrapp.YrService;
import no.nord.yrapp.model.YrSok;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";
    private static final String BASE_URL = "http://studit.hinesna.no:3001/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Context context = this;
        final EditText editText = (EditText)findViewById(R.id.sok);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                YrService yrService = retrofit.create(YrService.class);
                Call<List<YrSok>> call = yrService.getPlaces(editText.getText().toString());



                call.enqueue(new Callback<List<YrSok>>() {
                    @Override
                    public void onResponse(Response<List<YrSok>> response, Retrofit retrofit) {
                        if(response.isSuccess()) {
                            Log.d(TAG, response.body().size()+"");
                            ListView listView = (ListView) findViewById(R.id.sok_listview);
                            listView.setAdapter(new SearchAdapter(context, R.layout.search_item, response.body()));
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

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void goBack(YrSok yrSok){
        Intent intent = this.getIntent();
        //String val = intent.getExtras().getString("key");
        intent.putExtra("sted", yrSok.getSted());
        intent.putExtra("url", yrSok.getUrl());
        setResult(1, intent);
        finish(); // Ødelegg activity onFinished() kjører
    }
}
