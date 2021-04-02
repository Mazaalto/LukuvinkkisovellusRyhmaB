package lukuvinkkisovellus;

import java.io.IOException;
import java.util.Scanner;

public class Main {
<<<<<<< HEAD
    public static void main(String[] args) throws IOException {
=======
    public static void main(String[] args) throws Exception {
>>>>>>> 4674a6049f219611ccc9360b406f9c7214bb0256
        Scanner reader = new Scanner(System.in);

        UI kayttis = new UI(reader);
        kayttis.start();
    }
}