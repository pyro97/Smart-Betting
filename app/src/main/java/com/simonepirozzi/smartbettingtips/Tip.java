package com.simonepirozzi.smartbettingtips;

public class Tip {

    String giorno,orario,tip,stato,casa,trasferta,fotoC,fotoT,league,pagina,golC,golT;

    public Tip(){

    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public String getGolC() {
        return golC;
    }

    public void setGolC(String golC) {
        this.golC = golC;
    }

    public String getGolT() {
        return golT;
    }

    public void setGolT(String golT) {
        this.golT = golT;
    }

    public Tip(String giorno, String orario, String tip, String stato,
               String league, String casa, String trasferta, String fotoC, String fotoT, String pagina, String golC, String golT ){
        this.giorno=giorno;
        this.orario=orario;
        this.tip=tip;
        this.stato=stato;
        this.casa=casa;
        this.trasferta=trasferta;
        this.fotoC=fotoC;
        this.fotoT=fotoT;
        this.league=league;
        this.pagina=pagina;
        this.golC=golC;
        this.golT=golT;
    }


    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getGiorno() {
        return giorno;
    }

    public void setGiorno(String giorno) {
        this.giorno = giorno;
    }

    public String getOrario() {
        return orario;
    }

    public void setOrario(String orario) {
        this.orario = orario;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }

    public String getTrasferta() {
        return trasferta;
    }

    public void setTrasferta(String trasferta) {
        this.trasferta = trasferta;
    }

    public String getFotoC() {
        return fotoC;
    }

    public void setFotoC(String fotoC) {
        this.fotoC = fotoC;
    }

    public String getFotoT() {
        return fotoT;
    }

    public void setFotoT(String fotoT) {
        this.fotoT = fotoT;
    }


}
