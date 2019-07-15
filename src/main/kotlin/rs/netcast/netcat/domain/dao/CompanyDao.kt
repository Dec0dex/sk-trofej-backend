package rs.netcast.netcat.domain.dao

import org.springframework.data.jpa.repository.JpaRepository
import rs.netcast.netcat.domain.model.Company

interface CompanyDao : JpaRepository<Company, Long>