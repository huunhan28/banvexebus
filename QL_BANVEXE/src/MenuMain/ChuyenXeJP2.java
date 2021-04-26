/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuMain;
import Controller.Connect;
import NhanVien.NhanVienQL;
import NhanVien.NhanVienDAO;
import ChuyenXe.*;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Huu Nhan
 */
public class ChuyenXeJP2 extends javax.swing.JPanel {

    /**
     * Creates new form ChuyenXeJP2
     */
    public ChuyenXeJP2(String taiKhoan) {
        initComponents();
        layThongTinChuyenXe();
        //        layThongtinTKSuDung(taiKhoan);
        layTuyen();
        
        JTextFieldDateEditor editor = (JTextFieldDateEditor) txtDate.getDateEditor();
        editor.setEditable(false);
    }
    void layTuyen(){
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select MaTuyen from Tuyen");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                jComboBoxTuyen.addItem(rs.getString("MaTuyen"));
            }
        }catch(SQLException e){
            System.out.println("loi lay tuyen");
        }
    }
    void themChuyenXe(String maChuyen,String tuyen,int tongSoChoDat,Date ngayCuaVe,boolean trangThaiDi){
        Connection ketNoi = Connect.layKetNoi();
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
    public boolean checkHoTen(String hoTenB) {
        for (int i = 0; i < hoTenB.length(); i++) {
            if (hoTenB.codePointAt(i) >= 48 && hoTenB.codePointAt(i) <= 57) {
                return false;
            }
        }
        return true;
    }

//    public void layThongtinTKSuDung(String taiKhoan) {
//
//        txtTenTK.setText(taiKhoan);
//        Connection ketNoi = Connect.layKetNoi();
//        try {
//            PreparedStatement ps = ketNoi.prepareStatement("select TenKhachHang,MatKhau,TaiKhoan.SDT\n"
//                    + "from KhachHang,TaiKhoan \n"
//                    + "where TaiKhoan.SDT=KhachHang.SDT and KhachHang.TaiKhoan='" + taiKhoan + "'");
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                System.out.println("toi day roi");
//                txtHoTen.setText(rs.getString(1));
//                txtSDT.setText(rs.getString(3));
//                //jPasswordFieldSua.setText(rs.getString(2));
//            }
//        } catch (SQLException e) {
//            System.out.println("loi lay thong tin de in ");
//        }
//    }

    public void layThongTinChuyenXe() {
        DefaultTableModel dtm = (DefaultTableModel) Table_ChuyenXe.getModel();
//        dtm.setNumRows(0);
//        Vector list = null;
//        boolean trangThai=true;
//        try {
//            PreparedStatement ps = connect.prepareStatement("select * from ChuyenXe");
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                list = new Vector();
//
//                list.add(rs.getString(1));
//                list.add(rs.getString(2));
//                list.add(rs.getInt(3));
//                list.add(rs.getString(4));
//                trangThai=rs.getBoolean(5);
//                if(trangThai==true){
//                    list.add("Luot di");
//                }else{
//                    list.add("Luot ve");
//                }
//                
//                dtm.addRow(list);
//
//                Table_ChuyenXe.setModel(dtm);
//                rs.close();
//                ps.close();
//
//            }
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Lỗi tại lấy thông tin chuyến xe !");
//        }
        dtm.setNumRows(0);
        Connection ketNoi=Connect.layKetNoi();
        Vector vt;
        try {
            PreparedStatement ps=ketNoi.prepareStatement("select * from ChuyenXe");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                vt= new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3));
                vt.add(rs.getString(4));
                vt.add(rs.getString(5));
                
                dtm.addRow(vt);
                
            }
            Table_ChuyenXe.setModel(dtm);
            ps.close();
            rs.close();
            ketNoi.close();
        } catch (SQLException ex) {
            System.out.println("loi lay chuyen");
        }
    }

    public void loadThongTinChuyenXe() {

        try {

            if (!txtMaCXe.getText().trim().isEmpty()) {

                //layThongTinChuyenXe(txtMaCXe.getText().trim(), (String) jComboBoxTuyen.getSelectedItem(), txtTongSoChoDat.getText().trim(), txtDate.getDateFormatString().trim());
                JOptionPane.showMessageDialog(this, "Lưu chuyến xe thành công !");

            }
//            layThongTinChuyenXe(txtMaCXe.getText().trim(),jcbMaCa.getSelectedItem().toString().trim(),
//                    txtBienSoXe.getText().trim(),txtMaNV.getText().trim(),txtMaTuyen.getText().trim(),txtTenTuyen.getText().trim(),txtDate.getDateFormatString().trim(),txtTongSoChoDat.getText().trim(),txtGioKH.getText().trim(),
//                    txtGioKT.getText().trim(),txtChiTiet.getText().trim());
//         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi tai loadThongTinChuyenXe");
        }
    }

