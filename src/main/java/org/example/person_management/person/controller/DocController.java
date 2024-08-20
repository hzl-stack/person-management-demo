package org.example.person_management.person.controller;

import org.example.person_management.person.entity.dto.PersonStatusDto;
import org.example.person_management.person.entity.enums.PersonStatus;
import org.example.person_management.pub.result.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/hzl-web/person-management/doc")
public class DocController {
    @GetMapping(path = "getPersonStatus")
    public Result<List<PersonStatusDto>> getPersonStatus() {
        List<PersonStatusDto> statusList = new ArrayList<>();
        for (PersonStatus status : PersonStatus.values()) {
            statusList.add(new PersonStatusDto(status.getCode(), status.getName()));
        }
        return Result.ok(statusList);
    }
}
