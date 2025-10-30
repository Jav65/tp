package seedu.noknock.logic.parser;

import static seedu.noknock.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.noknock.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.noknock.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.noknock.logic.commands.FindPatientByNextOfKinCommand;
import seedu.noknock.model.person.PatientNokContainsKeywordsPredicate;

/**
 * Contains unit tests for
 * {@code FindPatientByNextOfKinCommandParser}.
 */
public class FindPatientByNextOfKinCommandParserTest {

    private final FindPatientByNextOfKinCommandParser parser = new FindPatientByNextOfKinCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientByNextOfKinCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindPatientByNextOfKinCommand() {
        // Multiple keywords, with extra spaces between
        String userInput = "Alice   Bob";
        FindPatientByNextOfKinCommand expectedCommand =
                new FindPatientByNextOfKinCommand(new PatientNokContainsKeywordsPredicate(
                        Arrays.asList("Alice", "Bob")));

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
