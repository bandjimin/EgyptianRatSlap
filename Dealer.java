import java.util.LinkedList;

public class Dealer {
    private Deck m_deck;

    // default constructor
    public Dealer() {
        m_deck = new Deck();
    }

    // deals method
    public LinkedList<Card> deals(int n) {
        LinkedList<Card> dealt_cards = new LinkedList<Card>();
        for (int i = 0; i < n; i++) {
            if (m_deck.size() == 0) {
                break;
            }
            dealt_cards.add(m_deck.deal());
        }
        return dealt_cards;
    }
}