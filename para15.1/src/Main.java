import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BD bd= new BD();
        Scanner scanner = new Scanner(System.in);
        boolean bool=true;
        System.out.println("""
                            Вы желаете:
                            1.Зарегистрировать нового пользователя
                            2.Зайти уже существующим пользователем
                            """);
        int vibor=scanner.nextInt();
        switch (vibor){
            case 1:{
                System.out.println("Введите имя ползователю");
                String user= scanner.next();
                bd.createUser(user,);
            }
            case 2:{
                System.out.println("Введите имя пользователя:");
                BD.user = scanner.next();
//                System.out.println("""
//                Пользователь с паролем?
//                1.Да
//                2.Нет
//                """);
//                int sw= scanner.nextInt();
//                switch (sw){
//                    case 1:{
//                        System.out.println("Введите пароль:");
//                        BD.password=scanner.next();
//                    }break;
//                    case 2:{
//                        BD.password="";
//                    }break;
//                    default:{System.out.println("Значение выходит за предусмотренные рамки");break;}
//                }

            }break;
        }
        while (bool) {
            System.out.println();
            System.out.println("""
                    Выберите действие:
                    1.Отобразить таблицу меню
                    2.рег
                    """);
            int swtch= scanner.nextInt();
            switch(swtch){
                case 1:{
                    bd.selectMenu();
                }break;
                case 2:{

                }break;
            }
        }
    }
}