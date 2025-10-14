package seedu.noknock.logic.parser;

import static seedu.noknock.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.noknock.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.noknock.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.noknock.logic.commands.FindPatientCommand;
import seedu.noknock.model.person.PatientNameContainsKeywordsPredicate;

public class FindPatientCommandParserTest {

    private FindPatientCommandParser parser = new FindPatientCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindPatientCommand() {
        // no leading and trailing whitespaces
        FindPatientCommand expectedFindPatientCommand =
                new FindPatientCommand(new PatientNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindPatientCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindPatientCommand);
    }
}
