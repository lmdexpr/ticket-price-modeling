package domain

import java.time.LocalDateTime

final case class Ticket(buyer : Customer, showTime : LocalDateTime)
{
  val plan : Plan = buyer.applicablePlans(showTime).min // 価格が最も低いプランを選ぶ

  def price : Price = plan.price(showTime)
}
