package validators;

import java.util.Arrays;

public class NameValidator implements StringValidator {

    // массив содержит все запрещенные имена (все буквы большие, потом будем имя приводить к заглавным буквам и сравнивать с элементами массива)
    private final static String[] notAllowedNames = {"KING", "LADY", "LORD", "QUEEN", "SIR"};// нужно записать элементы в алфавитном порядке, чтобы binarySearch работал, без сортировки binarySearch не будет работать
    // массив содержит все римские цифры
    private final static char[] romanNumerals = {'C', 'D', 'I', 'L', 'M', 'V', 'X'};// нужно записать элементы в алфавитном порядке, чтобы binarySearch работал, без сортировки binarySearch не будет работать

    // проверяет имя на валидность
    @Override
    public boolean isValid(String text) {
        if (text == null || text.trim().length() == 0) {
            return false;
        }

        boolean isValidName = false;
        if (Arrays.binarySearch(notAllowedNames, text.toUpperCase()) < 0) { // если имя не входит в список запрещенных имен
            if (Arrays.binarySearch(romanNumerals, text.toUpperCase().charAt(text.length() - 1)) < 0) { // если последняя буква не римская
                isValidName = true;
            }
        }

        return isValidName;
    }
}
