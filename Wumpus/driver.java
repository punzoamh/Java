package wumpusWorld;

import java.util.Scanner;

public class driver
{
        public static void main(String args[])
        {
                Scanner input= new Scanner(System.in);
                environment e= new environment();
                wumpus wumpus1 = new wumpus();
                String w[][]= new String[6][5];
                e.accept(w);
                System.out.println("\n\nFinding the solution...");
 
                Percepts t[]= new Percepts[22];
                int c=1;
                out:for(int i=1;i<=5;++i)
                {
                        for(int j=1;j<5;++j)
                        {
                                if(c>20)
                                        break out;
                                t[c]= new Percepts(w[i][j],c);
                                ++c;
                        }
                }
 
                t[13].safeZone=1;
                t[13].visited=1;
 
                int pos=13;
                int condition;
                int limit=0;
                String temp1,temp2;
                do
                {
                        ++limit;
                        condition=-1;
 
                        if(t[pos].env.contains("G"))
                        {
                                wumpus1.complete=1;
                                System.out.println("Gold Found!!");
                                break;
                        }
 
                        if(t[pos].senRight!=1 && t[pos].right!=1 && t[pos+1].doubt_pit<1
                                        && t[pos+1].doubt_wump<1
                                        && t[pos+1].pit!=1
                                        && t[pos+1].wumpus1!=1
                                        && !(t[pos].back.contains("r")
                                        && (t[pos].left!=1 || t[pos].up!=1 || t[pos].down!=1)
                                        && wumpus1.check(t[pos]) ))
                        {
                                
 
                                temp1="l";
                               
                                t[pos].right=1;
                                ++pos;
                                System.out.println("\nfront pos="+pos);
                                ++wumpus1.performanceMeasure;
                              
                                t[pos].back+=temp1;
                             
                                condition=t[pos].sense();
                                if(condition==3)
                                {wumpus1.complete=1;break;}
                                else
                                        if(condition==1 && t[pos].visited==0)
                                        {
                                                if(t[pos].senRight!=1 && t[pos+1].safeZone!=1)
                                                        t[pos+1].doubt_pit+=1;
                                                if(t[pos].senUp!=1 && (pos-4)>=1 && t[pos-4].safeZone!=1)
                                                        t[pos-4].doubt_pit+=1;
                                                if(t[pos].senLeft!=1 && t[pos-1].safeZone!=1 )
                                                        t[pos-1].doubt_pit+=1;
                                                if(t[pos].senDown!=1 && (pos+4)<=20 && t[pos+4].safeZone!=1)
                                                        t[pos+4].doubt_pit+=1;
 
                                                t[pos].safeZone=1;
                                        }
                                        else
                                                if(condition==2 && t[pos].visited==0)
                                                {
                                                        if(t[pos].senRight!=1 && t[pos+1].safeZone!=1)
                                                                t[pos+1].doubt_wump+=1;
                                                        if(t[pos].senUp!=1 && (pos-4)>=1 &&  t[pos-4].safeZone!=1)
                                                                t[pos-4].doubt_wump+=1;
                                                        if(t[pos].senLeft!=1 && t[pos-1].safeZone!=1)
                                                                t[pos-1].doubt_wump+=1;
                                                        if(t[pos].senDown!=1 && (pos+4)<=20 &&  t[pos+4].safeZone!=1)
                                                                t[pos+4].doubt_wump+=1;
 
                                                        t[pos].safeZone=1;
                                                }

                                                else
                                                        if(condition==0)
                                                                t[pos].safeZone=1;
 
 
                                t[pos].visited=1;
                        }
                        else
                                if(t[pos].senLeft!=1
                                && t[pos].left!=1
                                && t[pos-1].doubt_pit<1
                                && t[pos-1].doubt_wump<1 && t[pos-1].pit!=1
                                && t[pos-1].wumpus1!=1 && !(t[pos].back.contains("l")
                                && (t[pos].right!=1 || t[pos].up!=1 || t[pos].down!=1)
                                && wumpus1.check(t[pos]) ))
                                {
                                       
                                        temp1="r";
                                       
                                        t[pos].left=1;
                                        pos=pos-1;
                                        System.out.println("\nback pos= "+pos);
                                        ++wumpus1.performanceMeasure;
                                        
 
                                       
 
                                        t[pos].back+=temp1;
                                       
 
 
                                        condition=t[pos].sense();
                                        if(condition==3)
                                        {wumpus1.complete=1;break;}
                                        else
                                                if(condition==1 && t[pos].visited==0)
                                                {
                                                        if(t[pos].senRight!=1 && t[pos+1].safeZone!=1)
                                                                t[pos+1].doubt_pit+=1;
                                                        if(t[pos].senUp!=1 && (pos-4)>=1 &&  t[pos-4].safeZone!=1)
                                                                t[pos-4].doubt_pit+=1;
                                                        if(t[pos].senLeft!=1 && t[pos-1].safeZone!=1)
                                                                t[pos-1].doubt_pit+=1;
                                                        if(t[pos].senDown!=1 && (pos+4)<=20 &&  t[pos+4].safeZone!=1)
                                                                t[pos+4].doubt_pit+=1;
 
 
                                                        t[pos].safeZone=1;
                                                }
                                                else
                                                        if(condition==2 && t[pos].visited==0)
                                                        {
                                                                if(t[pos].senRight!=1 && t[pos+1].safeZone!=1)
                                                                        t[pos+1].doubt_wump+=1;
                                                                if(t[pos].senUp!=1 && (pos-4)>=1 &&  t[pos-4].safeZone!=1)
                                                                        t[pos-4].doubt_wump+=1;
                                                                if(t[pos].senLeft!=1 && t[pos-1].safeZone!=1)
                                                                        t[pos-1].doubt_wump+=1;
                                                                if(t[pos].senDown!=1 && (pos+4)<=20 &&  t[pos+4].safeZone!=1)
                                                                        t[pos+4].doubt_wump+=1;
 
                                                                t[pos].safeZone=1;
                                                        }
                                                        else
                                                                if(condition==0)
                                                                        t[pos].safeZone=1;
 
                                        t[pos].visited=1;
 
 
                                }
                                else
                                        if(t[pos].senUp!=1
                                        && t[pos].up!=1
                                        && (pos-4)>=1
                                        &&  t[pos-4].doubt_pit<1
                                        && t[pos-4].doubt_wump<1
                                        && t[pos-4].pit!=1 && t[pos-1].wumpus1!=1
                                        && !(t[pos].back.contains("u") && (t[pos].left!=1 || t[pos].right!=1 || t[pos].down!=1)
                                        && wumpus1.check(t[pos])  ))
                                        {
                                               
 
                                                temp1="d";
                                              
                                                t[pos].up=1;
                                                pos=pos-4;
                                                System.out.println("\nUp pos= "+pos);
                                                ++wumpus1.performanceMeasure;
                                             
                                                t[pos].back+=temp1;
                                              
                                                condition=t[pos].sense();
                                                if(condition==3)
                                                {wumpus1.complete=1;break;}
                                                else
                                                        if(condition==1 && t[pos].visited==0)
                                                        {
                                                                if(t[pos].senRight!=1 && t[pos+1].safeZone!=1)
                                                                        t[pos+1].doubt_pit+=1;
                                                                if(t[pos].senUp!=1 && (pos-4)>=1 &&  t[pos-4].safeZone!=1)
                                                                        t[pos-4].doubt_pit+=1;
                                                                if(t[pos].senLeft!=1 && t[pos-1].safeZone!=1)
                                                                        t[pos-1].doubt_pit+=1;
                                                                if(t[pos].senDown!=1 && (pos+4)<=20 &&  t[pos+4].safeZone!=1)
                                                                        t[pos+4].doubt_pit+=1;
 
 
                                                                t[pos].safeZone=1;
                                                        }
                                                        else
                                                                if(condition==2 && t[pos].visited==0)
                                                                {
                                                                        if(t[pos].senRight!=1 && t[pos+1].safeZone!=1)
                                                                                t[pos+1].doubt_wump+=1;
                                                                        if(t[pos].senUp!=1 && (pos-4)>=1 &&  t[pos-4].safeZone!=1)
                                                                                t[pos-4].doubt_wump+=1;
                                                                        if(t[pos].senLeft!=1 && t[pos-1].safeZone!=1)
                                                                                t[pos-1].doubt_wump+=1;
                                                                        if(t[pos].senDown!=1 && (pos+4)<=20 &&  t[pos+4].safeZone!=1)
                                                                                t[pos+4].doubt_wump+=1;
 
                                                                        t[pos].safeZone=1;
                                                                }
                                                                else
                                                                        if(condition==0)
                                                                                t[pos].safeZone=1;
 
                                                t[pos].visited=1;
                                        }
                                        else
                                                if(t[pos].senDown!=1 && t[pos].down!=1
                                                && (pos+4)<=20 &&  t[pos+4].doubt_pit<1
                                                && t[pos+4].doubt_wump<1
                                                && t[pos+4].pit!=1
                                                && t[pos+4].wumpus1!=1)
                                                {
                                                       
                                                        temp1="u";
                                                        
                                                        t[pos].down=1;
                                                        pos=pos+4;
                                                        System.out.println("\ndown pos= "+pos);
                                                        ++wumpus1.performanceMeasure;
                                                       
 
                                                        t[pos].back+=temp1;
                                                       
                                                        condition=t[pos].sense();
                                                        if(condition==3)
                                                        {wumpus1.complete=1;break;}
                                                        else
                                                                if(condition==1 && t[pos].visited==0)
                                                                {
                                                                        if(t[pos].senRight!=1 && t[pos+1].safeZone!=1)
                                                                                t[pos+1].doubt_pit+=1;
                                                                        if(t[pos].senUp!=1 && (pos-4)>=1 &&  t[pos-4].safeZone!=1)
                                                                                t[pos-4].doubt_pit+=1;
                                                                        if(t[pos].senLeft!=1 && t[pos-1].safeZone!=1)
                                                                                t[pos-1].doubt_pit+=1;
                                                                        if(t[pos].senDown!=1 && (pos+4)<=20 &&  t[pos+4].safeZone!=1)
                                                                                t[pos+4].doubt_pit+=1;
 
                                                                        t[pos].safeZone=1;
                                                                }
                                                                else
                                                                        if(condition==2 && t[pos].visited==0)
                                                                        {
                                                                                if(t[pos].senRight!=1 && t[pos+1].safeZone!=1)
                                                                                        t[pos+1].doubt_wump+=1;
                                                                                if(t[pos].senUp!=1 && (pos-4)>=1 &&  t[pos-4].safeZone!=1)
                                                                                        t[pos-4].doubt_wump+=1;
                                                                                if(t[pos].senLeft!=1 && t[pos-1].safeZone!=1)
                                                                                        t[pos-1].doubt_wump+=1;
                                                                                if(t[pos].senDown!=1 && (pos+4)<=20 &&  t[pos+4].safeZone!=1)
                                                                                        t[pos+4].doubt_wump+=1;
 
                                                                                t[pos].safeZone=1;
                                                                        }
                                                                        else
                                                                                if(condition==0)
                                                                                        t[pos].safeZone=1;
 
                                                        t[pos].visited=1;
                                                }
                                                else
                                                        if(limit>50)
                                                        {
                                                                int temp3=pos;
                                                                int flag_1=0,flag2=0,flag3=0,flag4=0;
 
                                                                System.out.println("\nCurrently at position "+temp3+".\nThinking....");
 
                                                               
                                                                while(t[pos].visited==1 && t[pos].senRight!=1)
                                                                {
                                                                        ++pos;
                                                                        ++wumpus1.performanceMeasure;
                                                                }
 
 
                                                                if(t[pos].pit==1 || t[pos].wumpus1==1 || (t[pos].senRight==1 && t[pos].visited==1 && t[pos].safeZone!=1))
                                                                {
                                                                       
                                                                        pos=temp3;
                                                                       
                                                                        flag_1=1;
                                                                }
 
                                                                if(flag_1==0)
                                                                        t[pos].back+="l";
 
                                                               
                                                                while(pos+4>=1 && t[pos].senUp!=1 && t[pos].visited==1)
                                                                {
                                                                        pos-=4;
                                                                        ++wumpus1.performanceMeasure;
                                                                }
 
                                                                if(t[pos].pit==1 || t[pos].wumpus1==1 || (t[pos].senUp==1 && t[pos].visited==1  && t[pos].safeZone!=1))
                                                                {
                                                                       
                                                                        pos=temp3;
                                                                        flag3=1;
                                                                        
                                                                }
 
                                                                if(flag3==0)
                                                                        t[pos].back+="d";
 
                                                               
                                                                while(t[pos].visited==1 && t[pos].senLeft!=1)
                                                                {
                                                                        --pos;
                                                                        ++wumpus1.performanceMeasure;
                                                                }
 
                                                                if(t[pos].pit==1 || t[pos].wumpus1==1 || (t[pos].senLeft==1 && t[pos].visited==1 && t[pos].safeZone!=1))
                                                                {
                                                                        
                                                                        pos=temp3;
                                                                        flag2=1;
                                                                       
                                                                }
 
                                                                if(flag2==0)
                                                                        t[pos].back+="r";
 
 
 
                                                               
                                                                while(pos+4<=20 && t[pos].senDown!=1 && t[pos].visited==1)
                                                                {
                                                                        pos+=4;
                                                                        ++wumpus1.performanceMeasure;
                                                                }
 
                                                                if(t[pos].pit==1 || t[pos].wumpus1==1 || (t[pos].senDown==1 && t[pos].visited==1 && t[pos].safeZone!=1))
                                                                {
                                                                       
                                                                        pos=temp3;
                                                                        flag4=1;
                                                                       
                                                                }
                                                               
                                                                if(flag4==0)
                                                                        t[pos].back+="u";
 
                                                                t[pos].safeZone=1;
                                                                t[pos].visited=1;
                                                                System.out.println("reached at position "+pos);
                                                                limit=0;
                                                        }
                        
                        if(t[pos].env.contains("W") && wumpus1.scream!=1)
                        {
                                wumpus1.performanceMeasure+=100;
                                wumpus1.scream=1;
                                t[pos].safeZone=1;
                                System.out.println("\n\nWumpus killed >--0-->");
                                t[pos].env.replace("W"," ");
                                for(int l=1;l<=20;++l)
                                {
                                        t[l].doubt_wump=0;
                                        t[l].env.replace("S"," ");
                                }
                        }
 
                        if(t[pos].env.contains("P"))
                        {
                                wumpus1.performanceMeasure+=50;
                                t[pos].pit=1;
                                System.out.println("\n\nFallen in pit of position "+pos+".");
                        }
 
                        for(int k=1;k<=20;++k)
                        {
                                if(t[k].doubt_pit==1 && t[k].doubt_wump==1)
                                {
                                        t[k].doubt_pit=0;
                                        t[k].doubt_wump=0;
                                        t[k].safeZone=1;
                                }
                        }
 
                        for(int y=1;y<=20;++y)
                        {
                                if(t[y].doubt_wump>1)
                                {
                                        t[y].wumpus1=1;
                                        for(int h=1;h<=20;++h)
                                        {
                                                if(h!=y)
                                                {
                                                        t[h].doubt_wump=0;
                                                        t[h].env.replace("S"," ");
                                                }
                                        }
 
                                }
 
                        }
 
                      
                        for(int y=1;y<=20;++y)
                        {
                                if(t[y].doubt_pit>1)
                                {t[y].pit=1;
                              
                                }
                        }
                     
 
 
                        try{Thread.sleep(200);}catch(Exception p){}
 
                }
                while(wumpus1.complete==0);
 
                if(wumpus1.complete==1)
                {
                        
 
                        wumpus1.performanceMeasure*=-1;
                        wumpus1.performanceMeasure+=1000;
                }
                System.out.println("The score of the agent till he reaches gold is "+wumpus1.performanceMeasure
                                            +".\nNow he will return back following the best explored path.");
 
        }
 
}