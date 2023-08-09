package tables;

import db.MySQLConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudensTable extends AbsTable{
    private final static String TABLE_NAME = "Student";

    public StudensTable(MySQLConnector db) {
        super(TABLE_NAME, db);
    }


    //Заполнить таблицы данными(15 студентов)
    public void insertIntoStudent(){
       // db = new MySQLConnector();
        final String sqlRequest = String.format("INSERT Student(id, fio, sex, id_group) VALUES (1, 'Ivanov', 'm', 12), (2, 'Petrov', 'm', 12), (3, 'Sidorova', 'f', 12), (4, 'Ivanov', 'm', 12), (5, 'Petrov', 'm', 12), (6, 'Sidorov', 'm', 12), (7, 'Ivanov', 'm', 12), (8, 'Petrov', 'm', 12), (9, 'Sidorov', 'm', 12), (10, 'Ivanov', 'm', 11), (11, 'Petrov', 'm', 11), (12, 'Sidorov', 'm', 11), (13, 'Ivanova', 'f', 11), (14, 'Petrov', 'm', 11), (15, 'Sidorov', 'm', 11)", TABLE_NAME);
        ResultSet rs = db.executeRequest(sqlRequest);
        //db.close();

    }

}
