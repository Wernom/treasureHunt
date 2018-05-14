import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Form extends JTextField implements KeyListener {
    private String text;

    public Form(String text) {
        super(text);
        this.text = text;
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.text = this.getText();
    }

    @Override
    public String getText() {
        return text;
    }
}
