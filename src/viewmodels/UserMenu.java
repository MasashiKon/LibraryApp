package viewmodels;

import models.Book;
import models.User;
import services.BookDaoService;
import services.UserDaoService;

import java.util.ArrayList;

public class UserMenu {

    static void showMenu(User user) throws InterruptedException {

        String option;

        do{

            App.clrscr();


            System.out.println("Hello, please select a option:\n"

                    + "1. Search book\n"
                    + "2. Checkout\n"
                    + "3. Return book\n"
                    + "4. Show user info\n"
                    + "5. Leave"

            );

            System.out.print("Please input a number: ");

            if(App.input.hasNext()) {

                System.out.println("yes");

            }

            option = App.input.nextLine();


            App.clrscr();



            switch(option) {

                case "1":
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
                case "2":
                    System.out.println("Please enter the book ID:");
                    int checkoutID = App.input.nextInt();
                    Book checkoutBook = BookDaoService.loadBookById(checkoutID);

                    if(checkoutBook.isAvailable()) {
                        UserDaoService.bookCheckOut(user.getId(), checkoutBook.getTitle());
                        BookDaoService.switchIsAvailable(checkoutID, false);
                        App.clrscr();
                        System.out.println("Please return the book in two weeks");
                        Thread.sleep(8000);
                    } else {
                        App.clrscr();
                        System.out.println("The book is not available now.");
                        Thread.sleep(8000);
                    }

                    break;
                case "3":
                    System.out.println("Please enter the book ID:");
                    int returnID = App.input.nextInt();
                    Book returnBook = BookDaoService.loadBookById(returnID);

                    if(!(returnBook.isAvailable())) {
                        UserDaoService.bookReturn(user.getId());
                        BookDaoService.switchIsAvailable(returnID, true);
                        App.clrscr();
                        System.out.println("Thank you");
                        Thread.sleep(8000);
                    } else {
                        System.out.println("The book is not checked out now.");
                        Thread.sleep(8000);
                    }
                    break;
                case "4":

                    User showUser = UserDaoService.loadUserById(user.getId());

                    if(showUser != null) {
                        System.out.println(showUser);
                        System.out.println("Press 'Q' to leave");
                    } else {
                        System.out.println("No user found");
                    }
                    do{
                        String leave = App.input.nextLine();
                        if(leave.equals("Q") || leave.equals("q")) {
                            break;
                        }
                    } while(true);
                case "5":
                    System.out.println("bye!");
                    break;
                default:
                    System.out.println("invalid input");
                    break;
            }

        } while(!option.equals("5"));


        App.input.close();
        System.exit(0);
    }

}
