import scala.util.Random

class Player(val name: String) {
  def this() = this(List("Spud", "Talus", "Shrimp", "Scrappie", "Oxyroid", "Isuk")(Random.nextInt(6)))

  override def toString = name

  var hand: Set[Card] = Set()

  def dealtCard(c: Card) { hand += c }
}
