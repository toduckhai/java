package SQL;

/**
 *
 * @author 84989
 */
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDemo0 {
    public static void main(String[] args) {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabse","root","");
            System.out.println("connection ok");
        } 
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e1) {
            e1.printStackTrace();
        }
        try {
            Statement stm = con.createStatement();
            String sql1 = "select * from sinhvien";
            ResultSet rs = stm.executeQuery(sql1);
            while(rs.next()){
            System.out.print(rs.getInt(1)+"-");
            System.out.print(rs.getString("hoten")+"-");
            System.out.print(rs.getString("diachi")+"-");
            System.out.print(rs.getString(4)+"-");
            System.out.print(rs.getInt(5));
            System.out.println("");
            }
            rs.close();
            stm.close();
            con.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
}