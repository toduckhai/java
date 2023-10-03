package SQL;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
public class MyTableModel extends AbstractTableModel{
   Vector tblData;// chưa du lieu bang
   Vector colName;// chứa tiêu đề cột
   String tenCot[]={"stt","Ma Khach","Ten Khach","Dia Chi","Nam Sinh","SDT"};
   int Colcount=0; 
   
   public MyTableModel(ResultSet rs) throws SQLException{
       ResultSetMetaData rsmData=rs.getMetaData();
        Colcount=rsmData.getColumnCount();
       
       colName=new Vector(Colcount);
       tblData=new Vector();   
       
      
       // lấy về tên của tiêu đề cột thông qua ResultSetMetaData
       for(int i=1;i<=Colcount;i++)
           colName.addElement(rsmData.getColumnName(i));
       //duyệt Result set
       while(rs.next()){
           // tạo ra 1 dòng dữ liệu và lấy dữ liệu từ rs
           Vector rowData=new Vector();
           for(int i=1;i<=Colcount;i++){
               rowData.addElement(rs.getObject(i));               
               }
           // thêm dòng dữ liệu vào tblData
           tblData.addElement(rowData);     
           
       }      
          }             
    public int getRowCount() {
        return tblData.size();
    }
    @Override
    public int getColumnCount() {
        return Colcount;
       
    }
    @Override
    public String getColumnName(int columnIndex) {
    return (String)colName.elementAt(columnIndex);
     
    }   
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Vector rowData=(Vector)tblData.elementAt(rowIndex);
        return rowData.elementAt(columnIndex);
       
    }
}