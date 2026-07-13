import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
// Singleton Design Pattern: Ensures that a class has only one instance and provides a global point of access to it.

// Lazy Initialization Singleton (Not Thread-Safe)
// This implementation is not thread-safe. If multiple threads call getInstance() simultaneously when instance is null, it's possible to create multiple instances.
class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
    } // Private constructor to prevent instantiation

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }

        return instance;
    }
}

// Thread-Safe Singleton using Synchronized Method
// This implementation is thread-safe but may have performance issues due to
// synchronization overhead.
// This approach is correct but has a performance cost: every call to
// getInstance() acquires a lock, even after the instance has been created. Once
// the instance exists, there is no reason to synchronize.
class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {
    } // Private constructor to prevent instantiation

    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }

        return instance;
    }
}

// Double-Checked Locking Singleton
// This implementation is thread-safe and reduces the overhead of acquiring a
// lock by first checking if the instance is already created before
// synchronizing.
class DoubleCheckedSingleton {
    private static volatile DoubleCheckedSingleton instance; // The volatile keyword ensures that multiple threads
                                                             // handle the instance variable correctly when it is being
                                                             // initialized to the Singleton instance.

    private DoubleCheckedSingleton() {
    }

    public static DoubleCheckedSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }

        return instance;
    }
}

// Eager Initialization Singleton
// This implementation creates the instance at the time of class loading. It is
// thread-safe but may lead to resource wastage if the instance is never used.
class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}

// Bill Pugh Singleton Implementation
// This implementation uses a static inner helper class to hold the Singleton
// instance. The instance is created only when the getInstance() method is
// called for the first time, ensuring lazy initialization and thread safety.
class BillPughSingleton {
    private BillPughSingleton() {
    }

    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}

// Enum Singleton
// This implementation uses an enum to create a Singleton. It is thread-safe and
// provides serialization machinery for free, but it may not be suitable for all
// use cases.
enum EnumSingleton {
    INSTANCE;

    public void someMethod() {
        // Method implementation
    }
}

// Static Block Initialization Singleton
// This implementation uses a static block to initialize the Singleton instance.
// It is thread-safe and allows for exception handling during instance creation,
// but it may lead to resource wastage if the instance is never used.
class StaticBlockSingleton {
    private static final StaticBlockSingleton instance;

    private StaticBlockSingleton() {
    }

    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating singleton instance");
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}

// ************** Example: in-memory cache manager *****************
/*
 * CacheManager cacheA = new CacheManager();
 * cacheA.put("user:42", userData);
 * 
 * CacheManager cacheB = new CacheManager();
 * cacheB.get("user:42"); // null! Different instance, different map
 * 
 * // Problems:
 * // - Duplicate HashMaps wasting memory
 * // - Writes in one component invisible to others
 * // - TTL cleanup duplicated across instances
 */
enum CacheManager {
    INSTANCE;

    private record CacheEntry(Object value, Instant expiry) {
        boolean isExpired() {
            return expiry != null && Instant.now().isAfter(expiry);
        }
    }

    private final ConcurrentHashMap<String, CacheEntry> cache = new ConcurrentHashMap<>();

    public void put(String key, Object value, long ttlMillis) {
        Instant expiry = ttlMillis > 0 ? Instant.now().plusMillis(ttlMillis) : null;
        cache.put(key, new CacheEntry(value, expiry));
    }

    public void put(String key, Object value) {
        put(key, value, 0); // No TTL
    }

    public String get(String key) {
        CacheEntry entry = cache.get(key);
        if (entry == null || entry.isExpired()) {
            cache.remove(key); // Clean up expired entry
            return null;
        }
        return (String) entry.value();
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public int size() {
        cache.entrySet().removeIf(entry -> entry.getValue().isExpired());
        return cache.size();
    }
}

public class Main {
    public static void main(String[] args) {
        CacheManager cache1 = CacheManager.INSTANCE;
        CacheManager cache2 = CacheManager.INSTANCE;

        // Both cache1 and cache2 refer to the same instance
        System.out.println("Are both cache instances the same? " + (cache1 == cache2)); // true

        cache1.put("user:42", "John Doe", 5000); // 5 seconds TTL
        cache1.put("user:43", "Jane Doe");

        System.out.println("Cache size after adding user: " + cache2.size()); // 1
        System.out.println("Cache size after adding another user: " + cache2.size()); // 2
        System.out.println("Size of the cache: " + cache2.size()); // 2
    }
}