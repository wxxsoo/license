package license.entity.license.web;

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
@DiscriminatorValue("WEB")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WebLicense  extends License {
    // 라이선스 내용 1
    private String contentC;
    // 라이선스 내용 2
    private String contentD;

    @Builder
    public WebLicense(ProductCode productCode, String certificationNumber, String majorVersion, String customerName, LocalDate contractDate, String contentC, String contentD) {
        super(productCode, certificationNumber, majorVersion, customerName, contractDate);
        this.contentC = contentC;
        this.contentD = contentD;
    }
}
