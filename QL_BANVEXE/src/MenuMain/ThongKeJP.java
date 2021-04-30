/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuMain;

import Controller.Connect;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ADMIN
 */ 
    
public class ThongKeJP extends javax.swing.JPanel {
    public DefaultTableModel dtm,dtm2,dtm3;
    /**
     * Creates new form ThongKe
     */
    public ThongKeJP(String taiKhoan) {
        initComponents();
        layThongKeTheoNgayTT();
        layThongKeTheoNgayVSV();
        layThongKeTheoNgayVT();
        
        layBieuDoTT();
        layBieuDoVSV();
        layBieuDoVT();
    }
//    public String chuyenNgay(Date ngay){
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar c = Calendar.getInstance();
//        c.setTime(ngay);
//        c.add(Calendar.DATE, 2);
//        String strNgay = sdf.format(c.getTime());
//        return strNgay;
//    }
    void layThongKeTheoNgayTT(){
        String ngay;
        String thang=(String) jComboBoxThang.getSelectedItem();
        String nam=(String) jComboBoxNam.getSelectedItem();
        dtm= (DefaultTableModel) jTableTT.getModel();
        dtm.setNumRows(0);
        Connection ketNoi=Connect.layKetNoi();
        Vector vt;
        try {
            PreparedStatement ps=ketNoi.prepareStatement("select ngay,sum(gia)\n" +
                                                        "from Ve,LoaiVe,ChuyenXe\n" +
                                                        "where ve.MaLoaiVe=LoaiVe.MaLoaiVe and Ve.MaChuyenXe=ChuyenXe.MaChuyenXe and YEAR(ngay)="+nam+" and MONTH(ngay)="+thang+"\n" +
                                                        "group by Ngay");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                vt= new Vector();
//                ngay=chuyenNgay(rs.getDate(1));
                
                vt.add(rs.getDate(1));
                
                
                vt.add(rs.getString(2));
                
                dtm.addRow(vt);
                
            }
            jTableTT.setModel(dtm);
            ps.close();
            rs.close();
            ketNoi.close();
        } catch (SQLException ex) {
            System.out.println("loi lay thong ke theo ngay ");
        }
    }
    void layThongKeTheoNgayVSV(){
        String ngay;
        String thang=(String) jComboBoxThang.getSelectedItem();
        String nam=(String) jComboBoxNam.getSelectedItem();
        dtm2= (DefaultTableModel) jTableVSV.getModel();
        dtm2.setNumRows(0);
        Connection ketNoi=Connect.layKetNoi();
        Vector vt;
        try {
            PreparedStatement ps=ketNoi.prepareStatement("select ngay,sum(SoChoDat)\n"+
                                                        "from Ve,ChuyenXe,LoaiVe\n"+
                                                        "where ve.MaChuyenXe=ChuyenXe.MaChuyenXe and Ve.MaLoaiVe=LoaiVe.MaLoaiVe and Ve.MaLoaiVe='VSV' and YEAR(ngay)="+nam+" and MONTH(ngay)="+thang+"\n"+
                                                        "group by Ngay");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                vt= new Vector();
//                ngay=chuyenNgay(rs.getDate(1));
                
                vt.add(rs.getDate(1));
                
                
                vt.add(rs.getString(2));
                
                dtm2.addRow(vt);
                
            }
            jTableVSV.setModel(dtm2);
            ps.close();
            rs.close();
            ketNoi.close();
        } catch (SQLException ex) {
            System.out.println("loi lay Ve Sinh Vien ");
        }
    }
    void layThongKeTheoNgayVT(){
        String ngay;
        String thang=(String) jComboBoxThang.getSelectedItem();
        String nam=(String) jComboBoxNam.getSelectedItem();
        dtm3= (DefaultTableModel) jTableVT.getModel();
        dtm3.setNumRows(0);
        Connection ketNoi=Connect.layKetNoi();
        Vector vt;
        try {
            PreparedStatement ps=ketNoi.prepareStatement("select ngay,sum(SoChoDat)\n" +
                                                            "from Ve,ChuyenXe,LoaiVe\n" +
                                                            "where ve.MaChuyenXe=ChuyenXe.MaChuyenXe and Ve.MaLoaiVe=LoaiVe.MaLoaiVe and Ve.MaLoaiVe='VT' and YEAR(ngay)="+nam+" and MONTH(ngay)="+thang+"\n" +
                                                            "group by Ngay");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                vt= new Vector();
//                ngay=chuyenNgay(rs.getDate(1));
                
                vt.add(rs.getDate(1));
                vt.add(rs.getString(2));
                
                dtm3.addRow(vt);
                
            }
            jTableVT.setModel(dtm3);
            ps.close();
            rs.close();
            ketNoi.close();
        } catch (SQLException ex) {
            System.out.println("loi lay Ve Thuong "+ex.getMessage());
        }
    }
    public void layBieuDoTT(){
        jPanelBieuDoTT.removeAll();
        String thang=(String) jComboBoxThang.getSelectedItem();
        String nam=(String) jComboBoxNam.getSelectedItem();
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        Connection ketNoi=Connect.layKetNoi();
        try {
            PreparedStatement ps=ketNoi.prepareStatement("select ngay,sum(gia)\n" +
                                                        "from Ve,LoaiVe,ChuyenXe\n" +
                                                        "where ve.MaLoaiVe=LoaiVe.MaLoaiVe and Ve.MaChuyenXe=ChuyenXe.MaChuyenXe and YEAR(ngay)="+nam+" and MONTH(ngay)="+thang+"\n" +
                                                        "group by Ngay");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
//                ngay=chuyenNgay(rs.getDate(1));
                
                dataset.setValue(rs.getDouble(2), "Tong tien", rs.getDate(1));
            }
            
            ps.close();
            rs.close();
            ketNoi.close();
        } catch (SQLException ex) {
            System.out.println("loi lay thong ke theo ngay ");
        }
        
        JFreeChart chart=ChartFactory.createBarChart("Doanh thu tong tien", "Ngay", "Tien", dataset,PlotOrientation.VERTICAL,false,true,false);
        ChartPanel panel=new ChartPanel(chart);
        panel.setMouseZoomable(true);
        panel.setPreferredSize(new Dimension(727,451));
        jPanelBieuDoTT.setLayout(new BorderLayout());
        jPanelBieuDoTT.add(panel, BorderLayout.NORTH);
        
