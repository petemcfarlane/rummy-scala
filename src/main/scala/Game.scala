class Game(val players: Player*) {
  var deck: Deck = new Deck
  var discarded: List[Card] = List()

  def deal() {
    (1 to 7).foreach(c => {
      players.foreach(p => {
        val (card, d) = deck.deal
        p.dealtCard(card)
        deck = d
      })
    })
  }

  deal()
}
