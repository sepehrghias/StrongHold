package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    USERCREATE("user\\s+create"),
    USERNAME("-u\\s+((?<usernameWithoutSpace>[^\\\"\\s]+)|(?<usernameWithSpace>\\\"[^\\\"]+\\\"))"),

    PASSWORD("-p\\s+((?<passwordWithoutSpace>[^\\\"\\s]+)|(?<passwordWithSpace>\\\"[^\\\"]+\\\"))"),


    PASSWORD_CONFIRM("-c\\s+((?<passwordConfirmWithoutSpace>[^\\\"\\s]+)|(?<passwordConfirmWithSpace>\\\"[^\\\"]+\\\"))"),

    EMAIL("-email\\s+(?<emailAddress>[\\S]+)\\s*"),

    NICKNAME("-n\\s+((?<nicknameWithoutSpace>[^\\\"\\s]+)|(?<nicknameWithSpace>\\\"[^\\\"]+\\\"))"),



    STAY_LOGGED_IN_LOGIN("user\\s+stay\\s+logged\\s+in\\s+login\\s+-u\\s+(?<StayUsernameWithSpace>\\\".+\\\")|(?<StayUsernameWithOutSpace>[^\\\"\\s]+)"),


    SLOGAN("-s\\s+((?<sloganWithoutSpace>[^\\\"\\s]+)|(?<sloganWithSpace>\\\"[^\\\"]+\\\"))"),

    HAVE_SLOGAN("-s\\s"),


    VALIDUSERNAME("[a-zA-Z\\d\\_]+"),

    INVALID_USER_NAME("[^a-zA-Z\\d\\_]"),

    STRONGPASSWORD("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z\\d])[\\S]{6,}"),

    VALIDEMAIL("[a-zA-Z\\d\\_\\.]+@[a-zA-Z\\_\\.]+\\.[a-zA-Z\\_\\.]+"),

    HASUPPERCASE("[A-Z]"),

    HASLOWERCASE("[a-z]"),

    HASNUMBER("[\\d]"),

    HASSPECIALCHARACTER("[^a-zA-Z\\d]"),

    QUESTIONPICK("^question\\spick"),

    QUSETIONNUMBER("-q\\s(?<number>[\\d]+)"),

    ANSWERQUESTION("-a\\s+((?<answerWithoutSpace>[^\\\"\\s]+)|(?<answerWithSpace>\\\"[^\\\"]+\\\"))"),

   CONFIRMANSWER("-c\\s+((?<answerWithoutSpace>[^\\\"\\s]+)|(?<answerWithSpace>\\\"[^\\\"]+\\\"))"),

    LOGINUSER("user\\s+login\\s+(\\-u\\s+(?<username>.+)\\s*()|\\-p\\s+(?<password>[\\S]+)\\s*()){2}\\3\\5(?<loggedInFlag>--stay-logged-in)?\\s*$"),

    PASSWORDFOROT("forgot\\s+my\\s+password"),
    FORGOTPASSWORD("forgot\\s+my\\s+password\\s+\\-u\\s+(?<username>.+)\\s*"),

    EMPTYUSERNAME("-u\\s*[\\S]"),

    EMPTYPASSWORD("-p\\s*[\\S]"),

    EMPTYCONFIRM("-c\\s*[\\S]"),

    EMPTYNICKNAME("-n\\s*[\\S]"),

    EMPTYEMAIL("-email\\s*[\\S]"),


    USERLOGIN("^\\s*user\\s+login");


    private String regex;

    LoginMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, LoginMenuCommands loginMenuCommands) {
        Matcher matcher = Pattern.compile(loginMenuCommands.regex).matcher(command);
        if (matcher.find())
            return matcher;
        return null;
    }
}
