package SQL;

/**
 *
 * @author Hp
 */
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Hp
 */
public class FormData1 extends JFrame  {
    JScrollPane tablePane;
    JTable table;
    MyTableModel myModel;
    ConnectionDatabase connecdata;
    public FormData1() {
        super("Form kết nối CSDL");
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
            MyTableSelectionListener myListener=new MyTableSelectionListener(table);
            table.getSelectionModel().addListSelectionListener(myListener);            
            tablePane = new JScrollPane(table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.setLayout(new BorderLayout());
        this.getContentPane().add(tablePane, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);
        this.setVisible(true);
    }
    public static void main(String args[]) {
        new FormData1();
    }
}
class MyTableSelectionListener implements ListSelectionListener{
JTable table;
   public MyTableSelectionListener(JTable _table){
       table=_table;
   }
           
    public void valueChanged(ListSelectionEvent e) {
        int row=table.getSelectedRow();
      //  table.getSe
        int col=table.getSelectedColumn();
        if(row>=0) System.out.println("Chon dong "+row+" Cot "+col);
        
    }
    
}