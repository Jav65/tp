package seedu.noknock.model.session;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.noknock.model.person.Patient;
import seedu.noknock.testutil.CaringSessionBuilder;
import seedu.noknock.testutil.PatientBuilder;

public class PatientCaringSessionTest {

    private final Patient patientA = new PatientBuilder().withName("Alice").withIC("S1234567A").build();
    private final Patient patientACopy = new PatientBuilder().withName("Alice").withIC("S1234567A").build();
    private final Patient patientB = new PatientBuilder().withName("Bob").withIC("S9876543Z").build();

    private final CaringSession session1 = new CaringSessionBuilder()
            .withDate("2025-10-10").withTime("09:00").withCareType("medication").build();
    private final CaringSession session1Copy = new CaringSessionBuilder()
            .withDate("2025-10-10").withTime("09:00").withCareType("medication").build();
    private final CaringSession session2 = new CaringSessionBuilder()
            .withDate("2025-10-11").withTime("09:00").withCareType("checkup").build();

    //valid patient, valid session
    @Test
    public void constructor_validArgs_success() {
        assertDoesNotThrow(() -> new PatientCaringSession(patientA, session1));
    }
    //invalid patient, valid session
    @Test
    public void constructor_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PatientCaringSession(null, session1));
    }
    //valid patient, invalid session
    @Test
    public void constructor_nullSession_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PatientCaringSession(patientA, null));
    }
    //same patient, same session
    @Test
    public void isSamePair_identicalObjects_returnsTrue() {
        PatientCaringSession pcs = new PatientCaringSession(patientA, session1);
        assertTrue(pcs.isSamePair(patientA, session1));
    }
    //equivalent patient, equivalent session
    @Test
    public void isSamePair_equivalentObjects_returnsTrue() {
        PatientCaringSession pcs = new PatientCaringSession(patientA, session1);
        assertTrue(pcs.isSamePair(patientACopy, session1Copy));
    }
    //different patient, equivalent session
    @Test
    public void isSamePair_differentPatient_returnsFalse() {
        PatientCaringSession pcs = new PatientCaringSession(patientA, session1);
        assertFalse(pcs.isSamePair(patientB, session1Copy));
    }
    //same patient, different session
    @Test
    public void isSamePair_differentSession_returnsFalse() {
        PatientCaringSession pcs = new PatientCaringSession(patientA, session1);
        assertFalse(pcs.isSamePair(patientACopy, session2));
    }
    //empty patient in first pair, empty session in second pair
    @Test
    public void isSamePair_nullArgs_returnsFalse() {
        PatientCaringSession pcs = new PatientCaringSession(patientA, session1);
        assertFalse(pcs.isSamePair(null, session1));
        assertFalse(pcs.isSamePair(patientA, null));
    }
    //same patient, same session
    @Test
    public void equals_sameInstance_returnsTrue() {
        PatientCaringSession pcs = new PatientCaringSession(patientA, session1);
        assertTrue(pcs.equals(pcs));
    }
    //same patient, equivalent session
    @Test
    public void equals_sameValues_returnsTrue() {
        PatientCaringSession pcs1 = new PatientCaringSession(patientA, session1);
        PatientCaringSession pcs2 = new PatientCaringSession(patientACopy, session1Copy);
        assertTrue(pcs1.equals(pcs2));
    }
    //equivalent patient, different sessions
    @Test
    public void equals_samePatientDifferentSession_returnsFalse() {
        PatientCaringSession pcs1 = new PatientCaringSession(patientA, session1);
        PatientCaringSession pcs2 = new PatientCaringSession(patientACopy, session2);
        assertFalse(pcs1.equals(pcs2));
    }
    //different patient, same session
    @Test
    public void equals_differentPatientSameSession_returnsFalse() {
        PatientCaringSession pcs1 = new PatientCaringSession(patientA, session1);
        PatientCaringSession pcs2 = new PatientCaringSession(patientB, session1);
        assertFalse(pcs1.equals(pcs2));
    }
    //null PatientCaringSession
    @Test
    public void equals_nullOrDifferentType_returnsFalse() {
        PatientCaringSession pcs = new PatientCaringSession(patientA, session1);
        assertFalse(pcs.equals(null)); // E5a
        assertFalse(pcs.equals("string")); // E5b
    }
    //equivalent patient, equivalent session
    @Test
    public void hashCode_sameValues_sameHashCode() {
        PatientCaringSession pcs1 = new PatientCaringSession(patientA, session1);
        PatientCaringSession pcs2 = new PatientCaringSession(patientACopy, session1Copy);
        assertEquals(pcs1.hashCode(), pcs2.hashCode());
    }
}
