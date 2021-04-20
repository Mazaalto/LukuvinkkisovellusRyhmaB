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
            dao.lisaaLinkki(new Linkki("otsake", "urli.com"));
            dao.lisaaLinkki(new Linkki("otsake2", "urli2.com"));
            
            dao.lisaaKirja(new Kirja("k", "kir1", 1996, "tammi", "www.kirja.net"));
            dao.lisaaKirja(new Kirja("k", "kir2", 1993, "tammi", "www.kirja.net"));
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
    public void kirjojenMaaraOnOikea() {
        assertEquals(2, dao.KirjojenLukumaara());
    }

    @Test
    public void lukuvinkitLuetaanOikeinTiedostosta() {
        List<Lukuvinkki> lukuvinkit = dao.listaaKaikki();
        assertEquals(2, lukuvinkit.size());

        Lukuvinkki lukuvinkki1 = lukuvinkit.get(0);
        assertEquals("Vinkin otsikko: otsake, vinkin linkki: urli.com", lukuvinkki1.toString());


        Lukuvinkki lukuvinkki2 = lukuvinkit.get(1);
        assertEquals("Vinkin otsikko: otsake2, vinkin linkki: urli2.com", lukuvinkki2.toString());
    }
    
    @Test
    public void kirjatLuetaanOikeinTiedostosta() {
        List<Lukuvinkki> lukuvinkit = dao.listaaKirjat();

        Lukuvinkki lukuvinkki1 = lukuvinkit.get(0);
        assertEquals("Vinkin otsikko: k, kirjailija: kir1, julkaisuvuosi: 1996, julkaisija: tammi, linkki: www.kirja.net", lukuvinkki1.toString());


        Lukuvinkki lukuvinkki2 = lukuvinkit.get(1);
        assertEquals("Vinkin otsikko: k, kirjailija: kir2, julkaisuvuosi: 1993, julkaisija: tammi, linkki: www.kirja.net", lukuvinkki2.toString());
    }

    @Test
    public void lisattyLukuvinkkiTallentuuTiedostoon() throws Exception {
        Linkki lisattava = new Linkki("uusi vinkki", "www.uusi.fi");
        dao.lisaaLinkki(lisattava);
        List<Lukuvinkki> lukuvinkit = dao.listaaKaikki();
        assertEquals(3, dao.LukuvinkkienMaara());
        Lukuvinkki lukuvinkki = lukuvinkit.get(2);
        assertEquals("Vinkin otsikko: uusi vinkki, vinkin linkki: www.uusi.fi", lukuvinkki.toString());
    }

    @Test
    public void lukuvinkinPoistoOnnistuu() throws Exception {
        Linkki lisattava = new Linkki("uusi vinkki", "www.uusi.fi");
        Linkki lisattava2 = new Linkki("uusi vinkki2", "www.uusi2.fi");
        dao.lisaaLinkki(lisattava);
        dao.lisaaLinkki(lisattava2);
        dao.poistaLinkki(lisattava);        
        assertEquals(3, dao.LukuvinkkienMaara());        
    }
    
    @Test
    public void tietokannanTyhjentaminenOnnistuu() throws Exception {
        dao.tyhjennaTietokanta();
        assertEquals(0, dao.listaaKaikki().size());
    }

    @After
    public void tearDown() {
        lukuvinkkiFile.delete();
    }
}
