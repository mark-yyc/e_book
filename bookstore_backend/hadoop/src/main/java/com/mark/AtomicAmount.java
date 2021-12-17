package com.mark;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicAmount {
    private AtomicInteger atomicInteger=new AtomicInteger(0);
    public void increment(){
        atomicInteger.incrementAndGet();
    }
    public int getValue(){
        return atomicInteger.get();
    }
}
