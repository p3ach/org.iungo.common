package org.iungo.common.cli.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Simple arguments class primarily used for CLI arguments but can be used anywhere a list of arguments needs to be processed.
 * <p>The following arguments assuming that -config requires a value;
 * <p>-config file:///foo.txt -debug "hello world"
 * <p>Will return;
 * <p>[-debug] {-config=file:///foo.txt} [hello world] [-config]
 * <p>Methods are provided to return the flags, flags with values and non flags as Set, Map and Set respectively. Additionally there are methods to query individual flags etc.
 * @author Dick
 *
 */
public class Arguments {

	public static final String FLAG_PREFIX = "-";

	public static final String QUOTE = "\"";
	
	/**
	 * All non flags i.e. no prefix.
	 */
    private final List<String> nonFlags = new LinkedList<>();

    /**
     * All flags i.e. with prefix but without a value.
     */
    private final Set<String> flags = new HashSet<>();
    
    /**
     * All flags i.e. with prefix and with a value.
     */
    private final Map<String, String> flagsWithValue = new HashMap<>();
    
    /**
     * All flags which are expected to be followed by a value.
     */
    private final Set<String> flagsRequiringValue = new HashSet<>();

    private String flagPrefix = FLAG_PREFIX;
    
    private String quote = QUOTE;
    
    private List<String> arguments = Collections.emptyList();
    
    /*
     * Get/Set...
     */
    
	public String getFlagPrefix() {
    	return flagPrefix;
    }
	
	public void setFlagPrefix(final String flagPrefix) {
		this.flagPrefix = flagPrefix;
	}

    public String getQuote() {
    	return quote;
    }
	
	public void setQuote(final String quote) {
		this.quote = quote;
	}
    
    public List<String> getArguments() {
		return arguments;
	}
	
	public void setArguments(final List<String> arguments) {
		this.arguments = arguments;
	}
    
    /*
     * Non flags.
     */
  
	public List<String> getNonFlags() {
    	return Collections.unmodifiableList(nonFlags);
    }
    
	/*
	 * Flags.
	 */
	
    public Set<String> getFlags() {
    	return Collections.unmodifiableSet(flags);
    }
    
    public Boolean hasFlag(final String flag) {
    	return flags.contains(flag) || flagsWithValue.containsKey(flag);
    }
    
    /*
     * Flags with value.
     */
    
    public Map<String, String> getFlagsWithValue() {
    	return Collections.unmodifiableMap(flagsWithValue);
    }
    
    public Boolean hasFlagWithValue(final String flag) {
    	return flagsWithValue.containsKey(flag);
    }
    
    public String getValueForFlag(final String flag) {
    	return flagsWithValue.get(flag);
    }

    public String getValueForFlag(final String flag, final String valueIfNull) {
    	final String value = flagsWithValue.get(flag);
    	return (value == null ? valueIfNull : value);
    }
    
    public Set<String> getFlagsRequiringValue() {
    	return Collections.unmodifiableSet(flagsRequiringValue);
    }

    public void addFlagRequiringValue(final String flag) {
    	flagsRequiringValue.add(flag);
    }
    
    public void addFlagsRequiringValue(final Set<String> flags) {
    	for (String flag : flags) {
			addFlagRequiringValue(flag);
		}
    }
    
    /*
     * Parse.
     */

    public void parse() {
    	final Iterator<String> iterator = getArguments().iterator();
    	while (true) {
    		if (!iterator.hasNext()) {
    			break;
    		}
    		final String text = iterator.next();
    		if (text.startsWith(flagPrefix)) { // It is a flag.
    			if (flagsRequiringValue.contains(text)) { // We need a value...
    				if (!iterator.hasNext()) {
    					throw new UnsupportedOperationException(String.format("Flag [%s] requires a value.", text)); // No more arguments!
    				}
    				flagsWithValue.put(text, removeQuotes(iterator.next()));
    			} else { // We do not need a value.
    				flags.add(text);
    			}
    		} else { // It is a value.
    			nonFlags.add(removeQuotes(text));
    		}
    	}
    }

    /**
     * Remove the starting and ending QUOTE if they both exist.
     * @param text
     * @return
     */
    protected String removeQuotes(final String text) {
		if (text.startsWith(quote) && text.endsWith(quote)) {
			return text.substring(1, text.length()-1);
		} else {
			return text;
		}
    }
    
	@Override
	public String toString() {
		return String.format("%s %s %s %s %s", getArguments(), getFlags(), getFlagsWithValue(), getNonFlags(), getFlagsRequiringValue());
	}
    
}