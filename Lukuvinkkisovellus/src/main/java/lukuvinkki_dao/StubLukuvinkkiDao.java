
package lukuvinkki_dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lukuvinkkisovellus.Kirja;
import lukuvinkkisovellus.Linkki;
import lukuvinkkisovellus.Lukuvinkki;

public class StubLukuvinkkiDao implements LukuvinkkiDao {

    private ArrayList<Lukuvinkki> lukuvinkit;

    public StubLukuvinkkiDao() {
        this.lukuvinkit = new ArrayList<Lukuvinkki>();        
    }
     
    
    @Override
    public ArrayList<Lukuvinkki> listaaKaikki() {
        return lukuvinkit;
    }
    

    @Override
    public void lisaaLinkki(Linkki lukuvinkki) throws Exception {
        lukuvinkit.add(lukuvinkki); 
    }

    @Override
    public int LukuvinkkienMaara() {
        return lukuvinkit.size();
    }

 
    @Override
    public void poistaLinkki(Lukuvinkki lukuvinkki) throws Exception {
        for (Lukuvinkki l : lukuvinkit) {
            if (l.getOtsikko().equals(lukuvinkki.getOtsikko())) {
                lukuvinkit.remove(l);
                break;
            }
        }
    }

    @Override
    public void tyhjennaTietokanta() throws Exception {
        lukuvinkit.removeAll(lukuvinkit);
    }

    @Override
    public void lisaaKirja(Kirja lukuvinkki) throws Exception {
        lukuvinkit.add(lukuvinkki);
    }

    @Override
    public List<Lukuvinkki> listaaKirjat() {
        return lukuvinkit;
    }

    @Override
    public int KirjojenLukumaara() {
        return lukuvinkit.size();
    }

    @Override
    public void poistaKirja(Kirja kirja) throws Exception {
        for (Lukuvinkki l : lukuvinkit) {
            if (l.getOtsikko().equals(kirja.getOtsikko())) {
                lukuvinkit.remove(l);
                break;
            }
        }
    }

    @Override
    public List<Lukuvinkki> listaaKaikkiLinkit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void merkkaaLuetuksi(Lukuvinkki lukuvinkki) throws Exception {
        lukuvinkki.merkkaaLuetuksi();
    }

    @Override
    public void tuoTiedostosta(String tiedosto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public File vieTiedostoon() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
