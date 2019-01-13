package fish.util;

import java.util.regex.Pattern;

public class Validator {
    // 校验非空
    public static boolean isNotNull(String str) {
        String str1 = str.trim();
        if (null != str1 && !("".equals(str1))) {
            return true;
        }
        return false;
    }

    // 数字校验
    public static boolean isNumber(String str) {
        String str1 = str.trim();
        Pattern pattern = Pattern.compile("[0-9]+");
        return pattern.matcher(str1).matches();
    }

    public static boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println("1234:   " + Validator.isNumber("1"));
        System.out.println("S1234:   " + Validator.isNumber("s1234"));
        System.out.println("1.123:   " + Validator.isFloat("1.1e13"));
    }
}
