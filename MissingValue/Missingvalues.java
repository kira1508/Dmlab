import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Missingvalues {
	static ArrayList<String[]> rows = new ArrayList<>();

	public static void main(String args[]) throws IOException {

		BufferedReader buf = new BufferedReader(
				new FileReader("/home/kiran/DMLAB/DataMining-Lab-master/ReplaceMissingValues/csvdata.csv"));
		String line;
		Scanner in = new Scanner(System.in);

		while ((line = buf.readLine()) != null) {

			String[] temp = line.split(",");
			rows.add(temp);

		}
		int colnum, ch;
		int avg;
		String freqword;

		while (true) {
			System.out.println("Enter the colnum");
			colnum = in.nextInt();
			System.out.println("Enter 1 to numeric attribute \n 2 to text attribute");
			ch = in.nextInt();
			switch (ch) { 
			case 1:
				avg = avgsum(colnum);
				for (String[] e : rows) {
					if (e[colnum].isEmpty()) {
						e[colnum] = String.valueOf(avg);
					}
				}
				display();
				break;
			case 2:
				freqword = freq(colnum);
				for (String[] e : rows) {
					if (e[colnum].isEmpty()) {
						e[colnum] = freqword;
					}
				}
				display();
				break;

			}
		}

	}

	private static void display() {
		// TODO Auto-generated method stub
		for (String[] line : rows) {
			System.out.print("len: " + line.length + "\t");
			for (String word : line) {
				System.out.print(word + "\t");
			}
			System.out.println();
		}

	}

	private static String freq(int colnum) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> freq = new HashMap<>();
		int max = 0;
		for (String[] e : rows) {
			if (!e[colnum].isEmpty()) {
				if (freq.containsKey(e[colnum])) {
					int a = freq.get(e[colnum]);
					freq.put(e[colnum], a + 1);
				} else {
					freq.put(e[colnum], 1);
				}
			}

		}
		String maxword = null;
		for (String e : freq.keySet()) {
			int num = freq.get(e);
			if (num > max) {
				max = num;
				maxword = e;
			}
		}

		return maxword;
	}

	private static int avgsum(int colnum) {
		// TODO Auto-generated method stub
		int avg = 0, sum = 0;

		for (String[] e : rows) {
			if (!e[colnum].isEmpty()) {
				sum = sum + Integer.parseInt(e[colnum]);
			}
		}
		return sum / rows.size();
	}

}
