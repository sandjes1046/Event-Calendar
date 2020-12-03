package Backbone;
import java.util.*;

public class Schedule
{
	public static Vector<Meeting> sortMeetings(Vector<Meeting> meetings){
		Vector<Meeting> toSort = deleteOldMeetings(meetings);
		Collections.sort(meetings, Comparator.comparingInt(Meeting::getYear)
				.thenComparingInt(Meeting::getMonth)
				.thenComparingInt(Meeting::getNumDate)
				.thenComparingInt(Meeting::getStart));
		return meetings;
	}
	
	public static Vector<Meeting> deleteOldMeetings(Vector<Meeting> meetings){
		Calendar c = Calendar.getInstance();
		Date today = c.getTime();
		Calendar m = Calendar.getInstance();
		
		Vector<Meeting> valid = new Vector<Meeting>();
		
		for(int i = 0; i < meetings.size(); i++) {
			m.setTime(meetings.get(i).getDate());
			m.add(Calendar.DATE, meetings.get(i).getStart()%32);
			Date meet = m.getTime();
			if (!today.after(meet)) {
				valid.add(meetings.get(i));
			}
		}
		return valid;
	}
	
}