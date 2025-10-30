package seedu.noknock.model.date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.noknock.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    public void constructorValidTest() {
        // Format Tests
        String testDateString1 = "2024-03-31";
        String testDateString2 = "31-03-2024";
        Date testDate2 = new Date(testDateString2);
        Date testDate1 = new Date(testDateString1);
        LocalDate expectedDate = LocalDate.parse(testDateString1);
        assertEquals(testDate1.value, expectedDate);
        assertEquals(testDate2.value, expectedDate);
        assertEquals(testDate1.value, testDate2.value);
    }

    @Test
    public void constructorInvalidTest() {
        // null test
        assertThrows(NullPointerException.class, () -> new Date(null));

        // empty field test
        assertThrows(IllegalArgumentException.class, ()-> new Date(" "));

        // format test
        String testDateString1 = "2024-3-31";
        String testDateString2 = "2024-03-031";
        String testDateString3 = "02024-03-031";
        String testDateString4 = "2024-03-";
        String testDateString5 = "2024-31";
        String testDateString6 = "2024--31";
        String testDateString7 = "2024-03--31";

        assertThrows(IllegalArgumentException.class, () -> new Date(testDateString1));
        assertThrows(IllegalArgumentException.class, () -> new Date(testDateString2));
        assertThrows(IllegalArgumentException.class, () -> new Date(testDateString3));
        assertThrows(IllegalArgumentException.class, () -> new Date(testDateString4));
        assertThrows(IllegalArgumentException.class, () -> new Date(testDateString5));
        assertThrows(IllegalArgumentException.class, () -> new Date(testDateString6));
        assertThrows(IllegalArgumentException.class, () -> new Date(testDateString7));
    }

    @Test
    public void invalidParseTest() {
        // null value
        assertNull(Date.parse(null));

        // invalid values
        assertNull(Date.parse(" "));
        assertNull(Date.parse("31-00-2024"));
    }

    @Test
    public void factoryBoundaryValueTest() {
        // Day Boundary Value Test
        String validDayString1 = "2024-02-29";
        String validDayString2 = "2025-02-28";
        String validDayString3 = "2025-01-31";
        String validDayString4 = "2025-01-01";

        String invalidDayString1 = "2024-02-30";
        String invalidDayString2 = "2025-02-29";
        String invalidDayString3 = "2025-01-32";
        String invalidDayString4 = "2025-01-00";

        assertTrue(Date.isValidDate(validDayString1));
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDayString1));

        assertTrue(Date.isValidDate(validDayString2));
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDayString2));

        assertTrue(Date.isValidDate(validDayString3));
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDayString3));

        assertTrue(Date.isValidDate(validDayString4));
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDayString4));

        // Month Boundary Value Test
        String validMonthString1 = "2025-01-31";
        String validMonthString2 = "2025-03-31";
        String validMonthString3 = "2025-12-30";

        String invalidMonthString1 = "2025-00-31";
        String invalidMonthString3 = "2025-13-30";

        assertTrue(Date.isValidDate(validMonthString1));
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidMonthString1));

        assertTrue(Date.isValidDate(validMonthString2));

        assertTrue(Date.isValidDate(validMonthString3));
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidMonthString3));
    }

    @Test
    public void equals() {
        // same object -> returns true
        String testDateString1 = "2024-03-31";
        Date testDate1 = new Date(testDateString1);
        assertTrue(testDate1.equals(testDate1));

        // same value -> returns true
        Date testDate2 = new Date(testDateString1);
        assertTrue(testDate1.equals(testDate2));

        // null value comparison -> returns false
        assertFalse(testDate1.equals(null));

        // different types -> returns false
        assertFalse(testDate1.equals(5.0f));

        // different types same value -> returns false
        LocalDate testLocalDate = LocalDate.parse(testDateString1);
        assertFalse(testDate1.equals(testLocalDate));

        // different values -> return false
        String testDateString2 = "2024-02-13";
        assertFalse(testDate1.equals(new Date(testDateString2)));
    }

    @Test
    public void prettyTest() {
        String testDateString1 = "2024-03-31";
        Date testDate = new Date(testDateString1);
        assertEquals("31 March 2024", testDate.printPretty());
    }

    @Test
    public void toStringTest() {
        String testDateString1 = "2024-03-31";
        Date testDate = new Date(testDateString1);
        assertEquals("2024-03-31", testDate.toString());
    }

    @Test
    public void testHashCode() {
        String testDateString1 = "2024-03-31";
        LocalDate testDate = LocalDate.parse(testDateString1);
        int expectedHashCode = testDate.hashCode();
        assertEquals(expectedHashCode, new Date(testDateString1).hashCode());
    }
}
