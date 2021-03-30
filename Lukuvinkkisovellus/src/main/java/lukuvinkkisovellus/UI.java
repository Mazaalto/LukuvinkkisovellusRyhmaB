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

    }
}
