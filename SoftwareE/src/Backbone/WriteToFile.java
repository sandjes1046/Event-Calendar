package Backbone;
import java.io.*;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class WriteToFile
{

	static int length = 20;
	static String data = "textfile.txt";

	// For changing username
	public static void replaceUsername(int i, final String replaceWith)
	{
		try
		{
			//input the file content

			final BufferedReader file = new BufferedReader(new FileReader(data));
			final StringBuffer inputBuffer = new StringBuffer();
			String line;

			// Writes all of the file
			for(int j = 0; j < i*length; j++)
			{
				line = file.readLine();
				inputBuffer.append(line);
				inputBuffer.append(System.lineSeparator());
			}

			// at username
			line = replaceWith;
			file.readLine();
			inputBuffer.append(line);
			inputBuffer.append(System.lineSeparator());

			//again while file still has data
			while((line = file.readLine()) != null)
			{
				inputBuffer.append(line);
				inputBuffer.append(System.lineSeparator());
			}
			file.close();

			BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(data)));

			//write contents of StringBuffer to a file
			bwr.write(inputBuffer.toString());

			//flush the stream
			bwr.flush();

			//close the stream
			bwr.close();

		}
		catch(IOException ex){
			System.out.println(ex);
		}

	}

	public static void replacePassword(int i, final String replaceWith)
	{
		try
		{
			//input the file content
			final BufferedReader file = new BufferedReader(new FileReader(data));
			final StringBuffer inputBuffer = new StringBuffer();
			String line;

			// Writes all of the file
			for(int j = 0; j < i*length + 1; j++) // Adds 1 to get past username
			{
				line = file.readLine();
				inputBuffer.append(line);
				inputBuffer.append(System.lineSeparator());
			}

			// At password
			line = replaceWith;
			file.readLine();
			inputBuffer.append(line);
			inputBuffer.append(System.lineSeparator());

			//again while file still has data
			while((line = file.readLine()) != null)
			{
				inputBuffer.append(line);
				inputBuffer.append(System.lineSeparator());
			}
			file.close();
			
			BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(data)));

			//write contents of StringBuffer to a file
			bwr.write(inputBuffer.toString());

			//flush the stream
			bwr.flush();

			//close the stream
			bwr.close();
		}
		catch(IOException ex){
			System.out.println(ex);
		}
	}
	
	public static void checkMeetings(int idx, int[] replaced) {
		Vector<Meeting> meetings = ReadFromFile.meetings(idx);
		Vector<Meeting> actual = new Vector<Meeting>();
		for(int i = 0; i < meetings.size(); i++) {
			for(int j = 0; j < meetings.get(i).getDuration(); j++) {
				if(replaced[meetings.get(i).start+j] == 0) {
					System.out.println(meetings.get(i).getName() + " has a conflict. Please resolve.");
					break;
				}
				else {
					actual.add(meetings.get(i));
				}
			}			
		}
		if(meetings.size() != actual.size()) {
			replaceMeetings(idx, actual);
		}
	}

	public static void replaceAvailability(int i, int[] replaceWith)
	{
		try
		{
			checkMeetings(i, replaceWith);
			
			
			//input the file content
			final BufferedReader file = new BufferedReader(new FileReader(data));
			final StringBuffer inputBuffer = new StringBuffer();
			String line;

			// Writes all of the file
			for(int j = 0; j < i*length + 2; j++) // Adds 1 to get past username
			{
				line = file.readLine();
				inputBuffer.append(line);
				inputBuffer.append(System.lineSeparator());
			}

			// At genAvail []
			file.readLine();
			line = (Arrays.toString(replaceWith)).replace("[", "").replace("]", ""); //converting array to string with still with [""]s
			
			inputBuffer.append(line);
			inputBuffer.append(System.lineSeparator());
			//again while file still has data
			while((line = file.readLine()) != null)
			{
				inputBuffer.append(line);
				inputBuffer.append(System.lineSeparator());
			}
			file.close();
			
			BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(data)));

			//write contents of StringBuffer to a file
			bwr.write(inputBuffer.toString());

			//flush the stream
			bwr.flush();

			//close the stream
			bwr.close();
		}
		catch(IOException ex){
			System.out.println(ex);
		}
	}

	public static void replaceMeetings(int i, Vector<Meeting> m)
	{
		try
		{
			//input the file content
			final BufferedReader file = new BufferedReader(new FileReader(data));
			final StringBuffer inputBuffer = new StringBuffer();
			String line;

			// Writes all of the file
			for(int j = 0; j < i*length + 3; j++) // Adds 1 to get past username
			{
				line = file.readLine();
				inputBuffer.append(line);
				inputBuffer.append(System.lineSeparator());
			}

			// At list of meetings

			int size = m.size();

			if(size > length-4) { // 1 = username, 2 = password, 3 = availability, 4 = "-"
				System.out.println("Error, too many meetings to store.");
				size = length-4;
			}

			for(int j = 0; j < size; j++) {
				file.readLine();
				Calendar c = Calendar.getInstance();
				c.setTime(m.get(j).getDate());
				line = m.get(j).getName() + ", " + m.get(j).getDuration() + ", " + m.get(j).getStart() + ", " + c.get(Calendar.YEAR)+ ", " + (c.get(Calendar.MONTH)+1) + ", " + c.get(Calendar.DATE) + ", " + Boolean.toString(m.get(j).getSeen());
				inputBuffer.append(line);
				inputBuffer.append(System.lineSeparator());
			}

			// Adds stopper
			file.readLine();
			line = "-";
			inputBuffer.append(line);
			inputBuffer.append(System.lineSeparator());

			for(int k = 0; k < length-size-4; k++) { // For the rest of the user's empty meeting slots
				inputBuffer.append(System.lineSeparator());
				file.readLine();
			}

			//again while file still has data
			while((line = file.readLine()) != null)
			{
				inputBuffer.append(line);
				inputBuffer.append(System.lineSeparator());
			}
			file.close();
			
			BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(data)));

			//write contents of StringBuffer to a file
			bwr.write(inputBuffer.toString());

			//flush the stream
			bwr.flush();

			//close the stream
			bwr.close();
		}
		catch(IOException ex){
			System.out.println(ex);
		}
	}

	public static void addUser(String username, String password, int[] avail) 
	{
		try {
			File file = new File(data); // create a file object
			FileWriter fileWriter = new FileWriter(file, true); // true if you want to append to file
			BufferedWriter buffer = new BufferedWriter(fileWriter); // need a buffer to write print writer
			PrintWriter printWriter = new PrintWriter(buffer); // needed a buffer as a parameter

			printWriter.println(username);
			printWriter.println(password);
			printWriter.println(Arrays.toString(avail).replace("[", "").replace("]", ""));
			printWriter.println("-");
			for(int i = 0; i < length-4; i++) { // Extends file
				printWriter.println("");
			}
			//close when done
			printWriter.close();
		}
		catch(IOException ex){
			System.out.println(ex);
		}
	}

}
