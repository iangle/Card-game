package CardGame;

/*** Isaac Angle
 * This class is the main class for a go fish game that I am working on
 * This class will run the PlayGame class
 */
public class Main {

    public static void main(String[] args){

        Deck myDeck = new Deck(52);

        System.out.println(myDeck.toString());

        for(int i = 0; i < myDeck.getCards().size(); i++){
            System.out.println(myDeck.getCards().get(i));
            System.out.println("--------");
        }
    }
}
