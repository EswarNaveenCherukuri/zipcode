package zipcode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import model.Zipcode;
import model.ZipcodeComparator;

public class ZipcodeHelper {

	private static final Log LOG = LogFactory.getLog(ZipcodeHelper.class);

	/**
	 * @param zipcodeRangeList
	 * @return
	 */
	public List<Zipcode> sortByLowerBounds(List<Zipcode> zipcodeRangeList) {
		LOG.info("in sortByLowerBounds" +zipcodeRangeList.toString());
		Collections.sort(zipcodeRangeList, new ZipcodeComparator());
		return zipcodeRangeList;
	}

	/**
	 * @param sortedZipCodeList
	 * @return
	 */
	public List<Zipcode> mergeZipcodes(List<Zipcode> sortedZipCodeList) {
		List<Zipcode> mergedZipcodeList = new LinkedList<Zipcode>();
		Zipcode zipcode = null;
		for (Zipcode zipcodeInterval : sortedZipCodeList) {
			if (zipcode == null)
				zipcode = zipcodeInterval;
			else {
				if (zipcode.getMaxRange() >= zipcodeInterval.getMinRange()) {
					zipcode.setMaxRange(Math.max(zipcode.getMaxRange(), zipcodeInterval.getMaxRange()));
				} else {
					mergedZipcodeList.add(zipcode);
					zipcode = zipcodeInterval;
				}
			}
		}
		mergedZipcodeList.add(zipcode);
		LOG.info(mergedZipcodeList.toString());
		return mergedZipcodeList;
	}

}
