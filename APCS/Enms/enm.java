package APCS.Enms;

public class enm 
{
    private String name;
    private int type;
    private int lvl;
    private int hp;
    public enm(String name, int type, int lvl) {this.name = name; this.type = type; this.lvl = lvl;hp = 8 + (lvl*2);}

    public String getName() {return name;}

    public int getHp() {return hp;}

    public void doHp(int x) {if(hp>0){hp += x;}}

    public int getLvl() {return lvl;}
    
    public int getType() {return type;}

    public void setName(String x) {name = x;}
    
    public void setLvl(int x) {lvl = x;}

    public void setType(int x) {type = x;}
}
