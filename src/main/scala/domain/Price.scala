package domain

final case class Price(private val value: Int) extends Ordered[Price] {
  private val possiblePrices = Seq(900, 1000, 1100, 1300, 1400, 1500, 1600, 1800)
  require(possiblePrices contains value)

  override def compare(that: Price) = value compare that.value
}
