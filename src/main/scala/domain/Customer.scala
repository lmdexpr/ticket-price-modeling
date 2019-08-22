package domain

import domain.Certificate._
import java.time.LocalDate

final case class Customer(birthDay : LocalDate, certificates : Seq[Certificate]) 
{
  require(! Seq(UniversityStudent, HighSchoolStudent).subsetOf(certificates)) // 同時に大学生であり高校生であることはない。もっと色々ダメな条件があるので良いやり方が欲しい。

  val age : Age = Age(birthDay)
  def isChild : Boolean = age <= 12

  val applicablePlans : Seq[Plan] = certificates map (_ match {
    case CinemaCitizen         => if (age >= 60) Plan.CinemaSenior else Plan.CinemaCitizen
    case UniversityStudent     => Plan.UniversityStudent
    case HighSchoolStudent     => Plan.HighSchoolStudent
    case MICard                => Plan.MICard
    case ParkingSurvice        => Plan.ParkingSurvice
    case DisabilityCertificate => if (certificates contains HighSchoolStudent || isChild) Plan.DisabilityStudent else Plan.Disability
  }) :+ (if (age >= 70) Plan.Senior else if (isChild) Plan.Child else Plan.Adult)
}
