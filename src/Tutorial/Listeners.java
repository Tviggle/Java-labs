package Tutorial;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.*;
import java.io.*;

public class Listeners
{
    Listeners()
    {
        Frame.btStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                func_B();
                Frame.frame.requestFocus();
            }
        });

        Frame.lu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MyClient.sending("get list of users");
                    Thread.sleep(100);
                    Object userIP = JOptionPane.showInputDialog(Frame.frame,"Select User", "Choice User",
                            JOptionPane.QUESTION_MESSAGE, null, MyClient.users, MyClient.users[0]);

                    MyClient.sending("add objs");
                    MyClient.sending(userIP);
                    SavedApp ser=new SavedApp(Habitat.houses,Habitat.ids,Habitat.borns);
                    MyClient.sending(ser);
                }catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });


        Frame.frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode()== KeyEvent.VK_T)
                {
                    func_T();
                    if(Frame.text)
                        Frame.timeOn.setSelected(true);
                    else
                        Frame.timeOff.setSelected(true);
                }
                if(e.getKeyCode()== KeyEvent.VK_E)
                {
                    if(Frame.simulation)
                        func_E();
                }
                if(e.getKeyCode()== KeyEvent.VK_B)
                {
                    if(!Frame.simulation)
                        func_B();
                }
            }
        });

        Frame.field.addKeyListener( new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode()== KeyEvent.VK_ENTER)
                {
                    String s=getcurrentText();
                    Frame.habitat.analyser(s);
                }
            }
        });

        Frame.btStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                func_E();
                Frame.frame.requestFocus();

            }
        });

        Frame.timeOn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.text = true;
            }
        });

        Frame.timeOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.text = false;
            }
        });

        Frame.threadon1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Starter.st.wakeup();
                Starter.st.stop=false;
            }
        });

        Frame.threadoff1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Starter.st.stop=true;
            }
        });

        Frame.threadon2.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
                Starter.wo.wakeup();
                Starter.wo.stop=false;
        }
    });

        Frame.threadoff2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Starter.wo.stop=true;
                    /*Iterator<Object> hIterator= Habitat.houses.iterator();
                    while(hIterator.hasNext()) {
                        Object current = hIterator.next();
                        if(current instanceof Wood)
                        {
                            Wood nextwood = (Wood) current;
                            synchronized (nextwood) {
                                try {
                                    nextwood.wait();
                                } catch (InterruptedException e1) {
                                }
                            }
                        }
            }*/
        }});

        Frame.slider1.addChangeListener(e -> {
            Frame.frame.requestFocus();
            Habitat.P2=Frame.slider1.getValue();
        });

        Frame.slider2.addChangeListener(e -> {
            Frame.frame.requestFocus();
            Habitat.P1=Frame.slider2.getValue();
        });

        Frame.field1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                Frame.field1.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        Frame.field1.addActionListener(e -> {
                    Frame.frame.requestFocus();
                    int k = Integer.parseInt(Frame.field1.getText());
                    if(k > 0){
                        Habitat.N1 = k;
                        JOptionPane.showMessageDialog(Frame.frame, "Установлен период появления деревянных домов равный "+Habitat.N1+" секунд");
                    }else{
                        Habitat.N1 = 1;
                        JOptionPane.showMessageDialog(Frame.frame, "Неверное значение" +
                                "\nУстановлено значение по умолчанию = 1.");
                    }
                }
        );

        Frame.field2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                Frame.field2.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        Frame.field2.addActionListener(e -> {
                    Frame.frame.requestFocus();
                    int k = Integer.parseInt(Frame.field2.getText());
                    if(k > 0){
                        Habitat.N2 = k;
                        JOptionPane.showMessageDialog(Frame.frame, "Установлен период появления каменных домов равный "+Habitat.N2+" секунд");
                    }else{
                        Habitat.N2 = 1;
                        JOptionPane.showMessageDialog(Frame.frame, "Неверное значение" +
                                "\nУстановлено значение по умолчанию = 1.");
                    }
                }
        );

        Frame.field3.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                Frame.field3.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        Frame.field3.addActionListener(e -> {
                    Frame.frame.requestFocus();
                    int k = Integer.parseInt(Frame.field3.getText());
                    if(k > 0){
                        Habitat.K1 = k;
                        JOptionPane.showMessageDialog(Frame.frame, "Установлен период появления деревянных домов равный "
                                +Habitat.K1+" секунд");
                    }else{
                        Habitat.K1 = 10;
                        JOptionPane.showMessageDialog(Frame.frame, "Неверное значение" +
                                "\nУстановлено значение по умолчанию = 10.");
                    }
                }
        );

        Frame.field4.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                Frame.field4.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        Frame.field4.addActionListener(e -> {
                    Frame.frame.requestFocus();
                    int k = Integer.parseInt(Frame.field4.getText());
                    if(k > 0){
                        Habitat.K2 = k;
                        JOptionPane.showMessageDialog(Frame.frame, "Установлен период появления каменных домов равный "
                                +Habitat.K2+" секунд");
                    }else{
                        Habitat.K2 = 10;
                        JOptionPane.showMessageDialog(Frame.frame, "Неверное значение" +
                                "\nУстановлено значение по умолчанию = 10.");
                    }
                }
        );

        Frame.current.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.simulation=false;
                long stop=System.currentTimeMillis();
                Habitat.currentobject(stop);
                Frame.frame.requestFocus();
            }
        });

        Frame.jmiStart.addActionListener(e -> {
            func_B();
            Frame.frame.requestFocus();
        });
        Frame.jmiStop.addActionListener(e -> {
            func_E();
            Frame.frame.requestFocus();
        });
        Frame.jmiTime.addActionListener(e -> {
            func_T();
            Frame.frame.requestFocus();
            if(Frame.text)
                Frame.timeOn.setSelected(true);
            else
                Frame.timeOff.setSelected(true);
        });
        Frame.jmiConsol.addActionListener(e -> {
            Frame.consol.setVisible(true);
            Frame.frame.requestFocus();
        });
        Frame.jmiSave.addActionListener(e -> {
            SavedApp sap=new SavedApp(Habitat.houses,Habitat.ids,Habitat.borns);
                try {
                JFileChooser fc = new JFileChooser("src/");
                fc.showSaveDialog(Frame.frame);
                File sf = fc.getSelectedFile();
                if(sf!= null) {
                    FileOutputStream fileOutputStream = new FileOutputStream(sf);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject(sap);
                    fileOutputStream.close();
                    objectOutputStream.close();
                }
            }
            catch(Exception e1){e1.printStackTrace();}
        });
        Frame.jmiLoad.addActionListener(e -> {
            try {
                JFileChooser fc = new JFileChooser("src/");
                fc.showOpenDialog(Frame.frame);
                File lf = fc.getSelectedFile();
                if(lf!=null) {
                    if(Frame.simulation==true)
                        Frame.habitat.clear();
                    FileInputStream fileInputStream = new FileInputStream(lf);
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                    SavedApp sap = (SavedApp) objectInputStream.readObject();
                    sap.Loading();
                    Frame.habitat.resume();
                }
            }
            catch(Exception e1){e1.printStackTrace();}

        });

        Frame.jtbStart.addActionListener(e -> {
            func_B();
            Frame.frame.requestFocus();
        });
        Frame.jtbStop.addActionListener(e -> {
            func_E();
            Frame.frame.requestFocus();
        });
        Frame.jtbTime.addActionListener(e -> {
            func_T();
            Frame.frame.requestFocus();
            if(Frame.text)
                Frame.timeOn.setSelected(true);
            else
                Frame.timeOff.setSelected(true);
        });

        Frame.jcb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox)e.getSource();
                String item = (String)box.getSelectedItem();
                Starter.st.setPriority(Integer.parseInt(item));
                System.out.println(Starter.st.getPriority());
            }
        });
        Frame.jcb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox)e.getSource();
                String item = (String)box.getSelectedItem();
                Starter.wo.setPriority(Integer.parseInt(item));
                System.out.println(Starter.wo.getPriority());
            }
        });
    }
    static void func_B()
    {
        Frame.frame.requestFocus();
        Frame.btStart.setEnabled(false);
        Frame.btStop.setEnabled(true);
        //Wood.woods=Stone.stones=0;
        //Frame.habitat.clear();
        Frame.start=System.currentTimeMillis();
        Frame.t=0;
        Starter.wo.prev=0;
        Starter.creat.prev=0;
        Starter.st.prev=0;
        Starter.wo.moving=true;
        Starter.st.moving=true;
        Frame.simulation=true;
    }
    static void func_E()
    {
        Frame.simulation=false;
        long stop=System.currentTimeMillis();
        if(Frame.info.isSelected())
        {
            results();
            int result= JOptionPane.showConfirmDialog(Frame.frame,
                    Frame.TextArea,
                    "Message",
                    JOptionPane.OK_CANCEL_OPTION);
            if(result==JOptionPane.OK_OPTION)
            {
                Frame.habitat.clear();
                Frame.btStop.setEnabled(false);
                Frame.btStart.setEnabled(true);
                Frame.time=0;
            }
            else
            {
                Frame.start=Frame.start+(System.currentTimeMillis()-stop);
                Frame.simulation=true;
            }
        }
        else
        {
            Frame.btStop.setEnabled(false);
            Frame.btStart.setEnabled(true);
            Frame.time=0;
            Frame.habitat.clear();
        }
    }

    static void func_T()
    {
        Frame.text=!Frame.text;
    }

    static void results()
    {
        Frame.TextArea[0]=("Количество деревянных домов " + Wood.woods);
        Frame.TextArea[1]=("Количество каменных домов " + Stone.stones);
        Frame.TextArea[2]=("Всего объектов " + (Stone.stones + Wood.woods));
        Frame.TextArea[3]=("Время симуляции " + (Frame.time / 1000));
    }

    public String getcurrentText()
    {
        try {
            int offset=Frame.field.getLineOfOffset(Frame.field.getCaretPosition());
            int start=Frame.field.getLineStartOffset(offset);
            int end=Frame.field.getLineEndOffset(offset);
            return Frame.field.getText(start, (end-start));
        } catch (BadLocationException ex) {
            System.out.println(ex.getMessage());
            return "-1";
        }
    }
}
