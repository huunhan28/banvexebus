/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VeXe;

/**
 *
 * @author ADMIN
 */
public class VeXe {
    private int soChoDat;
    private String maVe, maChuyenXe,noiDi,noiDen, taiKhoan,maLoaiVe;

    public VeXe() {
    }

    public VeXe(String maVe, int soChoDat, String maChuyenXe, String noiDi, String noiDen, String taiKhoan, String maLoaiVe) {
        this.maVe = maVe;
        this.soChoDat = soChoDat;
        this.maChuyenXe = maChuyenXe;
        this.noiDi = noiDi;
        this.noiDen = noiDen;
        this.taiKhoan = taiKhoan;
        this.maLoaiVe = maLoaiVe;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public int getSoChoDat() {
        return soChoDat;
    }

    public void setSoChoDat(int soChoDat) {
        this.soChoDat = soChoDat;
    }

    public String getMaChuyenXe() {
        return maChuyenXe;
    }

    public void setMaChuyenXe(String maChuyenXe) {
        this.maChuyenXe = maChuyenXe;
    }

    public String getNoiDi() {
        return noiDi;
    }

    public void setNoiDi(String noiDi) {
        this.noiDi = noiDi;
    }

    public String getNoiDen() {
        return noiDen;
    }

    public void setNoiDen(String noiDen) {
        this.noiDen = noiDen;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMaLoaiVe() {
        return maLoaiVe;
    }

    public void setMaLoaiVe(String maLoaiVe) {
        this.maLoaiVe = maLoaiVe;
    }

    
    
}
