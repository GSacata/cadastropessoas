package nexum.cadastropessoas.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PeopleUtils {

    public static Boolean CPFValidator(String document) {
        String cleanDoc = PeopleUtils.onlyDigits(document);
        if (cleanDoc.length() != 11) {
            return false;
        }
        String leadingNine = cleanDoc.substring(0, 9);
        String firstTrailing = cleanDoc.substring(9, 10);
        String secondTrailing = cleanDoc.substring(10);

        String firstTVerif = CPFReturnDigit(leadingNine);
        String secondTVerif = CPFReturnDigit(leadingNine + firstTrailing);

        if (firstTrailing.equals(firstTVerif) && secondTrailing.equals(secondTVerif)) {
            return true;
        } else {
            return false;
        }
    }

    private static String CPFReturnDigit(String partialCleanDoc) {
        Integer multiplier = partialCleanDoc.length() + 1;
        Integer sum = 0;
        String[] splitDoc = partialCleanDoc.split("");

        for (String num: splitDoc) {
            Integer numInt = Integer.parseInt(num);
            sum += (numInt * multiplier);
            multiplier -= 1;
        }
        Integer remainder = sum % 11;

        if (remainder < 2) {
            return Integer.toString(0);
        } else {
            return Integer.toString(11 - remainder);
        }
    }

    public static Boolean CNPJValidator(String document) {
        String cleanDoc = PeopleUtils.onlyDigits(document);
        if (cleanDoc.length() != 14) {
            return false;
        }
        String leadingtwelve = cleanDoc.substring(0, 12);
        String firstTrailing = cleanDoc.substring(12, 13);
        String secondTrailing = cleanDoc.substring(13);

        String firstTVerif = CNPJReturnDigit(leadingtwelve);
        String secondTVerif = CNPJReturnDigit(leadingtwelve + firstTrailing);

        if (firstTrailing.equals(firstTVerif) && secondTrailing.equals(secondTVerif)) {
            return true;
        } else {
            return false;
        }
    }

    private static String CNPJReturnDigit(String partialCleanDoc) {
        Integer multiplier = 6;
        Integer sum = 0;
        String[] splitDoc = partialCleanDoc.split("");

        if (partialCleanDoc.length() == 12) {
            multiplier -= 1;
        }

        for (String num: splitDoc) {
            Integer numInt = Integer.parseInt(num);
            sum += (numInt * multiplier);
            if (multiplier.equals(2)) {
                multiplier = 9;
            } else {
                multiplier -= 1;
            }
        }
        Integer remainder = sum % 11;

        if (remainder < 2) {
            return Integer.toString(0);
        } else {
            return Integer.toString(11 - remainder);
        }
    }

    public static Boolean PhoneValidator(String phone) {
        // Currently only validates phone numbers following brazillian standards
        // 333, 0 800 222 5151, 55 14 0000-1111, 55 14 99999-8888, 055 14 96666-4444
        String cleanPhone = PeopleUtils.onlyDigits(phone);
        System.out.println(cleanPhone);

        if (cleanPhone.length() == 3) {
            return true;
        }
        else if (cleanPhone.length() >= 11 && cleanPhone.length() <= 14) {
            return true;
        }
        else {
            return false;
        }
    }

    public static Boolean EmailValidator(String email) {
        Pattern pat1 = Pattern.compile("\\p{Space}");
        Pattern pat2 = Pattern.compile("[@]+");
        Matcher mat1 = pat1.matcher(email);
        Matcher mat2 = pat2.matcher(email);
        if (!mat1.find() && mat2.find()) {
            return true;
        } else {
            return false;
        }
    }

    public static String onlyDigits(String document) {
        String[] toArray = document.split("");
        List<String> toDigitsList = new ArrayList<String>();

        Pattern pat = Pattern.compile("\\d+");
        
        for (String item: toArray) {
            Matcher mat = pat.matcher(item);
            Boolean found = mat.find();
            if (found) {
                toDigitsList.add(item);
            }
        }

        String cleanString = String.join("", toDigitsList);
        return cleanString;
    }
}