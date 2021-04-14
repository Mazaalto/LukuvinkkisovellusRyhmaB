package lukuvinkkisovellus;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import lukuvinkki_dao.LukuvinkkiDao;
import lukuvinkki_dao.TallennusDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import static org.junit.Assert.*;

public class TallennusDaoTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    File lukuvinkkiFile;
    LukuvinkkiDao dao;

    @Before
    public void setUp() throws Exception {
        lukuvinkkiFile = testFolder.newFile("lukuvinkkisovellusTest.db");        
        dao = new TallennusDao("jdbc:sqlite:"+lukuvinkkiFile.getAbsolutePath());
        try {
            dao.lisaa(new Lukuvinkki("otsake", "urli.com"));
            dao.lisaa(new Lukuvinkki("otsake2", "urli2.com"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
    }

    @Test
    public void lukuvinkkienMaaraOnOikea() {        
        assertEquals(2, dao.LukuvinkkienMaara());
    }

    @Test
    public void lukuvinkitLuetaanOikeinTiedostosta() {
        List<Lukuvinkki> lukuvinkit = dao.listaaKaikki();
        assertEquals(2, lukuvinkit.size());

        Lukuvinkki lukuvinkki1 = lukuvinkit.get(0);
        assertEquals("otsake", lukuvinkki1.getOtsikko());
        assertEquals("urli.com", lukuvinkki1.getUrl());

        Lukuvinkki lukuvinkki2 = lukuvinkit.get(1);
        assertEquals("otsake2", lukuvinkki2.getOtsikko());
        assertEquals("urli2.com", lukuvinkki2.getUrl());

    }

    @Test
    public void lisattyLukuvinkkiTallentuuTiedostoon() throws Exception {
        Lukuvinkki lisattava = new Lukuvinkki("uusi vinkki", "www.uusi.fi");
        dao.lisaa(lisattava);
        List<Lukuvinkki> lukuvinkit = dao.listaaKaikki();
        assertEquals(3, dao.LukuvinkkienMaara());
        Lukuvinkki lukuvinkki = lukuvinkit.get(2);
        assertEquals("uusi vinkki", lukuvinkki.getOtsikko());
        assertEquals("www.uusi.fi", lukuvinkki.getUrl());
    }

    @After
    public void tearDown() {
        lukuvinkkiFile.delete();
    }

}
