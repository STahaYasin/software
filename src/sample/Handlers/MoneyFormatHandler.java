package sample.Handlers;

import java.text.NumberFormat;
import java.util.Locale;

public class MoneyFormatHandler {
    public static String getAsText(double amount){
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        return numberFormat.format(amount);
    }
}