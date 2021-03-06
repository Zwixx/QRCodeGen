/*
 * Copyright (C) 2012 Stefan Ganzer
 *
 * This file is part of QRCodeGen.
 *
 * QRCodeGen is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * QRCodeGen is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package qrcodegen.qrcode;

import com.google.zxing.qrcode.decoder.Mode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 * @author Stefan Ganzer
 */
public class ModeLocalizerTest {

	public ModeLocalizerTest() {
	}

	@BeforeEach
	public void setUp() {
	}

	@AfterEach
	public void tearDown() {
	}

	@Test
	public void constructorShouldThrowNullPointerExceptionIfLocaleIsNullValue() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			new ModeLocalizer(null);
		});
	}

	@Test
	public void constructorShouldSucceedForNonNullLocale() {
		new ModeLocalizer(Locale.ENGLISH);
		new ModeLocalizer(Locale.GERMAN);
	}

	@Test
	public void getLocaleShouldReturnInjectedLocale() {
		Locale expectedResult = Locale.ENGLISH;
		ModeLocalizer ml = new ModeLocalizer(expectedResult);
		Locale result = ml.getLocale();
		assertThat(result, equalTo(expectedResult));
	}

	@Test
	public void getModeAsLocalizedStringShouldReturnStringForNullValue() {
		Locale locale = Locale.ENGLISH;
		ModeLocalizer ml = new ModeLocalizer(locale);
		assertThat(ml.getLocale(), equalTo(locale));

		String result = ml.getModeAsLocalizedString(null);
		assertThat(result, not(nullValue()));
	}

	@Test
	public void getModeAsLocalizedStringShouldReturnGermanStringForGermanLocale() {
		Locale locale = Locale.GERMAN;
		ModeLocalizer ml = new ModeLocalizer(locale);
		assertThat(ml.getLocale(), equalTo(locale));

		String expectedResult = "Undefiniert";
		String result = ml.getModeAsLocalizedString(null);
		assertThat(result, equalTo(expectedResult));
	}

	@Test
	public void getModeAsLocalizedStringShouldReturnStringsForEveryModeValue() {
		Locale locale = Locale.GERMAN;
		ModeLocalizer ml = new ModeLocalizer(locale);
		assertThat(ml.getLocale(), equalTo(locale));

		for (Mode m : Mode.values()) {
			String result = ml.getModeAsLocalizedString(m);
			assertThat(result, not(nullValue()));
		}
	}
}
