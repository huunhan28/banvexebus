/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KhachHang;

import Login.Login;
import Controller.Connect;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import static java.lang.Math.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
        layTram();
        clock();
        
        switchPanels(jPanelMuaVe);
        setColor(jPanelList1);
        resetColor(jPanelList2);
        resetColor(jPanelList3);
        resetColor(jPanelList4);
        
        switchPanels2(jPanelTraCuuTuyen);
        setColor(jPanelList5);
        resetColor(jPanelList6);
        
        //jLabelHello.setText("huu");
        
        jTableKetQuaTimDuong.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTableKetQuaTimDuong.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTableKetQuaTimDuong.getColumnModel().getColumn(1).setPreferredWidth(350);
        
        JTextFieldDateEditor editor = (JTextFieldDateEditor) jDateChooserMVNgay.getDateEditor();
        editor.setEditable(false);
        java.util.Date date=new java.util.Date(); 
        jDateChooserMVNgay.setMinSelectableDate(date);
    }
    public void clock() {
        Thread clock = new Thread() {
            public void run() {
                try {
                    while (true) {
                        Calendar cal = new GregorianCalendar();
                        int second = cal.get(Calendar.SECOND);
                        int minute = cal.get(Calendar.MINUTE);
                        int hour = cal.get(Calendar.HOUR_OF_DAY);
                        String thu;
                        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
                        if (dayOfWeek == 1) {
                            thu = "Chủ nhật";
                        } else {
                            thu = "Thứ " + Integer.toString(dayOfWeek);
                        }
                        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
                        int month = cal.get(Calendar.MONTH)+1;
                        int year = cal.get(Calendar.YEAR);

                        jLabelThoiGian.setText(dayOfMonth + "/" + month + "/" + year + " " + hour + ":" + minute + ":" + second);
                        //jTextField_NgayGioNhapKho_Them.setText(dayOfMonth + "-" + month + "-" + year + " " + hour + ":" + minute + ":" + second);
                        sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        clock.start();
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
                              
                jTextFieldPassSua.setText(rs.getString(2));
            }
        }catch(SQLException e){
            System.out.println("loi lay thong tin de in ");
        }
        
    }
    void layTuyen(){
        dtm2= (DefaultTableModel) jTableTraCuuTuyen.getModel();
        dtm2.setNumRows(0);
        Connection ketNoi=Connect.layKetNoi();
        Vector vt;
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select MaTuyen from Tuyen");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                //jComboBoxMVTuyen.addItem(rs.getString("MaTuyen"));
                vt= new Vector();
                vt.add(rs.getString(1));
                dtm2.addRow(vt);
            }
            jTableTraCuuTuyen.setModel(dtm2);
            ps.close();
            rs.close();
            ketNoi.close();
        }catch(SQLException e){
            System.out.println("loi lay tuyen");
        }
    }
    void layTram(){
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select MaTram from Tram");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                jComboBoxMVNoiDi.addItem(rs.getString("MaTram"));
                jComboBoxMVNoiDen.addItem(rs.getString("MaTram"));
                //---------------------------------
                jComboBoxDiTu.addItem(rs.getString("MaTram"));
                jComboBoxDen.addItem(rs.getString("MaTram"));
            }
        }catch(SQLException e){
            System.out.println("loi lay tram");
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
    public DefaultTableModel dtm,dtm2,dtm3;
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
    //=============Kiem tra co phai la so
    public static boolean isNumeric(String str) { 
        try {  
          Integer.parseInt(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }
    public int layKhoangCachTuTramToiTramTrongTuyen(String maTuyen,String maTramDi,String maTramDen){
        int tram1=0,tram2=0;
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select ThoiGianTuyenDenTram from TuyenTram where Tuyen='"+maTuyen+"' and Tram='"+maTramDi+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                tram1=rs.getInt(1);
            }
        }catch(SQLException e){
            System.out.println("loi lay ThoiGianTuyenDenTram");
        }
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select ThoiGianTuyenDenTram from TuyenTram where Tuyen='"+maTuyen+"' and Tram='"+maTramDen+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                tram2=rs.getInt(1);
            }
        }catch(SQLException e){
            System.out.println("loi lay ThoiGianTuyenDenTram");
        }
        return abs(tram2-tram1);
    }
    //--------------Tim tram chung--------------
    public String timTramChung(String tuyen1,String tuyen2){
        String tramChung="";
        int i=0,j=0;
        String tram1[]=new String[100];
        String tram2[]=new String[100];
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select Tram from TuyenTram where Tuyen='"+tuyen1+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                tram1[i]=rs.getString("Tram");
                i++;
            }
        }catch(SQLException e){
            System.out.println("loi lay tram");
        }
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select Tram from TuyenTram where Tuyen='"+tuyen2+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                tram2[j]=rs.getString("Tram");
                j++;
            }
        }catch(SQLException e){
            System.out.println("loi lay tram");
        }
        for (int k = 0; k < tram1.length; k++) {
            for (int l = 0; l < tram2.length; l++) {
                if((tram2[l]) == null ? tram1[k] == null : (tram2[l]).equals(tram1[k])){
                    return tram1[k];
                }
            }
        }
        
        return tramChung;
    }
    public boolean kiemTraChungTuyen(String tuyen,String tram1,String tram2 ){
        String tuyenNoiDi[]=new String[100];
        String tuyenNoiDen[]=new String[100];
        String tuyenChung[]=new String[100];
        boolean flag=false;
        int i=0,j=0,k=0;
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select Tuyen from TuyenTram where Tram='"+tram1+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                tuyenNoiDi[i]=rs.getString(1);
                i++;
            }
        }catch(SQLException e){
            System.out.println("loi lay tuyen tu tram di tram den de mua ve");
        }
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select Tuyen from TuyenTram where Tram='"+tram2+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                tuyenNoiDen[j]=rs.getString(1);
                j++;
            }
        }catch(SQLException e){
            System.out.println("loi lay tuyen tu tram di tram den de mua ve");
        }
        System.out.println(i +"  "+j);
        for (int n = 0; n < i; n++) {
            for (int m = 0; m < j; m++) {
                if (tuyenNoiDi[n].equalsIgnoreCase(tuyenNoiDen[m])) {
                    tuyenChung[k]=tuyenNoiDi[n];
                    k++;
                }
            }
        }
        for (int l = 0; l < k; l++) {
            if(tuyenChung[l].equals(tuyen)){
                return true;
            }
        }
        return flag;
        
    }
    //----------thoiGianTuTramToiTramTrongTuyen
    int thoiGianTuTramToiTramTrongTuyen(String tuyen,String tram1,String tram2){
        int tramDi=0,tramDen=0;
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select ThoiGianTuyenDenTram from TuyenTram where Tuyen='"+tuyen+"' and Tram='"+tram1+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                tramDi=rs.getInt(1);
            }
        }catch(SQLException e){
            System.out.println("loi lay ThoiGianTuyenDenTram");
        }
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select ThoiGianTuyenDenTram from TuyenTram where Tuyen='"+tuyen+"' and Tram='"+tram2+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                tramDen=rs.getInt(1);
            }
        }catch(SQLException e){
            System.out.println("loi lay ThoiGianTuyenDenTram");
        }
        return abs(tramDen-tramDi);
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
        jPanelList3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanelList2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabelHome = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jlbDangXuat = new javax.swing.JLabel();
        jLabelTaiKhoan = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabelThoiGian = new javax.swing.JLabel();
        jPanelList4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
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
        jButtonDoiMatKhau = new javax.swing.JButton();
        jTextFieldPassSua = new javax.swing.JTextField();
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
        jLabelKTSoGhe = new javax.swing.JLabel();
        jLabelThongBaoTuyen = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanelTraCuu = new javax.swing.JPanel();
        jPanelList5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanelList6 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jPanelTraCuuTuyen = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableTraCuuTuyen = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableKetQuaTuyen = new javax.swing.JTable();
        jPanelTimDuong = new javax.swing.JPanel();
        jComboBoxDiTu = new javax.swing.JComboBox<>();
        jComboBoxDen = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableKetQuaTimDuong = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jComboBoxSoChuyen = new javax.swing.JComboBox<>();
        btnTimDuong = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabelHello = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

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

        jPanelList3.setBackground(new java.awt.Color(85, 65, 118));
        jPanelList3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelList3MouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Tài khoản");

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

        javax.swing.GroupLayout jPanelList3Layout = new javax.swing.GroupLayout(jPanelList3);
        jPanelList3.setLayout(jPanelList3Layout);
        jPanelList3Layout.setHorizontalGroup(
            jPanelList3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelList3Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanelList2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelList3Layout.setVerticalGroup(
            jPanelList3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelList3Layout.createSequentialGroup()
                .addComponent(jPanelList2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
        );

        jLabelHome.setBackground(new java.awt.Color(51, 51, 51));
        jLabelHome.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelHome.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHome.setText("VEXEBUS");

        jlbDangXuat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlbDangXuat.setForeground(new java.awt.Color(255, 255, 255));
        jlbDangXuat.setText("Đăng xuất");
        jlbDangXuat.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jlbDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbDangXuatMouseClicked(evt);
            }
        });

        jLabelTaiKhoan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTaiKhoan.setText("user");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Bạn đã đăng nhập với tư cách,");

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/bus_40px.png"))); // NOI18N

        jLabelThoiGian.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelThoiGian.setForeground(new java.awt.Color(255, 255, 255));

        jPanelList4.setBackground(new java.awt.Color(85, 65, 118));
        jPanelList4.setPreferredSize(new java.awt.Dimension(179, 54));
        jPanelList4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelList4MouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Tra cứu");

        javax.swing.GroupLayout jPanelList4Layout = new javax.swing.GroupLayout(jPanelList4);
        jPanelList4.setLayout(jPanelList4Layout);
        jPanelList4Layout.setHorizontalGroup(
            jPanelList4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelList4Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelList4Layout.setVerticalGroup(
            jPanelList4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelListLayout = new javax.swing.GroupLayout(jPanelList);
        jPanelList.setLayout(jPanelListLayout);
        jPanelListLayout.setHorizontalGroup(
            jPanelListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelList1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelList3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelListLayout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanelListLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabelHome)
                .addGap(41, 41, 41))
            .addGroup(jPanelListLayout.createSequentialGroup()
                .addGroup(jPanelListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelListLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jlbDangXuat))
                    .addGroup(jPanelListLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanelListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jLabelTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanelList4, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
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
                .addComponent(jPanelList4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelList3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabelThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jlbDangXuat)
                .addGap(50, 50, 50))
        );

        jLayeredPane1.setLayout(new java.awt.CardLayout());

        jPanelTimVe.setBackground(new java.awt.Color(255, 255, 255));

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTimVeLayout.createSequentialGroup()
                .addGap(0, 158, Short.MAX_VALUE)
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
            .addGroup(jPanelTimVeLayout.createSequentialGroup()
                .addGap(351, 351, 351)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jPanelTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Mật khẩu");

        jButtonDoiMatKhau.setText("Đổi mật khẩu");
        jButtonDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDoiMatKhauActionPerformed(evt);
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
                .addComponent(jTextFieldPassSua, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118)
                .addComponent(jButtonDoiMatKhau)
                .addContainerGap(132, Short.MAX_VALUE))
        );
        jPanelTaiKhoanLayout.setVerticalGroup(
            jPanelTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTaiKhoanLayout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(jPanelTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButtonDoiMatKhau)
                    .addComponent(jTextFieldPassSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(400, Short.MAX_VALUE))
        );

        jLayeredPane1.add(jPanelTaiKhoan, "card4");

        jPanelMuaVe.setBackground(new java.awt.Color(255, 255, 255));

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

        jTextFieldMVSoGhe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldMVSoGheKeyReleased(evt);
            }
        });

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

        jLabelThongBaoThoiGian.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelThongBaoThoiGian.setForeground(new java.awt.Color(0, 102, 0));
        jLabelThongBaoThoiGian.setText("Thong bao 1");

        jLabelThongBao2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelThongBao2.setForeground(new java.awt.Color(0, 102, 0));
        jLabelThongBao2.setText("Thong bao 2");

        jLabelKTSoGhe.setForeground(new java.awt.Color(255, 51, 51));

        jLabelThongBaoTuyen.setForeground(new java.awt.Color(255, 51, 51));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/change_50px.png"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelMuaVeLayout = new javax.swing.GroupLayout(jPanelMuaVe);
        jPanelMuaVe.setLayout(jPanelMuaVeLayout);
        jPanelMuaVeLayout.setHorizontalGroup(
            jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMuaVeLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(439, 439, 439))
                    .addGroup(jPanelMuaVeLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelMuaVeLayout.createSequentialGroup()
                                .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBoxMVNoiDi, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxMVChuyen, javax.swing.GroupLayout.Alignment.LEADING, 0, 270, Short.MAX_VALUE)
                                    .addComponent(jTextFieldMVSoGhe, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelKTSoGhe, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelMuaVeLayout.createSequentialGroup()
                                .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxMVLoaiVe, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxMVNoiDen, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooserMVNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabelThongBaoTuyen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBoxMVTuyen, javax.swing.GroupLayout.Alignment.LEADING, 0, 267, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMuaVeLayout.createSequentialGroup()
                                .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelMuaVeLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabelThongBao2, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelMuaVeLayout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelThongBaoThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(12, 12, 12)))
                        .addContainerGap())))
        );
        jPanelMuaVeLayout.setVerticalGroup(
            jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMuaVeLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxMVLoaiVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldMVSoGhe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelKTSoGhe, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxMVChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBoxMVNoiDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMuaVeLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabelThongBaoThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMuaVeLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBoxMVNoiDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jLabelThongBao2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxMVTuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabelThongBaoTuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanelMuaVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooserMVNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(82, 82, 82)
                .addComponent(jButton1)
                .addGap(37, 37, 37))
        );

        jLayeredPane1.add(jPanelMuaVe, "card2");

        jPanelList5.setBackground(new java.awt.Color(64, 43, 100));
        jPanelList5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelList5MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Tra cứu");

        javax.swing.GroupLayout jPanelList5Layout = new javax.swing.GroupLayout(jPanelList5);
        jPanelList5.setLayout(jPanelList5Layout);
        jPanelList5Layout.setHorizontalGroup(
            jPanelList5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelList5Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelList5Layout.setVerticalGroup(
            jPanelList5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        jPanelList6.setBackground(new java.awt.Color(64, 43, 100));
        jPanelList6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelList6MouseClicked(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Tìm đường");

        javax.swing.GroupLayout jPanelList6Layout = new javax.swing.GroupLayout(jPanelList6);
        jPanelList6.setLayout(jPanelList6Layout);
        jPanelList6Layout.setHorizontalGroup(
            jPanelList6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelList6Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        jPanelList6Layout.setVerticalGroup(
            jPanelList6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        jLayeredPane2.setLayout(new java.awt.CardLayout());

        jTableTraCuuTuyen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tuyến"
            }
        ));
        jTableTraCuuTuyen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTraCuuTuyenMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableTraCuuTuyen);

        jTableKetQuaTuyen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tram"
            }
        ));
        jScrollPane3.setViewportView(jTableKetQuaTuyen);

        javax.swing.GroupLayout jPanelTraCuuTuyenLayout = new javax.swing.GroupLayout(jPanelTraCuuTuyen);
        jPanelTraCuuTuyen.setLayout(jPanelTraCuuTuyenLayout);
        jPanelTraCuuTuyenLayout.setHorizontalGroup(
            jPanelTraCuuTuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTraCuuTuyenLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelTraCuuTuyenLayout.setVerticalGroup(
            jPanelTraCuuTuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTraCuuTuyenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTraCuuTuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLayeredPane2.add(jPanelTraCuuTuyen, "card2");

        jTableKetQuaTimDuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Di chuyển bằng", "Chi tiết", "Thời gian"
            }
        ));
        jScrollPane4.setViewportView(jTableKetQuaTimDuong);

        jLabel1.setText("Đi từ");

        jLabel23.setText("Đến");

        jComboBoxSoChuyen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đi tối đa 1 chuyến", "Đi tối đa 2 chuyến", "Đi tối đa 3 chuyến" }));

        btnTimDuong.setBackground(new java.awt.Color(255, 255, 255));
        btnTimDuong.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnTimDuong.setForeground(new java.awt.Color(54, 33, 89));
        btnTimDuong.setText("Tìm Đường");
        btnTimDuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimDuongActionPerformed(evt);
            }
        });

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/change_50px.png"))); // NOI18N
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelTimDuongLayout = new javax.swing.GroupLayout(jPanelTimDuong);
        jPanelTimDuong.setLayout(jPanelTimDuongLayout);
        jPanelTimDuongLayout.setHorizontalGroup(
            jPanelTimDuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTimDuongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTimDuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTimDuongLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jComboBoxSoChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelTimDuongLayout.createSequentialGroup()
                        .addGroup(jPanelTimDuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelTimDuongLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxDiTu, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelTimDuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnTimDuong)
                                .addGroup(jPanelTimDuongLayout.createSequentialGroup()
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(2, 2, 2)
                                    .addComponent(jComboBoxDen, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(2, 2, 2))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelTimDuongLayout.setVerticalGroup(
            jPanelTimDuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTimDuongLayout.createSequentialGroup()
                .addGroup(jPanelTimDuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTimDuongLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanelTimDuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxDiTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(35, 35, 35)
                        .addGroup(jPanelTimDuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)))
                    .addGroup(jPanelTimDuongLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel24)))
                .addGap(18, 18, 18)
                .addComponent(jComboBoxSoChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btnTimDuong)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTimDuongLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jLayeredPane2.add(jPanelTimDuong, "card3");

        javax.swing.GroupLayout jPanelTraCuuLayout = new javax.swing.GroupLayout(jPanelTraCuu);
        jPanelTraCuu.setLayout(jPanelTraCuuLayout);
        jPanelTraCuuLayout.setHorizontalGroup(
            jPanelTraCuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTraCuuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelList5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelList6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(216, 216, 216))
            .addComponent(jLayeredPane2)
        );
        jPanelTraCuuLayout.setVerticalGroup(
            jPanelTraCuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTraCuuLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelTraCuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelList6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelList5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane2)
                .addContainerGap())
        );

        jLayeredPane1.add(jPanelTraCuu, "card5");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(54, 33, 89));
        jLabel16.setText("Xin chào,");

        jLabelHello.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelHello.setForeground(new java.awt.Color(54, 33, 89));
        jLabelHello.setText("ten khach hang");

        jPanel2.setBackground(new java.awt.Color(54, 33, 89));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 609, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelHello, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabelHello, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanelList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLayeredPane1))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(1141, 705));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelList1MouseClicked
        // TODO add your handling code here:
        switchPanels(jPanelMuaVe);
        setColor(jPanelList1);
        resetColor(jPanelList2);
        resetColor(jPanelList3);
        resetColor(jPanelList4);
    }//GEN-LAST:event_jPanelList1MouseClicked

    private void jPanelList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelList2MouseClicked
        // TODO add your handling code here:
        switchPanels(jPanelTimVe);
        resetColor(jPanelList1);
        setColor(jPanelList2);
        resetColor(jPanelList3);
        resetColor(jPanelList4);
    }//GEN-LAST:event_jPanelList2MouseClicked

    private void jPanelList3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelList3MouseClicked
        // TODO add your handling code here:
        switchPanels(jPanelTaiKhoan);
        resetColor(jPanelList1);
        resetColor(jPanelList2);
        setColor(jPanelList3);
        resetColor(jPanelList4);
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
            if(isNumeric(soGhe1)==false){
                JOptionPane.showMessageDialog(this, "So ghe phai la mot so nguyen.VD:1");
                return;
            }
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
            String maLoaiVe=layMaLoaiVeTuTenLoaiVe(tenLoaiVe1);
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
            layVe(jLabelTaiKhoan.getText());

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBoxMVTuyenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxMVTuyenItemStateChanged
        
        thongBaoMuaVe1();
        thongBaoMuaVe2();
    }//GEN-LAST:event_jComboBoxMVTuyenItemStateChanged

    private void jComboBoxMVNoiDiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxMVNoiDiItemStateChanged
        // TODO add your handling code here:
        jComboBoxMVTuyen.removeAllItems();
        jLabelThongBaoTuyen.setText("");
        String noiDi=(String) jComboBoxMVNoiDi.getSelectedItem();
        String noiDen=(String) jComboBoxMVNoiDen.getSelectedItem();
        
        
        String []tuyenNoiDi=new String[100];
        String []tuyenNoiDen=new String[100];
        String []tuyenChung=new String[100];
        int i=0,j=0,k=0;
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select Tuyen from TuyenTram where Tram='"+noiDi+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                tuyenNoiDi[i]=rs.getString(1);
                i++;
            }
        }catch(SQLException e){
            System.out.println("loi lay tuyen tu tram di tram den de mua ve");
        }
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select Tuyen from TuyenTram where Tram='"+noiDen+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                tuyenNoiDen[j]=rs.getString(1);
                j++;
            }
        }catch(SQLException e){
            System.out.println("loi lay tuyen tu tram di tram den de mua ve");
        }
        System.out.println(i +"  "+j);
        for (int n = 0; n < i; n++) {
            for (int m = 0; m < j; m++) {
                if (tuyenNoiDi[n].equalsIgnoreCase(tuyenNoiDen[m])) {
                    tuyenChung[k]=tuyenNoiDi[n];
                    k++;
                }
            }
        }
        
        if(k==0){//khong co tuyen chung nao
            jLabelThongBaoTuyen.setText("Khong co tuyen nao chua 2 tram nay");
        }
        //co tuyen chung
        for (int l = 0; l < k; l++) {
            jComboBoxMVTuyen.addItem(tuyenChung[l]);
        }
        int min=100000000;
        for(int g=0;g<k;g++){
            if(layKhoangCachTuTramToiTramTrongTuyen(tuyenChung[g], noiDi, noiDen)<min){
                jComboBoxMVTuyen.setSelectedItem(tuyenChung[g]);
            }
        }
        //--------
        thongBaoMuaVe1();
        thongBaoMuaVe2();
    }//GEN-LAST:event_jComboBoxMVNoiDiItemStateChanged

    private void jButtonDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDoiMatKhauActionPerformed
        // TODO add your handling code here:
        String matKhau=jTextFieldPassSua.getText().trim();
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

    private void jlbDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbDangXuatMouseClicked
        // TODO add your handling code here:
        int ret= JOptionPane.showConfirmDialog(this, "Ban chac chan muon dang xuat?","Xac nhan",0);
                
        if(ret == JOptionPane.CANCEL_OPTION){
            return;
        }else if(ret == JOptionPane.OK_OPTION){
            this.dispose();
            //new Login(null, true);
            
            Login dialog = new Login(new javax.swing.JFrame(), true);
            dialog.setVisible(true);
        }
        
    }//GEN-LAST:event_jlbDangXuatMouseClicked

    private void jComboBoxMVNoiDenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxMVNoiDenItemStateChanged
        // TODO add your handling code here:
        jComboBoxMVTuyen.removeAllItems();
        jLabelThongBaoTuyen.setText("");
        String noiDi=(String) jComboBoxMVNoiDi.getSelectedItem();
        String noiDen=(String) jComboBoxMVNoiDen.getSelectedItem();
        
        
        String []tuyenNoiDi=new String[100];
        String []tuyenNoiDen=new String[100];
        String []tuyenChung=new String[100];
        int i=0,j=0,k=0;
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select Tuyen from TuyenTram where Tram='"+noiDi+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                tuyenNoiDi[i]=rs.getString(1);
                i++;
            }
        }catch(SQLException e){
            System.out.println("loi lay tuyen tu tram di tram den de mua ve");
        }
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select Tuyen from TuyenTram where Tram='"+noiDen+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                tuyenNoiDen[j]=rs.getString(1);
                j++;
            }
        }catch(SQLException e){
            System.out.println("loi lay tuyen tu tram di tram den de mua ve");
        }
        System.out.println(i +"  "+j);
        for (int n = 0; n < i; n++) {
            for (int m = 0; m < j; m++) {
                if (tuyenNoiDi[n].equalsIgnoreCase(tuyenNoiDen[m])) {
                    tuyenChung[k]=tuyenNoiDi[n];
                    k++;
                }
            }
        }
        
        if(k==0){//khong co tuyen chung nao
            jLabelThongBaoTuyen.setText("Khong co tuyen nao chua 2 tram nay");
        }
        //co tuyen chung
        for (int l = 0; l < k; l++) {
            jComboBoxMVTuyen.addItem(tuyenChung[l]);
        }
        int min=100000000;
        for(int g=0;g<k;g++){
            if(layKhoangCachTuTramToiTramTrongTuyen(tuyenChung[g], noiDi, noiDen)<min){
                jComboBoxMVTuyen.setSelectedItem(tuyenChung[g]);
                min=layKhoangCachTuTramToiTramTrongTuyen(tuyenChung[g], noiDi, noiDen);
            }
        }
        
        //----------thong bao khoang cach
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

    private void jTextFieldMVSoGheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldMVSoGheKeyReleased
        // TODO add your handling code here:
        if(isNumeric(jTextFieldMVSoGhe.getText().trim())==false){
            jLabelKTSoGhe.setText("So ghe phai la mot so nguyen.VD:1");
        }else{
            jLabelKTSoGhe.setText("");
        }
    }//GEN-LAST:event_jTextFieldMVSoGheKeyReleased

    private void jPanelList4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelList4MouseClicked
        // TODO add your handling code here:
        switchPanels(jPanelTraCuu);
        resetColor(jPanelList1);
        resetColor(jPanelList2);
        resetColor(jPanelList3);
        setColor(jPanelList4);
    }//GEN-LAST:event_jPanelList4MouseClicked

    private void jPanelList5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelList5MouseClicked
        // TODO add your handling code here:
        switchPanels2(jPanelTraCuuTuyen);
        setColor(jPanelList5);
        resetColor(jPanelList6);
    }//GEN-LAST:event_jPanelList5MouseClicked

    private void jPanelList6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelList6MouseClicked
        // TODO add your handling code here:
        switchPanels2(jPanelTimDuong);
        setColor(jPanelList6);
        resetColor(jPanelList5);
    }//GEN-LAST:event_jPanelList6MouseClicked

    private void jTableTraCuuTuyenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTraCuuTuyenMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTableTraCuuTuyen.getModel();
        int selectedRow = jTableTraCuuTuyen.getSelectedRow();

        String maTuyen=model.getValueAt(selectedRow, 0).toString();
        
        //        Connection ketNoi=Connect.layKetNoi();
