package com.gestaosimples.servico.resources.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable {

    /**  */
    private static final long serialVersionUID = 5583814625753233436L;

    private String filed;

    private String msg;

    public FieldMessage() {
    }

    public FieldMessage(String filed, String msg) {
        super();
        this.filed = filed;
        this.msg = msg;
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
