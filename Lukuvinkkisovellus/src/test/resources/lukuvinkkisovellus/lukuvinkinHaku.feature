Feature: Käyttäjänä haluan hakea lukuvinkkejä otsikon perusteella

Scenario: lukuvinkin haku otsikolla
    Given lukuvinkkisovellus is initialized
    When lukuvinkki otsikolla "yle" ja urlilla "testiurl.com" lisätään
    And lukuvinkki otsikolla "hs" ja urlilla "testiurl2.com" lisätään
    And lukuvinkki otsikolla "iltasanomat" ja urlilla "testiurl3.com" lisätään
    And haetaan lukuvinkkiä otsikolla "yle" ja urlilla "testiurl.com"
    Then lukuvinkki otsikolla "yle" ja urlilla "testiurl.com" palauttaa tekstin "Vinkin otsikko: yle, vinkin linkki: testiurl.com"
