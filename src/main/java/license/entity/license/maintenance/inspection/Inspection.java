package license.entity.license.maintenance.inspection;

import license.entity.license.maintenance.Maintenance;
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
public class Inspection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "routine_inspection_id")
    private Long id;

    @Column(nullable = false)
    private LocalDate visitDate;

    private String visitor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_id")
    private Maintenance maintenance;

    @Builder
    public Inspection(LocalDate visitDate, String visitor) {
        this.visitDate = visitDate;
        this.visitor = visitor;
    }

    //== 연관 관계 메소드 ==//
    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }
}
