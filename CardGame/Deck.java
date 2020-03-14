package CardGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/*** Isaac Angle
 * This class allows for the creation of a deck object
 * that works like a normal deck of cards,
 */
public class Deck {

    private int numCardsInDeck = 52;

    private Stack<Cards> myDeck= new Stack<Cards>();


    //constructor with the number of cards in the deck as a parameter
    public Deck(){

        int counter = 0;

        // fills the deck with cards from 1 to 13, 13 being a queen
        // and one being an ace
        Cards[] deckArray = new Cards[numCardsInDeck];
        for(int i = 1; i <= 13; i++){
            deckArray[counter] = new Cards(i, SUIT.DIAMOND);
            counter++;
            deckArray[counter] = new Cards(i, SUIT.SPADE);
            counter++;
            deckArray[counter] = new Cards(i, SUIT.CLUB);
            counter++;
            deckArray[counter] = new Cards(i, SUIT.HEART);
            counter++;
        }

        shuffleDeck(deckArray, numCardsInDeck);

        //push's the shuffled cards onto the deck
        for (int i = 0; i < 52; i++){
            myDeck.push(deckArray[i]);
        }
    }

    //getter that returns a stack of cards in the deck
    public Stack<Cards> getCards(){
        return myDeck;
    }

    //overriding the toString method to allow for the printing out of
    //the rank and suit of a card
    @Override
    public String toString() {
        return "This deck contains " + myDeck.size() + " cards." + "\n";
    }


    //idea taken from https://www.geeksforgeeks.org/shuffle-a-deck-of-cards-3/
    private void shuffleDeck( Cards[] deck, int cardNum){

        Random rand = new Random();
        for(int i = 0; i < cardNum; i++){

            //picks a random card
            int randNum = i + rand.nextInt(numCardsInDeck - i);

            //swaps the two cards
            Cards temp = deck[randNum];
            deck[randNum] = deck[i];
            deck[i] = temp;
        }
    }

    //returns the number of cards in the deck
    public int getNumCardsInDeck(){
        return numCardsInDeck;
    }

    //this method takes in an integer that is the amount of
    //cards that will be taken from the deck
    public void decNumCardsInDeck(int amountTaken){

        if(numCardsInDeck - amountTaken > 0 ) {

            numCardsInDeck -= amountTaken;

        }else{

            System.out.println("There are not enough cards in the deck");
        }
    }

}

