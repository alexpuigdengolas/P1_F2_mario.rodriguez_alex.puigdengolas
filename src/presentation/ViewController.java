package presentation;

import business.*;
import business.role.Enginyer;
import business.tests.Publication;
import business.tests.budgetRequest;
import business.tests.doctoralDefense;
import business.tests.estudiMaster;

import javax.swing.text.View;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * This class will be used to show any information that is needed
 */
public class ViewController {
    private BusinessController businessController;
    private ViewUI viewUI;

    /**
     * This method is the constructor of the controller
     * @param businessController the business controller that controls all the execution of the code
     */
    //Controller
    public ViewController(BusinessController businessController) {
        this.businessController = businessController;
        this.viewUI = new ViewUI(this);
    }


    public BusinessController getBusinessController() {
        return businessController;
    }

    /**
     * This is the view that will make the user chose between the tow modes of execution of the code
     * @return returns a boolean that shows the mode of execution of the code
     */
    public boolean startView(){
        viewUI.presentationView();
        String option;

        boolean ok = false;
        do {
            option = viewUI.startView();
            if (option.equals("A")) {
                ok = true;
                return true;
            } else if (option.equals("B")) {
                ok = true;
                return false;
            }else{
               viewUI.showErrorMessageln("The option is not available, please try again");
               viewUI.showMessageln("Who are you?");
            }
        }while (!ok);
        return false;
    }

    /**
     * This view is the literal first one to appear in execution that will allow us to chose the way
     * that the data will be read and written
     * @return the boolean that shows the way that the data will be written
     */
    public boolean dataSelection() {
        String option;

        boolean ok = false, csvOn = false;
        do{
            option = viewUI.dataSelection();


        if(option.equals("I")){
            csvOn = true;
            viewUI.makeLine();
            viewUI.showMessageln("Loading data from CSV files...");
            viewUI.makeLine();
            ok = true;
        }else if(option.equals("II")){
            viewUI.makeLine();
            viewUI.showMessageln("Loading data from JSON files...");
            viewUI.makeLine();
            ok = true;
        }else{
            viewUI.makeLine();
            viewUI.showErrorMessageln("This option is not available please try again");
            viewUI.makeLine();
        }

        }while(!ok);

        return csvOn;
    }

    /**
     * This is the view of that makes the user chose the type of things that makes you chose between creating
     * editions or tests
     * @return an int representing the option selected
     */
    public int mainCompositorView() {
        boolean ok = false;
        String option;


        viewUI.makeLine();
        viewUI.showMessageln("Entering management mode...");
        do{
            option = viewUI.mainCompositorView();
            if (option.equals("1")) {
                ok = true;
                return Integer.parseInt(option);
            } else if (option.equals("2")) {
                ok = true;
                return Integer.parseInt(option);
            } else if (option.equals("3")) {
                ok = true;
                return Integer.parseInt(option);
            } else {
                viewUI.makeLine();
                viewUI.showErrorMessageln("The option is not available, please try again");
                viewUI.makeLine();
            }
        }while (!ok);


        return 0;
    }

    /**
     * This view will allow the user to manage the Trials and make possible to
     * create, delete and show all the trials
     * @return the option selected
     */
    public String manageTrialsView() {
        String option;
        boolean ok = false;
        do{
            option = viewUI.manageTrialsView();
            if(option.equals("a")||option.equals("b")||option.equals("c")||option.equals("d")){
                ok = true;
                return option;
            }else{
                viewUI.showErrorMessageln("The option is not available, please try again");
            }
        }while (!ok);

        return null;


    }

    /**
     * This view will appear if the we want to create a trial
     * @return the int that indicates witch type of trial we want to create
     */
    public int trialChoiceView() {
        String option;
        boolean ok = false;

        do{
            option = viewUI.trialChoiceView();

            if(option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4")){
                ok = true;
                return Integer.parseInt(option);
            }else{
                viewUI.makeLine();
                viewUI.showErrorMessageln("The option is not available, please try again");
                viewUI.makeLine();
            }
        }while (!ok);
        return 0;
    }

