/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericarrayqueuelabweek5;

import java.util.NoSuchElementException;

/**
 *
 * @author UgurKaradag
 *
 */

public class GenericArrayQueue {
    
    private int[] q; // array of elements
    int N = 0; // number of elements on queue private
    int first; // index of first element of queue
    int last; // index of next available slot

    public GenericArrayQueue(int size) {
        q = new int[size];
        first = 0;
        last = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }
    // Enqueue, put a new data to the last
    public void enqueue(int data) {
        // double size of array if necessary and recopy to front of array
        if (N == q.length) {
            resize(2 * q.length);   // double size of array if necessary
        }
        q[last++] = data; // add data

        if (last == q.length) {
            last = 0;          // wrap-around
        }
        N++;
    }

    // Dequeue remove the first data
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        int data = q[first];
        q[first] = 0;
        N--;
        first++;
        if (first == q.length || q[first] == 0) {
            first = 0;           // wrap-around
        }        // shrink size of array if necessary
        if (N > 0 && N == q.length / 4) {
            resize(q.length / 2);
        }
        return data;
    }

    public void resize(int newSize) {
        if (newSize <= N) {
            throw new IllegalArgumentException("Invalid size: " + newSize);
        }
        System.out.println("newSize= " + newSize);
        @SuppressWarnings("unchecked")
        int[] temp = new int[newSize];
        for (int i = 0; i < N; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp.clone();
        first = 0;
        last = N;
    }

    public String printQueueOrder() {
        String s = "";
        for (int i = 0; i < q.length; i++) {
            s += (q[i] + " ");
        }
        return s;
    }
    
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < q.length; i++) {
            s += (q[(first + i) % q.length] + " ");
        }
        return s;
    }    

    public int getMax() {
        int max = q[0];
        for(int i = 0; i < q.length; i++) {
            if(q[i] > max) {
                max = q[i];
            }
        }
        return max;
    }
    public int peek() {
        return q[first];
    }



}
