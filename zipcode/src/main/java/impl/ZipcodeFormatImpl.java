package impl;

import exception.FormatExceptionLengths;
import exception.FormatExceptionSubstring;

public class ZipcodeFormatImpl implements ZipcodeFormat{
	
	/**
	 * This method checks unformatted data
	 * @string zipcode
	 */
	public int zipCodeParseUnformatted(String zipCode) {
		try {
            if (zipCode.length() == 5) {
                return Integer.parseInt(zipCode);
            } 
        } catch (final NumberFormatException numberFormatException) {
            throw new FormatExceptionSubstring(
                    "The ZIP code is not a number.  " + numberFormatException.getMessage(), zipCode,
                    0, zipCode.length());
        }
        throw new FormatExceptionLengths("The unformatted ZIP code must be 5 or 9 characters long.",
                                         zipCode, new int[]{5, 9});
	}
	
	/**
	 * This method checks formatted data
	 * @string zipcode
	 */
	public int zipCodeParseFormatted(String zipCode) {
		if (zipCode.length() == 5) {
            return zipCodeParseUnformatted(zipCode);
        } else if (zipCode.length() == 10) {
            return zipCodeParseUnformatted(zipCode.substring(0, 5) + zipCode.substring(6, 10));
        }
        throw new FormatExceptionLengths("The formatted ZIP code must be 5 or 10 characters long.",
                                      zipCode, new int[]{5, 10});
	}

}
