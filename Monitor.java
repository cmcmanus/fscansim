import java.io.*;
import java.util.*;

public class Monitor {

	public static PrintWriter out;
	public static int samples = 0;
	public static LinkedList<Double> response = new LinkedList<Double>();
	public static int headmove = 0;
	
	public static void monitor(Job j, double deathtime, int track){
		samples++;
		response.add(deathtime - j.birth);
		headmove += Math.abs(track - j.track);
		out.println(deathtime + "\t" + (deathtime - j.birth) + "\t" + Math.abs(track - j.track) + "\t" + headmove);
	}
	
	public static void finish(){
		
		double sum = 0;
		for (Double d : response){
			sum += d;
		}
		double mean = sum / samples;
		out.println("Average Response Time: " + mean);
		System.out.println("Average Response Time: " + mean);
		
		sum = 0;
		for (Double d : response){
			sum += Math.abs(d - mean);
		}
		double dev = sum / samples;
		out.println("Response Time Deviation: " + dev);
		System.out.println("Response Time Deviation: " + dev);
		
		out.println("Total Head Movement: " + headmove);
		System.out.println("Total Head Movement: " + headmove);
		
		out.close();
		System.out.println("Simulation Finished");
	}
}
