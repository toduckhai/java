package SQL;

import java.sql.*;

public class ConnectionDatabase {

    private final String className = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/mydatabase";
    private final String user = "root";
    private final String pass = "";
    private final String table = "sinhvien";
    private Connection connection;

    public void initConnection() {

        try {
            Class.forName(className);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", user, pass);
            //  DriverManager.getConnection(url, user, pass);
            System.out.println("Ket noi ok");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getAllData() {
        ResultSet rs = null;
        String sqlcomand = "select * from " + table;
        try {
            if (connection != null) {
                Statement st = connection.createStatement();
                rs = st.executeQuery(sqlcomand);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    private void showData(ResultSet rs) {
        try {
            while (rs.next()) {
                System.out.printf("%-2s", rs.getInt(1));
                System.out.printf("%-20s", rs.getString(2));
                System.out.printf("%-15s", rs.getString(3));
                System.out.printf("%-15s", rs.getString(4));
                System.out.printf("%-15s", rs.getString(5));
                System.out.printf("\n");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getADataID(String _id) {
        ResultSet rs = null;
        String sqlcomand = "select * from " + table + " where ID=" + _id;
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlcomand);
            rs = pst.executeQuery(sqlcomand);
            rs.close();
        } catch (SQLException ex) {
            System.out.println(sqlcomand);
            ex.printStackTrace();
        }
        return rs;
    }

    public void closeConnec() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void insertRow(int _id, String _hoTen, String _diaChi,
            String _tenLop,
            String ns) {
        String insertQuery = "insert into sinhvien values ('"
                + _id + "','" + _hoTen + "','"
                + _diaChi + "','" + _tenLop + "','" + ns + "');";
        //+_diaChi+"','"+_tenLop+"','2005/01/01');";
        System.out.println(insertQuery);
        if (this.connection != null) {
            try {
                Statement st = connection.createStatement();
                int n = st.executeUpdate(insertQuery);
                System.out.println("insert ok" + n);
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void insertRowPreparedStatement(int _id, String _hoTen,
            String _diaChi, String _tenLop, String _ns) {
        String insertQuery = "INSERT INTO SINHVIEN VALUES(?,?,?,?,?);";
        if (this.connection != null) {
            try {//connection.setAutoCommit(false);
                PreparedStatement pst
                        = connection.prepareStatement(insertQuery);
                pst.setInt(1, _id);
                pst.setString(2, _hoTen);
                pst.setString(3, _diaChi);
                pst.setString(4, _tenLop);
                pst.setString(5, _ns);
                System.out.println(pst.toString());
                int n = pst.executeUpdate();
                System.out.println("insert ok");
                pst.close();
                // connection.commit();
                //connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean DeleteID(int _id) {
        String sql = "delete from SINHVIEN where ID=?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, _id);
            System.out.println(pr.toString());
            return pr.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateSinhVienID(int _id, String _hoTen, String _diaChi,
            String _tenLop, String _ns) {
        int k=0;
        String sql = "update sinhvien set hoten=?,diachi=?,tenlop=?,namsinh=? "
                + "where id=?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, _hoTen);
            pr.setString(2, _diaChi);
            pr.setString(3, _tenLop);
            pr.setString(4, _ns);
            pr.setInt(5, _id);
            k=pr.executeUpdate();
           
            return k > 0;
        } catch (SQLException e) {
             System.out.println("gia tri"+k);
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkID(String _id, ResultSet _rs) {
        try {
            while (_rs.next()) {
                if (_rs.getInt(1) == Integer.valueOf(_id)) {
                    _rs.close();
                    return true;
                }
            }
            _rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String args[]) {
        ConnectionDatabase kn = new ConnectionDatabase();
        kn.initConnection();
        kn.showData(kn.getAllData());
        // kn.DeleteID(7);

        //kn.showData(kq);
     // kn.updateSinhVienID(11, "Do Ai Quoc1", "Thái Binh", "K16a KTPM", "2000/02/02");
        //System.out.println("sau khi update");
        //kn.showData(kn.getAllData(connection,table));
        //kn.closeConnec();
         //kn.DeleteID(12);
        // System.out.println("Sau khi xoa");
        // kn.createTable(); 
        //kn.insertRow((int) 7, "Nguyễn Phú Trọng", "Hà Nội", "K16", "2000/01/12");
        //kn.transaction();khi 
        // kn.insertRowPreparedStatement((int)13, "Nguyen Van Thong", "Nam Dinh", "K16A", "1999/01/01");
        //System.out.println("Sau khi insert");
        // System.out.println("du lieu sau khi xoa la:");
        //kn.getADataID("01"); 
        //System.out.println("sau khi sua");
       //kn.showData(kn.getAllData());
    }
}