//        try{
//            PreparedStatement ps= ketNoi.prepareStatement("select Tram from TuyenTram where Tuyen='"+jComboBoxMVTuyen.getSelectedItem()+"'");
//            ResultSet rs=ps.executeQuery();
//            while(rs.next()){
//                jComboBoxMVNoiDi.addItem(rs.getString("Tram"));
//                jComboBoxMVNoiDen.addItem(rs.getString("Tram"));
//            }
//        }catch(SQLException e){
//            System.out.println("loi lay tram");
//        }
    dtm3= (DefaultTableModel) jTableKetQuaTuyen.getModel();
        dtm3.setNumRows(0);
        Connection ketNoi=Connect.layKetNoi();
        Vector vt;
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select Tram from TuyenTram where Tuyen='"+maTuyen+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                //jComboBoxMVTuyen.addItem(rs.getString("MaTuyen"));
                vt= new Vector();
                vt.add(rs.getString(1));
                dtm3.addRow(vt);
            }
            jTableKetQuaTuyen.setModel(dtm3);
            ps.close();
            rs.close();
            ketNoi.close();
        }catch(SQLException e){
            System.out.println("loi lay tram");
        }
    }//GEN-LAST:event_jTableTraCuuTuyenMouseClicked

    private void btnTimDuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimDuongActionPerformed
        // TODO add your handling code here:
