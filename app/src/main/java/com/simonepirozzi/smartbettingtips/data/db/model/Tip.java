package com.simonepirozzi.smartbettingtips.data.db.model;

public class Tip {

    String day, time, tip, state, home, away, photoHome, photoAway, league, page, golHome, golAway;

    public Tip() { }

    public Tip(String day, String time, String tip, String state, String home, String away, String photoHome, String photoAway, String league, String page, String golHome, String golAway) {
        this.day = day;
        this.time = time;
        this.tip = tip;
        this.state = state;
        this.home = home;
        this.away = away;
        this.photoHome = photoHome;
        this.photoAway = photoAway;
        this.league = league;
        this.page = page;
        this.golHome = golHome;
        this.golAway = golAway;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }

    public String getPhotoHome() {
        return photoHome;
    }

    public void setPhotoHome(String photoHome) {
        this.photoHome = photoHome;
    }

    public String getPhotoAway() {
        return photoAway;
    }

    public void setPhotoAway(String photoAway) {
        this.photoAway = photoAway;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getGolHome() {
        return golHome;
    }

    public void setGolHome(String golHome) {
        this.golHome = golHome;
    }

    public String getGolAway() {
        return golAway;
    }

    public void setGolAway(String golAway) {
        this.golAway = golAway;
    }
}
