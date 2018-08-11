package wumpusWorld;

import java.util.Scanner;

class environment
{
       Scanner input=new Scanner(System.in);
      
       int pits;     //number of pits
       int wumpus,gold; // wumpus position   gold position
       int pitPosition[]; // position of pits
       int b_pos[]=new int[20];
       int s_pos[]=new int[20];
       void accept(String w[][])
       {
               for(int i=0;i<20;++i)
               {
                       b_pos[i]=-1;
                       s_pos[i]=-1;
               }

               for(int i=0;i<6;++i)
                       for(int j=0;j<5;++j)
                               w[i][j]="";

               int count=1;
              

               System.out.println("The positions are as follows.");
               for(int i=1;i<=5;++i)
               {
                       System.out.println("\n-----------------------------------------------------------------");
                       System.out.print("|\t");
                       for(int j=1;j<=4;++j)
                               System.out.print((count++)+"\t|\t");
               }
               System.out.println("\n-----------------------------------------------------------------");
               System.out.println("\nAgent Position: 13");
               w[4][1]="A";
               System.out.println("\nEnter How Many Pits to Place in Environment.");
               pits=input.nextInt();
               pitPosition=new int[pits];
               System.out.println("Be Sure No Positions Overlap.");
               System.out.println("Enter the position of pits.");
               for(int i=0;i<pits;++i)
               {
                       pitPosition[i]=input.nextInt();
                       show_sense(pitPosition[i],1,w);
               }
               System.out.println("Enter the position of wumpus.");
               wumpus=input.nextInt();
               show_sense(wumpus,2,w);

               System.out.println("Enter the position of gold.");
               gold=input.nextInt();

               insert(w);
       }

       void insert(String w[][])
       {
               int temp=0;
               int count=0;
               int flag1=0,flag2=0;
               for(int i=0;i<pits;++i)
               {
                       temp=pitPosition[i];
                       count=0;
                       for(int j=1;j<=5;++j)
                       {
                               for(int k=1;k<=4;++k)
                               {
                                       ++count;
                                       if(count==temp)
                                               w[j][k]+="P";
                                       else
                                               if(count==gold && flag1==0)
                                               {
                                                       w[j][k]+="G";
                                                       flag1=1;
                                               }
                                               else
                                                       if(count==wumpus && flag2==0)
                                                       {
                                                               w[j][k]+="W";
                                                               flag2=1;
                                                       }
                               }
                       }
               }

               display(w);
       }

       void show_sense(int a,int b,String w[][])
       {
               int t1,t2,t3,t4;
               t1=a-1;
               t2=a+1;
               t3=a+4;
               t4=a-4;

               if(a==5 || a==9)
                       t1=0;
               if(a==8 || a==12)
                       t2=0;
               if(a==4)
                       t2=0;
               if(a==13)
                       t1=0;

               if(t3>20)
                       t3=0;
               if(t4<0)
                       t4=0;

              

               if(b==1)
               {b_pos[0]=t1;b_pos[1]=t2;b_pos[2]=t3;b_pos[3]=t4;}
               else
                       if(b==2)
                       {s_pos[0]=t1;s_pos[1]=t2;s_pos[2]=t3;s_pos[3]=t4;}

               int temp1,count;

               for(int i=0;i<5;++i)
               {
                       if(b==1)
                               temp1=b_pos[i];
                       else
                               temp1=s_pos[i];
                       count=0;
                       for(int j=1;j<=4;++j)
                       {
                               for(int k=1;k<=4;++k)
                               {
                                       ++count;
                                       if(count==temp1 && b==1 && !w[j][k].contains("B"))
                                       {
                                               w[j][k]+="B";
                                       }
                                       else
                                               if(count==temp1 && b==2 && !w[j][k].contains("S"))
                                                       w[j][k]+="S";
                               }
                       }
               }

      
       }
       void display(String w[][])
       {
               System.out.println("\nThe environment for problem is as follows.\n");
               for(int i=1;i<=5;++i)
               {
                       System.out.println("\n-----------------------------------------------------------------");
                       System.out.print("|\t");
                       for(int j=1;j<=4;++j)
                               System.out.print(w[i][j]+"\t|\t");
               }
               System.out.println("\n-----------------------------------------------------------------");
       }


}