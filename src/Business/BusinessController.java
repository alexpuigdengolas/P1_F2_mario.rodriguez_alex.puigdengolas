package Business;

import Persistance.CsvCotroller;
import Persistance.jsonController;
import Presentation.ViewController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class BusinessController {
    ViewController viewController = new ViewController(this);
    LinkedList<Test> tests = new LinkedList<Test>();
    List<Edition> editions = new LinkedList<Edition>();


    public BusinessController(){
        boolean csvOn = viewController.dataSelection();

        CsvCotroller csv = new CsvCotroller(); //creamos objeto
        jsonController json = new jsonController();

        if (csvOn) {
            editions = csv.readCSV("CSV/Edicions.csv");
        }else{
            editions = json.readJSON("JSON/Edicions.json");
            tests = json.readTestJSON("JSON/EdicionsPubliTest.json", "JSON/EdicionsDefTest.json", "JSON/EdicionsMasterTest.json", "JSON/EdicionsReqTest.json");
            for(int i = 0; i < editions.size(); i++){
                if(editions.get(i).getYear() == Calendar.getInstance().get(Calendar.YEAR)){
                    editions.get(i).setTests(this.tests);
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
            json.writeEditionsJSON(editions, "JSON/Edicions.json");

        }
    }

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

    public void compositorController(){
        int optionManage = 0, trialChoice = 0, trialShowChoice = 0;
        String optionManageTrials, optionManageEditions;
        boolean exit = false, exitTrialManager = false, exitEditionManager = false;
        do {
            Compositor actualUser = new Compositor();

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
                        System.out.println(" ");
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
                System.out.println(" ");
                System.out.println("Shutting down...");
            }
        }while (!exit);
    }

    public List<Edition> getEditions() {
        return editions;
    }

    public ViewController getViewController() {
        return viewController;
    }

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }

    public void conductorComposer(){
        Conductor actualUser = new Conductor();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        Edition edition = lookForCurrentEdition(editions, currentYear);
        if(edition == null) {
            viewController.noEditionView(currentYear);
        }else {
            viewController.mainConductorView(edition, currentYear);
            executeEdition(edition);

        }
    }

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




    private Edition lookForCurrentEdition(List<Edition> editions, int currentYear) {
        Edition currentEdition = null;

        for (Edition edition : editions) {
            if (edition.getYear() == currentYear) {
                currentEdition = edition;
            }
        }

        return currentEdition;
    }

    public boolean checkEditionsYear(int year) {
        boolean ok = true;
        for(int i = 0; editions.size() > i; i++){
            if(editions.get(i).getYear() == year){
                ok = false;
            }
        }
        return ok;
    }
}
