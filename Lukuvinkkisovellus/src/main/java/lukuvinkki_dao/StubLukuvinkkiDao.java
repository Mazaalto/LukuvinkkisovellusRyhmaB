
package lukuvinkki_dao;

import java.util.ArrayList;
import java.util.List;
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
    public void lisaa(Lukuvinkki lukuvinkki) throws Exception {
        lukuvinkit.add(lukuvinkki); 
    }

    @Override
    public int LukuvinkkienMaara() {
        return lukuvinkit.size();
    }

 
    @Override
    public void poista(Lukuvinkki lukuvinkki) throws Exception {
        for (Lukuvinkki l : lukuvinkit) {
            if (l.equals(lukuvinkki)) {
                lukuvinkit.remove(l);
            }
        }
    }

    @Override
    public void tyhjennaTietokanta() throws Exception {
        lukuvinkit.removeAll(lukuvinkit);
    }
    
}
