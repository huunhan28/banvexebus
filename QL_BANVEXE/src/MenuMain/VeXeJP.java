/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuMain;

import Controller.Connect;
import NhanVien.NhanVienQL;
import NhanVien.NhanVienDAO;
import java.awt.Color;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VeXeJP extends javax.swing.JPanel {

    /**
     * Creates new form QLVe
     */
    public VeXeJP(String taiKhoan) {
        initComponents();

        layTuyen();
        layLoaiVe();
        layVe();

    }
    public DefaultTableModel dtm;

    void layVe() {
        dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setNumRows(0);
        Connection ketNoi = Connect.layKetNoi();
        Vector vt;
        try {
            PreparedStatement ps = ketNoi.prepareStatement("select * from Ve ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vt = new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3));
                vt.add(rs.getString(4));
                vt.add(rs.getString(5));
                vt.add(rs.getString(6));
                vt.add(rs.getString(7));

                dtm.addRow(vt);

            }
            jTable1.setModel(dtm);
            ps.close();
            rs.close();
            ketNoi.close();
        } catch (SQLException ex) {
            System.out.println("loi lay ve admin");
        }
    }

    void layKhachHang(String taiKhoan) {

        txtTenTK.setText(taiKhoan);
        Connection ketNoi = Connect.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("select TenKhachHang,MatKhau,TaiKhoan.SDT\n"
                    + "from KhachHang,TaiKhoan \n"
                    + "where TaiKhoan.SDT=KhachHang.SDT and KhachHang.TaiKhoan='" + taiKhoan + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("toi day roi");
                txtHoTen.setText(rs.getString(1));
                txtSDT.setText(rs.getString(3));
                //jPasswordFieldSua.setText(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("loi lay thong tin de in ");
        }

    }
//    public void layThongTinVe(String maVe, int soChoDat, String maChuyenXe, String noiDi, String noiDen, String taiKhoan, String maLoaiVe){
//        Connection connect =Connect.layKetNoi();
//        try{
//            PreparedStatement ps =connect.prepareCall("select MaVe,MaChuyenXe,MaLoaiVe,TaiKhoan,NoiDi,NoiDen,SoChoDat from"+
//                    "TaiKhoan,Ve,ChuyenXe,LoaiTaiKhoan where TaiKhoan.TaiKhoan= Ve.TaiKhoan and Ve.MaChuyenXe=ChuyenXe.MaChuyenXe");
//            ResultSet rs =ps.executeQuery();
//            if(rs.next()){
//                txtMaVe.setText(rs.getString ("MaVe"));
//                txtMaCXe.setText(rs.getString("MaChuyenXe"));
//                txtTaiKhoan.setText(rs.getString("TaiKhoan"));
//                txtSoChoDat.setText(rs.getString("MaLoaiVe"));
//                jComboBoxNoiDi.setSelectedItem(rs.getString("NoiDi"));
//                jComboBoxNoiDen.setSelectedItem(rs.getString("NoiDen"));
//               
//            }
//            rs.close();
//            ps.close();
//                }
//        catch(Exception e){
//                System.out.println("PACKAGE KETOAN - layThongTinMucTP()");
//            
//            JOptionPane.showMessageDialog(this," Lỗi tại lấy thông tin của vé ! ");
//                }
//    }

    boolean ktTrangThaiDi(String tuyen, String noiDi, String noiDen) {
        int di = 0, den = 0;
        Connection ketNoi = Connect.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("select ThoiGianTuyenDenTram from TuyenTram where Tuyen='" + tuyen + "' and Tram='" + noiDi + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                di = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("loi lay ThoiGianTuyenDenTram");
        }
        try {
            PreparedStatement ps = ketNoi.prepareStatement("select ThoiGianTuyenDenTram from TuyenTram where Tuyen='" + tuyen + "' and Tram='" + noiDen + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                den = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("loi lay ThoiGianTuyenDenTram");
        }
        if (den - di > 0) {
            return true;
        } else {
            return false;
        }

    }

    void xoaVe(int maVe) {
        //them ve
        Connection ketNoi = Connect.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("delete from Ve where MaVe='" + maVe + "'");
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("loi xoa ve" + e.getMessage());
            return;
        }

    }

    void muaVe(String maChuyen, int maVe, int soChoDat, String loaiVe, String NoiDi, String NoiDen, String taiKhoan) {
        //them ve
        Connection ketNoi = Connect.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("insert into Ve values(?,?,?,?,?,?,?)");
            ps.setInt(1, maVe);
            ps.setString(2, maChuyen);
            ps.setInt(3, soChoDat);
            ps.setString(4, loaiVe);
            ps.setString(5, NoiDi);
            ps.setString(6, NoiDen);
            ps.setString(7, taiKhoan);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("loi luu ve" + e.getMessage());
            return;
        }

    }

    void layTuyen() {
        Connection ketNoi = Connect.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("select MaTuyen from Tuyen");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                jComboBoxTuyen.addItem(rs.getString("MaTuyen"));
            }
        } catch (SQLException e) {
            System.out.println("loi lay tuyen admin");
        }
    }

    void layLoaiVe() {
        Connection ketNoi = Connect.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("select TenLoaiVe from LoaiVe");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                jComboBoxLoaiVe.addItem(rs.getString("TenLoaiVe"));
            }
        } catch (SQLException e) {
            System.out.println("loi lay loai ve admin");
        }
    }

    java.util.Date layNgayTuMaVe(String maVe) {
        java.util.Date date = null;
        Connection ketNoi = Connect.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("select Ngay from Ve,ChuyenXe where Ve.MaChuyenXe=ChuyenXe.MaChuyenXe and MaVe='" + maVe + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                date = rs.getDate(1);
                System.out.println(date);
            }
        } catch (SQLException e) {
            System.out.println("loi lay ngay tu ma ve admin");
        }
        return date;

    }

    String layMaLoaiVeTuTenLoaiVe(String tenLoaiVe) {
        String maLV = "";
        Connection ketNoi = Connect.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("select MaLoaiVe from LoaiVe where TenLoaiVe='" + tenLoaiVe + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                maLV = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("loi lay ma loai ve");
        }
        return maLV;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldTuyenCu = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldTuyenMoi = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldNoiDiCu = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldNoiDiMoi = new javax.swing.JTextField();
        jTextFieldNoiDenCu = new javax.swing.JTextField();
        jTextFieldNoiDenMoi = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextFieldLoaiVeMoi = new javax.swing.JTextField();
        jTextFieldLoaiVeCu = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTextFieldSoChoDatCu = new javax.swing.JTextField();
        jTextFieldSoChoDatMoi = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextFieldNgayMoi = new javax.swing.JTextField();
        jTextFieldNgayCu = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jTextFieldGioMoi = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextFieldGioCu = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jTextFieldTrangThaiMoi = new javax.swing.JTextField();
        jTextFieldTranThaiCu = new javax.swing.JTextField();
        jButtonThayDoi = new javax.swing.JButton();
        jButtonHuy = new javax.swing.JButton();
        jLabelMaVe = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabelMaChuyen = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabelTaiKhoan = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtTenTK = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        btnThoat = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jTextField14 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnChinhSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        txtMaVe = new javax.swing.JTextField();
        txtSoChoDat = new javax.swing.JTextField();
        jComboBoxLoaiVe = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButtonThem = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTaiKhoan = new javax.swing.JTextField();
        jComboBoxGioKhoiHanh = new javax.swing.JComboBox<>();
        jDateChooserNgayKH = new com.toedter.calendar.JDateChooser();
        jComboBoxTuyen = new javax.swing.JComboBox<>();
        jComboBoxNoiDi = new javax.swing.JComboBox<>();
        jComboBoxNoiDen = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jLabel9.setText("Mã vé:");

        jLabel15.setText("Tuyến");

        jTextFieldTuyenCu.setText("jTextField1");

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/change_20px.png"))); // NOI18N

        jTextFieldTuyenMoi.setText("jTextField2");

        jLabel17.setText("Nơi đi");

        jTextFieldNoiDiCu.setText("jTextField1");

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/change_20px.png"))); // NOI18N

        jTextFieldNoiDiMoi.setText("jTextField2");

        jTextFieldNoiDenCu.setText("jTextField1");

        jTextFieldNoiDenMoi.setText("jTextField2");

        jLabel20.setText("Nơi đến");

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/change_20px.png"))); // NOI18N

        jTextFieldLoaiVeMoi.setText("jTextField2");

        jTextFieldLoaiVeCu.setText("jTextField1");

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/change_20px.png"))); // NOI18N

        jLabel26.setText("Loại vé");

        jTextFieldSoChoDatCu.setText("jTextField1");

        jTextFieldSoChoDatMoi.setText("jTextField2");

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/change_20px.png"))); // NOI18N

        jLabel28.setText("Số chỗ đặt");

        jLabel29.setText("Ngày");

        jTextFieldNgayMoi.setText("jTextField2");

        jTextFieldNgayCu.setText("jTextField1");

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/change_20px.png"))); // NOI18N

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/change_20px.png"))); // NOI18N

        jTextFieldGioMoi.setText("jTextField2");

        jLabel32.setText("Giờ");

        jTextFieldGioCu.setText("jTextField1");

        jLabel33.setText("Trạng thái");

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/change_20px.png"))); // NOI18N

        jTextFieldTrangThaiMoi.setText("jTextField2");

        jTextFieldTranThaiCu.setText("jTextField1");

        jButtonThayDoi.setText("Thay đổi");
        jButtonThayDoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThayDoiActionPerformed(evt);
            }
        });

        jButtonHuy.setText("Hủy");
        jButtonHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHuyActionPerformed(evt);
            }
        });

        jLabel35.setText("Mã chuyến:");

        jLabel36.setText("Tài khoản:");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldTuyenCu, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel16))
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNoiDiCu, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel18))
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNoiDenCu, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel24))
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldLoaiVeCu, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel25))
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldSoChoDatCu, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel27))
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNgayCu, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel30))
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldGioCu, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel31))
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jDialog1Layout.createSequentialGroup()
                                        .addComponent(jTextFieldTranThaiCu, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel34))
                                    .addComponent(jButtonThayDoi, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldTuyenMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNoiDiMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNoiDenMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldLoaiVeMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSoChoDatMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNgayMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldGioMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTrangThaiMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel9)
                            .addComponent(jLabel36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelMaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMaChuyen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabelMaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelMaChuyen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(jTextFieldTuyenCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldTuyenMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(jTextFieldNoiDiCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldNoiDiMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(jTextFieldNoiDenCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldNoiDenMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel26)
                        .addComponent(jTextFieldLoaiVeCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldLoaiVeMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28)
                        .addComponent(jTextFieldSoChoDatCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldSoChoDatMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel29)
                        .addComponent(jTextFieldNgayCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldNgayMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(jTextFieldGioCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldGioMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel33)
                        .addComponent(jTextFieldTranThaiCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldTrangThaiMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonThayDoi)
                    .addComponent(jButtonHuy))
                .addGap(36, 36, 36))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(54, 33, 89));

        jLabel2.setBackground(new java.awt.Color(0, 102, 153));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("THÔNG TIN TÀI KHOẢN");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("SDT:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Tên TK:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Họ Tên:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Chức Vụ:");

        btnThoat.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnThoat.setForeground(new java.awt.Color(255, 0, 0));
        btnThoat.setText("THOÁT");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThoat))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(35, 35, 35)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtTenTK, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnThoat)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTenTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Vé", "Mã C.Xe", "Số Chỗ Đặt", "Mã loại vé", " Nơi Đi", "Nơi Đến ", "Tài Khoản"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 204, 0));
        jButton6.setText("XUẤT FILE EXCEL");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("DANH SÁCH VÉ XE");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(522, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(398, 398, 398))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addGap(5, 5, 5))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Mã Vé");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Loại Vé:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Nơi đi :");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Nơi Đến: ");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Số Chỗ Đặt:");

        btnChinhSua.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnChinhSua.setForeground(new java.awt.Color(0, 0, 255));
        btnChinhSua.setText("CHỈNH SỬA ");
        btnChinhSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChinhSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 0, 0));
        btnXoa.setText("XOÁ ");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("NgàyKH:");

        jPanel7.setBackground(new java.awt.Color(54, 33, 89));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("THÔNG TIN VÉ XE");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Tuyến :");

        jButtonThem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonThem.setForeground(new java.awt.Color(0, 153, 0));
        jButtonThem.setText("THÊM");
        jButtonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 204, 204));
        jButton2.setText("HIỆN THỊ DANH SÁCH");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Giờ KH:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Tài Khoản:");

        txtTaiKhoan.setEnabled(false);

        jComboBoxGioKhoiHanh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5h", "7h", "9h", "11h", "13h", "15h", "17h", "19h", " " }));

        jComboBoxTuyen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxTuyenItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel12))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtMaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel22))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jComboBoxNoiDi, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel13))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(32, 32, 32)
                                        .addComponent(jComboBoxLoaiVe, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel10))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel21))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtSoChoDat)
                                            .addComponent(jDateChooserNgayKH, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel23)))
                                .addGap(19, 19, 19)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxGioKhoiHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxTuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxNoiDen, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnChinhSua)
                                .addGap(51, 51, 51)
                                .addComponent(btnXoa)
                                .addGap(46, 46, 46)
                                .addComponent(jButtonThem)
                                .addGap(38, 38, 38)
                                .addComponent(jButton2)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtMaVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jComboBoxTuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jComboBoxNoiDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jComboBoxNoiDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBoxLoaiVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoChoDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jComboBoxGioKhoiHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addComponent(jDateChooserNgayKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChinhSua, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonThem)
                    .addComponent(jButton2))
                .addGap(112, 112, 112)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        jPanel6.setBackground(new java.awt.Color(54, 33, 89));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ VÉ XE ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1071, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnChinhSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChinhSuaActionPerformed
        String maVe = txtMaVe.getText().trim();
        //============lay thong tin cu==========
        String maChuyenXeCu = "", tuyenCu, noiDiCu = "", noiDenCu = "", loaiVeCu = "", gioCu = "", taiKhoan = "";
        int soChoCu = 0;
        Date ngayCu = null;
        boolean trangThaiDiCu;
        int tonTai = 0;
        Connection connect = Connect.layKetNoi();
        try {
            PreparedStatement ps = connect.prepareStatement("select V.MaChuyenXe,SoChoDat,TenLoaiVe,NoiDi,NoiDen,Ngay,TaiKhoan\n"
                    + "from Ve as V,ChuyenXe as C,LoaiVe as L\n"
                    + "where V.MaChuyenXe=C.MaChuyenXe and V.MaLoaiVe=L.MaLoaiVe and  MaVe='" + maVe + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tonTai = 1;
                maChuyenXeCu = rs.getString(1);
                soChoCu = rs.getInt(2);
                loaiVeCu = rs.getString(3);
                noiDiCu = rs.getString(4);
                noiDenCu = rs.getString(5);
                ngayCu = rs.getDate(6);
                System.out.println("moi lay ra"+ngayCu);
                taiKhoan = rs.getString(7);

            }
        } catch (SQLException e) {
            System.out.println("loi lay thong tin ve cu");
        }
        if (tonTai == 0) {
            JOptionPane.showMessageDialog(this, "Ma ve khong ton tai");
            return;
        }
        String[] thongTin = maChuyenXeCu.split("-");
        gioCu = thongTin[1];
        tuyenCu = thongTin[2];
        if (thongTin[3] == "0") {
            trangThaiDiCu = false;
        } else {
            trangThaiDiCu = true;
        }
        //--------------set vao textfield--------------
        txtTaiKhoan.setText(taiKhoan);
        jLabelMaVe.setText(maVe);
        jLabelTaiKhoan.setText(taiKhoan);

        jTextFieldTuyenCu.setText(tuyenCu);
        jTextFieldNoiDiCu.setText(noiDiCu);
        jTextFieldNoiDenCu.setText(noiDenCu);
        jTextFieldLoaiVeCu.setText(loaiVeCu);
        jTextFieldSoChoDatCu.setText(soChoCu + "");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(ngayCu);
        c.add(Calendar.DATE, 2);
        String strNgay = sdf.format(c.getTime());
        System.out.println(strNgay);
        
        jTextFieldNgayCu.setText(strNgay);
        jTextFieldGioCu.setText(gioCu);
        if (trangThaiDiCu == false) {
            jTextFieldTranThaiCu.setText("Luot ve");
        } else {
            jTextFieldTranThaiCu.setText("Luot di");
        }
        //------------------
        

        //--------------lay thong tin moi-------------------
        String tuyenMoi, noiDiMoi, noiDenMoi, loaiVeMoi, gioMoi;
        int soChoMoi;
        java.util.Date ngayMoi;
        boolean trangThaiDiMoi;

        tuyenMoi = (String) jComboBoxTuyen.getSelectedItem();
        noiDiMoi = (String) jComboBoxNoiDi.getSelectedItem();
        noiDenMoi = (String) jComboBoxNoiDen.getSelectedItem();
        loaiVeMoi = (String) jComboBoxLoaiVe.getSelectedItem();
        gioMoi = (String) jComboBoxGioKhoiHanh.getSelectedItem();
        soChoMoi = Integer.parseInt(txtSoChoDat.getText());

       
        String date = sdf.format(jDateChooserNgayKH.getDate());
        System.out.println(date);

        trangThaiDiMoi = ktTrangThaiDi(tuyenMoi, noiDiMoi, noiDenMoi);
        String trangThai = "";
        if (trangThaiDiMoi == true) {
            trangThai = "1";
        } else {
            trangThai = "0";
        }
        // lay ma chuyen
        String year = date.substring(2, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);
        System.out.println(year + "-" + month + "-" + day);

        String maChuyen = day + month + year + "-" + gioMoi + "-" + tuyenMoi + "-" + trangThai;
        System.out.println(maChuyen);

        //--------------set vao textfield--------------
        jLabelMaChuyen.setText(maChuyen);

        jTextFieldTuyenMoi.setText(tuyenMoi);
        jTextFieldNoiDiMoi.setText(noiDiMoi);
        jTextFieldNoiDenMoi.setText(noiDenMoi);
        jTextFieldLoaiVeMoi.setText(loaiVeMoi);
        jTextFieldSoChoDatMoi.setText(soChoMoi + "");
        jTextFieldNgayMoi.setText(date.toString());
        jTextFieldGioMoi.setText(gioMoi);
        if (trangThaiDiMoi == false) {
            jTextFieldTrangThaiMoi.setText("Luot ve");
        } else {
            jTextFieldTrangThaiMoi.setText("Luot di");
        }
        jDialog1.setSize(437, 511);
        jDialog1.setLocation(400, 300);
        jDialog1.setVisible(true);


    }//GEN-LAST:event_btnChinhSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int ret = JOptionPane.showConfirmDialog(this, "Ban chac chan muon xoa?", "Xac nhan", 0);

        if (ret == JOptionPane.CANCEL_OPTION) {
            return;
        } else {
            int maVe = Integer.parseInt(txtMaVe.getText().trim());
            int tonTai = 0;
            Connection connect = Connect.layKetNoi();
            try {
                PreparedStatement ps = connect.prepareStatement("select *\n"
                        + "from Ve \n"
                        + "where  MaVe='" + maVe + "'");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    tonTai = 1;
                }
            } catch (SQLException e) {
                System.out.println("loi lay thong tin ve cu");
            }
            if (tonTai == 0) {
                JOptionPane.showMessageDialog(this, "Ma ve khong ton tai");
                return;
            }
            xoaVe(maVe);
        }
        layVe();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThoatActionPerformed

    private void jButtonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButtonThemActionPerformed

    private void jButtonThayDoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThayDoiActionPerformed
        // TODO add your handling code here:
        int maVe = Integer.parseInt(jLabelMaVe.getText().trim());
        String maChuyen = jLabelMaChuyen.getText();
        String tuyen = jTextFieldTuyenMoi.getText();
        String noiDi = jTextFieldNoiDiMoi.getText();
        String noiDen = jTextFieldNoiDenMoi.getText();
        int soGhe = Integer.parseInt(jTextFieldSoChoDatMoi.getText());
        String loaiVe = jTextFieldLoaiVeMoi.getText();
        String taiKhoan = jLabelTaiKhoan.getText();
        //-------------------------
        String ngay = jTextFieldNgayMoi.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date ngayCuaVe = null;
        try {
            ngayCuaVe = sdf.parse(ngay);
        } catch (ParseException ex) {
            System.out.println("chuyen luu chuyen chua co admin");
        }
        String trangThaiDi1 = jTextFieldTrangThaiMoi.getText();
        boolean trangThaiDi = true;
        if (trangThaiDi1 == "0") {
            trangThaiDi = false;
        } else {
            trangThaiDi = true;
        }
        //===============================
        int tonTaiChuyenXe = 0;
        int tongSoChoDat = soGhe;
        Connection ketNoi = Connect.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("select MaChuyenXe,TongSoChoDat from ChuyenXe");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("MaChuyenXe").equalsIgnoreCase(maChuyen)) {
                    tongSoChoDat = rs.getInt("TongSoChoDat") + soGhe;
                    tonTaiChuyenXe = 1;
                }
            }
        } catch (SQLException e) {
            System.out.println("loi lay tuyen");
        }
        //---------chua ton tai chuyen xe
        if (tonTaiChuyenXe == 0) {// chua ton tai chuyen xe nay => tao chuyen xe
            try {
                PreparedStatement ps = ketNoi.prepareStatement("insert into ChuyenXe values(?,?,?,?,?)");
                ps.setString(1, maChuyen);
                ps.setString(2, tuyen);
                ps.setInt(3, tongSoChoDat);
                ps.setDate(4, new Date(ngayCuaVe.getTime()));
                ps.setBoolean(5, trangThaiDi);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("loi luu chuyen xe");
            }
        }

        xoaVe(maVe);
        String maLoaiVe = layMaLoaiVeTuTenLoaiVe(loaiVe);
        muaVe(maChuyen, maVe, soGhe, maLoaiVe, noiDi, noiDen, taiKhoan);

        jDialog1.setVisible(false);
        layVe();

    }//GEN-LAST:event_jButtonThayDoiActionPerformed

    private void jButtonHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHuyActionPerformed
        // TODO add your handling code here:

        jDialog1.setVisible(false);
    }//GEN-LAST:event_jButtonHuyActionPerformed

    private void jComboBoxTuyenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxTuyenItemStateChanged
        // TODO add your handling code here:
        jComboBoxNoiDi.removeAllItems();
        jComboBoxNoiDen.removeAllItems();
        Connection ketNoi = Connect.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("select Tram from TuyenTram where Tuyen='" + jComboBoxTuyen.getSelectedItem() + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                jComboBoxNoiDi.addItem(rs.getString("Tram"));
                jComboBoxNoiDen.addItem(rs.getString("Tram"));
            }
        } catch (SQLException e) {
            System.out.println("loi lay tram");
        }
    }//GEN-LAST:event_jComboBoxTuyenItemStateChanged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedRow = jTable1.getSelectedRow();

        txtMaVe.setText(model.getValueAt(selectedRow, 0).toString());
        txtMaVe.enable(false);
        String maCX = "";
        maCX = (model.getValueAt(selectedRow, 1).toString());
        String[] thongTin = maCX.split("-");

        txtSoChoDat.setText(model.getValueAt(selectedRow, 2).toString());
        jComboBoxTuyen.setSelectedItem(thongTin[2]);
        jComboBoxLoaiVe.setSelectedItem(model.getValueAt(selectedRow, 3).toString());
        jComboBoxNoiDi.setSelectedItem(model.getValueAt(selectedRow, 4).toString());
        jComboBoxNoiDen.setSelectedItem(model.getValueAt(selectedRow, 5).toString());
        txtTaiKhoan.setText(model.getValueAt(selectedRow, 6).toString());
        jComboBoxGioKhoiHanh.setSelectedItem(thongTin[1]);

