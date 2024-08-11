package com.microservice.admin.api;

import com.microservice.admin.dto.reponse.PermissionRequest;
import com.microservice.admin.constant.ApiResponse;
import com.microservice.admin.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permission")
public class Permission {

    @Autowired
    PermissionService permissionService;

    @PostMapping("/create")
    public ApiResponse create(@RequestBody PermissionRequest permissionRequest){
        return ApiResponse
                .builder()
                .code(200)
                .message("Tạo thành công!")
                .data(permissionService.create(permissionRequest))
                .build();
    }

    @PostMapping("/find")
    public ApiResponse find(
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "limit",defaultValue = "10") Integer limit
    ){
        return ApiResponse
                .builder()
                .code(200)
                .message("Truy vấn thành công!")
                .data(permissionService.find(page,limit))
                .build();
    }

    @PostMapping("/delete")
    public ApiResponse delete(@RequestParam(value = "name", required = true) String name){
        return ApiResponse
                .builder()
                .code(200)
                .message("Delete thành công!")
                .data(permissionService.delete(name))
                .build();
    }

}
