package pojo;

import enums.UserRole;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Order implements Serializable
{
    private String nameProduct;
    private Integer amount;
    private Date date;
    private Double cost;
    private String nameCustomer;
    private String country;
    private String adress;
    public Order(){}
    public Order(String nameProduct, Integer amount, Date date, Double cost, String nameCustomer)
    {
        this.nameProduct = nameProduct;
        this.amount = amount;
        this.date = date;
        this.cost = cost;
        this.nameCustomer = nameCustomer;
    }
    public void setCountry(String country) {this.country = country;}
    public String getCountry(){return country;}
    public void setAdress(String adress){this.adress = adress;}
    public String getAdress(){return adress;}
    public void setNameProduct(String nameProduct) {this.nameProduct = nameProduct;}
    public String getNameProduct() {return  nameProduct;}
    public void setAmount(Integer amount){this.amount = amount;}
    public Integer getAmount(){return amount;}
    public void setDate(String date) throws ParseException
    {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        this.date = new java.sql.Date(format.parse(date).getTime());
    }
    public void setDate(Date date)
    {
        this.date = date;
    }
    public Date getDate(){return date;}
    public void setCost(Double cost){this.cost = cost;}
    public Double getCost(){return cost;}
    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }
    public String getNameCustomer(){return nameCustomer;}
}
