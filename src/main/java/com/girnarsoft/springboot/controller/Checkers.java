package com.girnarsoft.springboot.controller;

import org.springframework.stereotype.Component;

/**
 * Checkers class is used for checking that users enter integer value in empId
 * and String name checker authenticate user through his employeeId and password
 * 
 * @author gspl
 *
 */
@Component
public class Checkers {

	/**
	 * validate integer to check that user deoesn't enter null value or string
	 */
	public boolean validateInt(String str) {
		for (int index = 0; index < str.length(); index++) {
			// System.out.println(str.charAt(i)+ " ");
			if ((str.charAt(index) >= '1' && str.charAt(index) <= '9') || str.charAt(index) == '0') {
				continue;
			}
			return false;
		}
		if (str.length() > 8) {
			return false;
		}
		return true;
	}

	/**
	 * validate name to be of right format
	 * 
	 * @param name
	 * @return
	 */
	public boolean validateString(String name) {
		if (name.trim().length() == 0) {
			System.err.println("\nName can't be blank  ! try again");
			return false;
		}
		// regular exper

		String regex = "^[a-zA-Z ]*$";
		if (!name.matches(regex)) {
			return false;
		}
		return true;
	}

}
