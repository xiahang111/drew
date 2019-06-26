package com.drew.entity;

public class ResponseResult {

    public int status;

    public String message;

    public Object data;

    public static ResponseResult error = new ResponseResult(500,"fail",null);
    public static ResponseResult success = new ResponseResult(200,"success",null);

    public ResponseResult(){
        this.status = 200;
        this.message = "success";

        this.data = null;
    }



    public ResponseResult(Object data){

        this.status = 200;
        this.message = "success";

        this.data = data;
    }

    public ResponseResult(int status,String message,Object data){
        this.status = status;
        this.message = message;

        this.data = data;
    }

}
