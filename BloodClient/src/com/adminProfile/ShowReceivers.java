/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adminProfile;

import bloodclient.Connector;
import com.standards.StandardTextField;
import org.json.JSONException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author TOSHIBA
 */
public class ShowReceivers extends JPanel{
    private String[] columnNames = {"No", "First Name", "Last Name", "Gender", "Blood Type","Age", "gender"};
    private Object[][] data = {
            {"", "", "", "", "", ""}
    };
    private DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {

        @Override
        public boolean isCellEditable(int row, int column) {
            if (row == 0) {
                return true;
            } else {
                return false;
            }
        }
    };
    private JTable table;
    private JScrollPane pane;
    private MyTableHeaders tableHeader;
    private TableColumn tableColumn;
    private JPanel center_panel = new JPanel() {
        public void paintComponent(Graphics g) {
            g.setColor(Color.decode("#383838"));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    };
    private JPanel north_panel;
    private StandardTextField search_item;
    private Thread thread;
    private JPopupMenu popupMenu;
    private JMenuItem show, delete, add_to_frequently, delete_menu;
    private JButton addCustomer, supplier, user, calculator, refreshButton, order, wechi, credit, tax;
    private ImageIcon addIcon, orderIcon, customerIcon, supplierIcon, taxIcon, userIocn, calculatorIcon, refreshIcon, creditIcon;
    private JPanel item_holder_panel;
    private String datas = null;

    public ShowReceivers(String data) {
        this.datas = data;
        setLayout(new BorderLayout());
        center_panel = new JPanel();
        center_panel.setLayout(new BorderLayout());
        table = new JTable(tableModel);
        table.setFont(new Font("nyala", Font.PLAIN, 20));
        pane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableHeader = new MyTableHeaders();
        table.getTableHeader().setDefaultRenderer(tableHeader);

        for (int i = 0; i < table.getColumnCount(); i++) {
            tableColumn = table.getColumnModel().getColumn(i);
            if (i == 1 || i == 2 || i == 3 || i == 4) {
                tableColumn.setPreferredWidth(200);
            } else if (i == 0) {
                tableColumn.setPreferredWidth(60);
            } else {
                tableColumn.setPreferredWidth(175);
            }

        }


        table.setRowHeight(30);
        table.setFont(new Font("arial", Font.PLAIN, 20));
        tableModel.getDataVector().removeAllElements();
        tableModel.fireTableDataChanged();

        show = new JMenuItem("አስተካክል");
        show.setFont(new Font("nyala", Font.PLAIN, 18));
        delete = new JMenuItem("አስወግድ");
        delete.setFont(new Font("nyala", Font.PLAIN, 18));
        add_to_frequently = new JMenuItem("ወደ ተመላላሽ ታካሚ ዝርዝር አስገባ");
        add_to_frequently.setFont(new Font("nyala", Font.PLAIN, 18));

        popupMenu = new JPopupMenu();
        popupMenu.add(show);
        popupMenu.add(delete);
        popupMenu.add(add_to_frequently);

        getAllData();



        search_item = new StandardTextField(40, 50, 10);
        search_item.setFont(new Font("arial", Font.BOLD, 18));
        search_item.setPlaceholder("Search receiver");

        north_panel = new JPanel();
        north_panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));
        north_panel.setPreferredSize(new Dimension(500, 50));
        north_panel.setLayout(new BorderLayout());

        north_panel.add(search_item, BorderLayout.EAST);
        center_panel.add(north_panel, BorderLayout.NORTH);
        search_item.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                DefaultTableModel tableModels = (DefaultTableModel) table.getModel();
                String items = search_item.getText().toString();
                TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(tableModels);
                table.setRowSorter(tr);
                tr.setRowFilter(RowFilter.regexFilter(items));
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    JTable source = (JTable) e.getSource();
                    int row = source.rowAtPoint(e.getPoint());
                    int column = source.columnAtPoint(e.getPoint());
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }

            }
        });

        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component c = (Component) e.getSource();
                JPopupMenu popup = (JPopupMenu) c.getParent();
                JTable table = (JTable) popup.getInvoker();
                String card_number = table.getValueAt(table.getSelectedRow(), 4).toString();

            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component c = (Component) e.getSource();
                JPopupMenu popup = (JPopupMenu) c.getParent();
                JTable table = (JTable) popup.getInvoker();
                String card_number = table.getValueAt(table.getSelectedRow(), 4).toString();

            }
        });
        center_panel.add(pane, BorderLayout.CENTER);
        add(center_panel, BorderLayout.CENTER);

    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.decode("#383838"));
        g.fillRect(0, 0, getWidth(), getHeight());
    }


    public void getAllData() {
        tableModel.getDataVector().removeAllElements();
        table.revalidate();
        try {
            ArrayList<String[]> ds = Connector.getInstance().parseReceiverJSON(this.datas);
            for (int i =0,j=1; i < ds.size(); i++,j++){
               tableModel.addRow(new Object[]{"" + j, ds.get(i)[0], ds.get(i)[1], ds.get(i)[2], ds.get(i)[3], ds.get(i)[4]});
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(this.datas);
        //tableModel.addRow(new Object[]{"" + i, rsu.getString(2), rsu.getString(3), rsu.getString(4), rsu.getString(9), rsu.getString(5),rsu.getString(6),rsu.getString(8),rsu.getString(12)});

    }

}
