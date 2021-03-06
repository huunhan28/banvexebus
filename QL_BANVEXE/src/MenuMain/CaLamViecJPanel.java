/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuMain;
import Controller.Connect;
import Login.Login;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import org.apache.poi.ss.usermodel.Row;

import XuatFile.*;
import java.text.ParseException;
/**
 *
 * @author Huu Nhan
 */
public class CaLamViecJPanel extends javax.swing.JPanel {
    DefaultTableModel dtm,dtm2,dtm3;
    /**
     * Creates new form CaLamViec1
     */
    public CaLamViecJPanel(String taiKhoan) {
        initComponents();
        layChuyen();
        layNhanVienLX();
        layNhanVienKV();
        layXe();
        layCaLamViec();
        layChuyenXeChuaPhanCong();
        layKhachHang(taiKhoan);
        txtSDT.setEditable(false);
        txtTenTK.setEditable(false);
        txtHoTen.setEditable(false);
    }
    void layKhachHang(String taiKhoan){
        
        txtTenTK.setText(taiKhoan);
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select TenKhachHang,MatKhau,TaiKhoan.SDT\n" +
            "from KhachHang,TaiKhoan \n" +
            "where TaiKhoan.SDT=KhachHang.SDT and KhachHang.TaiKhoan='"+taiKhoan+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                System.out.println("toi day roi");
                txtHoTen.setText(rs.getString(1));
                txtSDT.setText(rs.getString(3));
                //jPasswordFieldSua.setText(rs.getString(2));
            }
        }catch(SQLException e){
            System.out.println("loi lay thong tin de in ");
        }
        
    }
