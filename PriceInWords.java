import java.util.*;

public class PriceInWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String valueFromUser;
        boolean success;
        Currency ruble;
        Price price;


        ruble = new Currency("рубль", "рубля", "рублей");
        success = false;
        while (!success) {
            valueFromUser = scanner.next();
            try {
                price = new Price(valueFromUser, ruble);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
            success = true;
            price.printPriceInWords();
        }
    }
}






