package com.simonepirozzi.smartbettingtips;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.appcompat.widget.AppCompatImageView;

public class CustomAdapterPremium extends ArrayAdapter<Tip> {
    private int resource;
    private LayoutInflater inflater;
    private FirebaseFirestore db;

    Tip tip;
    String giorno,mese;
    int numero;

    public CustomAdapterPremium(Context context, int resourceId, List<Tip> objects) {
            super(context, resourceId, objects);
            resource = resourceId;
            inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
    		if (v == null) {
    			v = inflater.inflate(R.layout.list_element_premium, null);
    		}
    		
             tip = getItem(position);

    		TextView data,orario,nomeC,nomeT,consiglio,golCasa,golTra;
    		AppCompatImageView fotoC,fotoT,risultato;

    		data=v.findViewById(R.id.data1);
    		orario=v.findViewById(R.id.orario1);
    		nomeC=v.findViewById(R.id.nomeC1);
    		nomeT=v.findViewById(R.id.nomeT1);
    		consiglio=v.findViewById(R.id.tip1);
    		fotoC=v.findViewById(R.id.fotoC1);
    		fotoT=v.findViewById(R.id.fotoT1);
    		risultato=v.findViewById(R.id.risultato1);
    		golCasa=v.findViewById(R.id.golC1);
    		golTra=v.findViewById(R.id.golT1);


    		data.setText(tip.getGiorno()+" - "+tip.getLeague());
    		orario.setText(tip.getOrario());
    		nomeC.setText(tip.getCasa());
    		nomeT.setText(tip.getTrasferta());
    		consiglio.setText(tip.getTip());
    		golCasa.setText(tip.getGolC());
    		golTra.setText(tip.getGolT());

        Picasso.get().load(tip.getFotoC()).into(fotoC);
        Picasso.get().load(tip.getFotoT()).into(fotoT);

        if(tip.getStato().equalsIgnoreCase("attesa"))   risultato.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_action_name));
        else if(tip.getStato().equalsIgnoreCase("perso"))   risultato.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_action_canc));
        else if(tip.getStato().equalsIgnoreCase("vinto"))   risultato.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_action_ok));



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

