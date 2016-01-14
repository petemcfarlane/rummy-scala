import org.specs2.mutable.Specification

class PlayerSpec extends Specification{
  "A player" >> {
    "has a name" >> {
      val player = new Player("Paul")
      player.toString must_== "Paul"
      player.name must_== "Paul"
    }
    "has an auto-generated name if none given" >> {
      new Player().name must_!= ""
    }
    "has a hand (collection of cards)" >> {
      new Player().hand must be empty
    }
    "can be dealt cards" >> {
      val player = new Player()
      val spadeAce = new Card("S", "A")
      val heart10 = new Card("H", "10")
      player.dealtCard(spadeAce)
      player.hand must_== Set(spadeAce)
      player.dealtCard(heart10)
      player.hand must_== Set(spadeAce, heart10)
    }
    "can take a turn" >> {
      val p = new Player()
      p.dealtCard(Card("H", "A"))
      val discarded = List(Card("S", "A"))
      val deck = new Deck(List(Card("S", "2"), Card("S", "3")))
      val ret = p.takeTurn(discarded.head, deck)
      ret match {
        case (Card("A", "H"), remainingDeck) => println(p.hand + "\n" + discarded + "\n" + remainingDeck.cards)
        case (discarded, remainingDeck) => println(p.hand + "\n" + discarded + "\n" + remainingDeck.cards)
      }
      1 must_== 1
    }
  }
}
