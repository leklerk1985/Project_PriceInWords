import java.util.*;

public class Testing {
    private static final String EXPECTED_ERROR_MESSAGE = "Нужно ввести число от 1 до 999 включительно!";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final Currency RUBLE = new Currency("рубль", "рубля", "рублей");
    private static Price price;

    public static void main(String[] args) {

        // *** Введение некорректных значений.

        // Тест 1 (ввод нечисловых строк).
        testEnteringNotNumericString();

        // Тест 2 (ввод отрицательных чисел).
        testEnteringNegativeNumber();

        // Тест 3 (ввод нуля).
        testEnteringZero();

        // Тест 4 (ввод 1000).
        testEntering1000();

        // Тест 5 (ввод чисел больше 1000).
        testEnteringBiggerThan1000();


        // *** Введение корректных значений.

        // Тест 6 (ввод чисел между 1 и 9).
        testEnteringBetween1And9();

        // Тест 7 (ввод чисел между 11 и 19).
        testEnteringBetween11And19();

        // Тест 8 (ввод чистых десятков (например, 20)).
        testEnteringPureDecades();

        // Тест 9 (ввод чистых сотен (например, 300)).
        testEnteringPureHundreds();

        // Тест 10 (ввод сотен-десятков (например, 420)).
        testEnteringHundredsWithDecades();

        // Тест 11 (ввод сотен-единиц (например, 508)).
        testEnteringHundredsWithYears();

        // Тест 12 (ввод десятков-единиц (например, 45)).
        testEnteringDecadesWithYears();

        // Тест 13 (ввод сотен-десятков-единиц (например, 387)).
        testEnteringHundredsWithDecadesWithYears();

    }

    private static void testEnteringIncorrectValues(String scenario, String[] values) {
        for (String value: values) {
            try {
                testEnteringIncorrectValue(scenario, value);
            } catch (IllegalArgumentException e) {
                System.err.printf(e.getMessage());
                return;
            }
        }
        System.out.printf(ANSI_GREEN + "\"%s\" passed %n" + ANSI_RESET, scenario);
    }

    private static void testEnteringIncorrectValue(String scenario, String value) throws IllegalArgumentException {
        try {
            price = new Price(value, RUBLE);
        } catch (IllegalArgumentException e) {
            String errMessage = e.getMessage();
            if (!errMessage.equals(EXPECTED_ERROR_MESSAGE)) {
                String message = String.format("\"%s\" fails with message \"%s\" %n", scenario, errMessage);
                throw new IllegalArgumentException(message);
            }
        }
    }

    private static void testEnteringCorrectValues(String scenario, HashMap<String, String> values) {
        for (Map.Entry<String, String> item: values.entrySet()) {
            try {
                testEnteringCorrectValue(scenario, item.getKey(), item.getValue());
            } catch (RuntimeException e) {
                System.err.printf(e.getMessage());
                return;
            }
        }

        System.out.printf(ANSI_GREEN + "\"%s\" passed %n" + ANSI_RESET, scenario);
    }

    private static void testEnteringCorrectValue(String scenario, String value, String expectedValue) throws RuntimeException {
        price = new Price(value, RUBLE);
        String priceInWords = price.getPriceInWords();

        if (!priceInWords.equals(expectedValue)) {
            String messageExpected = String.format("Expected %s, got %s", expectedValue, priceInWords);
            String message = String.format("\"%s\" fails with message \"%s\" %n", scenario, messageExpected);
            throw new RuntimeException(message);
        }
    }

    private static void testEnteringNotNumericString() {
        String[] values = {"триста сорок", "девяносто", "twenty", "aaa", "", "_", "-", "$", ")", "!"};
        testEnteringIncorrectValues("Тест: сообщение о некорректных данных при вводе нечисловой строки", values);
    }

    private static void testEnteringNegativeNumber() {
        String[] values = {"-31", "-1000", "-2", "-999999", "-178"};
        testEnteringIncorrectValues("Тест: сообщение о некорректных данных при вводе отрицательного числа", values);
    }

