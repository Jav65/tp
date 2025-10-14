package seedu.noknock.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.noknock.model.person.IC;
import seedu.noknock.model.person.Name;
import seedu.noknock.model.person.Patient;
import seedu.noknock.model.person.Ward;
import seedu.noknock.model.tag.Tag;
import seedu.noknock.model.util.SampleDataUtil;

/**
 * A utility class to help with building Patient objects.
 */
public class PatientBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_WARD = "2A";
    public static final String DEFAULT_IC = "S1234567A";

    private Name name;
    private Ward ward;
    private IC ic;
    private Set<Tag> tags;

    /**
     * Creates a {@code PatientBuilder} with the default details.
     */
    public PatientBuilder() {
        name = new Name(DEFAULT_NAME);
        ward = new Ward(DEFAULT_WARD);
        ic = new IC(DEFAULT_IC);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PatientBuilder with the data of {@code patientToCopy}.
     */
    public PatientBuilder(Patient patientToCopy) {
        name = patientToCopy.getName();
        ward = patientToCopy.getWard();
        ic = patientToCopy.getIC();
        tags = new HashSet<>(patientToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Ward} of the {@code Patient} that we are building.
     */
    public PatientBuilder withWard(String ward) {
        this.ward = new Ward(ward);
        return this;
    }

    /**
     * Sets the {@code Ic} of the {@code Patient} that we are building.
     */
    public PatientBuilder withIc(String ic) {
        this.ic = new IC(ic);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and sets it to the {@code Patient} that we are building.
     */
    public PatientBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Patient build() {
        return new Patient(name, ward, ic, tags);
    }
}
