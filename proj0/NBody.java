public class NBody{

	/** Method reads the radius of the universe. */
	public static double readRadius(String file){
		In in = new In(file);
		int n = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	/** Method reads the planets. */
	public static Planet[] readPlanets(String file){
		In in = new In(file);
		int n = in.readInt();
		double radius = in.readDouble();

		Planet [] planets = new Planet[n];
		
		int i = 0;
		while (i<n){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			planets[i] = new Planet(xP,yP,xV,yV,m,img);
			i += 1;
		}
		return planets;
	}

	/** Main method. */
	public static void main(String[] args){
		/** Collecting All Needed Input. */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet [] planets = readPlanets(filename);

		/** Drawing the Background. */
		StdDraw.setScale(-radius,radius);
		StdDraw.clear();
		StdDraw.picture(0,0,"/images/starfield.jpg");

		/** Drawing all the planets. */
		for (Planet p : planets){
			p.draw();
		}

		/** Prevent flickering. */
		StdDraw.enableDoubleBuffering();

		/** Time variable*/
		double time = 0;
		while (time<=T){
			double [] xForces = new double[planets.length];
			double [] yForces = new double[planets.length];

			for (int i=0;i<planets.length;i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for (int i=0;i<planets.length;i++){
				planets[i].update(dt,xForces[i],yForces[i]);
			}
			StdDraw.picture(0,0,"/images/starfield.jpg");
			for (Planet p : planets){
				p.draw();
		    }
		    StdDraw.show(10);
		    time += dt;
		}

		StdOut.printf("%d\n",planets.length);
		StdOut.printf("%.2e\n",radius);
		for(int i=0;i<planets.length;i++){
			StdOut.printf("%11.4e %11.4e %11..4e %11.4e %11.4e %12s\n",
						  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
						  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}
	}
}