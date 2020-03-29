package license.entity.license.maintenance;

import license.entity.license.License;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by wansoo.
 * User: accomplishlee
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "maintenance_id")
    private Long id;

    // 유지보수 시작일
    @Column(nullable = false)
    private LocalDate startDate;
    // 유지보수 종료일
    private LocalDate endDate;

    private Long supportTime; // 기술 지원 시간

    private String description;

    private String salesPerson; // 영업 담당자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "license_id")
    private License license;

    @Builder
    public Maintenance(LocalDate startDate, LocalDate endDate, Long supportTime, String description, String salesPerson) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.supportTime = supportTime;
        this.description = description;
        this.salesPerson = salesPerson;
    }

    // == 연관 관계 메소드 ==//
    public void setLicense(License license) {
        this.license = license;
    }
}
