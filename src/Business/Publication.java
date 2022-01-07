package Business;

public class Publication extends Test{
    private String nameMag;
    private String quartil;
    private int acceptanceProbability;
    private int revisionProbability;
    private int notAcceptedProbability;

    public Publication(String name, String nameMag, String quartil, int acceptanceProbability, int revisionProbability, int notAcceptedProbability) {
        super(name);
        this.nameMag = nameMag;
        this.quartil = quartil;
        this.acceptanceProbability = acceptanceProbability;
        this.revisionProbability = revisionProbability;
        this.notAcceptedProbability = notAcceptedProbability;
    }

    public Publication(String name) {
        super(name);
    }

    public String getNameMag() {
        return nameMag;
    }

    public void setNameMag(String nameMag) {
        this.nameMag = nameMag;
    }

    public String getQuartil() {
        return quartil;
    }

    public void setQuartil(String quartil) {
        this.quartil = quartil;
    }

    public int getAcceptanceProbability() {
        return acceptanceProbability;
    }

    public void setAcceptanceProbability(int acceptanceProbability) {
        this.acceptanceProbability = acceptanceProbability;
    }

    public int getRevisionProbability() {
        return revisionProbability;
    }

    public void setRevisionProbability(int revisionProbability) {
        this.revisionProbability = revisionProbability;
    }

    public int getNotAcceptedProbability() {
        return notAcceptedProbability;
    }

    public void setNotAcceptedProbability(int notAcceptedProbability) {
        this.notAcceptedProbability = notAcceptedProbability;
    }

    public void showInfo(){
        System.out.println("Trial: "+getName()+" (Paper publication)");
        System.out.println("Journal: "+getNameMag()+" ("+getQuartil()+")");
        System.out.println("Chances: "+getAcceptanceProbability()+"% acceptance, "+getRevisionProbability()+"% revision, "+ getNotAcceptedProbability()+"% rejection");
    }

    @Override
    public void execute(Test test, Edition edition) {
        super.execute(test, edition);
        Publication publication = (Publication) test;
        String quartil = publication.getQuartil();
        try {
            for (int i = 0; i < edition.getPlayers().size(); i++) {
                //TODO Tener en cuenta probabiulidades de ejecución (No esta bien explicado en el enunciado)
                boolean result = false;
                edition.getBusinessController().getViewController().submitting(edition.getPlayers().get(i));
                while (!result) {
                    double numAcceptance = Math.random() * 100;
                    double numRejected = Math.random() * 100;
                    double numRevision = Math.random() * 100;
                    if (Math.abs(numAcceptance - publication.getAcceptanceProbability()) > Math.abs(numRejected - publication.getNotAcceptedProbability()) && Math.abs(numAcceptance - publication.getAcceptanceProbability()) > Math.abs(numRevision - publication.getRevisionProbability())) {
                        getReward(test, edition.getPlayers().get(i));
                        edition.getBusinessController().getViewController().acceptedPublication(edition.getPlayers().get(i));
                        result = true;
                    } else {
                        if (Math.abs(numAcceptance - publication.getAcceptanceProbability()) < Math.abs(numRejected - publication.getNotAcceptedProbability()) && Math.abs(numRejected - publication.getNotAcceptedProbability()) > Math.abs(numRevision - publication.getRevisionProbability())) {
                            getPenalitation(test, edition, i);
                            edition.getBusinessController().getViewController().rejectedPublication(edition.getPlayers().get(i));
                            result = true;
                        } else {
                            edition.getBusinessController().getViewController().revisedPublication();
                        }
                    }
                }
            }
        }catch (Exception ignored){

        }
    }

