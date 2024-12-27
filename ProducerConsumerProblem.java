import java.util.*;

class ProducerConsumerProblem {
    public static void main(String[] args) {
        SharedBuffer sharedBuffer=new SharedBuffer(3);
        System.out.println("Current Thread Name: "+Thread.currentThread().getName());
        Thread producerThread=new Thread(new Producer(sharedBuffer));
        producerThread.setName("Producer Thread");
        Thread consumerThread=new Thread(new Consumer(sharedBuffer));
        consumerThread.setName("Consumer Thread");
        producerThread.start();
        consumerThread.start();
    }
}
class Producer implements Runnable{
    SharedBuffer sharedBuffer;

    public Producer(SharedBuffer sharedBuffer){
        this.sharedBuffer=sharedBuffer;
    }
    
    @Override
    public void run(){
        for(int i=0;i<7;i++){
            sharedBuffer.produce(i);
        }
    }
}
class Consumer implements Runnable{
    SharedBuffer sharedBuffer;

    public Consumer(SharedBuffer sharedBuffer){
        this.sharedBuffer=sharedBuffer;
    }
    
    @Override
    public void run(){
        for(int i=0;i<7;i++){
            sharedBuffer.consume();
        }
    }
}
class SharedBuffer{
    private int bufferSize;
    private Queue<Integer> sharedBuffer;
    public SharedBuffer(int size){
        this.bufferSize=size;
        this.sharedBuffer=new LinkedList<>();
    }
    
    public synchronized void produce(int item){
        while(sharedBuffer.size()==bufferSize){
            try{
                System.out.println("Size of Buffer is Full: Wait for consumer to consume");
                wait();
            } catch(Exception e){
                //exception handling
            }
        }
        sharedBuffer.add(item);
        System.out.println("Item "+item+" added to buffer");
        notify();
    }
    
    public synchronized int consume(){
        while(sharedBuffer.size()==0){
            try{
                System.out.println("Size of Buffer is Zero: Wait for producer to produce");
                wait();
            } catch(Exception e){
                //exception handling
            }
        }
        int item=sharedBuffer.remove();
        System.out.println("Item "+item+" is consumed from buffer");
        notify();
        return item;
    }
}
