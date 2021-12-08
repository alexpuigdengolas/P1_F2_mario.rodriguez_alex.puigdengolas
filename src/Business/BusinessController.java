package Business;

import Presentation.ViewController;

import java.util.LinkedList;

public class BusinessController {
    ViewController viewController = new ViewController(this);
    LinkedList<Test> tests = new LinkedList<Test>();
    LinkedList<Edition> editions = new LinkedList<Edition>();

    public BusinessController(){
        int optionManage = 0, trialChoice = 0, trialShowChoice = 0;
        String optionManageTrials, optionManageEditions;
        boolean exit = false, exitTrialManager = false, exitEditionManager = false;

        boolean role = viewController.startView();

        //Compositor
        if(role){
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

        //Conductor
        }else{
            Conductor actualUser = new Conductor();
        }
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
