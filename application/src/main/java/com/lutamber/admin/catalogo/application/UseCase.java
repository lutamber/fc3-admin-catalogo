package com.lutamber.admin.catalogo.application;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN aIn);

}
