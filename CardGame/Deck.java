package CardGame;

import java.util.ArrayList;
import java.util.List;

/*** Isaac Angle
 * This class allows for the creation of a deck object
 * that works like a normal deck of cards,
 */
public class Deck {

    private int numCards = 52;
    private List<Cards> myDeck= new ArrayList<Cards>();

    public Deck(){}

    public Deck(int numCards){
        if(numCards != 52 && numCards != 54){
            System.out.println("That is not a valid deck size!!");
        }

        this.numCards = numCards;

        // fills the deck with cards from 1 to 13, 13 being a queen
        // and one being an ace
        for (int i = 1; i <= 13; i++){
            myDeck.add(new Cards(i,SUIT.DIAMOND));
            myDeck.add(new Cards(i,SUIT.SPADE));
            myDeck.add(new Cards(i,SUIT.HEART));
            myDeck.add(new Cards(i,SUIT.CLUB));
        }
    }

    //getter that returns the number of cards in the deck
    public int getNumCards(){
        return numCards;
    }

    //getter that returns a list of cards in the deck
    public List<Cards> getCards(){
        return myDeck;
    }

    //overriding the toString method to allow for the printing out of
    //the rank and suit of a card
    @Override
    public String toString() {
        return "This deck contains " + numCards + " cards." + "\n";
    }

}
