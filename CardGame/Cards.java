package CardGame;

import static CardGame.SUIT.DIAMOND;

/*** Isaac Angle. This class will create an object called a card.
 * it will be used in the same way that cards are used in a deck
 * each card has a rank and a suit that is a part of its makeup
 */

public class Cards {

    private int rank = 0;
    private SUIT cardSuit;
    private String suit = "";

    public Cards(){}

    //creates the different suits and gives them a value that
    //makes more since to print out to the screen
    public Cards(int rank, SUIT cardSuit){
        this.rank = rank;
        this.cardSuit = cardSuit;

        switch(cardSuit){
            case DIAMOND:
                suit = "Diamond";
                break;
            case SPADE:
                suit = "Spade";
                break;
            case CLUB:
                suit = "Club";
                break;
            case HEART:
                suit = "Heart";
                break;
            default:
                suit = "a suit";
        }
    }

    //getter for the rank of a card
    public int getRank(){
        return rank;
    }

    //getter for the suit of a card
    public SUIT getSUIT(){
        return cardSuit;
    }

    //overriding the toString function in order to
    //print out a card's rank and suit
    @Override
    public String toString() {
        return "card Rank: " + rank + "\n" + "card Suit: " + suit;
    }
}
