/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChuyenXe;
import ChuyenXe.ChuyenXe;
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
public class ChuyenXeDAO {
    public boolean insertCX(ChuyenXe cx) throws SQLException{
        
        String sql="insert into ChuyenXe values(?,?,?,?) ";
        Connection  connect =Connect.layKetNoi();
        PreparedStatement ps=connect.prepareStatement(sql);
        ResultSet rs =ps.executeQuery();
        int soCho = Integer.parseInt(rs.getString("TongSoChoDat"));
        while(rs.next()){
            ps.setString(1, cx.getMaChuyenXe());
            ps.setString(2, cx.getMaTuyen());
            ps.setString(3, cx.getNgay());
            ps.setString(4, cx.getTongSoChoDat());
       
        }
        return ps.executeUpdate()>0; 
    }
    
    public boolean updateCX(ChuyenXe cx ) throws SQLException{
         String sql="update  ChuyenXe set MaChuyenXe,MaTuyen,TongSoChoDat,Ngay ";
         Connection  connect =Connect.layKetNoi();
         PreparedStatement ps=connect.prepareStatement(sql);
           
         ps.setString(1, cx.getMaChuyenXe());
            ps.setString(2, cx.getMaTuyen());
            ps.setString(3, cx.getNgay());
            ps.setString(4, cx.getTongSoChoDat());
       
         
          return ps.executeUpdate()>0; 
    }
    public int kTMaCXTonTai(String maCX){
        int tonTai=0;
        Connection ketNoi = (Connection) Connect.layKetNoi();
        String sql = "select* from NhanVien where maNV= ? ";

        PreparedStatement ps;
        try {
            ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maCX);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tonTai= 1;
            }else{
                tonTai= 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tonTai;
        
    }
      public boolean delete(String maChuyenXe) throws Exception {
        Connection ketNoi = (Connection) Connect.layKetNoi();
        String sql = "delete from ChuyenXe where maChuyenXe= ? ";

        PreparedStatement ps = ketNoi.prepareStatement(sql);

        ps.setString(1, maChuyenXe);

        return ps.executeUpdate() > 0;

    }

}
