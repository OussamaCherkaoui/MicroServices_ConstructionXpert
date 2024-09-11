package org.task.Project;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PROJECT")
public interface ProjectRest {

    @GetMapping("/apiProject/isExist/{id}")
    Boolean isExist(@PathVariable Long id);
}
