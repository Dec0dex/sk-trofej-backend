package rs.netcast.netcat.domain.dao

import org.springframework.data.jpa.repository.JpaRepository
import rs.netcast.netcat.domain.model.Application
import rs.netcast.netcat.domain.model.Company

interface ApplicationDao : JpaRepository<Application, Long> {

    fun findAllByCompany(company: Company): List<Application>

}