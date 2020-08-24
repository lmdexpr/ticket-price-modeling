package domain

import java.time.LocalDateTime

final case class Ticket(private val buyer: Customer, private val showTime: LocalDateTime) {
  def plan: Plan   = buyer.applicablePlans(showTime).min // 価格が最も低いプランを選ぶ
  def price: Price = plan.price(showTime)
}
