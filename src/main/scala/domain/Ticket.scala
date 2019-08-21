package domain

import java.time.LocalDateTime

final case class Ticket(buyer : Customer, orderedAt : LocalDateTime)
{
  val plan : Plan = buyer.applicablePlans.min // 価格が最も低いプランを選ぶ
}
