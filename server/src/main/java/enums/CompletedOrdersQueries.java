package enums;

public enum CompletedOrdersQueries
{
    getOrders("Select products.name, completedorders.amount, orderDate, confirmationDate, cost, customers.name from completedorders inner join products on completedorders.idProducts = products.idProducts " +
            "inner join customers on customers.idCustomers = completedorders.idCustomers"),
    insertOrder("insert into completedorders (idProducts, amount, orderDate, confirmationDate, cost, idCustomers )" +
            "VALUES (?, ?, ?, ?, ?, ?)");

    private String query;

    CompletedOrdersQueries(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return query;
    }
}
