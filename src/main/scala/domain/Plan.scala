package domain

//TODO: write plan classes
// class Plan

case class Price(value : Int) extends Ordered[Price]
{
  private val possiblePrices = Seq(900, 1000, 1100, 1300, 1400, 1500, 1600, 1800)

  require(possiblePrices contains value)

  override def compare(that : Price) = value compare that.value
}

