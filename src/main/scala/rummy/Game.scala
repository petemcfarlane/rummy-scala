package rummy

import rummy.events._

import scala.io.StdIn._

class Game(val players: Player*) {
  EventStore.record(new StartGame)

  for (p <- players) yield EventStore.record(new PlayerJoins(p))

  var deck: Deck = new Deck
  EventStore.record(new DeckIsShuffled(deck))

  var discarded: List[Card] = List()

  def deal() {
    EventStore.record(new PlayersDealtHands)
    (1 to 7).foreach(_ => {
      players.foreach(p => {
        deck.deal match {
          case (card, remaining) => p.dealtCard(card.get); deck = remaining
        }
      })
    })
    deck.deal match {
      case (card, remaining) => discarded = card.get :: discarded; deck = remaining
    }
  }

  deal()
}

object Game {
  def main(args: Array[String]): Unit = {
    println("Welcome to a new game of rummy.\n")
    val player = Player(readLine("What is your name?\n"))
    println(f"Hi $player\n")

    val g = new Game(player)

    while (!player.canMeld) {
      println("Your current hand: " + player.hand.mkString(", ") + "\n")
      println("Last discarded card: " + g.discarded.head + "\n")
      //      println("Cards in deck: (" + g.deck.cards.size + ") " + g.deck.cards.mkString(", ") + "\n")
      EventStore.record(new PlayerEvaluatesHand)

      println("Do you want to take an unknown card [1] or the last discarded card [2]?\n")
      readInt() match {
        case 1 => g.deck = player.takeUnknownCard(g.deck)
        case 2 => player.takeLastDiscardedCard(g.discarded)
        case _ => throw new RuntimeException("Invalid input!")
      }

      println("Which card do you want to discard?\n")
      val indexedCards = player.hand.toIndexedSeq.zipWithIndex
      indexedCards.foreach {
        case (c, i) => println(f"[$i] $c")
      }
      val card = indexedCards(readInt())._1
      g.discarded = player.discard(card) :: g.discarded
      println("\nDiscarded: " + card + "\n")
    }


    EventStore.record(new PlayerWinsGame)
    println(f"!!! RUMMMY !!!\n$player has won!")

    EventStore.events foreach(e => println(e))
  }
}
