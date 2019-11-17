/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.standards;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

/**
 *
 * @author Meseret
 */
public class StandardButton extends JButton {

    private Color color;
    private String title;

    public StandardButton(String titles) {
        title = titles;
        color= Color.decode("#EC3627");
        super.setContentAreaFilled(false);
        super.setBorderPainted(false);
        super.setFocusPainted(false);
        super.setForeground(Color.white);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                color = Color.decode("#931638");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                color = Color.decode("#EC3627");
            }
        });
    }

    public void paintComponent(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("nyala", Font.BOLD, 35));
        g2.drawString(title, getWidth() / 2 - 120, getHeight() / 2 + 10);
    }

}
