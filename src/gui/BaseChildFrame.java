package gui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class BaseChildFrame extends JFrame {

    public BaseChildFrame() {
        setCloseOperation();
    }

    private JFrame parentFrame;

    public JFrame getParentFrame() {
        return parentFrame;
    }

    protected void showFrame(JFrame parent) {
        this.parentFrame = parent;
        parent.setVisible(false);
        super.setVisible(true);
    }

    protected void closeFrame() {
        if (parentFrame == null) {
            throw new IllegalArgumentException("Parent frame mast not be null");
        }
        super.setVisible(false); // установить дочерний фрейм невидимым при его закрытии
        parentFrame.setVisible(true); //установить родительский фрейм видимым

    }

    protected void setCloseOperation() {
        super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeFrame();
            }
        });
    }

}
