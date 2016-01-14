import scala.util.Random

class Player(val name: String) {
  def this() = this(List("Spud", "Talus", "Shrimp", "Scrappie", "Oxyroid", "Isuk")(Random.nextInt(6)))

  override def toString = name

  type Hand = Set[Card]

  var hand: Hand = Set()

  def dealtCard(c: Card) { hand += c }

  def takeTurn(lastDiscarded: Card, deck: Deck): (Card, Deck) = {
    var remaining = deck

    if (wantsCard(lastDiscarded))
      hand += lastDiscarded
    else {
      deck.deal match {
        case (card, d) => hand += card.get; remaining = d
      }
    }

    if (canMeld(hand)) {
      // win the game
    }

    val cardToDiscard = chooseCardToDiscard
    hand -= cardToDiscard

    (cardToDiscard, remaining)
  }

  private def wantsCard(card: Card): Boolean = canMeld(hand + card)

  private def canMeld(hand: Hand): Boolean = false

  private def chooseCardToDiscard = hand.toList(Random.nextInt(hand.size))
}
