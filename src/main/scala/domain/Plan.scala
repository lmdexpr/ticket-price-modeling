package domain

import java.time.{LocalDateTime, DayOfWeek}

sealed case class Plan(
    private val weekday: Price,
    private val weekdayLate: Price,
    private val holiday: Price,
    private val holidayLate: Price,
    private val movieDay: Option[Price]
) extends Ordered[Plan] {
  def price(showTime: LocalDateTime): Price = {
    val isHoliday     = Seq(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY) contains showTime.getDayOfWeek
    val daytimeOrLate = if (showTime.getHour <= 20) (_: Price) else (_: Price)

    if (showTime.getDayOfMonth == 1 && movieDay.isDefined) movieDay.get
    else if (isHoliday) daytimeOrLate(holiday, holidayLate)
    else daytimeOrLate(weekday, weekdayLate)
  }

  override def compare(that: Plan) = {
    val now = LocalDateTime.now()
    price(now) compare that.price(now) // 一番高いところで比較しとけば大丈夫
  }
}

object Plan {
  object CinemaCitizen     extends Plan(Price(1000), Price(1000), Price(1300), Price(1000), Some(Price(1100)))
  object CinemaSenior      extends Plan(Price(1000), Price(1000), Price(1000), Price(1000), Some(Price(1000)))
  object Adult             extends Plan(Price(1800), Price(1300), Price(1800), Price(1300), Some(Price(1100)))
  object Senior            extends Plan(Price(1100), Price(1100), Price(1100), Price(1100), Some(Price(1100)))
  object UniversityStudent extends Plan(Price(1500), Price(1300), Price(1300), Price(1300), Some(Price(1100)))
  object HighSchoolStudent extends Plan(Price(1000), Price(1000), Price(1000), Price(1000), Some(Price(1000)))
  object Child             extends Plan(Price(1000), Price(1000), Price(1000), Price(1000), Some(Price(1000)))
  object Disability        extends Plan(Price(1000), Price(1000), Price(1000), Price(1000), Some(Price(1000)))
  object DisabilityStudent extends Plan(Price(900),  Price(900),  Price(900),  Price(900),  Some(Price(900)))
  object MICard            extends Plan(Price(1600), Price(1300), Price(1600), Price(1300), None)
  object ParkingSurvice    extends Plan(Price(1400), Price(1100), Price(1400), Price(1100), None)
}
