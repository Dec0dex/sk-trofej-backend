package rs.netcast.netcat.domain.dao

import org.springframework.data.jpa.repository.JpaRepository
import rs.netcast.netcat.domain.model.Application
import rs.netcast.netcat.domain.model.Log

interface LogDao : JpaRepository<Log, Long> {

    fun deleteAllByApplication(application: Application)

}