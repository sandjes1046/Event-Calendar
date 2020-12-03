package Backbone;

import java.util.Scanner;
import java.util.Vector;
import java.io.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;

//reads from file in java as string
//reads from textfile.txt with pseudo names and info
public class ReadFromFile
{

	static int length = 20; // 20 Lines per person
	static String data = "textfile.txt";
	
	public static Vector<String> allUserNames() { // Gets all usernames

		Vector<String> all = new Vector<String>();

		try{
			File temp = new File(data);
			Scanner input = new Scanner(temp);

			while (input.hasNextLine()) {
				all.add(input.nextLine()); // Line 1 of 100
				for (int i = 0; i < length - 1; i++) // Skip lines 2-100
				{
					input.nextLine();
				}
			}
			input.close();
		}

		catch(IOException ex){
			System.out.println(ex);
		}
		return all;

	}

	public static String password(final int i) { // Needs user's spot - assumes it is valid

		String pass ="";

		try{
			File temp = new File(data);
			Scanner input = new Scanner(temp);

			for (int j = 0; j < length * i+1; j++)
			{// Skip Lines 1-100*i
				input.nextLine();
			}

			pass = input.nextLine();
			input.close();
		}

		catch(IOException ex){
			System.out.println(ex);
		}

		return pass; // Password
	}

	public static int[] availability(final int i) { // Gets 1 availability

		int[] a = new int[160];

		try{
			File temp = new File(data);
			Scanner input = new Scanner(temp);


			for (int j = 0; j < length * i+2; j++){// Skip Lines 1-100*i
				input.nextLine();
			}

			String s = input.nextLine();
			String [] line = s.split("[,]");

			for (int k = 0; k < 160; k++){
				a[k] = Integer.parseInt(line[k].trim());
			}
			input.close();
		}

		catch(IOException ex){
			System.out.println(ex);
		}

		return a;
	}
	
	// Helper function for availabilities
	public static int[] applyMeetings(int[] availability, int idx, Date day) {
		Vector<Meeting> meetings = Schedule.deleteOldMeetings(meetings(idx));
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, 1); // Monday
		for(int i = 0; i < meetings.size(); i++) {
			if(meetings.get(i).getDate().before(day)) {
				continue;
			}
			else if(meetings.get(i).getDate().equals(day)) {
				for(int j = 0; j < meetings.get(i).getDuration(); j++) {
					availability[meetings.get(i).getStart() + j] = 0;
				}
			}
			else 
				break;
		}
		return availability;
	}

	public static Vector<int[]> availabilities(int[] indices, Date day) { // Gets availabilities of indices

		Vector<int[]> availabilities = new Vector<int[]>();

		try{
			File temp = new File(data);
			Scanner input = new Scanner(temp);
			
			for (int i = 0; i < indices.length; i++) {
				int[] a = availability(indices[i]);
				a = applyMeetings(a, indices[i], day);
				availabilities.add(a);
			}
			input.close();
		}

		catch(IOException ex){
			System.out.println(ex);
		}

		return availabilities;
	}

	public static Vector<Meeting> meetings(final int i) { // Returns meetings for given person

		Vector<Meeting> meetingList = new Vector<Meeting>();

		try{
			File temp = new File(data);
			Scanner input = new Scanner(temp);


			for (int j = 0; j < length * i+3; j++)// Skip Lines 1-100*i
				input.nextLine();

			String s = input.nextLine();
			final String delims = "[,]";

			while (!(s.equals("")) && !(s.equals("-"))) {
				final String[] tokens = s.split(delims);
				final String nameOfMtg = tokens[0];
				final int howLong = Integer.parseInt(tokens[1].trim());
				final int whatTime = Integer.parseInt(tokens[2].trim());
				final int year = Integer.parseInt(tokens[3].trim());
				final int month = Integer.parseInt(tokens[4].trim());
				final int day = Integer.parseInt(tokens[5].trim());
				final boolean seen = Boolean.parseBoolean(tokens[6].trim());
				final Calendar c = new GregorianCalendar(year, month - 1, day);
				final Date date = c.getTime();
				final Meeting meeting = new Meeting(nameOfMtg, howLong, whatTime, date, seen);
				meetingList.add(meeting);
				s = input.nextLine();
			}
			input.close();
		}

		catch(IOException ex){
			System.out.println(ex);
		}

		return meetingList;
	}

}
