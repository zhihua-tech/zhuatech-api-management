/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.apimanagement.domain;
import org.springframework.stereotype.Component;
import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Component
public class DomainCatalog {
    private final Map<String, WorkflowAction> actions = new LinkedHashMap<>();
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public DomainCatalog() {
        actions.put("SUBMIT", new WorkflowAction("SUBMIT", "提交发布", List.of("草稿"), "待审批", "OPERATOR"));
        actions.put("PUBLISH", new WorkflowAction("PUBLISH", "批准上线", List.of("待审批"), "已上线", "ADMIN"));
        actions.put("RETIRE", new WorkflowAction("RETIRE", "下线接口", List.of("已上线"), "已下线", "ADMIN"));
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String systemName() { return "知华科技企业集成与 API 管理平台"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String scene() { return "API资产、应用接入、鉴权、流量策略、编排、订阅、监控、告警与审计"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String initialStatus() { return "草稿"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String partyLabel() { return "API/消费应用"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String amountLabel() { return "调用价值"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String quantityLabel() { return "调用量"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String dueLabel() { return "发布期限"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<ModuleDefinition> modules() { return List.of(
            new ModuleDefinition("API_CATALOG", "API目录", "统一登记版本、协议、负责人和生命周期"),
            new ModuleDefinition("APPLICATION", "应用接入", "管理消费应用、凭据与授权范围"),
            new ModuleDefinition("AUTH_POLICY", "认证策略", "配置JWT、签名、OAuth2和IP白名单"),
            new ModuleDefinition("TRAFFIC_POLICY", "流量策略", "执行限流、熔断、降级与配额"),
            new ModuleDefinition("ORCHESTRATION", "接口编排", "完成协议转换、字段映射和服务编排"),
            new ModuleDefinition("SUBSCRIPTION", "订阅管理", "审批订阅并控制环境和有效期"),
            new ModuleDefinition("OBSERVABILITY", "运行监控", "跟踪成功率、延迟、错误码和依赖"),
            new ModuleDefinition("ALERT", "告警处置", "告警分派、升级和复盘"),
            new ModuleDefinition("AUDIT", "调用审计", "保留调用、策略变更与管理员操作证据")
        ); }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Map<String, WorkflowAction> actions() { return Collections.unmodifiableMap(actions); }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record ModuleDefinition(String code,String name,String description) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record WorkflowAction(String code,String label,List<String> from,String to,String requiredRole) {}
}
