package tables;

import db.MySQLConnector;

import java.sql.ResultSet;

public class CuratorsTable extends AbsTable{
    private final static String TABLE_NAME = "Curator";

    public CuratorsTable(MySQLConnector db) {super(TABLE_NAME, db);}

    //Заполнить таблицы данными: 4 куратора
    public void insertIntoCurator(){
       //db = new MySQLConnector();
        final String sqlRequest = String.format("INSERT Curator(id, fio) VALUES (111, 'Ivanov'), (112, 'Petrov'), (113, 'Sidorov'), (114, 'Smirnov')", TABLE_NAME);
        ResultSet rs = db.executeRequest(sqlRequest);
       // db.close();

    }
}
