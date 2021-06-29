package chapter12;

import java.util.Arrays;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 */
public class LeetCode_MinStack {

    private int[] data;
    private int top;

    /** initialize your data structure here. */
    public LeetCode_MinStack() {
        data = new int[1];
        top = 0;
    }

    public void push(int x) {
        if (top >= data.length) {
            data = Arrays.copyOf(data, data.length + 4);
        }

        data[top++] = x;
    }

    public void pop() {
        if (top > 0) {
            top --;
        }
    }

    public int top() {
        return data[top-1];
    }

    public int getMin() {
        int min = data[0];
        for (int i=1; i<top; i++) {
            min = min < data[i] ? min : data[i];
        }
        return min;
    }

    public static void main(String[] args) {
        LeetCode_MinStack minStack = new LeetCode_MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
