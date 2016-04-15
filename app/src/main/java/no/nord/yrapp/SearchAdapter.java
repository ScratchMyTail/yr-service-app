package no.nord.yrapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import no.nord.yrapp.activities.SearchActivity;
import no.nord.yrapp.model.YrSok;

/**
 * Created by christerhansen on 15.04.16.
 */
public class SearchAdapter extends ArrayAdapter<YrSok> {
    Context context;
    List<YrSok> yrSokList;

    public SearchAdapter(Context context, int resourceId, List<YrSok> yrSokList){
        super(context, resourceId, yrSokList);
        this.context = context;
        this.yrSokList = yrSokList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(this.context);
            v = vi.inflate(R.layout.search_item, null);
        }
        final YrSok yrSok = this.yrSokList.get(position);

        TextView textView = (TextView)v.findViewById(R.id.place_name);
        textView.setText(yrSok.getSted());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity searchActivity = (SearchActivity)context;
                searchActivity.goBack(yrSok);
            }
        });

        return v;
    }
}
