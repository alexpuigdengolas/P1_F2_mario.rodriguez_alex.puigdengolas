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
}
