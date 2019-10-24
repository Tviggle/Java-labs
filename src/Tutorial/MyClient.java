package Tutorial;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MyClient extends Thread {
    private Socket client;
    static private ObjectOutputStream out;
    static private ObjectInputStream in;
    static public  String[] users;

    MyClient()
    {
        try {
            client = new Socket("localhost", 1212);
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
        }
        catch (IOException e) {
        e.printStackTrace();
    }
        this.start();
    }
    public void run()
    {
        while(true){
            try {
                String tmp = (String) in.readObject();
                if(tmp.equals("sending list")){
                    users= (String[])in.readObject();
                }else if(tmp.equals("sending objs")){
                    SavedApp serial= (SavedApp)in.readObject();
                    System.out.println(serial.houses.size());
                    serial.adding();
                }
            } catch (EOFException e) { }
            catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    static void sending(Object obj){
        try {
            out.writeObject(obj);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

