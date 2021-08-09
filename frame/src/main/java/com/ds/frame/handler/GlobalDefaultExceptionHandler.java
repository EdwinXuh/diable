package com.ds.frame.handler;

import com.ds.common.entity.pojo.Result;
import com.ds.common.enumerate.ResultEnum;
import com.ds.common.handler.ServiceException;
import com.ds.common.utils.result.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author raptor
 */
@RestControllerAdvice
public class GlobalDefaultExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
    /**
     * 生产环境
     */
    private final static String ENV_PROD = "prod";

    /**
     * 当前环境
     */
    @Value("${spring.profiles.active}")
    private String profile;


    /**
     * 处理参数异常，一般用于校验body参数
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationBodyException(MethodArgumentNotValidException e, HttpServletResponse response) {
        log.error("参数校验异常", e);
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return ResultUtil.defineFail(ResultEnum.Invalid_Request_Parameter);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletResponse response) {
        log.error(e.toString(), e);
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return ResultUtil.defineFail(ResultEnum.Method_Argument_Type_Mismatch_Exception);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result handSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e, HttpServletResponse response) {
        log.error(e.toString(), e);
        response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
        return ResultUtil.defineFail(ResultEnum.SQL_Integrity_Constraint_Violation_Exception);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handNoHandlerFoundException(NoHandlerFoundException e, HttpServletResponse response) {
        log.error("{}资源不存在", e.getRequestURL());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return ResultUtil.defineFail(ResultEnum.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result handIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) {
        log.error(e.toString(), e);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        return ResultUtil.defineFail(ResultEnum.ACCESS_NOT_AUTHORIZED);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public Result handleIndexOutOfBoundsException(IndexOutOfBoundsException e, HttpServletResponse response){
        log.error(e.toString()+"shuzuyuejie", e);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResultUtil.defineFail(ResultEnum.WECHAT_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result handAccessDeniedException(AccessDeniedException e, HttpServletResponse response) {
        log.error("无权限访问", e);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        return ResultUtil.defineFail(ResultEnum.ACCESS_NOT_AUTHORIZED);
    }

    /**
     * Controller上一层相关异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler({
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    public Result handControllerException(Exception e, HttpServletResponse response) {
        log.error("controller接收异常", e);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        if (ENV_PROD.equals(profile)) {
            return ResultUtil.defineFail(ResultEnum.REQUEST_PARAMETER_ERROR);
        }
        return ResultUtil.defineFail("-400", e.getMessage());
    }

    /**
     * 主动throw的异常
     *
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public Result handleUnProcessableServiceException(ServiceException e, HttpServletResponse response) {
        response.setStatus(e.getStatusCode().value());
        return ResultUtil.defineFail(e.getErrorCode(), e.getMessage());
//        return new ErrorMessage(e.getErrorCode(), e.getMessage());
    }


    /**
     * 未定义异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handleException(Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error(e.toString(), e);
        if (ENV_PROD.equals(profile)) {
            // 生产环境不适合把具体的异常信息展示给用户
            return ResultUtil.defineFail(ResultEnum.UNKNOWN_ERROR);
        }
        return ResultUtil.defineFail("-500", e.toString());
    }

}