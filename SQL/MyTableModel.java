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
   String tenCot[]={"stt","Họ Và Tên","Dịa Chỉ","Năm Sinh","Lớp"};
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
           //rowData={1,"to huu nguyen", "1980","null}
           Vector rowData=new Vector();
           for(int i=1;i<=Colcount;i++){
               rowData.addElement(rs.getObject(i));               
               }
           // thêm dòng dữ liệu vào tblData
           tblData.addElement(rowData);      
           //tblData{{{1,"to huu nguyen", "1980","null},{7,"Dang Van Ren", "k17A",1992}}
       }      
          }             
    public int getRowCount() {
        return tblData.size();
    }
    @Override
    public int getColumnCount() {
        return Colcount;
       //return colName.size();
    }
    @Override
    public String getColumnName(int columnIndex) {
    return (String)colName.elementAt(columnIndex);
      // return tenCot[columnIndex];
    }   
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ///rowData1={2,"nguyen Van C","thai Nguyen","KTMPk18","1990"}
        Vector rowData=(Vector)tblData.elementAt(rowIndex);
        return rowData.elementAt(columnIndex);
       
//return 0;
    }

    

    
}