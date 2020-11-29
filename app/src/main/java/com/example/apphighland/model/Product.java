package com.example.apphighland.model;

import com.example.apphighland.R;

public class Product {
    private int ID;
    private String pName;
    private int SLB;
    private int price;
    private byte[] img;

    public Product(int ID,String pName, int SLB, int price, byte[] img) {
        this.ID = ID;
        this.pName = pName;
        this.SLB = SLB;
        this.price = price;
        this.img = img;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getSLB() {
        return SLB;
    }

    public void setSLB(int SLB) {
        this.SLB = SLB;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
