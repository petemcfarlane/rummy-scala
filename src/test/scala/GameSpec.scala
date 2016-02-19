import org.specs2.mutable.Specification
import rummy.{Game, Player}

class GameSpec extends Specification{
  "A game" >> {
    val p1: Player = Player("Bob")
    val p2: Player = Player("Ben")
    val game = new Game(p1, p2)
    "starts with 2 to 4 players" >> {
      game.players.length must_== 2
    }
    "each player should be dealt 7 cards" >> {
      p1.hand.size must_== 7
      p2.hand.size must_== 7
    }
    "the discarded pile should have one card" >> {
      game.discarded.length must_== 1
    }
    "the deck should contain the remaining cards." >> {
      game.deck.length must_== 52 - 7 - 7 - 1
    }
  }
}
