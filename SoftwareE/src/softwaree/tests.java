/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package softwaree;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;


/**
 *
 * @author Sandjes1046
 */
public class tests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    String date = "11/1/2019";
    Date meet = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    
    try{
        
        meet = dateFormat.parse(date);//converting a string to a date 

    }catch(ParseException e){
        e.printStackTrace();}
    Calendar c = Calendar.getInstance();
    c.setTime(meet);
    c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    //c.add(Calendar.DATE,7);
    System.out.println("Date of Monday this week: " + c.getTime());
    
    //date to string
    String c_string = dateFormat.format(c.getTime());
    System.out.println(c_string);


    // TODO code application logic here
    }
    
}
