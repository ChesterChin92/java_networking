//modify from Trains by:
//     removing main()to be split between client and server
//     convert System.out.println from showTrains() and findPassword() to String 
//
// Type wrong soli. lazy modify liao
package SY;
import java.util.*;
import javax.swing.JOptionPane;
import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Users {
  private int id;
  private String password; 
  private int newTicketID = 0;

  private ArrayList<Users> user;  

  //Constructor creates the ArrayList of existing customers loaded from file.
  public Users() {  
	  user = new ArrayList<Users>();

	  getUsersList();
  }
  public List<Users> getUser()      { return user; } 
  
  public void getUsersList()
  {
	  String Gpassword="";
	  int Gid=0;
	  
      try 
      {
    	  Scanner scnr = new Scanner(new File("user.txt"));
          //scnr.useDelimiter("\\s*#\\s*");
          while (scnr.hasNextInt()) 
          {
        	  Gid  = scnr.nextInt();
        	  Gpassword = scnr.next();        	     
        	  
        	  getUser().add(new Users(Gid, Gpassword));        	  
        }
        scnr.close();	        	       
    }
    catch (IOException e) 
    {
    	
    }
  }

  public Users(int nID, String nPassword) {
    id = nID;      password = nPassword;
  }

  public String toString() {
    return new String(id+"\t\t"+password);
  }
  
  public String priceString(){
	   return new String(id+"\t\t"+password);
  }
   
  //Display customer records
  public String showUser() {
  	String s = new String ("id\t\tpassword\n");
    for (Users c: getUser())
      s = s + c.toString() + System.lineSeparator() ;
    //s = s + "total "+ ticket.size()+" registered customers";
    return s;
 }
  
  public String checkLogin(int nID, String nPassword){
	  int found=0;
	    String s = new String();
	    for (Users c: getUser()){
	   		if ((String.valueOf(nID)).equals(String.valueOf(c.id)) && (nPassword.equals(c.password))) {
	   			found=1;
			    s = new String ("++");
	   			//s = s + (c.priceString());
	   		}
	    }
		if (found==0)
			s = new String("--");
		return s;
	 }


  /*************************************** main removed from here and is split between dclient2.java and deserver.java ********
  //This main is for testing.
  public static void main(String[] args) {
    
    UserRec2a userdb = new UserRec2a();

    userdb.showRecs();

 	userdb.addRec(888, "FredNurg", "fnurg4");
    userdb.addRec(555, "JohnPolan", "jpolan8");
    userdb.addRec(333, "RonMulan", "rmulan5");

    userdb.showRecs();
	
	
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	try {
	while(true) {
		System.out.println("Please enter your request");
		String request = in.readLine();
		if (request.equals("findUser")) {
			System.out.println("Enter user name to search ");
    		String s = in.readLine();
    		userdb.findPassword(s);
		} else if (request.equals("viewUsers")) {
			userdb.showRecs();
		} else if (request.equals("newUser")) {
			System.out.println("Enter new user name: ");
    		String n = in.readLine();
    		System.out.println("Enter password: ");
    		String p = in.readLine();
			userdb.addRec(888, n, p);
		} else if (request.equals("logout")) {
			System.out.println("Please come back again soon ");
			break;
		} else {
			System.out.println("Unrecognised Input, please enter again ");
		}
	}        
	}
    catch (IOException x ) {
         System.out.println("Problem encountered -> " + x );
    }
}
****************************************/

}