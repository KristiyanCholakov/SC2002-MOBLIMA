package constants;

/**
 * A class consisting of all regular expression constants.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class Regexes {
    public static final String EMAIL_REGEX = "^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$";
    public static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$";
    public static final String USERNAME_REGEX = "^(?<username>[[a-z]|[A-Z]|\\d|_]+)$";
    public static final String BIRTHDAY_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";
    public static final String DURATION_REGEX = "^\\d{2}:\\d{2}:\\d{2}$";
}
