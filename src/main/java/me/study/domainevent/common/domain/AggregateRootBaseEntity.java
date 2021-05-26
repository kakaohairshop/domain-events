package me.study.domainevent.common.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.function.UnaryOperator;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AggregateRootBaseEntity<T extends AbstractAggregateRoot<T>> extends AbstractAggregateRoot<T> {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public T apply(UnaryOperator<T> operator) {
        return operator.apply((T) this);
    }

    public T publish(UnaryOperator<T> operator) {
        return apply(operator);
    }

    public <U> U map(Function<? super T, ? extends U> mapper) {
        return mapper.apply((T) this);
    }
}
