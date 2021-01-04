import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
/**
 * Studying about 
 * "ConcurrentHashMap", 
 * "ConcurrentLinkedDeque", 
 * "CopyOnWriteArrayList"
 **/
class UseAtomic {
	public static void main(String... args) {
		

























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
}
