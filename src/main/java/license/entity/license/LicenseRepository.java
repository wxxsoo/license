package license.entity.license;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wansoo.
 * User: accomplishlee
 */
public interface LicenseRepository<T extends License> extends JpaRepository<License, Long> {
}
