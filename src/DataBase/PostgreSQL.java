package DataBase;

import com.github.mauricio.async.db.postgresql.PostgreSQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSQL {
    public void tryConnection(String url, String user, String password){
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT VERSION()")) {

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(PostgreSQLConnection.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
