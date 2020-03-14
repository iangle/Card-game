package CardGame;

import java.util.ArrayList;
import java.util.List;
import java.util.spi.LocaleServiceProvider;

/***Isaac Angle
 * This class is the player class, which is used to create
 * two different types of players, COMPUTER and HUMAN
 */
public class Player {

    private USER myUser;

    //set of cards that is the users current hand
    private List<Cards> myHand = new ArrayList<Cards>();

    //a list that holds the sets of four that the player has gotten
    private List<Cards> myBooks = new ArrayList<>();

    //default constructor
    public Player(){}

    public Player(USER myUser, List<Cards> startingHand){

        //initializing the user type
        this.myUser = myUser;

        //initializing the hand to start with the given cards
        this.myHand = startingHand;
    }

    //getter for the type of user being used
    public USER getUser(){
        return this.myUser;
    }

    //getter for the current hand of a player
    public List<Cards> getHand(){
        return this.myHand;
    }

    //adds a new card to the players hand
    public void addToHand(Cards newCard){
        myHand.add(newCard);
    }

    //getter for the list of books that the user has
    public List<Cards> getMyBooks(){
        return this.myBooks;
    }
}
