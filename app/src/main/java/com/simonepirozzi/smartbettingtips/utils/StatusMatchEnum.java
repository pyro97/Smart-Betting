package com.simonepirozzi.smartbettingtips.utils;

public enum StatusMatchEnum {
    STATUS_LOADING("attesa"), STATUS_LOSE("perso"), STATUS_WIN("vinto");

    public String value;
    StatusMatchEnum(String value) {
        this.value = value;
    }
}
