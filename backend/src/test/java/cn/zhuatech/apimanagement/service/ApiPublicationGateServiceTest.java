/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.apimanagement.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class ApiPublicationGateServiceTest {
    private final ApiPublicationGateService service = new ApiPublicationGateService();

    @Test void publishesGovernedApiVersion() {
        var result = service.assess(new ApiPublicationGateService.Request("API-100", true, true, true,
                true, true, true, true, false, true, true, true));
        assertThat(result.decision()).isEqualTo(ApiPublicationGateService.Decision.PUBLISH);
    }

    @Test void routesOperationalReadinessToReview() {
        var result = service.assess(new ApiPublicationGateService.Request("API-101", false, true, false,
                true, false, true, true, false, true, false, true));
        assertThat(result.actions()).hasSize(4);
        assertThat(result.decision()).isEqualTo(ApiPublicationGateService.Decision.REVIEW);
    }

    @Test void blocksInsecureOrBreakingPublication() {
        var result = service.assess(new ApiPublicationGateService.Request("", false, false, false,
                false, false, false, false, true, false, false, false));
        assertThat(result.blockers()).hasSize(7);
        assertThat(result.decision()).isEqualTo(ApiPublicationGateService.Decision.BLOCKED);
    }
}
