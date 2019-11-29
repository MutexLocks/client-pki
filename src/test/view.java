import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Tue Nov 26 11:14:44 CST 2019
 */



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
}
