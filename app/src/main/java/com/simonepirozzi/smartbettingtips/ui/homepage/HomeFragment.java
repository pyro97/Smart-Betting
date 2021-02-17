package com.simonepirozzi.smartbettingtips.ui.homepage;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.firestore.FirebaseFirestore;
import com.simonepirozzi.smartbettingtips.R;
import com.simonepirozzi.smartbettingtips.data.db.model.Tip;
import com.simonepirozzi.smartbettingtips.ui.common.adapter.CustomAdapter;
import com.simonepirozzi.smartbettingtips.utils.Constants;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements HomePageContract.View{

    private FirebaseFirestore db;
    private CustomAdapter customAdapter;
    private ListView listView;
    private HomePagePresenter mPresenter;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();
        customAdapter = new CustomAdapter(view.getContext(), R.layout.list_element, new ArrayList<Tip>());
        listView = view.findViewById(R.id.listView);
        progressBar = view.findViewById(R.id.progress_bar);
        listView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        listView.setAdapter(customAdapter);
        mPresenter = new HomePagePresenter(view.getContext(), customAdapter, db,this);
        mPresenter.getTips(Constants.NEW_PAGE);
    }

    @Override
    public void showProgressBr() {
        listView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        listView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }
}
