import java.util.*;

public class State {
	
	public int track = 1;
	private boolean up = true;
	public PriorityQueue<Job> lowqueue;
	public PriorityQueue<Job> highqueue;
	
	public State(){
		lowqueue = new PriorityQueue<Job>(10, new Comparator<Job>(){

			public int compare(Job arg0, Job arg1) {
				int t1 = arg0.track;
				int t2 = arg1.track;
				if (up && t1 < track)
					t1 += Simulator.N;
				if (up && t2 < track)
					t2 += Simulator.N;
				if (!up && t1 > track)
					t1 += Simulator.N;
				if (!up && t2 > track)
					t2 += Simulator.N;
				
				
				return Math.abs(t1 - track) - Math.abs(t2 - track);
			}
			
		});
		
		highqueue = new PriorityQueue<Job>(10, new Comparator<Job>(){

			public int compare(Job arg0, Job arg1) {
				int t1 = arg0.track;
				int t2 = arg1.track;
				if (up && t1 < track)
					t1 += Simulator.N;
				if (up && t2 < track)
					t2 += Simulator.N;
				if (!up && t1 > track)
					t1 += Simulator.N;
				if (!up && t2 > track)
					t2 += Simulator.N;
				
				
				return Math.abs(t1 - track) - Math.abs(t2 - track);
			}
			
		});
	}
	
	public void addJob(Job j){
		lowqueue.add(j);
	}
	
	public Job peekJob(){
		return highqueue.peek();
	}
	
	public Job removeJob(){
		Job j = highqueue.remove();
		if (j.track > track)
			up = true;
		else
			up = false;
		return j;
	}
	
	public void resetJobs(){
		PriorityQueue<Job> newqueue = new PriorityQueue<Job>(10, new Comparator<Job>(){

			public int compare(Job arg0, Job arg1) {
				int t1 = arg0.track;
				int t2 = arg1.track;
				if (up && t1 < track)
					t1 += Simulator.N;
				if (up && t2 < track)
					t2 += Simulator.N;
				if (!up && t1 > track)
					t1 += Simulator.N;
				if (!up && t2 > track)
					t2 += Simulator.N;
				
				
				return Math.abs(t1 - track) - Math.abs(t2 - track);
			}
			
		});
		
		newqueue.addAll(lowqueue);
		lowqueue = newqueue;
		
		newqueue = new PriorityQueue<Job>(10, new Comparator<Job>(){

			public int compare(Job arg0, Job arg1) {
				int t1 = arg0.track;
				int t2 = arg1.track;
				if (up && t1 < track)
					t1 += Simulator.N;
				if (up && t2 < track)
					t2 += Simulator.N;
				if (!up && t1 > track)
					t1 += Simulator.N;
				if (!up && t2 > track)
					t2 += Simulator.N;
				
				
				return Math.abs(t1 - track) - Math.abs(t2 - track);
			}
			
		});
		
		newqueue.addAll(highqueue);
		highqueue = newqueue;
	}
	
}
