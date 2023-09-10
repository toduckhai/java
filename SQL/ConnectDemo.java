package SQL;



import java.sql.*;

/**
 *
 * @author DELL
 */
public class ConnectDemo {
Connection con;
    public  ConnectDemo() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
           
        } 
        catch (ClassNotFoundException e1) {
            e1.printStackTrace();
             
        }      

    }
    public ResultSet getData(){
        ResultSet r=null;
        if(con!=null){
            try{
            Statement st=con.createStatement();
            r=st.executeQuery("select * from sinhvien");
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
         System.out.println(" ok");
        return r;
    }
    public void insert(int _id,String _hoTen,String _diaChi, String _tenLop,int _namSinh){
        String sql="Insert into sinhvien (id,hoten,diachi,tenlop,namsinh) values (?,?,?,?,?)";
        if(con!=null){
        try{ PreparedStatement pr=con.prepareStatement(sql);
        pr.setInt(1, _id);
        pr.setString(2,_hoTen);
        pr.setString(3,_diaChi);
        pr.setString(4,_tenLop);
        pr.setInt(5,_namSinh);
            System.out.println(pr.toString());
        pr.executeUpdate();
      pr.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
            
        }
    }
    public void updateSinhVien(int _id,String _hoTen, String _diaChi,String _tenLop, int _namSinh){
        String sql="update sinhvien set hoTen=?,diaChi=?,tenLop=?,namSinh=? where id=?";
        try{ PreparedStatement pr=con.prepareStatement(sql);
        pr.setString(1,_hoTen);
        pr.setString(2,_diaChi);
        pr.setString(3,_tenLop);
        pr.setInt(4,_namSinh);
        pr.setInt(5,_id);
        pr.executeUpdate();
           pr.close();
        }catch(SQLException e1){
            e1.printStackTrace();
        }
    }
    public void showData(ResultSet rs){
        try {
            while (rs.next()) {
                System.out.printf("%-2s", rs.getInt(1));
                System.out.printf("%-20s", rs.getString(2));
                System.out.printf("%-15s", rs.getString(3));
                System.out.printf("%-15s", rs.getString(4));
                 System.out.printf("%-15s", rs.getInt(5));
                System.out.printf("\n");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   

    public static void main(String args[]) {
        ConnectDemo demo = new ConnectDemo();
        demo.showData(demo.getData()); 
       
    // demo.insert(5,"Dang van Ren","Thai Binh", "K17A", 1992);
      demo.updateSinhVien(5, "Dang van Ban","Nam Dinh", "K18A", 1994);
//        ResultSet r=demo.getData();
//       if(r!=null){
//             System.out.println("du lieu sau khi insert");
//             demo.showData(r);
//       }
 System.out.println("sau khi update");
demo.showData(demo.getData());
    }
}