//        DefaultTableModel dtm4;
//        dtm4= (DefaultTableModel) jTableKetQuaTimDuong.getModel();
//        dtm4.setNumRows(0);
//        jTableKetQuaTimDuong.setModel(dtm4);
//            
//        String noiDi=(String) jComboBoxDiTu.getSelectedItem();
//        String noiDen=(String) jComboBoxDen.getSelectedItem();
//        String gioiHanTim="";
//        if(jComboBoxSoChuyen.getSelectedItem().equals("Đi tối đa 1 chuyến")){
//            gioiHanTim="1";
//        }else if(jComboBoxSoChuyen.getSelectedItem().equals("Đi tối đa 2 chuyến")){
//            gioiHanTim="2";
//        }else{
//            gioiHanTim="3";
//        }
//        if(gioiHanTim.equals("1")){
//            String []tuyenNoiDi=new String[100];
//            String []tuyenNoiDen=new String[100];
//            String []tuyenChung=new String[100];
//            int i=0,j=0,k=0;
//            Connection ketNoi=Connect.layKetNoi();
//            try{
//                PreparedStatement ps= ketNoi.prepareStatement("select Tuyen from TuyenTram where Tram='"+noiDi+"'");
//                ResultSet rs=ps.executeQuery();
//                while(rs.next()){
//                    tuyenNoiDi[i]=rs.getString(1);
//                    i++;
//                }
//            }catch(SQLException e){
//                System.out.println("loi lay tuyen tu tram di tram den de mua ve");
//            }
//            try{
//                PreparedStatement ps= ketNoi.prepareStatement("select Tuyen from TuyenTram where Tram='"+noiDen+"'");
//                ResultSet rs=ps.executeQuery();
//                while(rs.next()){
//                    tuyenNoiDen[j]=rs.getString(1);
//                    j++;
//                }
//            }catch(SQLException e){
//                System.out.println("loi lay tuyen tu tram di tram den de mua ve");
//            }
//            System.out.println(i +"  "+j);
//            for (int n = 0; n < i; n++) {
//                for (int m = 0; m < j; m++) {
//                    if (tuyenNoiDi[n].equalsIgnoreCase(tuyenNoiDen[m])) {
//                        tuyenChung[k]=tuyenNoiDi[n];
//                        k++;
//                    }
//                }
//            }
//            
//            
//            Vector vt3;
//            vt3= new Vector();
//            for (int l = 0; l < k; l++) {
//                vt3.add(tuyenChung[l]);
//                //vt3.add("");
//                dtm4.addRow(vt3);
//                System.out.println("tuyen chung "+tuyenChung[l]);
//            }
//            
//            jTableKetQuaTimDuong.setModel(dtm4);
//        }else if(gioiHanTim.equals("2")){
//            String []tuyenNoiDi=new String[100];
//            String []tuyenNoiDen=new String[100];
//            String []tuyenChung=new String[100];
//            int i=0,j=0,k=0;
//            Connection ketNoi=Connect.layKetNoi();
//            try{
//                PreparedStatement ps= ketNoi.prepareStatement("select Tuyen from TuyenTram where Tram='"+noiDi+"'");
//                ResultSet rs=ps.executeQuery();
//                while(rs.next()){
//                    tuyenNoiDi[i]=rs.getString(1);
//                    i++;
//                }
//            }catch(SQLException e){
//                System.out.println("loi lay tuyen tu tram di tram den de mua ve");
//            }
//            try{
//                PreparedStatement ps= ketNoi.prepareStatement("select Tuyen from TuyenTram where Tram='"+noiDen+"'");
//                ResultSet rs=ps.executeQuery();
//                while(rs.next()){
//                    tuyenNoiDen[j]=rs.getString(1);
//                    j++;
//                }
//            }catch(SQLException e){
//                System.out.println("loi lay tuyen tu tram di tram den de mua ve");
//            }
//            System.out.println(i +"  "+j);
//            for (int n = 0; n < i; n++) {
//                for (int m = 0; m < j; m++) {
//                    if (tuyenNoiDi[n].equalsIgnoreCase(tuyenNoiDen[m])) {
//                        tuyenChung[k]=tuyenNoiDi[n];
//                        k++;
//                    }
//                }
//            }
//            DefaultTableModel dtm5;
//            dtm5= (DefaultTableModel) jTableKetQuaTimDuong.getModel();
//            dtm5.setNumRows(0);
//            Vector vt;
//            vt= new Vector();
//            
//            for (int l = 0; l < k; l++) {
//                vt.add(tuyenChung[l]);
//                dtm5.addRow(vt);
//            }
//            
//            //jTableKetQuaTimDuong.setModel(dtm5);
//            //DefaultTableModel dtm5;
////            dtm5= (DefaultTableModel) jTableKetQuaTimDuong.getModel();
////            dtm5.setNumRows(0);
//            if(k==0){
//                for (int l = 0; l < i; l++) {
//                    for (int m = 0; m < j; m++) {
//                        if(timTramChung(tuyenNoiDi[l], tuyenNoiDen[m])==null){
//                            
//                        }else{
//                            String tramChung=timTramChung(tuyenNoiDi[l], tuyenNoiDen[m]);
//                            Vector vt1;
//                            vt1= new Vector();
////                            for (int l = 0; l < k; l++) {
////                                vt1.add(tuyenChung[l]);
////                            }
//                            vt1.add(tuyenNoiDi[l]+","+tuyenNoiDen[m]);
//                            vt1.add("Tu "+noiDi+" den "+tramChung+",tu "+tramChung+" den "+noiDen);
//                            dtm5.addRow(vt1);
//                            
////                            System.out.println("Di bang tuyen "+tuyenNoiDi[l]+" va "+tuyenNoiDen[m]);
////                            System.out.println("Tu "+noiDi+" den "+tramChung+",tu "+tramChung+" den "+noiDen);
//                            //return;
//                        }
//                    }
//                }
//            }
//            jTableKetQuaTimDuong.setModel(dtm5);
//            
//        }else{
//            String []tatCaTuyen=new String [100];
//            String []tuyenNoiDi=new String[100];
//            String []tuyenNoiDen=new String[100];
//            String []tuyenChung=new String[100];
//            int a=0,i=0,j=0,k=0;
//            Connection ketNoi=Connect.layKetNoi();
//            try{
//                PreparedStatement ps= ketNoi.prepareStatement("select MaTuyen from Tuyen");
//                ResultSet rs=ps.executeQuery();
//                while(rs.next()){
//                    tatCaTuyen[a]=rs.getString(1);
//                    a++;
//                }
//            }catch(SQLException e){
//                System.out.println("loi lay tat ca tuyen");
//            }
//            try{
//                PreparedStatement ps= ketNoi.prepareStatement("select Tuyen from TuyenTram where Tram='"+noiDi+"'");
//                ResultSet rs=ps.executeQuery();
//                while(rs.next()){
//                    tuyenNoiDi[i]=rs.getString(1);
//                    i++;
//                }
//            }catch(SQLException e){
//                System.out.println("loi lay tuyen tu tram di tram den de mua ve");
//            }
//            try{
//                PreparedStatement ps= ketNoi.prepareStatement("select Tuyen from TuyenTram where Tram='"+noiDen+"'");
//                ResultSet rs=ps.executeQuery();
//                while(rs.next()){
//                    tuyenNoiDen[j]=rs.getString(1);
//                    j++;
//                }
//            }catch(SQLException e){
//                System.out.println("loi lay tuyen tu tram di tram den de mua ve");
//            }
//            System.out.println(i +"  "+j);
//            for (int n = 0; n < i; n++) {
//                for (int m = 0; m < j; m++) {
//                    if (tuyenNoiDi[n].equalsIgnoreCase(tuyenNoiDen[m])) {
//                        tuyenChung[k]=tuyenNoiDi[n];
//                        k++;
//                    }
//                }
//            }
//            DefaultTableModel dtm5;
//            dtm5= (DefaultTableModel) jTableKetQuaTimDuong.getModel();
//            dtm5.setNumRows(0);
//            Vector vt;
//            vt= new Vector();
//            for (int l = 0; l < k; l++) {
//                vt.add(tuyenChung[l]);
//                dtm5.addRow(vt);
//            }
//            //if(k==0){
//            for (int l = 0; l < i; l++) {
//                for (int m = 0; m < j; m++) {
//                    if(timTramChung(tuyenNoiDi[l], tuyenNoiDen[m])==null){
//
//                    }else{
//                        String tramChung=timTramChung(tuyenNoiDi[l], tuyenNoiDen[m]);
//                        Vector vt1;
//                        vt1= new Vector();
//                        vt1.add(tuyenNoiDi[l]+","+tuyenNoiDen[m]);
//                        vt1.add("Tu "+noiDi+" den "+tramChung+",tu "+tramChung+" den "+noiDen);
//                        dtm5.addRow(vt1);
//                    }
//                }
//            }
//            //}
//            for (int l = 0; l < i; l++) {
//                for (int m = 0; m < j; m++) {
//                    for (int n = 0; n < a; n++) {
//                        String tramChung1=timTramChung(tuyenNoiDi[l], tatCaTuyen[n]);
//                        String tramChung2=timTramChung(tuyenNoiDen[m], tatCaTuyen[n]);
//                        if(kiemTraChungTuyen(tatCaTuyen[n],tramChung1,tramChung2)==true){
//                            System.out.println(tuyenNoiDi[l]+" "+tatCaTuyen[n]+" "+tuyenNoiDen[m]);
//                            System.out.println(noiDi+" "+tramChung1+" "+tramChung2+" "+noiDen);
//                            Vector vt2;
//                            vt2= new Vector();
//                            vt2.add(tuyenNoiDi[l]+","+tatCaTuyen[n]+","+tuyenNoiDen[m]);
//                            vt2.add(noiDi+" den "+tramChung1+","+tramChung1+" den "+tramChung2+","+tramChung2+" den "+noiDen);
//                            dtm5.addRow(vt2);
//                        }
//                    }
//                }
//            }
//            jTableKetQuaTimDuong.setModel(dtm5);
//        }
    //------------------------------------------
    DefaultTableModel dtm4;
        dtm4= (DefaultTableModel) jTableKetQuaTimDuong.getModel();
        dtm4.setNumRows(0);
        jTableKetQuaTimDuong.setModel(dtm4);
            
        String noiDi=(String) jComboBoxDiTu.getSelectedItem();
        String noiDen=(String) jComboBoxDen.getSelectedItem();
        String gioiHanTim="";
        if(jComboBoxSoChuyen.getSelectedItem().equals("Đi tối đa 1 chuyến")){
            gioiHanTim="1";
        }else if(jComboBoxSoChuyen.getSelectedItem().equals("Đi tối đa 2 chuyến")){
            gioiHanTim="2";
        }else{
            gioiHanTim="3";
        }
        if(gioiHanTim.equals("1")){
            String []tuyenNoiDi=new String[100];
            String []tuyenNoiDen=new String[100];
            String []tuyenChung=new String[100];
            int i=0,j=0,k=0;
            Connection ketNoi=Connect.layKetNoi();
            try{
                PreparedStatement ps= ketNoi.prepareStatement("select Tuyen from TuyenTram where Tram='"+noiDi+"'");
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    tuyenNoiDi[i]=rs.getString(1);
                    i++;
                }
            }catch(SQLException e){
                System.out.println("loi lay tuyen tu tram di tram den de mua ve");
            }
            try{
                PreparedStatement ps= ketNoi.prepareStatement("select Tuyen from TuyenTram where Tram='"+noiDen+"'");
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    tuyenNoiDen[j]=rs.getString(1);
                    j++;
                }
            }catch(SQLException e){
                System.out.println("loi lay tuyen tu tram di tram den de mua ve");
            }
            System.out.println(i +"  "+j);
            for (int n = 0; n < i; n++) {
                for (int m = 0; m < j; m++) {
                    if (tuyenNoiDi[n].equalsIgnoreCase(tuyenNoiDen[m])) {
                        tuyenChung[k]=tuyenNoiDi[n];
                        k++;
                    }
                }
            }
            
            
            Vector vt3;
            vt3= new Vector();
            for (int l = 0; l < k; l++) {
                vt3.add(tuyenChung[l]);
                vt3.add("Tu "+noiDi+" den "+noiDen);
                vt3.add(thoiGianTuTramToiTramTrongTuyen(tuyenChung[l], noiDi, noiDen));
                dtm4.addRow(vt3);
                System.out.println("tuyen chung "+tuyenChung[l]);
            }
            
            jTableKetQuaTimDuong.setModel(dtm4);
        }else if(gioiHanTim.equals("2")){
            String []tuyenNoiDi=new String[100];
            String []tuyenNoiDen=new String[100];
            String []tuyenChung=new String[100];
            int i=0,j=0,k=0;
            Connection ketNoi=Connect.layKetNoi();
            try{
                PreparedStatement ps= ketNoi.prepareStatement("select Tuyen from TuyenTram where Tram='"+noiDi+"'");
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    tuyenNoiDi[i]=rs.getString(1);
                    i++;
                }
            }catch(SQLException e){
                System.out.println("loi lay tuyen tu tram di tram den de mua ve");
            }
            try{
                PreparedStatement ps= ketNoi.prepareStatement("select Tuyen from TuyenTram where Tram='"+noiDen+"'");
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    tuyenNoiDen[j]=rs.getString(1);
                    j++;
                }
            }catch(SQLException e){
                System.out.println("loi lay tuyen tu tram di tram den de mua ve");
            }
            System.out.println(i +"  "+j);
            for (int n = 0; n < i; n++) {
                for (int m = 0; m < j; m++) {
                    if (tuyenNoiDi[n].equalsIgnoreCase(tuyenNoiDen[m])) {
                        tuyenChung[k]=tuyenNoiDi[n];
                        k++;
                    }
                }
            }
            DefaultTableModel dtm5;
            dtm5= (DefaultTableModel) jTableKetQuaTimDuong.getModel();
            dtm5.setNumRows(0);
            Vector vt;
            vt= new Vector();
            
            for (int l = 0; l < k; l++) {
                vt.add(tuyenChung[l]);
                vt.add(thoiGianTuTramToiTramTrongTuyen(tuyenChung[l], noiDi, noiDen));
                dtm5.addRow(vt);
            }
            
            //jTableKetQuaTimDuong.setModel(dtm5);
            //DefaultTableModel dtm5;
