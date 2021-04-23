Feature: Käyttäjä voi poistaa lukuvinkin otsikon perusteella

Scenario: yhden vinkin poisto onnistuu
    Given lukuvinkkisovellus is initialized
    And lukuvinkki lisätään otsikolla "otsikko1" ja urlilla "testiurl.com"
    When lukuvinkki otsikolla "otsikko1" poistetaan
    Then lukuvinkkiä ei löydy otsikolla "otsikko1"


