package Tutorial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Configure {
    void writing() throws Exception {
        BufferedWriter out=new BufferedWriter(new FileWriter("src/Конфигурация.txt"));
        out.write(String.valueOf(Habitat.N1));
        out.newLine();
        out.write(String.valueOf(Habitat.N2));
        out.newLine();
        out.write(String.valueOf(Habitat.P1));
        out.newLine();
        out.write(String.valueOf(Habitat.P2));
        out.newLine();
        out.write(String.valueOf(Habitat.K1));
        out.newLine();
        out.write(String.valueOf(Habitat.K2));
        out.newLine();
        out.flush();
        out.close();
    }
    void reading() throws Exception
    {
        BufferedReader in=new BufferedReader(new FileReader("src/Конфигурация.txt"));
        Habitat.N1=Integer.parseInt(in.readLine());
        Habitat.N2=Integer.parseInt(in.readLine());
        Habitat.P1=Integer.parseInt(in.readLine());
        Habitat.P2=Integer.parseInt(in.readLine());
        Habitat.K1=Integer.parseInt(in.readLine());
        Habitat.K2=Integer.parseInt(in.readLine());
        in.close();
    }
}
