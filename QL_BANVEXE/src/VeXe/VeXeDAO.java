/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VeXe;

import Controller.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class VeXeDAO {
    public boolean insert(VeXe tk) throws Exception {
        Connection ketNoi =Connect.layKetNoi();
        String sql = "insert into Ve values(?,?,?,?,?,?,?) ";

        PreparedStatement ps = ketNoi.prepareStatement(sql);

        ps.setString(1, tk.getMaVe());
        ps.setString(2, tk.getMaChuyenXe());      
        ps.setString(3, tk.getTaiKhoan());
        ps.setString(4, tk.getMaLoaiVe());
        ps.setString(5, tk.getNoiDi());
        ps.setString(6, tk.getNoiDen());
        ps.setInt(7, tk.getSoChoDat());

        return ps.executeUpdate()>0; 
        
    }
      public boolean update(VeXe tk) throws Exception {
        Connection ketNoi =Connect.layKetNoi();
        String sql = "update Ve set MaChuyenXe=?,TaiKhoan=?,MaLoaiVe=?,NoiDi=?,NoiDen=?,SoChoDat=?  " + "where MaVe=?";

        PreparedStatement ps = ketNoi.prepareStatement(sql);

        ps.setString(7, tk.getMaVe());
        ps.setString(1, tk.getMaChuyenXe());      
        ps.setString(2, tk.getTaiKhoan());
        ps.setString(3, tk.getMaLoaiVe());
        ps.setString(4, tk.getNoiDi());
        ps.setString(5, tk.getNoiDen());
        ps.setInt(6, tk.getSoChoDat());

        return ps.executeUpdate()>0; 
    }
      
     public int kTMaNVTonTai(int maVe){
        int tonTai=0;
        Connection ketNoi = (Connection) Connect.layKetNoi();
        String sql = "select* from Ve where MaVe= ? ";

        PreparedStatement ps;
        try {
            ps = ketNoi.prepareStatement(sql);
            ps.setInt(1, maVe);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tonTai= 1;
            }else{
                tonTai= 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeXeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tonTai;
        
    }
     public VeXe findId(int maVe) throws Exception {
        Connection ketNoi = (Connection) Connect.layKetNoi();
        String sql = "select* from Ve where MaVe= ? ";

        PreparedStatement ps = ketNoi.prepareStatement(sql);

        ps.setInt(1, maVe);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            VeXe tk = new VeXe();
            tk.setMaVe(rs.getString("MaVe"));
            tk.setMaChuyenXe(rs.getString("MaChuyenXe"));
            tk.setTaiKhoan(rs.getString("TaiKhoan"));
            tk.setMaLoaiVe(rs.getString("MaLoaiVe"));
            tk.setNoiDi(rs.getString("NoiDi"));
            tk.setNoiDen(rs.getString("NoiDen"));
            tk.setSoChoDat(rs.getInt("SoChoDat"));

            return tk;
        }
        return null;
    }

    public boolean delete(int maVe) throws Exception {
        Connection ketNoi = (Connection) Connect.layKetNoi();
        String sql = "delete from Ve where MaVe= ? ";

        PreparedStatement ps = ketNoi.prepareStatement(sql);

        ps.setInt(1, maVe);

        return ps.executeUpdate() > 0;

    }

}
