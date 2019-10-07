package eili.ds;

public class HashMap<K,V> {

    public static final int DEFAULT_CAPACITY = 128;
    public static final double LOAD_FACTOR   = 0.75d;

    private int size;
    private int capa;
    private Entry<K,V>[] entries;

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    public HashMap(int capacity) {
        this.size = 0;
        this.capa = capacity;
        this.entries = new Entry[capa];
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capa;
    }

    public void put(K key, V val) {
        ensureCapacity(1);
        safePut(this.entries, key, val);
        size++;
    }


    public V get(K key) {
        int bucket = Math.abs(key.hashCode()) % entries.length;
        Entry<K,V> runner = entries[bucket];
        while (runner != null && !runner.key.equals(key)) runner = runner.next;
        return runner == null ? null : runner.val;
    }


    public V remove(K key) {
        int bucket = Math.abs(key.hashCode()) % entries.length;
        Entry<K,V> prev   = null;
        Entry<K,V> runner = entries[bucket];
        while (runner != null) {
            if (runner.key.equals(key)) {
                if (prev == null) {
                    entries[bucket] = runner.next;
                } else {
                    prev.next = runner.next;
                }
                size--;
                return runner.val;
            } else {
                prev = runner;
                runner = runner.next;
            }
        }

        return null;
    }


    /**
     * Used by put and ensureCapacity to add an item to an Entires array.
     * Note entries may be the HashMap entries or it may be a new array
     * used for expansion.  As such, this method:
     *
     * 1) Does not check for enough capacity
     * 2) Expects the caller to udpate HashMap "size" if necessary
     *
     * @param entries
     * @param key
     * @param val
     */
    private void safePut(Entry<K,V>[] entries, K key, V val) {
        int bucket = Math.abs(key.hashCode()) % entries.length;
        Entry<K,V> newEntry = new Entry<>(key,val);
        if (entries[bucket] == null) {
            entries[bucket] = newEntry;
        } else {
            newEntry.next   = entries[bucket];
            entries[bucket] = newEntry;
        }
    }


    private void ensureCapacity(int requested) {
        int available = (int)(capa * LOAD_FACTOR - size);
        if (requested > available) {
            int needed = requested - available;
            int newCap = (size + needed) * 2;
            Entry<K,V>[] newEntries = new Entry[newCap];

            // copy over old entries into new array (size is left unchanged)
            // remember an entry may be null if no key has been hashed to that bucket!
            for (Entry<K,V> existing : entries) {
                while (existing != null) {
                    safePut(newEntries, existing.key, existing.val);
                    existing = existing.next;
                }
            }

            // and now point entries to newly populated array
            this.capa = newCap;
            this.entries = newEntries;
        }
    }


    class Entry<K,V> {
        K key;
        V val;
        Entry<K,V> next;

        Entry(K key, V val) {
            this.key  = key;
            this.val  = val;
            this.next = null;
        }
    }

}
