import java.util.*;
public class bcNF
{
    static int count = 0;
    static ArrayList<Integer> relation = new ArrayList<Integer>();
    public static void check_BCNF(int[] candidate,int[] superkey,int[][] FD,int[][] eachFD)
    {
        // = new int[FD.length][3];
        for(int i=0;i<FD.length;i++)
        {
            eachFD[i][0] = FD[i][0];
            eachFD[i][1] = FD[i][1];
            eachFD[i][2] = FD[i][2];
        }
        for(int i=0;i<eachFD.length;i++)
        {
                if(eachFD[i][2]==3)
                {
                        for(int j=0;j<superkey.length;j++)
                        {
                            if(eachFD[i][0]==superkey[j])
                            {
                                eachFD[i][2]=4;
                                count++;
                                break;
                            }
                        }
                }
        }
        if((FD.length-count)==0)
        {
            System.out.println("Relation is in BCNF");
            closure.gamma = 1;
            return;
        }
        else
        {
            decompose(eachFD);
        }
        //return eachFD;
    }
    public static void bcNFmode(int FD[][],int candidate[], int[] superkey)
    {
        int FD1[][] = new int[FD.length][3];
        for(int i=0;i<FD1.length;i++)
        {
            FD1[i][0] = FD[i][0];
            FD1[i][1] = FD[i][1];
            FD1[i][2] = 3;
        }
        int eachFD[][] = new int[FD.length][3];
        check_BCNF(candidate,superkey,FD1,eachFD);
    }
    public static void decompose(int eachFD[][])
    {
        int FD1[][] = new int[count][2];
        int x = 0;
        int k=0;
        for(int i=0;i<eachFD.length;i++)
        {
            x=0;
            if(eachFD[i][2]==3)
            {
                x = x|eachFD[i][0]|eachFD[i][1];
                relation.add(x);
            }
        }
        for(int i=0;i<eachFD.length;i++)
        {
            if(eachFD[i][2]==4)
            {
                FD1[k][0] = eachFD[i][0];
                FD1[k][1] = eachFD[i][1];
                x = x|FD1[k][0]|FD1[k][1];
                k++;
            }
        }
        
        relation.add(x);
        
        
        //System.out.println(relation);
    }
}