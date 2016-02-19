package rummy.events

object EventStore {
  var events: List[Event] = List()

  def record(e: Event) {
//    println(f"Event: $e\n")
    events = events :+ e
  }
}
