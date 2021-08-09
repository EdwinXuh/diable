package com.ds.frame.controller.base;

import com.ds.common.entity.pojo.Result;
import com.ds.common.utils.result.ResultUtil;
import com.ds.frame.controller.BaseController;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * @author : EdwinXu
 * @date : Created in 2021/8/9 20:41
 */
@RestController
@RequestMapping("/base")
public class UserController extends BaseController {
    @GetMapping("/info")
    @Secured("ROLE_ADMIN")
    public Result test(){
        return ResultUtil.success("as");
    }
}
