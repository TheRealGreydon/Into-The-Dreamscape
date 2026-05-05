package APCS;

public class Main
{
    public static void main(String[] args)
    {
        String name="Default";
        int gend=0;
        int outfit=0;
        int fav=1;

        Player p = new Player(name, gend, outfit, fav);

        p.setName("NDefault");
        System.out.println(p.toString());
    }
}
