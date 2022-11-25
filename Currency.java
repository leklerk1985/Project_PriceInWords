import java.util.HashMap;

public class Currency {
    private String nameNominative;
    private String nameGenitiveSingular;
    private String nameGenitivePlural;
    private HashMap<Integer, String> currencyWords;

    Currency(String nameNominative, String nameGenitiveSingular, String nameGenitivePlural) {
        this.nameNominative = nameNominative;
        this.nameGenitiveSingular = nameGenitiveSingular;
        this.nameGenitivePlural = nameGenitivePlural;

        setCurrencyWords();
    }

    private void setCurrencyWords() {
        currencyWords = new HashMap<>();
        currencyWords.put(0, nameGenitivePlural);
        currencyWords.put(1, nameNominative);
        currencyWords.put(2, nameGenitiveSingular);
        currencyWords.put(3, nameGenitiveSingular);
        currencyWords.put(4, nameGenitiveSingular);
        currencyWords.put(5, nameGenitivePlural);
        currencyWords.put(6, nameGenitivePlural);
        currencyWords.put(7, nameGenitivePlural);
        currencyWords.put(8, nameGenitivePlural);
        currencyWords.put(9, nameGenitivePlural);
        currencyWords.put(10, nameGenitivePlural);
        currencyWords.put(11, nameGenitivePlural);
        currencyWords.put(12, nameGenitivePlural);
        currencyWords.put(13, nameGenitivePlural);
        currencyWords.put(14, nameGenitivePlural);
        currencyWords.put(15, nameGenitivePlural);
        currencyWords.put(16, nameGenitivePlural);
        currencyWords.put(17, nameGenitivePlural);
        currencyWords.put(18, nameGenitivePlural);
        currencyWords.put(19, nameGenitivePlural);
    }

    public HashMap<Integer, String> getCurrencyWords() {
        return currencyWords;
    }
}