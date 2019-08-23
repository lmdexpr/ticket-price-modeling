package domain

final case class Price(breachEncapsulationOfValue : Int) extends Ordered[Price]
{
  private val possiblePrices = Seq(900, 1000, 1100, 1300, 1400, 1500, 1600, 1800)

  require(possiblePrices contains breachEncapsulationOfValue)

  override def compare(that : Price) = breachEncapsulationOfValue compare that.breachEncapsulationOfValue
}
