class Game(val players: Player*) {
  var deck: Deck = new Deck
  var discarded: List[Card] = List()

  def deal() {
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
