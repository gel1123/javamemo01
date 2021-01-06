import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

class App {

	private static ConcurrentHashMap<String, Long> conc_map
		= new ConcurrentHashMap<>();
	private static ConcurrentLinkedDeque<String> conc_deque
		= new ConcurrentLinkedDeque<>();
	private static CopyOnWriteArrayList cp_list
		= new CopyOnWriteArrayList();
	private static final long S = System.currentTimeMillis();
	private static final int T_NUM = 4;
	private static final CyclicBarrier B
		= new CyclicBarrier(T_NUM);

	public static void main(String... args) {


		Runnable r1 = ()-> {
			String name = "r1";
			w("---- "+name+" ----");
			conc_map.put(name, lap());
			conc_deque.add(name);
			w("["+name+" getNumberWaiting] "
				+ B.getNumberWaiting());
			//w("["+name+" getParties] "+B.getParties());
			try{ B.await(); } catch (
				InterruptedException | BrokenBarrierException e
			) { w(e); };
			w("!!! break through the barrier ["
				+T_NUM+" threads] !!!");
			w("---- conc_map ----");
			conc_map.entrySet().stream()
				.map(e -> e.getKey() + ": " + e.getValue())
				.forEach(System.out::println);
			w("---- conc_deque ----");
			w(conc_deque.poll());
			w(conc_deque.poll());
			w(conc_deque.poll());
			w(conc_deque.poll());
			w(conc_deque.poll());
		};
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				String name = "r2";
				w("---- "+name+" ----");
				conc_map.put(name, lap());
				conc_deque.add(name);
				w("["+name+" getNumberWaiting] "
					+ B.getNumberWaiting());
				//w("["+name+" getParties] "+B.getParties());
				try{ B.await(); } catch (
					InterruptedException | BrokenBarrierException e
				) { w(e); };
			}
		};
		RunnableFuture r3 = new FutureTask<Void>(
			() -> {
				String name = "r3";
				w("---- "+name+" ----");
				conc_map.put(name, lap());
				conc_deque.add(name);
				w("["+name+" getNumberWaiting] "
					+ B.getNumberWaiting());
				//w("["+name+" getParties] "+B.getParties());
				try{ B.await(); } catch (
					InterruptedException | BrokenBarrierException e
				) { w(e); };
				return null; // "Void" class must return null;
			}
		);
		RunnableFuture r4 = new FutureTask<Void>(
			new Callable<Void>() {
				@Override
				public Void call() throws Exception {
					String name = "r4";
					w("---- "+name+" ----");
					conc_map.put(name, lap());
					conc_deque.add(name);
					w("["+name+" getNumberWaiting] "
						+ B.getNumberWaiting());
					//w("["+name+" getParties] "+B.getParties());
					try{ B.await(); } catch (
						InterruptedException | BrokenBarrierException e
					) { w(e); };
					return null; // "Void" class must return null;
				}
			}
		);
		Thread t1 = new Thread(r1);
		t1.start();
		Thread t2 = new Thread(r2);
		t2.start();
		Thread t3 = new Thread(r3);
		t3.start();
		Thread t4 = new Thread(r4);
		t4.start();
	}
	public static void w(Object s) {
		if (s == null) {
			w("null");
			return;
		}
		if (s instanceof String) {
			System.out.println(s);
		} else {
			System.out.println("["+s.getClass().getName()+"] "+s);
		}
	}
	public static long lap() {
		return System.currentTimeMillis() - S;
	}
}
