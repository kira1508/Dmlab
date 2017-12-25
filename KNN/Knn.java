import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;



public class Knn {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<record> points = new ArrayList<>();
		BufferedReader buf = new BufferedReader(new FileReader("/home/kiran/GE/JAVA/6/data.csv"));
		Scanner in = new Scanner(System.in);
		int x,y,z;
		int k =3;
		String line;
		//k = in.nextInt();
		Random rand = new Random();
		System.out.println("Enter the three points");
		x =in.nextInt();
		y=in.nextInt();
		z=in.nextInt();
		double sum;
		ArrayList<ArrayList<record>> fincen = new ArrayList<>();
		while((line=buf.readLine())!=null) {
			
			String[] row = line.split(",");
			sum = ((x-Double.parseDouble(row[0]))*(x-Double.parseDouble(row[0])) + (y-Double.parseDouble(row[1]))*(y-Double.parseDouble(row[1]))+(z-Double.parseDouble(row[2]))*(z-Double.parseDouble(row[2])));
			record temp = new record(sum,Double.parseDouble(row[0]),Double.parseDouble(row[1]),Double.parseDouble(row[2]),row[3]);
			points.add(temp);
			
		}
		Collections.sort(points, new Comparator<record>() {
			@Override
			public
			int compare (record p1,record p2) {
				if(p1.dist==p2.dist)
					return 0;
				else if (p1.dist>p2.dist) {
					return 1;
				}
				else
					return -1;
				
			}
		});
		int[] a = new int[3];
		for(record e:points) {
			if (k==0)
				break;
			System.out.println(e.dist +" "+e.x+" "+e.y+" "+e.z+" "+e.cla);
			k--;
			String  c = e.cla;
			if(c.equals("A")) {
				a[0]++;
			}
			if(c.equals("B")) {
				a[1]++;
			}
			if(c.equals("B")) {
				a[2]++;
			}
			
		}
		int res =Math.min(a[0], Math.min(a[1], a[2]));
		if (res == a[0])
		System.out.println("Given points belong to A");
		if (res == a[1])
			System.out.println("Given points belong to B");
		if (res == a[2])
			System.out.println("Given points belong to C");
	}

}
