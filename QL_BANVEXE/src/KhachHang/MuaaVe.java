/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KhachHang;

import Login.Login;
import NhanVien.Connect;
import java.awt.Color;
import static java.lang.Math.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Huu Nhan
 */
public class MuaaVe extends javax.swing.JFrame {

    /**
     * Creates new form MuaaVe
     */
    public MuaaVe(String taiKhoan) {
        initComponents();
        layKhachHang(taiKhoan);
        layVe(taiKhoan);
        layLoaiVe();
        layTuyen();
        
        //jLabelHello.setText("huu");
    }
    void layKhachHang(String taiKhoan){
        
        jLabelTaiKhoan.setText(taiKhoan);
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select TenKhachHang,MatKhau\n" +
            "from KhachHang,TaiKhoan \n" +
            "where TaiKhoan.SDT=KhachHang.SDT and KhachHang.TaiKhoan='"+taiKhoan+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                System.out.println("toi day roi");
                jLabelHello.setText(rs.getString(1));
                              
                jPasswordFieldSua.setText(rs.getString(2));
            }
        }catch(SQLException e){
            System.out.println("loi lay thong tin de in ");
        }
        
    }
    void layTuyen(){
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select MaTuyen from Tuyen");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                jComboBoxMVTuyen.addItem(rs.getString("MaTuyen"));
            }
        }catch(SQLException e){
            System.out.println("loi lay tuyen");
        }
    }
    void layLoaiVe(){
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select TenLoaiVe from LoaiVe");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                jComboBoxMVLoaiVe.addItem(rs.getString("TenLoaiVe"));
            }
        }catch(SQLException e){
            System.out.println("loi lay loai ve");
        }
    }
    //==================tim maloaive tu tenloaive====================
    String layMaLoaiVeTuTenLoaiVe(String tenLoaiVe){
        String maLV="";
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select MaLoaiVe from LoaiVe where TenLoaiVe='"+tenLoaiVe+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                maLV=rs.getString(1);
            }
        }catch(SQLException e){
            System.out.println("loi lay ma loai ve");
        }
        return maLV;
    }
    float layGiaVeTuTenLoaiVe(String tenLoaiVe){
        float gia=0;
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select Gia from LoaiVe where TenLoaiVe='"+tenLoaiVe+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                gia=rs.getFloat(1);
            }
        }catch(SQLException e){
            System.out.println("loi lay gia");
        }
        return gia;
    }
    //==================
    boolean ktTrangThaiDi(String tuyen,String noiDi,String noiDen){
        int di=0,den=0;
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select ThoiGianTuyenDenTram from TuyenTram where Tuyen='"+tuyen+"' and Tram='"+noiDi+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                di=rs.getInt(1);
            }
        }catch(SQLException e){
            System.out.println("loi lay ThoiGianTuyenDenTram");
        }
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select ThoiGianTuyenDenTram from TuyenTram where Tuyen='"+tuyen+"' and Tram='"+noiDen+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                den=rs.getInt(1);
            }
        }catch(SQLException e){
            System.out.println("loi lay ThoiGianTuyenDenTram");
        }
        if(den-di>0){
            return true;
        }else{
            return false;
        }
        
    }
    //=================================
    void muaVe(String maChuyen,int maVe,int soChoDat,String loaiVe,String NoiDi,String NoiDen,String taiKhoan){
        //them ve
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("insert into Ve values(?,?,?,?,?,?,?)");
            ps.setInt(1, maVe);
            ps.setString(2, maChuyen);
            ps.setInt(3, soChoDat);
            ps.setString(4, loaiVe);
            ps.setString(5, NoiDi);
            ps.setString(6, NoiDen);
            ps.setString(7, taiKhoan);
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("loi luu ve"+e.getMessage());
            return;
        }
        
        
        
    }
    int layMaxMaVe(){
        int max=0;
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select MaVe from Ve");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                if(rs.getInt("MaVe")>=max){
                    max=rs.getInt("MaVe");
                }
            }
        }catch(SQLException e){
            System.out.println("loi lay max ve xe");
        }
        return max;
    }
    int layMaxThoiGianCuatuyen(String tuyen){
        int max=0;
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select ThoiGianTuyenDenTram from TuyenTram where Tuyen='"+tuyen+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                if(rs.getInt("ThoiGianTuyenDenTram")>=max){
                    max=rs.getInt("ThoiGianTuyenDenTram");
                }
            }
        }catch(SQLException e){
            System.out.println("loi lay max ve xe");
        }
        return max;
        
    }
    public DefaultTableModel dtm;
    void layVe(String taiKhoan){
        dtm= (DefaultTableModel) jTableTimVe.getModel();
        dtm.setNumRows(0);
        Connection ketNoi=Connect.layKetNoi();
        Vector vt;
        try {
            PreparedStatement ps=ketNoi.prepareStatement("select * from Ve where TaiKhoan='"+taiKhoan+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                vt= new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3));
                vt.add(rs.getString(4));
                vt.add(rs.getString(5));
                vt.add(rs.getString(6));
                vt.add(rs.getString(7));
                
                dtm.addRow(vt);
                
            }
            jTableTimVe.setModel(dtm);
            ps.close();
            rs.close();
            ketNoi.close();
        } catch (SQLException ex) {
            System.out.println("loi lay ve");
        }
    }
    //Lay thong bao mua ve 1
    void thongBaoMuaVe1(){
        
        String xx=(String) jComboBoxMVTuyen.getSelectedItem();
        String yy=(String) jComboBoxMVNoiDi.getSelectedItem();
        String zz=(String) jComboBoxMVNoiDen.getSelectedItem();
        //======================
        boolean trangThai=ktTrangThaiDi(xx, yy, zz);
        int maxThoiGianTuyen=layMaxThoiGianCuatuyen(xx);
        String luot;
        if(trangThai==true){
            luot="Luot di";
        }else{
            luot="Luot ve";
        }
        int thoiGian=0;
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select ThoiGianTuyenDenTram from TuyenTram where Tuyen='"+xx+"' and Tram='"+yy+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                thoiGian=rs.getInt(1);
            }
        }catch(SQLException e){
            System.out.println("loi lay ThoiGianTuyenDenTram");
        }
        
        
        if(luot.equalsIgnoreCase("Luot ve")){
            thoiGian=maxThoiGianTuyen-thoiGian;
        }
        jLabelThongBaoThoiGian.setText("Luu y: Thoi gian tuyen xe "+xx+" den tram "+yy+" trong "+luot+" la : "+thoiGian+" phut.");
        
    }
    //thong bao mua ve 2
    void thongBaoMuaVe2(){
        int tram1=0,tram2=0;
        String aa=(String) jComboBoxMVTuyen.getSelectedItem();
        String xx=(String) jComboBoxMVNoiDi.getSelectedItem();
        String yy=(String) jComboBoxMVNoiDen.getSelectedItem();
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select ThoiGianTuyenDenTram from TuyenTram where Tuyen='"+aa+"' and Tram='"+xx+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                tram1=rs.getInt(1);
            }
        }catch(SQLException e){
            System.out.println("loi lay ThoiGianTuyenDenTram");
        }
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select ThoiGianTuyenDenTram from TuyenTram where Tuyen='"+aa+"' and Tram='"+yy+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                tram2=rs.getInt(1);
            }
        }catch(SQLException e){
            System.out.println("loi lay ThoiGianTuyenDenTram");
        }
        jLabelThongBao2.setText("Luu y: Thoi gian di tu ben tram "+xx+" den tram "+yy+" la : "+abs(tram2-tram1)+" phut.");
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelList = new javax.swing.JPanel();
        jPanelList1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanelList2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanelList3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabelHome = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabelTaiKhoan = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanelMuaVe = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldMVSoGhe = new javax.swing.JTextField();
        jComboBoxMVLoaiVe = new javax.swing.JComboBox<>();
        jComboBoxMVTuyen = new javax.swing.JComboBox<>();
        jComboBoxMVChuyen = new javax.swing.JComboBox<>();
        jComboBoxMVNoiDi = new javax.swing.JComboBox<>();
        jComboBoxMVNoiDen = new javax.swing.JComboBox<>();
        jDateChooserMVNgay = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jLabelThongBaoThoiGian = new javax.swing.JLabel();
        jLabelThongBao2 = new javax.swing.JLabel();
        jPanelTimVe = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldTimVe = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTimVe = new javax.swing.JTable();
        jComboBoxTimKiemVe = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanelTaiKhoan = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPasswordFieldSua = new javax.swing.JPasswordField();
        jButtonDoiMatKhau = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabelHello = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelList.setBackground(new java.awt.Color(54, 33, 89));

        jPanelList1.setBackground(new java.awt.Color(64, 43, 100));
        jPanelList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelList1MouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Đặt vé");

        javax.swing.GroupLayout jPanelList1Layout = new javax.swing.GroupLayout(jPanelList1);
        jPanelList1.setLayout(jPanelList1Layout);
        jPanelList1Layout.setHorizontalGroup(
            jPanelList1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelList1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelList1Layout.setVerticalGroup(
            jPanelList1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        jPanelList2.setBackground(new java.awt.Color(85, 65, 118));
        jPanelList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelList2MouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Tìm vé");

        javax.swing.GroupLayout jPanelList2Layout = new javax.swing.GroupLayout(jPanelList2);
        jPanelList2.setLayout(jPanelList2Layout);
        jPanelList2Layout.setHorizontalGroup(
            jPanelList2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelList2Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelList2Layout.setVerticalGroup(
            jPanelList2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        jPanelList3.setBackground(new java.awt.Color(85, 65, 118));
        jPanelList3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelList3MouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Tài khoản");

        javax.swing.GroupLayout jPanelList3Layout = new javax.swing.GroupLayout(jPanelList3);
        jPanelList3.setLayout(jPanelList3Layout);
        jPanelList3Layout.setHorizontalGroup(
            jPanelList3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelList3Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelList3Layout.setVerticalGroup(
            jPanelList3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        jLabelHome.setBackground(new java.awt.Color(51, 51, 51));
        jLabelHome.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelHome.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHome.setText("VEXEBUS");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Đăng xuất");
        jLabel11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabelTaiKhoan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTaiKhoan.setText("user");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Bạn đã đăng nhập với tư cách,");

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/bus_40px.png"))); // NOI18N

        javax.swing.GroupLayout jPanelListLayout = new javax.swing.GroupLayout(jPanelList);
        jPanelList.setLayout(jPanelListLayout);
        jPanelListLayout.setHorizontalGroup(
            jPanelListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelList1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelList2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelList3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelListLayout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanelListLayout.createSequentialGroup()
                .addGroup(jPanelListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelListLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel17))
                    .addGroup(jPanelListLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(jPanelListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelListLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabelHome)
                .addGap(41, 41, 41))
        );
        jPanelListLayout.setVerticalGroup(
            jPanelListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelList1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelList2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelList3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(155, 155, 155)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(jLabel11)
                .addGap(50, 50, 50))
        );

        jLayeredPane1.setLayout(new java.awt.CardLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Loại vé");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Số ghế");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Chọn tuyến");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Chọn chuyến xe khởi hành");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Nơi đi");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Nơi đến");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Ngày");

        jComboBoxMVTuyen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxMVTuyenItemStateChanged(evt);
            }
        });

        jComboBoxMVChuyen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5h", "7h", "9h", "11h", "13h", "15h", "17h", "19h" }));

        jComboBoxMVNoiDi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxMVNoiDiItemStateChanged(evt);
            }
        });

        jComboBoxMVNoiDen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxMVNoiDenItemStateChanged(evt);
            }
        });

        jButton1.setText("Mua vé");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabelThongBaoThoiGian.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelThongBaoThoiGian.setForeground(new java.awt.Color(0, 102, 0));
        jLabelThongBaoThoiGian.setText("Thong bao 1");

        jLabelThongBao2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelThongBao2.setForeground(new java.awt.Color(0, 102, 0));
        jLabelThongBao2.setText("Thong bao 2");

        javax.swing.GroupLayout jPanelMuaVeLayout = new javax.swing.GroupLayout(jPanelMuaVe);
        jPanelMuaVe.setLayout(jPanelMuaVeLayout);
        jPanelMuaVeLayout.setHorizontalGroup(
            jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMuaVeLayout.createSequentialGroup()
                .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMuaVeLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(19, 19, 19)
                        .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxMVChuyen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxMVNoiDi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxMVNoiDen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooserMVNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxMVLoaiVe, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldMVSoGhe)
                            .addComponent(jComboBoxMVTuyen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelMuaVeLayout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(286, 286, 286))
            .addGroup(jPanelMuaVeLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelThongBao2, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelThongBaoThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMuaVeLayout.setVerticalGroup(
            jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMuaVeLayout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxMVLoaiVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldMVSoGhe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxMVTuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxMVChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBoxMVNoiDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabelThongBaoThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBoxMVNoiDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabelThongBao2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooserMVNgay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jButton1)
                .addGap(39, 39, 39))
        );

        jLayeredPane1.add(jPanelMuaVe, "card2");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(54, 33, 89));
        jLabel2.setText("TÌM MÃ VÉ");

        jTextFieldTimVe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldTimVeKeyReleased(evt);
            }
        });

        jTableTimVe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã vé", "Mã chuyến xe", "Số chỗ đặt", "Mã loại vé", "Nơi đi", "Nơi đến", "Tài khoản"
            }
        ));
        jScrollPane1.setViewportView(jTableTimVe);

        jComboBoxTimKiemVe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã vé", "Nơi đi", "Nơi đến" }));

        jLabel19.setText("Nhập dữ liệu cần tìm kiếm:");

        jLabel20.setText("Tìm kiếm theo:");

        javax.swing.GroupLayout jPanelTimVeLayout = new javax.swing.GroupLayout(jPanelTimVe);
        jPanelTimVe.setLayout(jPanelTimVeLayout);
        jPanelTimVeLayout.setHorizontalGroup(
            jPanelTimVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTimVeLayout.createSequentialGroup()
                .addGap(351, 351, 351)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTimVeLayout.createSequentialGroup()
                .addGap(0, 145, Short.MAX_VALUE)
                .addGroup(jPanelTimVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelTimVeLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldTimVe, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelTimVeLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxTimKiemVe, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );
        jPanelTimVeLayout.setVerticalGroup(
            jPanelTimVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTimVeLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(102, 102, 102)
                .addGroup(jPanelTimVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTimKiemVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(36, 36, 36)
                .addGroup(jPanelTimVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextFieldTimVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        jLayeredPane1.add(jPanelTimVe, "card3");

        jLabel3.setText("Mật khẩu");

        jPasswordFieldSua.setText("jPasswordField1");

        jButtonDoiMatKhau.setText("Đổi mật khẩu");
        jButtonDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDoiMatKhauActionPerformed(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(0, 0, 0));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Actions-help-about-icon-16.png"))); // NOI18N
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelTaiKhoanLayout = new javax.swing.GroupLayout(jPanelTaiKhoan);
        jPanelTaiKhoan.setLayout(jPanelTaiKhoanLayout);
        jPanelTaiKhoanLayout.setHorizontalGroup(
            jPanelTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTaiKhoanLayout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(jPasswordFieldSua, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel13)
                .addGap(67, 67, 67)
                .addComponent(jButtonDoiMatKhau)
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanelTaiKhoanLayout.setVerticalGroup(
            jPanelTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTaiKhoanLayout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(jPanelTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordFieldSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jButtonDoiMatKhau)
                    .addComponent(jLabel13))
                .addContainerGap(400, Short.MAX_VALUE))
        );

        jLayeredPane1.add(jPanelTaiKhoan, "card4");

        jLabelHello.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelHello.setForeground(new java.awt.Color(54, 33, 89));
        jLabelHello.setText("ten khach hang");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(54, 33, 89));
        jLabel16.setText("Xin chào,");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanelList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelHello, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 847, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelHello, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(30, 30, 30)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelList1MouseClicked
        // TODO add your handling code here:
        switchPanels(jPanelMuaVe);
        setColor(jPanelList1);
        resetColor(jPanelList2);
        resetColor(jPanelList3);
    }//GEN-LAST:event_jPanelList1MouseClicked

    private void jPanelList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelList2MouseClicked
        // TODO add your handling code here:
        switchPanels(jPanelTimVe);
        resetColor(jPanelList1);
        setColor(jPanelList2);
        resetColor(jPanelList3);
    }//GEN-LAST:event_jPanelList2MouseClicked

    private void jPanelList3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelList3MouseClicked
        // TODO add your handling code here:
        switchPanels(jPanelTaiKhoan);
        resetColor(jPanelList1);
        resetColor(jPanelList2);
        setColor(jPanelList3);
    }//GEN-LAST:event_jPanelList3MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String soGhe1= jTextFieldMVSoGhe.getText().trim();
        if(soGhe1.equals("")||jDateChooserMVNgay.getDateFormatString().equals("")){
            JOptionPane.showMessageDialog(this, "So ghe hoac ngay khong duoc trong");
            return;
        }
        
        int ret= JOptionPane.showConfirmDialog(this, "Ban chac chan muon mua?","Xac nhan",0);
                
        if(ret == JOptionPane.CANCEL_OPTION){
            return;
        }else if(ret == JOptionPane.OK_OPTION){

            int tonTaiChuyenXe=0;
            
            int soGhe=Integer.parseInt(soGhe1);
            String taiKhoan=jLabelTaiKhoan.getText();
            String tuyen=(String) jComboBoxMVTuyen.getSelectedItem() ;
            String chuyen=(String) jComboBoxMVChuyen.getSelectedItem();
            String noiDi=(String) jComboBoxMVNoiDi.getSelectedItem();
            String noiDen=(String) jComboBoxMVNoiDen.getSelectedItem();
            //kiem tra trang thai di
            boolean trangThaiDi=ktTrangThaiDi(tuyen,noiDi,noiDen);
            
            String tenLoaiVe1=(String) jComboBoxMVLoaiVe.getSelectedItem();
            System.out.println(tenLoaiVe1+"co hay khong co");
            String maLoaiVe=layMaLoaiVeTuTenLoaiVe(tenLoaiVe1);
            System.out.println(maLoaiVe+"khong co");
            //=========================

            float gia=layGiaVeTuTenLoaiVe(tenLoaiVe1);

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String date=sdf.format(jDateChooserMVNgay.getDate());
            System.out.println(date);
            //====================
            // lay ma chuyen
            String year=date.substring(2, 4);
            String month=date.substring(5, 7);
            String day=date.substring(8, 10);
            System.out.println(year+"-"+month+"-"+day);
            
            String trangThai="";
            if(trangThaiDi==true){
                trangThai="1";
            }else{
                trangThai="0";
            }
            
            String maChuyen=day+month+year+"-"+chuyen+"-"+tuyen+"-"+trangThai;
            System.out.println(maChuyen);
            //lay ma ve
            int maVe=layMaxMaVe()+1;
            //kiem tra chuyen

            int tongSoChoDat=soGhe;
            Connection ketNoi=Connect.layKetNoi();
            try{
                PreparedStatement ps= ketNoi.prepareStatement("select MaChuyenXe,TongSoChoDat from ChuyenXe");
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    if(rs.getString("MaChuyenXe").equalsIgnoreCase(maChuyen)){
                        tongSoChoDat=rs.getInt("TongSoChoDat")+soGhe;
                        tonTaiChuyenXe=1;
                    }
                }
            }catch(SQLException e){
                System.out.println("loi lay tuyen");
            }
            
            java.sql.Date ngayCuaVe = null;
            try {
                String ngay = new SimpleDateFormat("yyyy-MM-dd").format(jDateChooserMVNgay.getDate());
                java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
                
                ngayCuaVe = new java.sql.Date(tmp.getTime());
            }
            catch (Exception e) {
                System.out.println("loi lay ngay cua ve");
                e.printStackTrace();
            }
            if(tonTaiChuyenXe==0){// chua ton tai chuyen xe nay => tao chuyen xe
                try{
                    PreparedStatement ps= ketNoi.prepareStatement("insert into ChuyenXe values(?,?,?,?,?)");
                    ps.setString(1, maChuyen);
                    ps.setString(2, tuyen);
                    ps.setInt(3, tongSoChoDat);
                    ps.setDate(4, new Date(ngayCuaVe.getTime()));
                    ps.setBoolean(5, trangThaiDi);
                    ps.executeUpdate();
                }catch(SQLException e){
                    System.out.println("loi luu chuyen xe");
                }
            }
            //lay tong so cho dat
            

            muaVe(maChuyen,maVe,soGhe,maLoaiVe,noiDi,noiDen,taiKhoan);
            JOptionPane.showMessageDialog(this, "Mua thanh cong! Ma ve cua ban la:"+maVe+".");

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBoxMVTuyenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxMVTuyenItemStateChanged
        // TODO add your handling code here:
        jComboBoxMVNoiDi.removeAllItems();
        jComboBoxMVNoiDen.removeAllItems();
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select Tram from TuyenTram where Tuyen='"+jComboBoxMVTuyen.getSelectedItem()+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                jComboBoxMVNoiDi.addItem(rs.getString("Tram"));
                jComboBoxMVNoiDen.addItem(rs.getString("Tram"));
            }
        }catch(SQLException e){
            System.out.println("loi lay tram");
        }
        thongBaoMuaVe1();
        thongBaoMuaVe2();
    }//GEN-LAST:event_jComboBoxMVTuyenItemStateChanged

    private void jComboBoxMVNoiDiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxMVNoiDiItemStateChanged
        // TODO add your handling code here:
        
        thongBaoMuaVe1();
        thongBaoMuaVe2();
    }//GEN-LAST:event_jComboBoxMVNoiDiItemStateChanged

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
//        if(jPasswordFieldSua.isShowing()){
//            jPasswordFieldSua.show(false);
//        }else{
//            jPasswordFieldSua.show(true);
//        }
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jButtonDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDoiMatKhauActionPerformed
        // TODO add your handling code here:
        String matKhau=jPasswordFieldSua.getText().trim();
        if(matKhau==""){
            JOptionPane.showMessageDialog(this, "Thong tin rong");
            return;
        }
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("update TaiKhoan set MatKhau='"+matKhau+"' where TaiKhoan='"+jLabelTaiKhoan.getText()+"'");
            
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("loi luu thay doi thong tin TK");
        }
    }//GEN-LAST:event_jButtonDoiMatKhauActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        int ret= JOptionPane.showConfirmDialog(this, "Ban chac chan muon dang xuat?","Xac nhan",0);
                
        if(ret == JOptionPane.CANCEL_OPTION){
            return;
        }else if(ret == JOptionPane.OK_OPTION){
            this.dispose();
            new Login(null, true);
        }
        
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jComboBoxMVNoiDenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxMVNoiDenItemStateChanged
        // TODO add your handling code here:
        thongBaoMuaVe1();
        thongBaoMuaVe2();
    }//GEN-LAST:event_jComboBoxMVNoiDenItemStateChanged

    private void jTextFieldTimVeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTimVeKeyReleased
        // TODO add your handling code here:
        String theLoai1=(String) jComboBoxTimKiemVe.getSelectedItem();
        String theLoai="";
        if(theLoai1.equals("Mã vé")){
            theLoai="MaVe";
        }else if(theLoai1.equals("Nơi đi")){
            theLoai="NoiDi";
        }else{
            theLoai="NoiDen";
        }
        dtm.setNumRows(0);
        jTableTimVe.setModel(dtm);
        Vector vt;
        Connection ketNoi=Connect.layKetNoi();
        PreparedStatement ps;
        try {
            ps=ketNoi.prepareStatement("select * from Ve where TaiKhoan='"+jLabelTaiKhoan.getText()+"' and "+theLoai+" LIKE'%"+jTextFieldTimVe.getText()+"%'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                vt= new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3));
                vt.add(rs.getString(4));
                vt.add(rs.getString(5));
                vt.add(rs.getString(6));
                vt.add(rs.getString(7));
                dtm.addRow(vt);
                
            }
            jTableTimVe.setModel(dtm);
        } catch (SQLException ex) {
            System.out.println("loi tim kiem");
        }
    }//GEN-LAST:event_jTextFieldTimVeKeyReleased
    public void switchPanels(JPanel panel){
        jLayeredPane1.removeAll();
        jLayeredPane1.add(panel);
        jLayeredPane1.repaint();
        jLayeredPane1.revalidate();
    }
    void setColor(JPanel panel){
        panel.setBackground(new Color(85,65,118));
    }
    void resetColor(JPanel panel){
        panel.setBackground(new Color(64,43,100));
    }
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
            java.util.logging.Logger.getLogger(MuaaVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MuaaVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MuaaVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MuaaVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MuaaVe("huunhan").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonDoiMatKhau;
    private javax.swing.JComboBox<String> jComboBoxMVChuyen;
    private javax.swing.JComboBox<String> jComboBoxMVLoaiVe;
    private javax.swing.JComboBox<String> jComboBoxMVNoiDen;
    private javax.swing.JComboBox<String> jComboBoxMVNoiDi;
    private javax.swing.JComboBox<String> jComboBoxMVTuyen;
    private javax.swing.JComboBox<String> jComboBoxTimKiemVe;
    private com.toedter.calendar.JDateChooser jDateChooserMVNgay;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelHello;
    private javax.swing.JLabel jLabelHome;
    private javax.swing.JLabel jLabelTaiKhoan;
    private javax.swing.JLabel jLabelThongBao2;
    private javax.swing.JLabel jLabelThongBaoThoiGian;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanelList;
    private javax.swing.JPanel jPanelList1;
    private javax.swing.JPanel jPanelList2;
    private javax.swing.JPanel jPanelList3;
    private javax.swing.JPanel jPanelMuaVe;
    private javax.swing.JPanel jPanelTaiKhoan;
    private javax.swing.JPanel jPanelTimVe;
    private javax.swing.JPasswordField jPasswordFieldSua;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableTimVe;
    private javax.swing.JTextField jTextFieldMVSoGhe;
    private javax.swing.JTextField jTextFieldTimVe;
    // End of variables declaration//GEN-END:variables
}
