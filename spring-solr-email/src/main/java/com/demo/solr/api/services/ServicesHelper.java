/**
 * @author AnshuGupta
<<<<<<< HEAD
 * service Helper class
=======
>>>>>>> angularfeature1
 */

package com.demo.solr.api.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ServicesHelper {
	
	public String getNextDate(String curDate) {
        String nextDate = "";
        try {
            Calendar today = Calendar.getInstance();
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date date = format.parse(curDate);
            today.setTime(date);
            today.add(Calendar.DAY_OF_YEAR, 1);
            nextDate = format.format(today.getTime());
        } catch (Exception e) {
            return nextDate;
        }
        return nextDate;
    }
	
	public List<String> getDateList(String fromDate, String toDate) {
	    List<String> dateList = new ArrayList<String>();
	    dateList.add(fromDate);
	    String tempDate = fromDate;
	    while(!tempDate.equals(toDate))
	    {
	      tempDate = getNextDate(tempDate);
	      dateList.add(tempDate);
	    }
	    return dateList;
	  }
}
