import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class aggregate {
	static ArrayList<String[]> rows = new ArrayList<>();

	public static void main(String args[]) throws IOException {
		BufferedReader buf = new BufferedReader(
				new FileReader("/home/kiran/DMLAB/DataMining-Lab-master/AggregationDiscretization/data.csv"));
		String line;
		Scanner in = new Scanner(System.in);

		while ((line = buf.readLine()) != null) {

			String[] temp = line.split(",");
			rows.add(temp);

		}

		int colnum, ch;
		int avg;
		// freqword;

		while (true) {
			System.out.println("Enter the colnum");
			colnum = in.nextInt();
			System.out.println("Enter 1 to Aggregate \n2 to sampling \n3 to discretize");
			ch = in.nextInt();
			switch (ch) {
			case 1:
				avg = aggregate(colnum);
				System.out.println("MAx of the Attribute is " + avg);
				break;
			case 2:
				sampling(colnum);
				break;
			case 3:
				discret(colnum);

			}
		}

	}

	private static void discret(int colnum) {
		// TODO Auto-generated method stub

		int max = 0, min = 9999;
		for (int i = 0; i < rows.size(); i++) {
			int tem = Integer.parseInt(rows.get(i)[colnum]);
			if (tem > max)
				max = tem;
			if (min > tem)
				min = tem;
		}
		int mean = min + max / 2;
		for (int i = 0; i < rows.size(); i++) {

			int c = Integer.parseInt(rows.get(i)[colnum]);
			if (c > min && c < mean) {
				System.out.println(c + " lies between " + min + " and " + mean);
			} else {
				System.out.println(c + " lies between " + mean + " and " + max);
			}

		}

	}

	private static void sampling(int colnum) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		ArrayList<Integer> buck1 = new ArrayList<>();
		ArrayList<Integer> buck2 = new ArrayList<>();
		ArrayList<Integer> buck3 = new ArrayList<>();

		for (int j = 0; j < rows.size(); j++) {

			int ind = Math.abs(rand.nextInt() % 3);
			System.out.println(ind + " Index");
			if (ind == 0)
				buck1.add(Integer.parseInt(rows.get(j)[colnum]));
			else if (ind == 1)
				buck2.add(Integer.parseInt(rows.get(j)[colnum]));
			else
				buck3.add(Integer.parseInt(rows.get(j)[colnum]));
		}
		for (int i = 0; i < rows.size(); i++) {
			// System.out.println(rows.get(i)[colnum]+" value" );
			int c = Integer.parseInt(rows.get(i)[colnum]);
			if (buck1.contains(c)) {
				System.out.println("Record " + i + " Belongs type 1");
			} else if (buck2.contains(c)) {
				System.out.println("Record " + i + " Belongs type 2");
			} else
				System.out.println("Record " + i + " Belongs type 3");

		}

	}

	private static int aggregate(int colnum) {
		// TODO Auto-generated method stub
		int max = 0;
		for (int i = 0; i < rows.size(); i++) {
			int tem = Integer.parseInt(rows.get(i)[colnum]);
			if (tem > max)
				max = tem;
		}
		return max;
	}

}
