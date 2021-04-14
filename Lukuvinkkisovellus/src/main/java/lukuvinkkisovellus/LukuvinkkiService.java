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
}
