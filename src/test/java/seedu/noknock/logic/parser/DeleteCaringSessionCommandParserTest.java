package seedu.noknock.logic.parser;

import static seedu.noknock.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.noknock.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.noknock.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.noknock.commons.core.index.Index;
import seedu.noknock.logic.commands.DeleteCaringSessionCommand;

/**
 * Contains unit tests for
 * {@code DeleteCaringSessionCommandParser}.
 */
public class DeleteCaringSessionCommandParserTest {

    private final DeleteCaringSessionCommandParser parser = new DeleteCaringSessionCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCaringSessionCommand() {
        String userInput = "1 2";
        DeleteCaringSessionCommand expected =
                new DeleteCaringSessionCommand(Index.fromOneBased(1), Index.fromOneBased(2));

        assertParseSuccess(parser, userInput, expected);
    }

    @Test
    public void parse_missingArgs_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCaringSessionCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "", expectedMessage);
        assertParseFailure(parser, "1", expectedMessage);
        assertParseFailure(parser, "   1   ", expectedMessage);
    }

    @Test
    public void parse_tooManyArgs_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCaringSessionCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "1 2 3", expectedMessage);
    }

    @Test
    public void parse_nonIntegerArgs_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCaringSessionCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "a b", expectedMessage);
        assertParseFailure(parser, "1 b", expectedMessage);
        assertParseFailure(parser, "a 2", expectedMessage);
        assertParseFailure(parser, "0 1", expectedMessage); // invalid index
        assertParseFailure(parser, "-1 1", expectedMessage); // invalid index
    }
}
