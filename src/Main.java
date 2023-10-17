import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Menedger m = new Menedger("Artem", "234567890");
        m.writeToFile("Password.txt");
        System.out.println(m.code("*\u0019\u001F\u000E\u0006"));
        System.out.println(m.code("R"));
        System.out.println(m.readPassword("Password.txt"));

        /*
        Основной цикл программы
         */

        int value = 1;
        while(value != 0){
            System.out.println("1) Создать пароль\n2) Найти пароль по логину \n0) Выход");
            Scanner scanner = new Scanner(System.in);

            value = new Integer(scanner.next());

            if (value == 1){
                System.out.println("Введите логин: ");
                m.setLogin(scanner.next());
                System.out.println("Введите пароль: ");
                m.setPassword(scanner.next());

                System.out.println("Запись сохранена");
                m.writeToFile("Password.txt");
            }

            if(value == 2){
                System.out.println("Введите логин: ");
                m.setLogin(scanner.next());
                m.readPassword("Password.txt");
                System.out.println(m.getPassword());
            }
        }

        /*Artem
1234ff
908988951313*/
    }
}