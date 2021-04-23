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
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
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
    public void dangNhap(){
        //=========================================================
        if(txtTenDN.getText().trim().equals("")){
            txtTenDN.setBorder(BorderFactory.createLineBorder(Color.red));
            txtTenDN.setHorizontalAlignment(txtTenDN.RIGHT);
            txtTenDN.setForeground(Color.red);
            txtTenDN.setText("!");
        }
        if(jPassword.getText().trim().equals("")){
            jPassword.setBorder(BorderFactory.createLineBorder(Color.red));
            jPassword.setHorizontalAlignment(jPassword.RIGHT);
            jPassword.setForeground(Color.red);
            jPassword.setText("!");
        }
        String taiKhoan=txtTenDN.getText().trim();
        String matKhau=jPassword.getText().trim();
        if(taiKhoan!=null&&matKhau!=null&&taiKhoan!="!"&&matKhau!="!"){
            if(ktUser(taiKhoan,matKhau)==1){
                //da dang nhap duoc
                System.out.println("dang nhap duoc");
                this.dispose();
                new MuaaVe(taiKhoan).setVisible(true);
//                new muave(jTextFieldTaiKhoan.getText()).setVisible(true);
//                this.dispose();
            }
            else if(ktUser(txtTenDN.getText(),jPassword.getText())==2){
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
        jButtonDangNhap = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jlbName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenDN = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btnThoat = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        jLabelDangKy = new javax.swing.JLabel();
        jPassword = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabelHome = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        show_hide_pass = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();

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

        jButtonDangNhap.setText("Đăng nhập");
        jButtonDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDangNhapActionPerformed(evt);
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
                            .addComponent(jDateChooserDKNS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addComponent(jButtonDangKy, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonDangNhap)))))
                .addGap(82, 82, 82))
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
                .addGap(28, 28, 28)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDangKy)
                    .addComponent(jButtonDangNhap))
                .addContainerGap(84, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/20945315min.jpg"))); // NOI18N

        jlbName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlbName.setForeground(new java.awt.Color(54, 33, 89));
        jlbName.setText("Tên Đăng Nhâp: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(54, 33, 89));
        jLabel3.setText("Mật Khẩu: ");

        txtTenDN.setBorder(null);
        txtTenDN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTenDNMouseClicked(evt);
            }
        });
        txtTenDN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenDNKeyReleased(evt);
            }
        });

        btnThoat.setBackground(new java.awt.Color(255, 255, 255));
        btnThoat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThoat.setForeground(new java.awt.Color(54, 33, 89));
        btnThoat.setText("THOÁT");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(255, 255, 255));
        btnLogin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(54, 33, 89));
        btnLogin.setText("ĐĂNG NHẬP");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        btnLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLoginKeyPressed(evt);
            }
        });

        jLabelDangKy.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelDangKy.setForeground(new java.awt.Color(54, 33, 89));
        jLabelDangKy.setText("Chưa có tài khoản, đăng ký ngay.");
        jLabelDangKy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelDangKyMouseClicked(evt);
            }
        });

        jPassword.setBorder(null);
        jPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPasswordMouseClicked(evt);
            }
        });
        jPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPasswordKeyReleased(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(54, 33, 89));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/bus_40px.png"))); // NOI18N

        jLabelHome.setBackground(new java.awt.Color(51, 51, 51));
        jLabelHome.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelHome.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHome.setText("VEXEBUS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelHome))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(jLabelHome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        show_hide_pass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/hide.png"))); // NOI18N
        show_hide_pass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                show_hide_passMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnLogin)
                                .addGap(18, 18, 18)
                                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelDangKy, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPassword)
                                    .addComponent(jSeparator1)
                                    .addComponent(jSeparator3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(show_hide_pass))
                            .addComponent(txtTenDN, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbName)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(20, 20, 20))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jlbName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenDN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(show_hide_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLogin)
                            .addComponent(btnThoat))
                        .addGap(30, 30, 30)
                        .addComponent(jLabelDangKy)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        dangNhap();
        
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

    private void jButtonDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDangNhapActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Login dialog = new Login(new javax.swing.JFrame(), true);
            dialog.setVisible(true);
    }//GEN-LAST:event_jButtonDangNhapActionPerformed

    private void jPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordMouseClicked
        // TODO add your handling code here:
        if(jPassword.getText().equals("!")){
            jPassword.setText("");
            jPassword.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            jPassword.setHorizontalAlignment(jPassword.LEFT);
            jPassword.setForeground(Color.black);
        }
    }//GEN-LAST:event_jPasswordMouseClicked

    private void btnLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLoginKeyPressed
        // TODO add your handling code here:
        dangNhap();
    }//GEN-LAST:event_btnLoginKeyPressed

    private void jPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) { 
            dangNhap();
        }
    }//GEN-LAST:event_jPasswordKeyReleased

    private void txtTenDNKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenDNKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) { 
            dangNhap();
        }
    }//GEN-LAST:event_txtTenDNKeyReleased
    private  boolean flag = true;
    private void show_hide_passMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_show_hide_passMouseClicked
        // TODO add your handling code here:
        if (flag) {
            ImageIcon A = new ImageIcon(getClass().getResource("show.png"));
            show_hide_pass.setIcon(A);
            jPassword.setEchoChar((char) 0);
            flag = false;
        } else {
            ImageIcon A = new ImageIcon(getClass().getResource("hide.png"));
            show_hide_pass.setIcon(A);
            jPassword.setEchoChar((char) 8226);
            flag = true;
        }
    }//GEN-LAST:event_show_hide_passMouseClicked

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
    private javax.swing.JButton jButtonDangNhap;
    private com.toedter.calendar.JDateChooser jDateChooserDKNS;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDangKy;
    private javax.swing.JLabel jLabelHome;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextFieldDKDC;
    private javax.swing.JTextField jTextFieldDKMK;
    private javax.swing.JTextField jTextFieldDKNS;
    private javax.swing.JTextField jTextFieldDKSDT;
    private javax.swing.JTextField jTextFieldDKTK;
    private javax.swing.JTextField jTextFieldDKTen;
    private javax.swing.JLabel jlbName;
    private javax.swing.JLabel show_hide_pass;
    private javax.swing.JTextField txtTenDN;
    // End of variables declaration//GEN-END:variables
}
