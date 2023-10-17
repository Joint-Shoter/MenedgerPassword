import java.io.*;
public class Menedger {
    private char key = 'k';
    private String password;
    private String login;

    private int len;
    public Menedger( String login, String password) {
        this.password = password;
        this.len = password.length();
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
        this.len = password.length();
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public String code(String s){
        String result = "";
        for (char i : s.toCharArray()){
            result = result + (char)(i ^ key);
        }
        return result;
    }


    /*С начала записывается логин, потом через пробел длинна пароля, уже затем пароль
    */
    public void writeToFile(String fileName){
        try(FileWriter writer = new FileWriter(fileName, false))
        {
            // запись всей строки
            writer.write(login);

            writer.append(' ');
            writer.write(code(String.valueOf(len)));

            writer.append(' ');
            writer.write(code(password));
            writer.append('\n');


            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }


    }

    //Чтение пароля по логину
    public String readPassword(String fileName){
        String buffer = "";

        //Считываем файл в буффер
        try(FileReader reader = new FileReader(fileName))
        {
            // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){
                buffer = buffer + (char)c;
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        //Считываем длинну пароля
        int index = buffer.indexOf(login) + login.length() + 1;
        String len = "";

        char ch = buffer.charAt(index);
        while (ch != ' '){
            len = len + ch;
            index++;
            ch = buffer.charAt(index);
        }
        int passwordLen = new Integer(code(len));

        //Читаем пароль
        password = "";
        for(int i = 0; i <= passwordLen; i++){
            password = password + ch;
            index++;
            ch = buffer.charAt(index);
        }
        password = code(password);
        password = password.substring(1);

        return password;
    }

    //Достать пароль из файла можно по логину
    //Сначала поиск подстроки (читаем по 10 строк сразу т.к 'f' = \n)
    //Отступаем от данной строки длинну +1 и читаем длинну пароля
    //после этого чтение прароля
}
