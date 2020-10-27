package zipcode;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import exception.FormatExceptionRange;
import exception.FormatExceptionSubstring;
import impl.ZipcodeService;
import impl.ZipcodeServiceImpl;
import model.Zipcode;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ZipcodeServiceImplTest {
	
	
	@Mock
	ZipcodeService service;
	
	 @BeforeEach
	    void setUp() {
	        Mockito.reset(service);
	    }
	
	@Test
	@DisplayName("check if max range greater")
	void validateZipcodeRange1() {
		int minZipCode = 94200;
		int maxZipCode = 94299;
		boolean flag = service.validateZipcodeRange(minZipCode, maxZipCode);
		assertEquals(Boolean.FALSE, flag);
		assertNotNull(minZipCode);
		assertNotNull(maxZipCode);
	}
	
	@Test
	@DisplayName("check if min range greater")
	void validateZipcodeRange2() {
		int minZipCode = 94299;
		int maxZipCode = 94200;
		boolean flag = service.validateZipcodeRange(minZipCode, maxZipCode);
		assertEquals(Boolean.FALSE, flag);
		assertNotNull(minZipCode);
		assertNotNull(maxZipCode);	
	}
	
	@Test
	@DisplayName("check if min and max are equal")
	void validateZipcodeRange3() {
		int minZipCode = 94299;
		int maxZipCode = 94299;
		boolean flag = service.validateZipcodeRange(minZipCode, maxZipCode);
		assertEquals(Boolean.FALSE, flag);
		assertNotNull(minZipCode);
		assertNotNull(maxZipCode);
	}
	
	@Test
	@DisplayName("No Negatives to validate")
	void validateNegativesAndOdd1() {
		int minZipCode = 94299;
		assertDoesNotThrow(()->service.validateNegativesAndOdd(minZipCode));
	}
	
	@Test
	@DisplayName("Negatives to validate")
	void validateNegativesAndOdd2() {
		int zipcode = -94299;
		doThrow(FormatExceptionRange.class).when(service).validateNegativesAndOdd(zipcode);
	}

	@Test
	@DisplayName("999999999 to validate")
	void validateNegativesAndOdd3() {
		int zipcode = 999999999;
		doThrow(FormatExceptionRange.class).when(service).validateNegativesAndOdd(zipcode);
	}
	
	@Test
	void getZipcodeRange1() {
		String  zipcodeRange= "[94133,94133]";
		Zipcode value = new Zipcode(94133, 94133);
		when(service.getZipcodeRange(zipcodeRange)).thenReturn(value);
	}
	
	@Test
	void getZipcodeRange2() {
		String  zipcodeRange= "[94133,Test]";
		when(service.getZipcodeRange(zipcodeRange)).thenThrow(FormatExceptionSubstring.class);
	}
	
	@Test
	void getZipcodeRange3() {
		String  zipcodeRange= "[94133,'']";
		when(service.getZipcodeRange(zipcodeRange)).thenThrow(FormatExceptionSubstring.class);
	}
	
	@Test
	void getZipcodeRange4() {
		String  zipcodeRange= "[94133,'null']";
		when(service.getZipcodeRange(zipcodeRange)).thenThrow(FormatExceptionSubstring.class);
	}
	
	@Test
	void loadZipcodes1() {
		String zipcodeseries = "[94133,94133]";
		ZipcodeServiceImpl serviceImpl = new ZipcodeServiceImpl(zipcodeseries);
		List<Zipcode> list = serviceImpl.loadZipcodes();
		assertEquals(94133, list.get(0).getMinRange());
		assertEquals(94133, list.get(0).getMaxRange());
		
	}
}
