package license.entity.license.maintenance;

import java.util.List;
import java.util.Optional;

/**
 * Created by wansoo.
 * User: accomplishlee
 */
public interface MaintenanceRepositoryCustom {
    List<Maintenance> findAllWithFetchJoin();
}
