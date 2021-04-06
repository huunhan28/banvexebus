/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

/**
 *
 * @author ADMIN
 */
public class TaiKhoan {
    private int maTK;
    private String tenDN,matKhau;
    private String chucVu;

    public TaiKhoan() {
    }

    public TaiKhoan(int maTK, String tenDN, String matKhau, String chucVu) {
        this.maTK = maTK;
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.chucVu = chucVu;
    }

    public int getMaTK() {
        return maTK;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    
}
