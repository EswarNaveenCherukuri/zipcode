package zipcode;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import exception.FormatExceptionLengths;
import exception.FormatExceptionSubstring;
import impl.ZipcodeFormat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ZipCodeFormatImplTest {
	
	@Mock
	ZipcodeFormat format;
	
	@BeforeEach
    void setUp() {
        Mockito.reset(format);
    }

	@Test
	void zipCodeParseUnformatted1() {
		when(format.zipCodeParseFormatted("test")).thenThrow(FormatExceptionSubstring.class);
	}
	
	@Test
	void zipCodeParseUnformatted2() {
		when(format.zipCodeParseFormatted("1234")).thenThrow(FormatExceptionLengths.class);
	}
	
	@Test
	void zipCodeParseUnformatted3() {
		when(format.zipCodeParseFormatted("12343456789123")).thenThrow(FormatExceptionLengths.class);
	}


}
