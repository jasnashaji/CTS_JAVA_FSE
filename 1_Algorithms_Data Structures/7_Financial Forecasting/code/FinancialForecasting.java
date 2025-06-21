public class FinancialForecasting {

    // Recursive method to predict future value
    public static double predictValue(double initialValue, double growthRate, int years) {
        // Base case: if no more years to grow, return current value
        if (years == 0) {
            return initialValue;
        }
        // Recursive case: grow one year and recurse
        return predictValue(initialValue * (1 + growthRate), growthRate, years - 1);
    }

    public static void main(String[] args) {
        double initialValue = 10000;  // Initial amount
        double growthRate = 0.08;     // 8% annual growth
        int years = 5;                // Predict for 5 years

        double futureValue = predictValue(initialValue, growthRate, years);
        System.out.printf("Predicted future value after %d years: ₹%.2f%n", years, futureValue);
    }
}