    /**
     * This view will show when we create a Paper publication
     * @return the paper publication we all created
     */
    public Publication createPaperPublication() {
        String name = null, nameMag=null, quartil=null,option;
        int acceptanceProbability = 0, revisionProbability = 0, notAcceptedProbability = 0;
        boolean ok = false, ok2 = false, aux;
        Scanner sc = new Scanner(System.in);

        do{
            viewUI.makeLine();
            while(!ok){
                viewUI.showMessageln("Enter the trial’s name: ");
                name = sc.nextLine();
                aux = businessController.comprovaTest(name);
                if(name.equals("") || !aux) {
                    viewUI.showErrorMessageln("Please enter a correct trial's name");
                    viewUI.makeLine();
                } else{
                    ok = true;
                }
            }

            ok = false;
            while (!ok) {
                viewUI.showMessageln("Enter the journal’s name: ");
                nameMag = sc.nextLine();
                if(nameMag.equals("")){
                    viewUI.showErrorMessageln("Please enter a correct journal's name");
                    viewUI.makeLine();
                }else{
                    ok = true;
                }
            }
            ok = false;
            while(!ok){
                viewUI.showMessageln("Enter the journal’s quartile: ");
                quartil = sc.nextLine();
                if(!quartil.equals("Q1") && !quartil.equals("Q2") && !quartil.equals("Q3") && !quartil.equals("Q4")){
                    viewUI.showErrorMessageln("Please enter a correct journal's quartile, values between Q1-Q4");
                    viewUI.makeLine();
                }else{
                    ok = true;
                }
            }
            while(!ok2) {
                ok = false;
                while (!ok) {
                    viewUI.showMessageln("Enter the acceptance probability: ");
                    option = sc.nextLine();
                    aux = businessController.isNumber(option);
                    if(aux){
                        acceptanceProbability = Integer.parseInt(option);
                    }else{
                        acceptanceProbability = 101;
                    }
                    //sc.nextLine();
                    if ((acceptanceProbability < 0 || acceptanceProbability > 100)) {
                        viewUI.showErrorMessageln("Please enter a correct acceptance probability, values between 0-100");
                        viewUI.makeLine();
                    } else {
                        ok = true;
                    }
                }
                ok = false;

                while (!ok) {
                    viewUI.showMessageln("Enter the revision probability: ");
                    option = sc.nextLine();
                    aux = businessController.isNumber(option);
                    if(aux){
                        revisionProbability = Integer.parseInt(option);
                    }else{
                        revisionProbability = 101;
                    }
                    //sc.nextLine();
                    if ((revisionProbability < 0 || revisionProbability > 100)) {
                        viewUI.showErrorMessageln("Please enter a correct revision probability, values between 0-100");
                        viewUI.makeLine();
                    } else {
                        ok = true;
                    }
                }
                ok = false;

                while (!ok) {
                    viewUI.showMessageln("Enter the rejection probability: ");
                    option = sc.nextLine();
                    aux = businessController.isNumber(option);
                    if(aux){
                        notAcceptedProbability = Integer.parseInt(option);
                    }else{
                        notAcceptedProbability = 101;
                    }
                    //sc.nextLine();
                    if ((notAcceptedProbability < 0 || notAcceptedProbability > 100)) {
                        viewUI.showErrorMessageln("Please enter a correct rejection probability, values between 0-100");
                        viewUI.makeLine();
                    } else {
                        ok = true;
                    }
                }

                if(acceptanceProbability+revisionProbability+notAcceptedProbability != 100){
                    viewUI.showErrorMessageln("Please the sum of acceptance probability + revision probability + rejection probability must be equal to 100");
                    viewUI.makeLine();
                }else ok2 = true;
            }
            viewUI.makeLine();
            viewUI.showMessageln("The trial was created successfully!");
            return new Publication(name, nameMag, quartil, acceptanceProbability, revisionProbability, notAcceptedProbability);
        }while(!ok && !ok2);
    }

