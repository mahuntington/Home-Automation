package com.matthewhuntington.homeautomation;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.data.extensions.When;

public class CalendarParser
{
	protected ArrayList<Action> actions = new ArrayList<Action>();
	
	public CalendarParser(String id, String user, String password)
	{
		CalendarService myService = new CalendarService("Home Automation");
		URL feedUrl = null;
		CalendarEventFeed resultFeed = null;
		try
		{
			feedUrl = new URL("https://www.google.com/calendar/feeds/" + id + "/private/full");
			CalendarQuery myQuery = new CalendarQuery(feedUrl);
			myService.setUserCredentials(user, password);
			resultFeed = myService.query(myQuery, CalendarEventFeed.class);
		}
		catch (Exception e)
		{
			System.out.println("Could not get Calendar data from Google.");
			e.printStackTrace();
			return;
		}

		Date now = new Date();

		for (int i = 0; i < resultFeed.getEntries().size(); i++)
		{
			CalendarEventEntry entry = resultFeed.getEntries().get(i);
			List<When> times = entry.getTimes();
			for (int j = 0; j < times.size(); j++)
			{
				Date start = new Date(times.get(j).getStartTime().getValue());
				Date end = new Date(times.get(j).getEndTime().getValue());
				if (now.after(start) && now.before(end))
				{
					String command = entry.getTitle().getPlainText();
					char house = command.charAt(0);
					if (command.length() > 1)
					{
						int socket = Integer.parseInt(command.substring(1));
						Action new_action = new Action(house, socket, 1);
						actions.add( new_action );
					}
					else
					{
						actions.add( new Action(house, 1));
					}
				}

			}
		}
	}
	
	public ArrayList<Action> getActions()
	{
		return actions;
	}
}
