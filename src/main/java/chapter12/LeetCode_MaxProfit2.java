package chapter12;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * 这道题和算法无关，因为当天卖出后，当天又可以买入，就是一个最大利润的问题，直接将利润累加即可。
 */
public class LeetCode_MaxProfit2 {

    public static void main(String[] args) {
        int[] nums = {7,6,4,3,1};
        System.out.println(maxProfit(nums));
    }

    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i=1; i<prices.length; i++) {
            maxProfit += prices[i] - prices[i-1] > 0 ? prices[i] - prices[i-1] : 0;
        }
        return maxProfit;
    }
}
