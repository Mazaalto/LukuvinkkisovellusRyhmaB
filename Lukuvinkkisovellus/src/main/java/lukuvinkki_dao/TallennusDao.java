
package lukuvinkki_dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lukuvinkkisovellus.Lukuvinkki;

public class TallennusDao implements LukuvinkkiDao{
    private List<Lukuvinkki> lukuvinkit;
    private String lukuvinkkiTiedosto;
    
    public TallennusDao(String tiedosto) throws Exception {
        lukuvinkit = new ArrayList<>();
        lukuvinkkiTiedosto = tiedosto;
        try {
            Scanner lukija = new Scanner(new File(lukuvinkkiTiedosto));
            while (lukija.hasNextLine()) {
                String[] osat = lukija.nextLine().split(";");
                lukuvinkit.add(new Lukuvinkki(osat[0], osat[1]));               
            }
        } catch (Exception e) {
            FileWriter kirjoittaja = new FileWriter(new File(lukuvinkkiTiedosto));
            kirjoittaja.close();
        }
    }
    
// Tänne totetutetaan lukuvinkkien tallennus.
    @Override
    public List<Lukuvinkki> listaaKaikki() {
        return lukuvinkit;
    }

    @Override
    public void lisaa(Lukuvinkki lukuvinkki) throws Exception {
        lukuvinkit.add(lukuvinkki);
        tallenna();
    }
    
    private void tallenna() throws Exception {
        try (FileWriter kirjoittaja = new FileWriter(new File(lukuvinkkiTiedosto))) {
            for (Lukuvinkki vinkki: lukuvinkit) {
                kirjoittaja.write(vinkki.getOtsikko() + ";" + vinkki.getUrl() + "\n");
            }   
            System.out.println("Tallennus onnistui!");
           
        } 
    }

    @Override
    public int LukuvinkkienMaara() {
       return lukuvinkit.size();
    }
    
}
