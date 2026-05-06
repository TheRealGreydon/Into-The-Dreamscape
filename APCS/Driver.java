package APCS;

public class Driver
{
    public static void main(String[] args)
    {
        Main m = new Main();

        String name="Jordan";
        int gend=0;
        int outfit=0;
        int fav=0;

        Player p = new Player(name, gend, outfit, fav);

        p.setName("NDefault");
    }
}
