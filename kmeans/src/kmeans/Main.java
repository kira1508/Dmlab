package kmeans;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader buf = new BufferedReader(new FileReader("/home/kiran/DMLAB/dmlab2/prog9/data.csv"));
		Scanner in = new Scanner(System.in);
		int k;
		String line;
		//k = in.nextInt();
		Random rand = new Random();
		ArrayList<record> points = new ArrayList<>();
		ArrayList<ArrayList<record>> fincen = new ArrayList<>();
		while((line=buf.readLine())!=null) {
			
			String[] row = line.split(",");
			record temp = new record(Double.parseDouble(row[0]),Double.parseDouble(row[1]));
			points.add(temp);
			
		}
		ArrayList<record> c1 = new ArrayList<>();
		ArrayList<record> c2 = new ArrayList<>();
		ArrayList<record> c3 = new ArrayList<>();
		
		System.out.println("Enter K");
		k = in.nextInt();
		ArrayList<record> cent = new ArrayList<>();
		for(int i = 0;i<k;i++) {
			int ind = Math.abs(rand.nextInt()%points.size());
			cent.add(points.get(ind));
			
			
		}

		
		ArrayList<Double> dist = new ArrayList<>(); 
		for (int i =0;i<100;i++) {
			c1.clear();
			c2.clear();
			c3.clear();
			for(int j = 0;j<points.size();j++) {
				
				for(int m =0 ;m<k;m++) {
					double res = euclid(cent.get(m),points.get(j)); 
					dist.add(res) ;
					
				}
				double min = Collections.min(dist);
				if (min == dist.get(0))
					c1.add(points.get(j));
				if (min == dist.get(1))
					c2.add(points.get(j));
				if (min == dist.get(2))
					c3.add(points.get(j));
				dist.clear();
				
				}
			cent.add(calc(c1));
			cent.add(calc(c2));
			cent.add(calc(c3));
				
			}
		
		System.out.println("------0th cluster--------");
		for(record r:c1)
			System.out.println(r.x +" "+r.y);
		System.out.println("------1th cluster--------");
		for(record r:c2)
			System.out.println(r.x +" "+r.y);
		System.out.println("------2th cluster--------");
		for(record r:c3)
			System.out.println(r.x +" "+r.y);
		System.out.println("-----centroid------");
		for(int i=0;i<3;i++)
			System.out.println((cent.get(i)).x+","+(cent.get(i)).y);
		
	}

	private static record calc(ArrayList<record> c2) {
		// TODO Auto-generated method stub
		
		double x=0,y=0;
		for(record b:c2){
			x=x+b.x;
			y=y+b.y;
		}
		record a = new record(x/c2.size(),y/c2.size());
		
		return a;
		
	}

	private static double euclid(record cen, record poi) {
		// TODO Auto-generated method stub
		double p,q,r,s;
		p = poi.x;
		q= poi.y;
		r = cen.x;
		s = cen.y;
		double res = Math.pow((p-r),2)+Math.pow((q-s),2);
		return Math.sqrt(res);
	}

}
