package viewmodels;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import models.*;
import services.BookDaoService;
import services.UserDaoService;

public class App {

	public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        //private static final String url = "jdbc:sqlite:resources/MYSTERY_BUSINESS.db";
        App.clrscr();

        BookDaoService.loadDriver();
		Thread.sleep(100);

		BookDaoService.dropTable();
		Thread.sleep(100);
		BookDaoService.createTable();
		Thread.sleep(100);
		UserDaoService.dropTable();
		Thread.sleep(100);
		UserDaoService.createTable();
		Thread.sleep(100);
		ArrayList<Book> sampleBookList = new ArrayList<>();
		sampleBookList.add(new Book(1, "Don Quixote","Miguel de Cervantes"));
		sampleBookList.add(new Book(2, " A Tale of Two Cities","Charles Dickens"));
		sampleBookList.add(new Book(3, "The Lord of the Rings","J.R.R. Tolkien"));
		sampleBookList.add(new Book(4, "The Little Prince","Antoine de Saint-Exupery"));
		sampleBookList.add(new Book(5, "Harry Potter and the Sorcerer’s Stone"," J.K. Rowling", "Fantasy", "Harry Potter has never even heard of Hogwarts when the letters start dropping on the doormat at number four, Privet Drive. Addressed in green ink on yellowish parchment with a purple seal, they are swiftly confiscated by his grisly aunt and uncle. Then, on Harry's eleventh birthday, a great beetle-eyed giant of a man called Rubeus Hagrid bursts in with some astonishing news: Harry Potter is a wizard, and he has a place at Hogwarts School of Witchcraft and Wizardry. An incredible adventure is about to begin! These new editions of the classic and internationally bestselling, multi-award-winning series feature instantly pick-up-able new jackets by Jonny Duddle, with huge child appeal, to bring Harry Potter to the next generation of readers. It's time to PASS THE MAGIC ON ..."));
		sampleBookList.add(new Book(6, "Harry Potter and the Chamber of Secrets"," J.K. Rowling", "Fantasy", "Harry Potter's summer has included the worst birthday ever, doomy warnings from a house-elf called Dobby, and rescue from the Dursleys by his friend Ron Weasley in a magical flying car! Back at Hogwarts School of Witchcraft and Wizardry for his second year, Harry hears strange whispers echo through empty corridors - and then the attacks start. Students are found as though turned to stone ...Dobby's sinister predictions seem to be coming true. These new editions of the classic and internationally bestselling, multi-award-winning series feature instantly pick-up-able new jackets by Jonny Duddle, with huge child appeal, to bring Harry Potter to the next generation of readers. It's time to PASS THE MAGIC ON ..."));
		sampleBookList.add(new Book(7, "Harry Potter and the Prisoner of Azkaban"," J.K. Rowling", "Fantasy", "When the Knight Bus crashes through the darkness and screeches to a halt in front of him, it's the start of another far from ordinary year at Hogwarts for Harry Potter. Sirius Black, escaped mass-murderer and follower of Lord Voldemort, is on the run - and they say he is coming after Harry. In his first ever Divination class, Professor Trelawney sees an omen of death in Harry's tea leaves ...But perhaps most terrifying of all are the Dementors patrolling the school grounds, with their soul-sucking kiss. These new editions of the classic and internationally bestselling, multi-award-winning series feature instantly pick-up-able new jackets by Jonny Duddle, with huge child appeal, to bring Harry Potter to the next generation of readers. It's time to PASS THE MAGIC ON ..."));
		BookDaoService.saveBooks(sampleBookList);
		Thread.sleep(100);
		UserDaoService.saveUser(new User(0, "Administrator"));
		UserDaoService.saveUser(new User(1, "user1", "Citizen", "111-111-1111", "Oak st."));
		UserDaoService.saveUser(new User(2, "user2", "Citizen", "222-222-2222", "Pine st."));
		UserDaoService.saveUser(new User(3, "user3", "Citizen", "333-333-3333", "Maple st."));
		Thread.sleep(100);


		UserDaoService.loadDriver();
		Thread.sleep(100);

		App.clrscr();

		System.out.print("Please enter user name: ");
		String name = input.nextLine();

		if(name.equals("Administrator")) {

			String password = "admin";

			System.out.print("Password: ");
			String pass = input.nextLine();

			if(pass.equals(password)) {

				User user = UserDaoService.loadUserById(0);
				CuratorMenu.showMenu(user);
			}

		} else {

			User user = UserDaoService.loadUserByName(name);
			System.out.println(user);
			if(user != null) {

				UserMenu.showMenu(user);

			} else {
				System.out.println("There is no registration. Please register user info first.");
				Thread.sleep(5000);
			}
		}

		// ArrayList<Book> newBookList = BookDaoService.loadAllBooks();

//		for (Book book: newBookList) {
//			System.out.println(book);
//		}



		//System.out.println(BookDaoService.searchMinID());



//        try (Scanner input = new Scanner(System.in)) {
//
//        viewmodels.Bookshelf bookshelf = new viewmodels.Bookshelf();
//
//        Curator curator = new Curator(bookshelf);
//
//            curator.showMenu();
//
//
//            //try (Scanner scan = new Scanner(System.in)) {
//
//
//            input.close();
//
//
//
//
//
//
//
//        //}
//        }

    }



    public static void clrscr(){
		//Clears Screen in java
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime();//.exec("clear");
				System.out.print("\033\143");
		} catch (IOException | InterruptedException ex) {}
	}
}
