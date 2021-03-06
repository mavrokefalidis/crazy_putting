package com.project.putting_game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Random;

public class RandomBot
{
    private Field course;
    private Ball ball;
    private float maxX = 4000;
    private float maxY = 4000;
    private float minX = -4000;
    private float minY = -4000;
    private float maxW = Gdx.graphics.getWidth();
    private float maxH = Gdx.graphics.getHeight();
    private float minW = 0;
    private float minH = 0;
    private Shot2[] shots = new Shot2[1000];
    private Shot2 bestShot;
    private ArrayList<String> fieldFormula;
    private Hole hole;
    private Vector3 StartingPosition;


    RandomBot(Field course, Ball ball, Hole hole, ArrayList<String> fieldFormula)
    {
        this.course = course;
        this.ball = ball;
        this.fieldFormula = fieldFormula;
        //System.out.println("ball initial position: " + ball.position);
        StartingPosition = ball.position;
        this.hole = hole;
        bestShot = new Shot2(0,0,ball,course, hole, fieldFormula);
    }

    /**
     * startProcess repeats the process of creating, sorting, evaluating shots until a shot that reaches the hole has been found
     * @return a shot that has reached the hole
     */
    public Vector3 startProcess()
    {
        while(bestShot.getScore()>13)
        {
            takeShots();
            bubbleSort();
            setMinMax();
            bestShot = shots[0];
        }
        //System.out.println("RandX: " + bestShot.getRandX());
        //System.out.println("RandY: " + bestShot.getRandY());
        //System.out.println("Score: " + bestShot.getScore());
        //System.out.println("HoleW: " + hole.holeShape.width);
        //System.out.println("HoleH: " + hole.holeShape.height);
        return bestShot.getDirection();
    }

    /***
     * takeShots takes a set amount of random shots. The random values are "random" between two values (minX, maxX , minY, maxY)
     */
    public void takeShots()
    {
        Random random = new Random();
        for(int i=0; i<shots.length; i++)
        {
            float x = random.nextFloat() * (maxX - minX) + minX;
            float y = random.nextFloat() * (maxY - minY) + minY;
            shots[i] = new Shot2(x,y, ball, course, hole, fieldFormula);
        }
    }

    /***
     * bubbleSort sorts all the shots based on the distance to the hole. It's sorted from closest to farthest away
     */
    public void bubbleSort()
    {
        //System.out.println("Sorting");
        int end = shots.length-1;
        boolean done = false;
        while(!done)
        {
            for(int i=0; i<end; i++)
            {
                if(shots[i+1].getScore() < shots[i].getScore())
                {
                    Shot2 placeholder = shots[i+1];
                    shots[i+1] = shots[i];
                    shots[i] = placeholder;;
                }
            }
            end--;
            if(end<0)
                done = true;
        }
        //for(int i=0; i<shots.length; i++)
        //{
        //    System.out.println(shots[i].getScore() + " " + shots[i].getDirection());
        //}
    }

    /***
     * setMinMax goes over the top 10% of the shots, evaluates them and changes the min and max values accordingly.
     */
    public void setMinMax()
    {
        //System.out.println("Setting min & max");
        for(int i=0 ; i<shots.length/100; i++)
        {
            if(shots[i].getX()>=hole.position.x && maxW-minW>2) //MAX X
            {
                //System.out.println("maxX: " + maxX);
                //System.out.println("maxW: " + maxW);
                //System.out.println("newMaxX: " + shots[i].getRandX());
                //System.out.println("newW: " + shots[i].getX());
                if(StartingPosition.x <= hole.position.x && shots[i].getX()<=maxW)
                {
                    maxX = shots[i].getRandX();
                    maxW = shots[i].getX();
                }
                else if(StartingPosition.x > hole.position.x && shots[i].getX()>=minW)
                {
                    minX = shots[i].getRandX();
                    minW = shots[i].getX();
                }

                //if(minX>maxX)
                //    System.out.println("Fucked up!");
            }
            else if(shots[i].getX()<hole.position.x && maxW-minW>2) //MIN X
            {
                //System.out.println("minX: " + minX);
                //System.out.println("minW: " + minW);
                //System.out.println("newMinX: " + shots[i].getRandX());
                //System.out.println("newW: " + shots[i].getX());
                if(StartingPosition.x <= hole.position.x && shots[i].getX()>=minW)
                {
                    minX = shots[i].getRandX();
                    minW = shots[i].getX();
                }
                else if(StartingPosition.x > hole.position.x && shots[i].getX()<=maxW)
                {
                    maxX = shots[i].getRandX();
                    maxW = shots[i].getX();
                }

            }

            if(shots[i].getY()>=hole.position.y && maxH-minH>2) //MAX Y
            {
                //System.out.println("maxY: " + maxY);
                //System.out.println("maxH: " + maxH);
                //System.out.println("newY: " + shots[i].getRandY());
                //System.out.println("newH: " + shots[i].getY());
                if(StartingPosition.y <= hole.position.y && shots[i].getY()<=maxH)
                {
                    maxY = shots[i].getRandY();
                    maxH = shots[i].getY();
                }
                else if(StartingPosition.y > hole.position.y && shots[i].getY()>=minH)
                {
                    minY = shots[i].getRandY();
                    minH = shots[i].getY();
                }
            }
            else if(shots[i].getY()<hole.position.y && maxH-minH>2) //MIN Y
            {
                //System.out.println("minY: " + minY);
                //System.out.println("minH: " + minH);
                //System.out.println("newY: " + shots[i].getRandY());
                //System.out.println("newH: " + shots[i].getY());
                if(StartingPosition.y <= hole.position.y && shots[i].getY()>=minH)
                {
                    minY = shots[i].getRandY();
                    minH = shots[i].getY();
                }
                else if(StartingPosition.y > hole.position.y && shots[i].getY()<=maxH)
                {
                    maxY = shots[i].getRandY();
                    maxH = shots[i].getY();
                }
            }
        }
        //System.out.println("Max x y: " + maxX + " " + maxY);
        //System.out.println("Min x y: " + minX + " " + minY);
        //System.out.println("Max w h: " + maxW + " " + maxH);
        //System.out.println("Min w h: " + minW + " " + minH);
    }
}
