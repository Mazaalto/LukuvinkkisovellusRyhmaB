package lukuvinkkisovellus;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        UI kayttis = new UI(reader);
        kayttis.start();
    }
}
    