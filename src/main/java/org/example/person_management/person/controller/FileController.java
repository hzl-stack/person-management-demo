package org.example.person_management.person.controller;

import org.example.person_management.person.entity.vo.FileVo;
import org.example.person_management.person.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@CrossOrigin
@RequestMapping("/hzl-web/person-management/file")
public class FileController {
    @PostMapping(path = "upload")
    public Result<FileVo> upload(@RequestBody FileVo fileVo){
        try{
            return Result.ok(fileVo);
        }catch (Exception e){
            return Result.fail();
        }
    }
}
