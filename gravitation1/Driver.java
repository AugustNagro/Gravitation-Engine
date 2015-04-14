package gravitation1;

import libraries.ExtendedProjectile;
import libraries.PointProjectile;
import libraries.Vector3;

/**
 * Created by August Nagro on 1/27/2015.
 */
public class Driver {
	//constants
    private static double g = -9.8;
    public static final double gravConstant = 6.673E-11;
    private static double timeIncrement = 18000;
    //precision used for breaking up planets with volume (ExtendedProjectiles) into discrete sections.
    private static int nIncrements = 210;
    private static double blocksPerSide = 200;
    
    //planet constants can go here (feel free to add/change)
    private static double earthMass = 5.9736E24;
    private static double earthRadius = 6371; //000;
    
    private static double moonMass = 7.3477E22;
    private static double moonRadius = 1737; //000;
    
    private static double earthMoonDistance = 384400E3;
    private static double moonOrbitalVelocity = 1023;
    
    /**
     * Make and run your own simulation, or try out an example provided.
     * 
     * PointProjectiles represent point-like planets, and ExtendedProjectiles represent planets
     * with volume, having a discrete number of points, each with its own mass.  
     * @param args
     */
    public static void main(String[] args){
        example4();
    }
    
    /**
     * Example 1: The volume-less PointProjectile Moon orbiting the PointProjectile Earth
     */
    public static void example1(){
        GravitationEngine engine = new GravitationEngine(System.out);
        PointProjectile earth = new PointProjectile(new Vector3(), new Vector3(), earthMass, "Earth");
        PointProjectile moon = new PointProjectile(new Vector3(earthMoonDistance,0,0), new Vector3(0,moonOrbitalVelocity,0), moonMass, "Moon");
        engine.addProjectile(earth);
        engine.addProjectile(moon);
        engine.run(timeIncrement, nIncrements);
    }
    
    /**
     * Example 2: The PointProjectile Moon orbiting the cube-shaped, ExtendedProjectile Earth
     */
    public static void example2(){
    	double blockHeight = earthRadius / blocksPerSide;
        double blockMass = earthMass / Math.pow(blocksPerSide, 3);
    	
    	GravitationEngine engine = new GravitationEngine(System.out);
    	PointProjectile moon = new PointProjectile(new Vector3(earthMoonDistance,0,0), new Vector3(0,moonOrbitalVelocity,0), moonMass, "Moon");
    	ExtendedProjectile earth = new ExtendedProjectile("Earth");
    	engine.addProjectile(moon);
    	engine.addProjectile(earth);
    	
    	earth.populate(earthRadius/2, blockHeight, blockMass);
    	engine.run(timeIncrement, nIncrements);
    }
    
    /**
     * Example 2A: The same as example 2, except with a much larger radius of Earth. 
     * Because Earth is a cube-shapped ExtendedProjectile with a large radius, the moon's orbit is clearly thrown off.
     */
    public static void example2A(){
    	double sidelength = 2 * Math.cos(Math.PI/4) * earthMoonDistance;
    	double blockHeight = sidelength / blocksPerSide;
        double blockMass = earthMass / Math.pow(blocksPerSide, 3);
    	
    	GravitationEngine engine = new GravitationEngine(System.out);
    	PointProjectile moon = new PointProjectile(new Vector3(earthMoonDistance,0,0), new Vector3(0,moonOrbitalVelocity,0), moonMass, "Moon");
    	ExtendedProjectile earth = new ExtendedProjectile("Earth");
    	engine.addProjectile(moon);
    	engine.addProjectile(earth);
    	
    	earth.populate(sidelength/2, blockHeight, blockMass);    	
    	engine.run(timeIncrement, nIncrements);
    }
    
    /**
     * Example 3: The PointProjectile Moon orbits a spherical, ExtendedProjectile Earth.
     */
    public static void example3(){
    	double blockHeight = earthRadius * 2 / blocksPerSide;
        double blockMass = 1;
    	
    	GravitationEngine engine = new GravitationEngine(System.out);
    	PointProjectile moon = new PointProjectile(new Vector3(earthMoonDistance,0,0), new Vector3(0,moonOrbitalVelocity,0), moonMass, "Moon");
    	ExtendedProjectile earth = new ExtendedProjectile("Earth");
    	engine.addProjectile(moon);
    	engine.addProjectile(earth);
    	
    	earth.populateSphere(earthRadius, blockHeight, blockMass);    	
    	blockMass = earthMass / earth.numBlocks();
    	earth.setBlockMass(blockMass);    	
    	engine.run(timeIncrement, nIncrements);
    }
    
   /**
    * Example 3A: Same as Example 3, but with a radius half the distance from the moon to the earth. 
    * Notably, the moon still orbits successfully. 
    */
    public static void example3A(){
    	double blockHeight = earthMoonDistance / blocksPerSide;
        double blockMass = 1;
        
    	GravitationEngine engine = new GravitationEngine(System.out);
    	PointProjectile moon = new PointProjectile(new Vector3(earthMoonDistance,0,0), new Vector3(0,moonOrbitalVelocity,0), moonMass, "Moon");
    	ExtendedProjectile earth = new ExtendedProjectile("Earth");
    	engine.addProjectile(moon);
    	engine.addProjectile(earth);
    	
    	earth.populateSphere(earthMoonDistance/2, blockHeight, blockMass);
    	blockMass = earthMass / earth.numBlocks();
    	earth.setBlockMass(blockMass);    	
    	
    	engine.run(timeIncrement, nIncrements);
    }
    
    /**
     * Example 3B: Puts the PointProjectile Moon in a hollow, spherical shell of ExtendedProjectile Points. 
     * The Moon remains relatively motionless with high nIncrement and blocksPerSide. 
     */
    public static void example3B(){
    	double blockHeight = moonRadius * 2 / blocksPerSide;
        double blockMass = 1;
            	
    	GravitationEngine engine = new GravitationEngine(System.out);
    	PointProjectile moon = new PointProjectile(new Vector3(0,0,0), new Vector3(0,0,0), moonMass, "Moon");
    	engine.addProjectile(moon);
    	ExtendedProjectile moonShell = new ExtendedProjectile("MoonShell");
    	engine.addProjectile(moonShell);
    	moonShell.populateHollowSphere(moonRadius + blockHeight*2, blockHeight, blockMass);	
    	engine.run(timeIncrement, nIncrements);    	
    }
    /**
     * Example 4: Both the Earth and Moon are now ExtendedProjectiles
     */
    public static void example4(){
    	double earthBlockHeight = earthRadius * 2 / blocksPerSide;
    	double earthBlockMass = earthMass / Math.pow(blocksPerSide, 3);
    	
    	double moonBlockHeight = moonRadius *2 / blocksPerSide;
    	double moonBlockMass = moonMass / Math.pow(blocksPerSide, 3);
    	
    	GravitationEngine engine = new GravitationEngine(System.out);
    	
    	ExtendedProjectile earth = new ExtendedProjectile("Earth");
    	earth.populate(earthRadius, earthBlockHeight, earthBlockMass);
    	engine.addProjectile(earth);
    	
    	ExtendedProjectile moon = new ExtendedProjectile("moon");
    	moon.populate(moonRadius, moonBlockHeight, moonBlockMass);
    	moon.setPosition(new Vector3(earthMoonDistance, 0, 0));
    	engine.addProjectile(moon);
    	
    	engine.run(timeIncrement, nIncrements);
    	
    	
    }
}
