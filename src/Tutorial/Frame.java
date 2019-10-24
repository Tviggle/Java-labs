package Tutorial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Frame {
    static public JPanel objs=new JPanel();
    static public JFrame frame=new JFrame("Labs");
    static public JPanel panel=new JPanel();
    static Habitat habitat;

    static public int WIDTH=1200,HEIGHT=1000;
    static int t;
    static long time=0,start=0;

    static boolean simulation=false;
    static boolean text=true;
    static boolean bt=true;
    static String TextArea[]=new String[4];

    static JLabel textTimeSimulation = new JLabel(""),
            percent1=new JLabel("Вероятность появления каменного дома"),percent2=new JLabel("Вероятность появления деревянного дома"),
            period1=new JLabel("Период появления деревянных домов"),period2=new JLabel("Период появления каменных домов"),
            period3=new JLabel("Время жизни деревянных домов"),period4=new JLabel("Время жизни каменных домов"),
            thread1=new JLabel("Каменные дома"),thread2=new JLabel("Деревянные дома"),
            pr=new JLabel("Приоритет каменных/деревянных домов");
    static TextField field1=new TextField(""),field2=new TextField(""),
            field3=new TextField(""),field4=new TextField("");
    static JButton btStart = new JButton("Start"), btStop = new JButton("Stop"),
            current=new JButton("<html><center><font size=\"5\" >Current objects</font>"),
            lu=new JButton("Show users");
    static JRadioButton timeOn = new JRadioButton("Показать время симуляции"),
            timeOff = new JRadioButton("Скрыть время симуляции"),
            info=new JRadioButton("Показать информацию по завершении симуляции"),
            threadon1=new JRadioButton("Вкл"),threadoff1=new JRadioButton("Выкл"),
            threadon2=new JRadioButton("Вкл"),threadoff2=new JRadioButton("Выкл");
    static JSlider slider1=  new JSlider(0,100),slider2=new JSlider(0,100);

    static JMenuBar jMenuBar = new JMenuBar();
    static JMenu jmCommands = new JMenu("Меню");
    static JMenuItem jmiStart = new JMenuItem("Старт"), jmiStop = new JMenuItem("Стоп"), jmiTime = new JMenuItem("Показать/скрыть время симуляции"),
    jmiConsol=new JMenuItem("Консоль"),jmiSave=new JMenuItem("Сохранить"),jmiLoad=new JMenuItem("Загрузить");
    static JToolBar jToolBar = new JToolBar();
    static JButton jtbStart = new JButton("Старт"), jtbStop = new JButton("Стоп"), jtbTime = new JButton("Показать/скрыть время симуляции");

    static String[] priority= {"1","2","3","4","5","6","7","8","9","10"};
    static JComboBox jcb1=new JComboBox(priority);
    static JComboBox jcb2=new JComboBox(priority);

    static JDialog consol=new JDialog(frame,"Консоль",false);
    static JTextArea field=new JTextArea();

    Configure conf=new Configure();

    Frame()
    {
        try {
            conf.reading();
            field1.setText(Integer.toString(Habitat.N1));
            field2.setText(Integer.toString(Habitat.N2));
            field3.setText(Integer.toString(Habitat.K1));
            field4.setText(Integer.toString(Habitat.K2));
            slider1.setValue(Habitat.P2);
            slider2.setValue(Habitat.P1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame.setLayout(null);
        frame.setSize(WIDTH,HEIGHT);
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                try {
                    conf.writing();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                MyClient.sending("Exit");
                System.exit(0);
            }
        });
        frame.setVisible(true);
        frame.requestFocus();

        objs.setLayout(null);
        objs.setBounds(300,0,WIDTH,HEIGHT);

        panel.setLayout(null);
        panel.setBounds(0,0,300,HEIGHT);
        textTimeSimulation.setBounds(50,100, 300, 20);
        panel.add(textTimeSimulation);

        consol.setSize(500,300);
        field.setBounds(0,0,500,300);
        consol.add(field);

        btStart.setBounds(30, 10, 100, 50);
        panel.add(btStart);
        btStop.setBounds(170, 10, 100, 50);
        panel.add(btStop);
        btStop.setEnabled(false);
        current.setPreferredSize(new Dimension(200,100));
        current.setBounds(50,480,200,100);
        panel.add(current);
        info.setBounds(0,70,300,20);
        panel.add(info);
        timeOn.setBounds(30,180,200,20);
        panel.add(timeOn);
        timeOff.setBounds(30,210,200,20);
        panel.add(timeOff);
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(timeOn);
        buttonGroup1.add(timeOff);
        thread1.setBounds(18,590,140,40);
        panel.add(thread1);
        threadon1.setBounds(12,630,50,20);
        panel.add(threadon1);
        threadoff1.setBounds(62,630,60,20);
        panel.add(threadoff1);
        ButtonGroup buttonGroup2 = new ButtonGroup();
        buttonGroup2.add(threadon1);
        buttonGroup2.add(threadoff1);
        thread2.setBounds(170,590,140,40);
        panel.add(thread2);
        threadon2.setBounds(170,630,50,20);
        panel.add(threadon2);
        threadoff2.setBounds(220,630,60,20);
        panel.add(threadoff2);
        ButtonGroup buttonGroup3 = new ButtonGroup();
        buttonGroup3.add(threadon2);
        buttonGroup3.add(threadoff2);
        percent1.setBounds(30,235,300,20);
        panel.add(percent1);
        slider1.setBounds(0,260,280,30);
        slider1.setMajorTickSpacing(10);
        slider1.setPaintLabels(true);
        slider1.setSnapToTicks(true);
        panel.add(slider1);
        percent2.setBounds(30,300,300,20);
        panel.add(percent2);
        slider2.setBounds(0,325,280,30);
        slider2.setMajorTickSpacing(10);
        slider2.setPaintLabels(true);
        slider2.setSnapToTicks(true);
        panel.add(slider2);
        period1.setBounds(0,360,230,20);
        panel.add(period1);
        field1.setBounds(235,360,60,20);
        panel.add(field1);
        period2.setBounds(0,390,230,20);
        panel.add(period2);
        field2.setBounds(235,390,60,20);
        panel.add(field2);

        period3.setBounds(0,420,230,20);
        panel.add(period3);
        field3.setBounds(235,420,60,20);
        panel.add(field3);
        period4.setBounds(0,450,230,20);
        panel.add(period4);
        field4.setBounds(235,450,60,20);
        panel.add(field4);

        pr.setBounds(27,660,300,20);
        panel.add(pr);
        jcb1.setBounds(5,690,140,20);
        panel.add(jcb1);
        jcb2.setBounds(155,690,140,20);
        panel.add(jcb2);
        lu.setBounds(80,750,140,20);
        panel.add(lu);


        jmCommands.add(jmiStart);
        jmCommands.add(jmiStop);
        jmCommands.add(jmiTime);
        jmCommands.add(jmiConsol);
        jmCommands.add(jmiSave);
        jmCommands.add(jmiLoad);
        jMenuBar.add(jmCommands);

        jToolBar.add(jtbStart);
        jToolBar.add(jtbStop);
        jToolBar.add(jtbTime);
        jToolBar.setLayout(new FlowLayout(FlowLayout.LEADING));
        jToolBar.setBounds(0, 800, 300, 100);
        panel.add(jToolBar);

        frame.add(objs);
        frame.add(panel);
        frame.setJMenuBar(jMenuBar);
        frame.setResizable(false);

        timeOn.setSelected(true);
        info.setSelected(true);
        threadon1.setSelected(true);
        threadon2.setSelected(true);

        Listeners listen=new Listeners();

        habitat=new Habitat();
    }

    static void time_simulation(  ){
        if(text){
            textTimeSimulation.setText((time/1000)+" секунд после начала симуляции");
        }else {
            textTimeSimulation.setText("");
        }
        panel.repaint();
    }
}

