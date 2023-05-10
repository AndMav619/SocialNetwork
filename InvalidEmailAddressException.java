
package ece8221.hw3;


public class InvalidEmailAddressException extends java.lang.Exception{
    
    private String invalidEmail;
    
    public InvalidEmailAddressException(String email){
        invalidEmail=email;
    }
    
    public String getInvalidEmail(){
        return invalidEmail;
    }
    
}
