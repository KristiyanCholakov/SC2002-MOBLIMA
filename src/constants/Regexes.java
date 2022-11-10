package constants;

/**
 * The Regex class consisting of all regular expression constants that are later used in the program.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class Regexes {
    /**
     * A regular expression of an email format.
     */
    public static final String EMAIL_REGEX = "^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$";
    /**
     * A regular expression of the password format.
     */
    public static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$";
    /**
     * A regular expression of the username format
     */
    public static final String USERNAME_REGEX = "^(?<username>[[a-z]|[A-Z]|\\d|_]+)$";
    /**
     * A regular expression of the birthday date format.
     */
    public static final String BIRTHDAY_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";
    /**
     * A regular expression of the duration time format.
     */
    public static final String DURATION_REGEX = "^\\d{2}:\\d{2}:\\d{2}$";
}
