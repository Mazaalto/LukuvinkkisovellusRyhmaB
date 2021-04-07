
package lukuvinkkisovellus;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import lukuvinkki_dao.LukuvinkkiDao;
import lukuvinkki_dao.StubLukuvinkkiDao;
import lukuvinkki_dao.TallennusDao;
import static org.junit.Assert.*;

public class Stepdefs {
    
    
    
    //Tänne tulee Cucumber testit, alla esimerkki viikon 3 HelloCucumber laskarista
  
    LukuvinkkiService lukuvinkkiService;
    LukuvinkkiDao lukuvinkkiDao;
    
    @Given("lukuvinkkisovellus is initialized")
    public void lukuvinkkisovellusIsInitialized() {
        lukuvinkkiDao = new StubLukuvinkkiDao();
        lukuvinkkiService = new LukuvinkkiService(lukuvinkkiDao);
    }

    @When("lukuvinkki on lisatty")
    public void lukuvinkkiOnLisatty() throws Exception {
        lukuvinkkiService.lisaaLukuvinkki(new Lukuvinkki("otsikko1", "testiurl.com")); 
    }

    @Then("arvon tulisi olla {int}")
    public void arvonTulisiOlla(Integer val) {
        assertEquals(val.intValue(), lukuvinkkiDao.LukuvinkkienMaara());
    }

     


}