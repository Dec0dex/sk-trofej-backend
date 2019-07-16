package rs.netcast.netcat.services.querydsl

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Predicate
import org.springframework.stereotype.Service
import rs.netcast.netcat.domain.model.Application
import rs.netcast.netcat.domain.model.QDevice


@Service
class QueryDslProvider {

    fun addConstraintApplicationToDevice(predicate: Predicate?, application: Application): Predicate? {
        return predicate?.let {
            val device = QDevice.device
            val builder = BooleanBuilder()
            val finalPredicate: Predicate = builder.and(predicate).and(device.applications.contains(application))
            finalPredicate
        } ?: predicate
    }

}