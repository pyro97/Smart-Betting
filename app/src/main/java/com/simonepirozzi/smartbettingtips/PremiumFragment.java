package com.simonepirozzi.smartbettingtips;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PremiumFragment extends Fragment {

    FirebaseFirestore db;
    CustomAdapterPremium customAdapter;
    ListView listView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_premium, container, false);
        db = FirebaseFirestore.getInstance();
        customAdapter=new CustomAdapterPremium(view.getContext(),R.layout.list_element_premium,new ArrayList<Tip>());
        listView=view.findViewById(R.id.listViewP);
        listView.setAdapter(customAdapter);
        pronostici();


        return view;
    }


    public void pronostici(){
        db.collection("/pronostici").whereEqualTo("pagina","premium").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<Tip> nuovi=task.getResult().toObjects(Tip.class);
                ArrayList<Tip>  listaFinale=new ArrayList<>();
                for(int i=0;i<nuovi.size();i++){

                    listaFinale.add(nuovi.get(i));
                }

                Collections.sort(listaFinale, new Comparator<Tip>() {
                    @Override
                    public int compare(Tip o1, Tip o2) {
                        try {
                            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd:MM:yyyy hh:mm");
                            Date data1= simpleDateFormat.parse(o1.getGiorno()+" "+o1.getOrario());
                            Date data2= simpleDateFormat.parse(o2.getGiorno()+" "+o2.getOrario());
                            return data1.compareTo(data2);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return 0;
                    }
                });

                for(int j=0;j<listaFinale.size();j++){
                    customAdapter.add(listaFinale.get(j));
                }
            }
        });
    }
}
