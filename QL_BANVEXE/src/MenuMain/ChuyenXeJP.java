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
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
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

public class ChuyenXeJP extends javax.swing.JPanel {

    /**
     * Creates new form ChuyenXeJPanel
     */
    public ChuyenXeJP(String taiKhoan) {
        initComponents();
        layThongtinTKSuDung(taiKhoan);
        loadMaCa();

    }

    public boolean checkHoTen(String hoTenB) {
        for (int i = 0; i < hoTenB.length(); i++) {
            if (hoTenB.codePointAt(i) >= 48 && hoTenB.codePointAt(i) <= 57) {
                return false;
            }
        }
        return true;
    }

    public void loadMaCa() {
        String sql = "select MaCa from CaLamViec";
        Connection connect = Connect.layKetNoi();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                jcbMaCa.addItem(rs.getString("MaCa"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("PACKAGE  -  loadMaCa()");
            JOptionPane.showMessageDialog(this, "Lỗi tại loadMaCa");
        }

    }

    public void layThongtinTKSuDung(String taiKhoan) {

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

    public void layThongTinChuyenXe(String maChuyenXe, String MaTuyen, String tongSoChoDat, String ngay) {
        String sql = "select MaChuyenXe,MaCa,BienSoXe,MaNV,MaTuyen,TenTuyen,Ngay,TongSoChoDat,GioKH,GioKT,ChiTiet from "
                + "ChuyenXe,Tuyen,NhanVien,Xe,CaLamViec where ChuyenXe.MaChuyenXe=CaLamViec.MaChuyenXe and "
                + "CaLamViec.BienSoXe=Xe.BienSoXe and ChuyenXe.MaTuyen=Tuyen.MaTuyen";
        Connection connect = Connect.layKetNoi();
        DefaultTableModel dtm = (DefaultTableModel) Table_ChuyenXe.getModel();
        dtm.setNumRows(0);
        Vector list = null;
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int soCho = Integer.parseInt(rs.getString("TongSoChoDat"));
                list = new Vector();
//                    list.add(rs.getString("MaChuyenXe").trim());
//                    list.add(rs.getString("MaCa"));
//                    list.add(rs.getString("BienSoXe").trim());
//                    list.add(rs.getString("MaNV").trim());
//                    list.add(rs.getString("MaTuyen").trim());
//                    list.add(rs.getString("TenTuyen").trim());
//                    list.add(rs.getString("ChiTiet").trim());
//                    list.add(rs.getString("Ngay").trim());
//                    list.add(String.valueOf(soCho));
//                    list.add(rs.getString("GioKH").trim());
//                    list.add(rs.getString("GioKT").trim());

                list.add(rs.getString(1));
                list.add(rs.getString(2));
                list.add(rs.getString(3));
                list.add(rs.getString(4));
                dtm.addRow(list);

                Table_ChuyenXe.setModel(dtm);
                rs.close();
                ps.close();

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi tại lấy thông tin chuyến xe !");
        }
    }

    public void loadThongTinChuyenXe() {

        try {

            if (!txtMaCXe.getText().trim().isEmpty()) {

                layThongTinChuyenXe(txtMaCXe.getText().trim(), txtMaTuyen.getText().trim(), txtTongSoChoDat.getText().trim(), txtDate.getDateFormatString().trim());
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

    public void lamMoiHienThiThongTinChiTiet() {
        txtMaCXe.setText("null");
        txtMaNV.setText("null");
        txtMaTuyen.setText("null");
        txtBienSoXe.setText("null");
        txtGioKH.setText("null");
        txtGioKT.setText("null");
        txtTenTuyen.setText("");
        txtChiTiet.setText("");
    }

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
        JOptionPane.showMessageDialog(this, "Thêm thành công, Nhân viên mới có mã số là " + maCX);
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
            ps.setString(1, txtMaTuyen.getText());
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
        String sql = "select* from ChuyenXe where maCX= ? ";

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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
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
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtMaCXe = new javax.swing.JTextField();
        txtBienSoXe = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        txtTongSoChoDat = new javax.swing.JTextField();
        jcbMaCa = new javax.swing.JComboBox<>();
        txtGioKH = new javax.swing.JTextField();
        txtDate = new com.toedter.calendar.JDateChooser();
        txtMaTuyen = new javax.swing.JTextField();
        txtTenTuyen = new javax.swing.JTextField();
        txtChiTiet = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtGioKT = new javax.swing.JTextField();
        jbtThem = new javax.swing.JButton();
        jbtCapNhat = new javax.swing.JButton();
        jbtXoa = new javax.swing.JButton();
        jbtLuu = new javax.swing.JButton();
        jbtShowList = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jbtXuatFile = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_ChuyenXe = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));

        jPanel5.setBackground(new java.awt.Color(255, 102, 0));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        jPanel6.setBackground(new java.awt.Color(255, 102, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THÔNG TIN CHUYẾN XE");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 784, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Mã C.Xe:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Biển Số Xe:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Mã Ca:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Mã NV:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Mã Tuyến:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Tên Tuyến:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Chi Tiết:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Tổng Số Chỗ Ngồi:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Ngày Chạy:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Giờ KH:");

        jcbMaCa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Giờ KT:");

        jbtThem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtThem.setForeground(new java.awt.Color(0, 51, 204));
        jbtThem.setText("THÊM ");
        jbtThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtThemActionPerformed(evt);
            }
        });

