package com.simonepirozzi.smartbettingtips.ui.homepage;

public class HomePageContract {

    public interface View {
        void showProgressBr();
        void hideProgressBar();
    }

    interface Presenter{
        void getTips(String page);
    }
}
