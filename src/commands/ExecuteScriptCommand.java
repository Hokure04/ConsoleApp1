package commands;

import utility.*;
import exceptions.*;


/**
 * Класс команда executeScript наследующийся от абстрактного класса Commands.AbstractCommand
 */
public class ExecuteScriptCommand extends AbstractCommand {
    /** поле переговорщик с пользователем */
    private NegotiatorWithUser nGW;
    private ExceptionValidator eValidator;

    /**
     * Конструктор - создание команды executeScript
     * @param nGW переговорщик с пользователем
     */
    public ExecuteScriptCommand(NegotiatorWithUser nGW, ExceptionValidator eValidator){
        super("execute_script file_name","считать и исполнить скрипт из указанного файла");
        this.nGW = nGW;
        this.eValidator = eValidator;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса Commands.AbstractCommand
     * метод реализует исполнение команды executeScript
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    @Override
    public boolean execute(String argument){
        try{
            eValidator.argument(argument);
            System.out.println("Считывание скрипта");
            return true;
        }catch (IncorrectlyInstalledElement e){
            System.out.println("Установлено неправильное значение элемента! Вы дожны обязательно указать файл в котором находиться скрипт");
        }return false;
    }
}

