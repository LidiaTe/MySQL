import db.MySQLConnector;
import tables.CuratorsTable;
import tables.GroupsTable;
import tables.StudensTable;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//import static com.google.protobuf.Descriptors.FieldDescriptor.table;

public class Main {

    //        Создать таблицу Student
    // Колонки id, fio, sex, id_group
    public static Map<String, String> userColumns = new HashMap<String, String>(){{

        put("id", "int(10)");
        put("fio", "varchar(20)");
        put("sex", "varchar(10)");
        put("id_group", "int(10)");
    }};

//    Создать таблицу Curator
//    Колонки id, fio
    public static Map<String, String> curatorColumns = new HashMap<String, String>(){{

        put("id", "int(10)");
        put("fio", "varchar(20)");

    }};

//Создать таблицу Group
//Колонки id, name, id_curator
    public static Map<String, String> groupColumns = new HashMap<String, String>(){{

        put("id", "int(10)");
        put("name", "varchar(20)");
        put("id_curator", "int(10)");

    }};
            public static void main(String[] args) throws SQLException {
                MySQLConnector db = new MySQLConnector();
                try {

                    StudensTable table = new StudensTable(db);
//Создаем таблицу Студенты
                    if (table.isTableExist("student")) {
                        table.deleteTable("student");
                    }
                    table.create(userColumns);

                    //Заполняем данными таблицу студенты
                    table.insertIntoStudent();

                    GroupsTable table2 = new GroupsTable(db);

                    //Создаем таблицу Группы
                    if (table2.isTableExist("student_group")) {
                        table2.deleteTable("student_group");
                    }
                    table2.create(groupColumns);

                    // Заполняем данными таблицу Group
                    table2.insertIntoGroup();


                    CuratorsTable table3 = new CuratorsTable(db);
                    //Создаем таблицу Кураторы
                    if (table3.isTableExist("curator")) {
                        table2.deleteTable("curator");
                    }
                    table3.create(curatorColumns);
                    // Заполняем данными таблицу Group
                    table3.insertIntoCurator();

//                 Вывести на экран информацию о всех студентах включая название группы и имя куратора
                    System.out.println("Вывести на экран информацию о всех студентах включая название группы и имя куратора");
                    table.selectAllfrom3Tables();

//                    Вывести на экран количество студентов
                    System.out.println("Общее количество студентов:");
                    table.countStudens();

//                    Вывести студенток
                    System.out.println("Все студентки:");
                      table.femaleStudens();

//                    Обновить данные по группе сменив куратора
                    System.out.println("Обновляем данные по группе сменив куратора...");
                    System.out.println("");
                      table3.updateCurator();

//                    Вывести список групп с их кураторами
                    System.out.println("Cписок групп с их кураторами:");
                    table2.listOfGroupWithCurators();

//                    Используя вложенные запросы вывести на экран студентов из определенной группы(поиск по имени группы)
                    System.out.println("Студенты из группы Математика:");
                    table.studentsOfGroup();

                    }
                catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    db.close();}


            } //main

}
