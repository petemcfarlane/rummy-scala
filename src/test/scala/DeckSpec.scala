import org.specs2.mutable.Specification

class DeckSpec extends Specification{
  "A deck" >> {
    "should contain 52 cards by default" >> {
      new Deck().cards.length should_=== 52
    }
    "should be shuffled by default" >> {
      new Deck().cards should_!= new Deck().cards
    }
    "can deal a card" >> {
      val deck = new Deck
      val (card, remaining) = deck.deal
      card must beAnInstanceOf[Option[Card]]
      remaining.cards must not contain card.get
    }
    "should have a length to represent the number of cards in it" >> {
      val deck = new Deck
      deck.length should_== 52
      val (_, remaining) = deck.deal
      remaining.length should_== 51
    }
  }
}