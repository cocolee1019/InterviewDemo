package chapter12;

/**
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。 （只能交易一次）
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn8fsh/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class LeetCode_maxProfit {

    public static void main(String[] args) {
        System.out.println(maxProfit2(new int[]{2,1,2,1,0,1,2}));
    }

    /**
     * 双循环遍历 --- 超时
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int max = 0;
        for (int i=0; i<prices.length-1; i++) {
            for (int j=i+1; j<prices.length; j++) {
                int t = prices[j] - prices[i];
                max = t > max ? t : max;
            }
        }
        return max;
    }

    public static int maxProfit2(int[] prices) {
        int maxProfit = 0;
        int minPrice = prices[0];
        int t;
        for (int i = 1; i<prices.length; i++) {
            minPrice = minPrice > prices[i] ? prices[i] : minPrice;
            maxProfit = maxProfit > (t = prices[i] - minPrice) ? maxProfit : t;
        }

        return maxProfit;
    }
}
