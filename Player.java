import java.util.LinkedList;

public class Player{

    // MEMBER VARIABLES

    /** an integer (1, 2, etc) to identify which player it is */
    private int playerNum;
    /** LinkedList of cards in their hand first = top */
    private LinkedList<Card> hand;
    /** a String representing the type of pattern this player watches for */
    private String pattern;
    // and more????

    // METHODS

    // OVERLOADED CONSTRUCTOR
    public Player(int num, LinkedList<Card> ha, String pat){
        playerNum = num;
        hand = new LinkedList<Card>();
        pattern = pat;

        for (Card c: ha){
            hand.add(c); // need for new keyword? naw.
        }
    }

    /** removes a Card from the top of the player's hand and returns it */
    public Card playCard(){
        Card card = new Card(hand.get(0)); // new creates memory space
        hand.remove(0);
        return card;
    }

    /** evaluates the Player's pattern member variable */
    public boolean slaps(LinkedList<Card> pile){
        // IMPORTANT: last element of pile is the "top"
        // DOUBLES
        if (pattern.equals("doubles")){
            return Game.doubles(pile);
        // TOP BOTTOM
        } else if (pattern.equals("top bottom")){
            return Game.topBottom(pile);
        // SANDWICH
        } else if (pattern.equals("sandwich")){
            return Game.sandwich(pile);
        } else{
            return false;
        }
    }

    public String toString(){
        String s = "";
        s += "Player #" + playerNum + "\n";
        s += "Looks for the " + pattern + " pattern\n";
        s += "Hand:\n";
        for (Card c: hand){
            s += "- " + c + "\n";
        }
        return s;
    }

    public void addCard(Card card){
        hand.add(card);
    }

    public void addPile(LinkedList<Card> cards){
        for (Card c: cards){
            this.addCard(c);
        }
    }

    // ACCESSORS
    public int getPlayerNum(){
        return playerNum;
    }
    public LinkedList<Card> getHand(){
        return hand;
    }
    public String getPattern(){
        return pattern;
    }

    // OTHER PRIVATE HELPER METHODS???? naw

}