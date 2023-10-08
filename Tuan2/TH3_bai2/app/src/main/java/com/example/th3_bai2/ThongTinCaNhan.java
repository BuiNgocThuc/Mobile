package com.example.th3_bai2;

public class ThongTinCaNhan {
    private String hoten, cmnd, sothich, trinhdo;
    public ThongTinCaNhan(String hoten, String cmnd,
                         String sothich, String trinhdo)
    {
        setThongtin(hoten, cmnd, sothich, trinhdo);
    }
    public void setThongtin(String hoten, String cmnd,String sothich, String trinhdo)
    {
        this.hoten = hoten;
        this.cmnd = cmnd;
        this.sothich = sothich;
        this.trinhdo = trinhdo;
    }
    @Override
    public String toString()
    {
        String s="";
        s += "Xin chào "+ hoten +", CMND: " + cmnd +", trình độ: " + trinhdo + ", sở thích: " + sothich;
        return s;
    }
}
