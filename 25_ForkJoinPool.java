import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

class UseForkJoinPool {

	public static final long START = System.nanoTime();

	public static void sleepRandom() {
		try {
			Thread.sleep((long) (Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String lap(String name) {
		String nano = ((Long) (System.nanoTime() - START)).toString();
		System.out.println("[[" + name + "]] " + nano);
		return "returned value is " + nano + " (" + name + ")";
	}

	public static String doSomething(String name) {
		sleepRandom();
		return lap(name);
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		System.out.println("start");

		ForkJoinPool pool = new ForkJoinPool();

		ForkJoinTask<Void> act1 = new RecursiveAction() {
			@Override
			protected void compute() {
				doSomething("act1");
			}
		};
		ForkJoinTask<Void> act2 = new RecursiveAction() {
			@Override
			protected void compute() {
				doSomething("act2");
			}
		};
		ForkJoinTask<Void> act3 = new RecursiveAction() {
			@Override
			protected void compute() {
				doSomething("act3");
			}
		};

		ForkJoinTask<String> task1 = new RecursiveTask<String>() {
			@Override
			protected String compute() {
				return doSomething("task1");
			}
		};
		ForkJoinTask<String> task2 = new RecursiveTask<String>() {
			@Override
			protected String compute() {
				return doSomething("task2");
			}
		};
		ForkJoinTask<String> task3 = new RecursiveTask<String>() {
			@Override
			protected String compute() {
				return doSomething("task3");
			}
		};

		Future<Void> f1 = pool.submit(act1); // async and return Future<Void>
		Future<String> f2 = pool.submit(task1); // async and return Future
		pool.execute(act2); // async and return void
		pool.execute(task2); // async and return void

		//		pool.invoke(act1);
		//		pool.invoke(task1);
		//		pool.invoke(act2);
		//		pool.invoke(task2);
		//		pool.invoke(act3); // sync and return void
		//		pool.invoke(task3); // sync and return void

		pool.shutdown();
		System.out.println("done shutdown: " + ((Long) (System.nanoTime() - START)).toString());
		pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		System.out.println("done awaitTermination: " + ((Long) (System.nanoTime() - START)).toString());

		System.out.println(f1.get());
		System.out.println(f2.get());

		System.out.println("end");
	}
}
