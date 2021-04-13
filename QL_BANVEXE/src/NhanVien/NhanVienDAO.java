/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NhanVien;

import Controller.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class NhanVienDAO {
    
   

    public boolean insert(NhanVienQL tk) throws Exception {
        Connection ketNoi =Connect.layKetNoi();
        String sql = "insert into NhanVien values(?,?,?,?,?) ";

        PreparedStatement ps = ketNoi.prepareStatement(sql);

        ps.setString(1, tk.getMaNV());
        ps.setString(2, tk.getHoTen());      
        ps.setString(3, tk.getNgaySinh());
        ps.setString(4, tk.getDiaChi());
        ps.setString(5, tk.getChucVu());

        return ps.executeUpdate()>0; 
        
    }

    public boolean update(NhanVienQL tk) throws Exception {
        Connection ketNoi =Connect.layKetNoi();
        String sql = "update  NhanVien set TenNV=?,NgaySinh=?,DiaChi=?,ChucVu=?  " + "where MaNV=?";

        PreparedStatement ps = ketNoi.prepareStatement(sql);

        ps.setString(5, tk.getMaNV());
        ps.setString(1, tk.getHoTen());
        ps.setString(2, tk.getNgaySinh());
        ps.setString(3, tk.getDiaChi());
        ps.setString(4, tk.getChucVu());

        return ps.executeUpdate() > 0;

    }
    public int kTMaNVTonTai(String maNV){
        int tonTai=0;
        Connection ketNoi = (Connection) Connect.layKetNoi();
        String sql = "select* from NhanVien where maNV= ? ";

        PreparedStatement ps;
        try {
            ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNV);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tonTai= 1;
            }else{
                tonTai= 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tonTai;
        
    }
    public NhanVienQL findId(String maNV) throws Exception {
        Connection ketNoi = (Connection) Connect.layKetNoi();
        String sql = "select* from NhanVien where maNV= ? ";

        PreparedStatement ps = ketNoi.prepareStatement(sql);

        ps.setString(1, maNV);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            NhanVienQL tk = new NhanVienQL();
            tk.setMaNV(rs.getString("maNV"));
            tk.setHoTen(rs.getString("hoTen"));
            tk.setNgaySinh(rs.getString("ngaySinh"));
            tk.setDiaChi(rs.getString("diaChi"));
            tk.setChucVu(rs.getString("chucVu"));

            return tk;
        }
        return null;
    }

    public boolean delete(String maNV) throws Exception {
        Connection ketNoi = (Connection) Connect.layKetNoi();
        String sql = "delete from NhanVien where maNV= ? ";

        PreparedStatement ps = ketNoi.prepareStatement(sql);

        ps.setString(1, maNV);

        return ps.executeUpdate() > 0;

    }

}
