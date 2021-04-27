Feature: Käyttäjänä haluan lisätä lukuvinkin otsikolla ja urlilla

  Scenario: yhden vinkin lisäys onnistuu
    Given lukuvinkkisovellus is initialized
    When lukuvinkki otsikolla "otsikko1" ja urlilla "testiurl.com" lisätään
    Then arvon tulisi olla 1

  Scenario: monen vinkin lisäys onnistuu
    Given lukuvinkkisovellus is initialized
    When lukuvinkki otsikolla "otsikko1" ja urlilla "testiurl.com" lisätään
    And lukuvinkki otsikolla "otsikko2" ja urlilla "testiurl2.com" lisätään
    Then arvon tulisi olla 2

  Scenario: listaus yhdellä vinkillä onnistuu
    Given lukuvinkkisovellus is initialized
    When lukuvinkki otsikolla "otsikko1" ja urlilla "testiurl.com" lisätään
    Then Järjestelmä palauttaa merkkijonon "[Vinkin otsikko: otsikko1, vinkin linkki: testiurl.com]"

  Scenario: listaus monella vinkillä onnistuu
    Given lukuvinkkisovellus is initialized
    When lukuvinkki otsikolla "otsikko1" ja urlilla "testiurl.com" lisätään
    And lukuvinkki otsikolla "otsikko2" ja urlilla "testiurl2.com" lisätään
    And lukuvinkki otsikolla "otsikko3" ja urlilla "testiurl3.com" lisätään
    Then Järjestelmä palauttaa merkkijonon "Vinkin otsikko: otsikko1, vinkin linkki: testiurl.com"
    And Järjestelmä palauttaa merkkijonon "Vinkin otsikko: otsikko3, vinkin linkki: testiurl3.com"
    

  

  
