import java.io.IOException;
import java.util.Scanner;

public class App {

    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        App.clrscr();

        try (Scanner input = new Scanner(System.in)) {

        Bookshelf bookshelf = new Bookshelf();

        Curator curator = new Curator(bookshelf);

            curator.showMenu();


            //clrscr();

            //try (Scanner scan = new Scanner(System.in)) {       
                

            input.close();







        //}
        }

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
