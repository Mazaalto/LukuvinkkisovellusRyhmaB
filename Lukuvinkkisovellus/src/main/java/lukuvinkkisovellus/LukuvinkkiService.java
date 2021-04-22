package lukuvinkkisovellus;

import java.util.Iterator;
import java.util.List;
import lukuvinkki_dao.LukuvinkkiDao;
import lukuvinkkisovellus.Linkki;

public class LukuvinkkiService {

    private LukuvinkkiDao lukuvinkkiDao;

    public LukuvinkkiService(LukuvinkkiDao lukuvinkkiDao) {
        this.lukuvinkkiDao = lukuvinkkiDao;
    }

    public List listaaKaikki() {

        return this.lukuvinkkiDao.listaaKaikki();

    }
    
    public List listaaKirjat() {
        return this.lukuvinkkiDao.listaaKirjat();
    }

    public void lisaaLinkki(Linkki lukuvinkki) throws Exception {
        if (lukuvinkki.otsikko.trim().isEmpty() || lukuvinkki.url.trim().isEmpty()) {
            System.out.println("Lukuvinkillä on oltava otsikko ja url.");
            return;
        }
        
        this.lukuvinkkiDao.lisaaLinkki(lukuvinkki);
    }
    
    public void lisaaKirja(Kirja lukuvinkki) throws Exception {
        if (lukuvinkki.otsikko.trim().isEmpty() || lukuvinkki.url.trim().isEmpty()) {
            System.out.println("Lukuvinkillä on oltava otsikko ja url");
            return;
        }
        
        this.lukuvinkkiDao.lisaaKirja(lukuvinkki);
    }

    public List listaaOtsikonPerusteella(String otsikko) {
        List<Linkki> lukuvinkit = listaaKaikki();
        for (Iterator<Linkki> iterator = lukuvinkit.iterator(); iterator.hasNext(); ) {
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
    
    public int getKirjojenMaara() {
        return this.lukuvinkkiDao.KirjojenLukumaara();
    }
    
    public void poistaLukuvinkki(Linkki lukuvinkki) throws Exception {
        this.lukuvinkkiDao.poistaLinkki(lukuvinkki);
    }
    
    public void tyhjennaTietokanta() throws Exception {
        this.lukuvinkkiDao.tyhjennaTietokanta();
    }
    
    public void tuoTiedostosta(String tiedosto) throws Exception {
        this.lukuvinkkiDao.tuoTiedostosta(tiedosto);
    }
}
