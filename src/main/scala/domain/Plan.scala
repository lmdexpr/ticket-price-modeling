package domain

import java.time.{LocalDate, DayOfWeek}

sealed case class Plan(weekday : Price, weekdayLate : Price, holiday : Price, holidayLate : Price, movieDay : Option[Price]) extends Ordered[Plan]
{
  def price(showTime : ShowTime, day : LocalDate) : Price = {
    val isHoliday = Seq(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY) contains day.getDayOfWeek
    showTime match {
      case DayTime  => if (isHoliday) holiday else weekday
      case LateShow => if (isHoliday) holidayLate else weekdayLate
      case MovieDay => movieDay.getOrElse(if (isHoliday) holiday else weekday) // TODO: レイトショーを考慮していない。どうせ日付もらってるからその辺でうまくやろう。
    }
  }

  override def compare(that : Plan) = {
    val now = LocalDate.now()
    price(DayTime, now) compare that.price(DayTime, now) // 一番高いところで比較しとけば大丈夫
  }
}

object Plan
{
  case object CinemaCitizen         extends Plan(Price(1000), Price(1000), Price(1300), Price(1000), Some(Price(1100)))
  case object CinemaSenior          extends Plan(Price(1000), Price(1000), Price(1000), Price(1000), Some(Price(1000)))
  case object Adult                 extends Plan(Price(1800), Price(1300), Price(1800), Price(1300), Some(Price(1100)))
  case object Senior                extends Plan(Price(1100), Price(1100), Price(1100), Price(1100), Some(Price(1100)))
  case object UniversityStudent     extends Plan(Price(1500), Price(1300), Price(1300), Price(1300), Some(Price(1100)))
  case object HighSchoolStudent     extends Plan(Price(1000), Price(1000), Price(1000), Price(1000), Some(Price(1000)))
  case object Child                 extends Plan(Price(1000), Price(1000), Price(1000), Price(1000), Some(Price(1000)))
  case object Disability            extends Plan(Price(1000), Price(1000), Price(1000), Price(1000), Some(Price(1000)))
  case object DisabilityStudent     extends Plan( Price(900),  Price(900),  Price(900),  Price(900), Some(Price(900)))
  case object MICard                extends Plan(Price(1600), Price(1300), Price(1600), Price(1300), None)
  case object ParkingSurvice        extends Plan(Price(1400), Price(1100), Price(1400), Price(1100), None)
}

sealed trait ShowTime

object ShowTime
{
  case object DayTime  extends ShowTime
  case object LateShow extends ShowTime
  case object MovieDay extends ShowTime
}
