package algorithm.search.testGraph;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test {

	public static void main(String[] args) {
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(1);
		stack.push(2);
		stack.addLast(3);
		System.out.println(stack.removeLast());
		System.out.println(stack);
	}
}
