import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

class AddAction extends RecursiveAction {
	private static final int T_SIZE = 3;
	private int start;
	private int end;
	private int[] numbers;

	public AddAction(int[] n, int s, int e) {
		this.start = s;
		this.end = e;
		this.numbers = n;
	}

	@Override
	protected void compute() {
		System.out.println("---- compute [" + start + ", " + end + "] ----");
		int total = 0;
		if (end - start <= T_SIZE) {
			for (int i = start; i < end; i++) {
				total += numbers[i];
			}
			System.out.println(total);
		} else {
			new AddAction(numbers, start + T_SIZE, end).fork();
			new AddAction(numbers, start, Math.min(end, start + T_SIZE)).compute();
		}
	}
}

class App {
	public static void main(String... args) {
		int data[] = {1,2,3,4,5};
		ForkJoinPool service = new ForkJoinPool();
		service.invoke(new AddAction(data, 0, data.length));
	}

	public static void w(Object s) {
		if (s == null) {
			w("null");
			return;
		}
		Boolean ea = false;
		assert ea = true;
		if (!ea && s instanceof String) {
			System.out.println(s);
		} else {
			System.out.println("["+s.getClass().getName()+"] "+s);
		}
	}
}
