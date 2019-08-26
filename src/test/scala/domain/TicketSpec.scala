package domain

import java.time.{LocalDate, LocalDateTime}
import domain._
import domain.Certificate._

import org.scalatest.{FreeSpec, Matchers}

class TicketSpec extends FreeSpec with Matchers {
  "Ticket" - {
    "Holiday" - {
      "CinemaCitizen" in {
        val customer = Customer(LocalDate.of(1999, 6, 1), Seq(CinemaCitizen))
        val ticket   = Ticket(customer, LocalDateTime.of(2019, 7, 14, 12, 0))
        ticket.plan shouldBe Plan.CinemaCitizen
        ticket.price shouldBe Price(1300)
      }
      "CinemaSenior" in {
        val customer = Customer(LocalDate.of(1959, 6, 1), Seq(CinemaCitizen))
        val ticket   = Ticket(customer, LocalDateTime.of(2019, 7, 14, 12, 0))
        ticket.plan shouldBe Plan.CinemaSenior
        ticket.price shouldBe Price(1000)
      }
      "No discount" in {
        val customer = Customer(LocalDate.of(1999, 6, 1), Seq())
        val ticket   = Ticket(customer, LocalDateTime.of(2019, 7, 14, 12, 0))
        ticket.plan shouldBe Plan.Adult
        ticket.price shouldBe Price(1800)
      }
      "Many Certificates" in {
        val customer = Customer(LocalDate.of(2002, 6, 1), Seq(HighSchoolStudent, CinemaCitizen, MICard, DisabilityCertificate))
        val ticket   = Ticket(customer, LocalDateTime.of(2019, 7, 14, 12, 0))
        ticket.plan shouldBe Plan.DisabilityStudent
        ticket.price shouldBe Price(900)
      }
    }
  }
}
