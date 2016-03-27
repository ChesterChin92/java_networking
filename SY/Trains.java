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

public class Trains {
  private int id;
  private String departure;
  private String destination;
  private double price;
  private int newTicketID = 0;

  private ArrayList<Trains> ticket;  

  //Constructor creates the ArrayList of existing customers loaded from file.
  public Trains() {  
	  ticket = new ArrayList<Trains>();

	  getTrainsList();
  }
  public List<Trains> getTicket()      { return ticket; }

  public int useNewTicketID() 
  {
	    newTicketID++;
	    return newTicketID;
  }
  
  public void getTrainsList()
  {
	  String Gdeparture="", Gdestination="";
	  double Gprice = 0.0;
	  int Gid=0;
	  
      try 
      {
    	  Scanner scnr = new Scanner(new File("train-sy.txt"));
          //scnr.useDelimiter("\\s*#\\s*");
          while (scnr.hasNextInt()) 
          {
        	  Gid  = scnr.nextInt();
        	  Gdeparture = scnr.next();
        	  Gdestination = scnr.next();
        	  Gprice = scnr.nextDouble();	          
        	  
        	  getTicket().add(new Trains(Gid, Gdeparture, Gdestination, Gprice));        	  
        }
        scnr.close();	        	       
    }
    catch (IOException e) 
    {
    	
    }
  }

  public Trains(int nID, String nDeparture, String nDestination, double nPrice) {
    id = nID;      departure = nDeparture;    destination = nDestination;		price = nPrice;
  }

  public String toString() {
    return new String(id+"\t\t"+destination+"\t\t"+departure+"\t\t"+price+"\n");
  }
  
  public String priceString(){
	   return new String(id+""+price);
  }
   
  //Display train records
  public String showTicket() {
  	String s = new String ("id\t\tdestination\tdeparture\tprice\n");
    for (Trains c: getTicket())
      s = s + c.toString() + System.lineSeparator();
    //s = s + "total "+ ticket.size()+" registered customers";
    return s;
 }


  //Adds a new ticket to array of tickets and refreshes the file.
  void addTicket (int id, String departure, String destination, double price) {
	  ticket.add(new Trains(id, departure, destination, price));
	  addTrainList();
  }

  public String checkLogin(int id, String pass){
	  int ans = 0;
	  /*if(id==1001 && pass.equals("123"))
	  {
		  return new String("++");
	  }
	  else
	  {
		  return new String("--");
	  }*/
	  
	  for (Trains c: getTicket())
	  {
		  if(id==c.id && pass.equals(c.departure))
		  {
			  ans = 1;
		  }
		  else
		  {
			 ans = 0;
		  }
	  }
	  
	  if(ans == 0)
	  {
		  return new String("--");
	  }
	  else
	  {
		  return new String("++");
	  }
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
  
  public String searchPrice(int nID){
		 /*int found=0;
	    String s = new String();
	    for (Trains c: ticket){
	   		if ((String.valueOf(nID)).equals(String.valueOf(c.id))) {
	   			found=1;
			    s = new String ("ID\t\tPrice");
	   			s = s + (c.priceString());
	   		}
	    }
		if (found==0)
			s = new String("No such ticket "+ nID);
		return s;*/
	  
	  int found=0;
	    String s = new String();
	    for (Trains c: getTicket()){
	   		if ((String.valueOf(nID)).equals(String.valueOf(c.id))) {
	   			found=1;
			    s = new String ("ID\t\tPrice");
	   			s = s + (c.priceString());
	   		}
	    }
		if (found==0)
			s = new String("No such ticket "+ nID);
		return s;
	 }

  public void addTrainList(){
	  try
	  {
		  PrintWriter pw = new PrintWriter("train.txt");
		  int ctr = 0;
		  for (Trains c: getTicket()){			  			 
			  //System.out.printf("%s\n", details); //show list	          
			  pw.println(c.toString());
			  ctr++;
		  }	  	
	  		pw.close(); //close writter
	  }
	  catch(IOException ex){
		 
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