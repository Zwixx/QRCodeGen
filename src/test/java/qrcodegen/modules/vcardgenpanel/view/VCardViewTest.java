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
package qrcodegen.modules.vcardgenpanel.view;


import org.junit.jupiter.api.*;
import qrcodegen.ContentModule;
import qrcodegen.modules.vcardgenpanel.model.VCardModel;
import qrcodegen.modules.vcardgenpanel.presentationmodel.VCardPresentationModel;

import java.awt.print.Printable;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author Stefan Ganzer
 */
public class VCardViewTest {

	private VCardView view;

	public VCardViewTest() {
	}

	@BeforeAll
	public static void setUpClass() {
	}

	@AfterAll
	public static void tearDownClass() {
	}

	@BeforeEach
	public void setUp() {
		view = VCardView.newInstance(new VCardPresentationModel(new VCardModel()));
	}

	@AfterEach
	public void tearDown() {
		view = null;
	}

	/**
	 * 2013-03-09 Implementation of ContentModule is a quick fix for printing
	 * fault of the VCard v4 module. This probably needs to be reworked.
	 */
	@Test
	public void shouldImplementContentModule() {
		assertTrue(view instanceof ContentModule);
	}

	/**
	 * 2013-03-09 Implementation of ContentModule is a quick fix for printing
	 * fault of the VCard v4 module. This probably needs to be reworked.
	 */
	@Test
	public void getPrintableShouldAlwaysReturnNull() {
		Printable expectedResult = null;
		Printable actualResult = view.getPrintable(null);

		assertThat(actualResult, equalTo(expectedResult));
	}

	/**
	 * 2013-03-09 Implementation of ContentModule is a quick fix for printing
	 * fault of the VCard v4 module. This probably needs to be reworked.
	 */
	@Test
	public void getJobNameShouldReturnEmptyString() {
		String expectedResult = "";
		String actualResult = view.getJobName();

		assertThat(actualResult, equalTo(expectedResult));
	}
}