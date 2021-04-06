/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employee;

import java.sql.Connection;

import Employee.*;
import java.sql.*;
/**
 *
 * @author ADMIN
 */
public class EmployeeDao {
    public boolean insert(Employee tk) throws Exception{
          Connection ketNoi= (Connection) Connect.layKetNoi();
        String sql="insert into TaiKhoan values(?,?,?,?,?,?,?,?) ";
        
            PreparedStatement ps =ketNoi.prepareStatement(sql);
            
                ps.setString(1,tk.getMaNV());
                ps.setString(2,tk.getHoTen());
                ps.setString(3,tk.getTenDN());
                ps.setString(4, tk.getMatKhau());
                ps.setString(5,tk.getEmail());
                ps.setString(6, tk.getSoDT());
                ps.setInt(7, tk.getGioiTinh());
                ps.setString(8, tk.getDiaChi());
                
               // return ps.executeUpdate()>0; 
        return false;
                
                           
    }
    
    public boolean update(Employee tk) throws Exception{
          Connection ketNoi= (Connection) Connect.layKetNoi();
        String sql="update  TaiKhoan st hoTen=?,tenDN=?, matKhau=?, email=?,SDT=?,diaChi=?  "+ "wwhere maNV=?";
        
            PreparedStatement ps =ketNoi.prepareStatement(sql);
            
                ps.setString(8,tk.getMaNV());
                ps.setString(1,tk.getHoTen());
                ps.setString(2,tk.getTenDN());
                ps.setString(3, tk.getMatKhau());
                ps.setString(4,tk.getEmail());
                ps.setString(5, tk.getSoDT());
                ps.setInt(6, tk.getGioiTinh());
                ps.setString(7, tk.getDiaChi());
              
                return ps.executeUpdate()>0;
        
    }
    
    
    public Employee findId (String maNV) throws Exception{
          Connection ketNoi= (Connection) Connect.layKetNoi();
            String sql="select* from TaiKhoan where maNV= ? ";
        
            PreparedStatement ps =ketNoi.prepareStatement(sql);
            
             ps.setString(1, maNV);
             ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    Employee tk =new Employee();
                    tk.setMaNV(rs.getString("maNV"));
                    tk.setHoTen(rs.getString("hoTen"));
                    tk.setTenDN(rs.getString("tenDN"));
                    tk.setMatKhau(rs.getString("matKhau"));
                    tk.setEmail(rs.getString("email"));
                    tk.setSoDT(rs.getString("soDT"));
                    tk.setGioiTinh(rs.getInt("gioiTinh"));
                    tk.setDiaChi(rs.getString("diaChi"));
                    
                    return tk;         
        }
        return null;
            
    }
    
       public boolean delete (String maNV) throws Exception{
          Connection ketNoi= (Connection) Connect.layKetNoi();
            String sql="delete from TaiKhoan where maNV= ? ";
        
            PreparedStatement ps =ketNoi.prepareStatement(sql);
            
             ps.setString(1, maNV);
             
               
                    return ps.executeUpdate() >0;
                
        }
      
    
}
