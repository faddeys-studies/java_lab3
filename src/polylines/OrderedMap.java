package polylines;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;


public class OrderedMap<K, V> implements Map<K, V> {

    private SortedMap<Integer, Entry> orderedEntries = new TreeMap<>();
    private Map<K, Integer> keyIndices = new HashMap<>();
    private int insertCounter = 0;

    private Set<K> _keySet = null;
    private Collection<V> _values = null;
    private Set<Map.Entry<K, V>> _entrySet = null;

    @Override
    public int size() { return orderedEntries.size(); }

    @Override
    public boolean isEmpty() { return orderedEntries.isEmpty(); }

    @Override
    public boolean containsKey(Object key) { return keyIndices.containsKey(key); }

    @Override
    public boolean containsValue(Object value) {
        for(Entry e : orderedEntries.values()) {
            if (e.value == value || (value != null && value.equals(e.value))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        Integer idx = keyIndices.get(key);
        if (idx != null) {
            return orderedEntries.get(idx).value;
        } else {
            return null;
        }
    }

    public V put(K key, V value) {
        Integer prevIdx = keyIndices.putIfAbsent(key, insertCounter);
        if (prevIdx == null) {
            // new key - save new entry under next index and advance the index
            orderedEntries.put(insertCounter, new Entry(key, value));
            insertCounter++;
            return null;
        } else {
            // update existing entry
            Entry e = orderedEntries.get(prevIdx);
            return e.setValue(value);
        }
    }

    @Override
    public V remove(Object key) {
        Integer idx = keyIndices.remove(key);
        if (idx != null) {
            // found entry - remove it
            return orderedEntries.remove(idx).value;
        } else {
            // no entries found - do nothing
            return null;
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        // this implementation is very inefficient
        // but there is no other simple solution
        // because we need to know the index of each entry
        for(Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        orderedEntries.clear();
        keyIndices.clear();
        insertCounter = 0;
    }

    @Override
    public Set<K> keySet() {
        if (_keySet == null) {
            _keySet = new KeySet();
        }
        return _keySet;
    }

    @Override
    public Collection<V> values() {
        if (_values == null) {
            _values = new Values();
        }
        return _values;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        if (_entrySet == null) {
            _entrySet = new EntrySet();
        }
        return _entrySet;
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(action);
        for(Entry e : orderedEntries.values()) {
            action.accept(e.key, e.value);
        }
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Objects.requireNonNull(function);
        for (Entry e : orderedEntries.values()) {
            e.value = function.apply(e.key, e.value);
        }
    }

    private class Entry implements Map.Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }
        public V setValue(V value) {
            V prev = this.value;
            this.value = value;
            return prev;
        }
    }

    final class KeySet extends AbstractSet<K> {
        public final int size()                 { return OrderedMap.this.size(); }
        public final void clear()               { OrderedMap.this.clear(); }
        public final Iterator<K> iterator()     { return new KeyIterator(); }
        public final boolean contains(Object o) { return containsKey(o); }
        public final boolean remove(Object key) { return OrderedMap.this.remove(key) != null; }
    }

    final class Values extends AbstractSet<V> {
        public final int size()                 { return OrderedMap.this.size(); }
        public final void clear()               { OrderedMap.this.clear(); }
        public final Iterator<V> iterator()     { return new ValueIterator(); }
        public final boolean contains(Object o) { return containsKey(o); }
        public final boolean remove(Object key) { return OrderedMap.this.remove(key) != null; }
    }

    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        public final int size()                 { return OrderedMap.this.size(); }
        public final void clear()               { OrderedMap.this.clear(); }
        public final Iterator<Map.Entry<K, V>> iterator() { return new EntryIterator(); }
        public final boolean contains(Object o) { return containsKey(o); }
        public final boolean remove(Object key) { return OrderedMap.this.remove(key) != null; }
    }

    class OMIterator {

        private Iterator<Entry> delegate;
        private Entry current = null;

        OMIterator() {
            delegate = orderedEntries.values().iterator();
        }

        public boolean hasNext() { return delegate.hasNext();}
        public void remove() {
            if (current == null) {
                throw new IllegalStateException();
            }
            keyIndices.remove(current.key);
            delegate.remove();
            current = null;
        }

        Entry nextEntry() {
            current = delegate.next();
            return current;
        }

    }

    final class KeyIterator extends OMIterator implements Iterator<K> {
        public K next() { return nextEntry().key; }
    }

    final class ValueIterator extends OMIterator implements Iterator<V> {
        public V next() { return nextEntry().value; }
    }

    final class EntryIterator extends OMIterator implements Iterator<Map.Entry<K, V>> {
        public Map.Entry<K, V> next() { return nextEntry(); }
    }

}
