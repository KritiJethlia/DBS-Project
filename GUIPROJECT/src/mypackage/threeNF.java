/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypackage;

/**
 *
 * @author sristi
 */
import java.io.*;
import java.util.*;
import java.lang.*;
public class threeNF
{
    static ArrayList<Integer> finalRelations= new ArrayList<Integer>();
    // public static void main(String[] args) {
    //     int [][]binaryFD= new int[3][2];
    //     binaryFD[0][0]=1;
    //     binaryFD[0][1]=2;
    //     binaryFD[1][0]=1;
    //     binaryFD[1][1]=4;
    //     binaryFD[2][0]=4;
    //     binaryFD[2][1]=8;
    //     int []candidate= new int[1];
    //     candidate[0]=1;
    //     int []superkey=new int[8];
    //     superkey[0]=1;
    //     superkey[1]=3;
    //     superkey[2]=5;
    //     superkey[3]=9;
    //     superkey[4]=7;
    //     superkey[5]=13;
    //     superkey[6]=11;
    //     superkey[7]=15;
    //     String attribute[] = new String[4];
    //     attribute[0]="a";
    //     attribute[1]="b";
    //     attribute[2]="c";
    //     attribute[3]="d";

    //     check_3NF(binaryFD,candidate ,superkey,attribute);
    //   }
//    public static void check_3NF(int[][]binaryFD, int[]candidate , int[]superKey,String attribute[])
//    {
//        int primeatt=0;
//        ArrayList<Integer> not3NF= new ArrayList<Integer>();
//        for(int i=0;i<candidate.length;++i)
//        {
//            primeatt= primeatt | candidate[i];
//        }
//        for(int i=0;i<binaryFD.length;++i)
//        {
//            boolean skeyflag=false;
//            for(int j=0;j<superKey.length;++j)
//            {
//                if(binaryFD[i][0]== superKey[j])
//                 skeyflag = true;
//                 
//            }
//            if(skeyflag==true)
//             continue;
//            else
//            {
//                if((binaryFD[i][1] & primeatt)== binaryFD[i][1])
//                 continue;
//                else
//                 {
//                    binaryFD[i][1]=( binaryFD[i][1]| primeatt)^primeatt;
//                    not3NF.add(i);
//                 }
//            }
//        }
//        if(not3NF.size()==0 )
//       {
//           System.out.println("Relation is in 3NF");
//           MyFirstForm.beta=1;
//       }
//       else
//       {
//            convert_3NF(not3NF,attribute,binaryFD);
//       }
//    }
//    public static void convert_3NF(ArrayList<Integer>not3NF ,String attribute[],int[][]binaryFD)
//    {
//        ArrayList<ArrayList<String>> relation= new ArrayList<ArrayList<String>>();
//        ArrayList<String>primary =new ArrayList<String>();
//        int k=(int)Math.pow(2,attribute.length)-1;
//        for(int i=0;i<not3NF.size();++i)
//        {
//            k=(k|binaryFD[not3NF.get(i)][1])^binaryFD[not3NF.get(i)][1];
//            k=k|binaryFD[not3NF.get(i)][0];
//            ArrayList<String>temp =new ArrayList<String>();
//            relation.add(temp);
//        }
//        for(int i=0;i<attribute.length;++i)
//        {
//            int p=(int)Math.pow(2,i);
//            if((k&p)==p)
//            {
//                primary.add(attribute[i]);
//            }
//            for(int j=0;j<not3NF.size();++j)
//            {
//                if((binaryFD[not3NF.get(j)][0] &p)==p || (binaryFD[not3NF.get(j)][1] &p)==p )
//                {
//                    relation.get(j).add(attribute[i]);
//                }
//            }
//
//        }
//        //System.out.println(primary);
//        relation.add(primary);
//        // for(int i=0;i<relation.size();++i)
//        // {
//        //     System.out.println(relation.get(i));
//        // }
//        
//        for(int i=0;i<relation.size();i++)
//        {
//            int p=0;
//            for(int j=0;j<relation.get(i).size();j++)
//            {
//               p=p|(int)Math.pow(2,Arrays.asList(attribute).indexOf(relation.get(i).get(j))); 
//            }
//           finalRelations.add(p);
//        }
//
//    }
//
//    
}