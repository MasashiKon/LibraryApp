package services;

import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDaoService {
    private static final String url = "jdbc:sqlite:resources/PUBLIC_LIBRARY.db";

    public static void loadDriver() {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("The driver was successfully loaded.");
        } catch (ClassNotFoundException e) {
            System.out.println("The driver class was not found in the program files at runtime.");
            System.out.println(e);
            System.exit(1);
        }
    }

    public static void testDatabaseConnection() {

        try (Connection connection = DriverManager.getConnection(url)) {
            System.out.println("The connection to the SQLite database was successful!");
        } catch (SQLException e) {
            System.out.println("The connection to the database was unsuccessful!");
            System.out.println(e);
        }
    }

    public static void createTable() {
        String createTableStatement = "CREATE TABLE IF NOT EXISTS USERS ("
                + "USER_ID INTEGER PRIMARY KEY,"
                + "USER_F_NAME TEXT NOT NULL,"
                + "USER_L_NAME TEXT,"
                + "USER_PHONE_NUMBER TEXT,"
                + "USER_ADDRESS TEXT,"
                + "USER_BOOK TEXT"
                + ");";

        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(createTableStatement);
            System.out.println("The USERS table has been created.");
        } catch (SQLException e) {
            System.out.println("There was an error with your request.");
            System.out.println(e);
        }
    }

    public static void dropTable() {
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate("DROP TABLE IF EXISTS USERS;");
            System.out.println("The USERS table has been dropped.");
        } catch (SQLException e) {
            System.out.println("There was an error with your request.");
            System.out.println(e);
        }
    }

    public static void saveUsers(List<User> users) {
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement()
        ) {
            for (User user : users) {
                String insertIntoBook = "INSERT INTO USERS (USER_ID, USER_F_NAME, USER_L_NAME, USER_PHONE_NUMBER, USER_ADDRESS, USER_BOOK)"
                        + "VALUES ("
                        + "CAST('" + user.getId() + "' AS INTEGER),\""
                        + user.getFirstName() + "\",\""
                        + user.getLastName() + "\",\""
                        + user.getPhoneNumber() + "\",\""
                        + user.getAddress() + "\",\""
                        + user.getBook()
                        + "\");";
                statement.executeUpdate(insertIntoBook);
            }
            System.out.println("The books have been saved to the BOOKS table.");
        } catch (SQLException e) {
            System.out.println("There was an error with your request.");
            System.out.println(e);
        }
    }

    public static void saveUser(User user) {
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement()
        ) {
            String insertIntoBook = "INSERT INTO USERS (USER_ID, USER_F_NAME, USER_L_NAME, USER_PHONE_NUMBER, USER_ADDRESS, USER_BOOK)"
                    + "VALUES ("
                    + "CAST('" + user.getId() + "' AS INTEGER),\""
                    + user.getFirstName() + "\",\""
                    + user.getLastName() + "\",\""
                    + user.getPhoneNumber() + "\",\""
                    + user.getAddress() + "\",\""
                    + user.getBook()
                    + "\");";
            statement.executeUpdate(insertIntoBook);
            System.out.println("The user have been saved to the BOOKS table.");
        } catch (SQLException e) {
            System.out.println("There was an error with your request.");
            System.out.println(e);
        }
    }

    public static User loadUserById(int id) {
        User user = null;
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery("SELECT * FROM USERS WHERE USER_ID=" + id + ";")
        ) {
            System.out.println("Trying to load the user from the database...\n");
            while(results.next()) {
                user = new User(results.getInt(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5), results.getString(6));
            }
            System.out.println("The user were loaded from the database.");
        } catch (SQLException e) {
            System.out.println("There was an error with your request.");
            System.out.println(e);
        }
        return user;
    }

    public static User loadUserByName(String name) {
        User user = null;
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery("SELECT * FROM USERS WHERE USER_F_NAME=\"" + name + "\";")
        ) {
            System.out.println("Trying to load the user from the database...\n");
            while(results.next()) {
                user = new User(results.getInt(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5), results.getString(6));
            }
        } catch (SQLException e) {
            System.out.println("There was an error with your request.");
            System.out.println(e);
        }
        return user;
    }

    public static ArrayList<User> loadAllUsers() {
        ArrayList<User> bookArrayList = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery("SELECT * FROM USERS;")
        ) {
            System.out.println("Trying to load all users from the database...\n");
            while(results.next()) {
                bookArrayList.add(new User(results.getInt(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5), results.getString(6)));
            }
            System.out.println("All users were loaded from the database.");
        } catch (SQLException e) {
            System.out.println("There was an error with your request.");
            System.out.println(e);
        }
        return bookArrayList;
    }

    public static int searchMinID() {
        int id = 0;
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery("SELECT USER_ID FROM USERS;")
        ) {
            ArrayList<Integer> idList = new ArrayList<>();
            while (results.next()) {
                idList.add(results.getInt(1));
            }
            Collections.sort(idList);

            for (Integer num : idList) {
                if (num == id) {
                    id++;
                } else {
                    return id;
                }
            }
            id = idList.size() + 1;
        } catch (SQLException e) {
            System.out.println("There was an error with your request.");
            System.out.println(e);
        }
        return id;
    }

    public static void removeUserById(int id) {
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate("DELETE FROM USERS WHERE USER_ID = " + id + ";");
            System.out.println("The user is successfully removed.");
        } catch (SQLException e) {
            System.out.println("There was an error with your request.");
            System.out.println(e);
        }
    }

    public static void bookCheckOut(int id, String book) {
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate("UPDATE USERS SET USER_BOOK = \"" + book + "\" WHERE USER_ID = " + id + ";");
            System.out.println("Database updated\n");
        } catch (SQLException e) {
            System.out.println("There was an error with your request.");
            System.out.println(e);
        }
    }

    public static void bookReturn(int id) {
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate("UPDATE USERS SET USER_BOOK = " + null + " WHERE USER_ID = " + id + ";");
            System.out.println("Database updated\n");
        } catch (SQLException e) {
            System.out.println("There was an error with your request.");
            System.out.println(e);
        }
    }
}
