package com.endsound.trpg.demo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backend/api/demo")
public class BackendController {
    @PreAuthorize("hasRole('ROLE_BACKEND')")
    @GetMapping("common")
    public Object common(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("admin")
    public Object admin(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
