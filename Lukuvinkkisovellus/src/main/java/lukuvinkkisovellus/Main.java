package lukuvinkkisovellus;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import lukuvinkki_dao.TallennusDao;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner reader = new Scanner(System.in);

        
        
        Properties properties = new Properties();  
        properties.load(new FileInputStream("config.properties"));
        
        String lukuvinkkiTiedosto = properties.getProperty("lukuvinkkiTiedosto");
        TallennusDao tdao = new TallennusDao(lukuvinkkiTiedosto);
        
        tdao.lisaa(new Lukuvinkki("Lukuvinkki1", "www.netti.fi"));
        tdao.lisaa(new Lukuvinkki("Lukuvinkki2", "www.internet.net"));
        
        List<Lukuvinkki> lukuvinkit = tdao.listaaKaikki();
        
        for (int i = 0;i < lukuvinkit.size(); i++) {
            Lukuvinkki lv = lukuvinkit.get(i);
            System.out.println(lv.otsikko + " " + lv.url);
        }
    }
}