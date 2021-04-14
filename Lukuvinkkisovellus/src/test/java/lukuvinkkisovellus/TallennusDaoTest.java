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
        Connection db = DriverManager.getConnection("jdbc:sqlite:lukuvinkkisovellusTest.db");
        Statement s = db.createStatement();
        
        lukuvinkkiFile = testFolder.newFile("lukuvinkkisovellus.db");
        dao = new TallennusDao(lukuvinkkiFile.getAbsolutePath());
     //   dao.lisaa(new Lukuvinkki("Testailua","www.testi.net"));
   
        s.execute("CREATE TABLE IF NOT EXISTS Linkit (id INTEGER PRIMARY KEY, otsikko TEXT, url TEXT)");
        s.execute("INSERT INTO Linkit(otsikko,url) VALUES('Testailua', 'www.testi.net')");
        s.execute("INSERT INTO Linkit(otsikko,url) VALUES('Testailua2', 'www.testinen.net')");
//        try ( FileWriter file = new FileWriter(lukuvinkkiFile.getAbsolutePath())) {
//            file.write("Testailua;www.testi.net\n");
//            file.write("Testailua2;www.testinen.net\n");
//        }

        

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
        assertEquals("Testailua", lukuvinkki1.getOtsikko());
        assertEquals("www.testi.net", lukuvinkki1.getUrl());

        Lukuvinkki lukuvinkki2 = lukuvinkit.get(1);
        assertEquals("Testailua2", lukuvinkki2.getOtsikko());
        assertEquals("www.testinen.net", lukuvinkki2.getUrl());

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
