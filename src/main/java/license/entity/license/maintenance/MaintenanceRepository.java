package license.entity.license.maintenance;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wansoo.
 * User: accomplishlee
 */
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long>, MaintenanceRepositoryCustom {
}