    @Override
    void getReward(Test test, Player player) {
        super.getReward(test, player);
        Publication publication = (Publication) test;
        quartil = publication.getQuartil();
        switch (quartil){
            case ("Q1"):
                if (player.getRole().equals("Enginyer") || player.getRole().equals("Màster")) {
                    player.setInvestigationPoints(player.getInvestigationPoints() + 4);
                    player.checkRole();
                }else if(player.getRole().equals("Doctor")){
                    player.setInvestigationPoints(player.getInvestigationPoints() + (4 * 2));
                }
                break;

            case ("Q2"):
                if (player.getRole().equals("Enginyer") || player.getRole().equals("Màster")) {
                    player.setInvestigationPoints(player.getInvestigationPoints() + 3);
                    player.checkRole();
                }else if(player.getRole().equals("Doctor")){
                    player.setInvestigationPoints(player.getInvestigationPoints() + (3 * 2));
                }
                break;

            case ("Q3"):
                if (player.getRole().equals("Enginyer") || player.getRole().equals("Màster")) {
                    player.setInvestigationPoints(player.getInvestigationPoints() + 2);
                    player.checkRole();
                }else if(player.getRole().equals("Doctor")){
                    player.setInvestigationPoints(player.getInvestigationPoints() + (2 * 2));
                }
                break;

            case ("Q4"):
                if (player.getRole().equals("Enginyer") || player.getRole().equals("Màster")) {
                    player.setInvestigationPoints(player.getInvestigationPoints() + 1);
                    player.checkRole();
                }else if(player.getRole().equals("Doctor")){
                    player.setInvestigationPoints(player.getInvestigationPoints() + 2);
                }
                break;
        }
    }

    @Override
    void getPenalitation(Test test, Edition edition, int playerIterarion) {
        super.getPenalitation(test, edition, playerIterarion);
        Publication publication = (Publication) test;
        quartil = publication.getQuartil();
        switch (quartil) {
            case ("Q1") -> {
                if (edition.getPlayers().get(playerIterarion).getRole().equals("Enginyer")) {
                    edition.getPlayers().get(playerIterarion).setInvestigationPoints(edition.getPlayers().get(playerIterarion).getInvestigationPoints() - 5);
                } else if (edition.getPlayers().get(playerIterarion).getRole().equals("Màster") || edition.getPlayers().get(playerIterarion).getRole().equals("Doctor")) {
                    edition.getPlayers().get(playerIterarion).setInvestigationPoints(edition.getPlayers().get(playerIterarion).getInvestigationPoints() - 2);
                }
                edition.removePlayer(edition, playerIterarion);
            }
            case ("Q2") -> {
                if (edition.getPlayers().get(playerIterarion).getRole().equals("Enginyer")) {
                    edition.getPlayers().get(playerIterarion).setInvestigationPoints(edition.getPlayers().get(playerIterarion).getInvestigationPoints() - 4);
                } else if (edition.getPlayers().get(playerIterarion).getRole().equals("Màster") || edition.getPlayers().get(playerIterarion).getRole().equals("Doctor")) {
                    edition.getPlayers().get(playerIterarion).setInvestigationPoints(edition.getPlayers().get(playerIterarion).getInvestigationPoints() - 2);
                }
                edition.removePlayer(edition, playerIterarion);
            }
            case ("Q3") -> {
                if (edition.getPlayers().get(playerIterarion).getRole().equals("Enginyer")) {
                    edition.getPlayers().get(playerIterarion).setInvestigationPoints(edition.getPlayers().get(playerIterarion).getInvestigationPoints() - 3);
                } else if (edition.getPlayers().get(playerIterarion).getRole().equals("Màster") || edition.getPlayers().get(playerIterarion).getRole().equals("Doctor")) {
                    edition.getPlayers().get(playerIterarion).setInvestigationPoints(edition.getPlayers().get(playerIterarion).getInvestigationPoints() - 1);
                }
                edition.removePlayer(edition, playerIterarion);
            }
            case ("Q4") -> {
                if (edition.getPlayers().get(playerIterarion).getRole().equals("Enginyer")) {
                    edition.getPlayers().get(playerIterarion).setInvestigationPoints(edition.getPlayers().get(playerIterarion).getInvestigationPoints() - 2);
                } else if (edition.getPlayers().get(playerIterarion).getRole().equals("Màster") || edition.getPlayers().get(playerIterarion).getRole().equals("Doctor")) {
                    edition.getPlayers().get(playerIterarion).setInvestigationPoints(edition.getPlayers().get(playerIterarion).getInvestigationPoints() - 1);
                }
                edition.removePlayer(edition, playerIterarion);
            }
        }
    }
}
