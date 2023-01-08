import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MonthReport {
    public static boolean consideredMonth = false;

    public ArrayList<Month> checkReport = new ArrayList<>();
    public void load(String path){


        List<String> lines = readFileContents(path);
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(",");
            String title = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);


            Month month = new Month(title,isExpense,quantity,sumOfOne,Integer.parseInt(path.substring(path.length()-5,path.length()-4)));
            checkReport.add(month);
        }
        consideredMonth = true;
    }

    public ArrayList<HashMap<Integer, Integer>> sumMonth(){
        HashMap<Integer,Integer> sumMonthPlus = new HashMap<>();
        HashMap<Integer,Integer> sumMonthMinus = new HashMap<>();
        sumMonthPlus.put(1,0);
        sumMonthPlus.put(2,0);
        sumMonthPlus.put(3,0);
        sumMonthMinus.put(1,0);
        sumMonthMinus.put(2,0);
        sumMonthMinus.put(3,0);
        for (Month month : checkReport) {

            if (!month.isExpense) {
                sumMonthPlus.put(month.month, sumMonthPlus.get(month.month) + month.sumOfOne * month.quantity);
            } else {
                sumMonthMinus.put(month.month, sumMonthMinus.get(month.month) + month.sumOfOne * month.quantity);
            }
        }
        ArrayList< HashMap<Integer,Integer>> result = new ArrayList<>();
        result.add(sumMonthPlus);
        result.add(sumMonthMinus);
        return result;
    }

    public void sravnenie(ArrayList<Year> years){
        if(consideredMonth && YearReport.consideredYear) {
            ArrayList<HashMap<Integer, Integer>> summa = this.sumMonth();
            HashSet<Integer> erors = new HashSet<>();
            for (Year year : years) {
                if (year.is_expense) {
                    if ((year.amount != summa.get(0).get(year.month) && summa.get(0).get(year.month) != null)) {
                        erors.add(year.month);
                    }
                } else {
                    if ((year.amount != summa.get(1).get(year.month) && summa.get(1).get(year.month) != null)) {
                        erors.add(year.month);
                    }
                }
            }
            if (erors.size() != 0) {
                System.out.println("ошибка в месяцах:" + erors);
            } else {
                System.out.println("успешно");
            }
        }
        else {
            System.out.println("нужно считать данные");
        }
    }
    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
    public void avg() {
        if (consideredMonth && YearReport.consideredYear) {
            Month[] arraySum = new Month[3];
            Month[] arrayMinus = new Month[3];
            for (int i = 0; i < 3; i++) {
                arraySum[i] = new Month();
                arrayMinus[i] = new Month();
            }
            for (Month month : checkReport) {
                if ((arraySum[month.month - 1].sumOfOne * arraySum[month.month - 1].quantity < month.sumOfOne * month.quantity) && month.isExpense) {
                    arraySum[month.month - 1] = month;
                }
                if ((arrayMinus[month.month - 1].sumOfOne * arrayMinus[month.month - 1].quantity < month.sumOfOne * month.quantity) && !month.isExpense) {
                    arrayMinus[month.month - 1] = month;
                }
            }
            for (int i = 0; i < arraySum.length; i++) {
                System.out.println("месяц " + arraySum[i].month +  " \nтрата: "+arraySum[i].title+ " = " + arraySum[i].sumOfOne * arraySum[i].quantity + "\nприбыль " + arrayMinus[i].title + " = " + arrayMinus[i].sumOfOne * arrayMinus[i].quantity);
            }
        }
        else {
            System.out.println("нужно считать данные");
        }
    }
}
