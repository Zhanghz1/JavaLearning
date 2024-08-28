package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class LoginJFrame extends JFrame implements KeyListener, ActionListener {
    //登陆界面相关代码
    //空参构造，初始化登录页面

    JLabel verificationCodeLabel = new JLabel();

    public LoginJFrame() throws HeadlessException {

        initialJFrame();



    }

    private void initialJFrame() {
        this.setSize(488, 430);
        this.setVisible(true);
        this.setTitle("Login");
        //this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addKeyListener(this);
        //设置图片
        JLabel background = new JLabel(new ImageIcon("PuzzleGame\\image\\login\\background.png"));
        background.setBounds(40,40,508,560);
        this.getContentPane().add(background);
        //添加文字
        JTextField puzzleGame = new JTextField("Puzzle Game");
        background.add(puzzleGame);
        //用户名
        JLabel userNameLabel = new JLabel(new ImageIcon("PuzzleGame\\image\\login\\用户名.png"));
        userNameLabel.setBounds(100, 120, 80, 30);
        background.add(userNameLabel);
        // 创建用户名文本框，并设置位置和大小
        JTextField userNameTextField = new JTextField();
        userNameTextField.setBounds(190, 120, 150, 30); // 在用户名标签后面位置
        background.add(userNameTextField);
        //密码
        JLabel userCodeLabel = new JLabel(new ImageIcon("PuzzleGame\\image\\login\\密码.png"));
        userCodeLabel.setBounds(100, 180, 80, 30);
        background.add(userCodeLabel);
        // 创建密码文本框，并设置位置和大小
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(190, 180, 150, 30); // 在密码标签后面位置
        background.add(passwordField);
        //验证码
        JLabel verifyLable = new JLabel(new ImageIcon("PuzzleGame\\image\\login\\密码.png"));
        verifyLable.setBounds(100, 240, 80, 30);
        background.add(verifyLable);
        // 创建验证码文本框，并设置位置和大小
        JTextField verifyTextField = new JTextField();
        verifyTextField.setBounds(190, 240, 80, 30); // 在用户名标签后面位置
        background.add(verifyTextField);

        // 创建验证码标签，并设置位置和大小

        verificationCodeLabel.setBounds(300, 240, 100, 30); // 验证码标签位置
        refreshVerificationCode(); // 刷新验证码图片
        background.add(verificationCodeLabel);

        // 添加验证码图片点击刷新事件
        verificationCodeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshVerificationCode(); // 点击图片时刷新验证码
            }
        });

        this.setVisible(true);
    }

    private void refreshVerificationCode() {
        // 生成验证码图片
        BufferedImage image = VerificationCodeGenerator.generateVerificationCode();
        ImageIcon icon = new ImageIcon(image);

        // 更新验证码标签的图标
        verificationCodeLabel.setIcon(icon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
