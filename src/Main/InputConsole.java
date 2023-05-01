package Main;

import java.util.Scanner;

public class InputConsole {
    public static int ReaderFromInput(Scanner sc) {
        int cislo = 0;
        try {
            cislo = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Nastala vyjimka typu " + e);
            System.out.println("zadejte prosim cele cislo ");
            sc.nextLine();
            cislo = ReaderFromInput(sc);
        }
        return cislo;
    }


}


