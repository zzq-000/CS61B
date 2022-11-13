public class NBody{
    public static double readRadius(String file){
        In in = new In(file);
        int number = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String file){
        In in = new In(file);
        int number = in.readInt();
        Planet[] planetArray = new Planet[number];
        in.readDouble();
        for(int i = 0; i < number ; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planetArray[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
        }
        return planetArray;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] planetArray = NBody.readPlanets(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius,radius);
		StdDraw.clear();

        StdDraw.picture(0, 0, "images/starfield.jpg");
        // StdDraw.show();
        // for(Planet planet:planetArray){     
        //     planet.imgFileName = "images/" + planet.imgFileName;
        // }
        StdDraw.show();

        int timer = 0;

        while(timer < T){
            double [] XForces = new double[planetArray.length];
            double [] YForces = new double[planetArray.length];
            for(int i=0;i < planetArray.length; i++){
                XForces[i] = planetArray[i].calcNetForceExertedByX(planetArray);
                YForces[i] = planetArray[i].calcNetForceExertedByY(planetArray);
            }
            for(int i = 0; i < planetArray.length; i++){
                planetArray[i].update(dt, XForces[i], YForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(Planet planet:planetArray){
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            timer += dt;

            
        }
        StdOut.printf("%d\n", planetArray.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planetArray.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
        planetArray[i].xxPos, planetArray[i].yyPos, planetArray[i].xxVel,
        planetArray[i].yyVel, planetArray[i].mass, planetArray[i].imgFileName);   
}


    }
}