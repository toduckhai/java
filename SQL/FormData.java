package SQL;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Hp
 */
public class FormData extends JFrame {
    JScrollPane tblPane;
    JTable table;
    MyTableModel myModel;
    ConnectionDatabase connecdata;
    public FormData() {
        super("Form kết nối CSDL");
        connecdata = new ConnectionDatabase();
        connecdata.initConnection();
        ResultSet rs = connecdata.getAllData();
        try {
            myModel = new MyTableModel(rs);
            table = new JTable(myModel);
            tblPane = new JScrollPane(table);
            // thiet lap do rong cot cho Table
            table.getColumnModel().getColumn(0).setPreferredWidth(20);
            table.getColumnModel().getColumn(1).setPreferredWidth(130);
            table.getColumnModel().getColumn(2).setPreferredWidth(200);
            table.getColumnModel().getColumn(3).setPreferredWidth(50);
            table.getColumnModel().getColumn(4).setPreferredWidth(90);          
            } catch (SQLException e) {
                e.printStackTrace();
        }
        this.setLayout(new BorderLayout());
        this.getContentPane().add(tblPane, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);
        this.setVisible(true);

    }

    public static void main(String args[]) {
        new FormData();

    }

}