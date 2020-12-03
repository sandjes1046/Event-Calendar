package Backbone;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller
{
	// sign-up function for new users requesting username and password
	// username and password have conditions to which the function will verify
	public static int signUp(String username, String password) throws IOException
	{
		if(username.length() > 10) // Username must be less than 10 characters
		{
			System.out.println("Success");
		}
		else
		{
			System.out.println("Username too short");
			return -1;
		}
		if(password.length() >= 8){ // Password must be at least 8 characters

			Pattern digit = Pattern.compile("[0-9]");
			Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

			Matcher hasDigit = digit.matcher(password);
			Matcher hasSpecial = special.matcher(password);

			if (!hasDigit.find()){ // If the password does not have a digit
				System.out.println("Password must contain at least one number");
				return -2;
			}
			if (hasSpecial.find()){ // If the password has a special character
				System.out.println("Password must not contain any special characters");
				return -3;
			}
		}

		else
		{System.out.println("Password not long enough.");
		return -4;}

		Vector <String> users = ReadFromFile.allUserNames();

		for (int i = 0; i < users.size(); i++){
			if(username == users.get(i)){ 
				System.out.println("Username taken. Please use another");
				return -5;
			}
		}

		int[] genAvailability = new int[160];
		Arrays.fill(genAvailability, 0, 160, 1);
		WriteToFile.addUser(username,password,genAvailability);
		return users.size();
	}

	// login function, checks list of users for username
	public static int login(String username, String password)
	{
		Vector <String> users = ReadFromFile.allUserNames();
		for (int i = 0; i < users.size(); i++){
			if(username.equals(users.get(i)))
			{
				if(password.equals(ReadFromFile.password(i)))
				{
					return i; // Returns the position of the user for convenience
				}
				else
				{
					System.out.println("Wrong password");
					return -1; // Failed
				}
			}
		}
		System.out.println("Username not found. Please make new user");
		return -2; // Failed
	}

	// schedule meeting function, takes in all the users' schedules and the length of meeting
	//	users' schedules = [[],[],[]]; length = 15 minute time blocks (1 = 15 minutes, 4 = 1 hour)
	public static int getEarliestTime(Vector<int[]> schedules, int length, int start)
	{
		int num = schedules.size(); // Number of people to schedule

		// Goes through the weekly slots and initializes a count that tracks amount of users able to attend a meeting
		for(int i = start; i < 160; i++) // For each time slot
		{
			int count = 0; // Used for ensuring that everyone can fit in that time slot
			for (int j = 0; j < num; j++) // User slot
			{
				if(schedules.get(j)[i] == 0)
				{
					break; //loop will break if proposed meeting time and user availability conflicts
				}
				else
				{
					count++; // Checks that each person can start at the given time
				}
			}
			if(count == num) // First; check that the starting time works for everyone
			{
				for(int k = 0; k < num; k++) // For each user
				{
					for(int m = 1; m < length; m++) // Check if they are available the length of the meeting
					{
						if(schedules.get(k)[i+m] == 0)
						{
							i += m; // Since the length doesn't work, check after the blockage
							break;
						}
						else
						{
							count++;
						}
					}
					if(count != ((k + 1) + (k + 1) * length)) // Second; check that user can stay whole meeting
					{
						break;
					}
				}
				if(count == (num * length)) // Finally; check that everyone can stay for the meeting
				{
					return i; // Returns the starting slot that works for everyone for the length of time
				}
			}
		}
		System.out.println("Could not find a slot. Please try a different length, different people, or different week");
		return -1; // Could not find a slot that works for everyone for the meeting length
	}

	// Driver function that calls helper functions to schedule meeting
	public static boolean schedule(Date day, Vector<String> usernames, int length, String meetingName) {

		Calendar c = Calendar.getInstance();
		c.setTime(day);
		int startingTime = (c.get(Calendar.DAY_OF_WEEK)-2)*32;

		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		day = c.getTime();
		
		Vector <String> users = ReadFromFile.allUserNames();
		int[] positions = new int[usernames.size()];
		
		for(int i = 0; i < usernames.size(); i++) {
			for(int j = 0; j < users.size(); j++) {
				if(usernames.get(i).equals(users.get(j))) {
					positions[i] = j;
					break;
				}
			}
		}

		Vector<int[]> schedules = ReadFromFile.availabilities(positions, day);

		int time = getEarliestTime(schedules, length, startingTime);
		if(time == -1){
			System.out.println("Scheduling Failed");
			return false; // Scheduling failed
		}
		for(int i = 0; i < usernames.size(); i++){ // Adds meeting to each user
			Meeting m = new Meeting(meetingName, length, time, day, false);
			Vector<Meeting> meetings = ReadFromFile.meetings(positions[i]);
                        System.out.println(meetings.size());
			meetings.add(m);
			Vector<Meeting> sorted = Schedule.sortMeetings(meetings);
                        System.out.println(sorted.size());
			WriteToFile.replaceMeetings(positions[i], sorted);
		}
                
		Vector<Meeting> meetings = ReadFromFile.meetings(positions[0]);
                System.out.println(meetings.size());
		System.out.println("Scheduling Success");
		return true;
	}
} 