    /**
     * This view will show the user all the trials already existing
     * @param tests is all the tests that we have saved
     */
    public void trialChoiceShowView(LinkedList<Test> tests){
        boolean ok = false, aux;
        String optionAux;
        int option;
        Scanner sc = new Scanner(System.in);


        do {
            if (tests.size() == 0) {
                viewUI.makeLine();
                viewUI.showErrorMessageln("There are no test's in the system yet");
                viewUI.makeLine();
                ok = true;
            } else {
                viewUI.showMessageln(" ");
                viewUI.showTrials(tests);
                optionAux = sc.nextLine();
                aux = businessController.isNumber(optionAux);
                if(aux){
                    option = Integer.parseInt(optionAux);
                }else{
                    option = 0;
                }
                if(option > tests.size()+1 || option < 1){
                    viewUI.showErrorMessageln("The option is not available, please try again");
                }else if(option != tests.size()+1){
                    viewUI.makeLine();
                    viewUI.showMessageln(tests.get(option-1).getName());
                    tests.get(option-1).showInfo(this);
                    viewUI.makeLine();
                }else{
                    ok = true;
                }
            }
        }while(!ok);
    }

    /**
     * This view will show to the user when he wants to delete a test
     * @param tests is all the tests that we have saved
     */
    public void trialChoiceDeleteView(LinkedList<Test> tests, List<Edition> editions) {
        boolean ok = false, aux, error = false;
        String optionAux, confirmation;
        int option;
        Scanner sc = new Scanner(System.in);


        do {
            if (tests.size() == 0) {
                viewUI.makeLine();
                viewUI.showErrorMessageln("There are no test's in the system yet");
                viewUI.makeLine();
                ok = true;
            } else {
                viewUI.makeLine();
                viewUI.showTrials(tests);
                optionAux = sc.nextLine();
                aux = businessController.isNumber(optionAux);
                if(aux){
                    option = Integer.parseInt(optionAux);
                }else{
                    option = 0;
                }

                for (Edition edition : editions) {
                    for (int j = 0; edition.getTests().size() > j; j++) {
                        if (edition.getTests().get(j).getName().equals(tests.get(option - 1).getName())) {
                            error = true;
                            break;
                        }
                    }
                }

                if(error){
                    viewUI.showErrorMessageln("The test cant be deleted (it is in a existing edition)");
                }else {
                    if (option > tests.size() + 1 || option < 1) {
                        viewUI.showErrorMessageln("The option is not available, please try again");
                    } else if (option != tests.size() + 1) {
                        viewUI.makeLine();
                        viewUI.showMessage("Enter the trial’s name for confirmation: ");
                        confirmation = sc.nextLine();
                        if (confirmation.equals(tests.get(option - 1).getName())) {
                            viewUI.makeLine();
                            tests.remove(option - 1);
                            viewUI.showMessageln("The trial was successfully deleted");
                            ok = true;
                        } else {
                            viewUI.showErrorMessageln("The trial wasn't successfully deleted");
                            ok = true;
                        }

                    } else {
                        ok = true;
                    }
                }
            }
            viewUI.makeLine();
        }while(!ok);
    }

    /**
     * This view will allow the user to manage the Editions and make possible to
     * create, delete, duplicate and show all the editions and the trials that they contain
     * @return the option selected
     */
    public String manageEditionView() {
        String option;
        boolean ok = false;

        do {
            option = viewUI.manageEditionView();
            if(option.equals("a")||option.equals("b")||option.equals("c")||option.equals("d") || option.equals("e")){
                ok = true;
                return option;
            }else{
                viewUI.showErrorMessageln("The option is not available, please try again");
            }
        }while (!ok);
        return null;
    }

