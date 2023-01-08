public class Month {
    public String title;
    public boolean isExpense;
    public int quantity;
    public int sumOfOne;
    public int month;

    @Override
    public String toString() {
        return "Month{" +
                "title='" + title + '\'' +
                ", isExpense=" + isExpense +
                ", quantity=" + quantity +
                ", sumOfOne=" + sumOfOne +
                ", month=" + month +
                '}';
    }
    public Month() {
        this.title = null;
        this.isExpense = false;
        this.quantity = 0;
        this.sumOfOne = 0;
        this.month = 0;
    }
    public Month(String title, boolean isExpense, int quantity, int sumOfOne, int month) {
        this.title = title;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
        this.month = month;
    }
}
