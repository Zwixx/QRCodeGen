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
package qrcodegen.uri;


import org.junit.jupiter.api.*;
import qrcodegen.math.ArcDegree;
import qrcodegen.math.DecimalArcDegree;
import qrcodegen.math.Degree;
import qrcodegen.math.PreciseDecimalArcDegree;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author Stefan Ganzer
 */
public class GeoURITest {

	private GeoURI googleNewYork;
	private GeoURI northPoleGround;
	private GeoURI southPoleGround;
	private GeoURI northPole;
	private GeoURI southPole;

	public GeoURITest() {
	}

	@BeforeAll
	public static void setUpClass() {
	}

	@AfterAll
	public static void tearDownClass() {
	}

	@BeforeEach
	public void setUp() {
		googleNewYork = new GeoURI(new DecimalArcDegree(40.71872), new DecimalArcDegree(-73.98905), 100);
		northPoleGround = new GeoURI(new DecimalArcDegree(90.0), new DecimalArcDegree(-73.98905));
		southPoleGround = new GeoURI(new DecimalArcDegree(-90.0), new DecimalArcDegree(-73.98905));
		northPole = new GeoURI(new DecimalArcDegree(90.0), new DecimalArcDegree(-73.98905), 100);
		southPole = new GeoURI(new DecimalArcDegree(-90.0), new DecimalArcDegree(-73.98905), 100);
	}

	@AfterEach
	public void tearDown() {
		googleNewYork = null;
		northPoleGround = null;
		southPoleGround = null;
		northPole = null;
		southPole = null;
	}

	@Test
	public void creationShouldFailIfLatitudeLowerNegative90Degrees() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Degree lat = new ArcDegree(-91, 0, 0.0);
			Degree longit = new ArcDegree(0, 0, 0.0);
			new GeoURI(lat, longit);
		});
	}

	@Test
	public void creationShouldFailIfLatitudeHigher90Degrees() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Degree lat = new ArcDegree(91, 0, 0.0);
			Degree longit = new ArcDegree(0, 0, 0.0);
			new GeoURI(lat, longit);
		});
	}

	@Test
	public void creationShouldFailIfLongitudeLowerNegative180Degrees() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Degree lat = new ArcDegree(-181, 0, 0.0);
			Degree longit = new ArcDegree(0, 0, 0.0);
			new GeoURI(lat, longit);
		});
	}

	@Test
	public void creationShouldFailIfLongitudeHigher180Degrees() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Degree lat = new ArcDegree(181, 0, 0.0);
			Degree longit = new ArcDegree(0, 0, 0.0);
			new GeoURI(lat, longit);
		});
	}

	@Test
	public void shouldPrintFormattedString() {
		String expectedResult = "geo:40.71872,-73.98905,100";
		String actualResult = googleNewYork.toString();
		assertThat(actualResult, equalTo(expectedResult));
	}

	@Test
	public void longitudeShouldBeSetToZeroOnPoles() {
		double expectedResult = 0.0;
		double actualResult = northPoleGround.getLongitude().getValue();
		assertEquals(expectedResult, actualResult, 0.0);

		actualResult = southPoleGround.getLongitude().getValue();
		assertEquals(expectedResult, actualResult, 0.0);

		actualResult = northPole.getLongitude().getValue();
		assertEquals(expectedResult, actualResult, 0.0);

		actualResult = southPole.getLongitude().getValue();
		assertEquals(expectedResult, actualResult, 0.0);
	}

	@Test
	public void test() {
		GeoURI geo = new GeoURI(new ArcDegree(49, 35, 12.87), new ArcDegree(11, 0, 30.75));
		System.out.println(geo);
	}

	@Test
	public void uriShouldBeEqualToItself() {
		double latitude = 89.5;
		double longitude = 175.6;
		double altitude = 10;

		GeoURI uri_a = new GeoURI(new PreciseDecimalArcDegree(latitude), new PreciseDecimalArcDegree(longitude), altitude);

		assertTrue(uri_a.equals(uri_a));
	}

	@Test
	public void twoURIsWithSameValuesShouldBeEqual() {
		double latitude = 89.5;
		double longitude = 175.6;
		double altitude = 10;

		GeoURI uri_a = new GeoURI(new PreciseDecimalArcDegree(latitude), new PreciseDecimalArcDegree(longitude), altitude);
		GeoURI uri_b = new GeoURI(new PreciseDecimalArcDegree(latitude), new PreciseDecimalArcDegree(longitude), altitude);

		assertTrue(uri_a.equals(uri_b));
		assertTrue(uri_b.equals(uri_a));
	}

	@Test
	public void twoURIsWithDifferentValuesShouldNotBeEqual() {
		double latitude = 89.5;
		double longitude = 175.6;
		double altitude = 10;

		GeoURI uri_a = new GeoURI(new PreciseDecimalArcDegree(latitude), new PreciseDecimalArcDegree(longitude), altitude);
		GeoURI uri_b = new GeoURI(new PreciseDecimalArcDegree(latitude), new PreciseDecimalArcDegree(longitude));

		assertFalse(uri_a.equals(uri_b));
		assertFalse(uri_b.equals(uri_a));
	}

	@Test
	public void twoURIsWithSameValuesShouldHaveSameHashCode() {
		double latitude = 89.5;
		double longitude = 175.6;
		double altitude = 10;

		GeoURI uri_a = new GeoURI(new PreciseDecimalArcDegree(latitude), new PreciseDecimalArcDegree(longitude), altitude);
		GeoURI uri_b = new GeoURI(new PreciseDecimalArcDegree(latitude), new PreciseDecimalArcDegree(longitude), altitude);

		int hashCode_a = uri_a.hashCode();
		int hashCode_b = uri_b.hashCode();

		assertThat(hashCode_a, equalTo(hashCode_b));
	}
}