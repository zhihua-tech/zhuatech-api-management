# 企业集成与 API 管理平台 API

所有业务接口默认位于 `/api`，除 `/public/**` 和健康检查外均需要 HTTP Basic 身份认证。生产环境应接入企业 IAM 或统一身份平台。

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| GET | `/public/about` | 产品、公司、官网和许可元数据 |
| GET | `/catalog` | 业务模块、字段标签和状态动作 |
| GET | `/dashboard` | 业务规模、金额、状态和模块统计 |
| GET/POST | `/records` | 业务台账查询与创建 |
| GET/PUT/DELETE | `/records/{id}` | 详情、草稿修改与删除 |
| POST | `/records/{id}/actions` | 执行服务端状态迁移 |
| POST | `/records/{id}/comments` | 增加协作记录 |
| GET | `/records/{id}/timeline` | 查询完整操作时间线 |
| GET | `/records/search` | 组合检索、分页和逾期筛选 |
| GET | `/records/export.csv` | 导出 UTF-8 CSV |
| GET | `/sla-summary` | SLA、逾期、风险和人员工作量 |
| POST | `/domain/decision` | 执行企业集成与 API 管理平台专属领域规则 |
| GET/POST | `/enterprise/controls` | 企业控制项查询与幂等创建 |
| POST | `/enterprise/controls/{id}/submit` | 提交复核 |
| POST | `/admin/enterprise/controls/{id}/review` | 管理员审批或驳回 |
| POST | `/enterprise/controls/{id}/documents` | 登记附件哈希及存储元数据 |
| POST | `/enterprise/controls/{id}/complete` | 凭证完整后办结 |
| POST | `/admin/enterprise/controls/{id}/sync` | 登记外部系统回执 |

## 领域决策字段

| 字段 | 类型 | 含义 |
| --- | --- | --- |
| `apiCode` | String | API编号 |
| `successRate` | double | 成功率(%) |
| `p95LatencyMs` | int | P95延迟(ms) |
| `errorRate` | double | 错误率(%) |
| `daysSinceReview` | int | 距上次评审(天) |
| `authEnabled` | boolean | 已启用认证 |
| `rateLimitEnabled` | boolean | 已启用限流 |

接口统一返回 `ApiResponse`；业务冲突使用 HTTP 409，参数错误使用 400，未认证使用 401，无权限使用 403。

## V2.0 API 全生命周期接口

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| GET | `/api/apim/dashboard` | API、应用、订阅与运行健康总览 |
| POST | `/api/apim/apis` | 注册 API 版本及服务等级 |
| POST | `/api/apim/apis/{id}/submit` | 提交 API 发布审核 |
| POST | `/api/admin/apim/apis/{id}/publish` | 发布已启用认证和限流的 API |
| POST | `/api/apim/apps` | 创建调用方应用 |
| POST | `/api/apim/subscriptions` | 申请 API 订阅与配额 |
| POST | `/api/admin/apim/subscriptions/{id}/approve` | 审批订阅 |
| POST | `/api/apim/apis/{id}/metrics` | 上报调用量、错误率与 P95 延迟 |

发布门禁会校验认证与限流配置；运行指标用于判断成功率、延迟和错误率是否满足服务等级目标。
