/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package quotes;

import com.google.gson.* ;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class QuotesFinder {

    public Quote[] quoteArrayCreator() throws FileNotFoundException {
        Gson quotes = new Gson();
        FileReader quotesFile = new FileReader("src/main/resources/singleQuote.json");
        System.out.println(quotesFile.toString());
        Quote[] newQuotes = quotes.fromJson(quotesFile, Quote[].class);
        return newQuotes;
    }

    public String toStringRandomizer (Quote[] toRead) {
        int randomQuoteNumber = random(toRead.length);
        String finalQuote = String.format("\"%s\" was said by the author %s.", toRead[randomQuoteNumber].text, toRead[randomQuoteNumber].author);
        System.out.println(finalQuote);
        return finalQuote;
    }

    public int random(int sizeOfRandom){
        int a = (int) (Math.random() * sizeOfRandom);
        return a;
    }

    public  String[] apiArrayCreator() throws IOException {

        URL url = new URL("http://ron-swanson-quotes.herokuapp.com/v2/quotes");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        // Sets the length of time to connect before exception thrown.
        con.setConnectTimeout(5000);
        // Sets the time allowed to read the data before exception thrown.
        con.setReadTimeout(5000);
        // Response code is printed below.
        System.out.println(con.getResponseCode());
        // Will tell the app to allow redirects to occur.
        con.setInstanceFollowRedirects(true); // Not used but here for reference.

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String temp = in.readLine();
        StringBuffer content = new StringBuffer();
        while(temp != null) {
            content.append(temp);
            temp = in.readLine();
        }
        in.close();
        con.disconnect();


        Gson change = new Gson();
        String[] makeFinal = change.fromJson(content.toString(), String[].class);
        return makeFinal;
    }

    public String giveAQuote() throws IOException {
        try {
            return apiArrayCreator()[0];
        } catch (IOException e) {
            return toStringRandomizer(quoteArrayCreator());
        }
    }

    // pull json file
    // create Qoute class using internet quote
    // .add quote class to end of arraylist
    // then re json whole file
    // then save file

    public boolean addToJson() throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        Quote[] originalJson = quoteArrayCreator();
        ArrayList<Quote> arrayedJson = new ArrayList<>();
        arrayedJson.addAll(Arrays.asList(originalJson));
        Quote newQuote = new Quote(null, null, null, apiArrayCreator()[0]);
        arrayedJson.add(newQuote);
        gson.toJson(arrayedJson, new FileWriter("src/main/resources/singleQuote.json", false));
        return false;
    }
}
