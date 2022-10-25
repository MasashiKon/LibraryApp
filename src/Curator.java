public class Curator {

    Bookshelf bookshelf;
    private static int globalID = 1;
    
    public Curator(Bookshelf bookshelf) {
        this.bookshelf = bookshelf;
    }

    void showMenu() {
        String option;

            do{

                App.clrscr();

                System.out.println("Hello, please select a option:\n"
                + "1. Add books\n"
                + "2. Delete book\n"
                + "3. Search book\n"
                + "4. Check out\n"
                + "5. Return Book"
                );
                System.out.print("Please input a number: ");
                if(App.input.hasNext()) {
                    System.out.println("yes");
                }
                option = App.input.nextLine();

                App.clrscr();
                

                switch(option) {
                    case "1":
                        //System.out.println(option);
                        System.out.println("Please type book title");
                        String title = App.input.next();
                        Book book = new Book(globalID ,title);
                        globalID++;
                        this.bookshelf.addABook(book);
                        break;
                    case "2":
                        this.bookshelf.removeBook();
                        break;
                    case "3":
                        //System.out.println(option);
                        System.out.println("Please type book title");
                        String target = App.input.nextLine();
                    
                        if (this.bookshelf.bookSearch(target).size() > 0) {
                            System.out.println(this.bookshelf.bookSearch(target));
                        } else {
                            System.out.println("No book found");
                        }

                        break;
                    case "4":
                        this.bookshelf.checkOutBook();
                        break;
                    case "5":
                        this.bookshelf.returnBook();
                        break;
                    case "6":
                        System.out.println("bye!");
                        break;
                    default:
                        System.out.println("invalid input");
                        break;
                }


                // if (option.equals("3")) {
                //     System.out.println(option);
                //     break;
                // }



            } while(!option.equals("6"));


        App.input.close();
        System.exit(0);

        
    }

    public static void addBook(Book book, Bookshelf bookshelf){
        App.clrscr();
        bookshelf.addABook(book);
    }

}
