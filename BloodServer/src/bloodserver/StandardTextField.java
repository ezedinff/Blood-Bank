/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodserver;

import javax.swing.*;
import javax.swing.FocusManager;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class StandardTextField extends JTextField {

    private placeHolder holder;
    private String placeholder;
    private Font fonts;
    private boolean change = false;

    public StandardTextField() {
        //super.setBackground(Color.white);
        holder = new placeHolder(this);
    }

    public void changeSize(int w, int h) {
        if (change) {
            super.setPreferredSize(new Dimension(w, h));
        } else {
            super.setPreferredSize(new Dimension(10, 25));
        }

    }

    public Font getFonts() {
        return fonts;
    }

    public void setFonts(Font fonts) {
        this.fonts = fonts;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        holder.paint(g);
    }

    class placeHolder implements FocusListener {

        private StandardTextField field;

        public placeHolder(StandardTextField fi) {
            this.field = fi;
            field.addFocusListener(this);
        }

        @Override
        public void focusGained(FocusEvent e) {
            field.repaint();
        }

        @Override
        public void focusLost(FocusEvent e) {
            field.repaint();
        }

        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            if (field.getText().isEmpty()&& (FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() != field)) {
                g2.setFont(field.getFonts());
                g2.setColor(Color.gray);
                g2.drawString(field.getPlaceholder(), 10, 30);
                field.setBorder(BorderFactory.createEtchedBorder(Color.black, Color.black));
            } else if (field.getText().equals("") && (FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == field)) {

                field.setBorder(BorderFactory.createEtchedBorder(Color.decode("#e89271"), Color.decode("#e89271")));

            }
        }
    }
}