//        CategoryPlot p=chart.getCategoryPlot();
//        p.setRangeGridlinePaint(Color.black);
//        ChartFrame frame=new ChartFrame("Thong ke",chart);
//        frame.setVisible(true);
//        frame.setLocation(400, 400);
//        frame.setSize(450, 350);
    }
    public void layBieuDoVSV(){
        jPanelBieuDoVSV.removeAll();
        String thang=(String) jComboBoxThang.getSelectedItem();
        String nam=(String) jComboBoxNam.getSelectedItem();
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        Connection ketNoi=Connect.layKetNoi();
        try {
            PreparedStatement ps=ketNoi.prepareStatement("select ngay,sum(SoChoDat)\n" +
                                                        "from Ve,LoaiVe,ChuyenXe\n" +
                                                        "where ve.MaLoaiVe=LoaiVe.MaLoaiVe and Ve.MaChuyenXe=ChuyenXe.MaChuyenXe and Ve.MaLoaiVe='VSV' and YEAR(ngay)="+nam+" and MONTH(ngay)="+thang+"\n" +
                                                        "group by Ngay");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
//                ngay=chuyenNgay(rs.getDate(1));
                
                dataset.setValue(rs.getDouble(2), "Tong ve", rs.getDate(1));
            }
            
            ps.close();
            rs.close();
            ketNoi.close();
        } catch (SQLException ex) {
            System.out.println("loi lay thong ke theo ve sinh vien ");
        }
        
        JFreeChart chart=ChartFactory.createBarChart("Doanh thu ve sinh vien", "Ngay", "Ve", dataset,PlotOrientation.VERTICAL,false,true,false);
        ChartPanel panel=new ChartPanel(chart);
        panel.setMouseZoomable(true);
        panel.setPreferredSize(new Dimension(727,451));
        jPanelBieuDoVSV.setLayout(new BorderLayout());
        jPanelBieuDoVSV.add(panel, BorderLayout.NORTH);
        
//        CategoryPlot p=chart.getCategoryPlot();
//        p.setRangeGridlinePaint(Color.black);
//        ChartFrame frame=new ChartFrame("Thong ke",chart);
//        frame.setVisible(true);
//        frame.setLocation(400, 400);
//        frame.setSize(450, 350);
    }
    public void layBieuDoVT(){
        jPanelBieuDoVT.removeAll();
        String thang=(String) jComboBoxThang.getSelectedItem();
        String nam=(String) jComboBoxNam.getSelectedItem();
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        Connection ketNoi=Connect.layKetNoi();
        try {
            PreparedStatement ps=ketNoi.prepareStatement("select ngay,sum(SoChoDat)\n" +
                                                        "from Ve,LoaiVe,ChuyenXe\n" +
                                                        "where ve.MaLoaiVe=LoaiVe.MaLoaiVe and Ve.MaChuyenXe=ChuyenXe.MaChuyenXe and Ve.MaLoaiVe='VT' and YEAR(ngay)="+nam+" and MONTH(ngay)="+thang+"\n" +
                                                        "group by Ngay");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
//                ngay=chuyenNgay(rs.getDate(1));
                
                dataset.setValue(rs.getDouble(2), "Tong ve", rs.getDate(1));
            }
            
            ps.close();
            rs.close();
            ketNoi.close();
        } catch (SQLException ex) {
            System.out.println("loi lay thong ke theo ve thuong ");
        }
        
        JFreeChart chart=ChartFactory.createBarChart("Doanh thu ve thuong", "Ngay", "Ve", dataset,PlotOrientation.VERTICAL,false,true,false);
        ChartPanel panel=new ChartPanel(chart);
        panel.setMouseZoomable(true);
        panel.setPreferredSize(new Dimension(727,451));
        jPanelBieuDoVT.setLayout(new BorderLayout());
        jPanelBieuDoVT.add(panel, BorderLayout.NORTH);
        
