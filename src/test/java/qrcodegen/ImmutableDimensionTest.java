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
package qrcodegen;

import org.junit.jupiter.api.*;
import qrcodegen.tools.ImmutableDimension;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author Stefan Ganzer
 */
public class ImmutableDimensionTest {

	public ImmutableDimensionTest() {
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
	public void testDimensions(){
		doTest(0,0);
		doTest(-1,-99);
		doTest(99,1);
	}
	
	@Test
	public void testEquals(){
		doTest(0,0,1,0);
		doTest(-1,-99,1,99);
		doTest(1,99,99,1);
	}
	
	private static void doTest(int expWidth, int expHeight) {
		final ImmutableDimension dim = new ImmutableDimension(expWidth, expHeight);
		assertEquals(expWidth, dim.getWidth());
		assertEquals(expHeight, dim.getHeight());

		final Dimension asDimension = dim.asAwtDimension();
		assertEquals(expWidth, asDimension.width);
		assertEquals(expHeight, asDimension.height);
		
		final Dimension expDimension = new Dimension(expWidth, expHeight);
		final ImmutableDimension fromDimension = new ImmutableDimension(expDimension);
		assertEquals(expWidth, fromDimension.getWidth());
		assertEquals(expHeight, fromDimension.getHeight());
		
		assertTrue(dim.equals(fromDimension));
		assertTrue(fromDimension.equals(dim));
		assertEquals(dim.hashCode(), fromDimension.hashCode());
	}
	
	private static void doTest(int width1, int height1, int width2, int height2){
		final ImmutableDimension dim1 = new ImmutableDimension(width1, height1);
		final ImmutableDimension dim2 = new ImmutableDimension(width2, height2);
		assertTrue(dim1.equals(dim1));
		
		assertFalse(dim1.equals(dim2));
		assertFalse(dim2.equals(dim1));
	}
}
