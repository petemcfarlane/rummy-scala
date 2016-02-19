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
    "can take a turn" >> {
      val p = Player("Bob")
      p.hand = Set(Card("D","2"), Card("D","3"), Card("D","4"), Card("D","5"), Card("D","6"), Card("D","7"), Card("C","4"))
      val discarded = List(Card("S", "A"))
      val deck = new Deck(List(Card("S", "2"), Card("S", "3")))
      val ret = p.takeTurn(discarded.head, deck)
      1 must_== 1
    }
    "can decide if they want a card" >> {
      val p = Player("Bob")
      p.hand = Set(Card("D","2"), Card("D","3"), Card("D","4"), Card("D","5"), Card("D","6"), Card("D","7"), Card("C","4"))
      p.wantsCard(Card("D","9")) must_== false
      p.wantsCard(Card("D","8")) must_== true
    }
  }
}
