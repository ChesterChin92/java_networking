//modify from Trains by:
//     removing main()to be split between client and server
//     convert System.out.println from showTrains() and findPassword() to String 
//
// Type wrong soli. lazy modify liao
//package NetworkFinal;
package YH;
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
    //return new String(id+"\t\t"+password);
	  
	  //return format string will be nice
	  return String.format("%4d	%20s", id, password); 

  }
  
  public String priceString(){
	   return new String(id+"\t\t"+password);
  }
   
  //Display customer records
  public String showUser() {
  	//String s = new String ("id\t\tpassword\n");
	//here format title also. Spacing must be equal to toString method %4s[tab]$20s[tab]%20s and etc...
	  String s = String.format("%4s	%20s\n", "id", "password");
	  String t = String.format("%4s	%20s\n", "----", "----------");
	  s = s + t;
	  
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
	   		if(!(String.valueOf(nID)).equals(String.valueOf(c.id)) && (nPassword.equals(c.password)))
	   		{
	   			found = 0;
	   			s = new String("--, LU");
	   		}
	   		if(!(nPassword.equals(c.password)) && (String.valueOf(nID)).equals(String.valueOf(c.id)))
	   		{
	   			found = 0;
	   			s = new String("--, LP");
	   		}
	   		
	    }
		//if (found==0)
		//	s = new String("--");
		return s;
	 }

}