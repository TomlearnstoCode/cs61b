public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	/** Constructor1 */
	public Planet(double xP, double yP, double xV, 
				  double yV, double m, String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}

	/** Constructor2 */
	public Planet(Planet p){
		xxPos=p.xxPos;
		yyPos=p.yyPos;
		xxVel=p.xxVel;
		yyVel=p.yyVel;
		mass=p.mass;
		imgFileName=p.imgFileName;
	}

	/** Method calculates distance between two planets. */
	public double calcDistance(Planet Planetb){
		double diffx=this.xxPos-Planetb.xxPos;
		double diffy=this.yyPos-Planetb.yyPos;
		double dist=Math.sqrt(diffx*diffx+diffy*diffy);
		return dist;
	}

	/** Method calculates force exerted on Planetb. */
	public double calcForceExertedBy(Planet Planetb){
		double constantG=6.67e-11;
		return constantG*this.mass*Planetb.mass/(this.calcDistance(Planetb)*this.calcDistance(Planetb));
	}

	/** Method calculates force exerted in the X direction. */
	public double calcForceExertedByX(Planet Planetb){
		return this.calcForceExertedBy(Planetb)*(Planetb.xxPos-this.xxPos)/this.calcDistance(Planetb);
	}

	/** Method calculates force exerted in the Y direction. */
	public double calcForceExertedByY(Planet Planetb){
		return this.calcForceExertedBy(Planetb)*(Planetb.yyPos-this.yyPos)/this.calcDistance(Planetb);
	}

	/** Method calculates the netX force upon current Planet. */
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double NetForceX=0;
		for (Planet planetb : allPlanets){
			if (this.equals(planetb)){
				continue;
			}
			else{
				NetForceX += this.calcForceExertedByX(planetb);
			}
		}
		return NetForceX;
	}

	/** Method calculates the netY force upon current Planet. */
	public double calcNetForceExertedByY(Planet[] allPlanets){
		double NetForceY=0;
		for (Planet planetb : allPlanets){
			if (this.equals(planetb)){
				continue;
			}
			else{
				NetForceY += this.calcForceExertedByY(planetb);
			}
		}
		return NetForceY;
	}

	/** Method determines how much the forces exerted on the planet
	will cause that planet to accelerate. */
	public void update(double dt,double ForceX,double ForceY){
		double accelerationX=ForceX/this.mass;
		double accelerationY=ForceY/this.mass;
		this.xxVel += accelerationX*dt;
		this.yyVel += accelerationY*dt;
		this.xxPos += this.xxVel*dt;
		this.yyPos += this.yyVel*dt;
		return ;
	}

	/** Method draws one planet. */
	public void draw(){
		String filename = "/images/"+this.imgFileName;
		StdDraw.picture(this.xxPos,this.yyPos,filename);
	}
}