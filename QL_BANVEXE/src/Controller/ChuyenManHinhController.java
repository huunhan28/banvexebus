/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import MenuMain.TrangChuJPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import MenuMain.*;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.border.Border;

import MenuMain.*;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author ADMIN
 */
public class ChuyenManHinhController {
    private JPanel root;
    private String kindSelected;

    private List<DanhMucBean> listItem =null;
    public ChuyenManHinhController() {
    }

    public ChuyenManHinhController(JPanel jpnRoot) {
        this.root = jpnRoot;
        
    }
    
    
    public void setView(JPanel jpnItem,JLabel jlbItem)
    {
        kindSelected="TrangChu";
        jpnItem.setBackground(new Color(96,100,191));
        jlbItem.setBackground(new Color(96,100,191));
        
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new TrangChuJPanel());
        root.validate();
        root.repaint();
        
    }

    public void setEvent(List<DanhMucBean> listItem,java.lang.String taiKhoan){
        this.listItem= listItem;
        for( DanhMucBean item: listItem)
        {
            item.getJlb().addMouseListener((MouseListener) new LabelEvent(item.getKind(),item.getJpn(),item.getJlb(),taiKhoan));
        } 
        
    }
    
    class LabelEvent implements MouseListener{
      
        private String kind;
        private JPanel jpnItem;
        private JPanel node;
        private JLabel jlbItem;
        private  String taiKhoan;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem,String taiKhoan) {
            this.kind = kind;
            this.jpnItem = jpnItem;
          
            this.jlbItem = jlbItem;
            this.taiKhoan=taiKhoan;
        }
        
        
        @Override
        public void mouseClicked(MouseEvent e) {
            switch(kind){
                case "TrangChu":
                   node = new TrangChuJPanel();
                    break;
                case "NhanVien":
                   node = new NhanVienJP(taiKhoan);
                   
                    break;
                case "ChuyenXe":
                   node = new ChuyenXeJP(taiKhoan);
                   break;
                case "VeXe":
                   node = new VeXeJP(taiKhoan);
                    break;
                case "ThongKe":
                   node = new ThongKeJP(taiKhoan);
                   
                    break;
                default:
                     node = new TrangChuJPanel();
                     
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackgourd(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
           kindSelected= kind;
           jlbItem.setBackground(new Color(91,100,191));
           jpnItem.setBackground(new Color(91,100,191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            jlbItem.setBackground(new Color(91,100,191));
           jpnItem.setBackground(new Color(91,100,191));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jlbItem.setBackground(new Color(91,100,191));
           jpnItem.setBackground(new Color(91,100,191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
             if(!kindSelected.equalsIgnoreCase(kind)){
               jlbItem.setBackground(new Color(102,153,255));
                jpnItem.setBackground(new Color(102,153,255));
           }
       
        }

        
    }
    private void setChangeBackgourd(String kind){
        for(DanhMucBean item: listItem){
           if(item.getKind().equalsIgnoreCase(kind)){
            item.getJpn().setBackground(new Color(96,100,191));
            item.getJlb().setBackground(new Color(96,100,191));
        }
        else{
                item.getJpn().setBackground(new Color(51,153,0));
            item.getJlb().setBackground(new Color(51,153,0));
                }
    }
    }
   
}
