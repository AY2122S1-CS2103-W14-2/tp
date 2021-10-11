package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_CASE_NUMBER = new Prefix("cn/");
    public static final Prefix PREFIX_HOME_ADDRESS = new Prefix("ha/");
    public static final Prefix PREFIX_WORK_ADDRESS = new Prefix("wa/");
    public static final Prefix PREFIX_QUARANTINE_ADDRESS = new Prefix("qa/");
    public static final Prefix PREFIX_SHN_PERIOD = new Prefix("sh/");
    public static final Prefix PREFIX_NEXT_OF_KIN_NAME = new Prefix("kn/");
    public static final Prefix PREFIX_NEXT_OF_KIN_PHONE = new Prefix("kp/");
    public static final Prefix PREFIX_NEXT_OF_KIN_ADDRESS = new Prefix("ka/");
    // TODO: Remove tag prefix when integrating changes to Add command.
    @Deprecated
    public static final Prefix PREFIX_TAG = new Prefix("t/");
}
