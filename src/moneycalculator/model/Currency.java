/**
 *
 * @author Jesus Larez
 */
package moneycalculator.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Currency {

    private String isoCode;
    private String name;
    private String symbol;

    public Currency(String isoCode)
            throws IOException {
        this.isoCode = isoCode;
        URL url2
                = new URL("https://free.currconv.com/api/v7/currencies?apiKey=6a5f56da4e6e305a6014");
        URLConnection connection2 = url2.openConnection();
        try (BufferedReader reader
                = new BufferedReader(
                        new InputStreamReader(connection2.getInputStream()))) {
                    String line = reader.readLine();
                    String line1 = line.substring(line.indexOf(this.isoCode), //XXX":{"currencyName":"x","currencySymbol":"x",
                            line.indexOf("\"id\":\"" + this.isoCode + "\""));
                    String line2 = line1.substring(line1.indexOf("currencyName") + 15, line1.indexOf("currencySymbol") - 3);
                    this.name = line2;
                    String line3 = line1.substring(line1.indexOf("currencySymbol") + 17, line1.indexOf("currencySymbol") + 18);
                    this.symbol = line3;
                }
    }

    public String getIsoCode() {
        return isoCode;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

}
