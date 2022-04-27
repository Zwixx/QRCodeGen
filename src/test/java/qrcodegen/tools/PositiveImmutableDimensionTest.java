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
package qrcodegen.tools;

import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @author Stefan Ganzer
 */
public class PositiveImmutableDimensionTest {

    private PositiveImmutableDimension d1;

    public PositiveImmutableDimensionTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        d1 = new PositiveImmutableDimension(5, 10);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void combineShouldFailForNullValue() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            PositiveImmutableDimension d = new PositiveImmutableDimension(1, 1);

            d.combine(null);
        });
    }

    @Test
    public void combineShouldReturnThisIfThisEqualsOther() {
        PositiveImmutableDimension dim1 = new PositiveImmutableDimension(1, 1);
        PositiveImmutableDimension dim2 = new PositiveImmutableDimension(1, 1);
        assertThat(dim1, not(sameInstance(dim2)));

        PositiveImmutableDimension result = dim1.combine(dim2);
        assertThat(result, sameInstance(dim1));
    }

    @Test
    public void combineShouldReturnNewInstanceIfThisNotEqualsOther() {
        PositiveImmutableDimension dim1 = new PositiveImmutableDimension(1, 1);
        PositiveImmutableDimension dim2 = new PositiveImmutableDimension(2, 2);
        assertThat(dim1, not(sameInstance(dim2)));

        PositiveImmutableDimension result = dim1.combine(dim2);
        assertThat(result, notNullValue());
        assertThat(result, not(sameInstance(dim1)));
    }

    @Test
    public void combineShouldReturnMaxOfWidth() {
        int width1 = 1;
        int width2 = 5;
        int height = 1;
        PositiveImmutableDimension dim1 = new PositiveImmutableDimension(width1, height);
        PositiveImmutableDimension dim2 = new PositiveImmutableDimension(width2, height);

        PositiveImmutableDimension result = dim1.combine(dim2);
        assertThat(result, notNullValue());
        assertThat(result.getWidth(), is(width2));
        assertThat(result.getHeight(), is(height));
    }

    @Test
    public void combineShouldReturnMaxOfHeight() {
        int width = 1;
        int height1 = 1;
        int height2 = 5;
        PositiveImmutableDimension dim1 = new PositiveImmutableDimension(width, height1);
        PositiveImmutableDimension dim2 = new PositiveImmutableDimension(width, height2);

        PositiveImmutableDimension result = dim1.combine(dim2);
        assertThat(result, notNullValue());
        assertThat(result.getWidth(), is(width));
        assertThat(result.getHeight(), is(height2));
    }

    @Test
    public void creationShouldFailForNegativeWidth() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PositiveImmutableDimension d = new PositiveImmutableDimension(-1, 1);
        });
    }

    @Test
    public void creationShouldFailForNegativeHeight() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PositiveImmutableDimension d = new PositiveImmutableDimension(1, -1);
        });
    }

    @Test
    public void exceedsShouldThrowNullPointerExceptionIfOtherIsNullValue() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            PositiveImmutableDimension d = new PositiveImmutableDimension(1, 1);
            d.exceeds(null);
        });
    }

    @Test
    public void exceedsShouldReturnFalseIfOtherIsSameInstance() {
        boolean result = d1.exceeds(d1);
        assertFalse(result);
    }

    @Test
    public void exceedsShouldReturnFalseIfOtherIsEqualToThis() {
        PositiveImmutableDimension other = new PositiveImmutableDimension(d1.getWidth(), d1.getHeight());
        boolean result = d1.exceeds(other);
        assertFalse(result);
    }

    @Test
    public void exceedsShouldReturnFalseIfOtherWidthIsBigger() {
        PositiveImmutableDimension d2 = new PositiveImmutableDimension(d1.getWidth() + 1, d1.getHeight());
        boolean result = d1.exceeds(d2);
        assertFalse(result);
    }

    @Test
    public void exceedsShouldReturnFalseIfOtherHeightIsBigger() {
        PositiveImmutableDimension d2 = new PositiveImmutableDimension(d1.getWidth(), d1.getHeight() + 1);
        boolean result = d1.exceeds(d2);
        assertFalse(result);
    }

    @Test
    public void exceedsShouldReturnTrueIfOtherWidthIsSmaller() {
        PositiveImmutableDimension d2 = new PositiveImmutableDimension(d1.getWidth() - 1, d1.getHeight());
        boolean result = d1.exceeds(d2);
        assertTrue(result);
    }

    @Test
    public void exceedsShouldReturnTrueIfOtherHeightIsSmaller() {
        PositiveImmutableDimension d2 = new PositiveImmutableDimension(d1.getWidth(), d1.getHeight() - 1);
        boolean result = d1.exceeds(d2);
        assertTrue(result);
    }
}
