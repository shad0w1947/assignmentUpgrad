package assignmentUpgrad;
import java.util.*;

 class solution {

    static	Scanner  s=new Scanner(System.in);
	public static void main(String[] args) {
		PriorityQueue pq = new PriorityQueue();
		
		int n=s.nextInt();
		while(n>0) {
		   
	        String ss=s.next();
	        if(ss.equals("ENTER")) {
	        	Data temp=new Data();
	        	temp.name=s.next();
	        	temp.cgpa=s.nextDouble();
	        	temp.code=s.nextDouble();
	        	pq.insert(temp);
	        }
	        else if(ss.equals("SERVED")) {
	        	if(pq.getSize()>0) {
	        		pq.removeMax();
	        	}
	        }
			n--;
		}
		if(pq.getSize()==0) {
			System.out.println("EMPTY");
		}
		while(pq.getSize()>0) {
			System.out.println(pq.removeMax().name);
			
		}
			
	}
}
class PriorityQueue {
	private ArrayList<Data> heap;//=new ArrayList<>();

	public PriorityQueue() {
		heap = new ArrayList<>();
	}

	boolean isEmpty(){
		return heap.size() == 0;
	}

	int getSize(){
		return heap.size();
	}

	Data getMax()
     // throws PriorityQueueException
    {
		if(isEmpty()){
			// Throw an exception
          return null;
			//throw new PriorityQueueException();
		}
		return heap.get(0);
	}

	void insert(Data element){
		heap.add(element);
		int childIndex = heap.size() - 1;
		int parentIndex = (childIndex - 1) / 2;

		while(childIndex > 0){
			if((heap.get(childIndex).cgpa > heap.get(parentIndex).cgpa)||
					(heap.get(childIndex).cgpa == heap.get(parentIndex).cgpa&&heap.get(childIndex).name.compareTo(heap.get(parentIndex).name)<0)||
					(heap.get(childIndex).cgpa == heap.get(parentIndex).cgpa&&heap.get(childIndex).name.equals(heap.get(parentIndex).name)&&(heap.get(childIndex).code > heap.get(parentIndex).code) )){
				Data temp = heap.get(childIndex);
				heap.set(childIndex, heap.get(parentIndex));
				heap.set(parentIndex, temp);
				childIndex = parentIndex;
				parentIndex = (childIndex - 1) / 2;
			}else {
				return;
			}
		}
	}


	Data removeMax() 
      //throws PriorityQueueException
    {
		if(isEmpty()){
			// Throw an exception
          return null;
			//throw new PriorityQueueException();
		}
		Data temp = heap.get(0);
		heap.set(0, heap.get(heap.size() - 1));
		heap.remove(heap.size() - 1);
		int index = 0;
		int minIndex = index;
		int leftChildIndex = 1;
		int rightChildIndex = 2;

		while(leftChildIndex < heap.size()){

			if((heap.get(leftChildIndex).cgpa > heap.get(minIndex).cgpa)||
					(heap.get(leftChildIndex).cgpa == heap.get(minIndex).cgpa&&heap.get(leftChildIndex).name.compareTo(heap.get(minIndex).name)>0)||
					(heap.get(leftChildIndex).cgpa == heap.get(minIndex).cgpa&&heap.get(leftChildIndex).name.equals(heap.get(minIndex).name)&&(heap.get(leftChildIndex).code > heap.get(minIndex).code) )){
				minIndex = leftChildIndex;
			}
			if(rightChildIndex < heap.size() && (heap.get(rightChildIndex).cgpa > heap.get(minIndex).cgpa||
			(heap.get(rightChildIndex).cgpa == heap.get(minIndex).cgpa&&heap.get(rightChildIndex).name.compareTo(heap.get(minIndex).name)>0)||
			(heap.get(rightChildIndex).cgpa == heap.get(minIndex).cgpa&&heap.get(rightChildIndex).name.equals(heap.get(minIndex).name)&&(heap.get(rightChildIndex).code > heap.get(minIndex).code)) )){
				minIndex = rightChildIndex;
			}
			if(minIndex == index){
				break;
			}else{
				Data temp1 = heap.get(index);
				heap.set(index, heap.get(minIndex));
				heap.set(minIndex, temp1);
				index = minIndex;
				leftChildIndex = 2 * index + 1;
				rightChildIndex = 2 * index + 2;
			}
		}
		return temp;

	}
 }
class Data{
	String name;
	double cgpa;
	double code;
}

