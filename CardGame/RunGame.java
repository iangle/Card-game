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

    private Scanner scan = new Scanner(System.in);


    //default constructor
    public RunGame(){}

    //takes in the number of computer and human players
    //creates the starting hands for those players
    public RunGame(int numComps, int numHumans) {

        //initializing the number of players
        this.numComps = numComps;
        this.numHumans = numHumans;

        //gets the number of players from the user
        System.out.println("How many computer players are there?");
        this.numComps = scan.nextInt();
        System.out.println("How many human players are there?");
        this.numHumans = scan.nextInt();

        //initializing the lists of players
        this.compPlayers = new Player[this.numComps];
        this.humanPlayers = new Player[this.numHumans];

        //adds each computer player to the number of players and gives them a starting hand
        for (int i = 0; i < this.numComps; i++) {
            this.compPlayers[i] = new Player(USER.COMPUTER, createStartingHand());
            compPlayers[i].setEachPlayerNum(Player.incPlayerNum());
        }

        //adds each human player to the number of players and gives them a starting hand
        for (int i = 0; i < this.numHumans; i++) {
            this.humanPlayers[i] = new Player(USER.HUMAN, createStartingHand());
            humanPlayers[i].setEachPlayerNum(Player.incPlayerNum());
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

    //goes through the game and allows each player to guess and get cards
    // once someone gets 4 books or the deck runs out of cards the game is over
    private void Play() {

        //is true when the user has the card they guessed in their own hand
        boolean cardInHand = false;

        //is true if the currPlayer is the same as the guessed player
        boolean currPlayer = true;

        //is true when the player correctly guessed a card from an opponents hand
        boolean guessedCorrectly = false;

        //the number of the player picked for a guess
        int playerChosen = 0;

        //the rank of the card guessed
        int userGuess = 0;

        int counter = 0;

        Player guessedPlayer = null;


        //welcome message and general rules for how to play
        System.out.println("Welcome to the game of Go Fish! ");
        System.out.println("There are " + (humanPlayers.length + compPlayers.length) + " players");
        System.out.println("when guessing cards you can pick each player from the list with their respective number");

        //prints out the different computer players
        for (int i = 0; i < compPlayers.length; i++) {
            System.out.println("computer player: " + compPlayers[i].getEachPlayerNum());
        }

        //prints out the different human players
        for (int i = 0; i < humanPlayers.length; i++) {
            System.out.println("human player: " + humanPlayers[i].getEachPlayerNum());
        }


        //this while loop runs while there is still a deck and the game is not over
        while (!theDeck.getCards().empty() && !gameOver) {
            do {

                cardInHand = false;

                guessedCorrectly = false;

                currPlayer = true;

                System.out.println("The cards in your hand are: ");

                    for (int j = 0; j < humanPlayers[counter].getHand().size(); j++) {

                        System.out.println(humanPlayers[counter].getHand().get(j).toString());
                    }

                    //double checks that the user inputted a valid guess that is also in their hand
                    while (!cardInHand) {
                        System.out.println("Guess a card that is also in your hand (1-13): ");
                        userGuess = scan.nextInt();

                        //goes through the hand and checks for the guess
                        for (int k = 0; k < humanPlayers[counter].getHand().size(); k++) {
                            if (userGuess == humanPlayers[counter].getHand().get(k).getRank()) {
                                cardInHand = true;
                            }
                        }
                    }

                    //checks to make sure the user does not choose himself as the target for a guess
                    while (currPlayer) {
                        System.out.println("Which player would you like to ask for the card? ");
                        playerChosen = scan.nextInt();

                        if (playerChosen != humanPlayers[counter].getEachPlayerNum()) {
                            currPlayer = false;
                        }
                    }

                    //goes through each human player and sees if they are the one that is being guessed
                    for (int j = 0; j < humanPlayers.length; j++) {
                        if (playerChosen == humanPlayers[j].getEachPlayerNum()) {
                            guessedPlayer = humanPlayers[j];
                        }
                    }

                    //goes through each computer player and sees if they are the one that is being guessed
                    for (int j = 0; j < compPlayers.length; j++) {
                        if (playerChosen == compPlayers[j].getEachPlayerNum()) {
                            guessedPlayer = compPlayers[j];
                        }
                    }


                    //makes sure that the guessed player is not null
                    assert guessedPlayer != null;

                    //checks to see if the player has the card in their hand, if so it is added to the users hand
                    for (int j = 0; j < guessedPlayer.getHand().size(); j++) {
                        if (guessedPlayer.getHand().get(j).getRank() == userGuess) {
                            System.out.println("you guessed correctly!");
                            humanPlayers[counter].addToHand(guessedPlayer.getHand().get(j));
                            guessedPlayer.getHand().remove(guessedPlayer.getHand().get(j));
                            guessedCorrectly = true;
                        }
                    }

                    //if the guess was incorrect then output "Go Fish!" and draw a card from the deck
                    if (!guessedCorrectly) {
                        System.out.println("Go Fish!");
                        System.out.println("Player " + humanPlayers[counter].getEachPlayerNum() + " drew a card from the deck");
                        humanPlayers[counter].getHand().add(theDeck.getCards().pop());
                        theDeck.decNumCardsInDeck(1);
                    }
            } while(guessedCorrectly);


            //if any computer player gets 4 books they win
            for (Player compPlayer : compPlayers) {
                gameOver = compPlayer.getMyBooks().size() >= 4;
            }

            //if any human player gets 4 books they win
            for (Player humanPlayer : humanPlayers) {
                gameOver = humanPlayer.getMyBooks().size() >= 4;
            }

            counter++;

            if(counter > humanPlayers.length){

            }

        }
        System.out.println("Someone won the game!!");
    }

    private void computersTurn(){
        int guess = 0;
        int compCounter = 0;
        int personBeingGuessed = 0;

        Player guessedPlayer = null;
        System.out.println("player " + compPlayers[compCounter] + " is taking their turn");

        guess = randNum(13, 0);

        System.out.println("player " + compPlayers[compCounter] + " is guessing " + guess);

        personBeingGuessed = randNum((compPlayers.length + humanPlayers.length),compPlayers[compCounter].getEachPlayerNum());

        for(int i = 0; i < compPlayers.length; i++){
            if(personBeingGuessed == compPlayers[i].getEachPlayerNum()){
                guessedPlayer = compPlayers[i];
            }
        }

        for(int i = 0; i < humanPlayers.length; i++){
            if(personBeingGuessed == humanPlayers[i].getEachPlayerNum()){
                guessedPlayer = humanPlayers[i];
            }
        }

        assert guessedPlayer != null;

        for(int i = 0; i < guessedPlayer.getHand().size(); i++) {
            if (guessedPlayer.getHand().get(i).getRank() == guess) {
                System.out.println("player " + compPlayers[compCounter] + " guessed correctly!");
                compPlayers[compCounter].addToHand(guessedPlayer.getHand().get(i));
                guessedPlayer.getHand().remove(guessedPlayer.getHand().get(i));
            }
        }

    }

    //takes in the maximum number allowed and an integer that you dont want the random number to be
    //returns that random number
    private int randNum(int max, int notThisInt){

        int randNum = 0;

        //makes sure that the excluded int is excluded
        while(randNum == notThisInt) {
            randNum = rand.nextInt(max - 1) + 1;
        }
        return randNum;
    }
}
