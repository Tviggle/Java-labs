package Tutorial;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class Stone extends House {
    static int stones=0;

    ImageIcon img =new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/stone.jpg")).getImage()
            .getScaledInstance(50,50, Image.SCALE_DEFAULT));
    Stone(int x, int y)
    {
        jl=new JLabel();
        jl.setBounds(x,y,img.getIconWidth(),img.getIconHeight());
        jl.setIcon(img);
        Frame.objs.add(jl,0,0);
        Frame.objs.repaint();
        setId();
        setx(x);
        sety(y);
    }

    public void setcoords()
    {
        Random random = new Random();
        int i = random.nextInt(401);
        i1=i;
        i=random.nextInt(451);
        i2=i;
    }
    public void movexy(int V) {
        if (x > i1) direction1 = true;
        else direction1 = false;
        if (direction1) {
            sety(y);
            setx(x - (V/50));
            jl.setBounds(x, y, img.getIconWidth(), img.getIconHeight());
            Frame.objs.repaint();
        } else {
            if (y > i2) direction2 = true;
            else direction2 = false;
            if (direction2) {
                sety(y - (V/50));
                setx(x);
                jl.setBounds(x, y, img.getIconWidth(), img.getIconHeight());
                Frame.objs.repaint();
            }
        }
    }
}
