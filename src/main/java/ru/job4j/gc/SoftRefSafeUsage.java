package ru.job4j.gc;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class SoftRefSafeUsage {
    public static void main(String[] args) {
        Object data = new Object();
        SoftReference<Object> softRef = new SoftReference<>(data);
        WeakReference<Object> weakRef = new WeakReference<>(data);
        data = null;
        Object softData = softRef.get();
        if (softData != null) {
            System.out.println("SoftReference: " + softData.toString());
        } else {
            softData = new Object();
            softRef = new SoftReference<>(softData);
        }
        Object weakData = weakRef.get();
        if (weakData != null) {
            System.out.println("WeakReference: " + weakData.toString());
        } else {
            weakData = new Object();
            weakRef = new WeakReference<>(weakData);
        }
    }
}