//    public void lamMoiHienThiThongTinChiTiet() {
//        txtMaCXe.setText("null");
//        txtMaTuyen.setText("null");
//        txtBienSoXe.setText("null");
//        txtGioKH.setText("null");
//        txtGioKT.setText("null");
//        txtTenTuyen.setText("");
//    }

    public void themChuyenXe(String MaTuyen, String Ngay, String tongSoChoDat) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        String date = sdf.format(txtDate.getDate());
        Connection connect = Connect.layKetNoi();
        String sql = "insert into ChuyenXe(MaChuyenXe,MaTuyen,Ngay,TongSoChoDat) values(?,?,?,?)";
        PreparedStatement ps = connect.prepareStatement(sql);
        ps.executeUpdate();
        String maCX = "";
        ps = connect.prepareStatement("select top 1 MaChuyenXe from NHANVIEN order by MaChuyenXe desc ");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            maCX = rs.getString("MaChuyenXe");
        }
        JOptionPane.showMessageDialog(this, "Thêm thành công, Chuyen Xe mới có mã số là " + maCX);
        rs.close();
        ps.close();
    }

    public void capNhatChuyenXe(String MaCXe) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        String date = sdf.format(txtDate.getDate());

        Connection connect = Connect.layKetNoi();

        String sql = "update  ChuyenXe set MaTuyen=?,TongSoChoDat=?,Ngay=? " + "where MaChuyenXe=?";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);

            ps.setString(4, txtMaCXe.getText());
            ps.setString(1, (String) jComboBoxTuyen.getSelectedItem());
            ps.setString(2, txtTongSoChoDat.getText());
            ps.setString(3, date);
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi tại  CapNhatChuyenXe()");
            //Logger.getLogger(ChuyenXeJP.class.getName()).log(Level.SEVERE , null, e);
        }
    }

    public void xoaChuyenXe(String MaCXe) {
        Connection connect = Connect.layKetNoi();

        String sql = "delete from ChuyenXe  " + "where MaChuyenXe=?";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, MaCXe);

            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi tại  XoaChuyenXe()");
        }
    }

    public int kTMaCXeTonTai(String maCX) {
        int tonTai = 0;
        Connection ketNoi = (Connection) Connect.layKetNoi();
        String sql = "select * from ChuyenXe where MaChuyenXe= ? ";

        PreparedStatement ps;
        try {
            ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maCX);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tonTai = 1;
            } else {
                tonTai = 0;
            }
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(this, " ");
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

        jPanel8 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jbtXuatFile = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_ChuyenXe = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtSDT = new javax.swing.JTextField();
        txtTenTK = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtChucVu = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtMaCXe = new javax.swing.JTextField();
        txtTongSoChoDat = new javax.swing.JTextField();
        txtDate = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        txtGioKT = new javax.swing.JTextField();
        jbtCapNhat = new javax.swing.JButton();
        jbtXoa = new javax.swing.JButton();
        jbtLuu = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxGioKH = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jComboBoxTuyen = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("DANH SÁCH CHUYẾN XE");

        jbtXuatFile.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtXuatFile.setForeground(new java.awt.Color(0, 153, 0));
        jbtXuatFile.setText("XUẤT FILE EXCEL");
        jbtXuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtXuatFileActionPerformed(evt);
            }
        });

        Table_ChuyenXe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã C.Xe", "Mã Tuyến ", "Tổng Số Chỗ", "Ngày", "Trạng thái đi"
            }
        ));
        jScrollPane2.setViewportView(Table_ChuyenXe);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jbtXuatFile)
                .addGap(512, 512, 512))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel18))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1091, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 122, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jbtXuatFile, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(54, 33, 89));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("THÔNG TIN TÀI KHOẢN SỬ DỤNG");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("SĐT:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Tên TK:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Họ Tên:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Chức Vụ:");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setText("THOÁT ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(38, 38, 38))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(25, 25, 25)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(24, 24, 24)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSDT)
                                    .addComponent(txtTenTK)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txtChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4))
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTenTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(54, 33, 89));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THÔNG TIN CHUYẾN XE");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Mã C.Xe:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Mã Tuyến:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Tổng Số Chỗ Ngồi:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Ngày Chạy:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Giờ KH:");

        txtMaCXe.setEnabled(false);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Giờ KT:");

        txtGioKT.setEnabled(false);

        jbtCapNhat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtCapNhat.setForeground(new java.awt.Color(0, 153, 0));
        jbtCapNhat.setText("CẬP NHẬT");
        jbtCapNhat.setEnabled(false);
        jbtCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCapNhatActionPerformed(evt);
            }
        });

        jbtXoa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtXoa.setForeground(new java.awt.Color(255, 51, 0));
        jbtXoa.setText("XOÁ");
        jbtXoa.setEnabled(false);
        jbtXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtXoaActionPerformed(evt);
            }
        });

        jbtLuu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtLuu.setForeground(new java.awt.Color(0, 102, 204));
        jbtLuu.setText("THÊM");
        jbtLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtLuuActionPerformed(evt);
            }
        });

        jLabel9.setText("Mã chuyến xe được tạo tự động");

        jComboBoxGioKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5h", "7h", "9h", "11h", "13h", "15h", "17", "19h" }));

        jLabel10.setText("Giờ kết thúc được tạo tự động = GiờKH+2");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Trạng thái đi:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Luot di", "Luot Ve" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(105, 105, 105)
                        .addComponent(jbtCapNhat)
                        .addGap(87, 87, 87)
                        .addComponent(jbtXoa))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel11))
                                .addGap(51, 51, 51)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtMaCXe, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jComboBoxTuyen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(108, 108, 108))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jbtLuu)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTongSoChoDat, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(22, 22, 22)
                                .addComponent(txtGioKT, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(20, 20, 20)
                                .addComponent(jComboBoxGioKH, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 134, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaCXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel16)
                    .addComponent(jComboBoxGioKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBoxTuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtGioKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtTongSoChoDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtCapNhat)
                    .addComponent(jbtXoa)
                    .addComponent(jbtLuu))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtXuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtXuatFileActionPerformed
        try {
            SimpleDateFormat date = new SimpleDateFormat("yyy", Locale.getDefault());
            String nam = date.format(txtDate.getDate());
            date = new SimpleDateFormat("MM", Locale.getDefault());
            String thang = date.format(txtDate.getDate());

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Chuyến Xe");
            int rownum = 0;
            Cell cell;
            Row row = sheet.createRow(rownum);
            String[] tenMuc = new String[11];
            tenMuc[0] = "Mã Chuyến Xe";
            tenMuc[1] = "Mã Ca";
            tenMuc[2] = "Biển Số Xe";
            tenMuc[3] = "Mã NV";
            tenMuc[4] = "Ngày Chạy";
            tenMuc[5] = "Tổng số chỗ đặt";
            tenMuc[6] = "Giờ KH";
            tenMuc[7] = "Giờ KT";
            tenMuc[8] = "Mã Tuyến";
            tenMuc[9] = "Tên Tuyến ";
            tenMuc[10] = "Chi tiết";

            for (int i = 0; i < 11; i++) {
                cell = row.createCell(i, CellType.STRING);
                cell.setCellValue(tenMuc[i]);
            }
            Connection connect = Connect.layKetNoi();
            try {
                String sql = "select MaChuyenXe,MaCa,MaTuyen,BienSoXe,MaNV,NgayDi,TongSoChoDat,GioKH,GioKT,ChiTiet from "
                + "ChuyenXe,Tuyen,NhanVien,Xe,CaLamViec where ChuyenXe.MaChuyenXe=CaLamViec.MaChuyenXe and "
                + "CaLamViec.BienSoXe=Xe.BienSoXe and ChuyenXe.MaTuyen=Tuyen.MaTuyen";

                PreparedStatement ps = connect.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    rownum++;
                    tenMuc[0] = rs.getString("MaChuyenXe").trim();
                    tenMuc[1] = rs.getString("MaTuyen").trim();
                    tenMuc[2] = rs.getString("MaNV").trim();
                    tenMuc[3] = rs.getString("BienSoXe").trim();
                    tenMuc[4] = rs.getString("MaTuyen").trim();
                    tenMuc[5] = rs.getString("TenTuyen").trim();
                    tenMuc[6] = rs.getString("Ngay");
                    tenMuc[7] = rs.getString("TongSoChoDat");
                    tenMuc[8] = rs.getString("GioKH");
                    tenMuc[9] = rs.getString("GioKT");
                    tenMuc[10] = rs.getString("ChiTiet");
                }
                rs.close();
                ps.close();
            } catch (Exception e) {

            }
            try {
                File file = new File("C:\\Users\\ADMIN\\Desktop\\DO_AN_CNPM/Chuyen Xe_" + "_" + thang + "_" + nam + ".xls");
                file.getParentFile().mkdirs();

                FileOutputStream outFile = new FileOutputStream(file);
                workbook.write(outFile);
                System.out.println("Created file: " + file.getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Khoi tao danh sach thanh cong tai dia chi: " + file.getAbsolutePath());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Khoi tao file that bai!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thoi gian khong hop le!! Vui long chon thoi gian");
        }
    }//GEN-LAST:event_jbtXuatFileActionPerformed

    private void jbtCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCapNhatActionPerformed
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
            String date = sdf.format(txtDate.getDate());
            
            int n = JOptionPane.showConfirmDialog(this, "Chac chan muon luu?", "LUU", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {

                capNhatChuyenXe(txtMaCXe.getText().trim());
                loadThongTinChuyenXe();
            }
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ngay khong hop le vui long chon lai");
        }

    }//GEN-LAST:event_jbtCapNhatActionPerformed

    private void jbtXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtXoaActionPerformed

        if (kTMaCXeTonTai(txtMaCXe.getText().trim()) == 0) {
            JOptionPane.showMessageDialog(this, "MaCXe khong ton tai");
            return;
        }

        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá tài khoản này không !") == JOptionPane.NO_OPTION) {
            return;
        }
        try {

            xoaChuyenXe(txtMaCXe.getText());

            JOptionPane.showMessageDialog(this, "Tai khoan đã được xoá khỏi csdl !  ");
            loadThongTinChuyenXe();
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }//GEN-LAST:event_jbtXoaActionPerformed

    private void jbtLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtLuuActionPerformed

        String gioKH=(String) jComboBoxGioKH.getSelectedItem();
        String gioKT="";
        String tuyen=(String) jComboBoxTuyen.getSelectedItem();
        if(txtDate.getDateFormatString().trim().isEmpty()==true){
            JOptionPane.showMessageDialog(this, "Ngay khong duoc trong");
            return;
        }
        if(txtTongSoChoDat.getText().trim().isEmpty()==true){
            JOptionPane.showMessageDialog(this, "Tong so cho ngoi khong duoc trong");
            return;
        }
        int tongSoChoDat=Integer.parseInt(txtTongSoChoDat.getText());
        
        String trangThai=(String) jComboBox1.getSelectedItem();
        boolean trangThaiDi=true;
        String trangThai1="";
        if(trangThai.equals("Lượt đi")){
            trangThaiDi=true;
            trangThai1="1";
        }else{
            trangThaiDi=false;
            trangThai1="0";
        }
        
        if(gioKH.equals("5h")){
            gioKT="7h";
        }
        if(gioKH.equals("7h")){
            gioKT="9h";
        }
        if(gioKH.equals("9h")){
            gioKT="11h";
        }
        if(gioKH.equals("11h")){
            gioKT="13h";
        }
        if(gioKH.equals("13h")){
            gioKT="15h";
        }
        if(gioKH.equals("15h")){
            gioKT="17h";
        }
        if(gioKH.equals("17h")){
            gioKT="19h";
        }
        if(gioKH.equals("19h")){
            gioKT="21h";
        }
        //--------tao ma chuyen xe tu dong---
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String date=sdf.format(txtDate.getDate());
        System.out.println(date);
        String year=date.substring(2, 4);
        String month=date.substring(5, 7);
        String day=date.substring(8, 10);
        System.out.println(year+"-"+month+"-"+day);

        String maChuyen=day+month+year+"-"+gioKH+"-"+tuyen+"-"+trangThai1;
        System.out.println(maChuyen);
        if(kTMaCXeTonTai(maChuyen)==1){
            JOptionPane.showMessageDialog(this, "Chuyen xe nay da ton tai");
            return;
        }
        //-----------lay ngay-----------
        java.sql.Date ngayCuaVe = null;
        try {
            String ngay1 = new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate());
            java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(ngay1);
            ngayCuaVe = new java.sql.Date(tmp.getTime());
        }
        catch (Exception e) {
            System.out.println("loi lay ngay cua ve");
            e.printStackTrace();
        }
        int n = JOptionPane.showConfirmDialog(this, "Chac chan muon luu?", "LUU", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            themChuyenXe(maChuyen, tuyen, tongSoChoDat, ngayCuaVe, trangThaiDi);
        }
        layThongTinChuyenXe();
    }//GEN-LAST:event_jbtLuuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table_ChuyenXe;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxGioKH;
    private javax.swing.JComboBox<String> jComboBoxTuyen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtCapNhat;
    private javax.swing.JButton jbtLuu;
    private javax.swing.JButton jbtXoa;
    private javax.swing.JButton jbtXuatFile;
    private javax.swing.JTextField txtChucVu;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JTextField txtGioKT;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaCXe;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenTK;
    private javax.swing.JTextField txtTongSoChoDat;
    // End of variables declaration//GEN-END:variables
}
