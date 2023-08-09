package tables;

import db.MySQLConnector;

import java.sql.ResultSet;

public class GroupsTable extends AbsTable{
    private final static String TABLE_NAME = "Student_group";

    public GroupsTable(MySQLConnector db) {
        super(TABLE_NAME, db);
    }

    //Заполнить таблицы данными: 3 группы
    public void insertIntoGroup(){
        //db = new MySQLConnector();
        final String sqlRequest = String.format("INSERT Student_group(id, name, id_curator) VALUES (11, 'Biologi', 111), (12, 'Math', 112), (13, 'Literatura', 113)", TABLE_NAME);
        ResultSet rs = db.executeRequest(sqlRequest);
       // db.close();

    }
}
