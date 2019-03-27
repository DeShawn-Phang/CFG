package practice.ch06.sec01;

//<>指定类型参数K和V，在成员定义中，被用作实例变量的类型...
public class Entry<K, V> {
    private K key;
    private V value;

    public Entry(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
