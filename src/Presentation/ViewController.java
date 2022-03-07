package Presentation;

import Business.*;
import Business.Role.Enginyer;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ViewController {
    BusinessController businessController;

    //Controller
    public ViewController(BusinessController businessController) {
        this.businessController = businessController;
    }

    public boolean startView(){
        Scanner sc = new Scanner(System.in);
        String option;
        boolean ok = false;

        System.out.println(" _____ _          _____      _       _");
        System.out.println("/__   \\ |__  ___ /__   \\_ __(_) __ _| |___");
        System.out.println("  / /\\| '_ \\/ _ \\  / /\\| '__| |/ _` | / __|");
        System.out.println(" / /  | | | | __/ / /  | |  | | (_| | \\__ \\");
        System.out.println(" \\/   |_| |_|\\___|\\/   |_|  |_|\\__,_|_|___/");
        System.out.println(" ");
        System.out.println("Welcome to The Trials. Who are you?");
        System.out.println(" ");
        do {

            System.out.println("    A) The Composer");
            System.out.println("    B) This year's Conductor");
            System.out.println(" ");
            System.out.print("Enter the role:  ");
            option = sc.nextLine();

            if (option.equals("A")) {
                ok = true;
                return true;
            } else if (option.equals("B")) {
                ok = true;
                return false;
            }else{
                System.out.println("");
                System.err.println("The option is not available, please try again");
                System.out.println("");
                System.out.println("Who are you?");
                System.out.println(" ");
            }
        }while (!ok);

       return false;
    }

    public int mainCompositorView() {
        String option;
        Scanner sc = new Scanner(System.in);
        boolean ok = false;

        System.out.println(" ");
        System.out.println("Entering management mode...");
        do{
            System.out.println(" ");
            System.out.println("    1) Manage Test's");
            System.out.println("    2) Manage Editions");
            System.out.println(" ");
            System.out.println("    3) Exit");
            System.out.print("Enter an option:  ");
            option = sc.nextLine();

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
                System.out.println(" ");
                System.err.println("The option is not available, please try again");
                System.out.println(" ");
            }
        }while (!ok);
        return 0;
    }

    public String manageTrialsView() {
        Scanner sc = new Scanner(System.in);
        String option;
        boolean ok = false;

        do {
            System.out.println(" ");
            System.out.println("Trial Management System");
            System.out.println(" ");
            System.out.println("    a) Create Trial");
            System.out.println("    b) List Trials");
            System.out.println("    c) Delete Trial");
            System.out.println(" ");
            System.out.println("    d) Back");
            System.out.println(" ");
            System.out.print("Enter an option:  ");
            option = sc.nextLine();

            if(option.equals("a")||option.equals("b")||option.equals("c")||option.equals("d")){
                ok = true;
                return option;
            }else{
                System.err.println("The option is not available, please try again");
            }
        }while (!ok);
        return null;
    }

    public int trialChoiceView() {
        String option;
        Scanner sc = new Scanner(System.in);
        boolean ok = false;

        do{
            System.out.println("    --- Trial types ---");
            System.out.println(" ");
            System.out.println("1) Paper publication");
            System.out.println("2) Doctoral thesis defense");
            System.out.println("3) Master studies");
            System.out.println("4) Budget request");
            System.out.println(" ");
            System.out.print("Enter the test's type:  ");
            option = sc.nextLine();

            if(option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4")){
                ok = true;
                return Integer.parseInt(option);
            }else{
                System.out.println(" ");
                System.err.println("The option is not available, please try again");
                System.out.println(" ");
            }
        }while (!ok);
        return 0;
    }

    public Publication createPaperPublication() {
        String name = null, nameMag=null, quartil=null,option;
        int acceptanceProbability = 0, revisionProbability = 0, notAcceptedProbability = 0;
        boolean ok = false, ok2 = false, aux;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println(" ");
            while(!ok){
                System.out.print("Enter the trial’s name: ");
                name = sc.nextLine();
                aux = businessController.comprovaTest(name);
                if(name.equals("") || !aux) {
                    System.err.println("Please enter a correct trial's name");
                    System.out.println(" ");
                } else{
                    ok = true;
                }
            }

            ok = false;
            while (!ok) {
                System.out.print("Enter the journal’s name: ");
                nameMag = sc.nextLine();
                if(nameMag.equals("")){
                    System.err.println("Please enter a correct journal's name");
                    System.out.println(" ");
                }else{
                    ok = true;
                }
            }
            ok = false;
            while(!ok){
                System.out.print("Enter the journal’s quartile: ");
                quartil = sc.nextLine();
                if(!quartil.equals("Q1") && !quartil.equals("Q2") && !quartil.equals("Q3") && !quartil.equals("Q4")){
                    System.err.println("Please enter a correct journal's quartile, values between Q1-Q4");
                    System.out.println(" ");
                }else{
                    ok = true;
                }
            }
            while(!ok2) {
                ok = false;
                while (!ok) {
                    System.out.print("Enter the acceptance probability: ");
                    option = sc.nextLine();
                    aux = businessController.isNumber(option);
                    if(aux){
                        acceptanceProbability = Integer.parseInt(option);
                    }else{
                        acceptanceProbability = 101;
                    }
                    //sc.nextLine();
                    if ((acceptanceProbability < 0 || acceptanceProbability > 100)) {
                        System.err.println("Please enter a correct acceptance probability, values between 0-100");
                        System.out.println(" ");
                    } else {
                        ok = true;
                    }
                }
                ok = false;

                while (!ok) {
                    System.out.print("Enter the revision probability: ");
                    option = sc.nextLine();
                    aux = businessController.isNumber(option);
                    if(aux){
                        revisionProbability = Integer.parseInt(option);
                    }else{
                        revisionProbability = 101;
                    }
                    //sc.nextLine();
                    if ((revisionProbability < 0 || revisionProbability > 100)) {
                        System.err.println("Please enter a correct revision probability, values between 0-100");
                        System.out.println(" ");
                    } else {
                        ok = true;
                    }
                }
                ok = false;

                while (!ok) {
                    System.out.print("Enter the rejection probability: ");
                    option = sc.nextLine();
                    aux = businessController.isNumber(option);
                    if(aux){
                        notAcceptedProbability = Integer.parseInt(option);
                    }else{
                        notAcceptedProbability = 101;
                    }
                    //sc.nextLine();
                    if ((notAcceptedProbability < 0 || notAcceptedProbability > 100)) {
                        System.err.println("Please enter a correct rejection probability, values between 0-100");
                        System.out.println(" ");
                    } else {
                        ok = true;
                    }
                }

                if(acceptanceProbability+revisionProbability+notAcceptedProbability != 100){
                    System.err.println("Please the sum of acceptance probability + revision probability + rejection probability must be equal to 100");
                    System.out.println(" ");
                }else ok2 = true;
            }
                System.out.println(" ");
                System.out.println("The trial was created successfully!");
                return new Publication(name, nameMag, quartil, acceptanceProbability, revisionProbability, notAcceptedProbability);
        }while(!ok && !ok2);
    }

    public void trialChoiceShowView(LinkedList<Test> tests){
        boolean ok = false, aux;
        String optionAux;
        int option;
        Scanner sc = new Scanner(System.in);


        do {
            if (tests.size() == 0) {
                System.out.println(" ");
                System.err.println("There are no test's in the system yet");
                System.out.println(" ");
                ok = true;
            } else {
                System.out.println(" ");
                for(int i = 0; i < tests.size(); i++){
                    System.out.println("    "+ (i+1) +") "+ tests.get(i).getName());
                }
                System.out.println(" ");
                System.out.println("    "+ (tests.size()+1) +") Back");
                System.out.println(" ");
                System.out.print("Enter an potion: ");
                optionAux = sc.nextLine();
                aux = businessController.isNumber(optionAux);
                if(aux){
                    option = Integer.parseInt(optionAux);
                }else{
                    option = 0;
                }
                if(option > tests.size()+1 || option < 1){
                    System.err.println("The option is not available, please try again");
                }else if(option != tests.size()+1){
                    System.out.println(" ");
                    System.out.println(tests.get(option-1).getName());
                    tests.get(option-1).showInfo(this);
                    System.out.println(" ");
                }else{
                    ok = true;
                }
            }
        }while(!ok);
    }

    public void trialChoiceDeleteView(LinkedList<Test> tests) {
        boolean ok = false, aux;
        String optionAux, confirmation;
        int option;
        Scanner sc = new Scanner(System.in);


        do {
            if (tests.size() == 0) {
                System.out.println(" ");
                System.err.println("There are no test's in the system yet");
                System.out.println(" ");
                ok = true;
            } else {
                System.out.println(" ");
                for(int i = 0; i < tests.size(); i++){
                    System.out.println("    "+ (i+1) +") "+ tests.get(i).getName());
                }
                System.out.println(" ");
                System.out.println("    "+ (tests.size()+1) +") Back");
                System.out.println(" ");
                System.out.print("Enter an potion: ");
                optionAux = sc.nextLine();
                aux = businessController.isNumber(optionAux);
                if(aux){
                    option = Integer.parseInt(optionAux);
                }else{
                    option = 0;
                }

                if(option > tests.size()+1 || option < 1){
                    System.err.println("The option is not available, please try again");
                }else if(option != tests.size()+1){
                    System.out.println(" ");
                    System.out.print("Enter the trial’s name for confirmation: ");
                    confirmation = sc.nextLine();
                    if (confirmation.equals(tests.get(option - 1).getName())) {
                        System.out.println(" ");
                        tests.remove(option-1);
                        System.out.println("The trial was successfully deleted");
                        ok = true;
                    }else{
                        System.err.println("The trial wasn't successfully deleted");
                        ok = true;
                    }

                    System.out.println(" ");
                }else{
                    ok = true;
                }
            }
        }while(!ok);
    }

    public String manageEditionView() {
        Scanner sc = new Scanner(System.in);
        String option;
        boolean ok = false;

        do {
            System.out.println(" ");
            System.out.println("Edition Management System");
            System.out.println(" ");
            System.out.println("    a) Create Edition");
            System.out.println("    b) List Edition");
            System.out.println("    c) Duplicate Edition");
            System.out.println("    d) Delete Edition");
            System.out.println(" ");
            System.out.println("    e) Back");
            System.out.println(" ");
            System.out.print("Enter an option:  ");
            option = sc.nextLine();

            if(option.equals("a")||option.equals("b")||option.equals("c")||option.equals("d") || option.equals("e")){
                ok = true;
                return option;
            }else{
                System.err.println("The option is not available, please try again");
            }
        }while (!ok);
        return null;
    }

    public void createEditionView(List<Test> tests, List<Edition> editions) {
        boolean ok = false, aux;
        String option;
        Scanner sc = new Scanner(System.in);
        int year =2023, initialPlayers=1  , numTest=3, rounds = 0, trialSelect;
        LinkedList<Test> editionTests = new LinkedList<Test>();
        LinkedList<Player> players = new LinkedList<Player>();

        do {

            while(!ok) {
                System.out.println(" ");
                System.out.print("Enter the edition's year: ");
                option = sc.nextLine();
                aux = businessController.isNumber(option);
                if(aux){
                    year = Integer.parseInt(option);
                }else{
                    year = 0;
                }
                //sc.nextLine();
                if (businessController.checkEditionsYear(year)) {
                    ok = true;
                }
            }
            ok = false;
            while(!ok) {
                System.out.print("Enter the initial number of players: ");
                option = sc.nextLine();
                aux = businessController.isNumber(option);
                if(aux){
                    initialPlayers = Integer.parseInt(option);
                }else{
                    initialPlayers = 0;
                }
                //sc.nextLine();
                if ((initialPlayers > 0 && initialPlayers < 5) ) {
                    ok = true;
                }else{
                    System.err.println("The initial number of players must must be more than 0 and less than 6");
                    System.out.println("");
                }
            }
            ok = false;
            while(!ok) {
                System.out.print("Enter the number of trials: ");
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
                    System.err.println("The number oj trials must be more than 2 and less than 13");
                    System.out.println("");
                }
            }
            System.out.println(" ");
            if (tests.size() == 0) {
                System.out.println(" ");
                System.err.println("There are no test's in the system yet");
                System.out.println(" ");
                ok = true;
            } else {
                System.out.println(" ");
                for (int i = 0; i < tests.size(); i++) {
                    System.out.println("    " + (i + 1) + ") " + tests.get(i).getName());
                }

                for (int i = 0; i < numTest; i++) {
                    System.out.print("Pick a trial (" + (i + 1) + "/" + numTest + "):");
                    option = sc.nextLine();
                    aux = businessController.isNumber(option);
                    if(aux){
                        trialSelect = Integer.parseInt(option);
                    }else{
                        trialSelect = 0;
                    }
                    if (trialSelect > tests.size() + 1 || trialSelect < 1) {
                        System.err.println("The option is not available, please try again");
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
                editions.add(new Edition(year, initialPlayers, numTest, rounds, editionTests, players));
            }

        }while(!ok);

    }

    public void showEditionView(List<Edition> editions) {
        boolean ok = false;
        Scanner sc = new Scanner(System.in);
        int option ;
        String optionAux;
        boolean aux;
        do{
            if(editions.size() == 0){
                System.err.println("There are no editions in the system yet");
                ok = true;
            }else {
                System.out.println(" ");
                System.out.println("Here are the current editions, do you want to see more details or go back?");
                System.out.println(" ");
                for (int i = 0; i < editions.size(); i++) {
                    System.out.println("    " + (i + 1) + ") The Trials " + editions.get(i).getYear());
                }
                System.out.println(" ");
                System.out.println("    " + (editions.size() + 1) + ") Back");
                System.out.println(" ");

                System.out.print("Enter an option: ");
                optionAux = sc.nextLine();
                aux = businessController.isNumber(optionAux);
                if(aux){
                    option = Integer.parseInt(optionAux);
                }else{
                    option = 0;
                }
                if (option > editions.size() + 1 || option < 1) {
                    System.out.println(" ");
                    System.err.println("The option is not available, please try again");
                    System.out.println(" ");
                } else if (option != editions.size() + 1) {
                    System.out.println(" ");
                    editions.get((option) - 1).showInfo();
                    System.out.println(" ");
                } else {
                    ok = true;
                }
            }
        }while(!ok);
    }

    public void duplicateEdition(List<Edition> editions) {
        boolean ok = false, aux;
        String optionAux;
        Scanner sc = new Scanner(System.in);
        int option, year=2023, initialPlayers=2;
        Edition copyEdition;

        System.out.println("Which edition do you want to clone?");
        System.out.println(" ");
        do{
            if(editions.size() == 0){
                System.err.println("There are no editions in the system yet");
                ok = true;
            }else {
                for (int i = 0; i < editions.size(); i++) {
                    System.out.println("    " + (i + 1) + ") The Trials " + editions.get(i).getYear());
                }
                System.out.println(" ");
                System.out.println("    " + (editions.size() + 1) + ") Back");
                System.out.println(" ");
                System.out.print("Enter an option: ");
                optionAux = sc.nextLine();
                aux = businessController.isNumber(optionAux);
                if (aux) {
                    option = Integer.parseInt(optionAux);
                } else {
                    option = 0;
                }
                if (option > editions.size() + 1 || option < 1) {
                    System.out.println(" ");
                    System.err.println("The option is not available, please try again");
                    System.out.println(" ");
                } else if (option != editions.size()+1) {
                    copyEdition = editions.get(option - 1);
                    while (!ok) {
                        System.out.println(" ");
                        System.out.print("Enter the edition's year: ");
                        optionAux = sc.nextLine();
                        aux = businessController.isNumber(optionAux);
                        if (aux) {
                            year = Integer.parseInt(optionAux);
                        } else {
                            year = 0;
                        }
                        //sc.nextLine();
                        if (businessController.checkEditionsYear(year)) {
                            ok = true;
                        }
                    }
                    ok = false;
                    while (!ok) {
                        System.out.print("Enter the initial number of players: ");
                        optionAux = sc.nextLine();
                        aux = businessController.isNumber(optionAux);
                        if (aux) {
                            initialPlayers = Integer.parseInt(optionAux);
                        } else {
                            initialPlayers = 0;
                        }
                        //sc.nextLine();
                        if ((initialPlayers > 0 && initialPlayers < 5)) {
                            ok = true;
                        } else {
                            System.err.println("The initial number of players must must be more than 0 and less than 6");
                            System.out.println("");
                        }
                    }
                    System.out.println(" ");
                    editions.add(new Edition(year, initialPlayers, copyEdition.getNumTest(), copyEdition.getRounds(), copyEdition.getTests(), copyEdition.getPlayers()));
                }else{
                    ok = true;
                }
            }
        }while(!ok);
    }

    public void deleteEditionView(List<Edition> editions) {
        boolean ok,aux;
        Scanner sc = new Scanner(System.in);
        int year, option;//, initialPlayers;
        String optionAux;

        //Edition copyEdition;

        System.out.println("Which edition do you want to delete?");
        System.out.println(" ");
        do{
            if(editions.size() == 0){
                System.err.println("There are no editions in the system yet");
                ok = true;
            }else {
                for (int i = 0; i < editions.size(); i++) {
                    System.out.println("    " + (i + 1) + ") The Trials " + editions.get(i).getYear());
                }
                System.out.println(" ");
                System.out.println("    " + (editions.size() + 1) + ") Back");
                System.out.println(" ");
                System.out.print("Enter an option: ");
                optionAux = sc.nextLine();
                aux = businessController.isNumber(optionAux);
                if (aux) {
                    option = Integer.parseInt(optionAux);
                } else {
                    option = 0;
                }
                if (option > editions.size() + 1 || option < 1) {
                    System.out.println(" ");
                    System.err.println("The option is not available, please try again");
                    System.out.println(" ");
                    ok = false;
                } else if (option != editions.size() + 1) {
                    System.out.println(" ");
                    System.out.print("Enter the edition’s year for confirmation: ");
                    optionAux = sc.nextLine();
                    aux = businessController.isNumber(optionAux);
                    if (aux) {
                        year = Integer.parseInt(optionAux);
                    } else {
                        year = 0;
                    }
                    if (year == editions.get(option - 1).getYear()) {
                        editions.remove(option - 1);
                        System.out.println("The edition was successfully deleted.");
                        ok = true;
                    } else {
                        System.err.println("The edition wasn't successfully deleted.");
                        ok = true;
                    }
                }else{
                    ok = true;
                }
            }
        }while(!ok);
    }

    //Conductor
    public void mainConductorView(Edition edition, int year) {
        String name;
        boolean aux;
        Scanner sc = new Scanner(System.in);

        System.out.println(" ");
        System.out.println("Entering execution mode...");

        System.out.println(" ");
        System.out.println("    --- The Trials " + year + " ---");
        System.out.println(" ");
        for (int i = 0; i < edition.getInitialPlayers(); i++) {
            System.out.print("Enter the player's name (" + (i + 1) + "/" + edition.getInitialPlayers() + "): ");
            name = sc.nextLine();
            aux = false;
            for (int j = 0; j < edition.getPlayers().size(); j++) {
                if (edition.getPlayers().get(j).getName().equals(name)) {
                    aux = true;
                }
            }
            if (!aux) {
                edition.getPlayers().get(i).setName(name);
            }else {
                System.out.println("This name is already used, please try again");
                i--;
            }
        }
    }

    public void noEditionView(int year) {
        System.out.println(" ");
        System.out.println("Entering execution mode...");
        System.out.println(" ");
        System.out.println("No edition is defined for the current year ("+year+").");
        System.out.println(" ");
        System.out.println("Shutting down...");
    }

    public void acceptedPublication(Player player) {
        System.out.println("Accepted! PI count: "+player.getInvestigationPoints());
    }

    public void rejectedPublication(Player player) {
        System.out.println("Rejected! PI count: "+player.getInvestigationPoints());
    }

    public void revisedPublication() {
        System.out.print("Revisions... ");
    }

    public void submitting(Player player) {
        System.out.print(player.getName()+" is submitting...");
    }

    public boolean nextTest() {
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        String option;
        do {
            System.out.println("Continue the execution? [yes/no]: ");
            option = sc.nextLine();
            if (option.equals("yes") || option.equals("Yes")) {
                ok = true;
                return true;
            } else if (option.equals("no") || option.equals("No")) {
                ok = true;
                return false;
            } else {
                System.err.println("This answer is not available, please try again");
            }
        }while (!ok);
        return false;
    }

    public void editionEnded(Edition edition, Player winner) {
        System.out.println("THE TRIALS "+edition.getYear()+" HAVE ENDED - "+winner.getName()+" WON");
    }

    public boolean dataSelection() {
        Scanner sc = new Scanner(System.in);
        boolean ok = false, csvOn = false;
        String option;
        do{
            System.out.println("The IEEE needs to know where your allegiance lies.");
            System.out.println();
            System.out.println("    I) People's front of Engineering (CSV)");
            System.out.println("    II) Engineering People’s Front (JSON)");
            System.out.println();
            System.out.print("Pick a faction: ");
            option = sc.nextLine();
            if(option.equals("I")){
                csvOn = true;
                System.out.println();
                System.out.println("Loading data from CSV files...");
                System.out.println();
                ok = true;
            }else if(option.equals("II")){
                System.out.println();
                System.out.println("Loading data from JSON files...");
                System.out.println();
                ok = true;
            }else{
                System.out.println();
                System.err.println("This option is not available please try again");
                System.out.println();
            }
        }while(!ok);
        return csvOn;
    }

    public Test createDoctoralDefense() {
        String name, field,option;
        int diff;
        boolean ok ,aux;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println(" ");
            System.out.print("Enter the trial’s name: "); //TODO: supongo que mirar si no hay otro on el mismo nombre
            name = sc.nextLine();

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
        }while(!ok);
        return null;
    }

    public void deffensePassed(Player player) {
        System.out.println(player.getName()+" was successful. Congrats! PI count: "+player.getInvestigationPoints());
    }

    public void deffenseNotPassed(Player player) {
        System.out.println(player.getName()+" wasn't successful. Sorry... PI count: "+player.getInvestigationPoints());
    }

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
                aux = businessController.comprovaTest(name);// comprova si el nom esta repetit
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

                //aux = businessController.comprovaTest(master); Aqui falta mirar si el master esta be
                if(master.equals("")  ){
                    System.err.println("Please enter a correct master's name");
                    System.out.println(" ");
                }else{
                    ok = true;
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

    public void masterPassed(Player player){
        System.out.println(player.getName()+" was successful. Congrats! PI count: "+player.getInvestigationPoints());
    }

    public void masterNotPassed(Player player){
        System.out.println(player.getName()+" wasn't successful. Sorry... PI count: "+player.getInvestigationPoints());
    }

    public Test createBudgetRequest() {
        String name, entity,option;
        double budget;
        boolean ok,aux;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println(" ");
            System.out.print("Enter the trial’s name: "); // falta mirar que no es repetixi el nom
            name = sc.nextLine();
            System.out.print("Enter the entity’s name: ");
            entity = sc.nextLine();
            System.out.print("Enter the budget demanded: ");

            option = sc.nextLine();
            aux = businessController.isNumber(option);
            if(aux){
                budget = Integer.parseInt(option);
            }else{
                budget = 0;
            }

            if((budget < 1000 || budget > 2000000000)){
                ok = false;
            }else{
                ok = true;
            }

            if(!ok){
                System.err.println("The information is not valid, please try again");
            }else{
                System.out.println(" ");
                System.out.println("The trial was created successfully!");
                return new budgetRequest(name, entity, budget);
            }
        }while(!ok);
        return null;
    }

    public void BudgetPassed(Player player){
        System.out.println(player.getName()+" was successful. Congrats! PI count: "+player.getInvestigationPoints());
    }

    public void BudgetNotPassed(Player player){
        System.out.println(player.getName()+" wasn't successful. Sorry... PI count: "+player.getInvestigationPoints());
    }

    public void allPlayersDisc() {
        System.out.println("All player was disscualified, GAME OVER");
    }

    public void showPublication (Publication publication){
        System.out.println("Trial: "+publication.getName()+" (Paper publication)");
        System.out.println("Journal: "+publication.getNameMag()+" ("+publication.getQuartil()+")");
        System.out.println("Chances: "+publication.getAcceptanceProbability()+"% acceptance, "+publication.getRevisionProbability()+"% revision, "+ publication.getNotAcceptedProbability()+"% rejection");
    }

    public void showDoctoralDefense(doctoralDefense doctoralDefense) {
        System.out.println("Field: "+doctoralDefense.getField()+" (Doctoral Defense)");
        System.out.println("Difficulty: "+doctoralDefense.getDiff());
    }

    public void showBudgetRequest(budgetRequest budgetRequest){
        System.out.println("Entity: "+budgetRequest.getEntity());
        System.out.println("Quantity: "+budgetRequest.getQuantity());
    }

    public void showEstudyMaster(estudiMaster estudiMaster){
        System.out.println("Master: "+estudiMaster.getMaster()+"("+estudiMaster.getCredits()+") ["+estudiMaster.getProbability()+"%]");
    }


}
