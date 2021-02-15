package com.simonepirozzi.smartbettingtips.ui.homepage;

import android.content.Context;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.simonepirozzi.smartbettingtips.data.db.model.Tip;
import com.simonepirozzi.smartbettingtips.ui.common.adapter.CustomAdapter;
import com.simonepirozzi.smartbettingtips.utils.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class HomePagePresenter implements HomePageContract {

    private Context context;
    private CustomAdapter customAdapter;
    private FirebaseFirestore db;


    public HomePagePresenter(Context context, CustomAdapter customAdapter, FirebaseFirestore db) {
        this.context = context;
        this.customAdapter = customAdapter;
        this.db = db;
    }

    @Override
    public void getTips(String page) {
        db.collection(Constants.COLLECTION_PATH).whereEqualTo(Constants.COLLECTION_PAGE, page).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                List<Tip> results = task.getResult().toObjects(Tip.class);
                ArrayList<Tip> completedList = new ArrayList<>();
                for (int i = 0; i < results.size(); i++) {
                    completedList.add(results.get(i));
                }

                Collections.sort(completedList, new Comparator<Tip>() {
                    @Override
                    public int compare(Tip o1, Tip o2) {
                        try {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.FORMAT_PATTERN);
                            Date data1 = simpleDateFormat.parse(o1.getGiorno() + " " + o1.getOrario());
                            Date data2 = simpleDateFormat.parse(o2.getGiorno() + " " + o2.getOrario());
                            return data1.compareTo(data2);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return 0;
                    }
                });

                for (int j = 0; j < completedList.size(); j++) {
                    customAdapter.add(completedList.get(j));
                }
                customAdapter.notifyDataSetChanged();
            }
        });
    }
}
