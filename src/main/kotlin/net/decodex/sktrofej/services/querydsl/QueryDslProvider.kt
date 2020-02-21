package net.decodex.sktrofej.services.querydsl

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Predicate
import org.springframework.stereotype.Service


@Service
class QueryDslProvider {

//    fun addConstraintApplicationToDevice(predicate: Predicate?, application: Application): Predicate? {
//        return predicate.let {
//            val device =net.decodex.sktrofej.domain.model.QDevice.device
//            val builder = BooleanBuilder()
//            val finalPredicate: Predicate = builder.and(predicate).and(device.applications.contains(application))
//            finalPredicate
//        }
//    }
//
//    fun addConstraintApplicationToLog(predicate: Predicate?, application: Application): Predicate? {
//        return predicate.let {
//            val log =net.decodex.sktrofej.domain.model.QLog.log
//            val builder = BooleanBuilder()
//            val finalPredicate: Predicate = builder.and(predicate).and(log.application.eq(application))
//            finalPredicate
//        }
//    }

}