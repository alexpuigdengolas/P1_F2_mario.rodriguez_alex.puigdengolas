package Business;

import Persistance.CsvCotroller;
import Persistance.jsonController;
import Presentation.ViewController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * This class will allow contain all the Logic of the code, and we will use it as a bridge to connect all the
 * parts of the code.
 */
public class BusinessController {
    ViewController viewController = new ViewController(this);
    LinkedList<Test> tests = new LinkedList<Test>();
    List<Edition> editions = new LinkedList<Edition>();
    List<Player> players = new LinkedList<>();
    List<estudiMaster> masters = new LinkedList<>();

    /**
     * This is the constructor of the class, but it will work as the starter of the code
     * First executing the kind of file that we will get the information from, and then
     * choosing if we want to execute the code as a Composer or a Conductor
      */
    public BusinessController(){
        boolean csvOn = viewController.dataSelection();

        CsvCotroller csv = new CsvCotroller(); //creamos objeto
        jsonController json = new jsonController();

        if (csvOn) {
            editions = csv.readCSV("CSV/Edicions.csv");
        }else{
            editions = json.readJSON("JSON/Edicions.json");
            tests = json.readTestJSON("JSON/EdicionsPubliTest.json", "JSON/EdicionsDefTest.json", "JSON/EdicionsMasterTest.json", "JSON/EdicionsReqTest.json");

            players = json.readPlayersJSON("JSON/Enginyer.json", "JSON/Master.json", "JSON/Doctor.json");
            for(int i = 0; i < editions.size(); i++){
                if(editions.get(i).getYear() == Calendar.getInstance().get(Calendar.YEAR)){
                    editions.get(i).setTests(this.tests);
                    editions.get(i).getPlayers().clear();
                    editions.get(i).setPlayers((LinkedList<Player>) this.players);
                }
            }
        }

        for (Edition edition: editions) {
            edition.setBusinessController(this);
        }
        
        if(!editions.isEmpty()) {
            setTests(editions);
        }
        boolean role = viewController.startView();

        //Compositor
        if(role){
            compositorController();

        //Conductor
        }else{
            conductorComposer();
        }

        if(csvOn){
            csv.writeCSV(editions, "CSV/Edicions.csv");
        }else{
            json.writeTestsJSON(editions, "JSON/EdicionsPubliTest.json", "JSON/EdicionsDefTest.json", "JSON/EdicionsMasterTest.json", "JSON/EdicionsReqTest.json");
            json.writePlayersJSON(editions, "JSON/Enginyer.json", "JSON/Master.json", "JSON/Doctor.json");
            json.writeEditionsJSON(editions, "JSON/Edicions.json");

        }
    }

    /**
     * This method will allow us to set the tests that go into the edition selected
     * @param editions this is the list of editions that we have in the code
     */
    private void setTests(List<Edition> editions) {
        boolean exists = false;
        for(int i = 0; i < editions.size(); i++){
            for(int j = 0; j < editions.get(i).getNumTest(); j++){
                exists = false;
                for(int w = 0; w < tests.size()-1; w++) {
                    if(tests.get(w).getName().equals(editions.get(i).getTests().get(j).getName())) {
                        exists = true;
                        break;
                    }
                }
                if(!exists){
                    tests.add(editions.get(i).getTests().get(j));
                }
            }
        }
    }

    /**
     * This method will allow the user to get executed in the Composer mode,
     * that will allow us to create editions and tests
     */
    public void compositorController(){
        int optionManage = 0, trialChoice = 0, trialShowChoice = 0;
        String optionManageTrials, optionManageEditions;
        boolean exit = false, exitTrialManager = false, exitEditionManager = false;
        do {

            //Vista del compositor
            optionManage = viewController.mainCompositorView();
            if (optionManage == 1) {
                do {
                    //Vista de test management
                    optionManageTrials = viewController.manageTrialsView();
                    if (optionManageTrials.equals("a")) {
                        trialChoice = viewController.trialChoiceView();
                        if(trialChoice == 1){
                            //Create test View
                            tests.add(viewController.createPaperPublication());
                        }else if(trialChoice == 2){
                            tests.add(viewController.createDoctoralDefense());
                        }else if(trialChoice == 3){
                            tests.add(viewController.createMasterEstudy());
                        }else if(trialChoice == 4){
                            tests.add(viewController.createBudgetRequest());
                        }
                    } else if (optionManageTrials.equals("b")) {
                        //Show Test View
                        viewController.trialChoiceShowView((LinkedList<Test>) tests);

                    } else if (optionManageTrials.equals("c")) {
                        //Delete Test View
                        viewController.trialChoiceDeleteView((LinkedList<Test>) tests);

                    } else {
                        exitTrialManager = true;
                    }
                }while (!exitTrialManager);

            } else if (optionManage == 2) {
                do {
                    optionManageEditions = viewController.manageEditionView();
                    if (optionManageEditions.equals("a")) {
                        viewController.createEditionView(tests, editions);
                    } else if (optionManageEditions.equals("b")) {
                        viewController.showEditionView(editions);
                    } else if (optionManageEditions.equals("c")) {
                        viewController.duplicateEdition(editions);
                    } else if (optionManageEditions.equals("d")) {
                        viewController.deleteEditionView(editions);
                    } else {
                        exitEditionManager = true;
                    }
                }while (!exitEditionManager);
            } else {
                exit = true;
                viewController.shutDown();
            }
        }while (!exit);
    }

