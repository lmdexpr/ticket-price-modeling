package domain

import java.time.{LocalDate, LocalDateTime}
import java.time.temporal.ChronoUnit

final case class Age(private birthday : LocalDate) extends Ordered[Age] {
  def ageAt(now : LocalDate) = ChronoUnit.YEARS.between(birthday, now)

  require(ageAt(LocalDateTime.now()) >= 0)

  override def compare(that : Age) = {
    val now = LocalDateTime.now();
    ageAt(now) compare that.ageAt(now)
  }
}
