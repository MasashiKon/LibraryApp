package viewmodels;

import models.Book;
import models.User;
import services.BookDaoService;
import services.UserDaoService;

import java.util.ArrayList;

public class CuratorMenu {

    static void showMenu(User admin) throws InterruptedException {
        String option;

        do{

            App.clrscr();

            System.out.println("Hello, please select a option:\n"

                    + "1. Add books\n"
                    + "2. Delete book\n"
                    + "3. Search book\n"
                    + "4. Checkout\n"
                    + "5. Return book\n"
                    + "6. Show user info\n"
                    + "7. Register user\n"
                    + "8. Leave"

            );

            System.out.print("Please input a number: ");

            if(App.input.hasNext()) {

                System.out.println("yes");

            }

            option = App.input.nextLine();


            App.clrscr();



            switch(option) {

                case "1":

                    boolean quit = false;

                    do {

                        System.out.println("Please type book title:");
                        String title = App.input.nextLine();
                        System.out.println("Add another info?: Y/N");

                        do {
                            char answer = App.input.nextLine().toLowerCase().charAt(0);

                            if (answer == 'y') {

                                System.out.println("Please type author name:");
                                String author = App.input.nextLine();
                                System.out.println("Please type genre:");
                                String genre = App.input.nextLine();
                                System.out.println("Please type description:");
                                String description = App.input.nextLine();

                                Book book = new Book(BookDaoService.searchMinID(), title, author, genre, description);

                                BookDaoService.saveBook(book);
                                break;

                            } else if (answer == 'n') {

                                Book book = new Book(BookDaoService.searchMinID(), title);

                                BookDaoService.saveBook(book);

                                break;

                            } else {

                                System.out.println("Invalid input. Add another info?: Y/N");

                            }

                        } while(true);

                        System.out.println("Add another book?: Y/N");

                        do{
                            char answer = App.input.nextLine().toLowerCase().charAt(0);

                            if (answer == 'y') {

                                break;

                            } else if (answer == 'n') {

                                quit = true;

                                break;

                            } else {

                                System.out.println("Invalid input. Add another book?: Y/N");

                            }

                        } while(true);

                    } while(!quit);

                        break;
                    case "2":
                        System.out.println("Please type book id:");
                        int removeId = App.input.nextInt();
                        BookDaoService.removeBookById(removeId);
                        break;
                    case "3":
                        boolean con = true;
                        char byWitch;
                        do {
                            System.out.println("By which do you want to search? Please enter number:\n" +
                                    "1. Title\n" +
                                    "2. Author\n" +
                                    "3. Genre\n" +
                                    "4. Description");
                            byWitch = App.input.nextLine().toLowerCase().charAt(0);
                            if((byWitch == '1' ||byWitch == '2' ||byWitch == '3' ||byWitch == '4')) {
                                System.out.println("Please type a query word:");
                                String word = App.input.nextLine();

                                switch (byWitch) {
                                    case '1':
                                        ArrayList<Book> results = BookDaoService.searchBooksByString("BOOK_TITLE", word);
                                        if (results == null) {
                                            System.out.println("No book found.");
                                        } else {
                                            for (Book book : results) {
                                                System.out.println(book);
                                            }
                                        }
                                        break;
                                    case '2':
                                        ArrayList<Book> results2 = BookDaoService.searchBooksByString("BOOK_AUTHOR", word);
                                        if (results2 == null) {
                                            System.out.println("No book found.");
                                        } else {
                                            for (Book book : results2) {
                                                System.out.println(book);
                                            }
                                        }
                                        break;
                                    case '3':
                                        ArrayList<Book> results3 = BookDaoService.searchBooksByString("BOOK_GENRE", word);
                                        if (results3 == null) {
                                            System.out.println("No book found.");
                                        } else {
                                            for (Book book : results3) {
                                                System.out.println(book);
                                            }
                                        }
                                        break;
                                    case '4':
                                        ArrayList<Book> results4 = BookDaoService.searchBooksByString("BOOK_DESCRIPTION", word);
                                        if (results4 == null) {
                                            System.out.println("No book found.");
                                        } else {
                                            for (Book book : results4) {
                                                System.out.println(book);
                                            }
                                        }
                                        break;
                                    default:
                                        break;
                                }

                                System.out.println("\nContinue searching?: Y/N");
                                do {
                                    char answer = App.input.nextLine().toLowerCase().charAt(0);

                                    if(answer == 'y') {
                                        break;
                                    } else if(answer =='n') {
                                        con = false;
                                        break;
                                    } else {
                                        System.out.println("Invalid input. Continue searching?: Y/N");
                                    }
                                } while(true);
                            } else {
                                System.out.println("Invalid input. Try again?: Y/N");
                                do {
                                    char answer = App.input.nextLine().toLowerCase().charAt(0);

                                    if(answer == 'y') {
                                        break;
                                    } else if(answer =='n') {
                                        con = false;
                                        break;
                                    } else {
                                        System.out.println("Invalid input. Try again?: Y/N");
                                    }
                                } while(true);
                            }
                        } while (con);
                        break;
                    case "4":
                        System.out.println("Please enter user's ID:");
                        int userID = App.input.nextInt();
                        User checkoutUser = UserDaoService.loadUserById(userID);
                        System.out.println("Please enter the book ID:");
                        int checkoutID = App.input.nextInt();
                        Book checkoutBook = BookDaoService.loadBookById(checkoutID);

                        if(!(checkoutUser == null) && (checkoutBook.isAvailable())) {
                            UserDaoService.bookCheckOut(userID, checkoutBook.getTitle());
                            BookDaoService.switchIsAvailable(checkoutID, false);
                            App.clrscr();
                            System.out.println("Please return the book in two weeks");
                            Thread.sleep(8000);
                        } else if(checkoutUser == null){
                            App.clrscr();
                            System.out.println("The user is not registered.");
                            Thread.sleep(8000);
                        } else {
                            App.clrscr();
                            System.out.println("The book is not available now.");
                            Thread.sleep(8000);
                        }
                        break;
                    case "5":
                        System.out.println("Please enter user's ID:");
                        int userID2 = App.input.nextInt();
                        User returnUser = UserDaoService.loadUserById(userID2);
                        System.out.println("Please enter the book ID:");
                        int returnID = App.input.nextInt();
                        Book returnBook = BookDaoService.loadBookById(returnID);

                        if(!(returnUser == null) && !(returnBook.isAvailable())) {
                            UserDaoService.bookReturn(userID2);
                            BookDaoService.switchIsAvailable(returnID, true);
                            Thread.sleep(8000);
                            System.out.println("Thank you");
                        } else {
                            App.clrscr();
                            System.out.println("The book is not checked out now.");
                            Thread.sleep(8000);
                        }
                        break;
                    case "6":
                        System.out.println("Please enter user's ID:");
                        int userID3 = App.input.nextInt();

                        User showUser = UserDaoService.loadUserById(userID3);

                        if(showUser != null) {
                            System.out.println(showUser);
                            System.out.println("Press 'Q' to leave");
                        } else {
                            System.out.println("No user found");
                            System.out.println("Press 'Q' to leave");
                        }
                        do{
                            String leave = App.input.nextLine();
                            if(leave.equals("Q") || leave.equals("q")) {
                                break;
                            }
                        } while(true);
                        break;
                    case "7":
                        System.out.println("Enter user info:");
                        int id = UserDaoService.searchMinID();
                        System.out.println(id);
                        System.out.println("First name:");
                        String firstName = App.input.nextLine();
                        System.out.println("Last name:");
                        String lastName = App.input.nextLine();
                        System.out.println("Phone number:");
                        String phoneNumber = App.input.nextLine();
                        System.out.println("Address:");
                        String address = App.input.nextLine();
                        System.out.println(id);

                        UserDaoService.saveUser(new User(id, firstName, lastName, phoneNumber, address));

                        System.out.println("User has been added");
                        System.out.println(UserDaoService.loadUserById(id));
                        Thread.sleep(3000);
                        break;
                    case "8":
                        System.out.println("bye!");
                        break;
                    default:
                        System.out.println("invalid input");
                        break;
                }

            } while(!option.equals("8"));


            App.input.close();
            System.exit(0);
        }

}
