//modify from Trains by:
//     removing main()to be split between client and server
//     convert System.out.println from showTrains() and findPassword() to String 
//

// Type wrong soli. lazy modify liao
//package NetworkFinal;
package netwokingProtocolDesign;

import java.util.*;
import javax.swing.JOptionPane;
import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Trains {
	private int id;
	private String depart;
	private String destination;
	private String date;
	private String time;
	private double price;
	public int newDateTicketID = 0;

	private int BDTID;
	private String BDDATE;

	private ArrayList<Trains> ticket;
	private ArrayList<Trains> getDateTicket;

	// Constructor creates the ArrayList of existing customers loaded from file.
	public Trains() {
		ticket = new ArrayList<Trains>();
		getDateTicket = new ArrayList<Trains>();

		getTrainsList();
		getDateTicketOrderList();
	}

	public List<Trains> getTicket() {
		return ticket;
	}

	public List<Trains> getDateTicket() {
		return getDateTicket;
	}

	public int getID() {
		return newDateTicketID;
	}

	public int useNewDateTicketID() {
		newDateTicketID++;
		return newDateTicketID;
	}

	public void getTrainsList() {
		String Gdepart = "", Gdestination = "", Gdate = "", Gtime = "";
		double Gprice = 0.0;
		int Gid = 0;

		try {
			Scanner scnr = new Scanner(new File("train.txt"));
			// scnr.useDelimiter("\\s*#\\s*");
			while (scnr.hasNextInt()) {
				Gid = scnr.nextInt();
				Gdepart = scnr.next();
				Gdestination = scnr.next();
				Gdate = scnr.next();
				Gtime = scnr.next();
				Gprice = scnr.nextDouble();

				getTicket().add(new Trains(Gid, Gdepart, Gdestination, Gdate, Gtime, Gprice));
			}
			scnr.close();
		} catch (IOException e) {

		}
	}

	public void getDateTicketOrderList() {
		String BDdate = "";
		int BDid = 0, BDTid = 0;

		try {
			Scanner scnr = new Scanner(new File("dateTicket.txt"));
			// scnr.useDelimiter("\\s*#\\s*");
			while (scnr.hasNextInt()) {
				BDid = scnr.nextInt();
				BDTid = scnr.nextInt();
				BDdate = scnr.next();
				getDateTicket().add(new Trains(useNewDateTicketID(), BDTid, BDdate));
				newDateTicketID = BDid;
			}
			scnr.close();
		} catch (IOException e) {

		}

		// newDateTicketID = 0;
		for (Trains o : getDateTicket()) { // newOrderID should be max of all
											// existing ones
			if (o.getID() >= newDateTicketID)
				newDateTicketID = o.getID();
		}
	}

	public Trains(int nID, String nDepart, String nDestination, String nDate, String nTime, double nPrice) {
		id = nID;
		depart = nDepart;
		destination = nDestination;
		date = nDate;
		time = nTime;
		price = nPrice;
	}

	public Trains(int bdID, int bdtID, String bdDate) {
		newDateTicketID = bdID;
		BDTID = bdtID;
		BDDATE = bdDate;
	}

	public String toString() {
		// return format string will be nice
		return String.format("%4d	%20s	%20s	%10s	%10s	%10.2f", id, depart, destination, date, time,
				price);
		// return new
		// String(id+"\t\t"+depart+"\t\t"+destination+"\t\t"+date+"\t\t"+time+"\t\t"+price);
	}

	public String dateTicketToString() {
		return new String(newDateTicketID + "\t\t" + BDTID + "\t\t" + BDDATE);
	}

	public String priceString() {
		// return new String(id+"\t\t"+price);
		return String.format("%4d	%10.2f", id, price);
	}

	public String dateString() {
		// return new String(id+"\t\t"+date);
		return String.format("%4d	%10s", id, date);
	}

	public String dateTimeString() {
		// return new String(id+"\t\t"+date+"\t\t"+time);
		return String.format("%4d	%10s	%10s", id, date, time);
	}

	// Display customer records
	public String showTicket() {
		// String s = new String
		// ("id\t\tdepart\t\tdestination\t\tdate\t\ttime\t\tprice\n");

		// here format title also. Spacing must be equal to toString method
		// %4s[tab]$20s[tab]%20s and etc...
		String s = String.format("%4s	%20s	%20s	%10s	%10s	%10s\n", "id", "depart", "destination", "date",
				"time(Saver)", "price");
		String t = String.format("%4s	%20s	%20s	%10s	%10s	%10s\n", "----", "----------", "----------",
				"----------", "----------", "----------");
		s = s + t;
		for (Trains c : getTicket())
			s = s + c.toString() + '\n';
		// s = s + c.toString() ;

		// s = s + "total "+ ticket.size()+" registered customers";
		return s;
	}

	// Adds a new ticket to array of customers and refreshes the file.
	void addTicket(int id, String depart, String destination, String date, String time, double price) {
		ticket.add(new Trains(id, depart, destination, date, time, price));
		addTrainList();
	}

	public String addDateTicket(int ticketID, String ticketDate) {
		// int ans = 0;
		int found = 0;
		String s = new String();
		for (Trains c : getTicket()) {
			// s = String.valueOf(ticketID) + "||" + String.valueOf(e.id);
			// s += e.id + e.date + "||";

			/*
			 * if(ticketID == c.id) { s = "true"; break; } else { s = "false"; }
			 */

			if ((String.valueOf(ticketID)).equals(String.valueOf(c.id)) && (ticketDate.equals(c.date))) {
				found = 1;
				getDateTicket.add(new Trains(useNewDateTicketID(), ticketID, ticketDate));
				addDateTicketList();
				s = "XX, " + newDateTicketID+"000"; //Return barcode
			}
		}
		if (found == 0)
			s = new String("--, PD");
		return s;
	}
	
	
	public String addDateTimeTicket(int ticketID, String ticketDate, String ticketTime) {
		// int ans = 0;
		int found = 0;
		String s = new String();
		for (Trains c : getTicket()) {
			// s = String.valueOf(ticketID) + "||" + String.valueOf(e.id);
			// s += e.id + e.date + "||";

			/*
			 * if(ticketID == c.id) { s = "true"; break; } else { s = "false"; }
			 */

			if ((String.valueOf(ticketID)).equals(String.valueOf(c.id)) && (ticketDate.equals(c.date) && (ticketTime.equals(c.time))) ) {
				found = 1;
				getDateTicket.add(new Trains(useNewDateTicketID(), ticketID, ticketDate)); //May add time as well into the record
				addDateTicketList();
				s = "XX, " + newDateTicketID+"000"; //Return barcode
			}
		}
		if (found == 0)
			s = new String("--, PT");
		return s;
	}

	public String checkLogin(int id, String pass) {
		int ans = 0;
		/*
		 * if(id==1001 && pass.equals("123")) { return new String("++"); } else
		 * { return new String("--"); }
		 */

		for (Trains c : getTicket()) {
			if (id == c.id && pass.equals(c.depart)) {
				ans = 1;
			} else {
				ans = 0;
			}
		}

		if (ans == 0) {
			return new String("--");
		} else {
			return new String("++");
		}
	}

	public String searchPrice(int nID) {
		int found = 0;
		String s = new String();
		for (Trains c : getTicket()) {
			if ((String.valueOf(nID)).equals(String.valueOf(c.id))) {
				found = 1;
				// s = new String ("ID\t\tPrice");
				s = String.format("%4s	%10s\n", "ID", "Price");
				String t = String.format("%4s	%10s\n", "----", "----------");

				s = s + t;
				s = s + (c.priceString()) + System.lineSeparator();
			}
		}
		if (found == 0)
			s = new String("TN: " + nID);
		return s;
	}

	public String searchDate(int nID) {
		int found = 0;
		String s = new String();
		for (Trains c : getTicket()) {
			if ((String.valueOf(nID)).equals(String.valueOf(c.id))) {
				found = 1;
				// s = new String ("ID\t\tDate");
				s = String.format("%4s	%10s\n", "ID", "Date");
				String t = String.format("%4s	%10s\n", "----", "----------");

				s = s + t;
				s = s + (c.dateString()) + System.lineSeparator();
			}
		}
		if (found == 0)
			s = new String("PD: " + nID);
		return s;
	}

	public String searchDateTime(int nID) {
		int found = 0;
		String s = new String();
		for (Trains c : getTicket()) {
			if ((String.valueOf(nID)).equals(String.valueOf(c.id))) {
				found = 1;
				// s = new String ("ID\t\tDate\t\tTime");
				s = String.format("%4s	%10s	%10s\n", "ID", "Date", "Time");
				String t = String.format("%4s	%10s	%10s\n", "----", "----------", "----------");

				s = s + t;
				s = s + (c.dateTimeString()) + System.lineSeparator();
			}
		}
		if (found == 0)
			s = new String("PT: " + nID);
		return s;
	}

	public void addTrainList() {
		try {
			PrintWriter pw = new PrintWriter("train.txt");
			int ctr = 0;
			for (Trains c : getTicket()) {
				// System.out.printf("%s\n", details); //show list
				pw.println(c.toString());
				ctr++;
			}
			pw.close(); // close writter
		} catch (IOException ex) {

		}
	}

	public void addDateTicketList() {
		try {
			PrintWriter pw = new PrintWriter("dateTicket.txt");
			int ctr = 0;
			for (Trains c : getDateTicket()) {
				// System.out.printf("%s\n", details); //show list
				pw.println(c.dateTicketToString());
				ctr++;
			}
			pw.close(); // close writter
		} catch (IOException ex) {

		}
	}
}