package domain

sealed trait Certificate
object Certificate {
  case object CinemaCitizen     extends Certificate
  case object UniversityStudent extends Certificate
  case object HighSchoolStudent extends Certificate
  // case object ElementarySchoolStudent extends Certificate // 学生証の提示が求められていない。年齢で判断する。
  case object MICard                extends Certificate
  case object ParkingSurvice        extends Certificate // 提携駐車場でサービスチケットが配布される
  case object DisabilityCertificate extends Certificate
}