    /**
     * This view will show when we create an Edition
     * @param tests is all the tests that we have saved
     * @param editions is all the editions that we have saved
     */
    public void createEditionView(List<Test> tests, List<Edition> editions) {
        boolean ok = false, aux;
        String option;
        Scanner sc = new Scanner(System.in);
        int year =2023, initialPlayers=1  , numTest=3, rounds = 0, trialSelect;
        LinkedList<Test> editionTests = new LinkedList<Test>();
        LinkedList<Player> players = new LinkedList<Player>();

        do {

           enterEditionYear();

            while(!ok) {
                viewUI.showMessage("Enter the initial number of players: ");
                option = sc.nextLine();
                aux = businessController.isNumber(option);
                if(aux){
                    initialPlayers = Integer.parseInt(option);
                }else{
                    initialPlayers = 0;
                }
                //sc.nextLine();
                if ((initialPlayers > 0 && initialPlayers < 6) ) {
                    ok = true;
                }else{
                    viewUI.showErrorMessageln("The initial number of players must must be more than 0 and less than 6");
                    viewUI.makeLine();
                }
            }
            ok = false;
            while(!ok) {
                viewUI.showMessage("Enter the number of trials: ");
                option = sc.nextLine();
                aux = businessController.isNumber(option);
                if(aux){
                    numTest = Integer.parseInt(option);
                }else{
                    numTest = 0;
                }
                //sc.nextLine();
                if ((numTest > 2 && numTest < 12)) {
                    ok = true;
                }else{
                    viewUI.showErrorMessageln("The number oj trials must be more than 2 and less than 13");
                    viewUI.makeLine();
                }
            }
            viewUI.makeLine();
            if (tests.size() == 0) {
                viewUI.makeLine();
                viewUI.showErrorMessageln("There are no test's in the system yet");
                viewUI.makeLine();
                ok = true;
            } else {
                viewUI.makeLine();
                for (int i = 0; i < tests.size(); i++) {
                    viewUI.showMessageln("    " + (i + 1) + ") " + tests.get(i).getName());
                }

                for (int i = 0; i < numTest; i++) {
                    viewUI.showMessage("Pick a trial (" + (i + 1) + "/" + numTest + "):");
                    option = sc.nextLine();
                    aux = businessController.isNumber(option);
                    if(aux){
                        trialSelect = Integer.parseInt(option);
                    }else{
                        trialSelect = 0;
                    }
                    if (trialSelect > tests.size() + 1 || trialSelect < 1) {
                        viewUI.showErrorMessageln("The option is not available, please try again");
                        i--;
                    } else if (trialSelect != tests.size() + 1) {
                        editionTests.add(tests.get(trialSelect - 1));
                    }
                }
            }
            if (ok) {
                for(int i = 0; i < initialPlayers; i++){
                    players.add(new Enginyer());
                }
                editions.add(new Edition(this.businessController ,year, initialPlayers, numTest, rounds, editionTests, players));
            }

        }while(!ok);
        viewUI.makeLine();
    }

    /**
     * This view will show when we want to see the Editions
     * @param editions is all the editions that we have saved
     */
    public void showEditionView(List<Edition> editions) {
        boolean ok = false;
        Scanner sc = new Scanner(System.in);
        int option ;
        String optionAux;
        boolean aux;
        do{
            if(editions.size() == 0){
                viewUI.showErrorMessageln("There are no editions in the system yet");
                ok = true;
            }else {
                viewUI.makeLine();
                viewUI.showMessageln("Here are the current editions, do you want to see more details or go back?");
                viewUI.makeLine();
                viewUI.showEditions(editions);
                optionAux = sc.nextLine();
                aux = businessController.isNumber(optionAux);
                if(aux){
                    option = Integer.parseInt(optionAux);
                }else{
                    option = 0;
                }
                if (option > editions.size() + 1 || option < 1) {
                    viewUI.makeLine();
                    viewUI.showErrorMessageln("The option is not available, please try again");
                    viewUI.makeLine();
                } else if (option != editions.size() + 1) {
                    viewUI.makeLine();
                    editions.get((option) - 1).showInfo();
                    viewUI.makeLine();
                } else {
                    ok = true;
                }
            }
        }while(!ok);
    }

