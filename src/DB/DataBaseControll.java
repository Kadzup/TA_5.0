package DB;

import Models.Client;

import java.sql.*;

public class DataBaseControll {
    public DataBaseControll(){}

    public DataBaseControll(String URL, String USER, String PASS){
        this.url = URL;
        this.user = USER;
        this.password = PASS;
    }

    private String url = "jdbc:postgresql://localhost/Travel_Agnecy";
    private String user = "postgres";
    private String password = "7336Alexco12";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public void getCountry(String pattern, int id) {

        String SQL = "SELECT * FROM Country";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1,id);
            pstmt.setString(2,pattern);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(String.format("%d %s",
                        rs.getInt("id"),
                        rs.getString("c_name")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getClientCount() {
        String SQL = "SELECT count(*) FROM Client";
        int count = 0;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }

    public void getWorkers() {

        String SQL = "SELECT id, f_name, s_name, b_day, salary, \"position\", address, phone FROM Worker";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            displayWorker(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void displayWorker(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getString("id") + "\t"
                    + rs.getString("f_name") + "\t"
                    + rs.getString("s_name") + "\t"
                    + rs.getString("b_day") + "\t"
                    + rs.getString("salary") + "\t"
                    + rs.getString("position") + "\t"
                    + rs.getString("address") + "\t"
                    + rs.getString("phone"));

        }
    }

    private void displayOrder(ResultSet rs) throws SQLException {
        while (rs.next()) {
            //id, tour_id, worker_id, client_id, order_date, dep_date, arr_date
            System.out.println(rs.getString("id") + "\t"
                    + rs.getString("tour_id") + "\t"
                    + rs.getString("worker_id") + "\t"
                    + rs.getString("client_id") + "\t"
                    + rs.getString("order_date") + "\t"
                    + rs.getString("dep_date") + "\t"
                    + rs.getString("arr_date"));

        }
    }

    public void FindOrderById(int orderId) {
        String SQL = "SELECT * "
                + "FROM Order "
                + "WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            displayOrder(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    /**
     * Client client = new Client.Builder()
     *                 .setBDay(LocalDate.of(2019,10, 10))
     *                 .setEmail("dstefurak@email.net")
     *                 .setFName("Dmytro")
     *                 .setLName("Stefurak")
     *                 .setPaymentInfo("0000000000000000")
     *                 .setPhone("380508834011")
     * */
    public long insertClient(Client client) {
        String SQL = "INSERT INTO \"Client\"(f_name, s_name, address, phone) "
                + "VALUES(?,?,?,?)";

        long id = 0;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, client.getfName());
            pstmt.setString(2, client.getlName());
            pstmt.setString(3, client.getPaymentInfo());
            pstmt.setString(4, client.getPhone());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }
}
