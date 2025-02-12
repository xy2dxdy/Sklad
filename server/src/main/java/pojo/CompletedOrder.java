package pojo;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class CompletedOrder implements Serializable
{
    private String nameProduct;
    private Integer amount;
    private Date orderDate;
    private Date confirmationDate;
    private Double cost;
    private String nameCustomer;
    private String country;
    private String adress;
    public CompletedOrder(){}
    public CompletedOrder(String nameProduct, Integer amount, Date orderDate, Date cinfirmationDate, Double cost, String nameCustomer)
    {
        this.nameProduct = nameProduct;
        this.amount = amount;
        this.orderDate = orderDate;
        this.confirmationDate = cinfirmationDate;
        this.cost = cost;
        this.nameCustomer = nameCustomer;
    }
    public CompletedOrder(Order order)
    {
        this.nameProduct = order.getNameProduct();
        this.amount = order.getAmount();
        this.orderDate = order.getDate();
        LocalDate currentDate = LocalDate.now();
        this.confirmationDate = Date.valueOf(currentDate);
        this.cost = order.getCost();
        this.nameCustomer = order.getNameCustomer();
    }
    public void setCountry(String country) {this.country = country;}
    public String getCountry(){return country;}
    public void setAdress(String adress){this.adress = adress;}
    public String getAdress(){return adress;}
    public void setNameProduct(String nameProduct) {this.nameProduct = nameProduct;}
    public String getNameProduct() {return  nameProduct;}
    public void setAmount(Integer amount){this.amount = amount;}
    public Integer getAmount(){return amount;}
    public void setOrderDate(String date) throws ParseException
    {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        this.orderDate = new java.sql.Date(format.parse(date).getTime());
    }
    public Date getOrderDate(){return orderDate;}
    public void setConfirmationDate(String date) throws ParseException
    {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        this.confirmationDate = new java.sql.Date(format.parse(date).getTime());
    }
    public Date getConfirmationDate(){return confirmationDate;}
    public void setCost(Double cost){this.cost = cost;}
    public Double getCost(){return cost;}
    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }
    public String getNameCustomer(){return nameCustomer;}
}
