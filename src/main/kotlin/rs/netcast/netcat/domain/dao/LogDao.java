package rs.netcast.netcat.domain.dao;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import rs.netcast.netcat.domain.model.Application;
import rs.netcast.netcat.domain.model.Log;
import rs.netcast.netcat.domain.model.QLog;

import java.util.Optional;

public interface LogDao extends JpaRepository<Log, Long>, QuerydslPredicateExecutor<Log>, QuerydslBinderCustomizer<QLog> {

    void deleteAllByApplication(Application application);

    Optional<Log> findByMessageAndTagAndAffectedVersion(String message, String tag, String affectedVersion);

    @NotNull
    Page<Log> findAll(@Nullable Predicate predicate, @NotNull Pageable pageable);

    @Override
    default void customize(QuerydslBindings bindings, @NotNull QLog root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
}
