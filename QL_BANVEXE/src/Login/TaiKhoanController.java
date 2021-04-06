/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author ADMIN
 */
public class TaiKhoanController {
    private JDialog dialog;
    private JButton btnSubmit;
    private JTextField txtTenDN;
    private JTextField txtMK;
    private JLabel jlbMsg;

    public TaiKhoanController(JDialog dialog, JButton btnSubmit, JTextField txtTenDN, JTextField txtMK, JLabel jlbMsg) {
        this.dialog = dialog;
        this.btnSubmit = btnSubmit;
        this.txtTenDN = txtTenDN;
        this.txtMK = txtMK;
        this.jlbMsg = jlbMsg;
    }

   
    
            public void setEvent() {
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (txtTenDN.getText().length() == 0
                            || txtMK.getText().length() == 0) {
                        jlbMsg.setText("Vui lòng nhập dữ liệu bắt buộc!");
                    } else {
//                        TaiKhoan taiKhoan = taiKhoanService.login(txtTenDN.getText(), txtMK.getText());
//                        if (taiKhoan == null) {
//                            jlbMsg.setText("Tên đăng nhập và mật khẩu không đúng!");
//                        } else {
//                            if (!taiKhoan.is()) {
//                                jlbMsg.setText("Tài khoản của bạn đã đăng nhập !");
//                            } else {
//                                dialog.dispose();
//                                
//                            }
//                        }
                    }
                } catch (Exception ex) {
                    jlbMsg.setText(ex.toString());
                }
            }
        });
    }

}
