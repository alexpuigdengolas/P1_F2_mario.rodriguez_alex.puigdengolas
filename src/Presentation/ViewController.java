package Presentation;

import Business.Publication;
import Business.Test;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class ViewController {

    public boolean startView(){
        Scanner sc = new Scanner(System.in);
        String option;
        boolean ok = false;

        System.out.println(" _____ _          _____      _       _");
        System.out.println("/__   \\ |__  ___ /__   \\_ __(_) __ _| |___");
        System.out.println("  / /\\/ '_ \\/ _ \\  / /\\/ '__| |/ _` | / __|");
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
            System.out.println(" ");
            System.out.print("Enter the test's type:  ");
            option = sc.nextLine();

            if(option.equals("1")){
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
}
