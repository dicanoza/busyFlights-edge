package com.diogenes.busyflights.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

/**
 * 
 * @author diogenes
 *
 *         {@link DatePatterMatcher} extends {@link BaseMatcher} in order to
 *         validate date patterns and to be used during test validations.
 *
 */
public class DatePatterMatcher extends BaseMatcher<String> {

	private final String dateFromat;

	public DatePatterMatcher(String dateFromat) {
		this.dateFromat = dateFromat;
	}

	@Override
	public boolean matches(Object dateToValidate) {

		if (dateToValidate == null) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat.toString());
		sdf.setLenient(false);

		try {

			sdf.parse(dateToValidate.toString());

		} catch (ParseException e) {

			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public void describeTo(Description description) {
		// TODO Auto-generated method stub

	}

	@Factory
	public static Matcher<String> matchesDatePattern(String dateFromat) {
		return new DatePatterMatcher(dateFromat);
	}

}
