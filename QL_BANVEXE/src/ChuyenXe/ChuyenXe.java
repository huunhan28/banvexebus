/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChuyenXe;
import java.io.*;
public class ChuyenXe {
    private String maChuyenXe,maTuyen,ngay,tongSoChoDat;
    
    public ChuyenXe() {
    }

    public ChuyenXe(String maChuyenXe, String maTuyen, String ngay, String tongSoChoDat) {
        this.maChuyenXe = maChuyenXe;
        this.maTuyen = maTuyen;
        this.ngay = ngay;
        this.tongSoChoDat = tongSoChoDat;
    }

    public String getMaChuyenXe() {
        return maChuyenXe;
    }

    public void setMaChuyenXe(String maChuyenXe) {
        this.maChuyenXe = maChuyenXe;
    }

    public String getMaTuyen() {
        return maTuyen;
    }

    public void setMaTuyen(String maTuyen) {
        this.maTuyen = maTuyen;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getTongSoChoDat() {
        return tongSoChoDat;
    }

    public void setTongSoChoDat(String tongSoChoDat) {
        this.tongSoChoDat = tongSoChoDat;
    }
    
    
}