    /**
     * This view will show when we want to duplicate an Edition
     * @param editions is all the editions that we have saved
     */
    public void duplicateEdition(List<Edition> editions) {
        boolean ok = false, aux;
        String optionAux;
        Scanner sc = new Scanner(System.in);
        int option, year=2023,  initialPlayers=2;
        List<Player> players;
        Edition copyEdition;

        viewUI.showMessageln("Which edition do you want to clone?");
        viewUI.makeLine();
        do{
            if(editions.size() == 0){
                viewUI.showErrorMessageln("There are no editions in the system yet");
                ok = true;
            }else {
                viewUI.showEditions(editions);
                optionAux = sc.nextLine();
                aux = businessController.isNumber(optionAux);
                if (aux) {
                    option = Integer.parseInt(optionAux);
                } else {
                    option = 0;
                }
                if (option > editions.size() + 1 || option < 1) {
                    System.out.println(" ");
                    viewUI.showErrorMessageln("The option is not available, please try again");
                    System.out.println(" ");
                } else if (option != editions.size()+1) {
                    copyEdition = editions.get(option - 1);
                    players = copyEdition.getPlayers();

                    enterEditionYear();

                    while (!ok) {
                        viewUI.showMessage("Enter the initial number of players: ");
                        optionAux = sc.nextLine();
                        aux = businessController.isNumber(optionAux);
                        if (aux) {
                            initialPlayers = Integer.parseInt(optionAux);
                        } else {
                            initialPlayers = 0;
                        }
                        //sc.nextLine();
                        if ((initialPlayers > 0 && initialPlayers < 6)) {
                            ok = true;
                            if(initialPlayers > copyEdition.getPlayers().size()) {
                                for (int i = 0; i < initialPlayers - editions.size(); i++) {
                                    players.add(new Enginyer());
                                }
                            }else if (initialPlayers < copyEdition.getPlayers().size()){
                                players.clear();
                                for (int i = 0; i < initialPlayers; i++) {
                                    players.add(new Enginyer());
                                }
                            }
                        } else {
                            viewUI.showErrorMessageln("The initial number of players must must be more than 0 and less than 6");
                            viewUI.makeLine();
                        }
                    }
                    viewUI.makeLine();
                    editions.add(new Edition(this.businessController, year, initialPlayers, copyEdition.getNumTest(), 0, copyEdition.getTests(), players));
                }else{
                    ok = true;
                }
            }
        }while(!ok);
    }

    /**
     * This view will show when we want to delete an Edition
     * @param editions is all the editions that we have saved
     */
    public void deleteEditionView(List<Edition> editions) {
        boolean ok,aux;
        Scanner sc = new Scanner(System.in);
        int year, option;//, initialPlayers;
        String optionAux;

        //Edition copyEdition;

        viewUI.showMessageln("Which edition do you want to delete?");
        viewUI.makeLine();
        do{
            if(editions.size() == 0){
                viewUI.showErrorMessageln("There are no editions in the system yet");
                ok = true;
            }else {
                for (int i = 0; i < editions.size(); i++) {
                    viewUI.showMessageln("    " + (i + 1) + ") The Trials " + editions.get(i).getYear());
                }
                viewUI.makeLine();
                viewUI.showMessageln("    " + (editions.size() + 1) + ") Back");
                viewUI.makeLine();
                viewUI.showMessage("Enter an option: ");
                optionAux = sc.nextLine();
                aux = businessController.isNumber(optionAux);
                if (aux) {
                    option = Integer.parseInt(optionAux);
                } else {
                    option = 0;
                }
                if (option > editions.size() + 1 || option < 1) {
                    viewUI.makeLine();
                    viewUI.showMessageln("The option is not available, please try again");
                    viewUI.makeLine();
                    ok = false;
                } else if (option != editions.size() + 1) {
                    viewUI.makeLine();
                    viewUI.showMessage("Enter the edition’s year for confirmation: ");
                    optionAux = sc.nextLine();
                    aux = businessController.isNumber(optionAux);
                    if (aux) {
                        year = Integer.parseInt(optionAux);
                    } else {
                        year = 0;
                    }
                    if (year == editions.get(option - 1).getYear()) {
                        editions.remove(option - 1);
                        viewUI.showMessageln("The edition was successfully deleted.");
                        ok = true;
                    } else {
                        viewUI.showMessageln("The edition wasn't successfully deleted.");
                        ok = true;
                    }
                }else{
                    ok = true;
                }
            }
        }while(!ok);
    }

