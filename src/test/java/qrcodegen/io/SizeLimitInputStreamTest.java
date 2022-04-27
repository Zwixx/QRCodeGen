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
package qrcodegen.io;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 * @author Stefan Ganzer
 */
public class SizeLimitInputStreamTest {

	private static final String LENGTH_10_BYTE = "abcdefghij";
	private static final Charset UTF_8 = Charset.forName("UTF-8");

	public SizeLimitInputStreamTest() {
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

	@Test()
	public void shouldThrowExceptionAfterReadingOneByteIfLimitIsZeroByte() throws IOException {
		Assertions.assertThrows(SizeLimitException.class, () -> {
			InputStream in = new SizeLimitInputStream(new ByteArrayInputStream(LENGTH_10_BYTE.getBytes(UTF_8)), 0);
			while (true) {
				int value = in.read();
				if (value == -1) {
					break;
				}
			}
		});
	}

	@Test
	public void shouldNotThrowExceptionIfLimitEqualsInputLength() throws IOException {
		InputStream in = new SizeLimitInputStream(new ByteArrayInputStream(LENGTH_10_BYTE.getBytes(UTF_8)), 10L);
		while (true) {
			int value = in.read();
			if (value == -1) {
				break;
			}
		}
	}

	@Test()
	public void shouldThrowExceptionIfLimitExceedsInputLength() throws IOException {
		Assertions.assertThrows(SizeLimitException.class, () -> {
			InputStream in = new SizeLimitInputStream(new ByteArrayInputStream(LENGTH_10_BYTE.getBytes(UTF_8)), 9L);
			while (true) {
				int value = in.read();
				if (value == -1) {
					break;
				}
			}
		});
	}

	@Test
	public void shouldCountReadBytesCorrectly() throws IOException {
		SizeLimitInputStream in = new SizeLimitInputStream(new ByteArrayInputStream(LENGTH_10_BYTE.getBytes(UTF_8)), Long.MAX_VALUE);
		while (true) {
			int value = in.read();
			if (value == -1) {
				break;
			}
		}
		long expected = 10;
		long actual = in.getCurrentSize();
		assertThat(actual, equalTo(expected));
	}

	@Test()
	public void shouldThrowIllegalArgumentExceptionIfLimitIsLessThanZero() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			InputStream in = new SizeLimitInputStream(new ByteArrayInputStream(LENGTH_10_BYTE.getBytes(UTF_8)), -1);
		});
	}
}