        jbtCapNhat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtCapNhat.setForeground(new java.awt.Color(0, 153, 0));
        jbtCapNhat.setText("CẬP NHẬT");
        jbtCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCapNhatActionPerformed(evt);
            }
        });

        jbtXoa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtXoa.setForeground(new java.awt.Color(255, 51, 0));
        jbtXoa.setText("XOÁ");
        jbtXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtXoaActionPerformed(evt);
            }
        });

        jbtLuu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtLuu.setForeground(new java.awt.Color(0, 102, 204));
        jbtLuu.setText("LƯU");
        jbtLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtLuuActionPerformed(evt);
            }
        });

        jbtShowList.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtShowList.setText("HIỆN THỊ DANH SÁCH");
        jbtShowList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtShowListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTongSoChoDat, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel3))
                                    .addGap(46, 46, 46)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtBienSoXe, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                        .addComponent(txtMaCXe))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel10))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jcbMaCa, 0, 99, Short.MAX_VALUE)
                                        .addComponent(txtMaNV))
                                    .addGap(25, 25, 25)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)))
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(109, 109, 109)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtGioKH, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtGioKT, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jbtThem)
                                .addGap(18, 18, 18)
                                .addComponent(jbtCapNhat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbtXoa)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtLuu)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtShowList)
                        .addGap(71, 71, 71))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTenTuyen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                            .addComponent(txtMaTuyen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtChiTiet, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(txtMaCXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbMaCa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaTuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(txtBienSoXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenTuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(txtTongSoChoDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtGioKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(txtGioKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtChiTiet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtThem)
                    .addComponent(jbtCapNhat)
                    .addComponent(jbtXoa)
                    .addComponent(jbtLuu)
                    .addComponent(jbtShowList))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(102, 204, 255));

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
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã C.Xe", "Mã Ca", "Biến Số Xe", "Mã NV", "Mã Tuyến ", "Tên Tuyến", "Chi Tiết", "Ngày", "Tổng Số Chỗ", "Giờ KH", "Giơ KT"
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
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jbtXuatFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtThemActionPerformed
        lamMoiHienThiThongTinChiTiet();
    }//GEN-LAST:event_jbtThemActionPerformed

    private void jbtCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCapNhatActionPerformed
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
            String date = sdf.format(txtDate.getDate());
            if (txtMaTuyen.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã tuyến không được để trống !");

            } else {
                int n = JOptionPane.showConfirmDialog(this, "Chac chan muon luu?", "LUU", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {

                    capNhatChuyenXe(txtMaCXe.getText().trim());
                    loadThongTinChuyenXe();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ngay khong hop le vui long chon lai");
        }


    }//GEN-LAST:event_jbtCapNhatActionPerformed

    private void jbtXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtXoaActionPerformed
        if (txtMaNV.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ma nhan vien can nhap de tim kiem ! ");
            return;
        }
        if (kTMaCXeTonTai(txtMaNV.getText().trim()) == 0) {
            JOptionPane.showMessageDialog(this, "MaCXe khong ton tai");
            return;
        }

        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá tài khoản này không !") == JOptionPane.NO_OPTION) {
            return;
        }
        try {

            xoaChuyenXe(txtMaNV.getText());

            JOptionPane.showMessageDialog(this, "Tai khoan đã được xoá khỏi csdl !  ");
            loadThongTinChuyenXe();
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }//GEN-LAST:event_jbtXoaActionPerformed

    private void jbtLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtLuuActionPerformed

        try {
            int soCho = Integer.parseInt(txtTongSoChoDat.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
            String date = sdf.format(txtDate.getDate());
            if (txtMaCXe.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã Chuyến xe! ");
            } else if (txtMaTuyen.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã tuyến không được để trống !");

            } else {
                int n = JOptionPane.showConfirmDialog(this, "Chac chan muon luu?", "LUU", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ngay khong hop le vui long chon lai");
        }
    }//GEN-LAST:event_jbtLuuActionPerformed

    private void jbtShowListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShowListActionPerformed

    }//GEN-LAST:event_jbtShowListActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table_ChuyenXe;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbtCapNhat;
    private javax.swing.JButton jbtLuu;
    private javax.swing.JButton jbtShowList;
    private javax.swing.JButton jbtThem;
    private javax.swing.JButton jbtXoa;
    private javax.swing.JButton jbtXuatFile;
    private javax.swing.JComboBox<String> jcbMaCa;
    private javax.swing.JTextField txtBienSoXe;
    private javax.swing.JTextField txtChiTiet;
    private javax.swing.JTextField txtChucVu;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JTextField txtGioKH;
    private javax.swing.JTextField txtGioKT;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaCXe;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaTuyen;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenTK;
    private javax.swing.JTextField txtTenTuyen;
    private javax.swing.JTextField txtTongSoChoDat;
    // End of variables declaration//GEN-END:variables
}
