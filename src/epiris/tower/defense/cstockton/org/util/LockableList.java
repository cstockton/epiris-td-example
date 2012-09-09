package epiris.tower.defense.cstockton.org.util;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: francoiscassistat
 * Date: 12 juin 2010
 * Time: 18:20:18
 *
 *
 * Lockable list, made to debug ConcurrentModificationException on Lists.
 * The lock can be switched on/off with setLocked(boolean).
 * When locked, all write access to the list or iterators gets ConcurrentModificationException.
 * Simple usage case :
 *
 * list.setLocked(true);
 *
 * for (Object o : list.iterator()) // now this won't get ConcurrentModificationException, the other instruction that cause this will thrown the exception
 * { ... }
 *
 * list.setLocked(false);
 */
public class LockableList<E> implements List<E> {
    protected class LockableListIterator implements Iterator<E> {
        protected Iterator<E> iterator;

        public LockableListIterator(Iterator<E> iterator) {
            this.iterator = iterator;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public E next() {
            return iterator.next();
        }

        @Override
        public void remove() {
            checkLock();
            iterator.remove();
        }
    }

    protected class LockableListListIterator implements ListIterator<E> {
        protected ListIterator<E> listIterator;

        public LockableListListIterator(ListIterator<E> listIterator) {
            this.listIterator = listIterator;
        }

        @Override
        public boolean hasNext() {
            return listIterator.hasNext();
        }

        @Override
        public E next() {
            return listIterator.next();
        }

        @Override
        public boolean hasPrevious() {
            return listIterator.hasPrevious();
        }

        @Override
        public E previous() {
            return listIterator.previous();
        }

        @Override
        public int nextIndex() {
            return listIterator.nextIndex();
        }

        @Override
        public int previousIndex() {
            return listIterator.previousIndex();
        }

        @Override
        public void remove() {
            checkLock();
            listIterator.remove();
        }

        @Override
        public void set(E e) {
            checkLock();
            listIterator.set(e);
        }

        @Override
        public void add(E e) {
            checkLock();
            listIterator.add(e);
        }
    }

    protected class LockableListSubList implements List<E>
    {
        protected List<E> list;

        public LockableListSubList(List<E> list) {
            this.list = list;
        }

        @Override
        public int size() {
            return list.size();
        }

        @Override
        public boolean isEmpty() {
            return list.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return list.contains(o);
        }

        @Override
        public Iterator<E> iterator() {
            return new LockableListIterator(list.iterator());
        }

        @Override
        public Object[] toArray() {
            return list.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return list.toArray(a);
        }

        @Override
        public boolean add(E e) {
            checkLock();
            return list.add(e);
        }

        @Override
        public boolean remove(Object o) {
            checkLock();
            return list.remove(o);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return list.containsAll(c);
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            checkLock();
            return list.addAll(c);
        }

        @Override
        public boolean addAll(int index, Collection<? extends E> c) {
            checkLock();
            return list.addAll(index, c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            checkLock();
            return list.removeAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            checkLock();
            return list.retainAll(c);
        }

        @Override
        public void clear() {
            checkLock();
            list.clear();
        }

        @Override
        public boolean equals(Object o) {
            return list.equals(o);
        }

        @Override
        public int hashCode() {
            return list.hashCode();
        }

        @Override
        public E get(int index) {
            return list.get(index);
        }

        @Override
        public E set(int index, E element) {
            checkLock();
            return list.set(index, element);
        }

        @Override
        public void add(int index, E element) {
            checkLock();
            list.add(index, element);
        }

        @Override
        public E remove(int index) {
            checkLock();
            return list.remove(index);
        }

        @Override
        public int indexOf(Object o) {
            return list.indexOf(o);
        }

        @Override
        public int lastIndexOf(Object o) {
            return list.lastIndexOf(o);
        }

        @Override
        public ListIterator<E> listIterator() {
            return new LockableListListIterator(list.listIterator());
        }

        @Override
        public ListIterator<E> listIterator(int index) {
            return new LockableListListIterator(list.listIterator(index));
        }

        @Override
        public List<E> subList(int fromIndex, int toIndex) {
            return new LockableListSubList(list.subList(fromIndex, toIndex));
        }
    }

    protected List<E> list;
    protected boolean locked;

    public LockableList(List<E> list) {
        this.list = list;
        locked = false;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    protected void checkLock() {
        if (locked)
            throw new ConcurrentModificationException("Locked");
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new LockableListIterator(list.iterator());
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(E e) {
        checkLock();
        return list.add(e);
    }

    @Override
    public boolean remove(Object o) {
        checkLock();
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        checkLock();
        return list.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkLock();
        return list.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        checkLock();
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkLock();
        return list.retainAll(c);
    }

    @Override
    public void clear() {
        checkLock();
        list.clear();
    }

    @Override
    public boolean equals(Object o) {
        return list.equals(o);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        checkLock();
        return list.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        checkLock();
        list.add(index, element);
    }

    @Override
    public E remove(int index) {
        checkLock();
        return list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return new LockableListListIterator(list.listIterator());
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new LockableListListIterator(list.listIterator(index));
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return new LockableListSubList(list.subList(fromIndex, toIndex));
    }
}