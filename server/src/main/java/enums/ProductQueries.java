package enums;

public enum ProductQueries
{
    findProduct("Select * from products where name = ?"),
    insertProduct("INSERT INTO `sklad`.`products` (`idSuppliers`, `idProductsGroups`, `name`, `price`, `amount`, `receiptDate`) " +
            "VALUES (?, ?, ?, ?, ?, ?)"),
    remove("Delete from product where idProducts = ?"),
    //buyProduct("INSERT INTO usercontent (`userid`, `usercontentid`) VALUES (?, ?)"),
    updatedProduct("Update products set amount = ? where idProducts = ?"),
    updateProduct("Update products set name = ?, price = ?, amount = ?, receiptDate = ? where idProducts = ?"),
    getProducts("Select suppliers.name, productsgroups.name, products.name, price, amount, receiptDate from products inner join productsgroups on productsgroups.idProductsGroups = products.idProductsGroups inner join suppliers on suppliers.idSuppliers = products.idSuppliers where productsgroups.name = ?"),
    findIdSupplier("Select idSuppliers from suppliers where name = ?"),
    findIdProductsGroup("Select idProductsGroups from productsgroups where name = ?"),
    findIdProduct("Select idProducts from products where name = ?"),
    findIdCustomer("Select idCustomers from customers where name = ?"),
    getProduct("Select * from products where idProducts != ?"),
    addSupplier("INSERT into `sklad`.`suppliers` (`name`)" + "VALUES (?)"),
    addCustomer("Insert into customers (name, country, adress) VALUES(?, ?, ?)"),
    getNameOfProductsGroups("Select name from productsgroups"),
    getOrders("Select products.name, orders.amount, date, cost, customers.name from orders inner join products on orders.idProducts = products.idProducts " +
        "inner join customers on customers.idCustomers = orders.idCustomers"),
    addOrder("Insert into orders (idProducts, amount, date, cost, idCustomers) " +
            "VALUES (?, ?, ?, ?, ?)"),
    addProductGroup("INSERT into `sklad`.`productsgroups` (`name`)" + "VALUES (?)");
    private String query;

    ProductQueries (String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return query;
    }
}
