/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.apimanagement.controller;

import cn.zhuatech.apimanagement.common.ApiResponse;
import cn.zhuatech.apimanagement.service.ApiPublicationGateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enterprise/api-management")
public class ApiPublicationGateController {
    private final ApiPublicationGateService service;
    public ApiPublicationGateController(ApiPublicationGateService service) { this.service = service; }

    @PostMapping("/publication-gate")
    public ApiResponse<?> assess(@RequestBody ApiPublicationGateService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
