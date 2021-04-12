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

public class lukuvinkkiTest {
    LukuvinkkiService lukuvinkkiService;
    LukuvinkkiDao lukuvinkkiDao;
       
    @Before
    public void setUp() {
        lukuvinkkiDao = new StubLukuvinkkiDao();
        lukuvinkkiService = new LukuvinkkiService(lukuvinkkiDao);
        
    }
    public void LukuvinkinLisaysOnnistuu() throws Exception {
        lukuvinkkiService.lisaaLukuvinkki(new Lukuvinkki("otsikko1", "testiurl.com"));
        String vinkki = lukuvinkkiDao.listaaKaikki().get(0).toString();          
        assertEquals("Vinkin otsikko: otsikko1, vinkin linkki: testiurl.com", vinkki);
    }
}
