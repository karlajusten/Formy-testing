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
        And the Zip code field shows value "94306"

    Scenario: Test scroll page
        Given User access "scroll" page from Formy web site
        When User informs it's name "Karla" and date "26/05/2020"
        Then Nothing happens

    Scenario: Switch to active window
        Given User access "switch-window" page from Formy web site
        When User clicks in open new tab button
        Then New tab is created with the home page of Formy web site that has the message "Welcome to Formy"

    Scenario: Open Alert
        Given User access "switch-window" page from Formy web site
        When User clicks in open alert button
        Then Site shows a alert with message "This is a test alert!"
        And User clicks on OK button

    Scenario: Open Modal
        Given User access "modal" page from Formy web site
        When User clicks in open modal button
        Then Site shows a modal with title "Modal title"
        And User clicks on close button

    Scenario: Drag and Drop Image
        Given User access "dragdrop" page from Formy web site
        When User drag and drop a image to the box
        Then Site shows the message "Dropped!" in the box

    Scenario: Radio Button
        Given User access "radiobutton" page from Formy web site
        When User clicks on the three radio buttons
        Then Nothing happens

    Scenario: Test Date Picker
        Given User access "datepicker" page from Formy web site
        When User chooses the date "03/03/2020" and press enter
        Then Nothing happens

    Scenario: Test Dropdown
        Given User access "dropdown" page from Formy web site
        When User clicks on dropdown button and chooses autocomplete option
        Then Nothing happens

    Scenario: Test File Upload
        Given User access "fileupload" page from Formy web site
        When User chooses to upload the "file-to-upload.png" file
        Then Nothing happens