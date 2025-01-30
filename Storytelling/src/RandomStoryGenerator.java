import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RandomStoryGenerator {

    // Списки для хранения вариантов персонажей, действий и мест
    private static List<String> characters = new ArrayList<>();
    private static List<String> actions = new ArrayList<>();
    private static List<String> places = new ArrayList<>();

    // Хранилище сгенерированных историй
    private static List<String> stories = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random(); // Для генерации случайных чисел[2]

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в Генератор случайных историй!");

        // Инициализация списков начальными данными
        initData();

        // Основной цикл программы
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addCharacter();
                    break;
                case "2":
                    addAction();
                    break;
                case "3":
                    addPlace();
                    break;
                case "4":
                    generateStory();
                    break;
                case "5":
                    showAllStories();
                    break;
                case "6":
                    removeElement();
                    break;
                case "7":
                    running = false;
                    System.out.println("Выход из программы. До свидания!");
                    break;
                default:
                    System.out.println("Неверный ввод. Повторите попытку.");
            }
        }
    }

    // Инициализация начальными значениями
    private static void initData() {
        characters.add("Рыцарь");
        characters.add("Пират");
        characters.add("Учёный");

        actions.add("танцует");
        actions.add("изобретает что-то");
        actions.add("боится");

        places.add("в подводном городе");
        places.add("на таинственном острове");
        places.add("в дремучем лесу");

        System.out.println("Начальные данные загружены.");
        System.out.println("Персонажей: " + characters.size());
        System.out.println("Действий: " + actions.size());
        System.out.println("Мест: " + places.size());
    }

    // Вывод меню
    private static void printMenu() {
        System.out.println("\nЧто вы хотите сделать?");
        System.out.println("1 - Добавить персонажа");
        System.out.println("2 - Добавить действие");
        System.out.println("3 - Добавить место");
        System.out.println("4 - Сгенерировать историю");
        System.out.println("5 - Посмотреть все сгенерированные истории");
        System.out.println("6 - Удалить элемент (персонажа/действие/место)");
        System.out.println("7 - Выход");
        System.out.print("Выберите пункт: ");
    }

    // Добавить персонажа в список
    private static void addCharacter() {
        System.out.print("Введите имя нового персонажа: ");
        String newCharacter = scanner.nextLine();
        if (!newCharacter.isEmpty()) {
            characters.add(newCharacter);
            System.out.println("Персонаж '" + newCharacter + "' добавлен.");
        } else {
            System.out.println("Имя персонажа не может быть пустым.");
        }
    }

    // Добавить действие в список
    private static void addAction() {
        System.out.print("Введите новое действие: ");
        String newAction = scanner.nextLine();
        if (!newAction.isEmpty()) {
            actions.add(newAction);
            System.out.println("Действие '" + newAction + "' добавлено.");
        } else {
            System.out.println("Действие не может быть пустым.");
        }
    }

    // Добавить место в список
    private static void addPlace() {
        System.out.print("Введите новое место: ");
        String newPlace = scanner.nextLine();
        if (!newPlace.isEmpty()) {
            places.add(newPlace);
            System.out.println("Место '" + newPlace + "' добавлено.");
        } else {
            System.out.println("Место не может быть пустым.");
        }
    }

    // Сгенерировать случайную историю
    private static void generateStory() {
        if (characters.isEmpty() || actions.isEmpty() || places.isEmpty()) {
            System.out.println("Не хватает данных для генерации истории. Добавьте хотя бы по одному элементу в каждый список.");
            return;
        }
        String character = getRandomElement(characters);
        String action = getRandomElement(actions);
        String place = getRandomElement(places);

        String story = character + " " + action + " " + place;
        stories.add(story);

        System.out.println("Сгенерированная история: " + story);
    }

    // Получение случайного элемента из списка
    private static String getRandomElement(List<String> list) {
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    // Показать все сгенерированные истории
    private static void showAllStories() {
        if (stories.isEmpty()) {
            System.out.println("Пока нет сгенерированных историй.");
        } else {
            System.out.println("Все сгенерированные истории:");
            for (int i = 0; i < stories.size(); i++) {
                System.out.println((i + 1) + ") " + stories.get(i));
            }
        }
    }

    // Удаление элемента из списка
    private static void removeElement() {
        System.out.println("Из какого списка удалить элемент?");
        System.out.println("1 - Персонажи");
        System.out.println("2 - Действия");
        System.out.println("3 - Места");
        System.out.print("Выберите пункт: ");
        String choice = scanner.nextLine();

        List<String> chosenList;
        String listName;
        switch (choice) {
            case "1":
                chosenList = characters;
                listName = "персонажей";
                break;
            case "2":
                chosenList = actions;
                listName = "действий";
                break;
            case "3":
                chosenList = places;
                listName = "мест";
                break;
            default:
                System.out.println("Неправильный выбор.");
                return;
        }

        if (chosenList.isEmpty()) {
            System.out.println("Список " + listName + " пуст. Нечего удалять.");
            return;
        }
        System.out.println("Вот текущий список " + listName + ":");
        for (int i = 0; i < chosenList.size(); i++) {
            System.out.println((i + 1) + ") " + chosenList.get(i));
        }
        System.out.print("Введите номер элемента для удаления: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index < 0 || index >= chosenList.size()) {
                System.out.println("Некорректный индекс.");
            } else {
                String removed = chosenList.remove(index);
                System.out.println("Элемент '" + removed + "' успешно удалён.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите целое число.");
        }
    }
}