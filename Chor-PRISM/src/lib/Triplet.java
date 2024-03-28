package lib;

import org.apache.commons.lang3.tuple.Pair;

public class Triplet<L, M, R>
{
    // private field to hold the triplet
    private L first;
    private M second;
    private R third;


    // Create a triplet from specified typed params
    public Triplet(L left, M mid, R right) {
        first = left;
        second = mid;
        third = right;
    }

    public void setFirst(L first){
        this.first = first;
    }

    public void setSecond(M second){
        this.second = second;
    }

    public void setThird(R third){
        this.third = third;
    }

    // return left element of the triplet
    public L getFirst() {
        return first;
    }

    // return mid-element of the triplet
    public M getSecond() {
        return second;
    }

    // Return right element of the triplet
    public R getThird() {
        return third;
    }

    // override the toString() method to return a string representation of the triplet
    @Override
    public String toString() {
        return "(" + getFirst() + "," + getSecond() + "," + getThird() + ")";
    }
}
