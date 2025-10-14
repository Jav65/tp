package seedu.noknock.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.noknock.model.AddressBook;
import seedu.noknock.model.Model;
import seedu.noknock.model.ModelManager;
import seedu.noknock.model.UserPrefs;
import seedu.noknock.testutil.PatientBuilder;

public class ListPatientsCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        AddressBook addressBook = new AddressBook();

        // two patients
        addressBook.addPerson(new PatientBuilder().withName("Alice Pauline").build());
        addressBook.addPerson(new PatientBuilder().withName("Bob Brown").build());

        model = new ModelManager(addressBook, new UserPrefs());
    }

    @Test
    public void execute_listPatients_showsOnlyPatients() {
        CommandResult result = new ListPatientsCommand().execute(model);
        assertEquals(ListPatientsCommand.MESSAGE_SUCCESS, result.getFeedbackToUser());
        assertEquals(2, model.getFilteredPersonList().size());
    }
}
