package rs.netcast.netcat.domain.dto

import rs.netcast.netcat.domain.model.LogLevel


class LogCreationDto {

    lateinit var level: LogLevel
    lateinit var tag: String
    lateinit var message: String
    lateinit var affectedVersion: String
    lateinit var accessToken: String
    lateinit var mac: String
    lateinit var os: String
    lateinit var ip: String
    var timestamp: Long = 0
}