package license.entity.license.web;

import jdk.vm.ci.meta.Local;
import license.entity.license.License;
import license.entity.license.LicenseRepository;
import license.entity.license.ProductCode;
import license.entity.license.maintenance.Maintenance;
import license.entity.license.maintenance.MaintenanceRepository;
import license.entity.license.maintenance.support.Support;
import license.entity.license.maintenance.support.SupportRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.rmic.Main;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by wansoo.
 * User: accomplishlee
 */
@SpringBootTest
@Transactional
public class WebLicenseTest {

    @Autowired
    LicenseRepository<WebLicense> webLicenseRepository;
    @Autowired
    MaintenanceRepository maintenanceRepository;

    @Autowired
    SupportRepository supportRepository;

    @BeforeEach
    public void setUp() {
        WebLicense webLicense = WebLicense.builder()
                .productCode(ProductCode.WEB)
                .certificationNumber("WEB-002")
                .contractDate(LocalDate.of(2019,1,1))
                .majorVersion("3.0")
                .customerName("고객A")
                .contentC("hello")
                .contentD("world")
                .build();
        webLicenseRepository.save(webLicense);

        Maintenance maintenance = Maintenance.builder()
                .salesPerson("김영업")
                .supportTime(10L)
                .startDate(LocalDate.of(2020,1,1))
                .endDate(LocalDate.of(2020,12,31))
                .build();
        maintenance.setLicense(webLicense);
        maintenanceRepository.save(maintenance);

        Support support = Support.builder().visitDate(LocalDate.of(2020,2,1)).visitor("홍길동").usingSupportTime(2L).build();
        support.setMaintenance(maintenance);

        supportRepository.save(support);
    }


    /**
     * WebLicense를 참조하는 Maintenance,
     * WebLicense를 참조하는 Maintenane를 참조하는 Support 가 있을 때
     * 가장 상위엔티티인 WebLicense가 삭제되면...??
     */
//    @Test
//    public void WebLicense_Maintenacne_Supoort_삭제() {
//        assertThat(webLicenseRepository.count()).isEqualTo(1L);
//        assertThat(maintenanceRepository.count()).isEqualTo(1L);
//        assertThat(supportRepository.count()).isEqualTo(1L);
//        // given
//        WebLicense license = (WebLicense) webLicenseRepository.findAll().get(0);
//        // when
//        webLicenseRepository.delete(license);
//
//        assertThat(webLicenseRepository.count()).isEqualTo(0L);
//    }

    @Test
    public void HSPLicense_저장() {
        // given
        WebLicense webLicense = WebLicense.builder()
                .productCode(ProductCode.WEB)
                .certificationNumber("WEB-002")
                .contractDate(LocalDate.of(2019,1,1))
                .majorVersion("3.0")
                .customerName("고객A")
                .contentC("hello")
                .contentD("world")
                .build();

        // when
        Long id = webLicenseRepository.save(webLicense).getId();
        // then
        WebLicense result = (WebLicense) webLicenseRepository.findById(id).get();
        assertThat(webLicenseRepository.count()).isEqualTo(2L);
        assertThat(result.getProductCode()).isEqualTo(webLicense.getProductCode());
        assertThat(result.getCertificationNumber()).isEqualTo(webLicense.getCertificationNumber());
        assertThat(result.getContractDate()).isEqualTo(webLicense.getContractDate());
        assertThat(result.getMajorVersion()).isEqualTo(webLicense.getMajorVersion());
        assertThat(result.getContentC()).isEqualTo(webLicense.getContentC());
        assertThat(result.getContentD()).isEqualTo(webLicense.getContentD());
    }

    @Test
    public void Maintenance_저장() {
        // given
        WebLicense webLicense = (WebLicense) webLicenseRepository.findAll().get(0);
        Maintenance maintenance = Maintenance.builder()
                .salesPerson("김영업")
                .supportTime(10L)
                .startDate(LocalDate.of(2020,1,1))
                .endDate(LocalDate.of(2020,12,31))
                .build();
        maintenance.setLicense(webLicense);
        // when
        Long id = maintenanceRepository.save(maintenance).getId();
        // then
        Maintenance result = maintenanceRepository.findAllWithFetchJoin().get(1);
        assertThat(maintenanceRepository.count()).isEqualTo(2L);
        // License와 매핑 확인
        assertThat(result.getLicense().getId()).isEqualTo(webLicense.getId());
    }

    @Test
    public void Support_저장() {

        // given
        Maintenance maintenance = maintenanceRepository.findAll().get(0);
        Support support = Support.builder().visitDate(LocalDate.of(2020,2,1)).visitor("홍길동").usingSupportTime(2L).build();
        support.setMaintenance(maintenance);
        // when
        Long id = supportRepository.save(support).getId();

        // then
        Support result = supportRepository.findById(id).get();
        assertThat(supportRepository.count()).isEqualTo(2L);
        assertThat(result.getMaintenance().getId()).isEqualTo(maintenance.getId());
    }
}