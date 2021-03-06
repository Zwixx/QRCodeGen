/*
 Copyright 2012 Stefan Ganzer

 This file is part of QRCodeGen.

 QRCodeGen is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 QRCodeGen is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package qrcodegen.modules.vcard.reader;


import org.junit.jupiter.api.*;
import qrcodegen.modules.vcard.Property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 *
 * @author Stefan Ganzer
 */
public class NotePropertyParserTest {

	private static final String VALID_INPUT_EMPTY = "";
	private static final String VALID_INPUT_NULL = null;
	private NotePropertyParser parser;

	public NotePropertyParserTest() {
	}

	@BeforeAll
	public static void setUpClass() {
	}

	@AfterAll
	public static void tearDownClass() {
	}

	@BeforeEach
	public void setUp() {
		parser = new NotePropertyParser();
	}

	@AfterEach
	public void tearDown() {
		parser = null;
	}

	/**
	 * Test of parse method, of class NotePropertyParser.
	 */
	@Test
	public void testParseValidEmptyInput() {
		parser.reset(null, VALID_INPUT_EMPTY);
		parser.parse();
		assertTrue(parser.isValid());
	}

	@Test
	public void testParseValidNullInput() {
		parser.reset(null, VALID_INPUT_NULL);
		parser.parse();
		assertTrue(parser.isValid());
	}

	/**
	 * Test of getPropertyEntry method, of class NotePropertyParser.
	 */


	/**
	 * Test of getProperty method, of class NotePropertyParser.
	 */
	@Test
	public void testGetProperty() {
		System.out.println("getProperty");
		Property expResult = Property.NOTE;
		Property result = parser.getProperty();
		assertEquals(expResult, result);
	}

	/**
	 * Test of isValid method, of class NotePropertyParser.
	 */


	/**
	 * Test of isDone method, of class NotePropertyParser.
	 */


	/**
	 * Test of reset method, of class NotePropertyParser.
	 */

}
