package course3.lesson2;

import java.sql.*;
import java.util.Random;

public class JdbcApp1 {

    private static Connection connection;
    private static Statement statement;
    private static final Random random = new Random();

    public static void main(String[] args) {
        try {
            connect();
            createTable();
//            long start = System.nanoTime();
//            insertStudents();
//            System.out.println("insert stmt " + (System.nanoTime() - start + "ms"));
//            start = System.nanoTime();
//            insertStudentsBatch();
//            System.out.println("insert batch " + (System.nanoTime() - start + "ms"));

            insertOneStudents("nick2", "pass2");
//            readData();
//            dropTable();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:demobase.db");
        statement = connection.createStatement();
    }


    private static void disconnect() {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //close smth else
    }

    private static void createTable() throws SQLException {
        statement.executeUpdate("create table if not exists chat (\n" +
                " id integer primary key autoincrement not null, \n" +
                " nick text not null, \n" +
                " password text not null,\n" +
                " login text not null\n" +
                ");");
    }

    private static void insertStudents() throws SQLException {
        for (int i = 0; i < 10; i++) {
            statement.executeUpdate("insert into students (name, group_name, score) " +
                    "values ('Bob" + i + "', '22', 3)");
        }
    }

    private static void insertStudentsBatch() {
        try (PreparedStatement ps = connection.prepareStatement("insert into students (name, group_name, score) " +
                "values (?, ?, ?)")) {
            for (int i = 0; i < 10; i++) {
                ps.setString(1, "Jack " + i);
                ps.setString(2, "group " + (10 - i));
                ps.setInt(3, random.nextInt(6));
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // name = ' drop database; '
    private static void insertOneStudents(String name, String group) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("insert into chat (nick, password, login) " +
                "values (?, ?, ?)")) {
            ps.setString(1, name);
            ps.setString(2, group);
            ps.setString(3, "login2");
            ps.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void readData() {
        try (ResultSet rs = statement.executeQuery("select * from students where id=1 and group_name = 22")) {
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString("name") +
                        " " + rs.getString(3) + " " + rs.getString("score"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void dropTable() throws SQLException {
        statement.executeUpdate("drop table chat");
    }
}
