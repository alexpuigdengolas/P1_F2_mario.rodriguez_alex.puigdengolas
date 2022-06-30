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
        return viewUI.startView();
    }

    /**
     * This view is the literal first one to appear in execution that will allow us to chose the way
     * that the data will be read and written
     * @return the boolean that shows the way that the data will be written
     */
    public boolean dataSelection() {
        return viewUI.dataSelection();
    }

    /**
     * This is the view of that makes the user chose the type of things that makes you chose between creating
     * editions or tests
     * @return an int representing the option selected
     */
    public int mainCompositorView() {
        return viewUI.mainCompositorView();
    }

    /**
     * This view will allow the user to manage the Trials and make possible to
     * create, delete and show all the trials
     * @return the option selected
     */
    public String manageTrialsView() {
        return viewUI.manageTrialsView();
    }

    /**
     * This view will appear if the we want to create a trial
     * @return the int that indicates witch type of trial we want to create
     */
    public int trialChoiceView() {
        return viewUI.trialChoiceView();
    }

    /**
     * This view will show when we create a Paper publication
     * @return the paper publication we all created
     */
    public Publication createPaperPublication() {
        return viewUI.createPaperPublication();
    }

    /**
     * This view will show the user all the trials already existing
     * @param tests is all the tests that we have saved
     */
    public void trialChoiceShowView(LinkedList<Test> tests){
        viewUI.trialChoiceShowView(tests);
    }

    /**
     * This view will show to the user when he wants to delete a test
     * @param tests is all the tests that we have saved
     */
    public void trialChoiceDeleteView(LinkedList<Test> tests, List<Edition> editions) {
        viewUI.trialChoiceDeleteView(tests, editions);
    }

    /**
     * This view will allow the user to manage the Editions and make possible to
     * create, delete, duplicate and show all the editions and the trials that they contain
     * @return the option selected
     */
    public String manageEditionView() {
        return viewUI.manageEditionView();
    }

    /**
     * This view will show when we create an Edition
     * @param tests is all the tests that we have saved
     * @param editions is all the editions that we have saved
     */
    public void createEditionView(List<Test> tests, List<Edition> editions) {
        viewUI.createEditionView(tests, editions);
    }

    /**
     * This view will show when we want to see the Editions
     * @param editions is all the editions that we have saved
     */
    public void showEditionView(List<Edition> editions) {
        viewUI.showEditionView(editions);
    }

    /**
     * This view will show when we want to duplicate an Edition
     * @param editions is all the editions that we have saved
     */
    public void duplicateEdition(List<Edition> editions) {
        viewUI.duplicateEdition(editions);
    }

    /**
     * This view will show when we want to delete an Edition
     * @param editions is all the editions that we have saved
     */
    public void deleteEditionView(List<Edition> editions) {
        viewUI.deleteEditionView(editions);
    }

    /**
     * This view will be used when we execute an edition
     * @param edition the current edition
     * @param year the current year
     */
    public void mainConductorView(Edition edition, int year) {
        viewUI.mainConductorView(edition, year);
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
        return viewUI.createDoctoralDefense();
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
        return viewUI.createMasterEstudy();
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
        return viewUI.createBudgetRequest();
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
        viewUI.BudgetPassed(players);
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

    public void showCurrentTest(Test test, int i) {
        viewUI.showCurrentTest(test, i);
    }
}
