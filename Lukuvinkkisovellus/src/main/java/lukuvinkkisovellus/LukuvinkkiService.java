package lukuvinkkisovellus;

import java.util.Iterator;
import java.util.List;
import lukuvinkki_dao.LukuvinkkiDao;
import lukuvinkkisovellus.Lukuvinkki;

public class LukuvinkkiService {

    private LukuvinkkiDao lukuvinkkiDao;

    public LukuvinkkiService(LukuvinkkiDao lukuvinkkiDao) {
        this.lukuvinkkiDao = lukuvinkkiDao;
    }

    public List listaaKaikki() {

        return this.lukuvinkkiDao.listaaKaikki();

    }

    public void lisaaLukuvinkki(Lukuvinkki lukuvinkki) throws Exception {
        if (lukuvinkki.otsikko.length() < 3 || lukuvinkki.url.length() < 5) {
            System.out.println("Lukuvinkin otsikon on oltava vähintään 3 ja urlin 5 merkkiä pitkä.");
            return;
        }
        
        this.lukuvinkkiDao.lisaa(lukuvinkki);
    }

    public List listaaOtsikonPerusteella(String otsikko) {
        List<Lukuvinkki> lukuvinkit = listaaKaikki();
        for (Iterator<Lukuvinkki> iterator = lukuvinkit.iterator(); iterator.hasNext(); ) {
            String value = iterator.next().getOtsikko();

            if (!value.contains(otsikko)) {
                iterator.remove();
            }
        }
  
        return lukuvinkit;
    }
    public int getLukuvinkkienMaara() {
        return this.lukuvinkkiDao.LukuvinkkienMaara();
    }
    
    public void poistaLukuvinkki(Lukuvinkki lukuvinkki) throws Exception {
        this.lukuvinkkiDao.poista(lukuvinkki);
    }
    public void tyhjennaTietokanta() throws Exception {
        this.lukuvinkkiDao.tyhjennaTietokanta();
    }
}
