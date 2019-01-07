package com.example.directoryx.justgo.Model;

public class ListUser {

    public String nama,distance;

    public int thumbnail;

    public ListUser(String nama,String distance,int thumbnail){
        this.nama = nama;
        this.distance = distance;
        this.thumbnail = thumbnail;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
