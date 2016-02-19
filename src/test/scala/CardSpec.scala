import org.specs2.mutable.Specification
import rummy.Card

class CardSpec extends Specification {
  "A card" >> {
    val card = Card("H", "2")
    "should have a value" >> {
      card.value must_=== "2"
    }
    "and a suit" >> {
      card.suit must_=== "H"
    }
    "must have a valid suit, H, C, S or D" >> {
      Card("H", "A")
      Card("C", "2")
      Card("S", "3")
      Card("D", "4")

      Card("I", "J") must throwAn[IllegalArgumentException]
    }
    "must have a valid value: 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, or A" >> {
      Card("H", "2")
      Card("D", "J")
      Card("C", "Q")
      Card("S", "A")
      Card("H", "11") must throwAn[IllegalArgumentException]
    }
    "can be represented as a string" >> {
      Card("H", "2").toString must_=== "2 of Hearts"
      Card("D", "10").toString must_=== "10 of Diamonds"
      Card("C", "J").toString must_=== "Jack of Clubs"
      Card("S", "A").toString must_=== "Ace of Spades"
    }
  }
}
