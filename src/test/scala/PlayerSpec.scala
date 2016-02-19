import org.specs2.mutable.Specification
import rummy.{Deck, Player, Card}

class PlayerSpec extends Specification{
  "A player" >> {
    "has a name" >> {
      val player = Player("Paul")
      player.toString must_== "Paul"
      player.name must_== "Paul"
    }
    "has a hand (collection of cards)" >> {
      Player("Bob").hand must be empty
    }
    "can be dealt cards" >> {
      val player = Player("Bob")
      val spadeAce = new Card("S", "A")
      val heart10 = new Card("H", "10")
      player.dealtCard(spadeAce)
      player.hand must_== Set(spadeAce)
      player.dealtCard(heart10)
      player.hand must_== Set(spadeAce, heart10)
    }
    "can take the last discarded card" >> {
      val player = Player("Bob")
      player.takeLastDiscardedCard(List(Card("H", "4"), Card("D", "2"))) should_== List(Card("D", "2"))
      player.hand must contain(Card("H", "4"))
    }
    "can take an unknown card" >> {
      val player = Player("Bob")
      player.takeUnknownCard(new Deck(List(Card("S", "A")))).cards should_== List()
      player.hand must contain(Card("S", "A"))
    }
    "can discard a card" >> {
      val player = Player("Bob")
      player.dealtCard(Card("H", "3"))
      player.dealtCard(Card("D", "4"))
      player.dealtCard(Card("D", "5"))

      player.discard(Card("D", "4")) should_== Card("D", "4")
      player.hand should_== Set(Card("H", "3"), Card("D", "5"))
    }

    "can win if their hand melds" >> {
      val player = Player("Bob")
      player.dealtCard(Card("D", "2"))
      player.dealtCard(Card("D", "3"))
      player.dealtCard(Card("D", "4"))
      player.dealtCard(Card("D", "5"))
      player.dealtCard(Card("D", "6"))
      player.dealtCard(Card("D", "7"))
      player.dealtCard(Card("D", "9"))

      player.canMeld should_== false

      player.discard(Card("D", "9"))
      player.dealtCard(Card("D", "8"))

      player.canMeld should_== true
    }
  }
}
