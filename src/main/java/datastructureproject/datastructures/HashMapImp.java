package datastructureproject.datastructures;

import datastructureproject.datastructures.MathImp;

/**
 * Hashmap implementation using linked lists
 * @param <K>
 * @param <V>
 */
public class HashMapImp<K, V> {

    private final int DEFAULT_CAPACITY = 32;
    private Entry<K, V>[] map;
    private int size;

    @SuppressWarnings("unchecked")
    public HashMapImp() {
        this.size = 0;
        this.map = new Entry[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public HashMapImp(int capacity) {
        this.size = 0;
        this.map = new Entry[capacity];
    }


    public void put(K key, V value) {
        if (size >= 0.75 * this.map.length) {
            this.extend();
        }

        Entry<K, V> newEntry = new Entry<>(key, value, null);
        int index = MathImp.abs(key.hashCode() % this.map.length);
        Entry<K, V> currentEntry = this.map[index];

        if (currentEntry == null) {
            this.map[index] = newEntry;
            this.size++;
        } else {
            while (true) {
                if (currentEntry.key.equals(key)) {
                    currentEntry.value = value;
                    return;
                }
                if(currentEntry.next == null) {
                    currentEntry.next = newEntry;
                    this.size++;
                    return;
                } else {
                    currentEntry = currentEntry.next;
                }
            }
        }
    }


    public V get(K key) {
        Entry<K, V> entry = this.map[MathImp.abs(key.hashCode()) % this.map.length];
        while (entry != null) {
            if (key == entry.key) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void extend() {
        this.size = 0;
        int newCapacity = this.map.length * 2;
        Entry<K, V>[] oldEntries = this.map;
        this.map = new Entry[newCapacity];
        for (Entry<K, V> entry : oldEntries) {
            while (entry != null) {
                this.put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }



}
