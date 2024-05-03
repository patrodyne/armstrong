package org.patrodyne.armstrong.nysmj;

import java.util.Properties;

/**
 * Interface to execute commands.
 * 
 * @author Rick O'Sullivan
 */
public interface Command
{
	public void execute(Properties options);
}
