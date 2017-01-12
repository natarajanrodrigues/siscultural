package io.github.siscultural.utils;

/**
 * Created by natarajan on 12/01/17.
 */
public class NumberRegisterUtils {

    public static String onlyNumber(String formattedNumberRegister) {
        return null;
    }

    public static String formatCode(String unformattedNumberRegister) {
        StringBuilder stringBuilder = new StringBuilder();

        if (unformattedNumberRegister.length() == 11) {
            stringBuilder.append(unformattedNumberRegister.substring(0,3));
            stringBuilder.append(".");
            stringBuilder.append(unformattedNumberRegister.substring(3,6));
            stringBuilder.append(".");
            stringBuilder.append(unformattedNumberRegister.substring(6,9));
            stringBuilder.append("-");
            stringBuilder.append(unformattedNumberRegister.substring(9,11));

        } else if (unformattedNumberRegister.length() == 14){
            stringBuilder.append(unformattedNumberRegister.substring(0,2));
            stringBuilder.append(".");
            stringBuilder.append(unformattedNumberRegister.substring(2,5));
            stringBuilder.append(".");
            stringBuilder.append(unformattedNumberRegister.substring(5,8));
            stringBuilder.append("/");
            stringBuilder.append(unformattedNumberRegister.substring(8,12));
            stringBuilder.append("-");
            stringBuilder.append(unformattedNumberRegister.substring(12,14));

        }


        return stringBuilder.toString();

    }

}
