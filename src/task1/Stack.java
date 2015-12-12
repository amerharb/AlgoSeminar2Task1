package task1;

public class Stack<E>
{

    final int DEFAULT_CAPACITY = 255;
    E items[];
    int topItem = -1; //its empty

    public Stack()
    {
        items = (E[]) new Object[DEFAULT_CAPACITY]; //init we will start with 255 (1byte) items
    }

    public boolean isEmpty()
    {
        return topItem == -1;
    }

    public E peek()
    {
        return items[topItem];
    }

    public E pop()
    {
        return items[topItem--];
    }

    public void push(E e)
    {
        if (topItem + 1 == items.length) {
            increaseArray();
        }
        items[++topItem] = e;
    }

    public void clear()
    {
        topItem = -1;
    }

    public int size()
    {
        return topItem + 1;
    }

    private void increaseArray()
    {
        E[] newItems = (E[]) new Object[items.length * 2];
        System.arraycopy(items, 0, newItems, 0, items.length);
        items = newItems;

    }
}