//        java.util.Date ngay=layNgayTuMaVe(model.getValueAt(selectedRow, 0).toString());
//        jDateChooserNgayKH.setDate(ngay);
//        System.out.println(ngay);
        java.util.Date date = null;
        Connection ketNoi = Connect.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("select Ngay from Ve,ChuyenXe where Ve.MaChuyenXe=ChuyenXe.MaChuyenXe and MaVe='" + model.getValueAt(selectedRow, 0).toString() + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                date = rs.getDate(1);
                System.out.println(date);
            }
        } catch (SQLException e) {
            System.out.println("loi lay ngay tu ma ve admin");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 2);
        String strNgay = sdf.format(c.getTime());
        System.out.println(strNgay);

        try {
            jDateChooserNgayKH.setDate(sdf.parse(strNgay));
        } catch (ParseException ex) {
            System.out.println("loi chuyen lay ngay tu table");
        }
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChinhSua;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButtonHuy;
    private javax.swing.JButton jButtonThayDoi;
    private javax.swing.JButton jButtonThem;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBoxGioKhoiHanh;
    private javax.swing.JComboBox<String> jComboBoxLoaiVe;
    private javax.swing.JComboBox<String> jComboBoxNoiDen;
    private javax.swing.JComboBox<String> jComboBoxNoiDi;
    private javax.swing.JComboBox<String> jComboBoxTuyen;
    private com.toedter.calendar.JDateChooser jDateChooserNgayKH;
    private javax.swing.JDialog jDialog1;
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
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelMaChuyen;
    private javax.swing.JLabel jLabelMaVe;
    private javax.swing.JLabel jLabelTaiKhoan;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextFieldGioCu;
    private javax.swing.JTextField jTextFieldGioMoi;
    private javax.swing.JTextField jTextFieldLoaiVeCu;
    private javax.swing.JTextField jTextFieldLoaiVeMoi;
    private javax.swing.JTextField jTextFieldNgayCu;
    private javax.swing.JTextField jTextFieldNgayMoi;
    private javax.swing.JTextField jTextFieldNoiDenCu;
    private javax.swing.JTextField jTextFieldNoiDenMoi;
    private javax.swing.JTextField jTextFieldNoiDiCu;
    private javax.swing.JTextField jTextFieldNoiDiMoi;
    private javax.swing.JTextField jTextFieldSoChoDatCu;
    private javax.swing.JTextField jTextFieldSoChoDatMoi;
    private javax.swing.JTextField jTextFieldTranThaiCu;
    private javax.swing.JTextField jTextFieldTrangThaiMoi;
    private javax.swing.JTextField jTextFieldTuyenCu;
    private javax.swing.JTextField jTextFieldTuyenMoi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaVe;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSoChoDat;
    private javax.swing.JTextField txtTaiKhoan;
    private javax.swing.JTextField txtTenTK;
    // End of variables declaration//GEN-END:variables
}
