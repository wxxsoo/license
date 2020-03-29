package license.entity.license.maintenance;

import com.querydsl.jpa.impl.JPAQueryFactory;
import license.entity.license.QLicense;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by wansoo.
 * User: accomplishlee
 */

public class MaintenanceRepositoryImpl implements MaintenanceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MaintenanceRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Maintenance> findAllWithFetchJoin() {
        return queryFactory
                .selectFrom(QMaintenance.maintenance)
                .join(QMaintenance.maintenance.license, QLicense.license).fetchJoin()
                .fetch();
    }
}
