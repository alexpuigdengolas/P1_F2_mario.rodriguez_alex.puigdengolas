package presentation;

import business.BusinessController;
import business.Edition;
import business.Player;
import business.Test;
import business.role.Enginyer;
import business.tests.Publication;
import business.tests.budgetRequest;
import business.tests.doctoralDefense;
import business.tests.estudiMaster;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ViewUI {
    private ViewController viewController;
    private BusinessController businessController;

    /**
     * This method is the constructor of the controller
     *
     * @param viewController is the view controller that is calling this method
     */
    //Controller
    public ViewUI(ViewController viewController) {
        this.viewController = viewController;
    }


    public void presentationView(){
        System.out.println(" _____ _          _____      _       _");
        System.out.println("/__   \\ |__  ___ /__   \\_ __(_) __ _| |___");
        System.out.println("  / /\\| '_ \\/ _ \\  / /\\| '__| |/ _` | / __|");
        System.out.println(" / /  | | | | __/ / /  | |  | | (_| | \\__ \\");
        System.out.println(" \\/   |_| |_|\\___|\\/   |_|  |_|\\__,_|_|___/");
        System.out.println(" ");
        System.out.println("Welcome to The Trials. Who are you?");
        System.out.println(" ");
    }


    /**
     * This is the view that will make the user chose between the tow modes of execution of the code
     * @return returns a boolean that shows the mode of execution of the code
     */
    public String startView(){
        Scanner sc = new Scanner(System.in);
        System.out.println("    A) The Composer");
        System.out.println("    B) This year's Conductor");
        System.out.println(" ");
        System.out.print("Enter the role:  ");


        return sc.nextLine();
    }

    /**
     * This view is the literal first one to appear in execution that will allow us to chose the way
     * that the data will be read and written
     * @return the boolean that shows the way that the data will be written
     */
    public String dataSelection() {
        Scanner sc = new Scanner(System.in);

            System.out.println("The IEEE needs to know where your allegiance lies.");
            System.out.println();
            System.out.println("    I) People's front of Engineering (CSV)");
            System.out.println("    II) Engineering People’s Front (JSON)");
            System.out.println();
            System.out.print("Pick a faction: ");

        return sc.nextLine();
    }

    /**
     * This is the view of that makes the user chose the type of things that makes you chose between creating
     * editions or tests
     * @return an int representing the option selected
     */
    public String mainCompositorView() {

        Scanner sc = new Scanner(System.in);

        System.out.println(" ");
        System.out.println("    1) Manage Test's");
        System.out.println("    2) Manage Editions");
        System.out.println(" ");
        System.out.println("    3) Exit");
        System.out.print("Enter an option:  ");



        return sc.nextLine();
    }

    /**
     * This view will allow the user to manage the Trials and make possible to
     * create, delete and show all the trials
     * @return the option selected
     */
    public String manageTrialsView() {
        Scanner sc = new Scanner(System.in);

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


        return sc.nextLine();

    }

    /**
     * This view will appear if the we want to create a trial
     * @return the int that indicates witch type of trial we want to create
     */
    public String trialChoiceView() {

        Scanner sc = new Scanner(System.in);

        System.out.println("    --- Trial types ---");
        System.out.println(" ");
        System.out.println("1) Paper publication");
        System.out.println("2) Doctoral thesis defense");
        System.out.println("3) Master studies");
        System.out.println("4) Budget request");
        System.out.println(" ");
        System.out.print("Enter the test's type:  ");


        return sc.nextLine();
    }

    /**
     * This view will allow the user to manage the Editions and make possible to
     * create, delete, duplicate and show all the editions and the trials that they contain
     * @return the option selected
     */
    public String manageEditionView() {
        Scanner sc = new Scanner(System.in);

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
        return sc.nextLine();
    }


    /**
     * This view will be used when we execute an edition
     * @param year the current year
     */
    public void mainConductorView(int year) {

        System.out.println(" ");
        System.out.println("Entering execution mode...");

        System.out.println(" ");
        System.out.println("    --- The Trials " + year + " ---");
        System.out.println(" ");
    }

    /**
     * This view will be showed if the year is not available
     * @param year the year that we are analyzing
     */
    public void noEditionView(int year) {
        System.out.println(" ");
        System.out.println("Entering execution mode...");
        System.out.println(" ");
        System.out.println("No edition is defined for the current year ("+year+").");
        System.out.println(" ");
        System.out.println("Shutting down...");
    }

    /**
     * This view will indicate that the publication has been passed
     * @param player the player that has passed
     */
    public void acceptedPublication(Player player) {
        System.out.println("Accepted! PI count: "+player.getInvestigationPoints());
    }

    /**
     * This view will indicate that the publication hasn't been passed
     * @param player the player that hasn't passed
     */
    public void rejectedPublication(Player player) {
        System.out.println("Rejected! PI count: "+player.getInvestigationPoints());
    }

    /**
     * This view will indicate that the publication is being revised
     */
    public void revisedPublication() {
        System.out.print("Revisions... ");
    }

    /**
     * This view will get showed when the player is submitting
     * @param player the player that is submitting
     */
    public void submitting(Player player) {
        System.out.print(player.getName()+" is submitting...");
    }

    /**
     * this view will be showed if we want to get to the next test
     * @return a boolean if we want to execute the next test
     */
    public boolean nextTest() {
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        String option;
        do {
            System.out.println(" ");
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
                System.out.println(" ");
            }
        }while (!ok);
        return false;
    }

    /**
     * This will get showed if the edition has ended
     * @param edition current edition
     * @param winner the winner of the edition
     */
    public void editionEnded(Edition edition, Player winner) {
        if (winner != null) System.out.println("THE TRIALS "+edition.getYear()+" HAVE ENDED - "+winner.getName()+" WON");
        else System.out.println("In this edition we don't have any winner");
    }


    /**
     * This view will indicate that the defense has been passed
     * @param player the player that has passed
     */
    public void defensePassed(Player player) {
        System.out.println(player.getName()+" was successful. Congrats! PI count: "+player.getInvestigationPoints());
    }

    /**
     * This view will indicate that the defense hasn't been passed
     * @param player the player that hasn't passed
     */
    public void defenseNotPassed(Player player) {
        System.out.println(player.getName()+" wasn't successful. Sorry... PI count: "+player.getInvestigationPoints());
    }

    /**
     * This view will indicate that the master study has been passed
     * @param player the player that has passed
     */
    public void masterPassed(Player player, int totalEcts, int ectsPassed){
        System.out.println(player.getName()+" was successful with "+ectsPassed+"/"+totalEcts+". Congrats! PI count: "+player.getInvestigationPoints());
    }

    /**
     * This view will indicate that the master study hasn't been passed
     * @param player the player that hasn't passed
     */
    public void masterNotPassed(Player player, int totalEcts, int ectsPassed){
        System.out.println(player.getName()+" wasn't successful with "+ectsPassed+"/"+totalEcts+". PI count: "+player.getInvestigationPoints());
    }

    /**
     * This view will indicate that the budget request has been passed
     * @param players the group of players that has passed
     */
    public void BudgetPassed(List<Player> players){
        System.out.println("The research group got the budget!");
        for (Player player : players) {
            if(player.getClass().getSimpleName().equals("Doctor")) {
                System.out.println(player.getName() + ", PhD. PI count: " + player.getInvestigationPoints());
            }else{
                System.out.println(player.getName() + ". PI count: " + player.getInvestigationPoints());
            }
        }
    }

    /**
     * This view will indicate that the budget request hasn't been passed
     * @param players the group of players that hasn't passed
     */
    public void BudgetNotPassed(List<Player> players){
        System.out.println("The research group didn't got the budget");
        for (Player player : players) {
            if(player.getClass().getSimpleName().equals("Doctor")) {
                System.out.println(player.getName() + ", PhD. PI count: " + player.getInvestigationPoints());
            }else{
                System.out.println(player.getName() + ". PI count: " + player.getInvestigationPoints());
            }
        }
    }

    /**
     * This view will get seen if all the players are eliminated
     */
    public void allPlayersDisc() {
        System.out.println("All player was disscualified, GAME OVER");
    }

    /**
     * The view that shows the publication
     * @param publication the publication that we want to show
     */
    public void showPublication (Publication publication){
        System.out.println("Trial: "+publication.getName()+" (Paper publication)");
        System.out.println("Journal: "+publication.getNameMag()+" ("+publication.getQuartil()+")");
        System.out.println("Chances: "+publication.getAcceptanceProbability()+"% acceptance, "+publication.getRevisionProbability()+"% revision, "+ publication.getNotAcceptedProbability()+"% rejection");
    }

    /**
     * The view that shows the doctoralDefense
     * @param doctoralDefense the doctoral defense that we want to show
     */
    public void showDoctoralDefense(doctoralDefense doctoralDefense) {
        System.out.println("Field: "+doctoralDefense.getField()+" (Doctoral Defense)");
        System.out.println("Difficulty: "+doctoralDefense.getDiff());
    }

    /**
     * The view that shows the budgetRequest
     * @param budgetRequest the budget request that we want to show
     */
    public void showBudgetRequest(budgetRequest budgetRequest){
        System.out.println("Entity: "+budgetRequest.getEntity());
        System.out.println("Quantity: "+budgetRequest.getQuantity());
    }

    /**
     * The view that shows the studyMaster
     * @param estudiMaster the study master that we want to show
     */
    public void showEstudyMaster(estudiMaster estudiMaster){
        System.out.println("Master: "+estudiMaster.getMaster()+"("+estudiMaster.getCredits()+") ["+estudiMaster.getProbability()+"%]");
    }

    /**
     * This method will show all the information of the edition
     * @param edition the edition we want to show
     */
    public void showEditionInformation(Edition edition){
        System.out.println("Year: "+ edition.getYear());
        System.out.println("Players: "+ edition.getInitialPlayers());
        System.out.println("Trials: ");
        for (int i = 0; i < edition.getTests().size(); i++) {
            System.out.println("    " + (i + 1) + "- The Trials " + edition.getTests().get(i).getName() +" ("+edition.getTests().get(i).getClass().getSimpleName()+")");
        }
    }

    /**
     * This view will show when shutting down
     */
    public void shutDown() {
        System.out.println(" ");
        System.out.println("Shutting down...");
    }

    /**
     * This will show if the year already exists
     */
    public void yearErrorChoseAgain() {
        System.err.printf("There is already an edition this year, please choose again:");
        System.out.println(" ");
    }

    /**
     * This will show if the year is too big
     */
    public void reallyBigYearError() {
        System.err.println("The year of the edition must be equal or greater than the current one(2022)");
        System.out.println("");
    }

    /**
     * This will show if the player is eliminated
     * @param player the player that has been eliminated
     */
    public void playerEliminated(Player player){
        System.out.println(player.getName() + " was eliminated!");
    }

    public void showCurrentTest(Test test, int i) {
        System.out.println(" ");
        System.out.println("Trial #"+(i+1)+" - "+test.getName());
        System.out.println(" ");
    }

    /**
     * Shows a message into console with a jump line
     *
     * @param message to show
     */
    public void showMessageln(String message) {
        System.out.println(message);
    }

    /**
     * Shows a message into console
     *
     * @param message to show
     */
    public void showMessage(String message) {
        System.out.print(message);
    }
    /**
     * Shows a message error into console with a jump line
     *
     * @param message to show
     */
    public void showErrorMessageln(String message) {
        System.err.println(message);
    }

    /**
     * Make a empty line
     */
    public void makeLine() {
        System.out.println(" ");
    }

    /**
     * Show the editions
     *  @param editions is all the editions that we have saved
     */
    public void showEditions(List<Edition> editions){
        for (int i = 0; i < editions.size(); i++) {
            System.out.println("    " + (i + 1) + ") The Trials " + editions.get(i).getYear());
        }
        System.out.println(" ");
        System.out.println("    " + (editions.size() + 1) + ") Back");
        System.out.println(" ");
    }

    /**
     * Show all the trials
     * @param tests is all the editions that we have saved
     */
    public void showTrials(LinkedList<Test> tests){
        for(int i = 0; i < tests.size(); i++){
            System.out.println("    "+ (i+1) +") "+ tests.get(i).getName());
        }
        System.out.println(" ");
        System.out.println("    "+ (tests.size()+1) +") Back");
        System.out.println(" ");
        System.out.print("Enter an option: ");
    }


}