    private static void testEnteringZero() {
        String[] values = {"0"};
        testEnteringIncorrectValues("Тест: сообщение о некорректных данных при вводе нуля", values);
    }

    private static void testEntering1000() {
        String[] values = {"1000"};
        testEnteringIncorrectValues("Тест: сообщение о некорректных данных при вводе 1000", values);
    }

    private static void testEnteringBiggerThan1000() {
        String[] values = {"1469", "21700", "1000000", "278910", "999999999"};
        testEnteringIncorrectValues("Тест: сообщение о некорректных данных при вводе числа больше 1000", values);
        System.out.println();
    }

    private static void testEnteringBetween1And9() {
        HashMap<String, String> values = new HashMap<>();
        values.put("1", "один рубль");
        values.put("3", "три рубля");
        values.put("4", "четыре рубля");
        values.put("7", "семь рублей");
        values.put("9", "девять рублей");

        testEnteringCorrectValues("Тест: ввод чисел между 1 и 9", values);
    }

    private static void testEnteringBetween11And19() {
        HashMap<String, String> values = new HashMap<>();
        values.put("11", "одиннадцать рублей");
        values.put("12", "двенадцать рублей");
        values.put("16", "шестнадцать рублей");
        values.put("17", "семнадцать рублей");
        values.put("19", "девятнадцать рублей");

        testEnteringCorrectValues("Тест: ввод чисел между 11 и 19", values);
    }

    private static void testEnteringPureDecades() {
        HashMap<String, String> values = new HashMap<>();
        values.put("10", "десять рублей");
        values.put("30", "тридцать рублей");
        values.put("40", "сорок рублей");
        values.put("60", "шестьдесят рублей");
        values.put("90", "девяносто рублей");

        testEnteringCorrectValues("Тест: ввод чистых десятков", values);
    }

    private static void testEnteringPureHundreds() {
        HashMap<String, String> values = new HashMap<>();
        values.put("100", "сто рублей");
        values.put("300", "триста рублей");
        values.put("400", "четыреста рублей");
        values.put("700", "семьсот рублей");
        values.put("800", "восемьсот рублей");

        testEnteringCorrectValues("Тест: ввод чистых сотен", values);
    }

    private static void testEnteringHundredsWithDecades() {
        HashMap<String, String> values = new HashMap<>();
        values.put("110", "сто десять рублей");
        values.put("430", "четыреста тридцать рублей");
        values.put("460", "четыреста шестьдесят рублей");
        values.put("680", "шестьсот восемьдесят рублей");
        values.put("990", "девятьсот девяносто рублей");

        testEnteringCorrectValues("Тест: ввод сотен-десятков", values);
    }

    private static void testEnteringHundredsWithYears() {
        HashMap<String, String> values = new HashMap<>();
        values.put("108", "сто восемь рублей");
        values.put("301", "триста один рубль");
        values.put("407", "четыреста семь рублей");
        values.put("609", "шестьсот девять рублей");
        values.put("902", "девятьсот два рубля");

        testEnteringCorrectValues("Тест: ввод сотен-единиц", values);
    }

    private static void testEnteringDecadesWithYears() {
        HashMap<String, String> values = new HashMap<>();
        values.put("23", "двадцать три рубля");
        values.put("31", "тридцать один рубль");
        values.put("59", "пятьдесят девять рублей");
        values.put("72", "семьдесят два рубля");
        values.put("99", "девяносто девять рублей");

        testEnteringCorrectValues("Тест: ввод десятков-единиц", values);
    }

    private static void testEnteringHundredsWithDecadesWithYears() {
        HashMap<String, String> values = new HashMap<>();
        values.put("111", "сто одиннадцать рублей");
        values.put("312", "триста двенадцать рублей");
        values.put("588", "пятьсот восемьдесят восемь рублей");
        values.put("646", "шестьсот сорок шесть рублей");
        values.put("999", "девятьсот девяносто девять рублей");

        testEnteringCorrectValues("Тест: ввод сотен-десятков-единиц", values);
    }
}