    /**
     * This view will be used when we execute an edition
     * @param edition the current edition
     * @param year the current year
     */
    public void mainConductorView(Edition edition, int year) {
        String name;
        boolean aux;
        Scanner sc = new Scanner(System.in);

        viewUI.mainConductorView(year);

        if(edition.getRounds() == 0) {
            for (int i = 0; i < edition.getInitialPlayers(); i++) {
                viewUI.showMessage("Enter the player's name (" + (i + 1) + "/" + edition.getInitialPlayers() + "): ");
                name = sc.nextLine();
                aux = false;

                for (int j = 0; j < edition.getPlayers().size(); j++) {
                    if (edition.getPlayers().get(j).getName().equals(name)) {
                        aux = true;
                    }
                }
                if (!aux) {
                    edition.getPlayers().get(i).setName(name);
                } else {
                    viewUI.showMessageln("This name is already used, please try again");
                    i--;
                }
            }
        }
    }

    /**
     * This view will be showed if the year is not available
     * @param year the year that we are analyzing
     */
    public void noEditionView(int year) {
        viewUI.noEditionView(year);
    }

    /**
     * This view will indicate that the publication has been passed
     * @param player the player that has passed
     */
    public void acceptedPublication(Player player) {
        viewUI.acceptedPublication(player);
    }

    /**
     * This view will indicate that the publication hasn't been passed
     * @param player the player that hasn't passed
     */
    public void rejectedPublication(Player player) {
        viewUI.rejectedPublication(player);
    }

    /**
     * This view will indicate that the publication is being revised
     */
    public void revisedPublication() {
        viewUI.revisedPublication();
    }

    /**
     * This view will get showed when the player is submitting
     * @param player the player that is submitting
     */
    public void submitting(Player player) {
        viewUI.submitting(player);
    }

    /**
     * this view will be showed if we want to get to the next test
     * @return a boolean if we want to execute the next test
     */
    public boolean nextTest() {
        return viewUI.nextTest();
    }

    /**
     * This will get showed if the edition has ended
     * @param edition current edition
     * @param winner the winner of the edition
     */
    public void editionEnded(Edition edition, Player winner) {
        viewUI.editionEnded(edition, winner);
    }

