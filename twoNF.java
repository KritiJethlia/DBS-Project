import java.io.*;
import java.util.*;
import java.lang.*;
public class twoNF
{
    static ArrayList<Integer> finalRelations= new ArrayList<Integer>();
    // public static void main(String[] args) {
    //     int [][]binaryFD= new int[5][2];
    //     binaryFD[0][0]=7;
    //     binaryFD[0][1]=8;

    //     binaryFD[1][0]=11;
    //     binaryFD[1][1]=16;

    //     binaryFD[2][0]=12;
    //     binaryFD[2][1]=32;

    //     binaryFD[3][0]=34;
    //     binaryFD[3][1]=8;

    //     binaryFD[4][0]=44;
    //     binaryFD[4][1]=2;
    //     int []candidate= new int[2];
    //     candidate[0]=7;
    //     candidate[1]=13;
    //     String attribute[] = new String[6];
    //     attribute[0]="a";
    //     attribute[1]="b";
    //     attribute[2]="c";
    //     attribute[3]="d";
    //     attribute[4]="e";
    //      attribute[5]="f";
    //     // attribute[6]="g";
    //     // attribute[7]="h";
    //     // attribute[8]="i";
    //     // attribute[9]="j";

    //     check_2NF(binaryFD,candidate,attribute);
    //   }
    public static void check_2NF(int[][]binaryFD, int[]candidate ,String attribute[])
    {
        int primeatt=0;
        ArrayList<Integer> not2NF= new ArrayList<Integer>();
        ArrayList<Integer> removed= new ArrayList<Integer>();
        for(int i=0;i<candidate.length;++i)
        {
            primeatt= primeatt | candidate[i];
        }
        for(int i=0;i<binaryFD.length;++i)
        {
            if((binaryFD[i][1]&primeatt) == binaryFD[i][1])
            {
              continue;
            }
            else
            {
              boolean flag2NF=true;
              for(int j=0;j<candidate.length;++j)
              {
                 if((binaryFD[i][0]&candidate[j])== binaryFD[i][0] && (binaryFD[i][0]&candidate[j])!= candidate[j])
                 {
                     flag2NF=false;
                 }
              }
              if (!flag2NF)
              {
               binaryFD[i][1]=( binaryFD[i][1]| primeatt)^primeatt;
               boolean change=true;
               while(change)
               {
                 for(int j=0;j<binaryFD.length;++j)
                 {
                     change=false;
                     if((((binaryFD[j][0] | primeatt) ^ primeatt) & binaryFD[i][1] )!= 0 && (binaryFD[j][1] & binaryFD[i][1])!= binaryFD[j][1] && removed.indexOf(j)==-1)//first remove prime attribues from left side
                     {
                         int temp=(binaryFD[j][1] | primeatt)^primeatt;
                         binaryFD[i][1]=binaryFD[i][1]|temp;
                         removed.add(j);
                         change = true;
                     }
                 }
              }
               not2NF.add(i);
              }
            }
         }
        if(not2NF.size()==0 )
       {
           System.out.println("Relation is in 2NF");
           closure.alpha=1;
       }
       else
       {
           for(int i=0;i<not2NF.size();i++)
            System.out.println(not2NF.get(i));
           //System.out.println("false");
            convert_2NF(not2NF,attribute,binaryFD,removed);
       }
    }
    public static void convert_2NF(ArrayList<Integer>not2NF ,String attribute[],int[][]binaryFD,ArrayList<Integer> removed)
    {
        ArrayList<ArrayList<String>> relation= new ArrayList<ArrayList<String>>();
        ArrayList<String>primary =new ArrayList<String>();
        int k=(int)Math.pow(2,attribute.length)-1;
        for(int i=0;i<not2NF.size();++i)
        {
            k=(k|binaryFD[not2NF.get(i)][1])^binaryFD[not2NF.get(i)][1];
            boolean removedflag=false;
            for(int j=0;j<removed.size();++j)
            {
                if(removed.get(j)==i)
                {
                 removedflag=true;
                }
            }
            if(!removedflag)
            {
                k=k|binaryFD[not2NF.get(i)][0];
            }
            ArrayList<String>temp =new ArrayList<String>();
            relation.add(temp);
        }
        for(int i=0;i<binaryFD.length;i++)
        {
            if(removed.indexOf(i)==-1 && not2NF.indexOf(i)==-1)
            {
                k=k|binaryFD[i][0];
                k=k|binaryFD[i][1];
            }
        }
        for(int i=0;i<attribute.length;++i)
        {
            int p=(int)Math.pow(2,i);
            if((k&p)==p)
            {
                primary.add(attribute[i]);
            }
            for(int j=0;j<not2NF.size();++j)
            {
                if((binaryFD[not2NF.get(j)][0] &p)==p || (binaryFD[not2NF.get(j)][1] &p)==p )
                {
                    relation.get(j).add(attribute[i]);
                }
            }

        }
        //System.out.println(primary);
        relation.add(primary);
        // for(int i=0;i<relation.size();++i)
        // {
        //     System.out.println(relation.get(i));
        // }
        
        for(int i=0;i<relation.size();i++)
        {
            int p=0;
            for(int j=0;j<relation.get(i).size();j++)
            {
               p=p|(int)Math.pow(2,Arrays.asList(attribute).indexOf(relation.get(i).get(j))); 
            }
           finalRelations.add(p);
        }

    }
}