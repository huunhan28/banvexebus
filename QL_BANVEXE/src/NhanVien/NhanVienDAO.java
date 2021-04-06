/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NhanVien;

import NhanVien.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class NhanVienDAO {
    
   

    public boolean insert(NhanVienQL tk) throws Exception {
        Connection ketNoi = (Connection) Connect.layKetNoi();
        String sql = "insert into NhanVien values(?,?,?,?,?,?,?) ";

        PreparedStatement ps = ketNoi.prepareStatement(sql);

        ps.setString(1, tk.getMaNV());
        ps.setString(2, tk.getHoTen());
        ps.setString(3, tk.getSDT());      
        ps.setString(4, tk.getNgaySinh());
        ps.setString(5, tk.getGioiTinh());
        ps.setString(6, tk.getDiaChi());
        ps.setString(7, tk.getChucVu());

        return ps.executeUpdate()>0; 
        
    }

    public boolean update(NhanVienQL tk) throws Exception {
        Connection ketNoi = (Connection) Connect.layKetNoi();
        String sql = "update  NhanVien st hoTen=?,SDT=?,ngaySinh=?,gioiTinh=?,diaChi=?,chucVu=?  " + "wwhere maNV=?";

        PreparedStatement ps = ketNoi.prepareStatement(sql);

        ps.setString(7, tk.getMaNV());
        ps.setString(1, tk.getHoTen());

        ps.setString(2, tk.getSDT());
        ps.setString(3, tk.getGioiTinh());
        ps.setString(4, tk.getNgaySinh());
        ps.setString(5, tk.getDiaChi());
        ps.setString(6, tk.getChucVu());

        return ps.executeUpdate() > 0;

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

            tk.setSDT(rs.getString("soDT"));
            tk.setNgaySinh(rs.getString("ngaySinh"));
            tk.setGioiTinh(rs.getString("gioiTinh"));
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
