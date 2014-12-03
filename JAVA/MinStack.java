package bingyue.leetcode;

import java.util.Stack;

/**
 * @Description Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *   push(x) -- Push element x onto stack.
 *   pop() -- Removes the element on top of the stack.
 *   top() -- Get the top element.
 *   getMin() -- Retrieve the minimum element in the stack.
 *   关于时间复杂度，不超过constant time 常量时间O(1)
 * @author here.bingyue@gmail.com
 */
public class MinStack {
	
	//声明数据栈
	private Stack<Integer> elementsStack=new Stack<Integer>();
	//声明辅助栈
	private Stack<Integer> supportStack=new Stack<Integer>();
	/**
	 * 考虑到时间复杂度的需求，添加一个辅助栈，
	 * 每次入栈时将元素分别存入数据栈和辅助栈，
	 * 辅助栈中的数据始终保持最小值在栈顶，需要获取最小值时，直接Peek()辅助栈即可。
	 */
	public static void main(String[] args){
		MinStack minStack=new MinStack();
		minStack.push(0);
		minStack.push(1);
		minStack.push(0);
		System.out.print(minStack.getMin());
		minStack.pop();
		System.out.print(minStack.getMin());
	}
	public void push(int x) {
    	//始终保持辅助栈顶是最小元素
    	if(supportStack.empty() || x <= supportStack.peek()){
    		supportStack.push(x);
    	}
    	elementsStack.push(x);
    }
	
    public void pop() {
        //更新辅助栈
    	if(elementsStack.peek().equals(supportStack.peek())){
    		supportStack.pop();
    	}
    	elementsStack.pop();
    }

    public int top() {
		return elementsStack.peek();
    }
    
    public int getMin() {
    	//辅助栈
		return supportStack.peek();
    }

}
