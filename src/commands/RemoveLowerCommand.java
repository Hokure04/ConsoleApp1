package commands;

import data.MusicBand;
import exceptions.IncorrectlyInstalledElement;
import exceptions.MusicBandDoesNotExistException;
import exceptions.NothingInTheCollectionException;
import utility.CollectionManager;
import utility.ExceptionValidator;
import utility.NegotiatorWithUser;
import utility.Receiver;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

/**
 * Класс команда Commands.RemoveLowerCommand наследующийся от абстрактного класса Commands.AbstractCommand
 */
public class RemoveLowerCommand extends AbstractCommand {
    /** поле мэнеджер коллекции */
    private CollectionManager collectionManager;
    /** поле переговорщик с пользователем */
    private NegotiatorWithUser nGW;
    private Receiver receiver;
    private ExceptionValidator eValidator;

    /**
     * Конструктор - создание команды removeLower
     * @param collectionManager мэнеджер коллекции
     * @param nGW переговорщик с пользователем
     */
    public RemoveLowerCommand(CollectionManager collectionManager, NegotiatorWithUser nGW, Receiver receiver, ExceptionValidator eValidator){
        super("remove_lower {element}", "удалить из коллекции все элементы, меньше чем заданный");
        this.collectionManager = collectionManager;
        this.nGW = nGW;
        this.receiver = receiver;
        this.eValidator = eValidator;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса Commands.AbstractCommand
     * метод реализует исполнение команды removeLower
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    @Override
    public boolean execute(String argument){
        try{
            eValidator.noArgument(argument);
            eValidator.nullCollection(collectionManager);
            MusicBand musicBand = new MusicBand(
                    collectionManager.generateNextId(),
                    nGW.askName(),
                    nGW.askCoordinates(),
                    nGW.askNumberOfParticipants(),
                    nGW.askSinglesCount(),
                    nGW.askDescription(),
                    nGW.askGenre(),
                    nGW.askStudio(),
                    LocalDateTime.now()
            );
            MusicBand collectionBand = collectionManager.getByValue(musicBand);
            eValidator.doesntExist(collectionBand);
            receiver.removeLower(collectionBand);
            System.out.println("Группы меньше заданной удалены!");
            return true;
        }catch (IncorrectlyInstalledElement e){
            System.out.println("Установлено неправильное значение элемента! Вы должны ввести просто команду без каких-либо аргументов");
        }catch (MusicBandDoesNotExistException e){
            System.out.println("Данного элемента в коллекции нет!");
        }catch (NothingInTheCollectionException e){
            System.out.println("Коллекция пуста! Веедите в неё данные и повторите попытку");
        }catch (NoSuchElementException e){
            System.out.println("Введённые данные не являются корректными программа не может их исполнить");
        }
        return false;
    }
}
