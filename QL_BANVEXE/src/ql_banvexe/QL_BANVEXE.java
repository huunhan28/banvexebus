/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql_banvexe;

import Login.Login;

/**
 *
 * @author ADMIN
 */
public class QL_BANVEXE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Login formLogin= new Login(null, true);
        formLogin.setTitle("Đăng Nhập Hệ Thống");
        formLogin.setResizable(false);
        formLogin.setLocationRelativeTo(null);
        formLogin.setVisible(true);
    }
    
    
}
