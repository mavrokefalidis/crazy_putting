package com.project.putting_game;

public class Engine
{

    public static final double g = 9.81;
    public static double Currentfriction;
    public static double currentHeight;
    public static final double h = 0.1;
    public static double xh;
    public static double yh;
    public static double vx_h;
    public static double vy_h;
    public static double vx;
    public static double vy;


    public static void calculate(Ball ball, Field fields)
    {
         double x =  ball.position.x;
         double y =  ball.position.y;

         vx = ball.velocity.x;
         vy = ball.velocity.y;

         xh = x + h * vx;
         yh = y + h * vy;

         vx_h = vx + h * forceX(ball);
         vy_h = vy + h * forceY();

         ball.velocity.x = (float)vx_h;
         System.out.println(ball.velocity.x + "XXXXXX");

         ball.velocity.y = (float)vy_h;
        System.out.println(ball.velocity.y + "YYYYYY");

         ball.position.x = (float)xh;
         ball.position.y = (float)yh;


         Currentfriction = fields.matrix[(int)ball.position.y][(int)ball.position.x].friction;
         currentHeight = fields.matrix[(int)ball.position.y][(int)ball.position.x].height;
         System.out.println(ball.velocity);
    }

    public static double forceX(Ball ball)
    {
        double Fx = ((-g) * (0)) - (Currentfriction * g * vx) ;
        System.out.println(Fx);
        System.out.println(g);
        System.out.println(currentHeight);
        System.out.println(Currentfriction);
        System.out.println(vx);
        System.out.println(vy);
        System.out.println("Ball position : "+ ball.position);
        return Fx;
    }
    public static double forceY()
    {
        double Fy = ((-g) * (0)) - (Currentfriction * g * vy) ;
        return Fy;
    }
}