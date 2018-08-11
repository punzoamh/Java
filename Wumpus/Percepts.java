package wumpusWorld;

public class Percepts
{
       int safeZone=0;
       int unsafeZone=0;
       int wumpus1=0;
       int pit=0;
       int gold1=0;
       int doubt_pit=0;
       int doubt_wump=0;
       String env;
       int num=0;
       int senRight=0;     //breeze right
       int senLeft=0;     //breeze left
       int senUp=0;	//breeze up
       int senDown=0;	//breeze down	
       int visited=0;
       int left,right,up,down;
       String back="";
      
       Percepts(String s,int n)
       {
               env=s;
               num=n;
               left=right=up=down=0;
               if(n==9 || n==5)
            	   senLeft=1;

               if(n==8 || n==12)
                       senRight=1;

               if(n==1)
               {
            	   senUp=1;senLeft=1;
               }
               if(n==13)
               {
            	   senDown=1;senLeft=1;
               }
               if(n==4)
               {
            	   senUp=1;senRight=1;
               }
               if(n==16)
               {
            	   senDown=1;senRight=1;
               }

       }

       int sense()
       {
         if(env.contains("BR")) return 1; //Breeze
         else
               if(env.contains("ST")) return 2; //Stench
               else
               if(env.contains("GL")) return 3; //Glitter
               if(env.contains("W")) return 4; //Wumpus
               else return 0;
       }

}