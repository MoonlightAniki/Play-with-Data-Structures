public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private BST<K, V> bst;

    public BSTMap() {
        bst = new BST<>();
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public void add(K key, V value) {
        bst.add(key, value);
    }

    @Override
    public V remove(K key) {
        return bst.remove(key);
    }

    @Override
    public void set(K key, V value) {
        bst.set(key, value);
    }

    @Override
    public V get(K key) {
        return bst.get(key);
    }

    @Override
    public boolean contains(K key) {
        return bst.contains(key);
    }
}
