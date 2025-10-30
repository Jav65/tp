package seedu.noknock.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.noknock.testutil.PatientBuilder;

public class PatientEqualsPredicateTest {
    //same patient
    @Test
    public void test_sameInstance_returnsTrue() {
        Patient target = new PatientBuilder().withName("Alice").build();
        PatientEqualsPredicate predicate = new PatientEqualsPredicate(target);
        assertTrue(predicate.test(target));
    }

    //equal patients
    @Test
    public void test_differentObjectButEqual_returnsTrue() {
        Patient target = new PatientBuilder().withName("Bob").withIC("S1234567A").build();
        Patient equalCopy = new PatientBuilder().withName("Bob").withIC("S1234567A").build();
        PatientEqualsPredicate predicate = new PatientEqualsPredicate(target);
        assertTrue(predicate.test(equalCopy));
    }

    //patients with different names
    @Test
    public void test_differentNames_returnsFalse() {
        Patient target = new PatientBuilder().withName("Charlie").build();
        Patient other = new PatientBuilder().withName("David").build();
        PatientEqualsPredicate predicate = new PatientEqualsPredicate(target);
        assertFalse(predicate.test(other));
    }

    //patients with different IC
    @Test
    public void test_differentICs_returnsFalse() {
        Patient target = new PatientBuilder().withName("Charlie Bee").withIC("S1234567A").build();
        Patient other = new PatientBuilder().withName("Charlie Bee").withIC("S1434567A").build();
        PatientEqualsPredicate predicate = new PatientEqualsPredicate(target);
        assertFalse(predicate.test(other));
    }
    //same patient but different wards (for editing the current patient)
    @Test
    public void test_differentWards_returnsFalse() {
        Patient target = new PatientBuilder().withName("Charlie Bee").withIC("S1234567A").withWard("2A").build();
        Patient other = new PatientBuilder().withName("Charlie Bee").withIC("S1434567A").withWard("2B").build();
        PatientEqualsPredicate predicate = new PatientEqualsPredicate(target);
        assertFalse(predicate.test(other));
    }
    //null object (boundary)
    @Test
    public void test_nullPatient_returnFalse() {
        Patient target = new PatientBuilder().withName("Eve").build();
        PatientEqualsPredicate predicate = new PatientEqualsPredicate(target);
        assertFalse(predicate.test(null));
    }
    //same predicate
    @Test
    public void equals_sameInstance_returnsTrue() {
        Patient target = new PatientBuilder().withName("Foo").build();
        PatientEqualsPredicate predicate = new PatientEqualsPredicate(target);
        assertTrue(predicate.equals(predicate));
    }
    //compare predicate with non-predicate
    @Test
    public void equals_differentType_returnsFalse() {
        Patient target = new PatientBuilder().withName("Bar").build();
        PatientEqualsPredicate predicate = new PatientEqualsPredicate(target);
        assertFalse(predicate.equals(42));
    }
    //predicates with the same patient
    @Test
    public void equals_sameTarget_returnsTrue() {
        Patient target = new PatientBuilder().withName("Zara").build();
        PatientEqualsPredicate first = new PatientEqualsPredicate(target);
        PatientEqualsPredicate second = new PatientEqualsPredicate(target);
        assertTrue(first.equals(second));
    }
    //predicates with different patients
    @Test
    public void equals_differentTarget_returnsFalse() {
        PatientEqualsPredicate first = new PatientEqualsPredicate(new PatientBuilder().withName("Adam").build());
        PatientEqualsPredicate second = new PatientEqualsPredicate(new PatientBuilder().withName("Bea").build());
        assertFalse(first.equals(second));
    }
    //tests equal function with null
    @Test
    public void equals_null_returnsFalse() {
        Patient target = new PatientBuilder().withName("Carl").build();
        PatientEqualsPredicate predicate = new PatientEqualsPredicate(target);
        assertFalse(predicate.equals(null));
    }
}

