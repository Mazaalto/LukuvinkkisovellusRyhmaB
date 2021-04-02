
package lukuvinkkisovellus;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import lukuvinkki_dao.LukuvinkkiDao;
import lukuvinkki_dao.TallennusDao;

public class UI {

    private Scanner reader;
    private LukuvinkkiService lukuvinkkiService;

    public UI(Scanner reader) throws FileNotFoundException, IOException {
        Properties properties = new Properties();  
        properties.load(new FileInputStream("config.properties"));
        String lukuvinkkiTiedosto = properties.getProperty("lukuvinkkiTiedosto");
        
        this.reader = reader;
        LukuvinkkiDao lukuvinkkiDao = new TallennusDao(lukuvinkkiTiedosto);
        this.lukuvinkkiService = new LukuvinkkiService(lukuvinkkiDao);
    }

    public void start() {
        System.out.println("Tervetuloa lukuvinkkisovellukseen!");
        printCommands();

    }

    private void printCommands() {
        System.out.println("Valitse allaolevista komennoista numero ja paina enter");
        while (true) {
            System.out.println("1 (lisää lukuvinkki) ja 2 (listaa lukuvinkit), tyhjä lopettaa");
            String komento = reader.nextLine();
            if (komento.equals("") || komento.equals(" ")) {
                break;
            }
            if (Integer.parseInt(komento) == 1) {
                //Tähän toteutetaan lisääminen
                System.out.println("Lisätään lukuvinkki");
                System.out.println("Anna otsikko: ");
                String otsikko = reader.nextLine();
                System.out.println("Anna url: ");
                String url = reader.nextLine();
                lukuvinkkiService.lisaaLukuvinkki(new Lukuvinkki(otsikko, url));
            }
            if (Integer.parseInt(komento) == 2) {
                //tähän toteutetaan kaikkien lukuvinkkien tulostus
                System.out.println("Listataan lukuvinkit");
                lukuvinkkiService.listaaKaikki();

            } else {
                System.out.println("Epäkelpo komento. Syötä komento uudelleen");
            }

        }

    }
}
