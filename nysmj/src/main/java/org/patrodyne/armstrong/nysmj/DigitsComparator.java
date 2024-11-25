package org.patrodyne.armstrong.nysmj;

import java.util.Comparator;
import java.util.List;

public class DigitsComparator implements Comparator<List<Character>>
{
	@Override
	public int compare(List<Character> digits1, List<Character> digits2)
	{
		Long number1 = toNumber(digits1);
		Long number2 = toNumber(digits2);
		return number1.compareTo(number2);
	}

	public static Long toNumber(List<Character> digits)
	{
		StringBuilder number = new StringBuilder();
		for ( Character digit : digits )
			number.append(digit);
		return Long.parseLong(number.toString());
	}
}
