# 企业 API 发布门禁

`POST /api/enterprise/api-management/publication-gate` 在 API 新版本上线前检查契约、认证授权、敏感数据、限流、安全扫描、破坏性变更迁移、回滚与审计证据。

- `PUBLISH`：接口契约、安全和运营准备完整，可以发布。
- `REVIEW`：不存在硬性阻断，但仍需补齐责任人、版本策略、限流或回滚准备。
- `BLOCKED`：契约、安全、敏感数据、破坏性变更或审计控制失败。

该门禁可接入 API 网关、CI/CD、开发者门户及消费方通知流程。
