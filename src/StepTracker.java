import java.util.Scanner;

class StepTracker {
    Scanner scanner;
    MonthData[] monthToData = new MonthData[12];

    StepTracker(Scanner scan) {
        scanner = scan;

        for(int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }

    void addNewNumbersStepsPerDay() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите номер месяца");
        int month = scanner.nextInt();
        if (month < 1 || month > 12) {
            System.out.println("Проверьте правильность ввода месяца");
            return;
        }

        System.out.println("Введите день от 1 до 30 (включительно)");
        int day = scanner.nextInt();
        if (day < 1 || day > 30) {
            System.out.println("Проверьте правильность ввода дня");
            return;
        }

        System.out.println("Введите количество шагов");
        int steps = scanner.nextInt();
        if (steps < 0) {
            System.out.println("Количество шагов не может быть отрицательным");
            return;
        }

        MonthData monthData = monthToData[month - 1];
        monthData.days[day - 1] = steps;
    }

    int goalByStepsPerDay = 10000;

    void changeStepGoal() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целевое количество шагов за день");
        int newStepsPerDay = scanner.nextInt();
        if (newStepsPerDay <= 0) {
            System.out.println("Количество шагов не может быть нулевым или отрицательным");
            return;
        }
        goalByStepsPerDay = newStepsPerDay;
    }

    Converter converter = new Converter();

    void printStatistic() {
        System.out.println("Введите номер месяца");
        Scanner scanner = new Scanner(System.in);
        int month = scanner.nextInt();
        if (month < 1 || month > 12) {
            System.out.println("Проверьте правильность ввода месяца");
            return;
        }

        MonthData monthData = monthToData [month - 1];
        int sumSteps = monthData.sumStepsFromMonth();

        System.out.println("Общая статистика по дням:");
        monthData.printDaysAndStepsFromMonth();
        System.out.println("Сумма шагов за месяц: " + monthData.sumStepsFromMonth());
        System.out.println("Максимальное пройденное количество шагов за месяц: " + monthData.maxSteps());
        System.out.println("Среднее пройденное количество шагов за месяц: " + (double) monthData.maxSteps() / 30);
        System.out.println("Пройденная за месяц дистанция в км: " + converter.convertToKm(sumSteps));
        System.out.println("Количество сожженных килокалорий за месяц: " + converter.convertStepsToKcal(sumSteps));
        System.out.println("Лучшая серия: " + monthData.bestSeries(goalByStepsPerDay));
        System.out.println();
    }
}
