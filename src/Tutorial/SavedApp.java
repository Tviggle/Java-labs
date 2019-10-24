package Tutorial;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class SavedApp implements Serializable {

    private static final long serialVersionUID = 1;

    Vector houses;
    private HashSet<Integer> ids;
    private Map<Integer,Integer> borns;
    public SavedApp(Vector T,HashSet E,Map U)
    {
        houses=T;
        ids=E;
        borns=U;
    }
    public void Loading()
    {
        Habitat.houses=this.houses;
        Habitat.ids=this.ids;
        Habitat.borns=this.borns;
    }
    public void adding()
    {
        Iterator<Object> hIterator= houses.iterator();
        while(hIterator.hasNext()) {
            Object current = hIterator.next();
            if (current instanceof Wood) {
                Wood nextwood = (Wood) current;
                nextwood.setBorn(Frame.t);
                Habitat.houses.add(nextwood);
                Habitat.ids.add(nextwood.id);
                Habitat.borns.put(nextwood.id,nextwood.born);
                nextwood.woods++;
                Frame.objs.add(nextwood.jl,0,0);
                }
            if (current instanceof Stone) {
                Stone nextstone = (Stone) current;
                nextstone.setBorn(Frame.t);
                Habitat.houses.add(nextstone);
                Habitat.ids.add(nextstone.id);
                Habitat.borns.put(nextstone.id,nextstone.born);
                nextstone.stones++;
                Frame.objs.add(nextstone.jl,0,0);
            }
            }
        Frame.objs.repaint();
    }
}
