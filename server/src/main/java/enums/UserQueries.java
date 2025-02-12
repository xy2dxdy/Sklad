package enums;

public enum UserQueries {
    findUserByLogin("Select * from users where login = ?"),
    insertUser("INSERT INTO users (`login`, `password`, `email`, `role`, `isBanned`, `surname`, `name`, `pattername`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"),
    getCurrencyRate("select currencyname, currensyrate from kp.user\n" +
            "inner join kp.currency on currencyid = user.currensyid" +
            "where currencyid = ?"),
    becomeCreator("UPDATE user SET userroleid = ? where userid = ?"),
    buyContent("INSERT INTO usercontent (`userid`, `usercontentid`) VALUES (?, ?)"),
    changeMoney("UPDATE users SET wallet = ? WHERE (`userid` = ?)"),
    updateUser("Update users set login = ?, password = ?, email = ?, role = ?, surname = ?, name = ?, pattername = ? where login = ?"),
    updateUserByUser("Update users set login = ?, password = ?, email = ?, surname = ?, name = ?, pattername = ? where login = ?"),
    blockUser("Update users set isBanned = ? where login = ?"),
    deleteUser("delete from users where login = ?"),
    getUsers("select * from users");

    private String query;

    UserQueries(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return query;
    }
}
