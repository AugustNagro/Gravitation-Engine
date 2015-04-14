package gravitation1;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import libraries.*;

public class GravitationEngine {

	private double time = 0;
	//need diamond type
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private PrintStream ps;
	
	public GravitationEngine(PrintStream ps) {
		this.ps = ps;
	}
	
	public void run(double timeIncrement, int nIncrements) {
		run(timeIncrement, nIncrements, true);
	}
	
	public void run(double timeIncrement, int nIncrements, boolean print) {
		System.out.println(header());
		if (print) {
			ps.println(toString());
		}
		for (int i = 0; i < nIncrements; ++i) {
			increment(timeIncrement);
			if (print) {
				ps.println(toString());
			}
		
		}
	}
	
	public void addProjectile(Projectile projectile) {
		if (projectiles.contains(projectile)) {
			throw new RuntimeException("Attempted to add Projectile that already exists!");
		} else {
			projectiles.add(projectile);
		}
	}
	
	public String toString() {
		String response = new String();
		for (Projectile projectile : projectiles) {
			response += "\t" + time + "\t" + projectile;
		}
		return response;
	}
	
	public String header() {
		String response = new String();
		for (Projectile projectile : projectiles) {
			response += projectile.getName();
			response += "\ttime\tx\ty\tz\tMag\tvx\tvy\tvz\tSpeed\tax\tay\taz\t|a|";
		}
		return response;
	}
	
	private void increment (double timeIncrement) {
		time += timeIncrement;
		for (Projectile projectile : projectiles) {
			projectile.update(this, timeIncrement);
		}
	}
	
	public double getTime() {
		return time;
	}
	
	public List<PointProjectile> getProjectiles() {
		List<PointProjectile> response = new ArrayList<PointProjectile>();
		for (Projectile proj : projectiles) {
			proj.addToList(response);
		}
		return response;
	}
	
}
