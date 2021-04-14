package lukuvinkki_dao;

import java.util.List;
import lukuvinkkisovellus.Lukuvinkki;

public interface LukuvinkkiDao {

    List<Lukuvinkki> listaaKaikki();

    void lisaa(Lukuvinkki lukuvinkki) throws Exception;
    
    int LukuvinkkienMaara();
    
    void poista(Lukuvinkki lukuvinkki) throws Exception;
}
