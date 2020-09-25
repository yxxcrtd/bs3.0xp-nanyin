package com.joyintech.interf.bankname.cfetsup.outbound;


public class CallReturn {
    private boolean success;
    private String message;

    public CallReturn() {
    }
    
    public CallReturn(String message) {
		super();
		this.message = message;
	}

	public CallReturn(boolean success) {
		super();
		this.success = success;
	}

	public CallReturn(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}



	public static CallReturn success(){
        CallReturn c = new CallReturn();
        c.setSuccess(true);
        return c;

    }

    public static CallReturn failed(String message){
        CallReturn c = new CallReturn();
        c.setSuccess(false);
        c.setMessage(message);
        return c;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
