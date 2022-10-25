import java.util.ArrayList;
public class Bookshelf {
    ArrayList<Book> booklist = new ArrayList<>();

    public Bookshelf() {
    }

    public void addABook(Book book) {
        booklist.add(book);
    }

    public Book getBook() {
        return booklist.get(0);
    }

    public ArrayList<Book> bookSearch(String keyword) {
        ArrayList<Book> resultList = new ArrayList<>();
        this.booklist.forEach(book -> {

            if (keyword.equals(book.getAuthor())) {

                resultList.add(book);

            } else if (keyword.equals(book.getGenre())) {

                resultList.add(book);

            } else if (keyword.equals(book.getId())) {

                resultList.add(book);

            } else if (keyword.equals(book.getTitle())) {

                resultList.add(book);

            } 

        });
        return resultList;
    }

    public void removeBook() {

        System.out.println("Please enter a book ID or title to remove");

            String keyword = App.input.nextLine();

            if (this.bookSearch(keyword).size() > 0) {

                int index = booklist.indexOf(this.bookSearch(keyword).get(0));
                Book book = this.bookSearch(keyword).get(0);

                System.out.println("Are you sure to remove this book?");
                System.out.println(book);
                System.out.println("\nY/N");
                
                do {

                    char isRemove = App.input.nextLine().toLowerCase().charAt(0);

                        if (isRemove == 'y') {

                            booklist.remove(index);
                            break;

                        } else if (isRemove == 'n') {
                        
                            break;

                        } else {

                            System.out.println("Invalid input. Are you sure to remove this book? Y/N");

                        }

                } while(true);

            } else {

                System.out.println("No book found");

            }
    }

    public Book findBook(int id) {
        for (Book book: booklist) {
            if (book.getId() == id){
                return book;
            }
        } 
        return new Book(-1, null, null, null, null, null, false);
    }

    public void checkOutBook() {
        ArrayList<Book> checkOutList = new ArrayList<>();

        for(int i = 1; i <= 5; i++) {

            System.out.println("Enter the book ID");
            String stirngId = App.input.nextLine();
            int id = -1;

            try{
                id = Integer.parseInt(stirngId);
            }
            catch (NumberFormatException ex){
                ex.printStackTrace();
            }

            Book book = findBook(id);

            if (book.getId() != -1 && book.isAvailable() == true) {
                checkOutList.add(book);
            } else if(book.getId() != -1 && book.isAvailable() != true) {
                System.out.println("This is book is not available");
            } else {
                System.out.println("No book found");
            }

            System.out.println("Add another book?\nY/N");

            do {

                char yOrN = App.input.nextLine().toLowerCase().charAt(0);

                    if (yOrN == 'y') {

                        break;

                    } else if (yOrN == 'n') {
                    
                        i = 6;
                        break;

                    } else {

                        System.out.println("Invalid input. Add another book?: Y/N");

                    }

            } while(true);

        }

        App.clrscr();

        if (checkOutList.size() != 0) {
            for(Book book: checkOutList) {
                System.out.println("ID: " + book.getId() + ", Title: " + book.getTitle());
            }
        
            System.out.println("\nAre you sure to check out?: Y/N");

            do {

                char yOrN = App.input.nextLine().toLowerCase().charAt(0);

                    if (yOrN == 'y') {

                        for(Book book: checkOutList) {
                            book.setAvailable(false);
                        }

                        System.out.println("Check out complete. Please return book in 2 weeks");

                        break;

                    } else if (yOrN == 'n') {
                    
                        break;

                    } else {

                        System.out.println("Invalid input. Are you sure to check out?: Y/N");

                    }

            } while(true);
        }
    }

    public void returnBook() {

        int id = -1;

        do{
            App.clrscr();
            System.out.println("Enter the book ID to return. Please press enter key to finish");

            String stirngId = App.input.nextLine();
            id = -1;

            try{
                id = Integer.parseInt(stirngId);

            }
            catch (NumberFormatException ex){
                ex.printStackTrace();
            }



            if(id != -1) {
                Book book = findBook(id);
                if(book.getId() != -1) {
                    book.setAvailable(true);
                } else {
                    System.out.println("This book is not belonging to the library");
                    System.out.println("Coutinue to return or back to menu?: C/B");

                    do {

                        char cOrB = App.input.nextLine().toLowerCase().charAt(0);
        
                            if (cOrB == 'c') {
        
                                break;
        
                            } else if (cOrB == 'b') {
                            
                                id = -1;
                                break;
        
                            } else {
        
                                System.out.println("Coutinue to return or back to menu?: C/B");
        
                            }
        
                    } while(true);
                }
            }
            
        } while(id != -1);
    }

    @Override
    public String toString() {
        return "Bookshelf [booklist=" + booklist + "]";
    }

    
}
