package enums;

public enum OrderQueries
{
    findUserByLogin("Select * from user s where login = ?"),
    deleteOrder("DELETE from orders where idProducts = ? AND amount = ? AND date = ? AND cost = ? AND idCustomers = ?"),
    insertUser("INSERT INTO `sklad`.`users` (`login`, `password`, `email`, `role`, `isBanned`, 'surname', 'name', 'pattername') " +
            "VALUES (?, ?, ?, ?, '0', ?, ?, ?)");


    private String query;

    OrderQueries (String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return query;
    }
}
