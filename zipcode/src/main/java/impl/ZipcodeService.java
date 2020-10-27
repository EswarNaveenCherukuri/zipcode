package impl;

import model.Zipcode;

public interface ZipcodeService {
	
	void validateNegativesAndOdd(int zipCode);
	
	boolean validateZipcodeRange(int minZipCode, int maxZipCode);

	Zipcode getZipcodeRange(String zipcodeRange);
}
