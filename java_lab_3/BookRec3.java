package java_lab_3;
import java.util.*;
import java.io.*;

public class BookRec3 {
  private int bookID;
  private String author;
  private String title;
  private String category;
  private ArrayList<BookRec3> books;  
  
  
  //Constructor creates the ArrayList of existing books loaded from file.
  public BookRec3() {  
    books = new ArrayList<BookRec3>();
    try { 
    	Reader  rdr = new BufferedReader(new FileReader("C:/Users/chins/workspace/java_lab/src/Book3List.txt"));
    	Scanner scnr = new Scanner(rdr).useDelimiter("\\s*[\\t\\n]\\s*");
    	while(scnr.hasNextInt()) {
      		books.add(new BookRec3(scnr.nextInt(), scnr.next(), scnr.next(), scnr.next()));
    	}
    	scnr.close();
    	rdr.close();
  	}
    catch(IOException ex) {
      System.out.println("Trouble reading book records file");
    }
  }


  public BookRec3(int nID, String nAuthor, String nTitle, String nCategory) {
		bookID = nID;      author = nAuthor;    title = nTitle;		category = nCategory;
  }

  
  public String toString() {
    return String.format("%04d\t%s\t%s\t%s\r\n", bookID, author, title, category);
  }
	
   
  //Display book records
  public void showbooks() {
    System.out.println ("ID\tAuthor\t\tAddress\t\t\tCategory");
    for (BookRec3 c: books)
      System.out.print(c.toString());
    System.out.printf("%d available books.\n", books.size());
 }

  //Save book records
  public void savebooks() throws IOException {
    PrintWriter out = new PrintWriter(new FileWriter("C:/Users/chins/workspace/java_lab/src/newBook.txt"));
    for (BookRec3 c: books)
      out.print(c.toString());
    out.close();
  }

  //Adds a new book to array of books and refreshes the file.
  void addBook (int nid, String nauthor, String ntitle, String ncategory) {
    books.add(new BookRec3(nid, nauthor, ntitle, ncategory));
    try {
      savebooks();
    } catch(IOException ex) {
      System.out.println ("Trouble saving book records");
    }
  }


  //This main is for testing.
  public static void main(String[] args) {
    
    BookRec3 db = new BookRec3();

    db.showbooks();

 	db.addBook(999, "KDU PG", "OS in Pespective", "Lecture");
    db.addBook(555, "KDU UC", "Android SDK", "Software");
    db.addBook(505, "KDU IT", "Advanced Network Concepts", "Book");

    db.showbooks();
    
    
  }
  
 


}
