package seedu.noknock.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.noknock.model.ReadOnlyAddressBook;
import seedu.noknock.model.person.Patient;
import seedu.noknock.testutil.AddressBookBuilder;
import seedu.noknock.testutil.PatientBuilder;


public class SampleDataUtilTest {
    private Patient alex = new PatientBuilder().withName("Alex Yeoh").withWard("2A").withIC("S1234567A")
            .withTags("VIP", "Diabetic").build();
    private Patient bernice = new PatientBuilder().withName("Bernice Yu").withWard("3B").withIC("T1234567B")
            .withTags("Allergic").build();
    private Patient charlotte = new PatientBuilder().withName("Charlotte Oliveiro").withWard("2A").withIC("S8654256A")
            .withTags("PostOp", "Dementia").build();
    private Patient david = new PatientBuilder().withName("David Li").withWard("4C").withIC("S3124567A")
            .withTags("Isolation").build();
    private Patient irfan = new PatientBuilder().withName("Irfan Ibrahim").withWard("1B").withIC("S1235767A")
            .withTags("Critical").build();
    private Patient roy = new PatientBuilder().withName("Roy Balakrishnan").withWard("2B").withIC("S3534657A").build();
    private Patient[] expectedSamplePatient = new Patient[] { alex, bernice, charlotte, david, irfan, roy};

    @Test
    public void testSamplePatients() {
        Patient[] arr = SampleDataUtil.getSamplePatients();
        for (int i = 0; i < 6; i++) {
            assertEquals(arr[i], expectedSamplePatient[i]);
        }
    }

    @Test
    public void testSampleAddressBook() {
        AddressBookBuilder abBuilder = new AddressBookBuilder();
        for (Patient p: expectedSamplePatient) {
            abBuilder = abBuilder.withPatient(p);
        }
        ReadOnlyAddressBook expectedAb = abBuilder.build();
        assertEquals(expectedAb, SampleDataUtil.getSampleAddressBook());
    }
}
