/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
public class NicknamePropertyParserTest {

	private static final String VALID_INPUT_NULL = null;
	private static final String VALID_INPUT_EMPTY = "";
	private static final String VALID_INPUT = "Flocke";
	private NicknamePropertyParser parser;

	public NicknamePropertyParserTest() {
	}

	@BeforeAll
	public static void setUpClass() {
	}

	@AfterAll
	public static void tearDownClass() {
	}

	@BeforeEach
	public void setUp() {
		parser = new NicknamePropertyParser();
	}

	@AfterEach
	public void tearDown() {
		parser = null;
	}

	/**
	 * Test of parse method, of class NicknamePropertyParser.
	 */
	@Test
	public void testParseValidNullInput() {
		System.out.println("parseValidNullInput");

		String expectedValue = "NICKNAME:\r\n";

		parser.reset(null, VALID_INPUT_NULL);
		parser.parse();
		assertTrue(parser.isValid());
		assertTrue(parser.getPropertyEntry().getNickname().isEmpty());
		assertEquals(expectedValue, parser.getPropertyEntry().asString());
	}

	@Test
	public void testParseValidEmptyInput() {
		System.out.println("parseValidEmptyInput");

		String expectedValue = "NICKNAME:\r\n";

		parser.reset(null, VALID_INPUT_EMPTY);
		parser.parse();
		assertTrue(parser.isValid());
		assertTrue(parser.getPropertyEntry().getNickname().isEmpty());
		assertEquals(expectedValue, parser.getPropertyEntry().asString());
	}

	@Test
	public void testParseValidInput() {
		System.out.println("parseValidInput");

		String expectedValue = "NICKNAME:" + VALID_INPUT + "\r\n";

		parser.reset(null, VALID_INPUT);
		parser.parse();
		assertTrue(parser.isValid());
		assertEquals(VALID_INPUT, parser.getPropertyEntry().getNickname());
		assertEquals(expectedValue, parser.getPropertyEntry().asString());
	}

	/**
	 * Test of getProperty method, of class NicknamePropertyParser.
	 */
	@Test
	public void testGetProperty() {
		System.out.println("getProperty");
		Property expResult = Property.NICKNAME;
		Property result = parser.getProperty();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getPropertyEntry method, of class NicknamePropertyParser.
	 */


	/**
	 * Test of isDone method, of class NicknamePropertyParser.
	 */


	/**
	 * Test of isValid method, of class NicknamePropertyParser.
	 */


	/**
	 * Test of reset method, of class NicknamePropertyParser.
	 */

}
