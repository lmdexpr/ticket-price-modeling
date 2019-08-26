package domain

import domain.Certificate._
import java.time.{LocalDate, LocalDateTime}

final case class Customer(birthDay : LocalDate, certificates : Seq[Certificate])
{
  lazy val wasBorn = LocalDate.now().isAfter(birthDay) // 生まれてて欲しい
  lazy val illegalStudentCertificate = certificates.contains(UniversityStudent) && certificates.contains(HighSchoolStudent) // 同時に大学生であり高校生であることはない

  require(wasBorn && !illegalStudentCertificate)

  val age : Age = Age(birthDay)
  private def overAge(threshould : Int)(implicit showTime : LocalDateTime)  : Boolean = age.ageAt(showTime.toLocalDate) >= threshould
  private def underAge(threshould : Int)(implicit showTime : LocalDateTime) : Boolean = age.ageAt(showTime.toLocalDate) <= threshould

  def applicablePlans(implicit showTime : LocalDateTime) : Seq[Plan] = certificates.map {
    case CinemaCitizen         => if (overAge(60)) Plan.CinemaSenior else Plan.CinemaCitizen
    case UniversityStudent     => Plan.UniversityStudent
    case HighSchoolStudent     => Plan.HighSchoolStudent
    case MICard                => Plan.MICard
    case ParkingSurvice        => Plan.ParkingSurvice
    case DisabilityCertificate =>
      if (certificates.contains(HighSchoolStudent) || underAge(12)) Plan.DisabilityStudent
      else Plan.Disability
  } :+ (if (overAge(70)) Plan.Senior else if (underAge(12)) Plan.Child else Plan.Adult)
}
