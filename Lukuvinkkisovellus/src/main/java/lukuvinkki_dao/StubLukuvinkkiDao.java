
package lukuvinkki_dao;

import java.util.ArrayList;
import java.util.List;
import lukuvinkkisovellus.Kirja;
import lukuvinkkisovellus.Linkki;
import lukuvinkkisovellus.Lukuvinkki;

public class StubLukuvinkkiDao implements LukuvinkkiDao {

    ArrayList<Lukuvinkki> lukuvinkit;

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
    public void poistaLinkki(Linkki lukuvinkki) throws Exception {
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

    
}
