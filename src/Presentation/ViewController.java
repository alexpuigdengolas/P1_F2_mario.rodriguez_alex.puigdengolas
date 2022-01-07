package Presentation;

import Business.*;

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
            System.out.println(" ");
            System.out.print("Enter the test's type:  ");
            option = sc.nextLine();

            if(option.equals("1") || option.equals("2")){
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
        String name, nameMag, quartil;
        int acceptanceProbability, revisionProbability, notAcceptedProbability;
        boolean ok = false;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println(" ");
            System.out.print("Enter the trial’s name: ");
            name = sc.nextLine();
            System.out.print("Enter the journal’s name: ");
            nameMag = sc.nextLine();
            System.out.print("Enter the journal’s quartile: ");
            quartil = sc.nextLine();
            if(!quartil.equals("Q1") && !quartil.equals("Q2") && !quartil.equals("Q3") && !quartil.equals("Q4")){
                ok = false;
            }else{
                ok = true;
            }
            System.out.print("Enter the acceptance probability: ");
            acceptanceProbability = sc.nextInt();
            sc.nextLine();
            if((acceptanceProbability < 0 || acceptanceProbability > 100) && ok){
                ok = false;
            }else{ok = true;}
            System.out.print("Enter the revision probability: ");
            revisionProbability = sc.nextInt();
            sc.nextLine();
            if((revisionProbability < 0 || revisionProbability > 100) && ok){
                ok = false;
            }else{ok = true;}
            System.out.print("Enter the rejection probability: ");
            notAcceptedProbability = sc.nextInt();
            sc.nextLine();
            if((notAcceptedProbability < 0 || notAcceptedProbability > 100) && ok){
                ok = false;
            }else{ok = true;}

            if(!ok){
                System.err.println("The information is not valid, please try again");
            }else{
                System.out.println(" ");
                System.out.println("The trial was created successfully!");
                return new Publication(name, nameMag, quartil, acceptanceProbability, revisionProbability, notAcceptedProbability);
            }
        }while(!ok);
        return null;
    }

    public void trialChoiceShowView(LinkedList<Test> tests){
        boolean ok = false;
        String option;
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
                option = sc.nextLine();

                if(Integer.parseInt(option) > tests.size()+1 || Integer.parseInt(option) < 1){
                    System.err.println("The option is not available, please try again");
                }else if(Integer.parseInt(option) != tests.size()+1){
                    System.out.println(" ");
                    tests.get(Integer.parseInt(option)-1).showInfo();
                    System.out.println(" ");
                }else{
                    ok = true;
                }
            }
        }while(!ok);
    }

    public void trialChoiceDeleteView(LinkedList<Test> tests) {
        boolean ok = false;
        String option, confirmation;
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
                option = sc.nextLine();

                if(Integer.parseInt(option) > tests.size()+1 || Integer.parseInt(option) < 1){
                    System.err.println("The option is not available, please try again");
                }else if(Integer.parseInt(option) != tests.size()+1){
                    System.out.println(" ");
                    System.out.print("Enter the trial’s name for confirmation: ");
                    confirmation = sc.nextLine();
                    if (confirmation.equals(tests.get(Integer.parseInt(option) - 1).getName())) {
                        System.out.println(" ");
                        tests.remove(Integer.parseInt(option)-1);
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

    public void createEditionView(LinkedList<Test> tests, List<Edition> editions) {
        boolean ok = false;
        Scanner sc = new Scanner(System.in);
        int year, initialPlayers, numTest, rounds = 0, trialSelect;
        LinkedList<Test> editionTests = new LinkedList<Test>();
        LinkedList<Player> players = new LinkedList<Player>();

        do {
            System.out.println(" ");
            System.out.print("Enter the edition's year: ");
            year = sc.nextInt();
            sc.nextLine();
            if(businessController.checkEditionsYear(year)){
               ok = true;
            }
            System.out.print("Enter the initial number of players: ");
            initialPlayers = sc.nextInt();
            sc.nextLine();
            if((initialPlayers < 1 || initialPlayers > 5) && ok){
                ok = true;
            }
            System.out.print("Enter the number of trials: ");
            numTest = sc.nextInt();
            sc.nextLine();
            if((numTest < 3 || numTest > 12) && ok){
                ok = true;
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
                    System.out.print("Pick a trial (" + (i + 1) + "/"+ numTest +"):");
                    trialSelect = sc.nextInt();
                    sc.nextLine();
                    if (trialSelect > tests.size() + 1 || trialSelect < 1) {
                        System.err.println("The option is not available, please try again");
                    } else if (trialSelect != tests.size() + 1) {
                        editionTests.add(tests.get(trialSelect - 1));
                    }
                }
                if (ok) {
                    for(int i = 0; i < initialPlayers; i++){
                        players.add(new Player());
                    }
                    editions.add(new Edition(year, initialPlayers, numTest, rounds, editionTests, players));
                }
            }
        }while(!ok);
    }

    public void showEditionView(List<Edition> editions) {
        boolean ok = false;
        Scanner sc = new Scanner(System.in);
        int option;
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
                option = sc.nextInt();
                sc.nextLine();
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
        boolean ok = false;
        Scanner sc = new Scanner(System.in);
        int option, year, initialPlayers;
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
                option = sc.nextInt();
                sc.nextLine();
                copyEdition = editions.get(option - 1);
                if (option > editions.size() + 1 || option < 1) {
                    System.out.println(" ");
                    System.err.println("The option is not available, please try again");
                    System.out.println(" ");
                } else if (option != editions.size() + 1) {
                    System.out.println(" ");
                    System.out.print("Enter the new edition's year: ");
                    year = sc.nextInt();
                    sc.nextLine();
                    if (businessController.checkEditionsYear(year)) {
                        ok = true;
                    } else {
                        ok = false;
                    }
                    System.out.print("Enter the new edition’s initial number of players: ");
                    initialPlayers = sc.nextInt();
                    sc.nextLine();
                    if ((initialPlayers < 1 || initialPlayers > 5) && ok) {
                        ok = false;
                    } else {
                        ok = true;
                    }
                    System.out.println(" ");
                    if (ok) {
                        ok = true;
                        editions.add(new Edition(year, initialPlayers, copyEdition.getNumTest(), copyEdition.getRounds(), copyEdition.getTests(), copyEdition.getPlayers()));
                    }

                } else {
                    ok = true;
                }
            }
        }while(!ok);
    }

    public void deleteEditionView(List<Edition> editions) {
        boolean ok = false;
        Scanner sc = new Scanner(System.in);
        int option, year, initialPlayers;
        Edition copyEdition;

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
                option = sc.nextInt();
                sc.nextLine();
                if (option > editions.size() + 1 || option < 1) {
                    System.out.println(" ");
                    System.err.println("The option is not available, please try again");
                    System.out.println(" ");
                } else if (option != editions.size() + 1) {
                    System.out.println(" ");
                    System.out.print("Enter the edition’s year for confirmation: ");
                    year = sc.nextInt();
                    sc.nextLine();
                    if (year == editions.get(option - 1).getYear()) {
                        editions.remove(option - 1);
                        System.out.println("The edition was successfully deleted.");
                        ok = true;
                    } else {
                        System.err.println("The edition wasn't successfully deleted.");
                        ok = true;
                    }
                }
            }
        }while(!ok);
    }

    //Conductor
    public void mainConductorView(Edition edition, int year) {
        String name;
        Scanner sc = new Scanner(System.in);

        System.out.println(" ");
        System.out.println("Entering execution mode...");

        System.out.println(" ");
        System.out.println("    --- The Trials "+ year +" ---");
        System.out.println(" ");
        for(int i = 0; i < edition.getInitialPlayers(); i++){
            System.out.print("Enter the player's name ("+(i+1)+"/"+edition.getInitialPlayers()+"): ");
            name = sc.nextLine();
            edition.getPlayers().get(i).setName(name);
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
        String name, field;
        int diff;
        boolean ok = false;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println(" ");
            System.out.print("Enter the trial’s name: ");
            name = sc.nextLine();
            System.out.print("Enter the thesis field of study: ");
            field = sc.nextLine();
            System.out.print("Enter the defense difficulty: ");
            diff = sc.nextInt();
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
}
