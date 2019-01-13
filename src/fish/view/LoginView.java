package fish.view;

import fish.po.SysUser;
import fish.service.IUserService;
import fish.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginView extends JFrame implements ActionListener {
    private JLabel nameLabel;
    private JLabel pwdLabel;
    private JTextField nameTextField;
    private JPasswordField pwdField;
    private JButton loginBtn;
    private JButton cancelBtn;

    private JPanel namePanel;
    private JPanel pwdPanel;
    private JPanel btnPanel;

    private void init() {
        nameLabel = new JLabel("用户名：");
        pwdLabel = new JLabel("密码：");
        nameTextField = new JTextField(10);
        pwdField = new JPasswordField(10);
        loginBtn = new JButton("登录");
        cancelBtn = new JButton("取消");

        cancelBtn.addActionListener(this);
        loginBtn.addActionListener(this);

        namePanel = new JPanel();
        pwdPanel = new JPanel();
        btnPanel = new JPanel();

        namePanel.add(nameLabel);
        namePanel.add(nameTextField);
        pwdPanel.add(pwdLabel);
        pwdPanel.add(pwdField);
        btnPanel.add(loginBtn);
        btnPanel.add(cancelBtn);

        this.add(namePanel);
        this.add(pwdPanel);
        this.add(btnPanel);
    }

    public LoginView() {
        init();
    }

    public static void main(String[] args) {
        LoginView t = new LoginView();
        t.setBounds(400, 200, 400, 300);
        t.setTitle("登陆界面");
        t.setLayout(new GridLayout(3, 1));
        t.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(loginBtn)) {
            String username = this.nameTextField.getText();
            String pwd = this.pwdField.getText();

            SysUser user = new SysUser();
            user.setUserName(username);
            user.setPwd(pwd);

            boolean result = false;
            IUserService service = new UserService();

            if (service.checkNotNull(user)) {
                JOptionPane.showMessageDialog(null, "用户名或密码不允许为空，请重新输入！");
                return;
            }

            try {
                result = service.checkUser(user);
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            if (result) {
                JOptionPane.showMessageDialog(null, "登录成功", "登录信息", JOptionPane.INFORMATION_MESSAGE);
                this.setVisible(false);
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "密码错误，登录失败", "登录信息", JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            this.nameTextField.setText("");
            this.pwdField.setText("");
        }
    }
}