void layChuyenXeChuaPhanCong(){
        dtm= (DefaultTableModel) jTableChuyenXeChuaDuocPhanCong.getModel();
        dtm.setNumRows(0);
        Connection ketNoi=Connect.layKetNoi();
        Vector vt;
        try {
            PreparedStatement ps=ketNoi.prepareStatement("SELECT MaChuyenXe\n" +
                                                        "FROM ChuyenXe as a\n" +
                                                        "WHERE NOT EXISTS\n" +
                                                        "(SELECT *\n" +
                                                        "?????? FROM CaLamViec as b\n" +
                                                        "?????? WHERE a.MaChuyenXe=b.MaChuyenXe)");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                vt= new Vector();
                vt.add(rs.getString(1));
                dtm.addRow(vt);
                
            }
            jTableChuyenXeChuaDuocPhanCong.setModel(dtm);
            ps.close();
            rs.close();
            ketNoi.close();
        } catch (SQLException ex) {
            System.out.println("loi lay chuyen xe chua duoc phan cong");
        }
    }
    void layCaLamViec(){
        dtm2= (DefaultTableModel) jTableCaLamViec.getModel();
        dtm2.setNumRows(0);
        Connection ketNoi=Connect.layKetNoi();
        Vector vt;
        try {
            PreparedStatement ps=ketNoi.prepareStatement("select * from CaLamViec");
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
                dtm2.addRow(vt);
                
            }
            jTableCaLamViec.setModel(dtm2);
            ps.close();
            rs.close();
            ketNoi.close();
        } catch (SQLException ex) {
            System.out.println("loi lay ca lam viec");
        }
    }
    void layChuyen(){
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select MaChuyenXe from ChuyenXe");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                jComboBoxCLVCX.addItem(rs.getString("MaChuyenXe"));
            }
        }catch(SQLException e){
            System.out.println("loi lay chuyen xe");
        }
    }
    void layNhanVienLX(){
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select MaNV from NhanVien where ChucVu='Lai xe'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                jComboBoxCLVNVLX.addItem(rs.getString("MaNV"));
            }
        }catch(SQLException e){
            System.out.println("loi lay nhan vien lai xe");
        }
    }
    void layNhanVienKV(){
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select MaNV from NhanVien where ChucVu='Kiem ve'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                jComboBoxCLVNVKV.addItem(rs.getString("MaNV"));
            }
        }catch(SQLException e){
            System.out.println("loi lay nhan vien kiem ve");
        }
    }
    void layXe(){
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select BienSoXe from Xe");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                jComboBoxCLVBS.addItem(rs.getString("BienSoXe"));
            }
        }catch(SQLException e){
            System.out.println("loi lay bien so");
        }
    }
    public int kiemTraMaCa(String maCa){
        int tonTai=0;
        Connection ketNoi=Connect.layKetNoi();
            try{
                PreparedStatement ps = ketNoi.prepareStatement("select * from CaLamViec where MaCa ='"+maCa+"'");
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    tonTai=1;
                }
                rs.close();
                ps.close();
                ketNoi.close();

            }catch(SQLException ex){

            }
        return tonTai;
    }
    public int kTTrungTuyenNgayTTTGNV(String tuyen,String ngay,String trangThai,String gioKH,String gioDen,String nhanVien){
        int trung=0;
        String ngay1="";
        String tuyen1="";
        String trangThai1="";
        String ThoiGianKhoiHanh1="";
        String ThoiGianDen1="";
        String NhanVien1="";
        
            Connection ketNoi=Connect.layKetNoi();
            try{
                PreparedStatement ps= ketNoi.prepareStatement("select MaChuyenXe,ThoiGianKhoiHanh,ThoiGianDen,MaNV from CaLamViec ");
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String []thongTin=rs.getString(1).split("-");
                    ngay1=thongTin[0];
                    tuyen1=thongTin[2];
                    trangThai1=thongTin[3];
                    ThoiGianKhoiHanh1=rs.getString(2);
                    ThoiGianDen1=rs.getString(3);
                    NhanVien1=rs.getString(4);
                    if(tuyen.equals(tuyen1)&&ngay.equals(ngay1)&&trangThai.equals(trangThai1)&&
                            gioKH.equals(ThoiGianDen1)&&nhanVien.equals(NhanVien1)){
                        trung=1;
                    }
                    if(tuyen.equals(tuyen1)&&ngay.equals(ngay1)&&trangThai.equals(trangThai1)&&
                            gioDen.equals(ThoiGianKhoiHanh1)&&nhanVien.equals(NhanVien1)){
                        trung=1;
                    }
                }
            }catch(SQLException e){
                System.out.println("loi lay thong tin tu ca lam viec de kiem tra trung nhan vien");
            }
        
        return trung;
    }
    public int kTTrungTuyenNgayTTTGNVKV(String tuyen,String ngay,String trangThai,String gioKH,String gioDen,String nhanVienKV){
        int trung=0;
        String ngay1="";
        String tuyen1="";
        String trangThai1="";
        String ThoiGianKhoiHanh1="";
        String ThoiGianDen1="";
        String NhanVienKV1="";
            Connection ketNoi=Connect.layKetNoi();
            try{
                PreparedStatement ps= ketNoi.prepareStatement("select MaChuyenXe,ThoiGianKhoiHanh,ThoiGianDen,MaNVKiemVe from CaLamViec ");
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String []thongTin=rs.getString(1).split("-");
                    ngay1=thongTin[0];
                    tuyen1=thongTin[2];
                    trangThai1=thongTin[3];
                    ThoiGianKhoiHanh1=rs.getString(2);
                    ThoiGianDen1=rs.getString(3);
                    NhanVienKV1=rs.getString(4);
                    //trong cung 1 ngay tgBD khong the bang thoi gian ket thuc, ngoai tru cung tuyen va khac luot
                    if(ngay.equals(ngay1)&&
                            gioKH.equals(ThoiGianDen1)&&nhanVienKV.equals(NhanVienKV1)){
                        if(tuyen.equals(tuyen1)&&!(trangThai.equals(trangThai1))){
                            System.out.println("truong hop cho qua"+trangThai+" "+trangThai1);
                             break;
                        }
                        return 1;
                    }
                    //trong cung 1 ngay thoi gian ket thuc khong the bang tgBD, ngoai tru cung tuyen va khac luot
                    if(ngay.equals(ngay1)&&
                            gioDen.equals(ThoiGianKhoiHanh1)&&nhanVienKV.equals(NhanVienKV1)){
                        if(tuyen.equals(tuyen1)&&!(trangThai.equals(trangThai1))){
                            System.out.println("truong hop cho qua"+trangThai+" "+trangThai1);
                            break;
                        }
                        return 1;
                    }
                    if(ngay.equals(ngay1)&&
                            gioKH.equals(ThoiGianKhoiHanh1)&&nhanVienKV.equals(NhanVienKV1)){
                        return 1;
                    }
                    
                }
            }catch(SQLException e){
                System.out.println("loi lay thong tin tu ca lam viec de kiem tra trung nhan vien"+e.getMessage());
            }
        
        return trung;
    }
    public int kTTrungTuyenNgayTTTGNVLX(String tuyen,String ngay,String trangThai,String gioKH,String gioDen,String nhanVienLX){
        int trung=0;
        String ngay1="";
        String tuyen1="";
        String trangThai1="";
        String ThoiGianKhoiHanh1="";
        String ThoiGianDen1="";
        String NhanVienLX1="";
            Connection ketNoi=Connect.layKetNoi();
            try{
                PreparedStatement ps= ketNoi.prepareStatement("select MaChuyenXe,ThoiGianKhoiHanh,ThoiGianDen,MaNVLaiXe from CaLamViec ");
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String []thongTin=rs.getString(1).split("-");
                    ngay1=thongTin[0];
                    tuyen1=thongTin[2];
                    trangThai1=thongTin[3];
                    ThoiGianKhoiHanh1=rs.getString(2);
                    ThoiGianDen1=rs.getString(3);
                    NhanVienLX1=rs.getString(4);
                    //trong cung 1 ngay tgBD khong the bang thoi gian ket thuc, ngoai tru cung tuyen va khac luot
                    if(ngay.equals(ngay1)&&
                            gioKH.equals(ThoiGianDen1)&&nhanVienLX.equals(NhanVienLX1)){
                        if(tuyen.equals(tuyen1)&&!(trangThai.equals(trangThai1))){
                            System.out.println("truong hop cho qua"+trangThai+" "+trangThai1);
                             break;
                        }
                        return 1;
                    }
                    //trong cung 1 ngay thoi gian ket thuc khong the bang tgBD, ngoai tru cung tuyen va khac luot
                    if(ngay.equals(ngay1)&&
                            gioDen.equals(ThoiGianKhoiHanh1)&&nhanVienLX.equals(NhanVienLX1)){
                        if(tuyen.equals(tuyen1)&&!(trangThai.equals(trangThai1))){
                            System.out.println("truong hop cho qua"+trangThai+" "+trangThai1);
                            break;
                        }
                        return 1;
                    }
                    if(ngay.equals(ngay1)&&
                            gioKH.equals(ThoiGianKhoiHanh1)&&nhanVienLX.equals(NhanVienLX1)){
                        return 1;
                    }
                    
                }
            }catch(SQLException e){
                System.out.println("loi lay thong tin tu ca lam viec de kiem tra trung nhan vien"+e.getMessage());
            }
        
        return trung;
    }
    public int kTTrungTuyenNgayTTTGXe(String tuyen,String ngay,String trangThai,String gioKH,String gioDen,String Xe){
        int trung=0;
        String ngay1="";
        String tuyen1="";
        String trangThai1="";
        String ThoiGianKhoiHanh1="";
        String ThoiGianDen1="";
        String Xe1="";
        
            Connection ketNoi=Connect.layKetNoi();
            try{
                PreparedStatement ps= ketNoi.prepareStatement("select MaChuyenXe,ThoiGianKhoiHanh,ThoiGianDen,BienSoXe from CaLamViec ");
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String []thongTin=rs.getString(1).split("-");
                    ngay1=thongTin[0];
                    tuyen1=thongTin[2];
                    trangThai1=thongTin[3];
                    ThoiGianKhoiHanh1=rs.getString(2);
                    ThoiGianDen1=rs.getString(3);
                    Xe1=rs.getString(4);
                    //trong cung 1 ngay tgBD khong the bang thoi gian ket thuc, ngoai tru cung tuyen va khac luot
                    if(ngay.equals(ngay1)&&
                            gioKH.equals(ThoiGianDen1)&&Xe.equals(Xe1)){
                        if(tuyen.equals(tuyen1)&&!(trangThai.equals(trangThai1))){
                            System.out.println("truong hop cho qua"+trangThai+" "+trangThai1);
                             break;
                        }
                        return 1;
                    }
                    //trong cung 1 ngay thoi gian ket thuc khong the bang tgBD, ngoai tru cung tuyen va khac luot
                    if(ngay.equals(ngay1)&&
                            gioDen.equals(ThoiGianKhoiHanh1)&&Xe.equals(Xe1)){
                        if(tuyen.equals(tuyen1)&&!(trangThai.equals(trangThai1))){
                            System.out.println("truong hop cho qua"+trangThai+" "+trangThai1);
                            break;
                        }
                        return 1;
                    }
                    if(ngay.equals(ngay1)&&
                            gioKH.equals(ThoiGianKhoiHanh1)&&Xe.equals(Xe1)){
                        return 1;
                    }
                }
            }catch(SQLException e){
                System.out.println("loi lay thong tin tu ca lam viec de kiem tra trung xe");
            }
        
        return trung;
    }
    public int kTChuyenXeDaDuocPhanCong(String maChuyenXe){
        int tonTai=0;
        Connection ketNoi=Connect.layKetNoi();
            try{
                PreparedStatement ps= ketNoi.prepareStatement("select * from CaLamViec where MaChuyenXe='"+maChuyenXe+"'");
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    tonTai=1;
                }
            }catch(SQLException e){
                System.out.println("loi kt chuyen xe da duoc phan cong");
            }
        return tonTai;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldTKCaLamViec = new javax.swing.JTextField();
        jComboBoxTKCaLamViec = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCaLamViec = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableChuyenXeChuaDuocPhanCong = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtTenTK = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldCLVMaCa = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jComboBoxCLVCX = new javax.swing.JComboBox<>();
        jComboBoxCLVNVLX = new javax.swing.JComboBox<>();
        jComboBoxCLVBS = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnSua = new javax.swing.JButton();
        btnXuatFile = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jComboBoxCLVNVKV = new javax.swing.JComboBox<>();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(54, 33, 89));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QU???N L?? CA L??M VI???C");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Nh???p th??ng tin:");

        jTextFieldTKCaLamViec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldTKCaLamViecKeyReleased(evt);
            }
        });

        jComboBoxTKCaLamViec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MaCa", "MaChuyenXe", "MaNVLaiXe", "MaNVKiemVe", "BienSoXe", "ThoiGianKhoiHanh", "ThoiGianDen" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(26, 26, 26)
                .addComponent(jTextFieldTKCaLamViec, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxTKCaLamViec, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(271, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextFieldTKCaLamViec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTKCaLamViec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("DANH S??CH CA L??M VI???C");

        jTableCaLamViec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? ca", "M?? chuy???n xe", "M?? NV l??i xe", "M?? NV ki???m v??", "Bi???n s???", "Th???i gian kh???i h??n", "Th???i gian ?????n"
            }
        ));
        jTableCaLamViec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCaLamViecMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCaLamViec);

        jTableChuyenXeChuaDuocPhanCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chuy???n xe ch??a ph??n c??ng"
            }
        ));
        jTableChuyenXeChuaDuocPhanCong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableChuyenXeChuaDuocPhanCongMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableChuyenXeChuaDuocPhanCong);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(51, 102, 0));

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));

        jPanel5.setBackground(new java.awt.Color(54, 33, 89));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("TH??NG TIN T??I KHO???N S??? D???NG");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("S??T:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("T??n TK:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("H??? T??n:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(38, 38, 38))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(25, 25, 25)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTenTK, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(24, 24, 24)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTenTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(54, 33, 89));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TH??NG TIN CA L??M VI???C");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel3)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("M?? ca:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("M?? chuy???n xe:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("M?? NV l??i xe:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Bi???n s???:");

        jComboBoxCLVCX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Ch???n chuy???n xe-" }));
        jComboBoxCLVCX.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxCLVCXItemStateChanged(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnThem.setForeground(new java.awt.Color(0, 204, 0));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Save-icon.png"))); // NOI18N
        btnThem.setText("TH??M");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("(Th???i gian ?????n = th???i gian kh???i h??nh + 2h)");

        btnSua.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSua.setForeground(new java.awt.Color(102, 0, 255));
        btnSua.setText("S???A ");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXuatFile.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXuatFile.setForeground(new java.awt.Color(0, 153, 0));
        btnXuatFile.setText("IN DANH S??CH ");
        btnXuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("M?? NV ki???m v??:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel24)
                    .addComponent(jLabel23))
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxCLVBS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldCLVMaCa)
                    .addComponent(jComboBoxCLVCX, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxCLVNVLX, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxCLVNVKV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSua)
                .addGap(48, 48, 48))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel9))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(btnXuatFile)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jTextFieldCLVMaCa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jComboBoxCLVCX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jComboBoxCLVNVLX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCLVNVKV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jComboBoxCLVBS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(101, 101, 101)
                .addComponent(jLabel9)
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnThem)
                    .addComponent(btnSua))
                .addGap(18, 18, 18)
                .addComponent(btnXuatFile)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldTKCaLamViecKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTKCaLamViecKeyReleased
        // TODO add your handling code here:
        String timTheo=(String) jComboBoxTKCaLamViec.getSelectedItem();
        dtm3= (DefaultTableModel) jTableCaLamViec.getModel();
        dtm3.setNumRows(0);
        //jTableCaLamViec.setModel(dtm3);
        Vector vt;
        Connection ketNoi=Connect.layKetNoi();
        PreparedStatement ps;
        try {
            ps=ketNoi.prepareStatement("select * from CaLamViec where  "+timTheo+" LIKE'%"+jTextFieldTKCaLamViec.getText()+"%'");
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
                dtm3.addRow(vt);

            }
            jTableCaLamViec.setModel(dtm3);
            ps.close();
            rs.close();
            ketNoi.close();
        } catch (SQLException ex) {
            System.out.println("loi tim kiem ca lam viec");
        }
    }//GEN-LAST:event_jTextFieldTKCaLamViecKeyReleased

    private void jTableCaLamViecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCaLamViecMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model= (DefaultTableModel)jTableCaLamViec.getModel();
        int selectedRow = jTableCaLamViec.getSelectedRow();
        jTextFieldCLVMaCa.setEditable(false);
        jTextFieldCLVMaCa.setText(model.getValueAt(selectedRow, 0).toString());
        jComboBoxCLVCX.setSelectedItem(model.getValueAt(selectedRow, 1).toString());
        jComboBoxCLVNVLX.setSelectedItem(model.getValueAt(selectedRow, 2).toString());
        jComboBoxCLVNVKV.setSelectedItem(model.getValueAt(selectedRow, 3).toString());
        jComboBoxCLVBS.setSelectedItem(model.getValueAt(selectedRow, 4).toString());

    }//GEN-LAST:event_jTableCaLamViecMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        String maCa=jTextFieldCLVMaCa.getText().trim();
        if(kiemTraMaCa(maCa)==1){
            JOptionPane.showMessageDialog(this, "Ma ca da ton tai");
            return;
        }
        String maCX=(String) jComboBoxCLVCX.getSelectedItem();
        String trangThai="";
        String ngay="";
        String tuyen="";
        Connection ketNoi=Connect.layKetNoi();
        String []thongTin=maCX.split("-");
        ngay=thongTin[0];
        tuyen=thongTin[2];
        trangThai=thongTin[3];

        String maNVLX=(String) jComboBoxCLVNVLX.getSelectedItem();
        String maNVKV=(String) jComboBoxCLVNVKV.getSelectedItem();
        String bienSoXe=(String) jComboBoxCLVBS.getSelectedItem();
        String thoiGianKhoiHanh=thongTin[1];
        String thoiGianDen="";
        if(thoiGianKhoiHanh.equals("5h")){
            thoiGianDen="7h";
        }
        if(thoiGianKhoiHanh.equals("7h")){
            thoiGianDen="9h";
        }
        if(thoiGianKhoiHanh.equals("9h")){
            thoiGianDen="11h";
        }
        if(thoiGianKhoiHanh.equals("11h")){
            thoiGianDen="13h";
        }
        if(thoiGianKhoiHanh.equals("13h")){
            thoiGianDen="15h";
        }
        if(thoiGianKhoiHanh.equals("15h")){
            thoiGianDen="17h";
        }
        if(thoiGianKhoiHanh.equals("17h")){
            thoiGianDen="19h";
        }
        if(thoiGianKhoiHanh.equals("19h")){
            thoiGianDen="21h";
        }
        if(kTTrungTuyenNgayTTTGNVLX(tuyen,ngay,trangThai,thoiGianKhoiHanh,thoiGianDen,maNVLX)==1){
            JOptionPane.showMessageDialog(this, "Nhan vien lai xe nay thoi diem nay khong the phan cong");
            return;
        }
        if(kTTrungTuyenNgayTTTGNVKV(tuyen,ngay,trangThai,thoiGianKhoiHanh,thoiGianDen,maNVKV)==1){
            JOptionPane.showMessageDialog(this, "Nhan vien kiem ve nay thoi diem nay khong the phan cong");
            return;
        }
        if(kTTrungTuyenNgayTTTGXe(tuyen,ngay,trangThai,thoiGianKhoiHanh,thoiGianDen,bienSoXe)==1){
            JOptionPane.showMessageDialog(this, "Xe nay vao thoi diem nay khong the phan cong");
            return;
        }
        if(maCa!=""){
            try{
                PreparedStatement ps= ketNoi.prepareStatement("insert into CaLamViec values(?,?,?,?,?,?,?)");
                ps.setString(1,maCa );
                ps.setString(2,maCX );
                ps.setString(3,maNVLX );
                ps.setString(4,maNVKV );
                ps.setString(5,bienSoXe );
                ps.setString(6,thoiGianKhoiHanh );
                ps.setString(7,thoiGianDen );
                ps.executeUpdate();
            }catch(SQLException e){
                System.out.println("loi luu them ca lam viec"+e.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(this, "Khong duoc chua thong tin rong!");
        }
        layCaLamViec();
        layChuyenXeChuaPhanCong();
    }//GEN-LAST:event_btnThemActionPerformed

    private void jComboBoxCLVCXItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxCLVCXItemStateChanged
        // TODO add your handling code here:
        String maChuyenXe=(String) jComboBoxCLVCX.getSelectedItem();
        if(kTChuyenXeDaDuocPhanCong(maChuyenXe)==1){
            Connection ketNoi=Connect.layKetNoi();
            try{
                PreparedStatement ps= ketNoi.prepareStatement("select BienSoXe from CaLamViec where MaChuyenXe='"+maChuyenXe+"'");
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    jComboBoxCLVBS.setSelectedItem(rs.getString(1));
                    //jComboBoxCLVBS.enable(false);
                }
            }catch(SQLException e){
                System.out.println("loi kt chuyen xe da duoc phan cong");
            }
            
        }
    }//GEN-LAST:event_jComboBoxCLVCXItemStateChanged

    private void btnXuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileActionPerformed
       XuatFileCaLamViec caLV= new XuatFileCaLamViec();
       caLV.setVisible(true);
    }//GEN-LAST:event_btnXuatFileActionPerformed

    private void jTableChuyenXeChuaDuocPhanCongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableChuyenXeChuaDuocPhanCongMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model= (DefaultTableModel)jTableChuyenXeChuaDuocPhanCong.getModel();
        int selectedRow = jTableChuyenXeChuaDuocPhanCong.getSelectedRow();
        
        jComboBoxCLVCX.setSelectedItem(model.getValueAt(selectedRow, 0).toString());
    }//GEN-LAST:event_jTableChuyenXeChuaDuocPhanCongMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        String maCa=jTextFieldCLVMaCa.getText().trim();
        if(maCa.isEmpty()){
            JOptionPane.showMessageDialog(this, "Ma ca trong");
            return;
        }
        //xoa ca nay
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("delete from CaLamViec where MaCa='"+maCa+"'");
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("loi xoa ca lam viec de sua"+e.getMessage());
        }
        //them ca moi
        String maCX=(String) jComboBoxCLVCX.getSelectedItem();
        String trangThai="";
        String ngay="";
        String tuyen="";
        
        String []thongTin=maCX.split("-");
        ngay=thongTin[0];
        tuyen=thongTin[2];
        trangThai=thongTin[3];

        String maNVLX=(String) jComboBoxCLVNVLX.getSelectedItem();
        String maNVKV=(String) jComboBoxCLVNVKV.getSelectedItem();
        String bienSoXe=(String) jComboBoxCLVBS.getSelectedItem();
        String thoiGianKhoiHanh=thongTin[1];
        String thoiGianDen="";
        if(thoiGianKhoiHanh.equals("5h")){
            thoiGianDen="7h";
        }
        if(thoiGianKhoiHanh.equals("7h")){
            thoiGianDen="9h";
        }
        if(thoiGianKhoiHanh.equals("9h")){
            thoiGianDen="11h";
        }
        if(thoiGianKhoiHanh.equals("11h")){
            thoiGianDen="13h";
        }
        if(thoiGianKhoiHanh.equals("13h")){
            thoiGianDen="15h";
        }
        if(thoiGianKhoiHanh.equals("15h")){
            thoiGianDen="17h";
        }
        if(thoiGianKhoiHanh.equals("17h")){
            thoiGianDen="19h";
        }
        if(thoiGianKhoiHanh.equals("19h")){
            thoiGianDen="21h";
        }
        if(kTTrungTuyenNgayTTTGNVLX(tuyen,ngay,trangThai,thoiGianKhoiHanh,thoiGianDen,maNVLX)==1){
            JOptionPane.showMessageDialog(this, "Nhan vien lai xe nay thoi diem nay khong the phan cong");
            return;
        }
        if(kTTrungTuyenNgayTTTGNVKV(tuyen,ngay,trangThai,thoiGianKhoiHanh,thoiGianDen,maNVKV)==1){
            JOptionPane.showMessageDialog(this, "Nhan vien kiem ve nay thoi diem nay khong the phan cong");
            return;
        }
        if(kTTrungTuyenNgayTTTGXe(tuyen,ngay,trangThai,thoiGianKhoiHanh,thoiGianDen,bienSoXe)==1){
            JOptionPane.showMessageDialog(this, "Xe nay vao thoi diem nay khong the phan cong");
            return;
        }
        if(maCa!=""){
            try{
                PreparedStatement ps= ketNoi.prepareStatement("insert into CaLamViec values(?,?,?,?,?,?,?)");
                ps.setString(1,maCa );
                ps.setString(2,maCX );
                ps.setString(3,maNVLX );
                ps.setString(4,maNVKV );
                ps.setString(5,bienSoXe );
                ps.setString(6,thoiGianKhoiHanh );
                ps.setString(7,thoiGianDen );
                ps.executeUpdate();
            }catch(SQLException e){
                System.out.println("loi luu them ca lam viec"+e.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(this, "Khong duoc chua thong tin rong!");
        }
        layCaLamViec();
        layChuyenXeChuaPhanCong();
    }//GEN-LAST:event_btnSuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXuatFile;
    private javax.swing.JComboBox<String> jComboBoxCLVBS;
    private javax.swing.JComboBox<String> jComboBoxCLVCX;
    private javax.swing.JComboBox<String> jComboBoxCLVNVKV;
    private javax.swing.JComboBox<String> jComboBoxCLVNVLX;
    private javax.swing.JComboBox<String> jComboBoxTKCaLamViec;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableCaLamViec;
    private javax.swing.JTable jTableChuyenXeChuaDuocPhanCong;
    private javax.swing.JTextField jTextFieldCLVMaCa;
    private javax.swing.JTextField jTextFieldTKCaLamViec;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenTK;
    // End of variables declaration//GEN-END:variables
}
