/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;
import KhachHang.MuaaVe;
import MenuMain.MainMenu;
import java.sql.*;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import Controller.Connect;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JTextField;
/**
 *
 * @author ADMIN
 */
public class Login extends javax.swing.JDialog {

    /**
     * Creates new form Login
     */
    public Login(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
    }
    public int ktUser(String email,String pass){
        int ok=0;
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select * from TaiKhoan");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
               
               if(rs.getString(1).equalsIgnoreCase(email)&&rs.getString(2).equalsIgnoreCase(pass)) {
                   if(rs.getString(3).equals("KH")){
                       ok = 1;
                       break;
                   }else if(rs.getString(3).equals("AD")){
                       ok = 2;
                       break;
                   }
                   
               }else{
                   ok = 0;
               }
            }
            
        }catch(SQLException e){
            System.out.println("loi lay tuyen");
        }
        return ok;
    }
    public int kiemTraTaiKhoan(String tk){
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select TaiKhoan from TaiKhoan");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                if(rs.getString("TaiKhoan").equalsIgnoreCase(tk)){
                    return 1;
                }
            }
        }catch(SQLException e){
            System.out.println("loi lay Tai Khoan");
        }
        return 0;
    }
    public int kiemTraSDT(String sdt){
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select SDT from KhachHang");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                if(rs.getString("SDT").equalsIgnoreCase(sdt)){
                    return 1;
                }
            }
        }catch(SQLException e){
            System.out.println("loi lay sdt");
        }
        return 0;
    }
    public void luuTaiKhoan(String tk,String ten,String sdt,String matKhau,java.util.Date ngaysinh,String diachi){
        Connection ketNoi=Connect.layKetNoi();
        
            try{
                PreparedStatement ps= ketNoi.prepareStatement("insert into KhachHang values(?,?,?,?,?)");
                ps.setString(1, sdt);
                ps.setString(2, ten);
                ps.setDate(3, new Date(ngaysinh.getTime()));
                ps.setString(4, diachi);
                ps.setString(5, tk);
                
                
                ps.executeUpdate();
            }catch(SQLException e){
                System.out.println("loi luu khach hang");
            }
            
            try{
                PreparedStatement ps= ketNoi.prepareStatement("insert into TaiKhoan values(?,?,?,?)");
                
                ps.setString(1, tk);
                ps.setString(2, matKhau);
                ps.setString(3, "KH");
                ps.setString(4, sdt);
                ps.executeUpdate();
            }catch(SQLException e){
                System.out.println("loi luu tai khoan");
            }
        
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jTextFieldDKTK = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldDKNS = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldDKTen = new javax.swing.JTextField();
        jTextFieldDKDC = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldDKSDT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldDKMK = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButtonDangKy = new javax.swing.JButton();
        jDateChooserDKNS = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jlbName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnLogin = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        txtTenDN = new javax.swing.JTextField();
        txtMK = new javax.swing.JTextField();
        jLabelDangKy = new javax.swing.JLabel();

        jLabel8.setText("Tài khoản");

        jLabel9.setText("Ngày sinh");

        jLabel5.setText("Tên ");

        jLabel10.setText("Địa chỉ");

        jLabel6.setText("SDT");

        jLabel7.setText("Mật khẩu");

        jButtonDangKy.setText("Đăng ký");
        jButtonDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDangKyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldDKSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldDKTen, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(jTextFieldDKTK, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(62, 62, 62)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldDKDC)
                            .addComponent(jTextFieldDKNS)
                            .addComponent(jTextFieldDKMK)
                            .addComponent(jDateChooserDKNS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(82, 82, 82))
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(jButtonDangKy, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDKTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDKTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDKSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDKMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jDateChooserDKNS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDKDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(30, 30, 30)
                .addComponent(jTextFieldDKNS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(jButtonDangKy)
                .addGap(24, 24, 24))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/bus_80px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("ĐĂNG NHẬP TÀI KHOẢN");

        jlbName.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlbName.setText("Tên Đăng Nhâp: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Mật Khẩu: ");

        btnLogin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(0, 0, 204));
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Login-icon-16.png"))); // NOI18N
        btnLogin.setText("ĐĂNG NHẬP");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnThoat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThoat.setForeground(new java.awt.Color(255, 0, 0));
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Button-Close-icon-16.png"))); // NOI18N
        btnThoat.setText("THOÁT");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        txtTenDN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTenDNMouseClicked(evt);
            }
        });

        txtMK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMKMouseClicked(evt);
            }
        });

        jLabelDangKy.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelDangKy.setForeground(new java.awt.Color(0, 0, 255));
        jLabelDangKy.setText("Chưa có tài khoản, đăng ký ngay.");
        jLabelDangKy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelDangKyMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMK, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTenDN, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jlbName, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelDangKy, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnLogin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1)
                        .addGap(33, 33, 33)
                        .addComponent(jlbName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTenDN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnThoat))
                .addGap(27, 27, 27)
                .addComponent(jLabelDangKy)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        //=========================================================
        if(txtTenDN.getText().trim().equals("")){
            txtTenDN.setBorder(BorderFactory.createLineBorder(Color.red));
            txtTenDN.setHorizontalAlignment(JTextField.RIGHT);
            txtTenDN.setForeground(Color.red);
            txtTenDN.setText("!");
        }
        if(txtMK.getText().trim().equals("")){
            txtMK.setBorder(BorderFactory.createLineBorder(Color.red));
            txtMK.setHorizontalAlignment(txtMK.RIGHT);
            txtMK.setForeground(Color.red);
            txtMK.setText("!");
        }
        String taiKhoan=txtTenDN.getText().trim();
        String matKhau=txtMK.getText().trim();
        if(taiKhoan!=null&&matKhau!=null&&taiKhoan!="!"&&matKhau!="!"){
            if(ktUser(taiKhoan,matKhau)==1){
                //da dang nhap duoc
                System.out.println("dang nhap duoc");
                this.dispose();
                new MuaaVe(taiKhoan).setVisible(true);
//                new muave(jTextFieldTaiKhoan.getText()).setVisible(true);
//                this.dispose();
            }
            else if(ktUser(txtTenDN.getText(),txtMK.getText())==2){
                //dang nhap tu cach admin
                this.dispose();
                new MainMenu(taiKhoan).setVisible(true);
//                new quanli().setVisible(true);
//                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this, "Sai thong tin dang nhap");
                System.out.println("sai thong tin dang nhap");
            }
        }
        
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtTenDNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenDNMouseClicked
        // TODO add your handling code here:
        if(txtTenDN.getText().equals("!")){
            txtTenDN.setText("");
            txtTenDN.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            txtTenDN.setHorizontalAlignment(JTextField.LEFT);
            txtTenDN.setForeground(Color.black);
        
        }
    }//GEN-LAST:event_txtTenDNMouseClicked

    private void txtMKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMKMouseClicked
        // TODO add your handling code here:
        if(txtMK.getText().equals("!")){
            txtMK.setText("");
            txtMK.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            txtMK.setHorizontalAlignment(JTextField.LEFT);
            txtMK.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtMKMouseClicked

    private void jButtonDangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDangKyActionPerformed
        // TODO add your handling code here:
        String sdt=jTextFieldDKSDT.getText().trim();
        String ten=jTextFieldDKTen.getText().trim();
        String tk=jTextFieldDKTK.getText().trim();
        String matKhau=jTextFieldDKMK.getText().trim();
        //if()
        //================================
        java.sql.Date ngaysinh = null;
        try {
            String ngay = new SimpleDateFormat("yyyy-MM-dd").format(jDateChooserDKNS.getDate());
            java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
            ngaysinh = new java.sql.Date(tmp.getTime());
        }
        catch (Exception e) {
            System.out.println("loi lay ngay sinh");
            e.printStackTrace();
        }
        //==================
        System.out.println(ngaysinh);

        String diachi=jTextFieldDKDC.getText().trim();

        if(kiemTraTaiKhoan(tk)==0&&kiemTraSDT(sdt)==0&&tk!=null&&ten!=null&&sdt!=null&&matKhau!=null&&ngaysinh!=null&&diachi!=null){
            luuTaiKhoan(tk, ten, sdt, matKhau,ngaysinh,diachi);
            System.out.println("Dang ki tai khoan thanh cong. Moi dang nhap.");
            JOptionPane.showMessageDialog(this, "Dang ki tai khoan thanh cong. Moi dang nhap.");
            jDialog1.dispose();
            this.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Tai khoan da ton tai hoac thieu thong tin");
        }
    }//GEN-LAST:event_jButtonDangKyActionPerformed

    private void jLabelDangKyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelDangKyMouseClicked
        // TODO add your handling code here:
        this.dispose();
        jDialog1.setSize(400, 400);
        jDialog1.setLocation(400, 300);
        jDialog1.setVisible(true);
    }//GEN-LAST:event_jLabelDangKyMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login dialog = new Login(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton jButtonDangKy;
    private com.toedter.calendar.JDateChooser jDateChooserDKNS;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDangKy;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldDKDC;
    private javax.swing.JTextField jTextFieldDKMK;
    private javax.swing.JTextField jTextFieldDKNS;
    private javax.swing.JTextField jTextFieldDKSDT;
    private javax.swing.JTextField jTextFieldDKTK;
    private javax.swing.JTextField jTextFieldDKTen;
    private javax.swing.JLabel jlbName;
    private javax.swing.JTextField txtMK;
    private javax.swing.JTextField txtTenDN;
    // End of variables declaration//GEN-END:variables
}
