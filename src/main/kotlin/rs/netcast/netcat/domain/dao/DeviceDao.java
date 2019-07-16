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
import rs.netcast.netcat.domain.model.Device;
import rs.netcast.netcat.domain.model.QDevice;

public interface DeviceDao extends JpaRepository<Device, String>, QuerydslPredicateExecutor<Device>, QuerydslBinderCustomizer<QDevice> {

    @NotNull
    Page<Device> findAll(@Nullable Predicate predicate, @NotNull Pageable pageable);

    @Override
    default void customize(QuerydslBindings bindings, @NotNull QDevice root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
}
