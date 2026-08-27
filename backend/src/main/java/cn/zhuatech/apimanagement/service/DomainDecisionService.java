/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.apimanagement.service;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Service;
import java.util.*;
@Service public class DomainDecisionService {
 public DecisionResult assess(DecisionRequest request) { int score=100;List<String> actions=new ArrayList<>();if(request.successRate()<99.9){score-=20;actions.add("提升接口成功率至99.9%以上");}if(request.p95LatencyMs()>500){score-=15;actions.add("优化P95响应延迟");}if(request.errorRate()>1){score-=20;actions.add("治理高频错误码");}if(!request.authEnabled()){score-=30;actions.add("启用接口认证");}if(!request.rateLimitEnabled()){score-=15;actions.add("配置配额和限流");}if(request.daysSinceReview()>180){score-=10;actions.add("执行生命周期复审");}return result(score,actions,"HEALTHY","DEGRADED","BLOCKED",Map.of("successRate",request.successRate(),"p95LatencyMs",request.p95LatencyMs(),"errorRate",request.errorRate())); }
 private DecisionResult result(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=80?good:score>=50?warn:bad;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 private DecisionResult riskResult(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=70?bad:score>=40?warn:good;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 public record DecisionRequest(
        @NotBlank String apiCode,
        @DecimalMin("0") @DecimalMax("100") double successRate,
        @PositiveOrZero int p95LatencyMs,
        @DecimalMin("0") @DecimalMax("100") double errorRate,
        @PositiveOrZero int daysSinceReview,
        boolean authEnabled,
        boolean rateLimitEnabled) {}
 public record DecisionResult(String decision,int score,Map<String,Object> metrics,List<String> actions) {}
}
