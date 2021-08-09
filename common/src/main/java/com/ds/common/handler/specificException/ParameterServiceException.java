package com.ds.common.handler.specificException;


import com.ds.common.enumerate.ResultEnum;
import com.ds.common.handler.ServiceException;
import org.springframework.http.HttpStatus;

/**
 * @author raptor
 */
public class ParameterServiceException extends ServiceException {
    private static final long serialVersionUID = 8362753245631601878L;

    public ParameterServiceException(ResultEnum resultEnum) {
        super(resultEnum.getCode(), resultEnum.getMsg());
        this.statusCode = HttpStatus.NOT_ACCEPTABLE;
    }
}
