package digitalCookBook;
import java.util.ArrayList;
import java.util.Iterator;
public class Test {
	public static void main(String args[]) {
		 /*ArrayList<String> list = new ArrayList<String>();
	        list.add("beijing");
	        list.add("shanghai");
	        list.add("shanghai");
	        list.add("guangzhou");
	        list.add("shenzhen");
	        list.add("hangzhou");
	    /* System.out.println(list.get(1)==list.get(2));
	     System.out.println(list.get(1).equals(list.get(2)));
	     list.remove("shanghai");
	     System.out.println(list);
	     */
	    /* Iterator iterator = list.iterator();
	     int i = 0;
	     while(iterator.hasNext()) {
	    	 System.out.println(iterator.next());
	    	 i++;
	     }
	     System.out.print(i);
	     */
	     Ingredient hongshaorou = new Ingredient("hongshao",1.5,"kg");
	     System.out.println(hongshaorou.getAmount());
	     hongshaorou.setServeAmount(4);
	     hongshaorou.setTotalAmount();
	     System.out.println(hongshaorou.getTotalAmount());
	     Ingredient laziji = new Ingredient("lazi",1.0,"kg");
	     System.out.println(laziji.getAmount());
	}
}
