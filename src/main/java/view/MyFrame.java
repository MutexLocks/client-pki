package view;

import javax.swing.*;

public class MyFrame
{
    private JFrame f = new JFrame();
    public MyFrame()
    {
        JDialog dialog = new JDialog(f,"file",true);

        f.setSize(360, 380);
        f.setLocationRelativeTo(null);// 窗体放在显示器中间
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        f.setVisible(true);// 窗体可见
    }

    public JFrame getFrame()
    {
        return f;
    }

}