import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class UseAtomic {
	public static void main(String... args) {
		final AtomicInteger i = new AtomicInteger(0);
		final int t_num = 3;
		ExecutorService s = Executors.newFixedThreadPool(t_num);
		long start = System.currentTimeMillis();
		for (int n = 0; n < t_num; n++) {
			s.submit(() -> {
				for (int m = 0; m < 10; m++) {
					w(m);
					i.incrementAndGet();
				}
			});
		}
		s.shutdown();
		try {
			s.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			w(e);
		}
		w("[counted number] " + i.get());
		w("[time required (millisecond)] "
				+ (System.currentTimeMillis() - start));
	}

	public static void w(Object s) {
		if (s == null) {
			w("null");
			return;
		}
		if (s instanceof String) {
			System.out.println(s);
		} else {
			System.out.println("[" + s.getClass().getName() + "] " + s);
		}
	}
}
