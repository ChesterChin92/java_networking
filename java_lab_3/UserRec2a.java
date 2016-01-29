package java_lab_3;
//modify from UserRec by:
//     removing main()to be split between client and server
//     convert System.out.println from showRecs() and findPassword() to String 
//

import java.util.*;
import java.io.*;

public class UserRec2a {
  private int id;
  private String name;
  private String password;
  private ArrayList<UserRec2a> users;  

  //Constructor creates the ArrayList of existing customers loaded from file.
  public UserRec2a() {  
	  users = new ArrayList<UserRec2a>();    
  }
  
  public void readUsers(){
	  users = new ArrayList<UserRec2a>();    
	    
      try { 
  	Reader  rdr = new BufferedReader(new FileReader("C:/Users/chins/workspace/java_lab/src/newUser.txt"));
  	Scanner scnr = new Scanner(rdr).useDelimiter("\\s*[\\t\\n]\\s*");
  	while(scnr.hasNextInt()) {
    		users.add(new UserRec2a(scnr.nextInt(), scnr.next(), scnr.next()));
    	
  	}
  	scnr.close();
  	rdr.close();
	}
  catch(IOException ex) {
    System.out.println("Trouble reading user file");
  }
	  
  }

  public UserRec2a(int nID, String nName, String nPass) {
    id = nID;      name = nName;    password = nPass;
  }

  public String toString() {
    return new String(id+"\t\t"+name+"\t\t"+password);
  }
   
  //Display customer records
  public String showRecs() {
  	String s = new String ("id\t\tname\t\tAddress");
    for (UserRec2a c: users)
      s = s + c.toString();
    s = s + "total "+ users.size()+" registered customers";
    return s;
 }

  //Adds a new customer to array of customers and refreshes the file.
  void addRec (int id, String name, String pass) {
    users.add(new UserRec2a(id, name, pass));
  }


  public String findPassword(String nName) {
    int found=0;
    String s = new String();
    for (UserRec2a c: users){
   		if ( nName.equals(c.name)) {
   			found=1;
		    s = new String ("ID\t\tName\t\t\tPassword");
   			s = s + (c.toString());
   		}
    }
	if (found==0)
		s = new String("No such user "+ nName);
	return s;
}
  
//Save record.
  public void saveUser()
  {
  	  try
	  {
		 String filename = "C:/Users/chins/workspace/java_lab/src/newUser.txt";
		  PrintWriter out = new PrintWriter(filename);
		  //out.write("Write something in a line.");
		 for (UserRec2a c: users){
			 out.println(c.toString());
			 //out.println("");
			 
		 } 	  
		 //out.println("TEST");
		 System.out.println("Items saved to newUser.txt successfully");
		 out.close();
	  }
	  catch(IOException e)
	  {
		  System.out.println("Trouble saving book records file");
	  }
	  catch(SecurityException e) {
		  
      e.printStackTrace();
		  
      }
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

