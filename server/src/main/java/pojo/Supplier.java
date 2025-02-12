package pojo;

import java.io.Serializable;

public class Supplier implements Serializable
{
    private String name;
    private String country;
    private String phoneNumber;
    public Supplier(){}
    public Supplier(String  name, String country, String phoneNumber)
    {
        this();
        this.name = name;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }
    public void setName(String name){this.name = name;}
    public String getName() {return  name;}
    public void setCountry(String country){this.country = country;}
    public String getCountry(){return country;}
    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}
    public String getPhoneNumber(){return phoneNumber;}
}
