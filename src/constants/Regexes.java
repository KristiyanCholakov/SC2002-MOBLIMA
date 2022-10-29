package constants;

public class Regexes {
    public static final String email_regex = "^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$";
    public static final String password_regex = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$";
    public static final String username_regex = "^(?<username>[[a-z]|[A-Z]|\\d|_]+)$";

    public static final String user_entry_regex = "^Username=(?<username>.+), Email=(?<email>.+), Password=(?<password>.+), F_Name=(?<fname>.+), L_Name=(?<lname>.+)$";

}
