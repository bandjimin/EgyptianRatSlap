import java.util.LinkedList;
import java.util.Scanner;

public class Driver{
    public static void main(String[] args){
        // Test Cases
        /*
        Game test = new Game(3);
        Dealer dealer = new Dealer();
        Player zero = new Player(0, dealer.deals(13), "top bottom");
        LinkedList<Card> pile = dealer.deals(5);

        Card card_a = new Card(2,Card.CLUBS);
        Card card_b = new Card(3,Card.CLUBS);
        Card card_c = new Card(3,Card.HEARTS);
        Card card_d = new Card(7,Card.HEARTS);
        Card card_e = new Card(2,Card.SPADES);
        Card card_f = new Card(7,Card.DIAMONDS);

        LinkedList<Card> cards = new LinkedList<Card>();
        cards.add(card_a);
        cards.add(card_b);
        cards.add(card_c);
        System.out.println(test.doubles(cards));
        System.out.println(test.topBottom(cards));
        System.out.println(test.sandwich(cards));
        cards.add(card_d);
        cards.add(card_e);
        System.out.println(test.doubles(cards));
        System.out.println(test.topBottom(cards));
        System.out.println(test.sandwich(cards));
        cards.add(card_f);
        System.out.println(test.doubles(cards));
        System.out.println(test.topBottom(cards));
        System.out.println(test.sandwich(cards));
        System.out.println(cards);
        */

        // System.out.println(test.getPlayers().get(0));
        // System.out.println(test.getPlayers().get(1));

        // test.play();
        

        // Simulated Game
        Scanner scnr = new Scanner(System.in);
        int numPlayers = 0;
        while (!(numPlayers >= 2 && numPlayers <= 6)){
            System.out.print("How many simulated players are in this game (2-6)? ");
            numPlayers = scnr.nextInt();
        }
        Game game = new Game(numPlayers);
        int winner = game.play();
        System.out.println("GAMEOVER");
        System.out.println("ULTIMATE WINNER Player #" + winner);
    }
}