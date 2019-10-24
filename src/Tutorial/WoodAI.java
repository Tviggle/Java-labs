package Tutorial;

import java.util.Iterator;

public class WoodAI extends BaseAI {
    synchronized void mover() {
        Iterator<Object> hIterator = Habitat.houses.iterator();
        while (hIterator.hasNext()) {
                Object current = hIterator.next();
                if (current instanceof Wood) {
                    Wood nextwood = (Wood) current;
                    nextwood.movexy(V);
                }
        }
    }
    synchronized public void wakeup()
    {
        synchronized (Wood.class)
        {
            notify();
        }
    }
}
