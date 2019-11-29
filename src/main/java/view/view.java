/*
 * Created by JFormDesigner on Tue Nov 26 10:33:52 CST 2019
 */

package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import function.FunctionImpl;
import model.Element;
import net.miginfocom.swing.*;

/**
 * @author Brainrain
 */
public class view extends JFrame {
    public view() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        comboBox1 = new JComboBox();
        label1 = new JLabel();
        formattedTextField1 = new JFormattedTextField();
        label3 = new JLabel();
        passwordField1 = new JPasswordField();
        label2 = new JLabel();
        formattedTextField2 = new JFormattedTextField();
        label4 = new JLabel();
        formattedTextField3 = new JFormattedTextField();
        label5 = new JLabel();
        formattedTextField4 = new JFormattedTextField();
        button1 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));
        contentPane.add(comboBox1, "cell 2 0");

        comboBox1.addItem("加密");
        comboBox1.addItem("解密");
        //---- label1 ----
        label1.setText("keystore \u8def\u5f84:");
        contentPane.add(label1, "cell 2 1");
        contentPane.add(formattedTextField1, "cell 3 1 8 1");

        //---- label3 ----
        label3.setText("keystore\u5bc6\u7801:");
        contentPane.add(label3, "cell 2 2");
        contentPane.add(passwordField1, "cell 3 2 8 1");

        //---- label2 ----
        label2.setText("\u5bf9\u65b9\u8bc1\u4e66\u8def\u5f84:");
        contentPane.add(label2, "cell 2 3");
        contentPane.add(formattedTextField2, "cell 3 3 8 1");

        //---- label4 ----
        label4.setText("\u8f93\u5165\u6587\u4ef6\u8def\u5f84\uff1a");
        contentPane.add(label4, "cell 2 4");
        contentPane.add(formattedTextField3, "cell 3 4 8 1");

        //---- label5 ----
        label5.setText("\u8f93\u51fa\u6587\u4ef6\u8def\u5f84:");
        contentPane.add(label5, "cell 2 5");
        contentPane.add(formattedTextField4, "cell 3 5 8 1");

        //---- button1 ----
        button1.setText("\u786e\u5b9a");
        contentPane.add(button1, "cell 5 6");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        JFrame frame = new MyFrame().getFrame();
        frame.setContentPane(contentPane);

        final Element element = new Element();
        button1.addActionListener(new ActionListener() {//给按钮添加事件接收器
            public void actionPerformed(ActionEvent e) {//接受到事件后,进行下面的处理
                element.setKeystorePath(formattedTextField1.getText());
                element.setKeystorePassword(passwordField1.getText());
                element.setOtherCertPath(formattedTextField2.getText());
                element.setInputFilePath(formattedTextField3.getText());
                element.setOutputFilePath(formattedTextField4.getText());
                if ("加密".equals(comboBox1.getSelectedItem())){
                    new FunctionImpl(element).encrytion();
                }
                if ("解密".equals(comboBox1.getSelectedItem())){
                    new FunctionImpl(element).decrytion();
                }
                //new CreateMap(myInfoBean);
            }
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JComboBox comboBox1;
    private JLabel label1;
    private JFormattedTextField formattedTextField1;
    private JLabel label3;
    private JPasswordField passwordField1;
    private JLabel label2;
    private JFormattedTextField formattedTextField2;
    private JLabel label4;
    private JFormattedTextField formattedTextField3;
    private JLabel label5;
    private JFormattedTextField formattedTextField4;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public static void main(String[] args) {
        new view();
    }
}
