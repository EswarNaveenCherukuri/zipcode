package zipcode;

import java.util.List;
import java.util.Scanner;
import model.Zipcode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import impl.ZipcodeServiceImpl;

public class ZipcodeMain {

	private static final Log LOG = LogFactory.getLog(ZipcodeMain.class);
	
	private static Scanner scan;

	public static void main(String[] args) {
		
		LOG.info("Enter the min and max ranges for the zip codes");
		
		scan = new Scanner(System.in);
		String zipcodeseries = scan.nextLine();
		LOG.info("Received input " + zipcodeseries);

		ZipcodeServiceImpl serviceImpl = new ZipcodeServiceImpl(zipcodeseries);
		List<Zipcode> zipcodeList = serviceImpl.loadZipcodes();
		
		ZipcodeHelper zipcodeHelper = new ZipcodeHelper();
		List<Zipcode> sortedZipCodeList = zipcodeHelper.sortByLowerBounds(zipcodeList);
		List<Zipcode> mergedZipcodeList = zipcodeHelper.mergeZipcodes(sortedZipCodeList);
		
		for (Zipcode zipcode : mergedZipcodeList) {
			LOG.info("[" + zipcode.getMinRange() + "," + zipcode.getMaxRange() + "]");
		}
	}

}
