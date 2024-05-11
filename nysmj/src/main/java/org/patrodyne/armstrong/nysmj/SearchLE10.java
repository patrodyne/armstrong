package org.patrodyne.armstrong.nysmj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Search numbers from 100 to less than the first 10-digit number for
 * Armstrong criteria.
 * 
 * <p>This prototype examines the sequence of unique combination of digits
 * for numbers in a limited range. The goal is to find a sequence generator
 * for such combinations; thus, reducing the search set.<p>
 */
public class SearchLE10 implements Command
{
	private static final Logger logger = LoggerFactory.getLogger(SearchLE10.class);

	@Override
	public void execute(Properties options)
	{
		// Convert numbers into a set of ordered digits.
		Set<List<Character>> digitListSet = new TreeSet<>(new DigitsComparator());
		for ( Long number = 100L; number < 1000; ++number )
		{
			char[] digitArray = number.toString().toCharArray();
			List<Character> digitList = new ArrayList<>();
			for ( char digit : digitArray )
				digitList.add(digit);
			Collections.sort(digitList);
			digitListSet.add(digitList);
		}
		
		// Show a unique list of digits representing the 
		// number's digits without regard to the original order.
		long sequence = 0;
		for ( List<Character> digitList : digitListSet )
		{
			logger.info("Sequence {}: {}", ++sequence, digitList);
		}
	}
}
