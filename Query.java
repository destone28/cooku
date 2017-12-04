package cooku;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class Query {

    //We need a real browser user agent or Google will block our request with a 403 - Forbidden
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
    public static String ricerca;
    public static String main(String ricerca) throws Exception {
    	String risultato ="";

    	if (ricerca.equals("/start"))
    		risultato = ("Benvenuto! Inserisci un ingrediente per riceverne la ricetta!\nPer inserire più ingredienti, scrivili tutti nello stesso messaggio.");
    	else if (ricerca.equals("/stop"))
    		risultato = ("Puoi fermarmi direttamente bloccando il bot, o, più pacificamente, non scrivendomi più! ;-)");
    	else {
        //Fetch the page
        final Document doc = Jsoup.connect("https://google.it/search?q=ricetta+"+ricerca).userAgent(USER_AGENT).get();

        //Traverse the results
        for (Element result : doc.select("h3.r a")){

            final String title = result.text();
            final String url = result.attr("href");

            //Now do something with the results (maybe something more useful than just printing to console)

            risultato += (title + " -> " + url+"\n\n");
        	 }
        }
        return risultato;
    }
}
