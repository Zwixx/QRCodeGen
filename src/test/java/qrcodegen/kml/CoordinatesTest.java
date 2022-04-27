/*
 * Copyright (C) 2013 Stefan Ganzer
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

package qrcodegen.kml;

import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 *
 * @author Stefan Ganzer
 */
public class CoordinatesTest {

    public CoordinatesTest() {
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
	public void shouldInterpretString(){
		String input = "-73.98905,40.71872,100";
		Coordinates c = new Coordinates(input);
		assertThat(c.getLongitude(),equalTo(Double.valueOf(-73.98905)));
		assertThat(c.getLatitude(), equalTo(Double.valueOf(40.71872)));
		assertThat(c.getAltitude(), equalTo(Double.valueOf(100)));
	}

	@Test
	public void shouldInterpretStringWithoutAltitude(){
		String input = "-73.98905,40.71872";
		Coordinates c = new Coordinates(input);
		assertThat(c.getLongitude(),equalTo(Double.valueOf(-73.98905)));
		assertThat(c.getLatitude(), equalTo(Double.valueOf(40.71872)));
		assertFalse(c.isAltitudeDefined());
	}
	
	@Test()
	public void shouldThrowExceptionIfLessThanTwoComponents(){
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			String input = "-73.98905";
			new Coordinates(input);
		});
	}
	
	@Test()
	public void shouldThrowExceptionIfMoreThanThreeComponents(){
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			String input = "-73.98905,40.71872,100,95";
			new Coordinates(input);
		});
	}
}