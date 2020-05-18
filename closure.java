import java.io.*;
import java.lang.*;
import java.util.*;
public class closure
{   //assume there's an array containing names of attributes 
    //array containing Func Dep as a string  "A,B->C" or "A->B,C"
    static int n;
    static int m;
    public static void main(String[] args) 
    {
        input(10,5);  //calling without object
    }

    public static void calc_closure(String attribute[], String FD[])
    {
        int x=-1;
        int flag=-1;
        int cl[] = new int[n];
        int binaryclo[] =new int [n];
        String closure[] = new String[n];   //stores closures of all attributes
        ArrayList <ArrayList<Integer>> multipleFD =new ArrayList<ArrayList<Integer>>();
        int sizeArrlist=0;
        int i,j;
        String l="";
        for(i=0;i<n;i++)
        {
            closure[i] = ""+attribute[i];
            binaryclo[i]=(int)Math.pow(2,i);//add element itself to the closure
            cl[i]=0;
        }
        for(i=0;i<n;i++)
        { 
            for(j=0;j<m;j++)
            {
                if(FD[j].indexOf('-')!=-1)
                l = FD[j].substring(0,FD[j].indexOf('-'));
                if(l.indexOf(attribute[i])!=-1)
                cl[i]++;
            }
            System.out.println(cl[i]);
        }
        int p=0;
        int trial = 0;
        int checker = 0;
        while(p<n)
        {
            //System.out.print("y");
            while(cl[p]>0)
            {
                //System.out.print("y");
                for(i=0;i<n;i++)
                {   
                    for(j=0;j<m;j++)
                    {
                        x = FD[j].indexOf("-");
                        if(x==-1)
                        continue;
                        String y = FD[j].substring(0,x);
                        int z = y.indexOf(attribute[i]);
                        String str[] = y.split(",",n);
                        for(int k=0;k<str.length;k++)
                        {
                            if(closure[i].indexOf(str[k])==-1)
                            {   
                                flag = 1;
                                break;
                            }
                        }
                        if(str.length>1)//if multiple attributes then
                        {
                            int leftsum =0;
                            for(int k=0;k<str.length;++k)
                            {
                             int index = Arrays.asList(attribute).lastIndexOf(str[k]);
                             leftsum = leftsum | (int)Math.pow(2,index);
                            }
                            ArrayList<Integer> temp= new ArrayList<Integer>();//create array list and append to multipleFD
                            temp.add(leftsum);
                            temp.add(leftsum);
                            multipleFD.add(temp);
                            sizeArrlist++;//increase size
                            String y1 = FD[j].substring(x+2);
                            String str1[] = y1.split(",",n);
                            for(int k=0;k<str1.length;k++)
                            {
                                if(str.length>1)//add the attributes on right side and append to the array
                                {
                                    int index = Arrays.asList(attribute).lastIndexOf(str1[k]);
                                    int temp2= multipleFD.get(sizeArrlist-1).get(1) | (int)Math.pow(2,index);
                                    multipleFD.get(sizeArrlist-1).set(1,temp2);
                                }
                            }
                        }
                        if(flag==1)
                        {
                            flag=0;
                            continue;
                        }
                        else
                        {
                            trial++;
                            //cl[p]--;
                            String y1 = FD[j].substring(x+2);
                            String str1[] = y1.split(",",n);
                            for(int k=0;k<str1.length;k++)
                            {
                                if(str.length>1)//add the attributes on right side and append to the array
                                {
                                    int index = Arrays.asList(attribute).lastIndexOf(str1[k]);
                                    int temp2= multipleFD.get(sizeArrlist-1).get(1) | (int)Math.pow(2,index);
                                    multipleFD.get(sizeArrlist-1).set(1,temp2);
                                }
                                if(closure[i].indexOf(str1[k])==-1)//add if it is not present already in closure
                                {
                                    if(Arrays.asList(attribute).lastIndexOf(str1[k])!=-1)
                                    {
                                         int index = Arrays.asList(attribute).lastIndexOf(str1[k]);
                                         binaryclo[i]+=(int)Math.pow(2,index);
                                    }
                                    closure[i] = closure[i]+" "+str1[k];
                                }
                            }
                        }  
                    }
                    if(trial==0) //ADDED
                    {
                        checker = 1;
                        continue;
                    }
                    if(trial!=0)  //ADDED
                    {
                        cl[p]--;
                        trial = 0;
                    }
                }
                if(checker==1) //ADDED
                break;
            }
            p++;
            checker = 0;
        }
        display(closure,attribute,FD);
        candidateKey(binaryclo, attribute,FD,multipleFD);
    }
    
    public static void display(String ptr[], String attribute[], String FD[])
    {
        for(int i=0;i<ptr.length;i++)
        {
            System.out.println("["+attribute[i]+"+] : "+ptr[i]+" ");
        }
        System.out.println();
    }
    public static void input(int x,int y)   // x,y contain values of n,m
    {
        Scanner sc = new Scanner(System.in);
        n=x;
        m=y;
        String attribute[] = new String[n];
        String FD[] = new String[m];
        System.out.println("ENTER ATTRIBUTES");
        for(int i=0;i<n;i++)
        attribute[i] = sc.nextLine();
        System.out.println("ENTER FDs");
        for(int j=0;j<m;j++)
        FD[j]=sc.nextLine();
        calc_closure(attribute,FD);
        //keybin(attribute,FD);
    }

