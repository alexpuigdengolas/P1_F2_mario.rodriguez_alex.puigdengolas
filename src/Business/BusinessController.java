package Business;

import Presentation.ViewController;

import java.util.LinkedList;

public class BusinessController {
    ViewController viewController = new ViewController();
    LinkedList<Test> tests = new LinkedList<Test>();

    public BusinessController(){
        int optionManage = 0, trialChoice = 0, trialShowChoice = 0;
        String optionManageTrials;
        boolean exit = false, exitTrialManager = false;

        boolean role = viewController.startView();

        //Compositor
        if(role){
            do {
                Compositor actualUser = new Compositor();
                optionManage = viewController.mainCompositorView();
                if (optionManage == 1) {
                    do {
                        optionManageTrials = viewController.manageTrialsView();
                        if (optionManageTrials.equals("a")) {
                            trialChoice = viewController.trialChoiceView();
                            if(trialChoice == 1){
                                tests.add(viewController.createPaperPublication());
                            }
                        } else if (optionManageTrials.equals("b")) {
                            viewController.trialChoiceShowView(tests);

                        } else if (optionManageTrials.equals("c")) {

                        } else {
                            exitTrialManager = true;
                        }
                    }while (!exitTrialManager);

                } else if (optionManage == 2) {

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
}
