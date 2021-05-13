package datastructureproject.datastructures;

public class ArrayListImp<E> {

    private final int DEFAULT_SIZE = 20;
    private int size;
    private Object[] array;

    public ArrayListImp() {
        this.array = new Object[this.DEFAULT_SIZE];
        this.size = 0;
    }

    public ArrayListImp(int size) {
        this.array = new Object[size];
        this.size = 0;
    }

    private void extend() {
        Object[] newArray = new Object[this.array.length * 2];
        for (int i = 0; i < this.array.length; i++) {
            newArray[i] = this.array[i];
        }
        this.array = newArray;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void add(E object) {
        if (this.size == this.array.length) {
            this.extend();
        }
        this.array[this.size++] = object;
    }

    public boolean contains(Object object) {
        for (int i = 0; i < this.size; i++) {
            if (this.array[i].equals(object)) {
                return true;
            }
        }
        return false;
    }

    public void remove(int index) {
        if(index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(String.format("Index %s is out of bounds", index));
        }
        if(this.array[index] == null) {
            throw new NullPointerException(String.format("Index %s doesn't contain object", index));
        }
        this.array[index] = null;

        // Move all elements after the removal index one index down to fill the empty gap
        for (int i = index+1; i <= this.size; i++) {
            this.array[i-1] = this.array[i];
        }
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if(index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(String.format("Index %s is out of bounds", index));
        }
        return (E) this.array[index];
    }

    public void clear() {
        this.size = 0;
        this.array = new Object[DEFAULT_SIZE];
    }

}
