package CardGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RunGame {

    private int numHumans, numComps, cardNum = 0;
    private int numCardsInDeck = 52;

    private Random rand = new Random();

    private Player[] humanPlayers;
    private Player[] compPlayers;

    private Deck theDeck = new Deck(numCardsInDeck);

    //default constructor
    public RunGame(){}

    //takes in the number of computer and human players
    //creates the starting hands for those players
    public RunGame(int numComps, int numHumans) {

        //initializing the number of players
        this.numComps = numComps;
        this.numHumans = numHumans;

        //initializing the lists of players
        this.compPlayers = new Player[this.numComps];
        this.humanPlayers = new Player[this.numHumans];

        //adds each computer player to the number of players and gives them a starting hand
        for (int i = 0; i < this.numComps; i++) {
            this.compPlayers[i] = new Player(USER.COMPUTER, createStartingHand());
        }

        //adds each human player to the number of players and gives them a starting hand
        for (int i = 0; i < this.numHumans; i++) {
            this.humanPlayers[i] = new Player(USER.HUMAN, createStartingHand());
        }
    }

    //returns a random card from the deck and also removes that card
    //from the deck
    private Cards DrawCard() {

        //creates a random number between 0 and the number of cards in the deck
        int randNum = rand.nextInt(numCardsInDeck);

        //gets a random card from the deck, saves it, then deletes it from the deck
        Cards newCard = theDeck.getCards().get(randNum);
        theDeck.getCards().remove(randNum);

        //decreases the size of the deck
        numCardsInDeck--;

        //returns the card that was drawn
        return newCard;

    }

    //creates the starting hand for a player
    private List<Cards> createStartingHand(){

        //creates a list of cards to be the hand
        List<Cards> hand = new ArrayList<Cards>();

        //adds 5 cards to the hand
        for(int i = 0; i < 4; i++){
            //adds a card to the hand
            hand.add(DrawCard());
        }

        //returns the new hand
        return hand;
    }

    private void Play(){

    }
}
