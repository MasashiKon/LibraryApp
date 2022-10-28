package services;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import models.Book;
import models.User;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class BookDaoService {
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
        String createTableStatement = "CREATE TABLE IF NOT EXISTS BOOKS ("
                + "BOOK_ID INTEGER PRIMARY KEY,"
                + "BOOK_TITLE TEXT NOT NULL,"
                + "BOOK_AUTHOR TEXT,"
                + "BOOK_GENRE TEXT,"
                + "BOOK_ADDRESS TEXT,"
                + "BOOK_DESCRIPTION TEXT,"
                + "BOOK_ISAVAILABLE BOOLEAN NOT NULL"
                + ");";

        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(createTableStatement);
            System.out.println("The BOOKS table has been created.");
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
            statement.executeUpdate("DROP TABLE IF EXISTS BOOKS;");
            System.out.println("The BOOKS table has been dropped.");
        } catch (SQLException e) {
            System.out.println("There was an error with your request.");
            System.out.println(e);
        }
    }

    public static void saveBooks(List<Book> Books) {
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement()
        ) {
            for (Book book : Books) {
                String insertIntoBook = "INSERT INTO BOOKS VALUES ("
                        + "CAST('" + book.getId() + "' AS INTEGER),\""
                        + book.getTitle() + "\",\""
                        + book.getAuthor() + "\",\""
                        + book.getGenre() + "\",\""
                        + book.getAddress() + "\",\""
                        + book.getDescription() + "\",\""
                        + (book.isAvailable() ? 1 : 0)
                        + "\");";
                statement.executeUpdate(insertIntoBook);
            }
            System.out.println("The books have been saved to the BOOKS table.");
        } catch (SQLException e) {
            System.out.println("There was an error with your request.");
            System.out.println(e);
        }
    }

    public static void saveBook(Book book) {
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement()
        ) {
            String insertIntoBook = "INSERT INTO BOOKS VALUES ("
                    + "CAST('" + book.getId() + "' AS INTEGER),\""
                    + book.getTitle() + "\",\""
                    + book.getAuthor() + "\",\""
                    + book.getGenre() + "\",\""
                    + book.getAddress() + "\",\""
                    + book.getDescription() + "\",\""
                    + (book.isAvailable() ? 1 : 0)
                    + "\");";
            statement.executeUpdate(insertIntoBook);
            System.out.println("The book have been saved to the BOOKS table.");
        } catch (SQLException e) {
            System.out.println("There was an error with your request.");
            System.out.println(e);
        }
    }

    public static Book loadBookById(int id) {
        Book book = null;
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery("SELECT * FROM BOOKS WHERE BOOK_ID=" + id + ";")
        ) {
            System.out.println("Trying to load the book from the database...\n");
            while(results.next()) {
                book = new Book(results.getInt(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5), results.getString(6), results.getBoolean(7));
            }
            System.out.println("The user were loaded from the database.");
        } catch (SQLException e) {
            System.out.println("There was an error with your request.");
            System.out.println(e);
        }
        return book;
    }

    public static ArrayList<Book> loadAllBooks() {
        ArrayList<Book> bookArrayList = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery("SELECT * FROM BOOKS;")
        ) {
            System.out.println("Trying to load all books from the database...\n");
            while(results.next()) {
                bookArrayList.add(new Book(results.getInt(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5), results.getString(6), results.getBoolean(7)));
            }
            System.out.println("All books were loaded from the database.");
        } catch (SQLException e) {
            System.out.println("There was an error with your request.");
            System.out.println(e);
        }
        return bookArrayList;
    }

    public static int searchMinID() {
        int id = 1;
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery("SELECT BOOK_ID FROM BOOKS;")
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

    public static void removeBookById(int id) {
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate("DELETE FROM BOOKS WHERE BOOK_ID = " + id + ";");
            System.out.println("The book is successfully removed.");
        } catch (SQLException e) {
            System.out.println("There was an error with your request.");
            System.out.println(e);
        }
    }

    public static ArrayList<Book> searchBooksByString(String property, String word) {
        ArrayList<Book> bookArrayList = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery("SELECT * FROM BOOKS WHERE " + property + " LIKE " + "'%" + word + "%';")
        ) {
            System.out.println("Searching books from the database...\n");
            while(results.next()) {
                bookArrayList.add(new Book(results.getInt(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5), results.getString(6), results.getBoolean(7)));
            }
            System.out.println("All books were loaded from the database.");
        } catch (SQLException e) {
            System.out.println("There was an error with your request.");
            System.out.println(e);
        }
        return bookArrayList;
    }

    public static void switchIsAvailable(int id, boolean bool) {
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate("UPDATE BOOKS SET BOOK_ISAVAILABLE = CAST('" + (bool ? 1 : 0) + "' AS BOOLEAN) WHERE BOOK_ID = " + id + ";");
            System.out.println("Database updated\n");
        } catch (SQLException e) {
            System.out.println("There was an error with your request.");
            System.out.println(e);
        }
    }
}
