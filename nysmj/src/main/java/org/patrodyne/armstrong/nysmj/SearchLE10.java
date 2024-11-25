package org.patrodyne.armstrong.nysmj;

import static java.lang.String.format;
import static org.patrodyne.armstrong.nysmj.DigitsComparator.toNumber;

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
		for ( Long number = 100L; number < 2000; ++number )
		{
			char[] digitArray = number.toString().toCharArray();
			List<Character> digitList = new ArrayList<>();
			for ( char digit : digitArray )
				digitList.add(digit);
			Collections.sort(digitList);
			digitListSet.add(digitList);
		}
		
		List<Delta> deltaList = new ArrayList<>();
		Delta deltaPrev = new Delta(null);
		
		// Show a unique list of digits representing the 
		// number's digits without regard to the original order.
		long sequence = 0;
		for ( List<Character> digitList : digitListSet )
		{
			logger.trace("Sequence {}: {}", ++sequence, digitList);
			Delta deltaNext = new Delta(digitList);
			deltaNext.delta(deltaPrev, 0);
			deltaList.add(deltaNext);
			deltaPrev = deltaNext;
		}
		
		logger.info("Delta Analysis\n");

		doDeltas(deltaList, 1);
		doDeltas(deltaList, 2);
		doDeltas(deltaList, 3);

		for ( Delta delta : deltaList )
		{
			logger.info("Delta: {}", delta.toString());
		}
		
		logger.info("Generator\n");
		for (int i7=0; i7 < 7 ; ++i7)
			for (int i8=i7+1; i8 < 8 ; ++i8)
				for (int i9=i8+1; i9 < 9; ++i9)
					for (int i10=i9+1; i10 < 10; ++i10) 
						logger.info("{}{}{}{}", i7, i8, i9, i10);
	}

	private void doDeltas(List<Delta> deltaList, int index)
	{
		Delta deltaPrev;
		deltaPrev = new Delta(null);
		for ( Delta deltaNext : deltaList )
		{
			deltaNext.delta(deltaPrev, index);
			deltaPrev = deltaNext;
		}
	}
	
	private class Delta
	{
		private long digits;
		private long[] deltas = new long[4];
		
		public Delta(List<Character> digitList)
		{
			if ( digitList != null )
				digits = toNumber(digitList);
			else
			{
				digits = 0L;
				for ( int index=0; index < deltas.length; ++index )
					deltas[index] = 0L;
			}
		}
		
		public void delta(Delta deltaPrev, int index)
		{
			if ( index == 0 )
				this.deltas[index] = this.digits - deltaPrev.digits;
			else
				this.deltas[index] = this.deltas[index-1] - deltaPrev.deltas[index-1];
		}
		
		@Override
		public String toString()
		{
			return format("%+08d\t%+08d\t%+08d\t%+08d\t%+08d", digits, deltas[0], deltas[1], deltas[2], deltas[3]);
		}
	}
}
