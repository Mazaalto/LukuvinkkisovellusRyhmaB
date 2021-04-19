package lukuvinkkisovellus;
import java.util.ArrayList;
import java.util.List;
import lukuvinkki_dao.LukuvinkkiDao;
import lukuvinkki_dao.StubLukuvinkkiDao;
import lukuvinkki_dao.TallennusDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LinkkiTest {
    LukuvinkkiService lukuvinkkiService;
    LukuvinkkiDao lukuvinkkiDao;
       
    @Before
    public void setUp() {
        lukuvinkkiDao = new StubLukuvinkkiDao();
        lukuvinkkiService = new LukuvinkkiService(lukuvinkkiDao);
        
    }
    public void LukuvinkinLisaysOnnistuu() throws Exception {
        lukuvinkkiService.lisaaLinkki(new Linkki("otsikko1", "testiurl.com"));
        String vinkki = lukuvinkkiDao.listaaKaikki().get(0).toString();          
        assertEquals("Vinkin otsikko: otsikko1, vinkin linkki: testiurl.com", vinkki);
    }
    
    public void LukuvinkkiKirjanLisaysOnnistuu() throws Exception {
        lukuvinkkiService.lisaaKirja(new Kirja("kirjanen", "incognito", 1997, "tammi", "www.kirja.net"));
        String vinkki = lukuvinkkiDao.listaaKirjat().get(0).toString();          
        assertEquals("Vinkin otsikko: kirjanen, kirjailija: incognito, julkaisuvuosi: 1997, julkaisija: tammi, linkki: www.kirja.net", vinkki);
    }
}
