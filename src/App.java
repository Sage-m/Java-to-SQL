import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        String sql = "select * from movie";
        String url = "jdbc:mysql://localhost:3306/javatosql";
        String username = "";
        String password = "";

        Connection con = DriverManager.getConnection(url, username, password);

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        rs.next();
        String name = rs.getString(2);
        System.out.println(name);

    }
}
