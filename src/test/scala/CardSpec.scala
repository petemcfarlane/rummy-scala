import org.specs2.mutable.Specification

class CardSpec extends Specification {
  "A card" >> {
    val card = new Card("H", "2")
    "should have a value" >> {
      card.value must_=== "2"
    }
    "should have a suit" >> {
      card.suit must_=== "H"
    }
    "must have a valid suit, H, C, S or D" >> {
      val heart = new Card("H", "A")
      val club = new Card("C", "2")
      val spade = new Card("S", "3")
      val diamond = new Card("D", "4")

      new Card("I", "J") must throwAn[IllegalArgumentException]
    }
    "must have a valid value: 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, or A" >> {
      val twoHeart = new Card("H", "2")
      val jackDiamonds = new Card("D", "J")
      val queenClubs = new Card("C", "Q")
      val aceSpades = new Card("S", "A")
      new Card("H", "11") must throwAn[IllegalArgumentException]
    }
    "can be represented as a string" >> {
      new Card("H", "2").toString must_=== "2 of Hearts"
      new Card("D", "10").toString must_=== "10 of Diamonds"
      new Card("C", "J").toString must_=== "Jack of Clubs"
      new Card("S", "A").toString must_=== "Ace of Spades"
    }
  }
}
