package view.register;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LogIn extends JFrame {
    public static boolean isVisual = true;

    private JLabel passwordLabel;
    private JLabel handleLabel;
    private JTextField handleField;
    private JPasswordField passwordField;
    private JButton logInButton;
    private JButton signInButton;
    private JCheckBox rememberMeCheck;
    private JLabel noAccountLabel;

    private Controller controller;

    public LogIn(JFrame caller, String title) throws HeadlessException {
        super(title);
        controller = new Controller();

        isVisual = !controller.isLogInDefault();
        caller.setVisible(!isVisual);

        initializeComponents();
        setComponents();
        JFrame source = this;

        logInButton.addActionListener(e -> {
            try {
                String handle = handleField.getText();
                String password = passwordField.getText();
                boolean isRemembered = rememberMeCheck.isSelected();
                controller.setLogInDataUI(handle, password, isRemembered, caller, source);
            } catch (NullPointerException | IOException ignored){}
        });

        signInButton.addActionListener(e -> new SignIn(this,"Sign In",false));

    }

    private void initializeComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        handleLabel = new JLabel("Handle ");

        passwordLabel = new JLabel("Password");

        handleField = new JTextField();

        passwordField = new JPasswordField();

        rememberMeCheck = new JCheckBox("Remember me");

        logInButton = new JButton("Login");
        logInButton.setBackground(new Color(0xa3ddcb));
        logInButton.setForeground(Color.BLACK);

        noAccountLabel = new JLabel("No account ?");
        noAccountLabel.setForeground(new Color(0xeb5e0b));

        signInButton = new JButton("Sign in");
        signInButton.setBackground(new Color(0x276678));
        signInButton.setForeground(Color.BLACK);
    }

    private void setComponents() {
        handleLabel.setBounds(100,60,60,30);
        add(handleLabel);

        passwordLabel.setBounds(100,95,60,30);
        add(passwordLabel);

        handleField.setBounds(180,65,120,20);
        add(handleField);

        passwordField.setBounds(180,100,120,20);
        add(passwordField);

        rememberMeCheck.setBounds(95,135,120,20);
        add(rememberMeCheck);

        logInButton.setBounds(220,170,80,20);
        add(logInButton);

        noAccountLabel.setBounds(120,220,100,30);
        add(noAccountLabel);

        signInButton.setBounds(200,225,80,20);
        add(signInButton);

        setLayout(null);
        setBounds(400,200,400,300);
        setVisible(isVisual);
        setResizable(false);
    }
}
