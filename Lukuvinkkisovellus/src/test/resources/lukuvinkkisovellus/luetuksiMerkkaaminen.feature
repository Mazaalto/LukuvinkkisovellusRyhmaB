Feature: Käyttäjänä voin merkata lukuvinkin luetuksi

 Scenario: yhden vinkin merkkaaminen luetuksi onnistuu
    Given lukuvinkkisovellus is initialized
    When lukuvinkki otsikolla "otsikko1" ja urlilla "testiurl.com" merkitään luetuksi
    Then lukuvinkki otsikolla "otsikko1" ja urlilla "testiurl" on merkitty luetuksi