//FILE NOT USED ANYWHERE IN MAIN CODE JUST FOR REFERENCE
import java.io.*;
import java.lang.*;
import java.util.*;
 
public class relationcd {
    public static void main(String args[])
    {
        ArrayList<Integer>relation =new ArrayList<Integer>();
        relation.add(7);
        relation.add(713);
        relation.add(58);
        relation.add(257);
        relation.add(11);
        int [][]binaryFD= new int[5][2];
            binaryFD[0][0]=3;
            binaryFD[0][1]=4;
    
            binaryFD[1][0]=9;
            binaryFD[1][1]=192;
    
            binaryFD[2][0]=10;
            binaryFD[2][1]=48;
    
            binaryFD[3][0]=1;
            binaryFD[3][1]=256;
    
            binaryFD[4][0]=128;
            binaryFD[4][1]=512; 
            
        String attribute[] = new String[10];
            attribute[0]="a";
            attribute[1]="b";
            attribute[2]="c";
            attribute[3]="d";
            attribute[4]="e";
            attribute[5]="f";
            attribute[6]="g";
            attribute[7]="h";
            attribute[8]="i";
            attribute[9]="j";
            
    
        finalKeys(relation, binaryFD, attribute);
    }
    public static void finalKeys(ArrayList<Integer>relation ,int[][]binaryFD ,String attribute[])
    {
       
        for(int i=0;i<relation.size();++i)
        {
            System.out.print("\n\n");
            System.out.println("Relation "+ (i+1));
            ArrayList <ArrayList<Integer>> FDcovered= new ArrayList <ArrayList<Integer>>();
            ArrayList<String> R= new ArrayList<String>();
           
           // System.out.println(relation.get(i));
            for(int j=0;j<attribute.length;++j)
            {
                int p=(int)Math.pow(2,j);
                if((relation.get(i) & p)==p)
                {
                    R.add(attribute[j]);    
                }
            }
            System.out.println(R);
            String R1[]= new String[R.size()];
            for(int j=0;j<R.size();j++)
            {
                R1[j]=R.get(j);
            }
            for(int j=0;j<binaryFD.length ;j++)
            {
                if((relation.get(i)&binaryFD[j][0])== binaryFD[j][0])
                {
                    if((relation.get(i)&binaryFD[j][1])!=0)
                    {
                        ArrayList<Integer> temp= new ArrayList<Integer>();
                        temp.add(relation.get(i)&binaryFD[j][0]);
                        temp.add(relation.get(i)&binaryFD[j][1]);
                        FDcovered.add(temp);
                    }
                }
            }
            System.out.print("FD :");
            if(FDcovered.size()==0)
             System.out.print("None");
            for(int j=0;j<FDcovered.size();++j)
            {
                System.out.println();
                ArrayList <String> Fdleft = new ArrayList <String>();
                ArrayList <String> Fdright = new ArrayList <String>();
                for(int k=0;k<attribute.length;k++)
                {
                    int p=(int)Math.pow(2,k);
                    if((FDcovered.get(j).get(0) & p)==p)
                    {
                        Fdleft.add(attribute[k]);    
                    }
                    if((FDcovered.get(j).get(1) & p)==p)
                    {
                        Fdright.add(attribute[k]);    
                    }
                }
                System.out.print(Fdleft.get(0));
                for(int k=1;k<Fdleft.size();k++)
                {
                    System.out.print(","+Fdleft.get(k));
                }
                System.out.print("->");
                System.out.print(Fdright.get(0));
                for(int k=1;k<Fdright.size();k++)
                {
                    System.out.print(","+Fdright.get(k));
                }
            }
            System.out.println();
           // System.out.println("FD "+FDcovered.size() +" R1 "+ R1.length);
            cal_key(FDcovered,attribute,R1,relation.get(i));
          // System.out.println("");
        }
    }
    public static void cal_key(ArrayList <ArrayList<Integer>> FDcovered ,String attribute[],String R1[],int relation)
    {
        Vector <Vector<String>> ckey= new Vector<Vector<String>>();
        Vector <Vector<String>> superkey= new Vector<Vector<String>>();
        int n1= attribute.length;
        int n2=R1.length;
        for(int i=1;i<=n2;++i)//no of elements in a candidate key
        {
            String data[] = new String[i]; 
            combinationUtil(R1,data,0,n2-1,0,i,ckey);//generates all combinations and stores in ckey
        }
        for(int i=0;i<ckey.size();i++)
        {
            int ans=0;
            for(int j=0;j<ckey.get(i).size();j++)
            {
                ans=ans|(int)Math.pow(2,Arrays.asList(attribute).indexOf(ckey.get(i).get(j)));
            }
           // System.out.println(ckey.get(i)+"="+ans+" ");
            boolean change=true;
            while(change)//run till a change happens in any of the iteration
            {
                int temp=ans;
                for(int j=0;j<FDcovered.size();++j)
                {
                    if((ans & FDcovered.get(j).get(0))== FDcovered.get(j).get(0))//if element exists then check
                    {
                      ans=ans|FDcovered.get(j).get(1);
                    }
                }
                if(temp==ans)
                 change=false;
            }
            
            if(ans!= relation )//remove the ones that don't give all the attributes
            {
                ckey.removeElementAt(i);
                i--;
            }
        }
        System.out.print("\nSuperkeys:  ");
        for(int i=0;i<ckey.size();++i)
        {
            superkey.add(ckey.elementAt(i));
            System.out.print(superkey.elementAt(i)+" ");
        } 
        for(int i=0;i<n2;i++)//
        {
            int min=n2;
            
            for(int j=0;j<ckey.size();++j)//for each attribute find the min size
            {
                // System.out.print(ckey.elementAt(j)+" ");
                if(ckey.get(j).lastIndexOf(attribute[i])!=-1)
                {
                    if(ckey.get(j).size() < min)
                    min=ckey.get(j).size();
                }
            }
            for(int j=0;j<ckey.size();++j)
            {
                if(ckey.get(j).lastIndexOf(attribute[i])!=-1)
                {
                    if(ckey.get(j).size() > min)//if a key with size greater than min size exists remove it
                    {
                     ckey.removeElementAt(j);
                     j--;
                    }
                }
            }
        }
        System.out.print("\nCandidate Keys : ");
        for(int i=0;i<ckey.size();++i)
        System.out.print(ckey.elementAt(i)+" ");
        }
    
    static void combinationUtil(String arr[], String data[], int start, int end, int index, int r,Vector <Vector<String>> ckey) 
    { 
        // Current combination is ready to be printed, print it 
        if (index == r) 
        { 
            Vector <String> temp= new Vector<String>();
            for (int j=0; j<r; j++) 
                temp.add(data[j]);
            ckey.add(temp);
            return; 
        } 
  
        // replace index with all possible elements. The condition 
        // "end-i+1 >= r-index" makes sure that including one element 
        // at index will make a combination with remaining elements 
        // at remaining positions 
        for (int i=start; i<=end && end-i+1 >= r-index; i++) 
        { 
            data[index] = arr[i]; 
            combinationUtil(arr, data, i+1, end, index+1, r,ckey); 
        } 
    } 
    
}
