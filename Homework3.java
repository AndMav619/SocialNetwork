
package ece8221.hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;




public class Homework3 {
    
    
    public static Person findPerson(Set<Person> users,String email){
        for(Person p:users){
            if (p.getEmail().equals(email)){
                return p;
            }
        }
        return null;
    }
    
    
    public static void main(String []args){
     
    
        
        Date date;
        String name;
        String email;
        String msgTxt;
        String inputFilePath;
        String outputFilePath;
        
        Set <Message> messages = new TreeSet<>();
        Set <Person> users = new HashSet<>();
        
        
        Scanner stdin=new Scanner(System.in);
        inputFilePath=stdin.nextLine();
        outputFilePath=stdin.nextLine();
        
        File inputFile = new File (inputFilePath);
        File outputFile= new File (outputFilePath);
        
        
        
        try (Scanner in=new Scanner (inputFile)){
            try (PrintWriter out=new PrintWriter (outputFile)){
                while(true){
                    out.println("?: ");
                    String input=in.next();
                    switch(input){
                        case "-ap":
                            name=in.next();
                            email=in.next();
                            if (findPerson(users,email)!=null){
                                out.println("USER "+email+" EXISTS");
                                break;
                            }
                            Person user;
                            try{
                                user=new Person(name,email);
                                users.add(user);
                                out.println("USER "+user+" OK");
                            }
                            catch(InvalidEmailAddressException ex){
                                out.println("INVALID EMAIL "+email);
                            }
                            break;
                            
                        case "-pp":
                            out.print("\n--- PLATFORM USERS ---\n");
                            for (Person p:users){
                                if(p!=null){
                                    out.println(p.toString());
                                }
                            }
                            break;
                            
                        case "-am":
                            String dateStr=in.next();
                            DateFormat fmt=new SimpleDateFormat (Message.DATE_FORMAT);
                            try {
                                date=fmt.parse(dateStr);
                            }
                            catch(ParseException ex){
                                out.println("DATE PARSE ERROR");
                                break;
                            }
                            email=in.next();
                            msgTxt=in.nextLine();
                            if (findPerson(users,email)==null){
                                out.println("USER "+email+" NOT FOUND");
                                break;
                            }else{
                                Message msg=new Message(date,msgTxt,findPerson(users,email));
                                messages.add(msg);
                                out.println("ADD MESSAGE OK");
                            }
                            break;
                            
                        case "-af":
                            String email1=in.next();
                            String email2=in.next();
                            if(email1.equals(email2)){
                                out.println("BOTH EMAILS MATCH");
                                break;
                            }
                            if(findPerson(users,email1)==null){
                                out.println("USER "+email1+" NOT FOUND");
                            }
                            if(findPerson(users,email2)==null){
                                out.println("USER "+email2+" NOT FOUND");
                                break;
                            }
                            if(findPerson(users,email1)==null){
                                break;
                            }
                            Person user1=findPerson(users,email1);
                            Person user2=findPerson(users,email2);
                            if(user1.followUser(user2)){
                                out.println(email1+" STARTED FOLLOWING "+email2);
                            }else{
                                out.println(email1+" ALREADY FOLLOWING "+email2);
                            }
                            break;
                            
                        case "pfg":
                            email=in.next();
                            if(findPerson(users,email)==null){
                                out.println("USER "+email+" NOT FOUND");
                                break;
                            }
                            out.print("\n--- "+email+" - Following ---\n");
                            user=findPerson(users,email);
                            for(Person p:user.getFollowing()){
                                out.println(p.toString());
                            }
                            break;
                        
                        case "pfr":
                            email=in.next();
                            if(findPerson(users,email)==null){
                                out.println("USER "+email+" NOT FOUND");
                                break;
                            }
                            out.print("\n--- "+email+" - Followers ---\n");
                            user=findPerson(users,email);
                            for(Person p:user.getFollowers()){
                                out.println(p.toString());
                            }
                            break;
                            
                        case "pm":
                            email=in.next();
                            if(findPerson(users,email)==null){
                                out.println("USER "+email+" NOT FOUND");
                                break;
                            }
                            user=findPerson(users,email);
                            out.print("\n--- "+user.toString()+" - Messages ---\n");
                            List<Message> relatedMessages;
                            relatedMessages=new LinkedList<>();
                            for(Message m:messages){
                                if(user.equals(m.getUser())){
                                    relatedMessages.add(m);
                                    continue;
                                }
                                for(Person p:user.getFollowing()){
                                    if(p.equals(m.getUser())){
                                        relatedMessages.add(m);
                                    }
                                }
                            }
                            Collections.sort(relatedMessages);
                            Collections.reverse(relatedMessages);
                            for(Message msg:relatedMessages){
                                out.println(msg.toString());
                            }
                            break;
                            
                        case "-q":
                            out.println("Bye bye!");
                            return;
                        
                        default:
                            out.println("Invalid option.Try again.");
                        
                    }
                }

            }
            catch(FileNotFoundException ex){
                System.out.format("Cannot open file '%s' for writing.\n",outputFilePath);
                return;
            }
        }
        catch (FileNotFoundException ex){
            System.out.format("Cannot open file '%s' for reading.\n",inputFilePath);
        }
        
        
        
        
    }
    
    
    
    
}
