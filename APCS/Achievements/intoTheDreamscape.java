package APCS.Achievements;

public class intoTheDreamscape implements achievement 
{
    private boolean comp = true;
    
    public String getName() {return "Into the Dreamscape";}

    public String getDis() {return "Venture into the Dreamscape for the first time";}

    public String getId() {return "ITD";}

    public boolean getComplete() {return comp;}

    public void setComp() {comp = true;}
}
