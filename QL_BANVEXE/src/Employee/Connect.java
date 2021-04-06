/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employee;




import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author ADMIN
 */
public class Connect {
  

  public static Connection layKetNoi(){
        Connection ketNoi = null;
        String uRL = "jdbc:sqlserver://;databaseName=BanVeXeBus";
        String userName = "sa";
        String pass = "123";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            ketNoi = DriverManager.getConnection(uRL,userName,pass);
            System.out.println("Ket noi thanh cong!!!");
        }
        catch(Exception e){
            System.out.println("Ket noi that bai!!!");
        }
        return ketNoi;
    }
}
   
