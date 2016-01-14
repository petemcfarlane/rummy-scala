import scala.util.Random

class Deck(val cards: List[Card]) {
  def this() = this(Random.shuffle(for {
    suit <- List("H", "D", "C", "S")
    value <- List("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A")
  } yield new Card(suit, value)))

  def deal: (Option[Card], Deck) =
    if (cards.nonEmpty) (Some(cards.head), new Deck(cards.tail))
    else (None, new Deck(cards))

  def length: Int = this.cards.length
}
