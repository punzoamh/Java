package wumpusWorld;



import java.util.Scanner;
 
class wumpus
{
        static int scream=0;
        static int performanceMeasure=0;
        static int complete=0;
 
        static boolean check(Percepts agentPercepts)
        {
                int perc1=agentPercepts.sense();
                if(perc1==1 || perc1==2)
                        return false;
 
                return true;
        }
       
}