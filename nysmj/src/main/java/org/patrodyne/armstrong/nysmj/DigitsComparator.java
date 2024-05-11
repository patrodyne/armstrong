package org.patrodyne.armstrong.nysmj;

import java.util.Comparator;
import java.util.List;

public class DigitsComparator implements Comparator<List<Character>>
{
	@Override
	public int compare(List<Character> digits1, List<Character> digits2)
	{
		String number1 = toNumber(digits1);
		String number2 = toNumber(digits2);
		return number1.compareTo(number2);
	}

	private String toNumber(List<Character> digits1)
	{
		StringBuilder number = new StringBuilder();
		for ( Character digit : digits1 )
			number.append(digit);
		return number.toString();
	}
}
