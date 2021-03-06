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
package qrcodegen.modules.vcard.reader;


import org.junit.jupiter.api.*;
import qrcodegen.modules.vcard.Property;
import qrcodegen.modules.vcard.PropertyEntry;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


/**
 *
 * @author Stefan Ganzer
 */
public class PropertyParserTest {

	private PropertyParser parser;

	public PropertyParserTest() {
	}

	@BeforeAll
	public static void setUpClass() {
	}

	@AfterAll
	public static void tearDownClass() {
	}

	@BeforeEach
	public void setUp() {
		parser = new PropertyParserImpl();
	}

	@AfterEach
	public void tearDown() {
		parser = null;
	}

	/**
	 * Test of splitAndDeescapeComponentList method, of class PropertyParser.
	 */
	@Test
	public void testSplitAndDeescapeComponentList() {

		String input = "A\\,a;B;C";
		String[] expResult = {"A,a", "B", "C"};
		assertArrayEquals(expResult, PropertyParser.splitAndDeescapeComponentList(input));

		input = ";;;";
		String[] expResult2 = {"", "", "", ""};
		assertArrayEquals(expResult2, PropertyParser.splitAndDeescapeComponentList(input));

		input = ";\\;;;";
		String[] expResult3 = {"", ";", "", ""};
		assertArrayEquals(expResult3, PropertyParser.splitAndDeescapeComponentList(input));
	}

	/**
	 * Test of splitValueList method, of class PropertyParser.
	 */
	@Test
	public void testSplitValueList() {
	}

	private static class PropertyParserImpl extends PropertyParser {

		PropertyParserImpl() {
			super(Property.BEGIN);
		}

		@Override
		public PropertyEntry getPropertyEntry() {
			return null;
		}
	}
}
