package Tutorial;

public abstract class BaseAI extends Thread {
    boolean moving=false;
    long prev;
    int V=400;
    boolean stop=false;

    synchronized public void run()
    {
        while(true)
        {
            if(this.stop)
            {
                try{
                    this.wait();
                }
                catch (InterruptedException e){}
            }
            if(this.moving)
            {
                long step=Frame.time-prev;
                if(step>=20)
                {
                    this.mover();
                    this.prev = Frame.time;
                }
            }
            try{
                Thread.sleep(1);
            }
            catch (InterruptedException e){}

        }
    }
    void mover()
    { }
}
