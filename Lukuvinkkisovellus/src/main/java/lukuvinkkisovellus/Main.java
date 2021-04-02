package lukuvinkkisovellus;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner reader = new Scanner(System.in);

        UI kayttis = new UI(reader);
        kayttis.start();
    }
}