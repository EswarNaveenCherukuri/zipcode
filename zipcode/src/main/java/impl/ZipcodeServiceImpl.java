package impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import exception.FormatExceptionRange;
import exception.ZipcodeException;
import model.Zipcode;
import zipcode.ZipcodeHelper;


/**
 * @author Naveen
 *
 */
public class ZipcodeServiceImpl implements ZipcodeService {
	
	private static final Log LOG = LogFactory.getLog(ZipcodeServiceImpl.class);


	private String zipcodeRanges;
	
	
	public ZipcodeServiceImpl(String zipcodeRanges) {
		this.zipcodeRanges = zipcodeRanges;
	}

	/**
	 * Load the zipcodes and validate 
	 * @return list
	 */
	public List<Zipcode> loadZipcodes() {
		LOG.info("Get the zipcodes and validate and load");
		String[] zipcodeIntervals = zipcodeRanges.split(" ");
		List<Zipcode> zipcodesList = new LinkedList<Zipcode>();
		for (int i = 0; i < zipcodeIntervals.length; i++) {
			zipcodesList.add(getZipcodeRange(zipcodeIntervals[i]));
		}
		return zipcodesList;
	}

	
	/**
	 * validates zip code ranges
	 * @param lowerBound , @param upperBound
	 * @return boolean
	 */
	public boolean validateZipcodeRange(int minRange, int maxRange) {
		LOG.info("Validating min and max range");
		if (minRange > maxRange) {
			throw new IllegalArgumentException(
					minRange + " " + maxRange + ":  " + "Zipcode minRange should be less than maxRange");
		}
		LOG.info("Validated min and max range ::" + true);
		return true;
	}

	/**
	 * @param zipRange
	 * @return Zipcode
	 */
	public Zipcode validateAndAdd(String[] zipRange) {
		LOG.info("in validateAndAdd method");
		Zipcode zipcode = null;

		if (zipRange.length != 2)
			throw new ZipcodeException("Zipcode should have min and max Ranges", zipRange[0]);

		ZipcodeFormat zipcodeFormat = new ZipcodeFormatImpl();
		
		int minRange = zipcodeFormat.zipCodeParseUnformatted(zipRange[0]);
		int maxRange = zipcodeFormat.zipCodeParseUnformatted(zipRange[1]);
		validateNegativesAndOdd(Integer.valueOf(zipRange[0]));
		validateNegativesAndOdd(Integer.valueOf(zipRange[1]));
		if(validateZipcodeRange(minRange, maxRange)) {
			zipcode = new Zipcode(minRange, maxRange);
		}
		LOG.info("Validation complete and loading the zipcodes");
		return zipcode;
	}

	/**
	 * split and get zip code range by passing the string of zip codes
	 * @return Zipcode
	 */
	public Zipcode getZipcodeRange(String zipcodeRange) {
		LOG.info("in getZipcodeRange method");
		return validateAndAdd(zipcodeRange.replaceAll("\\[|\\]", "").split(","));
	}

	/**
	 * validate negative and odds by the passing the zipcode
	 * @return = nothing
	 */
	public void validateNegativesAndOdd(int zipCode) {
		if (zipCode > 999999999 || zipCode < 0) {
			throw new FormatExceptionRange("ZIP code must not contain more than 9 digits or be negative.", zipCode, 0,
					999999999);
		}
	}

}
