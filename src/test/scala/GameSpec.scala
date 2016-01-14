import org.specs2.mutable.Specification

class GameSpec extends Specification{
  "A game" >> {
    val p1: Player = new Player
    val p2: Player = new Player
    val game = new Game(p1, p2)
    "starts with 2 to 4 players" >> {
      game.players must not be empty
    }
    "each player should be dealt 7 cards" >> {
      p1.hand.size must_== 7
      p2.hand.size must_== 7
      game.deck.length must_== 52 - 7 - 7
    }
  }
}