/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NhanVien;

/**
 *
 * @author ADMIN
 */
public class NhanVienQL {
    private String maNV,tenNV,ngaySinh,diaChi,chucVu;

    public NhanVienQL() {
    }

    public NhanVienQL(String maNV, String hoTen, String SDT, String ngaySinh, String gioiTinh, String diaChi, String chucVu) {
        
        this.maNV = maNV;
        this.tenNV = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.chucVu = chucVu;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return tenNV;
    }

    public void setHoTen(String hoTen) {
        this.tenNV = hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    
    
}
