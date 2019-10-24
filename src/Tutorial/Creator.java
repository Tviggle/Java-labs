package Tutorial;

public class Creator extends Thread {
    long prev;
    synchronized public void run() {
        while(true)
        {
            Frame.time_simulation();
            if(Frame.simulation==true)
            {
                Frame.time=System.currentTimeMillis()-Frame.start;
                long step=Frame.time-prev;
                if(step>=1000)
                {
                    Frame.t = (int) (Frame.time / 1000);
                    Frame.habitat.update(Frame.t);
                    this.prev = Frame.time;
                }
            }
            try{
                Thread.sleep(10);
            }
            catch (InterruptedException e){}
        }
    }
}
