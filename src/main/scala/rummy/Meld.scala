package rummy

object Meld {

  def apply(hand: Set[Card]): Boolean = findFirstWinningMelds(hand) match {
    case Some(_) => true
    case _ => false
  }

  def isStraight(hand: Set[Card]): Boolean = {
    println(hand.toList.sortBy(_.suit))
    false
  }

  def sameSuit(hand: Set[Card]): Boolean = hand.groupBy(c => c.suit).size == 1

  def inSequence(hand: Set[Card]): Boolean = {
    val valueMap = Map("2" -> 2, "3" -> 3, "4" -> 4, "5" -> 5, "6" -> 6, "7" -> 7, "8" -> 8, "9" -> 9, "10" -> 10, "J" -> 11, "Q" -> 12, "K" -> 13, "A" -> 14)
    val orderedHand = hand.toList.sortWith((a, b) => {
      valueMap(a.value) < valueMap(b.value)
    }).map(c => valueMap(c.value))

    orderedHand == (orderedHand.head until (orderedHand.head + orderedHand.length)).toList
  }

  def sameValue(hand: Set[Card]): Boolean = hand.groupBy(c => c.value).size == 1

  def inSuitAndSequence(hand: Set[Card]): Boolean = sameSuit(hand) && inSequence(hand)

  def isFlush(hand: Set[Card]): Boolean = true

  def permutationsFrom7(hand: Set[Card]) = {
    if (hand.size != 7) throw new IllegalArgumentException("hand should contain 7 cards, not " + hand.size)
    hand.toList.combinations(4).map((four) => {
      (four.toSet, hand -- four.toSet)
    })
  }

  def winning(hand: Set[Card]) = sameValue(hand) || inSuitAndSequence(hand)

  def findFirstWinningMelds(hand: Set[Card]): Option[(Set[Card], Set[Card])] = {
    permutationsFrom7(hand).find {
      case (four, three) if winning(four) && winning(three) => true
      case _ => false
    }
  }

  def aWinningCombinationExists(hand: Set[Card]): Boolean =
    hand.toList.combinations(7).exists((h) => Meld(h.toSet))
}
