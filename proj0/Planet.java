public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){
        double deltaX = p.xxPos - this.xxPos;
        double deltaY = p.yyPos - this.yyPos;
        return Math.pow(Math.pow(deltaX, 2)+Math.pow(deltaY,2), 0.5);
    }
    public double calcForceExertedBy(Planet p){
        
        return Planet.G * this.mass * p.mass / Math.pow(this.calcDistance(p),2);
    }

    public double calcForceExertedByX(Planet p){
        return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p){
        return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
    }
    public double calcNetForceExertedByX(Planet[] planetArray){
        double sum = 0;
        for(Planet planet:planetArray){
            if(!this.equals(planet)){
                sum += calcForceExertedByX(planet);
            }
        }
        return sum;
    }
    public double calcNetForceExertedByY(Planet[] planetArray){
        double sum = 0;
        for(Planet planet:planetArray){
            if(!this.equals(planet)){
                sum += calcForceExertedByY(planet);
            }
        }
        return sum;
    }
    public void update(double dt, double fX, double fY){
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel = xxVel + dt * aX;
        yyVel = yyVel + dt * aY;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,imgFileName);
        // StdDraw.show();
    }
}