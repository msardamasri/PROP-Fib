package main.subgrup14_1.mastermind.utils;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class Pair<L,R> {
    private L l;
    private R r;
    public Pair(L l, R r){
        this.l = l;
        this.r = r;
    }
    public L getL(){ return this.l; }
    public R getR(){ return this.r; }
    public void setL(L l){ this.l = l; }
    public void setR(R r){ this.r = r; }
}