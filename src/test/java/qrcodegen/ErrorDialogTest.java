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
import qrcodegen.ErrorDialog.MessageType;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 *
 * @author Stefan Ganzer
 */
public class ErrorDialogTest {

	public ErrorDialogTest() {
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
	public void testMessageType() {
		for (MessageType t : MessageType.values()) {
			if (t == MessageType.PLAIN) {
				assertNull(t.getIcon());
				assertNull(t.getImage());
			}else{
				assertNotNull(t.getIcon());
				assertNotNull(t.getImage());
			}
			assertNotNull(t.getKey());
		}
	}
}
