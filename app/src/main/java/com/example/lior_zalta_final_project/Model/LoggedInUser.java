package com.example.lior_zalta_final_project.Model;

import java.util.Calendar;
import java.util.HashMap;

public class LoggedInUser {
    private static User user;

    public static void setUser(User user) {
        LoggedInUser.user = user;
    }

    public static User getUser() {
        return user;
    }

    public static Double getBudgetSum(){
        Double sum = 0.0;

        for (Double val: user.getBudget().values()) {
            sum += val;
        }

        return sum;
    }

    public static Double getTransactionsSum(){
        Double sum = 0.0;

        for (Transaction transaction: user.getTransactions()) {
            sum += transaction.getAmount();
        }

        return sum;
    }

    public static HashMap<String, Double> getTransactionsSumCategory(){
        String curDate =  (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + Calendar.getInstance().get(Calendar.YEAR);
        HashMap<String, Double> categoriesAmount = new HashMap<>();

        for (String category: user.getCategories()) {
            categoriesAmount.put(category, 0.0);
        }

        for (Transaction transaction : user.getTransactions()) {
            String[] dateParts = transaction.getDate().split("-");
            String transactionDate = dateParts[1] + "-" + dateParts[2];
            if (transactionDate.equals(curDate)){
                categoriesAmount.merge(transaction.getCategory(), transaction.getAmount(), Double::sum);
            }
        }
        return categoriesAmount;
    }

}
