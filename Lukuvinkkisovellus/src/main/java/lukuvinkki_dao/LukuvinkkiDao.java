package lukuvinkki_dao;

import java.util.List;
import lukuvinkkisovellus.Kirja;
import lukuvinkkisovellus.Linkki;
import lukuvinkkisovellus.Lukuvinkki;

public interface LukuvinkkiDao {

    List<Lukuvinkki> listaaKaikki();
    
    List<Lukuvinkki> listaaKirjat();

    void lisaaLinkki(Linkki lukuvinkki) throws Exception;
    
    void lisaaKirja(Kirja lukuvinkki) throws Exception;
    
    int LukuvinkkienMaara();
    
    int KirjojenLukumaara();
    
    void poistaLinkki(Lukuvinkki lukuvinkki) throws Exception;
    
    void tyhjennaTietokanta() throws Exception;
    
    void merkkaaLuetuksi(Lukuvinkki lukuvinkki) throws Exception;

}
