// 1. Tạo class Pair<K, V>
class Pair<K, V> {
    private K key;
    private V value;

    // Constructor
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    // Các phương thức Getters và Setters
    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    // Override phương thức toString()
    @Override
    public String toString() {
        return key + " - " + value;
    }
}