    /**
     * This method will return the ViewController that the code uses
     * @return the view controller that we want to use
     */
    public ViewController getViewController() {
        return viewController;
    }

    /**
     * This method will allow the user to get executed in the Conductor mode,
     * that will allow us to execute the current year edition
     */
    public void conductorComposer(){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        Edition edition = lookForCurrentEdition(editions, currentYear);
        if(edition == null) {
            viewController.noEditionView(currentYear);
        }else {
            viewController.mainConductorView(edition, currentYear);
            executeEdition(edition);

        }
    }

    /**
     * This method will execute the edition that is associated to the current year and the test's that it contains
     * @param edition the current year edition
     */
    private void executeEdition(Edition edition) {
        //Ver tema de probabilidades
        boolean nextTest = true;
        Player winner = new Player();

        int i;
        for (i = 0; i < edition.getNumTest() && nextTest; i++) {
            if(i > 0){
                nextTest = viewController.nextTest();
                if(!nextTest){
                    break;
                }
            }

            edition.getTests().get(i).execute(edition.getTests().get(i), edition);
            if(edition.getPlayers().size() == 0){
                viewController.allPlayersDisc();
            }
        }
        if (i == edition.getNumTest()){
            winner = getWinner(edition);
            viewController.editionEnded(edition, winner);
        }
    }

    /**
     * This class will return the winner of the currently executed edition
     * @param edition the current year edition
     * @return the player that wined the edition
     */
    private Player getWinner(Edition edition) {
        int winner = 0;
        int maxpi = 0;
        for (int i = 0; edition.getPlayers().size() > i; i++){
            if(maxpi < edition.getPlayers().get(i).getInvestigationPoints()){
                winner = i;
            }
        }
        return edition.getPlayers().get(winner);
    }

    /**
     * This method will get all the editions and it will return the editions associated to this year
     * @param editions the list of all the editions
     * @param currentYear the current year (in a integer value)
     * @return the object Edition associated to the current year
     */
    private Edition lookForCurrentEdition(List<Edition> editions, int currentYear) {
        Edition currentEdition = null;

        for (Edition edition : editions) {
            if (edition.getYear() == currentYear) {
                currentEdition = edition;
            }
        }

        return currentEdition;
    }

    /**
     * Here we check if the edition's year is valid to create the new edition
     * @param year the current year (in a integer value)
     * @return a boolean that indicates if the year is valid
     */
    public boolean checkEditionsYear(int year) {
        boolean ok = true;
        for(int i = 0; editions.size() > i; i++){
            if(editions.get(i).getYear() == year){
                ok = false;
                viewController.yearErrorChoseAgain();
                break;
            }
        }
        if (year < 2022) {
            ok = false;
            viewController.reallyBigYearError();
        }
        return ok;
    }

    /**
     * This method will show if there we have a valid input
     * @param option is the input that we want to check
     * @return a boolean that shows if the input is valid
     */
    public boolean isNumber(String option){
        try {
            Integer.parseInt(option);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    /**
     * This method will see if there exists any test with the same name
     * @param name the name that we want to check
     * @return a boolean that shows if the input name is valid
     */
    public boolean comprovaTest(String name){
        for(int i = 0; i < tests.get(i).getName().length(); i++){
            if(tests.get(i).getName().equals(name)){
                return false;
            }
        }
        return true;
    }
}
