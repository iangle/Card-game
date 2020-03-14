package CardGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RunGame {

    private int numHumans, numComps, cardNum = 0;

    private Random rand = new Random();

    private Player[] humanPlayers;
    private Player[] compPlayers;

    private Deck theDeck = new Deck();

    private boolean gameOver = false;

    private Scanner scan;

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

        //play the game
        Play();
    }

    //returns a random card from the deck and also removes that card
    //from the deck
    private Cards DrawCard() {

        Cards newCard;
        //gets the top card from the deck, saves it, then deletes it from the deck
        if(!theDeck.getCards().empty()) {

            newCard = theDeck.getCards().pop();

            theDeck.decNumCardsInDeck(1);

            return newCard;
        }else{
            System.out.println("the deck has no more cards!");
            return null;
        }
    }

    //creates the starting hand for a player
    private List<Cards> createStartingHand(){

        //creates a list of cards to be the hand
        List<Cards> hand = new ArrayList<Cards>();

        //adds 5 cards to the hand
        for(int i = 0; i < 5; i++){
            //adds a card to the hand
            hand.add(DrawCard());

        }

        //returns the new hand
        return hand;
    }

    private void Play(){


        for (Player compPlayer : compPlayers) {
                gameOver = compPlayer.getMyBooks().size() >= 4;
        }

        for (Player humanPlayer : humanPlayers){
                gameOver = humanPlayer.getMyBooks().size() >= 4;
        }

        System.out.println("Welcome to my game of Go Fish! ");

        while (!theDeck.getCards().empty() && !gameOver){

                System.out.println("The cards in your hand are: ");

                for(int i = 0; i < humanPlayers.length; i++) {

                    for (int j = 0; j < humanPlayers[i].getHand().size(); j++) {

                        System.out.println(humanPlayers[i].getHand().get(j).toString());
                    }
                }

                System.out.println("Guess a card that is also in your hand (1-13): ");
                int userGuess = scan.nextInt();



        }
    }

    private void checkGuess(int guess, USER whichUser){
        //if(compPlayer){

        //}
    }
}
