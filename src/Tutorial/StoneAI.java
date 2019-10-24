package Tutorial;

import java.util.Iterator;

public class StoneAI extends BaseAI {
    synchronized void mover() {
        Iterator<Object> hIterator = Habitat.houses.iterator();
        while (hIterator.hasNext()) {
                Object current = hIterator.next();
                if (current instanceof Stone) {
                    Stone nextstone = (Stone) current;
                    nextstone.movexy(V);
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

