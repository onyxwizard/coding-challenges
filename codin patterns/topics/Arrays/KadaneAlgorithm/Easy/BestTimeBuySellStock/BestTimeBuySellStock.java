package KadaneAlgorithm.Easy.BestTimeBuySellStock;

public class BestTimeBuySellStock {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int currentProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            currentProfit = Math.max(diff, currentProfit + diff);
            maxProfit = Math.max(maxProfit, currentProfit);
        }

        return maxProfit;
    }
}
