package com.santillan.carteleraviamatica.exceptions;

import com.santillan.carteleraviamatica.util.ExceptionUtils;

public class ExceptionErrores extends Exception{
<<<<<<< HEAD
    private static final long serialVersionUID = 1L;

    String codError;
    public String getCodError(){
        return codError;
    }

    public ExceptionErrores(String codError, String mensajeError){
        super(mensajeError);
        this.codError = codError;
    }

    public ExceptionErrores(String codError){
        ExceptionUtils.manejoError(codError);
    }












=======

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String codError;

	public String getCodError() {
		return codError;
	}
	
	public ExceptionErrores(String codError, String mensajeError) {
		super(mensajeError);
		this.codError = codError;
	}
	
	public ExceptionErrores(String codError) {
		ExceptionUtils.manejoError(codError);
	}
>>>>>>> 8c0a7d232989c87d9398ab543845371e8b55cec6

}
