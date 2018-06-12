package com.montnets.es.exception;


/**
 * 
* Copyright: Copyright (c) 2018 Montnets
* 
* @ClassName: EsInitMonException.java
* @Description: 该类的功能描述
*自定义ES初始化异常
* @version: v1.0.0
* @author: chenhj
* @date: 2018年6月7日 上午10:18:26 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年6月7日     chenhj          v1.0.0               修改原因
 */
public  class EsInitMonException  extends RuntimeException {

        private static final long serialVersionUID = 1L;

        /**
         * 错误编码
         */
        private String errorCode;

        /**
         * 构造一个基本异常.
         *
         * @param message
         *            信息描述
         */
        public EsInitMonException(String message)
        {
            super(message);
        }
        /**
         * 构造一个基本异常.
         *
         * @param message
         *            信息描述
         * @param cause
         *            根异常类（可以存入任何异常）
         */
        public EsInitMonException(String message, Throwable cause)
        {
            super(message, cause);
        }
        
        public String getErrorCode()
        {
            return errorCode;
        }

        public void setErrorCode(String errorCode)
        {
            this.errorCode = errorCode;
        }
        
}
