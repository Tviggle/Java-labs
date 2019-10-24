package Tutorial;

import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Habitat {

    volatile static Vector houses=new Vector();
    static HashSet<Integer> ids=new HashSet<Integer>();
    static Map<Integer,Integer> borns=new TreeMap<Integer,Integer>();
    static int N1,N2,P1,P2;
    static int K1,K2;
    private ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource(
            "/screen.jpg")).getImage().getScaledInstance(900,1000, Image.SCALE_DEFAULT));

    Habitat()
        {
            JLabel tmp = new JLabel();
            tmp.setBounds( 0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
            tmp.setIcon(imageIcon);
            Frame.objs.add(tmp,0,0);
            Frame.objs.repaint();
        }
        void update(int t)
        {
            synchronized (houses) {
                Random random = new Random();
                if (t % N1 == 0) {
                    int i = random.nextInt(101);
                    if (i <= P1) {
                        int x = random.nextInt(601) + 250;
                        int y = random.nextInt(851);
                        Wood wood = new Wood(x, y);
                        wood.setcoords();
                        wood.setBorn(t);
                        wood.setTimelife(K1);
                        ids.add(wood.id);
                        borns.put(wood.id, wood.born);
                        houses.add(wood);
                        Wood.woods++;
                    }
                }
                if (t % N2 == 0) {
                    int j = random.nextInt(101);
                    if (j <= P2) {
                        int x = random.nextInt(601) + 250;
                        int y = random.nextInt(851);
                        Stone stone = new Stone(x, y);
                        stone.setcoords();
                        stone.setBorn(t);
                        stone.setTimelife(K2);
                        ids.add(stone.id);
                        borns.put(stone.id, stone.born);
                        houses.add(stone);
                        Stone.stones++;
                    }
                }
                dying(t);
            }
    }
    void dying(int t)
    {
        Iterator<Object> hIterator= houses.iterator();
        while(hIterator.hasNext()) {
                Object current = hIterator.next();
                if (current instanceof Wood) {
                    Wood nextwood = (Wood) current;
                    if (nextwood.isDead(t)) {
                        Frame.objs.remove(nextwood.jl);
                        ids.remove(nextwood.getId());
                        borns.remove(nextwood.getId());
                        hIterator.remove();
                        Wood.woods--;
                    }
                }

                if (current instanceof Stone) {
                    Stone nextstone = (Stone) current;
                    if (nextstone.isDead(t)) {
                        Frame.objs.remove(nextstone.jl);
                        ids.remove(nextstone.getId());
                        borns.remove(nextstone.getId());
                        hIterator.remove();
                        Stone.stones--;
                    }
                }
            }
    }

    static void currentobject(long t)
    {
        String currentObjects[]=new String[Habitat.houses.capacity()];
        int k=0;
        Iterator<Map.Entry<Integer, Integer>> iIterator = borns.entrySet().iterator();
        while(iIterator.hasNext())
        {
            Map.Entry<Integer,Integer> kv= iIterator.next();
            int id = kv.getKey();
            int born=kv.getValue();
            currentObjects[k++]=("Тип "+type(kv.getKey())+" "+id+" "+born );
        }
        JList<String> jlst = new JList<String>(currentObjects);
        JScrollPane jscrl = new JScrollPane(jlst);
        int j=JOptionPane.showConfirmDialog(Frame.frame, jscrl,"Живые",JOptionPane.OK_CANCEL_OPTION);
        if(j==JOptionPane.OK_OPTION)
        {
            Frame.habitat.clear();
            Frame.btStop.setEnabled(false);
            Frame.btStart.setEnabled(true);
            Frame.time=0;

        }
        else
        {
            Frame.start=Frame.start+(System.currentTimeMillis()-t);
            Frame.simulation=true;
        }
        Frame.frame.requestFocus();
    }
    static String type(int i) {
        Iterator<Object> hIterator = houses.iterator();
        while (hIterator.hasNext()) {
            Object current = hIterator.next();

            if (current instanceof Wood) {
                Wood nextwood = (Wood) current;
                if (nextwood.getId() == i) return "Wood";
            } else if (current instanceof Stone) {
                Stone nextstone = (Stone) current;
                if (nextstone.getId() == i) return "Stone";
            }
        }
        return "";
    }
    void resume()
    {
        Iterator<Object> hIterator= houses.iterator();
        while(hIterator.hasNext()) {
            Object current = hIterator.next();
            if (current instanceof Wood) {
                Wood nextwood = (Wood) current;
                nextwood.woods++;
                nextwood.setBorn(0);
                Frame.objs.add(nextwood.jl,0,0);
            }
                else
            {
                Stone nextstone = (Stone) current;
                nextstone.stones++;
                nextstone.setBorn(0);
                Frame.objs.add(nextstone.jl,0,0);
            }
        }
        Iterator<Map.Entry<Integer, Integer>> iIterator = borns.entrySet().iterator();
        while(iIterator.hasNext()) {
            Map.Entry<Integer, Integer> kv = iIterator.next();
            kv.setValue(0);
        }
        Frame.objs.repaint();
    }

    void analyser(String s)
    {
        String str1 = "Установить вероятность генерации каменных домов ";
        String str2 = "Получить вероятность генерации каменных домов";
        int g = s.length();
        int a=str2.length();
        int y = str1.length();
        String str = "";
        int i = 0;
        int j = 0;
        int k=0;
        boolean flag = false;
        switch(1) {
            case 1: {
                while (g > y) {
                    if (str1.charAt(i) == s.charAt(i)) {
                        if ((i + 1) == str1.length()) {
                            i++;
                            if (s.charAt(g - 1) != '%') break;
                            while (s.charAt(i) != '%') {
                                str += String.valueOf(s.charAt(i));
                                j++;
                                i++;
                            }
                            if (j != 0) {
                                flag = true;
                                break;
                            }
                        }
                    } else {

                        flag = false;
                        break;
                    }
                    i++;
                }
                if (flag) {

                    int perc = Integer.parseInt(str);
                    P2 = perc;
                    Frame.slider1.setValue(P2);
                    Frame.field.append("\n");
                    Frame.field.append("Установлена вероятность: " + perc);
                    break;
                }
            }
            case 2:
            {
                while (g == a) {
                    if (str2.charAt(k) != s.charAt(k)) {
                        flag = false;
                        break;
                    }
                    k++;
                    if(k==a)
                    {
                        flag=true;
                        break;
                    }
                }
                if(flag)
                {
                    Frame.field.append("\n");
                    Frame.field.append("Вероятность появления: " + P2);
                    break;
                }
            }
            default:
            {
                if (!flag) {
                    Frame.field.append("\n");
                    Frame.field.append("Некорректная команда");
                }
                break;
            }
        }
    }


    void clear()
    {
        ids.clear();
        borns.clear();
        houses.clear();
        Frame.objs.removeAll();
        Wood.woods=Stone.stones=0;
        new Habitat();
    }
}

