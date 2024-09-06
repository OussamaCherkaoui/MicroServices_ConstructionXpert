package org.ressource.task;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "TASK")
public interface TaskRest {
    @GetMapping("/apiTask/isExist/{id}")
    Boolean isExist(@PathVariable Long id);
}
