package seedu.noknock.model.session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.noknock.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for {@code Note}.
 */
public class NoteTest {

    @Test
    public void constructor_null_ok() {
        // null is allowed (treated as empty string)
        new Note(null); // no exception
    }

    @Test
    public void constructor_lengthOverLimit_throwsIllegalArgumentException() {
        String over200 = "a".repeat(201);
        assertThrows(IllegalArgumentException.class, () -> new Note(over200));
    }

    @Test
    public void isValidNote_nullOrWithinLimit_returnsExpected() {
        // null -> valid
        assertTrue(Note.isValidNote(null));

        // empty -> valid
        assertTrue(Note.isValidNote(""));

        // within limit (<= 200) -> valid
        assertTrue(Note.isValidNote("a"));
        assertTrue(Note.isValidNote("a".repeat(200)));

        // over limit (> 200) -> invalid
        assertFalse(Note.isValidNote("a".repeat(201)));
    }

    @Test
    public void toString_emptyValue_returnsDefaultNone() {
        assertEquals(Note.DEFAULT_NONE, new Note(null).toString());
        assertEquals(Note.DEFAULT_NONE, new Note("").toString());
    }

    @Test
    public void equals_sameContent_returnsTrue() {
        Note a = new Note("Some note");
        Note aCopy = new Note("Some note");

        // same object
        assertTrue(a.equals(a));
        // same value
        assertTrue(a.equals(aCopy));
    }

    @Test
    public void equals_different_returnsFalse() {
        Note a = new Note("Alpha");
        Note b = new Note("Beta");

        assertFalse(a.equals(b));
        assertFalse(a.equals(null));
        assertFalse(a.equals(5));
    }

    @Test
    public void hashCode_consistentWithEquals() {
        Note a = new Note("Hash me");
        Note aCopy = new Note("Hash me");
        Note b = new Note("Different");

        assertEquals(a, aCopy);
        assertEquals(a.hashCode(), aCopy.hashCode());

        assertFalse(a.equals(b));
    }
}
