package seedu.noknock.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.noknock.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.noknock.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.noknock.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.noknock.testutil.TypicalPatients.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.noknock.commons.core.index.Index;
import seedu.noknock.logic.Messages;
import seedu.noknock.model.AddressBook;
import seedu.noknock.model.Model;
import seedu.noknock.model.ModelManager;
import seedu.noknock.model.UserPrefs;
import seedu.noknock.model.person.Name;
import seedu.noknock.model.person.NextOfKin;
import seedu.noknock.model.person.Patient;
import seedu.noknock.model.person.Phone;
import seedu.noknock.model.person.Relationship;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteNextOfKinCommand}.
 */
public class DeleteNextOfKinCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Patient patient = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        if (patient.getNextOfKinList().size() < 2) {
            NextOfKin nok1 = new NextOfKin(new Name("Jane Doe"), new Phone("98765432"),
                    Relationship.fromString("Mother"));
            NextOfKin nok2 = new NextOfKin(new Name("John Doe"), new Phone("91234567"),
                    Relationship.fromString("Father"));
            List<NextOfKin> noks = new ArrayList<>(Arrays.asList(nok1, nok2));
            Patient updated = patient.withNextOfKinList(noks);
            model.setPatient(patient, updated);
        }
    }

    @Test
    public void execute_validIndicesUnfilteredList_success() {
        Index patientIndex = INDEX_FIRST_PERSON;
        Patient patient = model.getFilteredPatientList().get(patientIndex.getZeroBased());

        // Delete the first NOK
        Index nokIndex = Index.fromOneBased(1);
        NextOfKin nokToDelete = patient.getNextOfKinList().get(nokIndex.getZeroBased());

        DeleteNextOfKinCommand command = new DeleteNextOfKinCommand(patientIndex, nokIndex);

        List<NextOfKin> updatedNokList = new ArrayList<>(patient.getNextOfKinList());
        updatedNokList.remove(nokToDelete);
        Patient editedPatient = patient.withNextOfKinList(updatedNokList);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPatient(patient, editedPatient);
        expectedModel.updateFilteredPatientList(seedu.noknock.model.Model.PREDICATE_SHOW_ALL_PERSONS);

        String expectedMessage = String.format(DeleteNextOfKinCommand.MESSAGE_DELETE_NOK_SUCCESS,
                Messages.formatNextOfKin(nokToDelete));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPatientIndexUnfilteredList_failure() {
        Index outOfBoundPatientIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        Index anyNokIndex = Index.fromOneBased(1); // arbitrary

        DeleteNextOfKinCommand command = new DeleteNextOfKinCommand(outOfBoundPatientIndex, anyNokIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidNokIndex_failure() {
        Index patientIndex = INDEX_FIRST_PERSON;
        Patient patient = model.getFilteredPatientList().get(patientIndex.getZeroBased());

        // NOK index out of bounds
        int nokCount = patient.getNextOfKinList().size();
        Index outOfBoundNokIndex = Index.fromOneBased(nokCount + 1);

        DeleteNextOfKinCommand command = new DeleteNextOfKinCommand(patientIndex, outOfBoundNokIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_NOK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Index pIdx1 = INDEX_FIRST_PERSON;
        Index pIdx2 = Index.fromOneBased(2);
        Index nIdx1 = Index.fromOneBased(1);
        Index nIdx2 = Index.fromOneBased(2);

        DeleteNextOfKinCommand standardCommand = new DeleteNextOfKinCommand(pIdx1, nIdx1);

        // same values -> true
        DeleteNextOfKinCommand commandWithSameValues = new DeleteNextOfKinCommand(pIdx1, nIdx1);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> false
        assertFalse(standardCommand.equals(null));

        // different type -> false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different patient index -> false
        assertFalse(standardCommand.equals(new DeleteNextOfKinCommand(pIdx2, nIdx1)));

        // different nok index -> false
        assertFalse(standardCommand.equals(new DeleteNextOfKinCommand(pIdx1, nIdx2)));
    }

    @Test
    public void toStringMethod() {
        Index patientIndex = Index.fromOneBased(1);
        Index nokIndex = Index.fromOneBased(2);
        DeleteNextOfKinCommand command = new DeleteNextOfKinCommand(patientIndex, nokIndex);

        String expected = DeleteNextOfKinCommand.class.getCanonicalName()
                + "{patientIndex=" + patientIndex
                + ", nokIndex=" + nokIndex + "}";

        assertEquals(expected, command.toString());
    }
}
