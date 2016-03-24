package java_lab_2;
/** dserver0
 *  work with dclient0.java
 */


import java.net.*;
import java.io.*;
import java.util.*;

public class dserver0 {

	public static class UserRec {
		private int id;
		private String name;
		private String password;
		private ArrayList<UserRec> users;  

		//Constructor creates the ArrayList of existing customers loaded from file.
		public UserRec() {  
			users = new ArrayList<UserRec>();    
		}

		public UserRec(int nID, String nName, String nPass) {
			id = nID;      name = nName;    password = nPass;
		}

		public String toString() {
			return new String(id+"\t\t"+name+"\t\t"+password);
		}

		//Display customer records
		public void showRecs() {
			System.out.println ("id\t\tname\t\tAddress");
			
			for (UserRec c: users)
				System.out.println(c.toString());
				
			System.out.println(users.size()+" registered customers\n" );
			 
		}

		//Adds a new customer to array of customers and refreshes the file.
		void addRec (int id, String name, String pass) {
			users.add(new UserRec(id, name, pass));
		}


		public String findPassword(String nName) {
			int found=0;
			for (UserRec c: users){
				if ( nName.equals(c.name)) {
					found=1;
					System.out.println ("ID\t\tName\t\t\tPassword");
					System.out.println(c.toString());
					return "Result : " +c.toString();
				}
			}
			if (found==0)
				System.out.println("No such user "+ nName);
			return "Error : No such user" + nName;
		}

	}
	
	public static class something{
		public static void something_eh_method(){
			 System.out.println("Hello!");
		}
	}
	/**
	 * Description
	 * @param args
	 */
	public static void main( String[] args ) 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		UserRec userdb = new UserRec();
		something a = new something();
		a.something_eh_method();
		something.something_eh_method();
		
		userdb.showRecs();

		userdb.addRec(888, "FredNurg", "fnurg4");
		userdb.addRec(555, "JohnPolan", "jpolan8");
		userdb.addRec(333, "RonMulan", "rmulan5");

		userdb.showRecs();
		//	    System.out.println(userdb.findPassword("RonMulan"));

		try {
			//Socket setup code
			ServerSocket server = new ServerSocket(2019);
			System.out.println("Server: Socket created"); 
			Socket socket = server.accept();	
			System.out.println("Server:Connection accepted");    

			OutputStream sout = socket.getOutputStream();
			InputStream sin = socket.getInputStream();
			DataInputStream din = new DataInputStream(sin);
			PrintStream pout = new PrintStream(sout);
			int found = 0;
			String result = "";
			pout.println("Please enter your request.");
			
			//Loop Forever
			while(true){
				found=0;
				//System.out.println("Send Message?");
				//String request = in.readLine();

				
				String msg = din.readLine();
				System.out.println("Msg received:" + msg);	
				
				//Find User Module
				if (msg.equals("finduser"))
				{
					pout.println("Enter user name to search.");
					while(found==0)
					{
						msg = din.readLine();
						//			    					System.out.println("Server Result: " + userdb.findPassword(msg));
						pout.println(userdb.findPassword(msg).toString());
						found = 1;
						break;
					}
				}
				
				//Show record module
				else if (msg.equals("showrec")) {
					String return_msg = "";
					for (UserRec c : userdb.users) {

						return_msg += c.toString();

					}
					pout.println(return_msg);
				}
				
				//A precaution for "" (Empty String)
				else if (msg.equals("")) {
					pout.println("No such command. Please try again.");
				}

				//Catch all errors
				else{
					pout.println("No such command. Please try again.");
				}
			}


		}
		catch (IOException x ) {
			System.out.println("Sockets problem: " + x );
		}

	}



}



