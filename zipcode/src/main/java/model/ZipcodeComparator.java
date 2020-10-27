package model;


import java.util.Comparator;

public class ZipcodeComparator implements Comparator<Zipcode> {
	
	  public int compare(Zipcode interval1, Zipcode interval2) {
		    if (interval1.getMinRange() > interval2.getMinRange())
		      return 1;
		    else
		      return -1;
		  }
}
