package com.ds.common.handler.specificException;


import com.ds.common.enumerate.ResultEnum;
import com.ds.common.handler.ServiceException;
import org.springframework.http.HttpStatus;

/**
 * @author raptor
 * @description DescribeException
 * @date 2021/7/6 20:42
 */
public class DescribeException extends ServiceException {


    private static final long serialVersionUID = 8362753245631601878L;

    public DescribeException(ResultEnum resultEnum) {
        super(resultEnum.getCode(), resultEnum.getMsg());
        this.statusCode = HttpStatus.BAD_REQUEST;
    }

}
