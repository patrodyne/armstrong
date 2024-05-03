package org.patrodyne.armstrong.nysmj;

import java.util.Map.Entry;
import java.util.Properties;

/**
 * A mathematical research tool for Armstrong numbers.
 *
 * @author Rick O'Sullivan
 */
public class Main
{
	public static final String PROGRAM_NAME = "Main";
	public static final String KEY_HELP = "HELP";
	public static final String KEY_SOURCE = "SOURCE";
	public static final String KEY_TARGET = "TARGET";
	public static final String KEY_SOURCE_TYPE = "SOURCETYPE";
	public static final String KEY_TARGET_TYPE = "TARGETTYPE";

	/**
	 * Launch the application using command line options to specify
	 * the file conversion options. Throwables are trapped for error
	 * handling.
	 *
	 * @param args Command line arguments.
	 */
	public static void main(String[] args)
	{
		try
		{
			Properties options = parseOptions(args);
			if ( !options.containsKey(KEY_HELP) )
			{
				validateOptions(options);
				Main main = new Main();
				main.execute(options);
			}
			else
				showUsage();
		}
		catch (Throwable t)
		{
			errorHandler(t);
		}
	}

	private void execute(Properties options)
	{
		Command command = null;
		switch ( options.getProperty(KEY_SOURCE_TYPE).toLowerCase() )
		{
			case "search":
				switch ( options.getProperty(KEY_TARGET_TYPE).toLowerCase() )
				{
					case "le10":
						command = new SearchLE10();
						break;
					case "le61":
						command = new SearchLE61();
						break;
				}
				break;
		}
		if ( command != null )
			command.execute(options);
		else
			throw new UnsupportedOperationException("review options");
	}

	/**
	 * Parse CLI options into a {@link Properties} instance.
	 * 
	 * @param args The CLI arguments.
	 * 
	 * @return The CLI arguments as {@link Properties}
	 */
	private static Properties parseOptions(String[] args)
	{
		/** Represents the command line options as a dictionary of properties. */
		Properties options = new Properties();

		// Parse CLI arguments into a set of property options.
		for (int i=0; i < args.length; ++i)
		{
			String[] nvp = args[i].split("=", 2);
			if ( nvp.length == 2 )
				options.put(nvp[0].toUpperCase(), nvp[1]);
			else if ( nvp.length == 1 )
			{
				// A single value option is 'help'
				if ( nvp[0].toUpperCase().equals("HELP"))
					options.put(nvp[0].toUpperCase(), "");
				else
					options.put(nvp[0].toUpperCase(), "");
			}
			else
				throw new IllegalArgumentException("invalid option at position "+(i+1)+": "+args[i]);
		}

		return options;
	}

	/**
	 * Validate option properties.
	 * 
	 * @param options The command line options.
	 */
	private static void validateOptions(Properties options)
	{
		if ( !options.containsKey(KEY_SOURCE) )
			throw new IllegalArgumentException("Usage: Source option is required.");
		else
		{
			String source = options.getProperty(KEY_SOURCE);
			if ( source == null )
				throw new IllegalArgumentException("Usage: Source value is required.");
		}

		if ( !options.containsKey(KEY_SOURCE_TYPE) )
			options.put(KEY_SOURCE_TYPE, "search");
		else
		{
			String sourceType = options.getProperty(KEY_SOURCE_TYPE);
			if ( sourceType == null || !sourceType.equalsIgnoreCase("le10"))
				throw new IllegalArgumentException("Usage: sourceType must be le10.");
		}

		if ( !options.containsKey(KEY_TARGET) )
			options.put(KEY_TARGET, "stdout");
		else
		{
			String target = options.getProperty(KEY_TARGET);
			if ( target == null )
				throw new IllegalArgumentException("Usage: Target value is required.");
		}

		if ( !options.containsKey(KEY_TARGET_TYPE) )
			options.put(KEY_TARGET_TYPE, "le10");
		else
		{
			String targetType = options.getProperty(KEY_TARGET_TYPE);
			if ( targetType == null || !(targetType.equalsIgnoreCase("le10") || targetType.equalsIgnoreCase("le61")) )
				throw new IllegalArgumentException("Usage: targetType must be le10|le61.");
		}
	}

	/**
	 * Handle throwable exceptions by 1) printing the stack trace to the
	 * standard error stream and 2) in a windowing system, display the stack
	 * trace in a message dialog.
	 *
	 * @param t A throwable exception
	 */
	protected static void errorHandler(Throwable t)
	{
		StackTraceElement top = t.getStackTrace()[0];
		show(t.getClass().getSimpleName()+": "+t.getMessage()+", File="+top.getFileName()+", Line="+top.getLineNumber());
		if ( t.getMessage().startsWith("Usage:") )
			showUsage();
	}

	/**
	 * <p>Print the options for this application. Options are echoed to
	 * confirm the invocation plan.</p>
	 *
	 * @param options A dictionary of command line arguments.
	 *
	 * @see #show(Object)
	 */
	public static void showOptions(Properties options)
	{
		for ( Entry<Object, Object> option : options.entrySet())
		{
			if ( option.getValue() != null )
				show(option.getKey()+"="+option.getValue());
			else
				show(option.getKey());
		}
	}

	/**
	 * <p>Show the usage for this program. Provides a brief explanation
	 * of the command line interface.</p>
	 *
	 * @see #show(String)
	 */
	public static void showUsage()
	{
		show();
		show("Research Amstrong Numbers");
		show("usage: " + PROGRAM_NAME + " [options]");
		show();
		show("options:");
        show("  help - show this information.");
        show("  source=search");
        show("  sourceType=le10");
        show("  target=search");
        show("  targetType=le10|le61");
	}

	/**
	 * <p>Show an object to the console. Converts an object to its
	 * standard string representation an invokes {@link CommandLI#show(String)}.</p>
	 *
	 * <p>Null objects are ignored.</p>
	 *
	 * @param obj The object to show.
	 */
	public static void show(Object obj)
	{
		if ( obj != null )
			show(obj.toString());
	}

	/** Show empty text. This usually results with a blank line. */
	public static void show() { show(""); }

	/**
	 * <p>Show text to the console. Uses {@link log} to output a message at
	 * the INFO level. Typically, the logger is configured to send informational
	 * messages to the console but may be configured to append messages to a log
	 * file for scheduled jobs.</p>
	 *
	 * @param text The string to output.
	 */
	public static void show(String text)
	{
		System.out.println(text);
	}
}
