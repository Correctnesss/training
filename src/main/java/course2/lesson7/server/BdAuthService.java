package course2.lesson7.server;

import java.sql.*;

public class BdAuthService implements AuthService {

    private static Connection connection;

    private static Statement statement;

//    public BdAuthService() {
//        entries = new ArrayList<>();
//        entries.add(new BdAuthService.Entry("login1", "pass1", "nick1"));
//        entries.add(new BdAuthService.Entry("login2", "pass2", "nick2"));
//        entries.add(new BdAuthService.Entry("login3", "pass3", "nick3"));
//    }

    @Override
    public void start() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:demobase.db");
        statement = connection.createStatement();
    }

    @Override
    public void stop() {

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
    }

    @Override
    public String getNickByLoginAndPass(String login, String pass) {

        try (ResultSet rs = statement.executeQuery("select nick from chat where login = '" + login + "' and password = '" + pass + "'")) {
            return rs.getString("nick");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        entries.stream()
//                .filter(entry -> entry.login.equals(login) && entry.password.equals(pass))
//                .map(entry -> entry.nick)
//                .findFirst();

//        for (BdAuthService.Entry entry : entries) {
//            if (entry.login.equals(login) && entry.password.equals(pass)) {
//                return entry.nick;
//            }
//        }
//        return Optional.empty();
        return null;
    }

    @Override
    public String changeNick(String nick, String nick1) {
        try (ResultSet rs = statement.executeQuery("update chat set nick ='" + nick + "'  where nick = '" + nick1 + "'")) {
            return rs.getString("nick");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private class Entry {
        private String login;
        private String password;
        private String nick;

        public Entry(String login, String password, String nick) {
            this.login = login;
            this.password = password;
            this.nick = nick;
        }
    }
}
