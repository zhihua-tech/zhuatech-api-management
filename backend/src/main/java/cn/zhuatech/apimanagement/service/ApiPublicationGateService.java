/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.apimanagement.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ApiPublicationGateService {
    public Result assess(Request request) {
        var blockers = new ArrayList<String>();
        var actions = new ArrayList<String>();
        if (request.apiId() == null || request.apiId().isBlank()) blockers.add("API 编号不能为空");
        if (!request.openApiValid()) blockers.add("OpenAPI 契约校验失败");
        if (!request.authenticationConfigured()) blockers.add("认证与授权策略未配置");
        if (!request.piiReviewed()) blockers.add("个人信息与敏感数据评审未完成");
        if (!request.securityScanPassed()) blockers.add("API 安全扫描未通过");
        if (request.breakingChange() && !request.consumerMigrationApproved()) blockers.add("破坏性变更缺少消费方迁移批准");
        if (!request.auditReady()) blockers.add("API 发布审计证据不完整");
        if (!request.ownerAssigned()) actions.add("指定 API 业务与技术责任人");
        if (!request.versioningPolicyMet()) actions.add("补齐版本与弃用策略");
        if (!request.rateLimitConfigured()) actions.add("配置限流与配额");
        if (!request.rollbackReady()) actions.add("准备可验证的回滚方案");
        var decision = !blockers.isEmpty() ? Decision.BLOCKED : actions.isEmpty() ? Decision.PUBLISH : Decision.REVIEW;
        return new Result(decision, List.copyOf(blockers), List.copyOf(actions));
    }

    public enum Decision { PUBLISH, REVIEW, BLOCKED }
    public record Request(String apiId, boolean ownerAssigned, boolean openApiValid,
                          boolean versioningPolicyMet, boolean authenticationConfigured,
                          boolean rateLimitConfigured, boolean piiReviewed, boolean securityScanPassed,
                          boolean breakingChange, boolean consumerMigrationApproved,
                          boolean rollbackReady, boolean auditReady) {}
    public record Result(Decision decision, List<String> blockers, List<String> actions) {}
}
