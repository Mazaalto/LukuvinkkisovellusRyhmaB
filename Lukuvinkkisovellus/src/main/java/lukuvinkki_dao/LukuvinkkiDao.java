package lukuvinkki_dao;

import java.util.List;
import lukuvinkkisovellus.Kirja;
import lukuvinkkisovellus.Linkki;
import lukuvinkkisovellus.Lukuvinkki;

public interface LukuvinkkiDao {

    List<Lukuvinkki> listaaKaikki();
    
    List<Lukuvinkki> listaaKirjat();
    List<Lukuvinkki> listaaKaikkiLinkit();

    void lisaaLinkki(Linkki lukuvinkki) throws Exception;
    
    void lisaaKirja(Kirja lukuvinkki) throws Exception;
    
    int LukuvinkkienMaara();
    
    int KirjojenLukumaara();
    
    void poistaLinkki(Lukuvinkki lukuvinkki) throws Exception;
    
    void tyhjennaTietokanta() throws Exception;

    public void poistaKirja(Kirja kirja) throws Exception;
    
    void merkkaaLuetuksi(Lukuvinkki lukuvinkki) throws Exception;

}
