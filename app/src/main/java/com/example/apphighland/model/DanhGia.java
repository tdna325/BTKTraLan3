package com.example.apphighland.model;

public class DanhGia {
    int ID;
    int ID_product;
    String Account;
    String noidung;
    int sao;
    String ngay;

    public DanhGia(int ID, int ID_product, String account, String noidung, int sao,String ngay) {
        this.ID = ID;
        this.ID_product = ID_product;
        Account = account;
        this.noidung = noidung;
        this.sao = sao;
        this.ngay = ngay;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID_product() {
        return ID_product;
    }

    public void setID_product(int ID_product) {
        this.ID_product = ID_product;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getSao() {
        return sao;
    }

    public void setSao(int sao) {
        this.sao = sao;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
