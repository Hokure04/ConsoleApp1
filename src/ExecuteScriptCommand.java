/**
 * Класс команда executeScript наследующийся от абстрактного класса AbstractCommand
 */
public class ExecuteScriptCommand extends AbstractCommand {
    /** поле переговорщик с пользователем */
    private NegotiatorWithUSer nGW;

    /**
     * Конструктор - создание команды executeScript
     * @param nGW переговорщик с пользователем
     */
    public ExecuteScriptCommand(NegotiatorWithUSer nGW){
        super("execute_script file_name","считать и исполнить скрипт из указанного файла");
        this.nGW = nGW;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса AbstractCommand
     * метод реализует исполнение команды executeScript
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    @Override
    public boolean execute(String argument){
        try{
            if(argument.isEmpty()) throw new IncorrectlyInstalledElement();
            System.out.println("Считывание скрипта");
            return true;
        }catch (IncorrectlyInstalledElement e){
            System.out.println("Установлено неправильное значение элемента!");
        }return false;
    }
}

