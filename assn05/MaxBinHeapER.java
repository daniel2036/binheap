package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap = new ArrayList<>();

    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MaxBinHeapER() {
        _heap = new ArrayList<>();
    }

    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO: overloaded constructor
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries ) {
        int size = initialEntries.length;
        int start = (size / 2) - 1;
        for (int i = start; i >= 0; i--) {
            heapBuilder(initialEntries, size, i);
        }
        for(int i =0; i<initialEntries.length; i++){
            _heap.add(initialEntries[i]);
        }
    }
    private void heapBuilder(Prioritized<V, P>[] list, int size, int start){
        int largest = start;
        int left = (start*2)+1;
        int right = (start*2)+2;
        if(left < size && list[left].compareTo(list[largest])>0){
            largest = left;
        }
        if(right < size && list[right].compareTo(list[largest])>0){
            largest = right;
        }
        if(largest!=start){
            Prioritized temp = list[start];
            list[start] = list[largest];
            list[largest] = temp;
            heapBuilder(list, size, largest);
        }
    }
    @Override
    public int size() {
        return _heap.size();
    }

    // TODO: enqueue
    @Override
    public void enqueue(V value, P priority) {
        Patient patient = new Patient<>(value, priority);
        _heap.add(patient);
        if(_heap.size()>1){
            int index = (_heap.size()/2)-1;
            int current = _heap.size()-1;
            while(index>=0){
                if(priority.compareTo(_heap.get(index).getPriority())>0){
                    _heap.set(current,_heap.get(index));
                    _heap.set(index,patient);
                    current = index;
                    index = (index-1)/2;
                }
                else{
                    break;
                }
            }
        }
    }

    // TODO: enqueue
    public void enqueue(V value) {
        Patient patient = new Patient<>(value);
        enqueue(value, (P) patient.getPriority());
    }

    // TODO: dequeue
    @Override
    public V dequeue() {
        if (_heap.isEmpty()) {
            return null;
        }
        else if (_heap.size()==1) {
            return _heap.remove(0).getValue();
        }
//        if(_heap.size()==1){
//            return _heap.remove(0).getValue();
//        }
        V value = getMax();
        int last = _heap.size()-1;
        _heap.set(0,_heap.get(last));
        _heap.remove(last);
        bubbleDown(0);
        return value;
    }
    private void bubbleDown(int start){
        int largest = start;
        int left = (start*2)+1;
        int right = (start*2)+2;
        if(left < _heap.size() && _heap.get(left).getPriority().compareTo(_heap.get(largest).getPriority())>0){
            largest = left;
        }
        if(right < _heap.size() && _heap.get(right).getPriority().compareTo(_heap.get(largest).getPriority())>0){
            largest = right;
        }
        if(largest!=start){
            Prioritized temp = _heap.get(start);
            _heap.set(start, _heap.get(largest));
            _heap.set(largest, temp);
            bubbleDown(largest);
        }
    }

    // TODO: getMax
    @Override
    public V getMax() {
        if(_heap.isEmpty()){
            return null;
        }
        return _heap.get(0).getValue();
    }
    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }
}
