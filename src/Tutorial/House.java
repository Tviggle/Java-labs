package Tutorial;

import javax.swing.*;
import java.util.Random;

public abstract class House extends JPanel implements IBehaviour
{

    int x,y,timelife,born,id;
    int i1,i2;
    JLabel jl;
    boolean direction1,direction2;
    @Override
    public void movexy() {}
    @Override
    public void setx(int x) {this.x = x;}
    @Override
    public void sety(int y) {this.y = y;}
    @Override
    public int getx() {return this.x;}
    @Override
    public int gety() {return this.y;}
    public void setBorn(int t)
    {
        this.born=t;
    }
    public void setTimelife(int h)
    {
        this.timelife=h;
    }
    public boolean isDead(int t)
    {
        if((t-this.born)==this.timelife)return true;
        else return false;
    }
    int getId()
    {
        return this.id;
    }
    public void setId()
    {
        Random random=new Random();
        x=random.nextInt(900000)+100000;
        if(Habitat.ids.contains(x))
            setId();
        else this.id=x;
    }
}