//        CategoryPlot p=chart.getCategoryPlot();
//        p.setRangeGridlinePaint(Color.black);
//        ChartFrame frame=new ChartFrame("Thong ke",chart);
//        frame.setVisible(true);
//        frame.setLocation(400, 400);
//        frame.setSize(450, 350);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jComboBoxThang = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVSV = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableVT = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableTT = new javax.swing.JTable();
        jComboBoxNam = new javax.swing.JComboBox<>();
        jButtonThongKe = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelBieuDoTT = new javax.swing.JPanel();
        jPanelBieuDoVSV = new javax.swing.JPanel();
        jPanelBieuDoVT = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1091, 795));
        setMinimumSize(new java.awt.Dimension(1091, 795));
        setPreferredSize(new java.awt.Dimension(1091, 795));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1091, 795));
        jPanel1.setMinimumSize(new java.awt.Dimension(1091, 795));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(54, 33, 89));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("THÔNG TIN TÀI KHOẢN");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setText("THOÁT ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("SDT:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Tên TK:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Họ Tên:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Chức Vụ:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(3, 3, 3))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(54, 33, 89));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("THÔNG TIN THỐNG KÊ");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel3)
                .addContainerGap(104, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setMaximumSize(new java.awt.Dimension(772, 795));
        jPanel5.setMinimumSize(new java.awt.Dimension(772, 795));

        jPanel8.setBackground(new java.awt.Color(54, 33, 89));
        jPanel8.setForeground(new java.awt.Color(255, 102, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BIỂU ĐỒ THỐNG KÊ");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        jComboBoxThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jTableVSV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Ngày", "Vé sinh viên"
            }
        ));
        jScrollPane1.setViewportView(jTableVSV);

        jTableVT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Ngày", "Số Vé Thường"
            }
        ));
        jScrollPane2.setViewportView(jTableVT);

        jTableTT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Ngày", "Tổng Tiền"
            }
        ));
        jScrollPane3.setViewportView(jTableTT);

        jComboBoxNam.setEditable(true);
        jComboBoxNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025" }));

        jButtonThongKe.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonThongKe.setText("THỐNG KÊ");
        jButtonThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThongKeActionPerformed(evt);
            }
        });

        jLabel9.setText("Năm");

        jLabel10.setText("Tháng");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)
                        .addGap(21, 21, 21)
                        .addComponent(jComboBoxNam, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxThang, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jButtonThongKe))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonThongKe)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(39, 39, 39)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelBieuDoTTLayout = new javax.swing.GroupLayout(jPanelBieuDoTT);
        jPanelBieuDoTT.setLayout(jPanelBieuDoTTLayout);
        jPanelBieuDoTTLayout.setHorizontalGroup(
            jPanelBieuDoTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 727, Short.MAX_VALUE)
        );
        jPanelBieuDoTTLayout.setVerticalGroup(
            jPanelBieuDoTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 451, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Tong Tien", jPanelBieuDoTT);

        javax.swing.GroupLayout jPanelBieuDoVSVLayout = new javax.swing.GroupLayout(jPanelBieuDoVSV);
        jPanelBieuDoVSV.setLayout(jPanelBieuDoVSVLayout);
        jPanelBieuDoVSVLayout.setHorizontalGroup(
            jPanelBieuDoVSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 727, Short.MAX_VALUE)
        );
        jPanelBieuDoVSVLayout.setVerticalGroup(
            jPanelBieuDoVSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 451, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Ve Sinh Vien", jPanelBieuDoVSV);

        javax.swing.GroupLayout jPanelBieuDoVTLayout = new javax.swing.GroupLayout(jPanelBieuDoVT);
        jPanelBieuDoVT.setLayout(jPanelBieuDoVTLayout);
        jPanelBieuDoVTLayout.setHorizontalGroup(
            jPanelBieuDoVTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 727, Short.MAX_VALUE)
        );
        jPanelBieuDoVTLayout.setVerticalGroup(
            jPanelBieuDoVTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 451, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Ve Thuong", jPanelBieuDoVT);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(54, 33, 89));
        jLabel8.setText("BIỂU ĐỒ THỐNG KÊ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThongKeActionPerformed
        // TODO add your handling code here:
        layThongKeTheoNgayTT();
        layThongKeTheoNgayVSV();
        layThongKeTheoNgayVT();
        
        layBieuDoTT();
        layBieuDoVSV();
        layBieuDoVT();
        
        jTabbedPane1.repaint();
        //jTabbedPane1.revalidate();
    }//GEN-LAST:event_jButtonThongKeActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1StateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonThongKe;
    private javax.swing.JComboBox<String> jComboBoxNam;
    private javax.swing.JComboBox<String> jComboBoxThang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelBieuDoTT;
    private javax.swing.JPanel jPanelBieuDoVSV;
    private javax.swing.JPanel jPanelBieuDoVT;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableTT;
    private javax.swing.JTable jTableVSV;
    private javax.swing.JTable jTableVT;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
