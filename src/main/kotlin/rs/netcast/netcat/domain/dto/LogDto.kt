package rs.netcast.netcat.domain.dto

import rs.netcast.netcat.domain.model.Log
import rs.netcast.netcat.domain.model.LogLevel
import java.util.*

class LogDto() {

    constructor(log: Log) : this() {
        level = log.level
        tag = log.tag
        message = log.message
        affectedVersion = log.affectedVersion
        createdAt = log.createdAt
        updatedAt = log.createdAt
        application = ApplicationDto(log.application)
        device = DeviceDto(log.device)
        timestamp = log.timeStamp
        id = log.id
    }

    lateinit var level: LogLevel
    lateinit var tag: String
    lateinit var message: String
    lateinit var affectedVersion: String
    lateinit var createdAt: Date
    lateinit var updatedAt: Date
    lateinit var application: ApplicationDto
    lateinit var device: DeviceDto
    var timestamp: Long = 0
    var id: Long = 0

}