package license.entity.license.mobile;

import license.entity.license.License;
import license.entity.license.ProductCode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * Created by wansoo.
 * User: accomplishlee
 */
@Entity
@DiscriminatorValue("MOBILE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MobileLicense extends License {
    // 라이선스 내용 1
    private String contentA;
    // 라이선스 내용 2
    private String contentB;

    @Builder
    public MobileLicense(ProductCode productCode, String certificationNumber, String majorVersion, String customerName, LocalDate contractDate, String contentA, String contentB) {
        super(productCode, certificationNumber, majorVersion, customerName, contractDate);
        this.contentA = contentA;
        this.contentB = contentB;
    }
}
