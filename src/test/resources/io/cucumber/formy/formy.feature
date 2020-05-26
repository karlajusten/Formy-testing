Feature: Formy web site

    Scenario: Test the Keypress page
        Given User access "keypress" page from Formy web site
        When User informs it's name "Karla Aparecida Justen" and click on button
        Then Nothing happens

    Scenario: Test autocomplete page
        Given User access "autocomplete" page from Formy web site
        When User informs the address "1555 Park Blvd, Palo Alto, CA"
        Then Site shows autocomplete option
        And user click in autocomplete option

    Scenario: Test scroll page
        Given User access "scroll" page from Formy web site
        When User informs it's name "Karla" and date "26/05/2020"
        Then Nothing happens