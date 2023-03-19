package io.jeidiiy.springbootwebservice.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity {
    @CreatedDate
    var createdDate: LocalDateTime = LocalDateTime.MIN
        private set

    @LastModifiedDate
    var modifiedDate: LocalDateTime = LocalDateTime.MIN
        private set
}
