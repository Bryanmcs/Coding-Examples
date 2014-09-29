package lab10p;

public class Lab10 {
	
	public static void main(String []  args){
		FileIO reader = new FileIO(); 
	    String[] lat = reader.load("long.txt");
	    String[] lon = reader.load("lat.txt");
	    
	    double[] lati=new double [lat.length];
	    double[] longi=new double [lon.length];
	    
	    for(int i=0;i<longi.length;i++){
	    	longi[i]=Double.parseDouble(lon[i]);
	    	lati[i]=Double.parseDouble(lat[i]);
	    }
	    
	    double[][] dis=new double[lon.length][lon.length];
	    
	    for(int i=0;i<lon.length;i++){
	    	for(int j=0;j<lon.length;j++){
	    		dis[i][j]=getDistance(lati[i],longi[i],lati[j],longi[j]);
	    	}
	    }
	    
	    
	   
	    boolean [] visit=new boolean [lat.length];
	   // visit[0]=true;
	    boolean jit=false;
	    int [] path=new int [lat.length];
	    
	    int step=0;
	    double distance=77777777;
	    
	    for(int k=0;k<dis.length;k++){
	    	
	    	double cDis=0;
	    	int cStep=k;
	    	int [] cPath=new int[dis.length];
	    	visit[k]=true;
	    
	    	for(int i=0;i<dis.length;i++){
	    	
	    		if(i==79){
	    		visit[k]=false;
	    		}
	    			double small=1000000;
	    			int smallPos=0;
	    			for(int j=0;j<dis.length;j++){
	    				double sml=dis[cStep][j];
	    				if(visit[j]==false){
	    					if(sml<small&&sml!=0){
	    						small=sml;
	    						smallPos=j;
	    					}
	    				}
	    			}
	    			visit[cStep]=true;
	    			cDis+=small;
	    			cStep=smallPos;
	    	
	    	
	    			cPath[i]=cStep;
	    	}
	    	
	    	for(int l=0;l<visit.length;l++){
	    		visit[l]=false;
	    	}
	    	
	    	if(distance>cDis){
	    		distance=cDis;
	    		for(int t=0;t<path.length;t++){
	    			path[t]=cPath[t];
	    		}
	    	}
	    }
	    
	    ///Implememnt 2opt
	    int [] array=new int[path.length];
	    
	    for(int i=0;i<path.length;i++){
	    	array[i]=path[i];
	    }
	    
	    Twopt(array, dis, distance);
	    
	    
	    ///////////////
	    for(int i=0;i<path.length;i++){
	    	System.out.print((array[i]+1)+".");
	    	
	    }
	    System.out.println();
	    System.out.println(distance);
	    
	}
	
	
	
	
	
	
public static double getDistance(double a, double b,double c,double d){
	
		
		double lat=Math.toRadians(c-a);
		double longi=Math.toRadians(d-b);
		double r=6371;
		a=Math.toRadians(a);
		c=Math.toRadians(c);
		
		double dist=haversin(lat)+(Math.cos(a)*Math.cos(c)*haversin(longi));
		double ca = 2 * Math.atan2(Math.sqrt(dist),Math.sqrt(1 - dist));
		double a2=ca*r;
		return a2;
		
	}
	
	public static double haversin(double a){
		double c=a;
		c=Math.sin(c/2)*Math.sin(c/2);
		return c;
	}
	
	public static void Twopt(int [] farray, double[][] array1,double bestDistance){
		int m=1;
		int [] array=new int[farray.length];
		for(int i=0;i<farray.length;i++){
			array[i]=farray[i];
		}
		
		while(m<array.length-1){
			double distance=0;
			int temp=array[m];
			array[m]=array[m+1];
			array[m+1]=temp;
			
			for(int h=0;h<array.length-1;h++){
				distance+=array1[array[h]][array[h+1]];
			}
			distance += array1[array[array.length - 1]][array[0]];
			if(distance<bestDistance){
				for(int i=0;i<array.length;i++){
					farray[i]=array[i];
				}
				bestDistance=distance;
			}
			else {
				for(int i=0;i<array.length;i++){
					array[i]=farray[i];
				}
			}
			m++;
		}
	}

}
