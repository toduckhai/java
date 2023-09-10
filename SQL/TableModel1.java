package SQL;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
public class TableModel1 extends AbstractTableModel  {
    ResultSet rs;
    int colcount;
    Vector table=new Vector();
    TableModel1(ResultSet _rs){
        rs=_rs;        
    try{
        ResultSetMetaData rsm=rs.getMetaData();
        colcount=rsm.getColumnCount();
    while(rs.next()){
        Vector row=new Vector();
        for(int i=0;i<colcount;i++)
        {row.addElement(rs.getObject(i));
        }
        table.addElement(row);
    }
}catch(SQLException e){
    e.printStackTrace();
}   
    }

    @Override
    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}