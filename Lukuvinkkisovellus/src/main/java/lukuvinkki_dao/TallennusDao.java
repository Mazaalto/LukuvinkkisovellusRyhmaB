package lukuvinkki_dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lukuvinkkisovellus.Lukuvinkki;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TallennusDao implements LukuvinkkiDao {

    private List<Lukuvinkki> lukuvinkit;
    private String lukuvinkkiTiedosto;
    private String tietokantaosoite;

    public TallennusDao(String tietokantaosoite) throws Exception {
        this.tietokantaosoite = tietokantaosoite;
        //Tietokannan luonti
        try {
            Connection db = DriverManager.getConnection(tietokantaosoite);
            Statement s = db.createStatement();
            System.out.println("Yhdistäminen tietokantaan onnistui!");

            s.execute("CREATE TABLE IF NOT EXISTS Linkit (id INTEGER PRIMARY KEY, otsikko TEXT, url TEXT)");
            s.execute("CREATE TABLE IF NOT EXISTS Kirjat (id INTEGER PRIMARY KEY, otsikko TEXT, kirjailija TEXT, julkaisuvuosi INTEGER, julkaisija TEXT, url TEXT)");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        lukuvinkit = new ArrayList<>();
        //       lukuvinkkiTiedosto = tiedosto;
        /*
            Tässä tulostusesimerkki
            ResultSet r = s.executeQuery("SELECT * FROM Linkit");
            while (r.next()) {
            System.out.println(r.getInt("id")+" "+r.getString("otsikko")+" "+r.getInt("url"));
            }
         */

//        try {
//            Scanner lukija = new Scanner(new File(lukuvinkkiTiedosto));
//            while (lukija.hasNextLine()) {
//                String[] osat = lukija.nextLine().split(";");
//                lukuvinkit.add(new Lukuvinkki(osat[0], osat[1]));
//            }
//        } catch (Exception e) {
//            FileWriter kirjoittaja = new FileWriter(new File(lukuvinkkiTiedosto));
//            kirjoittaja.close();
//        }
    }

// Tänne totetutetaan lukuvinkkien tallennus.
    @Override
    public List<Lukuvinkki> listaaKaikki() {
        try {
            Connection db = DriverManager.getConnection(tietokantaosoite);
            Statement s = db.createStatement();
            ResultSet vinkit = s.executeQuery("SELECT * FROM Linkit");
            while (vinkit.next()) {
                lukuvinkit.add(new Lukuvinkki(vinkit.getString("otsikko"), vinkit.getString("url")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lukuvinkit;
    }

    @Override
    public void lisaa(Lukuvinkki lukuvinkki) throws Exception {
        Connection db = DriverManager.getConnection(tietokantaosoite);
        PreparedStatement stmt = db.prepareStatement("INSERT INTO Linkit(otsikko,url) VALUES(?,?)");
        String otsikko = lukuvinkki.getOtsikko();
        String url = lukuvinkki.getUrl();
        stmt.setString(1, otsikko);
        stmt.setString(2, url);
        stmt.execute();
        db.close();
        System.out.println("Lisääminen onnistui");
        lukuvinkit.add(lukuvinkki);

    }

    @Override
    public int LukuvinkkienMaara() {
        int vinkkienMaara = -5;
        try {
            Connection db = DriverManager.getConnection(tietokantaosoite);
            Statement s = db.createStatement();
            ResultSet vinkit = s.executeQuery("SELECT * FROM Linkit");
            ArrayList<String> kaikki = new ArrayList<>();
            while(vinkit.next()) {
                kaikki.add(vinkit.getString("otsikko"));
            }
            vinkkienMaara = kaikki.size();

        } catch (SQLException ex) {
            Logger.getLogger(TallennusDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vinkkienMaara;
    }

    @Override
    public void poista(Lukuvinkki lukuvinkki) throws Exception {
        Connection db = DriverManager.getConnection(tietokantaosoite);
        PreparedStatement stmt = db.prepareStatement("DELETE FROM Linkit WHERE otsikko = ?");
        
        stmt.setString(1, lukuvinkki.getOtsikko());
        stmt.execute();
        db.close();
        System.out.println("Lukuvinkki poistettu");
        lukuvinkit.remove(lukuvinkki);
    }

}
