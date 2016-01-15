import org.specs2.mutable.Specification

class MeldSpec extends Specification {
  "Determines if a hand can meld" >> {
    "are all cards the same suit" >> {
      Meld.sameSuit(Set(Card("H", "2"), Card("D", "2"))) must_== false
      Meld.sameSuit(Set(Card("S", "5"), Card("S", "9"))) must_== true
    }
    "are all cards in sequence" >> {
      Meld.inSequence(Set(Card("H", "2"), Card("H", "Q"), Card("D", "K"), Card("D", "4"), Card("S", "3"))) must_== false
      Meld.inSequence(Set(Card("H", "2"), Card("D", "4"), Card("S", "6"), Card("S", "5"), Card("S", "3"))) must_== true
    }
    "are all cards of the same suit in sequence" >> {
      Meld.inSuitAndSequence(Set(Card("H","4"), Card("H","6"), Card("H","5"), Card("H","7"))) must_== true
      Meld.inSuitAndSequence(Set(Card("D","4"), Card("H","6"), Card("H","5"), Card("H","7"))) must_== false
      Meld.inSuitAndSequence(Set(Card("H","2"), Card("H","6"), Card("H","5"), Card("H","7"))) must_== false
    }
    "are all the cards of the same value" >> {
      Meld.sameValue(Set(Card("H","2"), Card("D","2"), Card("C","2"), Card("S","2"))) must_== true
      Meld.sameValue(Set(Card("H","2"), Card("D","3"), Card("C","2"), Card("S","2"))) must_== false
    }
    val hand = Set(Card("S","2"), Card("S","3"), Card("S","4"), Card("S","5"), Card("S","7"), Card("S","8"), Card("S","9"))
    "are 35 permutations of making melds of 4 and 3 from a hand of 7" >> {
      Meld.permutationsFrom7(hand).length must_== 35
    }
    "can say if 3 or 4 cards are same suit and sequential, or all same value" >> {
      Meld.winning(Set(Card("D","9"), Card("D","10"), Card("D","J"))) must_== true
      Meld.winning(Set(Card("D","9"), Card("D","10"), Card("D","J"), Card("D","Q"))) must_== true
      Meld.winning(Set(Card("H","8"), Card("D","8"), Card("S","8"))) must_== true
      Meld.winning(Set(Card("H","6"), Card("D","6"), Card("S","6"), Card("C","6"))) must_== true

      Meld.winning(Set(Card("D","9"), Card("D","8"), Card("D","J"))) must_== false
      Meld.winning(Set(Card("D","9"), Card("D","10"), Card("D","J"), Card("D","K"))) must_== false
      Meld.winning(Set(Card("H","8"), Card("D","8"), Card("S","2"))) must_== false
      Meld.winning(Set(Card("H","6"), Card("D","4"), Card("S","6"), Card("C","6"))) must_== false
    }
    "can find 2 correct melds (of 3 and 4) from a hand" >> {
      Meld.findFirstWinningMelds(hand).get must_== (
        Set(Card("S","2"), Card("S","3"), Card("S","4"), Card("S","5")),
        Set(Card("S","7"), Card("S","8"), Card("S","9")))
    }

    "can say if a given hand can meld or not" >> {
      Meld(hand) must_== true
    }
  }
}
