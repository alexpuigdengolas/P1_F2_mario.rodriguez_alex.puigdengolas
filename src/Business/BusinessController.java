package Business;

import Presentation.ViewController;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class BusinessController {
    ViewController viewController = new ViewController(this);
    LinkedList<Test> tests = new LinkedList<Test>();
    List<Edition> editions = new LinkedList<Edition>();

    public BusinessController(List<Edition> editions){

        this.editions = editions;
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
                //todo
                //Aqui hay que poner que se guarde la info en los CSV's
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
            for(int i = 0; i < edition.getNumTest(); i++){
                executeEdition(editions, i);
            }
        }
    }

    private void executeEdition(List<Edition> editions, int iteration) {
        //Ver tema de probabilidades
        for (int i = 0; i < editions.get(iteration).getNumTest(); i++) {
            if(editions.get(iteration).getTests().get(i).getClass().getSuperclass().equals("Publication"))
            {
                executePublication(i, iteration, editions);
            }

        }
    }

    private void executePublication(int iterationTests, int iteration, List<Edition> editions) {
        for (int i = 0; i < editions.get(iteration).getPlayers().size(); i++) {
            //TODO Tener en cuenta probabiulidades de ejecución
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
