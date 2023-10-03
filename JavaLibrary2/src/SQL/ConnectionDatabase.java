package SQL;

import java.sql.*;

public class ConnectionDatabase {

    private final String className = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/mydatabase";
    private final String user = "root";
    private final String pass = "";
    private final String table = "khachhang";
    private Connection connection;

    public void initConnection() {

        try {
            Class.forName(className);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", user, pass);
            
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
                System.out.printf("%-15s", rs.getString(6));
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

    private void insertRow(int _id, String _maKhach, String _tenKhach,
            String _diaChi,
            String _namSinh, String _sdt) {
        String insertQuery = "insert into khachhang values ('"
                + _id + "','" + _maKhach + "','" + _tenKhach+"','"
                + _diaChi + "','" + _namSinh + "','" + _sdt + "');";
        
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

    public void insertRowPreparedStatement(int _id, String _maKhach, String _tenKhach,
            String _diaChi, String _namSinh, String _sdt) {
        String insertQuery = "INSERT INTO KHACHHANG VALUES(?,?,?,?,?,?);";
        if (this.connection != null) {
            try {//connection.setAutoCommit(false);
                PreparedStatement pst
                        = connection.prepareStatement(insertQuery);
                pst.setInt(1, _id);
                pst.setString(2, _maKhach);
                pst.setString(3, _tenKhach);
                pst.setString(4, _diaChi);
                pst.setString(5, _namSinh);
                pst.setString(6, _sdt);
                System.out.println(pst.toString());
                int n = pst.executeUpdate();
                System.out.println("insert ok");
                pst.close();
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean DeleteID(int _id) {
        String sql = "delete from KHACHHANG where ID=?";
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

    public boolean updateKhachHangID(int _id, String _maKhach, String _tenKhach, String _diaChi,
            String _namSinh, String _sdt) {
        int k=0;
        String sql = "update khachhang set makhach=?,tenkhach=?,diachi=?,namsinh=?,sdt=? "
                + "where id=?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, _maKhach);
            pr.setString(2, _tenKhach);
            pr.setString(3, _diaChi);
            pr.setString(4, _namSinh);
            pr.setString(5, _sdt);
            pr.setInt(6, _id);
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
        
    }
}




