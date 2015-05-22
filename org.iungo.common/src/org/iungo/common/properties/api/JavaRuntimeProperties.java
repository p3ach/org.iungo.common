package org.iungo.common.properties.api;

public class JavaRuntimeProperties {

	private static final Runtime runtime = Runtime.getRuntime();
	
	public static final JavaRuntimeProperties instance = new JavaRuntimeProperties();
	
	public Integer getAvailableProcessors() {
		return runtime.availableProcessors();
	}
	
	public String getProcessors() {
		return String.format("Processors : Available [%d]", getAvailableProcessors());
	}
	
	public Long getFreeMemory() {
		return runtime.freeMemory();
	}
	
	public Long getMaxMemory() {
		return runtime.maxMemory();
	}
	
	public Long getTotalMemory() {
		return runtime.totalMemory();
	}
	
	public String getMemory() {
		return String.format("Memory : Total [%d] Free [%d] Max [%d]", getTotalMemory(), getFreeMemory(), getMaxMemory());
	}

	@Override
	public String toString() {
		return String.format("%s\n%s", getProcessors(), getMemory());
	}
}
