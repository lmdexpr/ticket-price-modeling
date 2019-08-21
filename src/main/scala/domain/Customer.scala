package domain

import java.time.LocalDate

final case class Customer(birthDay : LocalDate, anyCertificate : Option[Certificate]) 
{
  val age : Age = Age(birthDay)

  val applicablePlans : Seq[Plan] = // TODO: 適用できる料金プランのリストを計算する
}
