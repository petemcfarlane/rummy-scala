package rummy

import rummy.events.{PlayerDiscardsCard, PlayerTakesUnknownCard, PlayerTakesLastDiscardedCard, EventStore}

import scala.util.Random

case class Player(val name: String) {
  override def toString = name

  type Hand = Set[Card]

  type DiscardPile = Set[Card]

  var hand: Hand = Set()

  def dealtCard(c: Card) { hand += c }

  def takeLastDiscardedCard(discardedPile: List[Card]): List[Card] = {
    EventStore.record(new PlayerTakesLastDiscardedCard)
    discardedPile match {
      case card :: remaining => hand += card; remaining
      case _ => throw new RuntimeException("To fix...")
    }
  }

  def takeUnknownCard(deck: Deck): Deck = {
    EventStore.record(new PlayerTakesUnknownCard)
    deck.deal match {
      case (card: Some[Card], remaining) => hand += card.get; remaining
      case _ => throw new RuntimeException("To fix...")
    }
  }

  def discard(card: Card): Card = {
    EventStore.record(new PlayerDiscardsCard(card))
    hand -= card
    card
  }

  def canMeld: Boolean = Meld(hand)

  private def chooseCardToDiscard =
    hand.toList(Random.nextInt(hand.size))
}
