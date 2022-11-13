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
}