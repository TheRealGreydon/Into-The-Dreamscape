package APCS.Achievements;

import APCS.Player;

public class firstSteps implements achievement 
{
    private boolean comp = false;
    private Player character;

    public firstSteps(Player character) {this.character = character;}

    public String getName() {return "First Steps";}

    public String getDis() {return "Beat the first level";}

    public String getId() {return "FS";}

    public boolean getComplete() {if(!comp) {if(character.getLevel()>=1) {comp=true;}} return comp;}

    public void setComp() {comp = true;}
}
