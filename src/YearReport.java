import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YearReport {
    public static boolean consideredYear = false;
    public ArrayList<Year> years;

    public YearReport() {
        years = new ArrayList<>();
    }

    public void load(String path) {
        years = new ArrayList<>();

        List<String> lines = readFileContents(path);
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(",");
            String title = parts[0];
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean is_expense = Boolean.parseBoolean(parts[2]);
            Year yeras = new Year(month, amount, is_expense,Integer.parseInt(path.substring(path.length()-8,path.length()-4)));
            years.add(yeras);


        }
        consideredYear = true;
    }
    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
    public void avgYear() {
        if (MonthReport.consideredMonth && consideredYear) {
            int plus = 0;
            int minus = 0;
            System.out.println(years.get(0).year);
            for (Year year : years) {
                if (year.is_expense) {
                    minus += year.amount;
                } else {
                    plus += year.amount;

                }
            }

            for (int i = 0; i < years.size() / 2; i++) {
                if (years.get(i * 2).is_expense) {
                    System.out.println("доход за " + (i + 1) + " месяц = " + (years.get(i * 2 + 1).amount - years.get(i * 2).amount));
                } else {
                    System.out.println("доход за " + (i + 1) + " месяц  = " + (years.get(i * 2).amount - years.get(i * 2 + 1).amount));
                }
            }
            System.out.println("средни доход за год = " + plus / 3 + "\nсредний расход за год = " + minus / 3);
        }
        else {
            System.out.println("нужно считать данные");
        }
    }
}
