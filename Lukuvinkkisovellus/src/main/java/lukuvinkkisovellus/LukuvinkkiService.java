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

    public List listaaKaikkiLinkit() {

        return this.lukuvinkkiDao.listaaKaikkiLinkit();

    }
    

    public List listaaKirjat() {
        return this.lukuvinkkiDao.listaaKirjat();
    }

    public void lisaaLinkki(Linkki lukuvinkki) throws Exception {
        if (lukuvinkki.getOtsikko().trim().isEmpty() || lukuvinkki.getUrl().trim().isEmpty()) {
            System.out.println("Lukuvinkillä on oltava otsikko ja url.");
            return;
        }

        this.lukuvinkkiDao.lisaaLinkki(lukuvinkki);
    }

    public void lisaaKirja(Kirja lukuvinkki) throws Exception {
        if (lukuvinkki.getOtsikko().trim().isEmpty() || lukuvinkki.getUrl().trim().isEmpty()) {
            System.out.println("Lukuvinkillä on oltava otsikko ja url");
            return;
        }

        this.lukuvinkkiDao.lisaaKirja(lukuvinkki);
    }

    public List listaaOtsikonPerusteella(String otsikko) {
        List<Lukuvinkki> lukuvinkit = listaaKaikki();
        for (Iterator<Lukuvinkki> iterator = lukuvinkit.iterator(); iterator.hasNext();) {
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

    public void poistaLukuvinkki(Lukuvinkki lukuvinkki) throws Exception {
        this.lukuvinkkiDao.poistaLinkki(lukuvinkki);
    }

    public void poistaKirja(Kirja kirja) throws Exception {
        this.lukuvinkkiDao.poistaKirja(kirja);
    }

    public void tyhjennaTietokanta() throws Exception {
        this.lukuvinkkiDao.tyhjennaTietokanta();
    }

    public void merkkaaLuetuksi(Lukuvinkki lukuvinkki) throws Exception {
        lukuvinkki.merkkaaLuetuksi();
        this.lukuvinkkiDao.merkkaaLuetuksi(lukuvinkki);
    }
    
    public void tuoTiedostosta(String tiedosto) throws Exception {
        this.lukuvinkkiDao.tuoTiedostosta(tiedosto);
    }
    
    public void vieTiedostoon() throws Exception {
        this.lukuvinkkiDao.vieTiedostoon();
    }
}
