import java.util.*;
import static java.lang.Math.floor;

public class Price {
    private int numberValue;
    private int hundred;
    private int decade;
    private int year;
    private Currency currency;

    Price(String numberVal, Currency curr) throws IllegalArgumentException {
        try {
            numberValue = Integer.parseInt(numberVal);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Нужно ввести число от 1 до 999 включительно!");
        }
        if (numberValue >= 1000 || numberValue <= 0) {
            throw new IllegalArgumentException("Нужно ввести число от 1 до 999 включительно!");
        }

        currency = curr;
        hundred = (int) floor(numberValue / 100.0);
        decade = (int) floor((numberValue - (hundred * 100.0)) / 10.0);
        year = numberValue - (hundred * 100) - (decade * 10);
    }

    private String getNumberValueInWords() {
        String numberValueInWords = "";
        ArrayList<String> filledParts = new ArrayList<>();
        ArrayList<String> allParts = new ArrayList<>();
        HashMap<Integer, String> numbersInWords;


        numbersInWords = getNumbersInWords();

        allParts.add(numbersInWords.get(hundred * 100));
        if (decade != 1) {
            allParts.add(numbersInWords.get(decade * 10));
            allParts.add(numbersInWords.get(year));
        } else {
            allParts.add(numbersInWords.get(decade * 10 + year));
        }

        for (String aPart: allParts) {
            if (!aPart.isBlank()) {
                filledParts.add(aPart);
            }
        }

        for (String fPart: filledParts) {
            numberValueInWords = numberValueInWords + fPart + " ";
        }
        return numberValueInWords.stripTrailing();
    }

    private static HashMap<Integer, String> getNumbersInWords() {
        HashMap<Integer, String> numbersInWords = new HashMap<>();
        numbersInWords.put(0, "");
        numbersInWords.put(1, "один");
        numbersInWords.put(2, "два");
        numbersInWords.put(3, "три");
        numbersInWords.put(4, "четыре");
        numbersInWords.put(5, "пять");
        numbersInWords.put(6, "шесть");
        numbersInWords.put(7, "семь");
        numbersInWords.put(8, "восемь");
        numbersInWords.put(9, "девять");
        numbersInWords.put(10, "десять");
        numbersInWords.put(11, "одиннадцать");
        numbersInWords.put(12, "двенадцать");
        numbersInWords.put(13, "тринадцать");
        numbersInWords.put(14, "четырнадцать");
        numbersInWords.put(15, "пятнадцать");
        numbersInWords.put(16, "шестнадцать");
        numbersInWords.put(17, "семнадцать");
        numbersInWords.put(18, "восемнадцать");
        numbersInWords.put(19, "девятнадцать");
        numbersInWords.put(20, "двадцать");
        numbersInWords.put(30, "тридцать");
        numbersInWords.put(40, "сорок");
        numbersInWords.put(50, "пятьдесят");
        numbersInWords.put(60, "шестьдесят");
        numbersInWords.put(70, "семьдесят");
        numbersInWords.put(80, "восемьдесят");
        numbersInWords.put(90, "девяносто");
        numbersInWords.put(100, "сто");
        numbersInWords.put(200, "двести");
        numbersInWords.put(300, "триста");
        numbersInWords.put(400, "четыреста");
        numbersInWords.put(500, "пятьсот");
        numbersInWords.put(600, "шестьсот");
        numbersInWords.put(700, "семьсот");
        numbersInWords.put(800, "восемьсот");
        numbersInWords.put(900, "девятьсот");

        return numbersInWords;
    }

    public String getPriceInWords() {
        String numberValueInWord;
        String currWord;
        HashMap<Integer, String> currencyWords;

        numberValueInWord = getNumberValueInWords();
        currencyWords = currency.getCurrencyWords();
        currWord = currencyWords.get(decade != 1 ? year : decade * 10 + year);

        return numberValueInWord + " " + currWord;
    }

    public void printPriceInWords() {
        String priceInWords = this.getPriceInWords();
        System.out.println(priceInWords);
    }
}