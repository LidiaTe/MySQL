package tables;

import db.IDBConnector;
import db.MySQLConnector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

public abstract class AbsTable {

    private String tableName;
    private String tableName2;
    private Map<String, String> columns;
    protected IDBConnector db;

    public AbsTable(String tableName, MySQLConnector db) {

        this.tableName = tableName;
        this.db = db;
        //db = new MySQLConnector();
    }

    public void create (Map<String, String> columns) {
        this.columns = columns;
        String sqlRequest = String.format("CREATE TABLE %s (%s)", this.tableName, convertMapColumnsToString());
        //db = new MySQLConnector();
        db.executeRequest(sqlRequest);
        //db.close();
    }

    private String convertMapColumnsToString(){
        final String result = columns.entrySet().stream().map((Map.Entry entry) -> String.format("%s %s", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(", "));
        return result;

    }


    public void selectAll(){
      //  db = new MySQLConnector();
        final String sqlRequest = String.format("SELECT * FROM %s", tableName);
         ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
         //количество колонок в результирующем запросе
         try {
         int columns = rs.getMetaData().getColumnCount();
//         перебор строк с данными
             while (rs.next()) {
                 for (int i = 1; i <= columns; i++) {
                     System.out.println(rs.getString(i) + "\t");
                 }
                 System.out.println();
             }
             } catch (SQLException ex) {
                 ex.printStackTrace();
             }finally {
                 db.close();
             }
     }

// Вывести на экран информацию о всех студентах включая название группы и имя куратора
    public void selectAllfrom3Tables(){
        //  db = new MySQLConnector();
        final String sqlRequest = String.format("SELECT student.id, student.fio, student.sex, student.id_group, student_group.name, curator.fio from student join student_group on student.id_group=student_group.id join curator on student_group.id_curator=curator.id");
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        //количество колонок в результирующем запросе
        try {
            int columns = rs.getMetaData().getColumnCount();
//         перебор строк с данными
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.println(rs.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            //db.close();
        }
    }

//    Вывести на экран количество строк таблицы
    public void countStudens(){
        //  db = new MySQLConnector();
        final String sqlRequest = String.format("SELECT count(*) from %s", tableName);
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        //количество колонок в результирующем запросе
        try {
            int columns = rs.getMetaData().getColumnCount();
    //         перебор строк с данными
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.println(rs.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
 //           db.close();
        }
    }

//    Вывести студенток SELECT * from student  where sex='f'
    public void femaleStudens(){
        //  db = new MySQLConnector();
        final String sqlRequest = String.format("SELECT * from %s  where sex='f'", tableName);
        ResultSet rs = db.executeRequestWithAnswer (sqlRequest);
        //количество колонок в результирующем запросе
        try {
            int columns = rs.getMetaData().getColumnCount();
            //         перебор строк с данными
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.println(rs.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
 //           db.close();
        }
    }

//    Обновить данные по группе сменив куратора
    public void updateCurator(){
        //  db = new MySQLConnector();
        final String sqlRequest = String.format("UPDATE student_group SET id_curator = 114 WHERE id = 11");
        ResultSet rs = db.executeUpdate(sqlRequest);
    }
//    Вывести список групп с их кураторами
// SELECT * from student_group join curator on curator.id=student_group.id_curator;
    public void listOfGroupWithCurators(){
        //  db = new MySQLConnector();
        final String sqlRequest = String.format("SELECT * from student_group join curator on curator.id=student_group.id_curator");
        ResultSet rs = db.executeRequestWithAnswer (sqlRequest);
        //количество колонок в результирующем запросе
        try {
            int columns = rs.getMetaData().getColumnCount();
            //         перебор строк с данными
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.println(rs.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            //           db.close();
        }
    }


//    Используя вложенные запросы вывести на экран студентов из определенной группы(поиск по имени группы)
//SELECT * from student join student_group on student.id_group=student_group.id where student_group.name='Math';
    public void studentsOfGroup(){
        //  db = new MySQLConnector();
        final String sqlRequest = String.format("SELECT * from student join student_group on student.id_group=student_group.id where student_group.name='Math'");
        ResultSet rs = db.executeRequestWithAnswer (sqlRequest);
        //количество колонок в результирующем запросе
        try {
            int columns = rs.getMetaData().getColumnCount();
            //         перебор строк с данными
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.println(rs.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            //           db.close();
        }
    }



    public boolean isTableExist(String tableName) throws SQLException {
    // db.executeRequest("SHOW TABLES");
     ResultSet rs = db.executeRequestWithAnswer("SHOW TABLES");
     while (rs.next()){
       String mysqlTableName = rs.getString(1);
       if (mysqlTableName.equals(tableName)){
           return true;
       }
     }
     return false;
     }

     public void deleteTable(String tableName){
        db.executeRequest(String.format("DROP TABLE %s", tableName));
     }



}
