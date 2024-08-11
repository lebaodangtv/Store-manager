package com.microservice.admin.api;

import com.microservice.admin.dto.reponse.RolesRequest;
import com.microservice.admin.constant.ApiResponse;
import com.microservice.admin.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class roles {

    @Autowired
    private RolesService rolesService;

    @PostMapping("/create")
    public ApiResponse create(@RequestBody RolesRequest rolesRequest){
        return ApiResponse
                .builder()
                .code(200)
                .message("Tạo thành công!")
                .data(rolesService.create(rolesRequest))
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
                .message("Tạo thành công!")
                .data(rolesService.find(page,limit))
                .build();
    }

    @PostMapping("/delete")
    public ApiResponse delete(@RequestParam(value = "id", required = true) String name){
        return ApiResponse
                .builder()
                .code(200)
                .message("Delete thành công!")
                .data(rolesService.delete(name))
                .build();
    }

}
