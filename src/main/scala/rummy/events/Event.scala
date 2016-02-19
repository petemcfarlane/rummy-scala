package rummy.events

import rummy.{Card, Deck, Player}

trait Event

class StartGame extends Event

class PlayerJoins(player: Player) extends Event

class DeckIsShuffled(deck: Deck) extends Event

class PlayersDealtHands extends Event

class PlayerEvaluatesHand extends Event

class PlayerTakesLastDiscardedCard extends Event

class PlayerTakesUnknownCard extends Event

class PlayerDiscardsCard(card: Card) extends Event

class PlayerWinsGame extends Event