
package ece8221.hw3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Message implements java.lang.Comparable<Message> {
    
    private Date date;
    private String message;
    private Person user;
    public static final String DATE_FORMAT = "dd/MM/yyyy-HH:mm:ss";
    
    public Message(Date date,String message,Person user){
        this.date=date;
        this.message=message;
        this.user=user;
    }
    
    public Date getDate(){
        return date;
    }
    
    public String getMessage(){
        return message;
    }
    
    public Person getUser(){
        return user;
    }
    
    public void setDate(Date date){
        this.date=date;
    }
    
    public void setMessage(String message){
        this.message=message;
    }
    
    public void setUser(Person user){
        this.user=user;
    }
    
    
    @Override
    public int compareTo(Message m){
        return this.date.compareTo(m.date);
    }
    
    @Override
    public String toString(){
        DateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
        String formatedDateString = fmt.format(date);
        return String.format("[%s] %s %s",formatedDateString,user.getName(),message );
    }
    
    

    
    
    
    
    
}
