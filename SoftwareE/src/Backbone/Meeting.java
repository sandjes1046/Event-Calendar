package Backbone;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Meeting
{
	// Instance Variables //
	String name; // Name of meeting
	int duration; // Length of meeting
	int start; // Start of meeting
	Date day; // Monday of the week
	boolean seen; // If meeting has been checked by checkNotifications

	public Meeting(String meetingName, int length, int time, Date day, boolean seen)
	{
		this.name = meetingName;
		this.duration = length;
		this.start = time;
		this.day = day;
		this.seen = seen;
	}

	// Getters //
	public String getName()
	{
		return this.name;
	}

	public Date getDate()
	{
		return this.day;
	}

	public int getYear()
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.day);
		return cal.get(Calendar.YEAR);
	}

	public int getMonth()
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.day);
		cal.add(Calendar.DATE, start/32);
		return cal.get(Calendar.MONTH);
	}

	public int getNumDate()
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.day);
		cal.add(Calendar.DATE, start/32);
		return cal.get(Calendar.DATE);
	}

	public int getStart()
	{
		return this.start;
	}

	public int getDuration()
	{
		return this.duration;
	}
	public boolean getSeen()
	{
		return this.seen;
	}
	public void hasSeen()
	{
		this.seen = true;
	}
	
	public static Vector<String> checkMeetings(int idx) {
		Vector <Meeting> meetings = ReadFromFile.meetings(idx);
		meetings = Schedule.deleteOldMeetings(meetings);
		
		return printMeetings(meetings);
	}
	
	public static Vector<String> printMeetings(Vector<Meeting> meetings) {
		Vector<String> meetingsToPrint = new Vector<String>();
		for(int i = 0; i < meetings.size(); i++){
			if(meetings.get(i).getSeen())
			System.out.print(meetings.get(i).getName() + " on: ");
			int dayOfMeeting = meetings.get(i).getStart()/32; // Day of week
			int timeOfMeeting = meetings.get(i).getStart()%32; // Starting time
			int hourOfMeeting = timeOfMeeting/4; // Hour from ^
			int minuteOfMeeting = timeOfMeeting%4*15; // Minute from ^
			Calendar start = Calendar.getInstance();
			start.setTime(meetings.get(i).getDate()); // Sets to correct week's Monday
			start.set(Calendar.DATE, start.get(Calendar.DATE)+dayOfMeeting); // Sets day
			start.set(Calendar.HOUR_OF_DAY, 9+hourOfMeeting); // Sets Day
			start.set(Calendar.MINUTE, 0+minuteOfMeeting); // Sets Minutes
			Calendar end = Calendar.getInstance();
			end.setTime(start.getTime());
			end.add(Calendar.MINUTE, 15*meetings.get(i).getDuration());
			String am_pm1 = "";
			String am_pm2 = "";
			if (start.get(Calendar.HOUR_OF_DAY) < 12) {
				am_pm1 = "AM";
			}
			else {
				am_pm1 = "PM";
			}
			if (end.get(Calendar.HOUR_OF_DAY) < 12) {
				am_pm2 = "AM";
			}
			else {
				am_pm2 = "PM";
			}
			meetingsToPrint.add(meetings.get(i).getName()+(start.get(Calendar.MONTH)+1)+"/"+start.get(Calendar.DATE)+"/"+start.get(Calendar.YEAR)+" from "+start.get(Calendar.HOUR)+":"+String.format("%02d", start.get(Calendar.MINUTE)) + " " + am_pm1 + " - " + end.get(Calendar.HOUR)+":"+String.format("%02d", end.get(Calendar.MINUTE)) + " " + am_pm2);
		}
		return meetingsToPrint;
	}

	public static Vector<String> checkNotifications(int idx) {
		Vector <Meeting> meetings = ReadFromFile.meetings(idx);
		meetings = Schedule.deleteOldMeetings(meetings);
		Vector <Meeting> unseenMeetings = new Vector<Meeting>();

		// Filters the seen meetings
		for(int i = 0; i<meetings.size(); i++) {
                    boolean what = meetings.get(i).getSeen();
          
                    
			if (!meetings.get(i).getSeen())
				unseenMeetings.add(meetings.get(i));
			meetings.get(i).hasSeen();
		}
                
                System.out.println(meetings.size());
                System.out.println(unseenMeetings.size());
		
		WriteToFile.replaceMeetings(idx, meetings);
		
		System.out.println("These are your new meetings: ");
		return printMeetings(unseenMeetings);

	}
	
}
