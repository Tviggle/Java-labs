package Tutorial;

public class Starter {
    static Creator creat=new Creator();
    static WoodAI wo=new WoodAI();
    static StoneAI st=new StoneAI();
    static void run()
    {
        st.start();
        creat.start();
        wo.start();
        st.setPriority(1);
        wo.setPriority(1);
    }
}
