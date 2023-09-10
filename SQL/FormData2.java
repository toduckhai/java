package SQL;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
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
    JTextField txtID, txtHoten, txtDiachi, txtLop, txtNamsinh;    
    boolean modeedit_save = false;

    public FormData2() {
        super("Form kết nối CSDL");
        JScrollPane tablePane;
        Panel southPane, textPane, buttonPane;
        connecdata = new ConnectionDatabase();
        connecdata.initConnection();
        ResultSet rs = connecdata.getAllData();
        try {
            myModel = new MyTableModel(rs);
            table = new JTable(myModel);
            // thiet lap do rong cot cho Table
            table.getColumnModel().getColumn(0).setPreferredWidth(15);
            table.getColumnModel().getColumn(1).setPreferredWidth(130);
            table.getColumnModel().getColumn(2).setPreferredWidth(200);
            table.getColumnModel().getColumn(3).setPreferredWidth(50);
            table.getColumnModel().getColumn(4).setPreferredWidth(90);
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
            btnGhi = new JButton("Ghi");
            btnGhi.addActionListener(this);
            buttonPane.add(btnThem);
            buttonPane.add(btnSua);
            buttonPane.add(btnXoa);
            buttonPane.add(btnHuy);
            buttonPane.add(btnGhi);
            textPane = new Panel(new GridLayout(5, 2));
            txtID = new JTextField(5);
            txtHoten = new JTextField(15);
            txtDiachi = new JTextField(15);
            txtLop = new JTextField(10);
            txtNamsinh = new JTextField(15);
            textPane.add(new JLabel("ID:"));
            textPane.add(txtID);
            textPane.add(new JLabel("Họ Tên:"));
            textPane.add(txtHoten);
            textPane.add(new JLabel("Dia Chi:"));
            textPane.add(txtDiachi);
            textPane.add(new JLabel("Lớp:"));
            textPane.add(txtLop);
            textPane.add(new JLabel("Nam Sinh"));
            textPane.add(txtNamsinh);
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
            txtHoten.setText(String.valueOf(myModel.getValueAt(1, 1)));
            txtHoten.setEditable(false);
            txtDiachi.setText(String.valueOf(myModel.getValueAt(1, 2)));
            txtDiachi.setEditable(false);
            txtLop.setText(String.valueOf(myModel.getValueAt(1, 3)));
            txtLop.setEditable(false);
            txtNamsinh.setText(String.valueOf(myModel.getValueAt(1, 4)));
            txtNamsinh.setEditable(false);
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
            txtHoten.setEditable(true);
            txtHoten.setText("");
            txtID.setEditable(true);
            txtID.setText("");
            txtNamsinh.setEditable(true);
            txtNamsinh.setText("");
            txtLop.setEditable(true);
            txtLop.setText("");
        }
        //mode khi an nui sua
        if (mode == 2) {
            btnGhi.setEnabled(true);
            btnHuy.setEnabled(true);
            btnSua.setEnabled(false);
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            txtDiachi.setEditable(true);
            txtHoten.setEditable(true);
            txtID.setEditable(false);
            txtNamsinh.setEditable(true);
            txtLop.setEditable(true);
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
            table.getColumnModel().getColumn(4).setPreferredWidth(90);
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
                System.out.print("Xoa ok");
                reLoadData();
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
                String _hoTen = txtHoten.getText();
                String _diaChi = txtDiachi.getText();
                String _lop = txtLop.getText();
                String _namSinh = txtNamsinh.getText();
                if (modeedit_save) {
                    if (id > 0 && id < 100000) {
                        ResultSet rs = connecdata.getAllData();
                        if (!connecdata.checkID(_id, rs)) {
                            connecdata.insertRowPreparedStatement(id, _hoTen, _diaChi,
                                     _lop, _namSinh);
                            reLoadData();
                            System.out.println("ghi ok");
                            ButtonJTextFieldControl(0);
                        } else {
                            System.out.println("Trung ID");
                        }
                    } else {
                        System.out.println("ID không hợp lệ");
                    }
                } else {
                    connecdata.updateSinhVienID(id, _hoTen, _diaChi, _lop, _namSinh);
                    reLoadData();
                    System.out.println("sửa  ok");
                    ButtonJTextFieldControl(0);
                }
            } catch (NumberFormatException e1) {
                System.out.println("ID không khác rỗng");
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
                txtHoten.setText(String.valueOf(table.getValueAt(row, 1)));
                txtDiachi.setText(String.valueOf(table.getValueAt(row, 2)));
                txtLop.setText(String.valueOf(table.getValueAt(row, 3)));
                txtNamsinh.setText(String.valueOf(table.getValueAt(row, 4)));

            }
        }

    }
}