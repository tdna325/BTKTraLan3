package com.example.apphighland.model;

public class GioHang {
    private int ID;
    private String Name;
    private int Price;
    private int SL;
    private byte[] Image;

    public GioHang(int ID, String name, int price, byte[] image,int SL) {
        this.ID = ID;
        this.Name = name;
        this.Price = price;
        this.Image = image;
        this.SL =SL;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getSL() {
        return SL;
    }

    public void setSL(int SL) {
        this.SL = SL;
    }

    public int getTongTien() {
        return SL*Price;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }


}
