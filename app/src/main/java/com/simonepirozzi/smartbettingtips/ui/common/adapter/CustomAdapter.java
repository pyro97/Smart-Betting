package com.simonepirozzi.smartbettingtips.ui.common.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.simonepirozzi.smartbettingtips.R;
import com.simonepirozzi.smartbettingtips.data.db.model.Tip;
import com.simonepirozzi.smartbettingtips.utils.StatusMatchEnum;
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
    private TextView data, orario, nomeC, nomeT, consiglio, golCasa, golTra;
    private LinearLayout banner;
    private AppCompatImageView fotoC, fotoT, risultato;
    private boolean isPremium ;
    private Context context;

    public CustomAdapter(Context context, int resource, List<Tip> objects, boolean isPremium) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.isPremium = isPremium;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        if (v == null) {
            v = inflater.inflate(R.layout.list_element, null);
        }
        tip = getItem(position);
        data = v.findViewById(R.id.data);
        orario = v.findViewById(R.id.orario);
        nomeC = v.findViewById(R.id.nomeC);
        nomeT = v.findViewById(R.id.nomeT);
        consiglio = v.findViewById(R.id.tip);
        fotoC = v.findViewById(R.id.fotoC);
        fotoT = v.findViewById(R.id.fotoT);
        risultato = v.findViewById(R.id.risultato);
        golCasa = v.findViewById(R.id.golC);
        golTra = v.findViewById(R.id.golT);
        banner = v.findViewById(R.id.bannerLayout);
        if(isPremium){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                banner.setBackgroundColor(context.getColor(R.color.colorGold));
            }
        }
        data.setText(tip.getGiorno() + " - " + tip.getLeague());
        orario.setText(tip.getOrario());
        nomeC.setText(tip.getCasa());
        nomeT.setText(tip.getTrasferta());
        consiglio.setText(tip.getTip());
        golCasa.setText(tip.getGolC());
        golTra.setText(tip.getGolT());

        Picasso.get().load(tip.getFotoC()).into(fotoC);
        Picasso.get().load(tip.getFotoT()).into(fotoT);

        switch (tip.getStato()){
            case STATUS_LOADING:
                risultato.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_action_name));
                break;
            case STATUS_LOSE:
                risultato.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_action_canc));
                break;
            case STATUS_WIN:
                risultato.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_action_ok));
                break;
        }

        data.setTag(position);
        orario.setTag(position);
        nomeC.setTag(position);
        nomeT.setTag(position);
        consiglio.setTag(position);
        fotoC.setTag(position);
        fotoT.setTag(position);

        return v;
    }
}

