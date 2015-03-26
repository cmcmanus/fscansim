
public class Job {

	public double birth;
	public int track;
	
	public Job(double b, int t){
		birth = b;
		track = t;
	}
	
	public String toString(){
		return birth + " " + track;
	}
}
