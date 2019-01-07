package com.example.directoryx.justgo.Model;

public class Users {

    public String nama,emergencynumber,urlphoto,uid;

    public Users(String nama,String emergencynumber,String urlphoto,String uid){
        this.nama = nama;
        this.emergencynumber = emergencynumber;
        this.urlphoto = urlphoto;
        this.uid = uid;

    }

    public Users(){

    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmergencynumber() {
        return emergencynumber;
    }

    public void setEmergencynumber(String emergencynumber) {
        this.emergencynumber = emergencynumber;
    }

    public String getUrlphoto() {
        return urlphoto;
    }

    public void setUrlphoto(String urlphoto) {
        this.urlphoto = urlphoto;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
