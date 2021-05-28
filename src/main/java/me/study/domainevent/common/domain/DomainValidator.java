package me.study.domainevent.common.domain;

@FunctionalInterface
public interface DomainValidator<T> {
    void valid(T t);
}
