package mihf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;



public class Main {
		
	
		public static void write(int[][] parking_area, int length, int width ) {
			
			for (int i = 0; i < length; i++) {
	            for (int j = 0; j <width; j++) {
	            		if(j==width-1) System.out.print(parking_area [i][j]);
	            		else System.out.printf(parking_area [i][j] + "	");
	                    }
	            	if(i==length-1) {}
	            	else System.out.printf("\n");

	            }
			
		}
		
		public static boolean check (int[][] parking_area, int column, int line , Car c, int length, int width) {
			for (int i = 0; i < c.getlength(); i++) {
	            for (int j = 0; j < c.getwidth(); j++) {
	            		if(j+column>=length || i+line>=width) return false;
	            		else if(parking_area [i+line][j+column]!=0) return false;
	                    }

	            }
		
		
			return true;
			
		} 
		
		
		public static int[][] put_in( int [][] parking_area, int column, int line , Car c) {
			
			for (int i = 0; i < c.getlength(); i++) {
	            for (int j = 0; j < c.getwidth(); j++) {
	            		parking_area [i+line][j+column]=c.getnumber();
	                    }

	            }
		
		
			return parking_area;
		}
	
		public static void main(String[] args) throws IOException{
			
			int length=5;
			int width=3;
			int number_of_cars=100;
			int counter=0;
			List<Car> cars = new ArrayList<Car>();			
			
			
			//input processing
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line = br.readLine();
			String [] temp;
			int number=1;
			while (line != null && cars.size() != number_of_cars) {
				if(counter==0) {temp=line.split("	",2);
								length=Integer.parseInt(temp[0]);
								width=Integer.parseInt(temp[1]);
								
								}
				else if(counter==1){
								number_of_cars=Integer.parseInt(line);
								}
				else if(counter>1){
						int a=0;
						int b=0;
						temp=line.split("	",2);
						a=Integer.parseInt(temp[0]);
						b=Integer.parseInt(temp[1]);
						
						cars.add(new Car(a,b,number));
						number++;
					}
				
				counter++;
				line = br.readLine();
				
			}
			
		
			
			//initialization of parking area
				
			int[][] parking_area = new int[length][width];
			for (int i = 0; i < length; i++) {
	            for (int j = 0; j <width; j++) {
	            		parking_area [i][j]=0;

	                }

	           }
			
			Collections.sort(cars, Collections.reverseOrder());
			int i=0;
			int j=0;
			boolean stop=false;
			while(cars.size()>0) {
				
			i=0;
			stop=false;
			while(parking_area[j][i]!=0) {
				j++;
				if(j==length) {i++;j=0;}
			}
			
			while ( i<width && 0<=cars.size()-1 && !stop) {
				if(	!check(parking_area,i,j,cars.get(0), length, width)) {
					
						boolean found=false;
						for (int f=0; !found; f++) {
							if (check(parking_area,i,j,cars.get(f), length, width)) {
								parking_area= put_in(parking_area,i,j,cars.get(f));
								cars.remove(f);
								stop=true;
								found=true;
							}
							else {cars.get(f).rotate();
								  if(check(parking_area,i,j,cars.get(f), length, width)) {
									  parking_area= put_in(parking_area,i,j,cars.get(f));
									  cars.remove(f);
									  stop=true;
									  found=true;
								  }
								  else { cars.get(f).rotate();
								  }
							
							}
						
						}
				
				
				}
					
				else {
					
					if( check(parking_area,i,j,cars.get(0), length, width)) {
						parking_area= put_in(parking_area,i,j,cars.get(0));
						i+=cars.get(0).getwidth();
						cars.remove(0);
						}
						else {stop=true;
						}
				}
				
				//write(parking_area,length,width);				
				
			}
			
			}
			
			
			//write output
			
			write(parking_area,length,width);
	        
			}
			

}

