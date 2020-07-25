package mihf;

import java.util.Comparator;

public class Car implements Comparable<Car> {
	int width;
	int length;
	int number;
	
	public Car(int a, int b, int c)  {
		if(a>b) {length=a;
		width=b;}
		else{width=a;
		length=b;}
		number=c;
	}
	
	public int getwidth() {
        return width;
    }
	public int getlength() {
		return length;
	}
	
	public int getnumber() {
		return number;
	}
	
	public void rotate() {
		int temp=width;
		width=length;
		length=temp;
	}

	public int compareTo(Car car) {
		int compareage=((Car)car).getlength();
		return (this.length)-compareage;
	}

}
