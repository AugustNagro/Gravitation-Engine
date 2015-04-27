#Gravitation-Engine
A physics engine that uses integration to model the force of gravitation on arbitrarily-shaped planets. This project provides experimental evidence for Newton's [Shell theorem](http://en.wikipedia.org/wiki/Shell_theorem)
##Notable Classes
**PointProjectile:** represent point-like planets

**ExtendedProjectile:** represent planets with volume, each object having a discrete number of points, each with its own mass.  
##Examples
**Example 1:** The volume-less PointProjectile Moon orbiting the PointProjectile Earth

![Ex 1](/images/ex1.png)

![Ex 1-1](/images/ex1-1.png)

**Example 2:** The PointProjectile Moon orbiting the cube-shaped, ExtendedProjectile Earth

![Ex 2](/images/ex2.png)

![Ex 2-1](/images/ex2-1.png)

**Example 2A:** The same as example 2, except with a much larger radius of Earth. Because Earth is a cube-shapped ExtendedProjectile with a large radius, the moon's orbit is thrown off.

![Ex 2A](/images/ex2A.png)

![Ex 2A-1](/images/ex2A-1.png)

**Example 3:** The PointProjectile Moon orbits a spherical, ExtendedProjectile Earth.

![](/images/ex3.png)

![](/images/ex3-1.png)

**Example 3A:** Same as Example 3, but with a radius half the distance from the moon to the earth. The moon continues to orbit the Earth, providing evidence for [Shell theorem](http://en.wikipedia.org/wiki/Shell_theorem).

![](/images/ex3A.png)

![](/images/ex3A-1.png)

**Example 3B:** Puts the PointProjectile Moon in a hollow, spherical shell of ExtendedProjectile Points. The Moon remains relatively motionless with high nIncrement and blocksPerSide. 

**Example 4:** Both the Earth and Moon are now ExtendedProjectiles. Warning, this example requires high CPU usage. 
