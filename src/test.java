import java.util.ArrayList;

public class test {

	public static void main(String[] args) {
		ArrayList<Integer> values = new ArrayList<Integer>();
		values.add(0);
		values.add(0);
		values.add(0);
		values.add(0);
		values.add(0);
		
		String Word = "voice";
		String GW = "aloin";
		ArrayList<Character> WList = new ArrayList<>();
		 for (char c : Word.toCharArray()) {
	            WList.add(c);
	        }
		 ArrayList<Character> GWList = new ArrayList<>();
		 for (char c : GW.toCharArray()) {
	            GWList.add(c);
	        }
		
		 System.out.println(WList);
		 System.out.println(GWList);
		 
		 for(int i=0; i<5; i++) {
			 if((GWList.contains(WList.get(i))) && (WList.get(i) == GWList.get(i))) {
				 values.set(i, 2);
			 }
			 else if((GWList.contains(WList.get(i))) && (WList.get(i) != GWList.get(i))){
				 values.set(i, 1);
			 }
			 else if(GWList.contains(WList.get(i)) == false) {
				 values.set(i, 0);
			 }
		 }
		 
		 System.out.println(values);

	}

}
