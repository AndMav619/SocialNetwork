
package ece8221.hw3;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class Person implements java.lang.Comparable<Person> {
    private String name;
    private String email;
    private Set <Person> following;
    private Set <Person> followers;
    
    
    public Person(String name,String email) throws InvalidEmailAddressException{
        this.name=name;
        this.email=email;
        if (verifyEmailAddress(email)==false){
           throw new InvalidEmailAddressException(email);
        }
        following=new HashSet<>();
        followers=new HashSet<>();
    }
    
    public String getName(){
        return name;
    }
    
    public String getEmail(){
        return email;
    }
    
    public Set<Person> getFollowing(){
        return following;
    }
    
    public Set<Person> getFollowers(){
        return followers;
    }
    
    public void setName(String name){
        this.name=name;
    }
    
    public void setEmail(String email){
        
        this.email=email;
    }
    
    public boolean followUser(Person otherPerson){
        if (this.equals(otherPerson)){
            return false;
        }
        if (following.add(otherPerson) && otherPerson.followers.add(this)){
            return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        return String.format("%s (%s)",name,email);
    }
    
    
    
    
    
    //checks if an email is valid
    public static boolean verifyEmailAddress(String emailAddress){
        String regexPattern="^(?=.{1,64}@[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                +"[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return emailAddress.matches(regexPattern);
    }
    
    
    
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Person){
            Person p=(Person)o;
            if(this.email.equals(p.email)){
                return true;
            }
        }
        return false;
        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.email);
        return hash;
    }
    
    
    @Override
    public int compareTo(Person p){
        return email.compareTo(p.email);
    }
    
    
}
