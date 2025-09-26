package Unit1.Stacks.Bears;

import java.util.Iterator;

public class Den implements Iterable<Bear> {
    private Bear[] bears;
    private int count;
    private double totalWeight;

    private class BearIterator implements Iterator<Bear> {
        int pos = count - 1;

        @Override
        public boolean hasNext() {
            return pos >= 0;
        }

        @Override
        public Bear next() {
            return (!hasNext()) ? null : bears[pos--];
        }
    }

    public Den() {
        bears = new Bear[3];
    }

    /**
     * Push bear onto the den
     * @param b
     * @return
     */
    public Bear push(Bear b) {
        if(count > 0 && bears[count - 1].getWeight() < b.getWeight()) {
            return null;
        }

        totalWeight += b.getWeight();

        if(count < bears.length) {
            bears[count++] = b;
            return b;
        }

        Bear[] temp = new Bear[bears.length * 2];

        for(int i = 0; i < bears.length; i++) {
            temp[i] = bears[i];
        }

        temp[count++] = b;
        bears = temp;
        return b;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public Bear pop() {
        if(count > 0) {
            Bear temp = bears[count - 1];
            bears[--count] = null;

            // if(1.0 * count/bears.length < .1) {
            //     trim();
            // }

            totalWeight -= temp.getWeight();

            return temp;
        }

        return null;
    }

    public Bear peek() {
        return (count == 0) ? null : bears[count - 1];
    }

    @Override
    public Iterator<Bear> iterator() {
        return new BearIterator();
    }

    public Bear[] getBears() {
        return bears;
    }

    public void setBears(Bear[] bears) {
        this.bears = bears;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }
}