    /**
     * This view will show when creating a DoctoralDefense
     * @return a test with all the information of the doctoral defense
     */
    public Test createDoctoralDefense() {
        String name, field,option;
        int diff;
        boolean ok = false ,aux;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println(" ");
            System.out.print("Enter the trial’s name: ");
            name = sc.nextLine();
            aux = businessController.comprovaTest(name);
            if(name.equals("") || !aux) {
                System.err.println("Please enter a correct trial's name");
                System.out.println(" ");
            } else{

                System.out.print("Enter the thesis field of study: ");
                field = sc.nextLine();
                System.out.print("Enter the defense difficulty: ");
                option = sc.nextLine();
                aux = businessController.isNumber(option);
                if(aux){
                    diff = Integer.parseInt(option);
                }else{
                    diff = 0;
                }

                if(diff < 1 || diff > 10){
                    ok = false;
                }else{
                    ok = true;
                }

                if(!ok){
                    System.err.println("The information is not valid, please try again");
                }else{
                    System.out.println(" ");
                    System.out.println("The trial was created successfully!");
                    return new doctoralDefense(name, field, diff);
                }
            }
        }while(!ok);
        return null;
    }

    /**
     * This view will indicate that the defense has been passed
     * @param player the player that has passed
     */
    public void defensePassed(Player player) {
        viewUI.defensePassed(player);
    }

    /**
     * This view will indicate that the defense hasn't been passed
     * @param player the player that hasn't passed
     */
    public void defenseNotPassed(Player player) {
        viewUI.defenseNotPassed(player);
    }

    /**
     * This view will show when creating a MasterStudy
     * @return a test with all the information of the master study
     */
    public Test createMasterEstudy() {
        String name = null, master = null,option;
        int credits = 0, prob = 0;
        boolean ok = false,aux;
        Scanner sc = new Scanner(System.in);

        do{
            while(!ok){
                System.out.println(" ");
                System.out.print("Enter the trial’s name: ");
                name = sc.nextLine();
                aux = businessController.comprovaTest(name);
                if(name.equals("") || !aux ){
                    System.err.println("Please enter a correct trial's name");
                    System.out.println(" ");
                }else{
                    ok = true;
                }
            }
            ok = false;

            while(!ok){
                System.out.print("Enter the master’s name: ");
                master = sc.nextLine();

                aux = businessController.comprovaTest(master); //Aqui falta mirar si el master esta be
                if(aux) {
                    if (master.equals("")) {
                        System.err.println("Please enter a correct master's name");
                        System.out.println(" ");
                    } else {
                        ok = true;
                    }
                }else{
                    System.err.println("Please enter a correct trial's name");
                    System.out.println(" ");
                }
            }
            ok = false;

            while(!ok){
                System.out.print("Enter the master’s ECTS number: ");
                option = sc.nextLine();
                aux = businessController.isNumber(option);
                if(aux){
                    credits = Integer.parseInt(option);
                }else{
                    credits = 0;
                }
                if( credits > 120 || credits < 60){
                    System.err.println("Please enter a correct credits value, [60-210]");
                    System.out.println(" ");
                }else{
                    ok = true;
                }
            }
            ok = false;

            while(!ok){
                System.out.print("Enter the credit pass probability: ");
                option = sc.nextLine();
                aux = businessController.isNumber(option);
                if(aux){
                    prob = Integer.parseInt(option);
                }else{
                    prob = 101;
                }

                if( prob > 100 || prob < 0){
                    System.err.println("Pleas enter a correct prob value, [0-100]");
                    System.out.println(" ");
                }else{
                    ok = true;
                }
            }

            System.out.println(" ");
            System.out.println("The trial was created successfully!");
            return new estudiMaster(name, master, credits, prob);
        }while(!ok);
    }

    /**
     * This view will indicate that the master study has been passed
     * @param player the player that has passed
     */
    public void masterPassed(Player player, int totalEcts, int ectsPassed){
        viewUI.masterPassed(player, totalEcts, ectsPassed);
    }

    /**
     * This view will indicate that the master study hasn't been passed
     * @param player the player that hasn't passed
     */
    public void masterNotPassed(Player player, int totalEcts, int ectsPassed){
        viewUI.masterNotPassed(player, totalEcts, ectsPassed);
    }

    /**
     * This view will show when creating a BudgetRequest
     * @return a test with all the information of the budget request
     */
    public Test createBudgetRequest() {
        String name, entity,option;
        double budget;
        boolean ok = false,aux;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println(" ");
            System.out.print("Enter the trial’s name: "); // falta mirar que no es repetixi el nom

            name = sc.nextLine();

            aux = businessController.comprovaTest(name); //Aqui falta mirar si el master esta be
            if(aux) {
                System.out.print("Enter the entity’s name: ");
                entity = sc.nextLine();
                System.out.print("Enter the budget demanded: ");

                option = sc.nextLine();
                aux = businessController.isNumber(option);
                if (aux) {
                    budget = Integer.parseInt(option);
                } else {
                    budget = 0;
                }

                if ((budget < 1000 || budget > 2000000000)) {
                    ok = false;
                } else {
                    ok = true;
                }

                if (!ok) {
                    System.err.println("The information is not valid, please try again");
                } else {
                    System.out.println(" ");
                    System.out.println("The trial was created successfully!");
                    return new budgetRequest(name, entity, budget);
                }
            }else{
                System.err.println("Please enter a correct trial's name");
                System.out.println(" ");
            }
        }while(!ok);
        return null;
    }

    /**
     * This view will indicate that the budget request has been passed
     * @param players the group of players that has passed
     */
    public void BudgetPassed(List<Player> players){
        viewUI.BudgetPassed(players);
    }

    /**
     * This view will indicate that the budget request hasn't been passed
     * @param players the group of players that hasn't passed
     */
    public void BudgetNotPassed(List<Player> players){
        viewUI.BudgetNotPassed(players);
    }

    /**
     * This view will get seen if all the players are eliminated
     */
    public void allPlayersDisc() {
        viewUI.allPlayersDisc();
    }

    /**
     * The view that shows the publication
     * @param publication the publication that we want to show
     */
    public void showPublication (Publication publication){
        viewUI.showPublication(publication);
    }

    /**
     * The view that shows the doctoralDefense
     * @param doctoralDefense the doctoral defense that we want to show
     */
    public void showDoctoralDefense(doctoralDefense doctoralDefense) {
        viewUI.showDoctoralDefense(doctoralDefense);
    }

    /**
     * The view that shows the budgetRequest
     * @param budgetRequest the budget request that we want to show
     */
    public void showBudgetRequest(budgetRequest budgetRequest){
        viewUI.showBudgetRequest(budgetRequest);
    }

    /**
     * The view that shows the studyMaster
     * @param estudiMaster the study master that we want to show
     */
    public void showEstudyMaster(estudiMaster estudiMaster){
        viewUI.showEstudyMaster(estudiMaster);
    }

    /**
     * This method will show all the information of the edition
     * @param edition the edition we want to show
     */
    public void showEditionInformation(Edition edition){
        viewUI.showEditionInformation(edition);
    }

    /**
     * This view will show when shutting down
     */
    public void shutDown() {
        viewUI.shutDown();
    }

    /**
     * This will show if the year already exists
     */
    public void yearErrorChoseAgain() {
        viewUI.yearErrorChoseAgain();
    }

    /**
     * This will show if the year is too big
     */
    public void reallyBigYearError() {
        viewUI.reallyBigYearError();
    }

    /**
     * This will show if the player is eliminated
     * @param player the player that has been eliminated
     */
    public void playerEliminated(Player player){
        viewUI.playerEliminated(player);
    }

    /**
     * show current test
     * @param test the current tests
     * @param i the position of the test
     */
    public void showCurrentTest(Test test, int i) {
        viewUI.showCurrentTest(test, i);
    }


    /**
     * The console ask to user for the yaer of the edition
     */
    public void enterEditionYear(){
        boolean ok = false, aux;
        String option;
        int year;
        Scanner sc = new Scanner(System.in);
        while(!ok) {
            viewUI.makeLine();
            viewUI.showMessage("Enter the edition's year: ");
            option = sc.nextLine();
            aux = businessController.isNumber(option);
            if(aux){
                year = Integer.parseInt(option);
            }else{
                year = 0;
            }

            if (businessController.checkEditionsYear(year)) {
                ok = true;
            }
        }
    }
}
