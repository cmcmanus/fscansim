import java.util.*;

public class Event implements Comparable<Event> {
	
	double time;
	Types type;
	
	public Event(double t, Types ty){
		time = t;
		type = ty;
	}
	
	public void function(PriorityQueue<Event> schedule, State state, double time){
		switch (type){
		case BIRTH:{
			int track = (int) (Math.random() * Simulator.N + 1);
			Job j = new Job(time, track);
			state.addJob(j);

			if (state.highqueue.size() == 0){
				PriorityQueue<Job> queue = state.highqueue;
				state.highqueue = state.lowqueue;
				state.lowqueue = queue;
				double deathtime = Simulator.a + Simulator.b * Math.abs(state.track - j.track) + time;
				Event deathevent = new Event(deathtime, Types.DEATH);
				schedule.add(deathevent);
			}
			double birthtime = Simulator.expon(Simulator.lambda) + time;
			Event birthevent = new Event(birthtime, Types.BIRTH);
			schedule.add(birthevent);
			return;
		}
		case DEATH:{
			Job deadjob = state.removeJob();
			Monitor.monitor(deadjob, time, state.track);
			state.track = deadjob.track;
			state.resetJobs();
			if (state.highqueue.size() == 0){
				PriorityQueue<Job> queue = state.highqueue;
				state.highqueue = state.lowqueue;
				state.lowqueue = queue;
			}
			
			if (state.highqueue.size() != 0){
				Job j = state.peekJob();
				double deathtime = Simulator.a + Simulator.b * Math.abs(state.track - j.track) + time;
				Event deathevent = new Event(deathtime, Types.DEATH);
				schedule.add(deathevent);
			}
			return;
		}
		}
	}
	
	public String toString(){
		return time + " " + type;
	}

	public int compareTo(Event arg0) {
		
		return ((Double) time).compareTo(arg0.time);
	}
}
