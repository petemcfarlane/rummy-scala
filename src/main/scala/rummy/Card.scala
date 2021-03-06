package rummy

case class Card(s: String, v: String) {
  val suit = s match {
    case "H" => "H"
    case "C" => "C"
    case "S" => "S"
    case "D" => "D"
    case _ => throw new IllegalArgumentException("suit must be A, C, S or D")
  }

  val value = v match {
    case "2" => "2"
    case "3" => "3"
    case "4" => "4"
    case "5" => "5"
    case "6" => "6"
    case "7" => "7"
    case "8" => "8"
    case "9" => "9"
    case "10" => "10"
    case "J" => "J"
    case "Q" => "Q"
    case "K" => "K"
    case "A" => "A"
    case _ => throw new IllegalArgumentException("value must be 2 to 10, J, Q, K or A")
  }

  override def toString = {
    this.suit match {
      case "H" => value + "♥"
      case "C" => value + "♣"
      case "S" => value + "♠"
      case "D" => value + "♦"
    }
  }
}
