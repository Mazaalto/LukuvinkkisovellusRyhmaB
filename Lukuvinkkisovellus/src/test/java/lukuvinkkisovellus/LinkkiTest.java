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
    private LukuvinkkiService lukuvinkkiService;
    private LukuvinkkiDao lukuvinkkiDao;
       
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
    
    @Test
    public void luetunLinkintoStringOikeanlainen() throws Exception {
        Linkki linkki = new Linkki("otsikko2", "testiurl.com");
        lukuvinkkiService.lisaaLinkki(linkki);
        lukuvinkkiService.merkkaaLuetuksi(linkki);
        String vinkki = lukuvinkkiDao.listaaKaikki().get(0).toString();
        assertEquals("Vinkin otsikko: otsikko2, linkki: testiurl.com, luettu " + linkki.getMilloinLuettu(), vinkki);
    }
    
    @Test
    public void kunLinkinMerkitseeLuetuksiNiinLuettuOnTrue() throws Exception {
        Linkki linkki = new Linkki("Ohjelmointi", "www.ohjelmointi.con");
        lukuvinkkiService.lisaaLinkki(linkki);
        lukuvinkkiService.merkkaaLuetuksi(linkki);
        assertTrue(linkki.onkoLuettu());
    }
}
