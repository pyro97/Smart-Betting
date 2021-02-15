package com.simonepirozzi.smartbettingtips.ui.homepage;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.simonepirozzi.smartbettingtips.R;
import com.simonepirozzi.smartbettingtips.data.db.model.Tip;
import com.simonepirozzi.smartbettingtips.ui.common.adapter.CustomAdapter;
import com.simonepirozzi.smartbettingtips.utils.Constants;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FirebaseFirestore db;
    private CustomAdapter customAdapter;
    private ListView listView;
    private HomePagePresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();
        customAdapter=new CustomAdapter(view.getContext(),R.layout.list_element,new ArrayList<Tip>(),false);
        listView=view.findViewById(R.id.listView);
        listView.setAdapter(customAdapter);
        mPresenter = new HomePagePresenter(view.getContext(),customAdapter,db);
        mPresenter.getTips(Constants.NEW_PAGE);
    }
}
