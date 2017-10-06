package com.figaro.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {


	private static final String DATE_FORMAT = "yyyy-MM-dd";

	public static Date stringToDate (String date) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
