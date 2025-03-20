public class Card {
    /** 2-10, J, Q, K, A */
    private int m_rank;
    /** represents the suit of a card as an integer (D, C, H, S) */
    private int m_suit;

    /** a default constructor which creates an Ace of Spades */
    public Card() {
        m_rank = 14; // ACE = 14
        m_suit = 1; // SPADES = 1
    }

    /** an overloaded constructor */
    public Card(int rank, int suit) {
        m_rank = rank;
        m_suit = suit;
    }

    /** a copy constructor */
    public Card(Card copyCard) {
        this.m_rank = copyCard.m_rank;
        this.m_suit = copyCard.m_suit;
    }

    /** setter for rank */
    public void setRank(int rank) {
        m_rank = rank;
    }

    /** setter for suit */
    public void setSuit(int suit) {
        m_suit = suit;
    }
    
    /** getter for rank */
    public int getRank() {
        return m_rank;
    }

    /** getter for suit */
    public int getSuit() {
        return m_suit;
    }

    /** getter of rank for toString */
    public String strRank() {
        String s = "" + this.m_rank;
        String[] rankArr = {"Jack", "Queen", "King", "Ace"};
        if (this.m_rank > 10 && this.m_rank <= 14) {s = rankArr[this.m_rank-11];}
        return s;
    }

    /** getter of suit for toString */
    public String strSuit() {
        String[] suitArr = {"Hearts", "Spades", "Clubs", "Diamonds"};
        return suitArr[this.m_suit];
    }

    /** toString method that nicely displays the suit and rank of the card
     *  ex. "Queen of Hearts"
     */
    public String toString() {
        return this.strRank() + " of " + this.strSuit();
    }

    /** equals method */
    public boolean equals(Object o) {
        // checks memory location
        if (this == o) {return true;}

        // checks if object is car
        if (!(o instanceof Card)) {return false;}

        // turn object into card
        Card c = (Card) o;

        // assume 2 cards are equal if their ranks are equal
        // && this.m_suit == c.m_suit not necessary because suit is irrelevant
        if (this.m_rank == c.m_rank) {return true;}

        return false;
    }

    public static final int HEARTS = 0;
    public static final int SPADES = 1;
    public static final int CLUBS = 2;
    public static final int DIAMONDS = 3;

    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static final int ACE = 14;

}
