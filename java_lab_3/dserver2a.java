package java_lab_3;
/** dserver2a
 *  work with dclient2a.java, 
 *  note UserRec2a.class must be in same folder as this file
 *           or
 *       class UserRec2a must be copied to this file
 */


import java.net.*;
import java.io.*;
import java.util.*;

public class dserver2a {



   
   public static void main( String[] args ) 
   {

    UserRec2a userdb = new UserRec2a();
    BookRec3 bookdb = new BookRec3();
    
    bookdb.showbooks();
    
    bookdb.addBook(1, "KDU, PG","K-title", "education");
    bookdb.addBook(2, "KDU, PG","K-title", "education");
    bookdb.addBook(3, "KDU, PG","K-title", "education");
    
    bookdb.showbooks();
    
    
    userdb.showRecs();

    userdb.readUsers();
 	userdb.addRec(888, "FredNurg", "fnurg4");
    userdb.addRec(555, "JohnPolan", "jpolan8");
    userdb.addRec(333, "RonMulan", "rmulan5");

    userdb.showRecs();
    
	
	
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
     
   try {
         ServerSocket server = new ServerSocket(2016);
         System.out.println("Server: Socket created"); 
         Socket socket = server.accept();	
         System.out.println("Server:Connection accepted");             
    	 
		 OutputStream sout = socket.getOutputStream();
         InputStream sin = socket.getInputStream();
         DataInputStream din = new DataInputStream(sin);
	     PrintStream pout = new PrintStream(sout);

         pout.println("hello, Good morning");
         String msg = din.readLine();
		 System.out.println("Msg received:" + msg);             

	  while(true) {
		pout.println("Please enter your request: findUser/viewUsers/newUser/logout");
		msg = din.readLine();
		if (msg.equals("findUser")) {
			pout.println("Enter user name to search ");
    		msg = din.readLine();
    		pout.println(userdb.findPassword(msg));
		} else if (msg.equals("viewUsers")) {
			pout.println(userdb.showRecs());
		} else if (msg.equals("newUser")) {
			pout.println("Enter new user name: ");
    		String n = din.readLine();
    		pout.println("Enter password: ");
    		String p = din.readLine();
			userdb.addRec(888, n, p);
		} else if (msg.equals("logout")) {
			pout.println("Please come back again soon ");
			userdb.saveUser();
			
			break;
		} else {
			pout.print("Unrecognised Input, please enter again ");
		}
	  }        

   }
   catch (IOException x ) {
         System.out.println("Sockets problem: " + x );
   }
  }
}

