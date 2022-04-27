/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qrcodegen.modules.vcard.reader;


import org.junit.jupiter.api.*;
import qrcodegen.modules.vcard.Property;

import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author Stefan Ganzer
 */
public class FnPropertyParserTest {

	private static final String VALID_INPUT = "Professor Dr. Dr. Dr. Augustus van Dusen";
	private static final String VALID_INPUT_2 = "J. Doe";
	private static final String VALID_INPUT_EMPTY = "";
	private static final String VALID_INPUT_NULL = null;
	private static final String INVALID_INPUT_CTRL_CHARACTERS = "Professor\u000bDr. Dr. Dr. Augustus van Dusen";
	private FnPropertyParser parser;

	public FnPropertyParserTest() {
	}

	@BeforeAll
	public static void setUpClass() {
	}

	@AfterAll
	public static void tearDownClass() {
	}

	@BeforeEach
	public void setUp() {
		parser = new FnPropertyParser();
	}

	@AfterEach
	public void tearDown() {
		parser = null;
	}

	/**
	 * Test of parse method, of class FnPropertyParser.
	 */
	@Test
	public void testParse() {
		System.out.println("parse");
		parser.reset(null, VALID_INPUT);
		parser.parse();
		assertTrue(parser.isValid());

		parser.reset(null, VALID_INPUT_2);
		parser.parse();
		assertTrue(parser.isValid());
	}

	@Test()
	public void testParseIllegalState() {
		Assertions.assertThrows(IllegalStateException.class, () -> {
			System.out.println("parseIllegalState");
			parser.reset(null, null);
			parser.parse();
			parser.parse();
		});
	}

	@Test
	public void testParseValidEmptyInput() {
		System.out.println("parseValidEmptyInput");
		parser.reset(null, VALID_INPUT_EMPTY);
		parser.parse();
		assertTrue(parser.isValid());
		assertTrue(parser.getPropertyEntry().getValueAsString().isEmpty());
	}

	@Test
	public void testParseValidNullInput() {
		System.out.println("parseValidNullInput");
		parser.reset(null, VALID_INPUT_NULL);
		parser.parse();
		assertTrue(parser.isValid());
		assertTrue(parser.getPropertyEntry().getValueAsString().isEmpty());
	}

	@Test
	public void testParseInvalidInput() {
		System.out.println("parseInvalidInput");
		parser.reset(null, INVALID_INPUT_CTRL_CHARACTERS);
		parser.parse();
		assertFalse(parser.isValid());
	}

	/**
	 * Test of getProperty method, of class FnPropertyParser.
	 */
	@Test
	public void testGetProperty() {
		System.out.println("getProperty");
		Property expResult = Property.FN;
		Property result = parser.getProperty();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getPropertyEntry method, of class FnPropertyParser.
	 */
	@Test
	public void testGetPropertyEntry() {
		System.out.println("getPropertyEntry");
		parser.reset(null, VALID_INPUT);
		parser.parse();
		assertTrue(parser.isDone());
		assertTrue(parser.isValid());
		assertEquals(VALID_INPUT, parser.getPropertyEntry().getValueAsString());
	}

	/**
	 * Test of isValid method, of class FnPropertyParser.
	 */
	public void testIsValid() {
		System.out.println("isValid");
		assertFalse(parser.isValid());
		parser.parse();
		assertTrue(parser.isValid());
	}

	/**
	 * Test of isDone method, of class FnPropertyParser.
	 */
	public void testIsDone() {
		System.out.println("isDone");
		assertFalse(parser.isDone());
		parser.parse();
		assertTrue(parser.isDone());
	}

	@Test
	public void testIsDone2() {
		System.out.println("isDone2");
		assertFalse(parser.isDone());
		parser.reset(null, null);
		assertFalse(parser.isDone());
		parser.parse();
		assertTrue(parser.isDone());
	}

	/**
	 * Test of reset method, of class FnPropertyParser.
	 */
	@Test
	public void testReset() {
		System.out.println("reset");
		assertFalse(parser.isDone());
		assertFalse(parser.isValid());
		parser.reset(null, VALID_INPUT);
		assertEquals(null, parser.getParams());
		assertEquals(VALID_INPUT, parser.getValue());
		assertFalse(parser.isDone());
		assertFalse(parser.isValid());
		parser.parse();
		assertTrue(parser.isDone());
		assertTrue(parser.isValid());
		parser.reset(null, null);
		assertFalse(parser.isDone());
		assertFalse(parser.isValid());
		assertEquals(null, parser.getParams());
		assertEquals(null, parser.getValue());
		
	}
}
