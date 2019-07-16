package rs.netcast.netcat.domain.dto

import rs.netcast.netcat.domain.model.Device

class DeviceDto() {

    constructor(device: Device) : this() {
        mac = device.mac
        os = device.os
        ip = device.ip
        email = device.email
    }

    lateinit var mac: String
    lateinit var os: String
    lateinit var ip: String
    var email: String? = null

}