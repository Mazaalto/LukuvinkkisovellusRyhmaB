package lukuvinkkisovellus;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import lukuvinkki_dao.LukuvinkkiDao;
import lukuvinkki_dao.TallennusDao;

public class UI {

    private Scanner reader;
    private LukuvinkkiService lukuvinkkiService;

    public UI(Scanner reader) throws FileNotFoundException, IOException, Exception {

        this.reader = reader;
        LukuvinkkiDao lukuvinkkiDao = new TallennusDao("jdbc:sqlite:lukuvinkkisovellus.db");
        this.lukuvinkkiService = new LukuvinkkiService(lukuvinkkiDao);
    }

    public void start() throws Exception {
        System.out.println("Tervetuloa lukuvinkkisovellukseen!");
        printCommands();

    }

    private void printCommands() throws Exception {
        System.out.println("Valitse allaolevista komennoista numero ja paina enter");
        while (true) {
            System.out.println("1 (lisää lukuvinkki), 2 (listaa lukuvinkit), 3 (poista lukuvinkki), 4 (kerro vinkkien määrä), 5 (poista kaikki lukuvinkit), tyhjä lopettaa");
            String komento = reader.nextLine();
            if (komento.equals("") || komento.equals(" ")) {
                break;
            } else if (komento.equals("1")) {
                System.out.println("1 (lisää linkki), 2 (lisää kirja)");
                String tyyppi = reader.nextLine();

                if (tyyppi.equals("1")) {
                    //Tähän toteutetaan lisääminen
                    System.out.println("Lisätään lukuvinkki (linkki)");
                    System.out.println("Anna otsikko: ");
                    String otsikko = reader.nextLine();
                    System.out.println("Anna url: ");
                    String url = reader.nextLine();
                    lukuvinkkiService.lisaaLinkki(new Linkki(otsikko, url));
                } else if (tyyppi.equals("2")) {
                    System.out.println("Lisätään lukuvinkki (kirja)");
                    System.out.println("Anna otsikko: ");
                    String otsikko = reader.nextLine();
                    System.out.println("Anna kirjailija: ");
                    String kirjailija = reader.nextLine();
                    System.out.println("Anna julkaisuvuosi: ");
                    int julkaisuvuosi = Integer.valueOf(reader.nextLine());
                    System.out.println("Anna julkaisija: ");
                    String julkaisija = reader.nextLine();
                    System.out.println("Anna url: ");
                    String url = reader.nextLine();
                    lukuvinkkiService.lisaaKirja(new Kirja(otsikko, kirjailija, julkaisuvuosi, julkaisija, url));
                }
            } else if (komento.equals("2")) {
                System.out.println("1 (linkit), 2 (kirjat), muuten (listaa kaikki)");
                //tähän toteutetaan kaikkien lukuvinkkien tulostus
                String kategoria = reader.nextLine();
                if (kategoria.equals("1")) {

                    List<Lukuvinkki> lukuvinkit = lukuvinkkiService.listaaKaikkiLinkit();

                    if (lukuvinkit.isEmpty()) {
                        System.out.println("ei tallennettuja vinkkejä.");
                    } else {
                        System.out.println("Listataan lukuvinkit");
                        lukuvinkit.stream().forEach(lv -> System.out.println(lv));
                    }
                } else if (kategoria.equals("2")) {
                    List<Lukuvinkki> lukuvinkit = lukuvinkkiService.listaaKirjat();

                    if (lukuvinkit.isEmpty()) {
                        System.out.println("ei tallennettuja vinkkejä.");
                    } else {
                        System.out.println("Listataan lukuvinkit (kirjat)");
                        lukuvinkit.stream().forEach(lv -> System.out.println(lv));
                    }

                }
                List<Lukuvinkki> lukuvinkit2 = lukuvinkkiService.listaaKaikki();
                lukuvinkit2.stream().forEach(lv -> System.out.println(lv));
                
            } else if (komento.equals("3")) {
                //tähän toteutetaan lukuvinkkien poistaminen
                List<Linkki> lukuvinkit = lukuvinkkiService.listaaKaikki();

                if (lukuvinkit.isEmpty()) {
                    System.out.println("Ei vielä yhtään lukuvinkkiä");
                } else {
                    System.out.println("Lukuvinkit tällä hetkellä:");

                    lukuvinkit.stream().forEach(lv -> System.out.println(lv));
                    while (true) {
                        System.out.println("Anna otsikko, jonka haluat poistaa:");
                        String otsikko = reader.nextLine();

                        lukuvinkit = lukuvinkkiService.listaaOtsikonPerusteella(otsikko);

                        lukuvinkit.stream().forEach(lv -> System.out.println(lv));
                        if (lukuvinkit.isEmpty()) {
                            System.out.println("Hakusanasi ei vastannut yhtäkään otsikkoa ohjelmassa");
                        } else {
                            break;
                        }

                    }
                    if (lukuvinkit.size() == 1) {
                        kysyPoistetaanko(lukuvinkit, reader, lukuvinkkiService);
                    } else {
                        while (lukuvinkit.size() > 1) {
                            System.out.println("Tarkenna hakusanaa, hakusana otsikolle:");
                            String otsikkotarkennus = reader.nextLine();
                            lukuvinkit = lukuvinkkiService.listaaOtsikonPerusteella(otsikkotarkennus);
                        }
                        kysyPoistetaanko(lukuvinkit, reader, lukuvinkkiService);
                    }
                }

            } else if (komento.equals("4")) {
                System.out.println("Lukuvinkkien määrä järjestelmässä yhteensä: " + lukuvinkkiService.getLukuvinkkienMaara());

            } else if (komento.equals("5")) {
                System.out.println("Haluatko varmasti poistaa kaikki lukuvinkit? 1 kyllä, 2 ei");
                if (reader.nextLine().equals("1")) {
                    System.out.println("Kaikki lukuvinkit poistettu");
                    lukuvinkkiService.tyhjennaTietokanta();
                }
            } else {
                System.out.println("Epäkelpo komento. Syötä komento uudelleen");
            }
        }
    }

    private void kysyPoistetaanko(List<Linkki> lukuvinkit, Scanner reader, LukuvinkkiService lukuvinkkiService) throws Exception {
        System.out.println("Poistetaanko: " + lukuvinkit.get(0).toString());
        System.out.println("1 poistetaan, 2 ei poisteta");
        if (reader.nextLine().equals("1")) {
            System.out.println("Oletko aivan varma? " + lukuvinkit.get(0).toString());
            System.out.println("1 kyllä, 2 ei");
            if (reader.nextLine().equals("1")) {
                lukuvinkkiService.poistaLukuvinkki(lukuvinkit.get(0));
            }
        }
    }

}