//            dtm5= (DefaultTableModel) jTableKetQuaTimDuong.getModel();
//            dtm5.setNumRows(0);
            if(k==0){
                for (int l = 0; l < i; l++) {
                    for (int m = 0; m < j; m++) {
                        if(timTramChung(tuyenNoiDi[l], tuyenNoiDen[m])==null){
                            
                        }else{
                            String tramChung=timTramChung(tuyenNoiDi[l], tuyenNoiDen[m]);
                            Vector vt1;
                            vt1= new Vector();
//                            for (int l = 0; l < k; l++) {
//                                vt1.add(tuyenChung[l]);
//                            }
                            vt1.add(tuyenNoiDi[l]+","+tuyenNoiDen[m]);
                            vt1.add("Tu "+noiDi+" den "+tramChung+",tu "+tramChung+" den "+noiDen);
                            vt1.add(thoiGianTuTramToiTramTrongTuyen(tuyenNoiDi[l],noiDi,tramChung)+","+thoiGianTuTramToiTramTrongTuyen(tuyenNoiDen[m],tramChung,noiDen));
                            dtm5.addRow(vt1);
                            
//                            System.out.println("Di bang tuyen "+tuyenNoiDi[l]+" va "+tuyenNoiDen[m]);
//                            System.out.println("Tu "+noiDi+" den "+tramChung+",tu "+tramChung+" den "+noiDen);
                            //return;
                        }
                    }
                }
            }
            jTableKetQuaTimDuong.setModel(dtm5);
            
        }else{
            String []tatCaTuyen=new String [100];
            String []tuyenNoiDi=new String[100];
            String []tuyenNoiDen=new String[100];
            String []tuyenChung=new String[100];
            int a=0,i=0,j=0,k=0;
            Connection ketNoi=Connect.layKetNoi();
            try{
                PreparedStatement ps= ketNoi.prepareStatement("select MaTuyen from Tuyen");
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    tatCaTuyen[a]=rs.getString(1);
                    a++;
                }
            }catch(SQLException e){
                System.out.println("loi lay tat ca tuyen");
            }
            try{
                PreparedStatement ps= ketNoi.prepareStatement("select Tuyen from TuyenTram where Tram='"+noiDi+"'");
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    tuyenNoiDi[i]=rs.getString(1);
                    i++;
                }
            }catch(SQLException e){
                System.out.println("loi lay tuyen tu tram di tram den de mua ve");
            }
            try{
                PreparedStatement ps= ketNoi.prepareStatement("select Tuyen from TuyenTram where Tram='"+noiDen+"'");
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    tuyenNoiDen[j]=rs.getString(1);
                    j++;
                }
            }catch(SQLException e){
                System.out.println("loi lay tuyen tu tram di tram den de mua ve");
            }
            System.out.println(i +"  "+j);
            for (int n = 0; n < i; n++) {
                for (int m = 0; m < j; m++) {
                    if (tuyenNoiDi[n].equalsIgnoreCase(tuyenNoiDen[m])) {
                        tuyenChung[k]=tuyenNoiDi[n];
                        k++;
                    }
                }
            }
            DefaultTableModel dtm5;
            dtm5= (DefaultTableModel) jTableKetQuaTimDuong.getModel();
            dtm5.setNumRows(0);
            Vector vt;
            vt= new Vector();
            for (int l = 0; l < k; l++) {
                vt.add(tuyenChung[l]);
                dtm5.addRow(vt);
            }
            if(k==0){
                for (int l = 0; l < i; l++) {
                    for (int m = 0; m < j; m++) {
                        if(timTramChung(tuyenNoiDi[l], tuyenNoiDen[m])==null){

                        }else{
                            String tramChung=timTramChung(tuyenNoiDi[l], tuyenNoiDen[m]);
                            Vector vt1;
                            vt1= new Vector();
                            vt1.add(tuyenNoiDi[l]+","+tuyenNoiDen[m]);
                            vt1.add("Tu "+noiDi+" den "+tramChung+",tu "+tramChung+" den "+noiDen);
                            vt1.add(thoiGianTuTramToiTramTrongTuyen(tuyenNoiDi[l],noiDi,tramChung)+","+thoiGianTuTramToiTramTrongTuyen(tuyenNoiDen[m],tramChung,noiDen));
                            dtm5.addRow(vt1);
                        }
                    }
                }
            }
            if(dtm5.getRowCount()==0){
                for (int l = 0; l < i; l++) {
                    for (int m = 0; m < j; m++) {
                        for (int n = 0; n < a; n++) {
                            String tramChung1=timTramChung(tuyenNoiDi[l], tatCaTuyen[n]);
                            String tramChung2=timTramChung(tuyenNoiDen[m], tatCaTuyen[n]);
                            if(kiemTraChungTuyen(tatCaTuyen[n],tramChung1,tramChung2)==true){
                                System.out.println(tuyenNoiDi[l]+" "+tatCaTuyen[n]+" "+tuyenNoiDen[m]);
                                System.out.println(noiDi+" "+tramChung1+" "+tramChung2+" "+noiDen);
                                Vector vt2;
                                vt2= new Vector();
                                vt2.add(tuyenNoiDi[l]+","+tatCaTuyen[n]+","+tuyenNoiDen[m]);
                                vt2.add(noiDi+" den "+tramChung1+","+tramChung1+" den "+tramChung2+","+tramChung2+" den "+noiDen);
                                vt2.add(thoiGianTuTramToiTramTrongTuyen(tuyenNoiDi[l], noiDi, tramChung1)+","+
                                        thoiGianTuTramToiTramTrongTuyen(tatCaTuyen[n], tramChung1, tramChung2)+","+
                                        thoiGianTuTramToiTramTrongTuyen(tuyenNoiDen[m], tramChung2, noiDen));
                                dtm5.addRow(vt2);
                            }
                        }
                    }
                }
            }
            jTableKetQuaTimDuong.setModel(dtm5);
        }
    
    }//GEN-LAST:event_btnTimDuongActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        String temp=(String) jComboBoxMVNoiDen.getSelectedItem();
        jComboBoxMVNoiDen.setSelectedItem(jComboBoxMVNoiDi.getSelectedItem());
        jComboBoxMVNoiDi.setSelectedItem(temp);
        
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
        // TODO add your handling code here:
        String temp=(String) jComboBoxDen.getSelectedItem();
        jComboBoxDen.setSelectedItem(jComboBoxDiTu.getSelectedItem());
        jComboBoxDiTu.setSelectedItem(temp);
    }//GEN-LAST:event_jLabel24MouseClicked
    public void switchPanels(JPanel panel){
        jLayeredPane1.removeAll();
        jLayeredPane1.add(panel);
        jLayeredPane1.repaint();
        jLayeredPane1.revalidate();
    }
    public void switchPanels2(JPanel panel){
        jLayeredPane2.removeAll();
        jLayeredPane2.add(panel);
        jLayeredPane2.repaint();
        jLayeredPane2.revalidate();
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
    private javax.swing.JButton btnTimDuong;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonDoiMatKhau;
    private javax.swing.JComboBox<String> jComboBoxDen;
    private javax.swing.JComboBox<String> jComboBoxDiTu;
    private javax.swing.JComboBox<String> jComboBoxMVChuyen;
    private javax.swing.JComboBox<String> jComboBoxMVLoaiVe;
    private javax.swing.JComboBox<String> jComboBoxMVNoiDen;
    private javax.swing.JComboBox<String> jComboBoxMVNoiDi;
    private javax.swing.JComboBox<String> jComboBoxMVTuyen;
    private javax.swing.JComboBox<String> jComboBoxSoChuyen;
    private javax.swing.JComboBox<String> jComboBoxTimKiemVe;
    private com.toedter.calendar.JDateChooser jDateChooserMVNgay;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelHello;
    private javax.swing.JLabel jLabelHome;
    private javax.swing.JLabel jLabelKTSoGhe;
    private javax.swing.JLabel jLabelTaiKhoan;
    private javax.swing.JLabel jLabelThoiGian;
    private javax.swing.JLabel jLabelThongBao2;
    private javax.swing.JLabel jLabelThongBaoThoiGian;
    private javax.swing.JLabel jLabelThongBaoTuyen;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelList;
    private javax.swing.JPanel jPanelList1;
    private javax.swing.JPanel jPanelList2;
    private javax.swing.JPanel jPanelList3;
    private javax.swing.JPanel jPanelList4;
    private javax.swing.JPanel jPanelList5;
    private javax.swing.JPanel jPanelList6;
    private javax.swing.JPanel jPanelMuaVe;
    private javax.swing.JPanel jPanelTaiKhoan;
    private javax.swing.JPanel jPanelTimDuong;
    private javax.swing.JPanel jPanelTimVe;
    private javax.swing.JPanel jPanelTraCuu;
    private javax.swing.JPanel jPanelTraCuuTuyen;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableKetQuaTimDuong;
    private javax.swing.JTable jTableKetQuaTuyen;
    private javax.swing.JTable jTableTimVe;
    private javax.swing.JTable jTableTraCuuTuyen;
    private javax.swing.JTextField jTextFieldMVSoGhe;
    private javax.swing.JTextField jTextFieldPassSua;
    private javax.swing.JTextField jTextFieldTimVe;
    private javax.swing.JLabel jlbDangXuat;
    // End of variables declaration//GEN-END:variables
}
