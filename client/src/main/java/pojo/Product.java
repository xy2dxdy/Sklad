package pojo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Product implements Serializable {

    private String supplier;
    private String productsGroup;
    private String name;
    private Double price;
    private Integer amount;
    private Date receiptDate;
    public Product()
    {}
    public Product(String supplier, String productsGroup, String name, Double price, Integer amount, Date receiptDate)
    {
        this();
        this.productsGroup = productsGroup;
        this.supplier = supplier;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.receiptDate = receiptDate;
    }
    //public Integer getIdProduct() {return idProduct;}
    //public void setIdProduct(Integer id){this.idProduct = id;}
    public String getSupplier() {return supplier;}
    public void setSupplier(String supplier){this.supplier = supplier;}
    public String getProductsGroup() {return productsGroup;}
    public void setProductsGroup(String productsGroup) {this.productsGroup = productsGroup;}
    public void setName(String name){this.name = name;}
    public String getName(){return name;}
    public void setPrice(Double price){this.price = price;}
    public Double getPrice(){return price;}
    public void setAmount(Integer amount){this.amount = amount;}
    public Integer getAmount(){return amount;}
    public void setReceiptDate(String receiptDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        this.receiptDate = format.parse(receiptDate);
    }
    public Date getReceiptDate(){return receiptDate;}
}