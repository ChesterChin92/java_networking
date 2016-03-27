//modify from UserRec by:
//     removing main()to be split between client and server
//     convert System.out.println from showRecs() and findPassword() to String 
//


// Type wrong soli. lazy modify liao
//package NetworkFinal;
package YH;
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

  public UserRec2a(int nID, String nName, String nPass) {
    id = nID;      name = nName;    password = nPass;
  }

  public String toString() {
    return new String(id+"\t\t"+name+"\t\t"+password);
  }
  
  public String usernameString(){
	   return new String(id+"\t\t"+name);
  }
   
  //Display customer records
  public String showRecs() {
  	String s = new String ("id\t\tname\t\tAddress");
    for (UserRec2a c: users)
      s = s + c.toString() + "\n";
    s = s + "total "+ users.size()+" registered customers";
    return s;
 }

  //Adds a new customer to array of customers and refreshes the file.
  void addRec (int id, String name, String pass) {
    users.add(new UserRec2a(id, name, pass));
  }


  /*public String findPassword(String nName) {
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
}*/
  
  public String findPassword(int nID){
		 int found=0;
	    String s = new String();
	    for (UserRec2a c: users){
	   		if ((String.valueOf(nID)).equals(String.valueOf(c.id))) {
	   			found=1;
			    s = new String ("ID\t\tName");
	   			s = s + (c.usernameString());
	   		}
	    }
		if (found==0)
			s = new String("No such ticket "+ nID);
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

