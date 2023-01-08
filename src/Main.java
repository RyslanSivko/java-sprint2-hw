import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MonthReport monthReport = new MonthReport();
        YearReport yearReport = new YearReport();
        Scanner scanner = new Scanner(System.in);
        printMenu();
        int command = scanner.nextInt();

        while (command != 0) {
            if (command == 1) {
                monthReport.checkReport = new ArrayList<>();
                for (int i = 1; i < 4; i++) {
                    monthReport.load("C:\\Users\\LEGION\\Desktop\\java-sprint2-hw-main\\resources\\m.20210" + i + ".csv");
                }
            } else if (command == 2) {
                yearReport.load("C:\\Users\\LEGION\\Desktop\\java-sprint2-hw-main\\resources\\y.2021.csv");
            } else if (command == 3 ){
                monthReport.sumMonth();
                monthReport.sravnenie(yearReport.years);
            } else if (command == 4) {
                monthReport.avg();
            } else if (command == 5) {
                yearReport.avgYear();
            }
            else {
                System.out.println("команды нет");
            }
            printMenu(); // печатаем меню ещё раз перед завершением предыдущего действия
            command = scanner.nextInt(); // повторное считывание данных от пользователя
        }
        System.out.println("завершено");
    }
    private static void printMenu() {
        System.out.println("1 - Считать все месячные отчёты ");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - выход");
    }
}