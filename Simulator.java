import java.util.*;
import java.io.*;

public class Simulator {
	
	public static int maxTime = 1000;
	public static double lambda = 10;
	public static int N = 600;
	public static double a = 0.01;
	public static double b = 0.0004;
	
	public static double expon(double lambda){
		double val = Math.random();
		double x = -1 * Math.log(1-val)/lambda;
		return x;
	}
	
	public static PriorityQueue<Event> initializeSchedule() throws FileNotFoundException{
		PriorityQueue<Event> sch = new PriorityQueue<Event>();
		double time = expon(lambda);
		Event firstbirth = new Event(time, Types.BIRTH);
		sch.add(firstbirth);
		
		return sch;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		Monitor.out = new PrintWriter(new File("out.txt"));
		Monitor.out.println("Time\tResponse Time\tHead Move\tHead Move Total");
		State state = new State();
		PriorityQueue<Event> schedule = initializeSchedule();
		double time = 0;
		//Scanner input = new Scanner(System.in);
		while (time <= maxTime){
			//System.out.println(schedule + "\n" + state.highqueue + "\n" + state.lowqueue);
			//input.nextLine();
			Event event = schedule.remove();
			time = event.time;
			event.function(schedule, state, time);
		}
		Monitor.finish();
	}
}
