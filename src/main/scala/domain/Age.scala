package domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit

final case class Age(private val birthday: LocalDate) extends Ordered[Age] {
  def at(now: LocalDate) = ChronoUnit.YEARS.between(birthday, now)

  require(at(LocalDate.now()) >= 0)

  override def compare(that: Age) = {
    val now = LocalDate.now();
    at(now) compare that.at(now)
  }
}
