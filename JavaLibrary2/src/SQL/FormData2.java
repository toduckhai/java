package SQL;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FormData2 extends JFrame implements ActionListener {

    JTable table;
    MyTableModel myModel;
    ConnectionDatabase connecdata;
    Connection con;
    JButton btnThem, btnSua, btnXoa, btnHuy, btnGhi;
    JTextField txtID, txtMakhach, txtTenkhach, txtDiachi, txtNamsinh, txtSdt;    
    boolean modeedit_save = false;
   

    public FormData2() {
        super("Form giao diện");
        JScrollPane tablePane;
        Panel southPane, textPane, buttonPane;
        connecdata = new ConnectionDatabase();
        connecdata.initConnection();
        ResultSet rs = connecdata.getAllData();
        setBackground(Color.YELLOW);
        try {
            myModel = new MyTableModel(rs);
            table = new JTable(myModel);
            // thiet lap do rong cot cho Table
            table.getColumnModel().getColumn(0).setPreferredWidth(15);
            table.getColumnModel().getColumn(1).setPreferredWidth(130);
            table.getColumnModel().getColumn(2).setPreferredWidth(200);
            table.getColumnModel().getColumn(3).setPreferredWidth(50);
            table.getColumnModel().getColumn(4).setPreferredWidth(50);
            table.getColumnModel().getColumn(5).setPreferredWidth(40);
            // gắn đối tượng lắng nghe Table;
            MyTableSelectionListener2 myTableListener = new MyTableSelectionListener2(table);
            table.getSelectionModel().addListSelectionListener(myTableListener);
            tablePane = new JScrollPane(table);
            buttonPane = new Panel(new FlowLayout());
            btnThem = new JButton("Thêm");
            btnThem.addActionListener(this);
            btnSua = new JButton("Sửa");
            btnSua.addActionListener(this);
            btnXoa = new JButton("Xóa");
            btnXoa.addActionListener(this);
            btnHuy = new JButton("Hủy");
            btnHuy.addActionListener(this);
            btnGhi = new JButton("Lưu");
            btnGhi.addActionListener(this);
            buttonPane.add(btnThem);
            buttonPane.add(btnSua);
            buttonPane.add(btnXoa);
            buttonPane.add(btnHuy);
            buttonPane.add(btnGhi);
            textPane = new Panel(new GridLayout(6, 2));
            txtID = new JTextField(6);
            txtMakhach = new JTextField(15);
            txtTenkhach = new JTextField(15);
            txtDiachi = new JTextField(10);
            txtNamsinh = new JTextField(15);
            txtSdt = new JTextField(15);
            textPane.add(new JLabel("ID:"));
            textPane.add(txtID);
            textPane.add(new JLabel("Ma Khach:"));
            textPane.add(txtMakhach);
            textPane.add(new JLabel("Ten Khach:"));
            textPane.add(txtTenkhach);
            textPane.add(new JLabel("Dia Chi:"));
            textPane.add(txtDiachi);
            textPane.add(new JLabel("Nam Sinh"));
            textPane.add(txtNamsinh);
            textPane.add(new JLabel("Sdt"));
            textPane.add(txtSdt);
            ButtonJTextFieldControl(0);
            southPane = new Panel(new BorderLayout());
            southPane.add(buttonPane, BorderLayout.NORTH);
            southPane.add(textPane, BorderLayout.CENTER);
            this.getContentPane().add(tablePane, BorderLayout.CENTER);
            this.getContentPane().add(southPane, BorderLayout.SOUTH);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(500, 400);
            this.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void ButtonJTextFieldControl(int mode) {
        //mod ban dau hoặc ấn nút hủy
        if (mode == 0) {
            btnGhi.setEnabled(false);
            btnHuy.setEnabled(false);
            btnSua.setEnabled(true);
            btnThem.setEnabled(true);
            btnXoa.setEnabled(true);
            txtID.setText(String.valueOf(myModel.getValueAt(1, 0)));
            txtID.setEditable(false);
            txtMakhach.setText(String.valueOf(myModel.getValueAt(1, 1)));
            txtMakhach.setEditable(false);
            txtTenkhach.setText(String.valueOf(myModel.getValueAt(1, 2)));
            txtTenkhach.setEditable(false);
            txtDiachi.setText(String.valueOf(myModel.getValueAt(1, 3)));
            txtDiachi.setEditable(false);
            txtNamsinh.setText(String.valueOf(myModel.getValueAt(1, 4)));
            txtNamsinh.setEditable(false);
            txtSdt.setText(String.valueOf(myModel.getValueAt(1, 5)));
            txtSdt.setEditable(false);
        }
        //mode khi an nut them
        if (mode == 1) {
            btnGhi.setEnabled(true);
            btnHuy.setEnabled(true);
            btnSua.setEnabled(false);
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            txtDiachi.setEditable(true);
            txtDiachi.setText("");
            txtMakhach.setEditable(true);
            txtMakhach.setText("");
            txtID.setEditable(true);
            txtID.setText("");
            txtNamsinh.setEditable(true);
            txtNamsinh.setText("");
            txtTenkhach.setEditable(true);
            txtTenkhach.setText("");
            txtSdt.setEditable(true);
            txtSdt.setText("");
        }
        //mode khi an nui sua
        if (mode == 2) {
            btnGhi.setEnabled(true);
            btnHuy.setEnabled(true);
            btnSua.setEnabled(false);
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            txtDiachi.setEditable(true);
            txtMakhach.setEditable(true);
            txtID.setEditable(false);
            txtNamsinh.setEditable(true);
            txtTenkhach.setEditable(true);
            txtSdt.setEditable(true);
        }
    }

    public void reLoadData() {
        ResultSet rs = connecdata.getAllData();
        try {
            MyTableModel myModel = new MyTableModel(rs);
            table.setModel(myModel);
            table.getColumnModel().getColumn(0).setPreferredWidth(15);
            table.getColumnModel().getColumn(1).setPreferredWidth(130);
            table.getColumnModel().getColumn(2).setPreferredWidth(200);
            table.getColumnModel().getColumn(3).setPreferredWidth(50);
            table.getColumnModel().getColumn(4).setPreferredWidth(50);
            table.getColumnModel().getColumn(5).setPreferredWidth(40);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new FormData2();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (e.getSource() == btnXoa) {
            if (txtID.getText() != null) {
                int ma = Integer.parseInt(txtID.getText());
                connecdata.DeleteID(ma);
                reLoadData();
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
            }
        }
        if (e.getSource() == btnThem) {
            modeedit_save = true;
            ButtonJTextFieldControl(1);
        } else if (e.getSource() == btnSua) {
            modeedit_save = false;
            System.out.println("Sua");
            ButtonJTextFieldControl(2);
        }
        if (e.getSource() == btnGhi) {
            try {
                String _id = txtID.getText();
                int id = Integer.valueOf(_id);
                String _maKhach = txtMakhach.getText();
                String _tenKhach = txtTenkhach.getText();
                String _diaChi = txtDiachi.getText();
                String _namSinh = txtNamsinh.getText();
                String _sdt = txtSdt.getText();
                if (modeedit_save) {
                    if (id > 0 && id < 100000) {
                        ResultSet rs = connecdata.getAllData();
                        if (!connecdata.checkID(_id, rs)) {
                            connecdata.insertRowPreparedStatement(id, _maKhach, _tenKhach, _diaChi,
                                     _namSinh, _sdt);
                            reLoadData();
                            JOptionPane.showMessageDialog(this, "Thêm thành công!");
                            ButtonJTextFieldControl(0);
                        } else {
                            JOptionPane.showMessageDialog(this, "ID đã tồn tại. Vui lòng chọn ID khác!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "ID không hợp lệ. Vui lòng nhập lại!");
                    }
                } else {
                    connecdata.updateKhachHangID(id, _maKhach, _tenKhach, _diaChi, _namSinh, _sdt);
                    reLoadData();
                    JOptionPane.showMessageDialog(this, "Sửa thành công!");
                    ButtonJTextFieldControl(0);
                }
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(this, "ID không hợp lệ. Vui lòng nhập lại!");
            }
        }
        if (e.getSource() == btnHuy) {
            ButtonJTextFieldControl(0);
            modeedit_save = false;
        }
    }

    class MyTableSelectionListener2 implements ListSelectionListener {
        JTable table;
        public MyTableSelectionListener2(JTable _table) {
            table = _table;
        }
        public void valueChanged(ListSelectionEvent e) {
            int row = table.getSelectedRow();
            int col = table.getSelectedColumn();
            if (row >= 0) {
                System.out.println("Chon dong " + row + " Cot " + col);
                System.out.println("get Value" + table.getValueAt(row, col));
                txtID.setText(String.valueOf(table.getValueAt(row, 0)));
                txtMakhach.setText(String.valueOf(table.getValueAt(row, 1)));
                txtTenkhach.setText(String.valueOf(table.getValueAt(row, 2)));
                txtDiachi.setText(String.valueOf(table.getValueAt(row, 3)));
                txtNamsinh.setText(String.valueOf(table.getValueAt(row, 4)));
                txtSdt.setText(String.valueOf(table.getValueAt(row, 5)));

            }
        }

    }
}