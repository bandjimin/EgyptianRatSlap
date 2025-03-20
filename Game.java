import java.util.LinkedList;
import java.util.Random;

public class Game{
    Random rand = new Random();

    // MEMBER VARIABLES

    /** a LinkedList of players */
    private LinkedList<Player> players;
    /** a LinkedList of cards */
    private LinkedList<Card> pile;
    /** a deck of cars */
    private Dealer dealer;
    /** an array of Strings representing valid patterns */
    private String[] patterns = {"top bottom", "sandwich", "doubles"};;

    // MINE
    private Player currentPlayer; // initialized in play method
    private Card currentCard; // initialized in play method
    private Player nextPlayer; // initialized in play method
    private LinkedList<Player> slapping; // initialized in play method
    private int[] royals = {Card.JACK, Card.QUEEN, Card.KING, Card.ACE};
    private int roundNum = 0; // initialized in play method
    private int tries; // initialized in play method
    private boolean faceoff; // initialized in play method

    // METHODS

    private void nextRound(){ // used to mark each time someone wins a round by face card or slap
        roundNum += 1;
        String s = "";
        if (roundNum > 1){
            s += "\nWINNER: Player #" + currentPlayer.getPlayerNum() + "\n\n\n";
        }
        s += "ROUND " + roundNum + "\n";
        /*
        for (Player p: players){
            s += "" + p + "\n";
        }
        */
        System.out.println(s);
    }

    private boolean isRoyal(Card card){
        for (int num: royals){
            if (num == currentCard.getRank()){
                return true;
            }
        }
        return false;
    }

    // ACCESSORS
    public LinkedList<Player> getPlayers(){
        return players;
    }
    public LinkedList<Card> getPile(){
        return pile;
    }

    // DEFAULT CONSTRUCTOR
    public Game(){
        pile = new LinkedList<Card>();
        dealer = new Dealer();
        players = new LinkedList<Player>();
        slapping = new LinkedList<Player>();
        for (int i = 0; i < 2; i++){
            players.add(new Player(i+1, dealer.deals(52/2), patterns[rand.nextInt(3)]));
        } // 52/2 is a whole number
    }

    // OVERLOADED CONSTRUCTOR
    public Game(int numPlayers){
        pile = new LinkedList<Card>();
        dealer = new Dealer();
        players = new LinkedList<Player>();
        slapping = new LinkedList<Player>();
        for (int i = 0; i < numPlayers; i++){
            players.add(new Player(i+1, dealer.deals(52/numPlayers), patterns[rand.nextInt(3)]));
        }
        for (Player p: players){ // Some players might start with more cards than others
            LinkedList<Card> card = dealer.deals(1); // Deals one card, or no cards if deck is empty
            if (card.size() > 0){
                p.addCard(card.get(0));
            }
        }
        
    }

    public int play(){
        this.nextRound();
        // Game is already initialized
        currentPlayer = players.get(0); // index the first player as the current player
        while (players.size() > 1){
            currentCard = currentPlayer.playCard(); // takes from currentPlayer
            pile.add(new Card(currentCard));
            System.out.println("\nPlayer #" + currentPlayer.getPlayerNum() + " places the " + currentCard + "\n");
            // Death & The Next Player
            if (currentPlayer.getHand().size() == 0){
                int index = players.indexOf(currentPlayer);
                System.out.println("GAMEOVER for Player #" + currentPlayer.getPlayerNum());
                currentPlayer = this.nextPlayer(currentPlayer);
                players.remove(index);
                continue; // next person
            }
            // Royal Faceoff
            if (this.isRoyal(currentCard)){ // Checks if Royal
                faceoff = true;
                while (faceoff){
                    if (Card.JACK == currentCard.getRank()){tries = 1;}
                    else if (Card.QUEEN == currentCard.getRank()){tries = 2;} 
                    else if (Card.KING == currentCard.getRank()){tries = 3;} 
                    else {tries = 4;} // Must then be ace
                    nextPlayer = this.nextPlayer(currentPlayer);
                    System.out.println("\nFace Card Faceoff\n");
                    for (int i = 0; i < tries; i++){
                        currentCard = nextPlayer.playCard();
                        pile.add(new Card(currentCard));
                        System.out.println("Player #" + nextPlayer.getPlayerNum() + " places the " + currentCard);
                        if (nextPlayer.getHand().size() == 0){
                            int index = players.indexOf(nextPlayer);
                            System.out.println("GAMEOVER for Player #" + nextPlayer.getPlayerNum());
                            players.remove(index);
                            faceoff = false;
                            break;
                        }
                        if (this.isRoyal(currentCard)){
                            currentPlayer = nextPlayer;
                            System.out.println(""); // spacing
                            break;
                        }
                        if (i == (tries - 1)){
                            faceoff = false;
                        }
                    } 
                } // while true
                currentPlayer.addPile(pile);
                pile.clear();
                this.nextRound();
                continue;
            }
            
            // Slapping
            for (Player p: players){
                if (p.slaps(pile)){
                    slapping.add(p);
                }
            }
            if (slapping.size() > 0){
                currentPlayer = slapping.get(rand.nextInt(slapping.size()));
                System.out.println("SLAP\n");
                System.out.println("Player #" + currentPlayer.getPlayerNum() + " slaps the table");
                slapping.clear();
                currentPlayer.addPile(pile);
                pile.clear();
                this.nextRound();
                continue;
            }
            currentPlayer = this.nextPlayer(currentPlayer);
        }
        // Winner
        // System.out.println("ULTIMATE WINNER: Player #" + currentPlayer.getPlayerNum());
        return players.get(0).getPlayerNum();
    }

    private Player nextPlayer(Player currentPlayer){
        int index = players.indexOf(currentPlayer);
        if (index < (players.size()-1)){
            return players.get(index+1);
        } else{
            return players.get(0);
        }
    }

    public static boolean topBottom(LinkedList<Card> pile){
        if (pile.size() > 1){
            Card top = new Card(pile.get(pile.size()-1));
            Card bottom = new Card(pile.get(0));
            return top.equals(bottom);
        } else {return false;}
    }
    public static boolean doubles(LinkedList<Card> pile){
        if (pile.size() > 1){
            Card last = new Card(pile.get(pile.size()-1)); // last element
            Card penultimate = new Card(pile.get(pile.size()-2)); // penultimate element
            return last.equals(penultimate);
        } else {return false;}
    }
    public static boolean sandwich(LinkedList<Card> pile){
        if (pile.size() > 2){
            Card topBun = new Card(pile.get(pile.size()-1));
            Card botBun = new Card(pile.get(pile.size()-3));
            return topBun.equals(botBun);
        } else {return false;}
    }
}