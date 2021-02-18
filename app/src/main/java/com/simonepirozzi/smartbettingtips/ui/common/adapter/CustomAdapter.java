package com.simonepirozzi.smartbettingtips.ui.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.simonepirozzi.smartbettingtips.R;
import com.simonepirozzi.smartbettingtips.data.db.model.Tip;
import com.squareup.picasso.Picasso;
import java.util.List;
import androidx.appcompat.widget.AppCompatImageView;

import static com.simonepirozzi.smartbettingtips.utils.Constants.STATUS_LOADING;
import static com.simonepirozzi.smartbettingtips.utils.Constants.STATUS_LOSE;
import static com.simonepirozzi.smartbettingtips.utils.Constants.STATUS_WIN;

public class CustomAdapter extends ArrayAdapter<Tip> {
    private int resource;
    private LayoutInflater inflater;
    private Tip tip;
    private TextView date, time, home, away, tipText, golHome, golAway;
    private LinearLayout banner;
    private AppCompatImageView photoHome, photoAway, result;
    private Context context;

    public CustomAdapter(Context context, int resource, List<Tip> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        if (v == null) {
            v = inflater.inflate(R.layout.list_element, null);
        }
        tip = getItem(position);
        date = v.findViewById(R.id.date);
        time = v.findViewById(R.id.time);
        home = v.findViewById(R.id.home);
        away = v.findViewById(R.id.away);
        tipText = v.findViewById(R.id.tip);
        photoHome = v.findViewById(R.id.photoHome);
        photoAway = v.findViewById(R.id.photoAway);
        result = v.findViewById(R.id.result);
        golHome = v.findViewById(R.id.golHome);
        golAway = v.findViewById(R.id.golAway);
        banner = v.findViewById(R.id.bannerLayout);
        date.setText(tip.getDay() + " - " + tip.getLeague());
        time.setText(tip.getTime());
        home.setText(tip.getHome());
        away.setText(tip.getAway());
        tipText.setText(tip.getTip());
        golHome.setText(tip.getGolHome());
        golAway.setText(tip.getGolAway());

        Picasso.get().load(tip.getPhotoHome()).into(photoHome);
        Picasso.get().load(tip.getGolAway()).into(photoAway);

        switch (tip.getState()){
            case STATUS_LOADING:
                result.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_action_name));
                break;
            case STATUS_LOSE:
                result.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_action_canc));
                break;
            case STATUS_WIN:
                result.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_action_ok));
                break;
        }

        date.setTag(position);
        time.setTag(position);
        home.setTag(position);
        away.setTag(position);
        tipText.setTag(position);
        photoHome.setTag(position);
        photoAway.setTag(position);

        return v;
    }
}

