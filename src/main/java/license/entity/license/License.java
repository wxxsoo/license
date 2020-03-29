package license.entity.license;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by wansoo.
 * User: accomplishlee
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn // 하위 테이블의 구분 컬럼 생성(default = DTYPE)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "license_id")
    private Long id;
    // 제품 코드
    @Column(nullable = false)
    private ProductCode productCode;
    // 인증서 넘버
    @Column(nullable = false)
    @Setter
    private String certificationNumber;
    // 제품 major version
    @Column(nullable = false)
    @Setter
    private String majorVersion;
    // 고객사 명
    @Column(nullable = false)
    @Setter
    private String customerName;
    // 계약일
    @Setter
    private LocalDate contractDate;

    public License(ProductCode productCode, String certificationNumber, String majorVersion, String customerName, LocalDate contractDate) {
        this.productCode = productCode;
        this.certificationNumber = certificationNumber;
        this.majorVersion = majorVersion;
        this.customerName = customerName;
        this.contractDate = contractDate;
    }
}
