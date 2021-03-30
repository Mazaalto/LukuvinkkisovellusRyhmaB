/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lukuvinkkisovellus;

/**
 *
 * @author mazaalto
 */
import java.util.Scanner;

public class UI {

    private Scanner reader;

    public UI(Scanner reader) {
        this.reader = reader;
    }

    public void start() {
        System.out.println("Tervetuloa lukuvinkkisovellukseen!");
        printCommands();

    }

    private void printCommands() {
        System.out.println("Valitse allaolevista komennoista numero ja paina enter");
        while (true) {
            System.out.println("1 (lisää lukuvinkki) ja 2 (listaa lukuvinkit), tyhjä lopettaa");
            String komento = reader.nextLine();
            if (komento.equals("") || komento.equals(" ")) {
                break;
            }
            if (Integer.parseInt(komento) == 1) {
                System.out.println("Lisätään lukuvinkki");
            }
            if (Integer.parseInt(komento) == 2) {
                System.out.println("Listataan lukuvinkit");
            }
            
        }

    }
}
