import java.util.LinkedList;
import java.util.Random;

public class Deck {
    private LinkedList<Card> m_cards = new LinkedList<Card>();

    /** default constructor full 52 card deck */
    public Deck() {
        for (int r = 2; r <= 14; r++) {
            for (int s = 0; s < 4; s++) {
                m_cards.add(new Card(r, s));
            }
        }
    }

    /** copy constructor */
    public Deck(Deck deckToCopy) {
        for (Card c: deckToCopy.m_cards) {
            this.m_cards.add(new Card(c));
        }
    }

    /** toString method */ 
    public String toString() {
        String s = "";
        s += "Cards in Deck:\n";
        for (Card c: m_cards) {
            s += " - " + c + "\n";
        }
        return s;
    }

    /** size method */ 
    public int size() {
        return m_cards.size();
    }

    /** deal method */
    public Card deal() {
        Random rand = new Random();
        int idx = 0;
        if (m_cards.size() > 0) {
            idx = rand.nextInt(m_cards.size());
            Card random_card = m_cards.get(idx); // stores value of card
            m_cards.remove(idx); // removes the card from the linked list
            return random_card; // returns the stored card
        } else {
            Card ace = new Card();
            return ace; // returning IllegalArgumentException
            // workaround is extra code, which in theory should never be reached
            // ChatGPT used to understand the error
            // However, all the code is still mine though
        }
        
    }
}