package rummy

import scala.util.Random

case class Player(val name: String) {
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

    if (Meld.aWinningCombinationExists(hand)) {
      // win the game
    }

    val cardToDiscard = chooseCardToDiscard
    hand -= cardToDiscard

    (cardToDiscard, remaining)
  }

  def wantsCard(card: Card): Boolean =
    Meld.aWinningCombinationExists(hand + card)

  private def chooseCardToDiscard =
    hand.toList(Random.nextInt(hand.size))
}
