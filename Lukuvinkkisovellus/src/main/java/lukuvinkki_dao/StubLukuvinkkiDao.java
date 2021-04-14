/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lukuvinkki_dao;

import java.util.ArrayList;
import java.util.List;
import lukuvinkkisovellus.Lukuvinkki;

/**
 *
 * @author anttihalmetoja
 */
public class StubLukuvinkkiDao implements LukuvinkkiDao {

    ArrayList<Lukuvinkki> lukuvinkit;

    public StubLukuvinkkiDao() {
        this.lukuvinkit = new ArrayList<Lukuvinkki>();        
    }
    
    
    
    @Override
    public ArrayList<Lukuvinkki> listaaKaikki() {
        return lukuvinkit;
    }

    @Override
    public void lisaa(Lukuvinkki lukuvinkki) throws Exception {
        lukuvinkit.add(lukuvinkki); 
    }

    @Override
    public int LukuvinkkienMaara() {
        return lukuvinkit.size();
    }

 
    @Override
    public void poista(Lukuvinkki lukuvinkki) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
