import java.util.Map;
import java.util.TreeMap;

public class HashTable<K, V> {

    private static final int[] CAPACITY = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

    private static final int UPPER_TOLERANCE = 10;
    private static final int LOWER_TOLERANCE = 2;
    private int capacityIndex = 0;

    private int size;
    private int M;//用来对hash值取模的除数,最好为素数
    private TreeMap<K, V>[] hashtable;//发生hash冲突时用来存放冲突元素

    public HashTable() {
        this.M = CAPACITY[capacityIndex];
        this.size = 0;
        this.hashtable = new TreeMap[M];
        for (int i = 0; i < M; ++i) {
            hashtable[i] = new TreeMap<>();
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void add(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, value);
            ++size;

            if (size >= M * UPPER_TOLERANCE && capacityIndex + 1 < CAPACITY.length) {
                ++capacityIndex;
                resize(CAPACITY[capacityIndex]);
            }
        }
    }

    public V remove(K key) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            --size;

            if (size <= M * LOWER_TOLERANCE && capacityIndex - 1 >= 0) {
                --capacityIndex;
                resize(CAPACITY[capacityIndex]);
            }

            return map.remove(key);
        } else {
            return null;
        }
    }

    public void set(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            throw new IllegalArgumentException(key + " doesn't exist.");
        }
    }

    public boolean contains(K key) {
        TreeMap<K, V> map = hashtable[hash(key)];
        return map.containsKey(key);
    }

    public V get(K key) {
        TreeMap<K, V> map = hashtable[hash(key)];
        return map.get(key);
    }

    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; ++i) {
            newHashTable[i] = new TreeMap<>();
        }
        M = newM;
        for (TreeMap<K, V> map : hashtable) {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                newHashTable[hash(entry.getKey())].put(entry.getKey(), entry.getValue());
            }
        }
        hashtable = newHashTable;
    }

    public static void main(String[] args) {
        Map<Integer, Object> map = new TreeMap<>();
        System.out.println(map.put(1, 1));
        System.out.println(map.put(1, 2));
        System.out.println(map.put(3, null));
        System.out.println(map);
    }
}
