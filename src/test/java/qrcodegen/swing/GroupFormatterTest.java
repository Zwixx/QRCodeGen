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
package qrcodegen.swing;


import org.junit.jupiter.api.*;

import java.text.ParseException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 *
 * @author Stefan Ganzer
 */
public class GroupFormatterTest {
	
	public GroupFormatterTest() {
	}
	
	@BeforeAll
	public static void setUpClass() {
	}
	
	@AfterAll
	public static void tearDownClass() {
	}
	
	@BeforeEach
	public void setUp() {
	}
	
	@AfterEach
	public void tearDown() {
	}
	
	@Test
	public void valueToStringShouldNotInsertAPlaceHolderIfGroupSizeSmallerThanLength() throws ParseException{
		int groupSize = 2;
		EditGroupFormatter gf = new EditGroupFormatter(groupSize);
		String input = "a";
		String result = gf.valueToString(input);
		assertThat(result, equalTo(result));
	}

	@Test
	public void valueToStringShouldNotInsertAPlaceHolderIfGroupSizeEqualToLength() throws ParseException{
		int groupSize = 2;
		EditGroupFormatter gf = new EditGroupFormatter(groupSize);
		String input = "ab";
		String result = gf.valueToString(input);
		assertThat(result, equalTo(result));
	}

	@Test
	public void valueToStringShouldInsertPlaceHoldersIfGroupSizeBiggerThanLength() throws ParseException{
		int groupSize = 2;
		EditGroupFormatter gf = new EditGroupFormatter(groupSize);
		String input = "abcde";
		String expectedResult = "ab cd e";
		String result = gf.valueToString(input);
		assertThat(result, equalTo(expectedResult));
	}
	
		@Test
	public void valueToStringShouldInsertAPlaceHoldersInInputOfBiggerLength() throws ParseException{
		int groupSize = 2;
		EditGroupFormatter gf = new EditGroupFormatter(groupSize);
		String input = "abcdefghijklmnopq";
		String expectedResult = "ab cd ef gh ij kl mn op q";
		String result = gf.valueToString(input);
		assertThat(result, equalTo(expectedResult));
	}

}
