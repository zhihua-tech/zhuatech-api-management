/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.apimanagement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc class DomainDecisionApiTests {
 @Autowired MockMvc mvc;
 @Test void domainDecisionReturnsAuditableScoreMetricsAndActions() throws Exception {
  mvc.perform(post("/api/domain/decision").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("{\"apiCode\":\"CRM-CUSTOMER-V1\",\"successRate\":99.8,\"p95LatencyMs\":260,\"errorRate\":0.2,\"daysSinceReview\":30,\"authEnabled\":true,\"rateLimitEnabled\":true}"))
   .andExpect(status().isOk()).andExpect(jsonPath("$.data.decision").isString()).andExpect(jsonPath("$.data.score").isNumber()).andExpect(jsonPath("$.data.metrics").isMap()).andExpect(jsonPath("$.data.actions").isArray());
 }
 @Test void domainRiskScenarioReturnsExpectedBlockingDecision() throws Exception {
  mvc.perform(post("/api/domain/decision").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("{\"apiCode\":\"CRM-CUSTOMER-V1\",\"successRate\":90,\"p95LatencyMs\":2600,\"errorRate\":8,\"daysSinceReview\":500,\"authEnabled\":false,\"rateLimitEnabled\":false}"))
   .andExpect(status().isOk()).andExpect(jsonPath("$.data.decision").value("BLOCKED")).andExpect(jsonPath("$.data.actions").isNotEmpty());
 }
 @Test void domainDecisionRequiresAuthentication() throws Exception {mvc.perform(post("/api/domain/decision").contentType(MediaType.APPLICATION_JSON).content("{}" )).andExpect(status().isUnauthorized());}
}
