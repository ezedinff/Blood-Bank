/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adminProfile;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author TOSHIBA
 */
public class MyTableHeaders extends JLabel implements TableCellRenderer {

    public MyTableHeaders() {
        //super.setForeground(Color.black);

        super.setFont(new Font("nyala", Font.PLAIN, 17));
        super.setBorder(BorderFactory.createEtchedBorder());
    }

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
        setText(o.toString());
        return this;
    }

}