    public static void candidateKey(int ptr[], String attribute[],String FD[],ArrayList <ArrayList<Integer>> multipleFD){
        Vector <Vector<String>> ckey= new Vector<Vector<String>>();
        Vector <Vector<String>> superkey= new Vector<Vector<String>>();
        //System.out.print("x");
        for(int i=0;i<n;++i)//append individual elements to multipleFD array
        {
            ArrayList<Integer> temp= new ArrayList<Integer>();
            temp.add((int)Math.pow(2,i));
            temp.add(ptr[i]);
            multipleFD.add(temp);
        }
        for(int i=1;i<=n;++i)//no of elements in a candidate key
        {
           
            String data[] = new String[i]; 
            combinationUtil(attribute,data,0,n-1,0,i,ckey);//generates all combinations and stores in ckey
        }
        // for(int i=0;i<multipleFD.size();++i)
        // System.out.println(multipleFD.get(i)+" ");
        for(int i=0;i<ckey.size();i++)//check if they can help in getting all attributes
        {
            int ans=0;
            for(int j=0;j<ckey.get(i).size();j++)
            {
                ans=ans|ptr[Arrays.asList(attribute).indexOf(ckey.get(i).get(j))];
            }
            boolean change=true;
            while(change)//run till a change happens in any of the iteration
            {
                int temp=ans;
                for(int j=0;j<multipleFD.size();++j)
                {
                    if((ans & multipleFD.get(j).get(0))== multipleFD.get(j).get(0))//if element exists then check
                    {
                      ans=ans|multipleFD.get(j).get(1);
                    }
                }
                if(temp==ans)
                 change=false;
            }
            
            if(ans!= (int)(Math.pow(2,n)-1))//remove the ones that don't give all the attributes
            {
                ckey.removeElementAt(i);
                i--;
            }
        }
        System.out.println("Superkeys:");
        for(int i=0;i<ckey.size();++i)
        {
            superkey.add(ckey.elementAt(i));
            System.out.println(superkey.elementAt(i)+" ");
        } 
        for(int i=0;i<n;i++)//
        {
            int min=n;
            
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
        System.out.println("Candidate Keys : ");
        for(int i=0;i<ckey.size();++i)
        System.out.print(ckey.elementAt(i)+" ");
        int[] binsuper = new int [superkey.size()];
        for(int i=0;i<superkey.size();++i)//binary superkeys calculated
        {
            int key = 0;
            for(int j=0;j<superkey.get(i).size();++j)
            {
                int index = Arrays.asList(attribute).lastIndexOf(superkey.get(i).get(j));
                key = key | (int)Math.pow(2,index);
            }
            binsuper[i]=key;
        } 
        int bincandidate[]  = new int [ckey.size()];//binary candidate keys calculated
        for(int i=0;i<ckey.size();++i)
        {
            int key = 0;
            for(int j=0;j<ckey.get(i).size();++j)
            {
                int index = Arrays.asList(attribute).lastIndexOf(ckey.get(i).get(j));
                key = key | (int)Math.pow(2,index);
            }
            bincandidate[i]=key;
        }
        int Bin[][] = new int[m][2];
        keybin(attribute,FD,Bin);
        twoNF.check_2NF(Bin,bincandidate,attribute);
        System.out.println("\n"+"3 NF check");
        threeNF.check_3NF(Bin,bincandidate,binsuper,attribute);
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
    
    public static void keybin(String attribute[], String FD[],int Bin[][])
    {
        
        int d = 0;
        for(int i=0;i<m;i++)
        {
            int x = FD[i].indexOf("-");
            String y = FD[i].substring(0,x);
            for(int j=0;j<n;j++)
            {
                if(y.indexOf(attribute[j])!=-1)
                d = d|((int)Math.pow(2,j));
            }
            Bin[i][0] = (int) d;
            d=0;
            y = FD[i].substring(x+2);
            for(int j=0;j<n;j++)
            {
                if(y.indexOf(attribute[j])!=-1)
                d = d|((int)Math.pow(2,j));
            }
            Bin[i][1] = (int) d;
            d=0;
        }
        for(int i=0;i<m;i++)
        {
            System.out.println(Bin[i][0]+"->"+Bin[i][1]);
        }
    } 

    // Finding candidate Keys and super keys for the relations formed after decomposition
    public static void finalKeys(ArrayList<Integer>relation ,int[][]binaryFD ,String attribute[])// input :relation in binary format ,initial Fds in binary format ,all attributes
    {
        for(int i=0;i<relation.size();++i)
        {
            System.out.print("\n\n");
            System.out.println("Relation "+ (i+1));
            ArrayList <ArrayList<Integer>> FDcovered= new ArrayList <ArrayList<Integer>>();
            ArrayList<String> R= new ArrayList<String>();
           
           // System.out.println(relation.get(i));
            for(int j=0;j<attribute.length;++j)//check which all attributes are present in given relation
            {
                int p=(int)Math.pow(2,j);
                if((relation.get(i) & p)==p)
                {
                    R.add(attribute[j]);    
                }
            }
            System.out.println(R);
            String R1[]= new String[R.size()];//converting it to string array
            for(int j=0;j<R.size();j++)
            {
                R1[j]=R.get(j);
            }
            for(int j=0;j<binaryFD.length ;j++)
            {
                if((relation.get(i)&binaryFD[j][0])== binaryFD[j][0])//check if the left side attributes of fd are present in relation
                {
                    if((relation.get(i)&binaryFD[j][1])!=0)// check if they are present on right side
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
}