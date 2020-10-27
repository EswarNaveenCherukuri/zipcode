package zipcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import model.Zipcode;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ZipcodeHelperTest {
	
	@BeforeEach
    void setUp() {
    }
	
	ZipcodeHelper createZipcodeHelper() {
		return new ZipcodeHelper();
	}
	
	
	@Test
	void sortByLowerBounds1() {
		List<Zipcode> zipcodeRangeList = new ArrayList<Zipcode>();
		Zipcode zipcode1 = new Zipcode(94133,94133);
		zipcodeRangeList.add(zipcode1);
		Zipcode zipcode2 = new Zipcode(94122,94133);
		zipcodeRangeList.add(zipcode2);
		List<Zipcode> received = createZipcodeHelper().sortByLowerBounds(zipcodeRangeList);
		assertEquals(94122, received.get(0).getMinRange());
	}
	
	@Test
	void sortByLowerBounds2() {
		List<Zipcode> zipcodeRangeList = new ArrayList<Zipcode>();
		Zipcode zipcode1 = new Zipcode(94133,94133);
		zipcodeRangeList.add(zipcode1);
		List<Zipcode> received = createZipcodeHelper().sortByLowerBounds(zipcodeRangeList);
		assertEquals(94133, received.get(0).getMinRange());
	}
	
	@Test
	void mergeZipcodes1() {
		List<Zipcode> zipcodeRangeList = new ArrayList<Zipcode>();
		Zipcode zipcode1 = new Zipcode(94133,94133);
		zipcodeRangeList.add(zipcode1);
		Zipcode zipcode2 = new Zipcode(94122,94133);
		zipcodeRangeList.add(zipcode2);
		List<Zipcode>  received = createZipcodeHelper().mergeZipcodes(zipcodeRangeList);
		assertEquals(94133, received.get(0).getMinRange());
	}
	
	@Test
	void sortBeforeMerging() {
		Zipcode zipcode1 = new Zipcode(84000, 84004);
	    Zipcode zipcode2 = new Zipcode(84007, 84008);
	    List<Zipcode> zipcodeList = new LinkedList<Zipcode>();
	    zipcodeList.add(zipcode1);
	    zipcodeList.add(zipcode2);
	    List<Zipcode> sortedZipCodeList = createZipcodeHelper().sortByLowerBounds(zipcodeList);
	    List<Zipcode> mergedZipcodeList = createZipcodeHelper().mergeZipcodes(sortedZipCodeList);
	    assertEquals(mergedZipcodeList, zipcodeList);
	}
	

}
