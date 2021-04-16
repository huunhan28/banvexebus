/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuMain;
import Controller.Connect;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import javax.swing.JOptionPane;
/**
 *
 * @author Huu Nhan
 */
public class CaLamViec extends javax.swing.JPanel {
    DefaultTableModel dtm,dtm2;
    /**
     * Creates new form CaLamViec1
     */
    public CaLamViec(String taiKhoan) {
        initComponents();
        layChuyen();
        layNhanVien();
        layXe();
        layCaLamViec();
        layChuyenXeChuaPhanCong();
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
                                                        "    FROM CaLamViec as b\n" +
                                                        "    WHERE a.MaChuyenXe=b.MaChuyenXe)");
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
    void layNhanVien(){
        Connection ketNoi=Connect.layKetNoi();
        try{
            PreparedStatement ps= ketNoi.prepareStatement("select MaNV from NhanVien");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                jComboBoxCLVNV.addItem(rs.getString("MaNV"));
            }
        }catch(SQLException e){
            System.out.println("loi lay nhan vien");
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
                    if(tuyen.equals(tuyen1)&&ngay.equals(ngay1)&&trangThai.equals(trangThai1)&&
                            gioKH.equals(ThoiGianDen1)&&Xe.equals(Xe1)){
                        trung=1;
                    }
                    if(tuyen.equals(tuyen1)&&ngay.equals(ngay1)&&trangThai.equals(trangThai1)&&
                            gioDen.equals(ThoiGianKhoiHanh1)&&Xe.equals(Xe1)){
                        trung=1;
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
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldCLVMaCa = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jComboBoxCLVCX = new javax.swing.JComboBox<>();
        jComboBoxCLVNV = new javax.swing.JComboBox<>();
        jComboBoxCLVBS = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(54, 33, 89));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ CA LÀM VIỆC");

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
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Nhập thông tin:");

        jTextFieldTKCaLamViec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldTKCaLamViecKeyReleased(evt);
            }
        });

        jComboBoxTKCaLamViec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MaCa", "MaChuyenXe", "MaNV", "BienSoXe", "ThoiGianKhoiHanh", "ThoiGianDen" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldTKCaLamViec, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxTKCaLamViec, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(275, Short.MAX_VALUE))
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
        jLabel8.setText("DANH SÁCH CA LÀM VIỆC");

        jTableCaLamViec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ca", "Mã chuyến xe", "Mã nhân viên", "Biển số", "Thời gian khởi hàn", "Thời gian đến"
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
                "Chuyến xe chưa phân công"
            }
        ));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(51, 102, 0));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(54, 33, 89));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("THÔNG TIN TÀI KHOẢN");

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
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField3)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(54, 33, 89));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("THÔNG TIN CA LÀM VIỆC");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel3)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Mã ca:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Mã chuyến xe:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Mã nhân viên");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Biển số:");

        jComboBoxCLVCX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Chọn chuyến xe-" }));
        jComboBoxCLVCX.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxCLVCXItemStateChanged(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnThem.setForeground(new java.awt.Color(0, 204, 0));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Save-icon.png"))); // NOI18N
        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel9.setText("(Thời gian đến = thời gian khởi hành + 2h)");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCLVMaCa)
                            .addComponent(jComboBoxCLVCX, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxCLVNV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxCLVBS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(101, 101, 101)
                                .addComponent(btnThem))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel9)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                    .addComponent(jComboBoxCLVNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jComboBoxCLVBS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(45, 45, 45)
                .addComponent(btnThem)
                .addGap(72, 72, 72))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        dtm.setNumRows(0);
        jTableCaLamViec.setModel(dtm);
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
                dtm.addRow(vt);

            }
            jTableCaLamViec.setModel(dtm);
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

        jTextFieldCLVMaCa.setText(model.getValueAt(selectedRow, 0).toString());
        jComboBoxCLVCX.setSelectedItem(model.getValueAt(selectedRow, 1).toString());
        jComboBoxCLVNV.setSelectedItem(model.getValueAt(selectedRow, 2).toString());
        jComboBoxCLVBS.setSelectedItem(model.getValueAt(selectedRow, 3).toString());

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

        String maNV=(String) jComboBoxCLVNV.getSelectedItem();
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
        if(kTTrungTuyenNgayTTTGNV(tuyen,ngay,trangThai,thoiGianKhoiHanh,thoiGianDen,maNV)==1){
            JOptionPane.showMessageDialog(this, "Nhan vien nay thoi diem nay da den cuoi tram. Khong the phan cong");
            return;
        }

        if(kTTrungTuyenNgayTTTGXe(tuyen,ngay,trangThai,thoiGianKhoiHanh,thoiGianDen,bienSoXe)==1){
            JOptionPane.showMessageDialog(this, "Xe nay vao thoi diem nay da den cuoi tram. Khong the phan cong");
            return;
        }
        if(maCa!=""){
            try{
                PreparedStatement ps= ketNoi.prepareStatement("insert into CaLamViec values(?,?,?,?,?,?)");
                ps.setString(1,maCa );
                ps.setString(2,maCX );
                ps.setString(3,maNV );
                ps.setString(4,bienSoXe );
                ps.setString(5,thoiGianKhoiHanh );
                ps.setString(6,thoiGianDen );
                ps.executeUpdate();
            }catch(SQLException e){
                System.out.println("loi luu them ca lam viec");
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
                    jComboBoxCLVBS.enable(false);
                }
            }catch(SQLException e){
                System.out.println("loi kt chuyen xe da duoc phan cong");
            }
            
        }
    }//GEN-LAST:event_jComboBoxCLVCXItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxCLVBS;
    private javax.swing.JComboBox<String> jComboBoxCLVCX;
    private javax.swing.JComboBox<String> jComboBoxCLVNV;
    private javax.swing.JComboBox<String> jComboBoxTKCaLamViec;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableCaLamViec;
    private javax.swing.JTable jTableChuyenXeChuaDuocPhanCong;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextFieldCLVMaCa;
    private javax.swing.JTextField jTextFieldTKCaLamViec;
    // End of variables declaration//GEN-END:variables
}
