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
        List<Edition> importEditions = new ArrayList<Edition>() ;
        boolean csvOn = viewController.dataSelection();

        CsvCotroller csv = new CsvCotroller(); //creamos objeto
        jsonController json = new jsonController();

        if (csvOn) {
            importEditions = csv.readCSV("CSV/Edicions.csv");
        }else{
            importEditions = json.readJSON("JSON/Edicions.json");
        }

        this.editions = importEditions;
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
            json.writeJSON(editions, "JSON/Edicions.json");
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
                        }
                    } else if (optionManageTrials.equals("b")) {
                        //Show Test View
                        viewController.trialChoiceShowView(tests);

                    } else if (optionManageTrials.equals("c")) {
                        //Delete Test View
                        viewController.trialChoiceDeleteView(tests);

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
            if(edition.getTests().get(i).getClass().getSimpleName().equals("Publication"))
            {
                executePublication(i, edition);
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
        for (int i = 0; edition.getPlayers().size() < i; i++){
            if(maxpi < edition.getPlayers().get(i).getInvestigationPoints()){
                winner = i;
            }
        }
        return edition.getPlayers().get(winner);
    }

    private void executePublication(int iterationTests, Edition edition) {
        Publication publication = (Publication) edition.getTests().get(iterationTests);
        String quartil = ((Publication) edition.getTests().get(iterationTests)).getQuartil();
        try {
            for (int i = 0; i < edition.getPlayers().size(); i++) {
                //TODO Tener en cuenta probabiulidades de ejecución (No esta bien explicado en el enunciado)
                boolean result = false;
                viewController.submitting(edition.getPlayers().get(i));
                while (!result) {
                    double numAcceptance = Math.random() * 100;
                    double numRejected = Math.random() * 100;
                    double numRevision = Math.random() * 100;
                    if (Math.abs(numAcceptance - publication.getAcceptanceProbability()) > Math.abs(numRejected - publication.getNotAcceptedProbability()) && Math.abs(numAcceptance - publication.getAcceptanceProbability()) > Math.abs(numRevision - publication.getRevisionProbability())) {
                        getReward(quartil, edition.getPlayers().get(i));
                        viewController.acceptedPublication(edition.getPlayers().get(i));
                        result = true;
                    } else {
                        if (Math.abs(numAcceptance - publication.getAcceptanceProbability()) < Math.abs(numRejected - publication.getNotAcceptedProbability()) && Math.abs(numRejected - publication.getNotAcceptedProbability()) > Math.abs(numRevision - publication.getRevisionProbability())) {
                            getPenalitation(quartil, edition, i);
                            viewController.rejectedPublication(edition.getPlayers().get(i));
                            result = true;
                        } else {
                            viewController.revisedPublication();
                        }
                    }
                }
            }
        }catch (Exception e){

        }
    }

    private void getPenalitation(String quartil, Edition edition, int playerIterarion) {
        switch (quartil){
            case ("Q1"):
                edition.getPlayers().get(playerIterarion).setInvestigationPoints( edition.getPlayers().get(playerIterarion).getInvestigationPoints()- 5);
                removePlayer(edition, playerIterarion);
                break;
            case ("Q2"):
                edition.getPlayers().get(playerIterarion).setInvestigationPoints( edition.getPlayers().get(playerIterarion).getInvestigationPoints()- 4);
                removePlayer(edition, playerIterarion);
                break;
            case ("Q3"):
                edition.getPlayers().get(playerIterarion).setInvestigationPoints( edition.getPlayers().get(playerIterarion).getInvestigationPoints()- 3);
                removePlayer(edition, playerIterarion);
                break;
            case ("Q4"):
                edition.getPlayers().get(playerIterarion).setInvestigationPoints( edition.getPlayers().get(playerIterarion).getInvestigationPoints()- 2);
                removePlayer(edition, playerIterarion);
                break;
        }
    }

    private void removePlayer(Edition edition, int playerIterarion) {
        if(edition.getPlayers().get(playerIterarion).getInvestigationPoints() <= 0){
            edition.getPlayers().remove(playerIterarion);
        }
    }


    private void getReward(String quartil, Player player) {
        switch (quartil){
            case ("Q1"):
                player.setInvestigationPoints( player.getInvestigationPoints()+ 4);
                break;
            case ("Q2"):
                player.setInvestigationPoints( player.getInvestigationPoints()+ 3);
                break;
            case ("Q3"):
                player.setInvestigationPoints( player.getInvestigationPoints()+ 2);
                break;
            case ("Q4"):
                player.setInvestigationPoints( player.getInvestigationPoints()+ 1);
                break;
        }
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
