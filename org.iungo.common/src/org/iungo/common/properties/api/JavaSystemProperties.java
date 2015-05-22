package org.iungo.common.properties.api;

public class JavaSystemProperties extends IungoProperties {

	private static final long serialVersionUID = 1L;
	
	public static final String JAVA_VERSION = "java.version"; // Java Runtime Environment version
	public static final String JAVA_VENDOR = "java.vendor"; // Java Runtime Environment vendor
	public static final String JAVA_VENDOR_URL = "java.vendor.url"; // Java vendor URL
	
	public static final String JAVA_HOME = "java.home"; // Java installation directory
	
	public static final String JAVA_VM_SPECIFICATION_VERSION = "java.vm.specification.version"; // Java Virtual Machine specification version
	public static final String JAVA_VM_SPECIFICATION_VENDOR = "java.vm.specification.vendor"; // Java Virtual Machine specification vendor
	public static final String JAVA_VM_SPECIFICATION_NAME = "java.vm.specification.name"; // Java Virtual Machine specification name
	
	public static final String JAVA_VM_VERSION = "java.vm.version"; // Java Virtual Machine implementation version
	public static final String JAVA_VM_VENDOR = "java.vm.vendor"; // Java Virtual Machine implementation vendor
	public static final String JAVA_VM_NAME = "java.vm.name"; // Java Virtual Machine implementation name
	
	public static final String JAVA_SPECIFICATION_VERSION = "java.specification.version"; // Java Runtime Environment specification version
	public static final String JAVA_SPECIFICATION_VENDOR = "java.specification.vendor"; // Java Runtime Environment specification vendor
	public static final String JAVA_SPECIFICATION_NAME = "java.specification.name"; // Java Runtime Environment specification name
	
	public static final String JAVA_CLASS_VERSION = "java.class.version"; // Java class format version number
	public static final String JAVA_CLASS_PATH = "java.class.path"; // Java class path
	public static final String JAVA_LIBRARY_PATH = "java.library.path"; // List of paths to search when loading libraries
	public static final String JAVA_IO_TMPDIR = "java.io.tmpdir"; // Default temp file path
	public static final String JAVA_COMPILER = "java.compiler"; // Name of JIT compiler to use
	public static final String JAVA_EXT_DIRS = "java.ext.dirs"; // Path of extension directory or directories
	
	public static final String OS_NAME = "os.name"; // Operating system name
	public static final String OS_ARCH = "os.arch"; // Operating system architecture
	public static final String OS_VERSION = "os.version"; // Operating system version
	
	public static final String FILE_SEPARATOR = "file.separator"; // File separator ("/" on UNIX)
	public static final String PATH_SEPARATOR = "path.separator"; // Path separator (":" on UNIX)
	public static final String LINE_SEPARATOR = "line.separator"; // Line separator ("\n" on UNIX)
	
	public static final String USER_NAME = "user.name"; // User's account name
	public static final String USER_HOME = "user.home"; // User's home directory
	public static final String USER_DIR = "user.dir"; // User's current working directory

	public static final JavaSystemProperties instance = new JavaSystemProperties();
	
	public JavaSystemProperties() {
		super(System.getProperties());
	}
	
	/*
	 * Java.
	 */
	
	public String getJavaVersion() {
		return getProperty(JAVA_VERSION);
	}
	
	public String getJavaVendor() {
		return getProperty(JAVA_VENDOR);
	}
	
	public String getJavaVendorURL() {
		return getProperty(JAVA_VENDOR_URL);
	}
	
	public String getJavaHome() {
		return getProperty(JAVA_HOME);
	}
	
	public String getJava() {
		return String.format("Java : Version [%s] Vendor [%s] Vendor URL [%s] Installation Folder [%s]", getJavaVersion(), getJavaVendor(), getJavaVendorURL(), getJavaHome());
	}
	
	/*
	 * Java VM Specification.
	 */
	
	public String getJavaVMSpecificationVersion() {
		return getProperty(JAVA_VM_SPECIFICATION_VERSION);
	}
	
	public String getJavaVMSpecificationVendor() {
		return getProperty(JAVA_VM_SPECIFICATION_VENDOR);
	}
	
	public String getJavaVMSpecificationName() {
		return getProperty(JAVA_VM_SPECIFICATION_NAME);
	}
	
	public String getJavaVMSpecification() {
		return String.format("Java VM Specificationm : Version [%s] Vendor [%s] Name [%s]", getJavaVMSpecificationVersion(), getJavaVMSpecificationVendor(), getJavaVMSpecificationName());
	}
	
	/*
	 * Java VM.
	 */
	
	public String getJavaVMVersion() {
		return getProperty(JAVA_VM_VERSION);
	}
	
	public String getJavaVMVendor() {
		return getProperty(JAVA_VM_VENDOR);
	}
	
	public String getJavaVMName() {
		return getProperty(JAVA_VM_NAME);
	}
	
	public String getJavaVM() {
		return String.format("Java VM : Version [%s] Vendor [%s] Name [%s]", getJavaVMVersion(), getJavaVMVendor(), getJavaVMName());
	}

	/*
	 * Java specification.
	 */
	
	public String getJavaSpecificationVersion() {
		return getProperty(JAVA_SPECIFICATION_VERSION);
	}
	
	public String getJavaSpecificationVendor() {
		return getProperty(JAVA_SPECIFICATION_VENDOR);
	}
	
	public String getJavaSpecificationName() {
		return getProperty(JAVA_SPECIFICATION_NAME);
	}
	
	public String getJavaSpecification() {
		return String.format("Java Specification : Version [%s] Vendor [%s] Name [%s]", getJavaSpecificationVersion(), getJavaSpecificationVendor(), getJavaSpecificationName());
	}

	/*
	 * Java class.
	 */
	
	public String getJavaClassVersion() {
		return getProperty(JAVA_CLASS_VERSION);
	}

	public String getJavaClassPath() {
		return getProperty(JAVA_CLASS_PATH);
	}
	
	public String getJavaClass() {
		return String.format("Java Class : Version [%s] Path [%s]", getJavaClassVersion(), getJavaClassPath());
	}

	/*
	 * Other...
	 */
	
	public String getJavaLibraryPath() {
		return getProperty(JAVA_LIBRARY_PATH);
	}

	public String getJavaIOTmpDir() {
		return getProperty(JAVA_IO_TMPDIR);
	}

	public String getJavaCompiler() {
		return getProperty(JAVA_COMPILER);
	}

	public String getJavaExtDirs() {
		return getProperty(JAVA_EXT_DIRS);
	}
	
	/*
	 * OS.
	 */
	
	public String getOSName() {
		return getProperty(OS_NAME);
	}

	public String getOSArch() {
		return getProperty(OS_ARCH);
	}

	public String getOSVersion() {
		return getProperty(OS_VERSION);
	}
	
	public String getOS() {
		return String.format("OS : Name [%s] Architecture [%s] Version [%s]", getOSName(), getOSArch(), getOSVersion());
	}
	
	/*
	 * Separators.
	 */
	
	public String getFileSeparator() {
		return getProperty(FILE_SEPARATOR);
	}

	public String getPathSeparator() {
		return getProperty(PATH_SEPARATOR);
	}

	public String getLineSeparator() {
		return getProperty(LINE_SEPARATOR);
	}
	
	public String getSeparator() {
		return String.format("Separator : File [%s] Path [%s] Line [%s]", getFileSeparator(), getPathSeparator(), getLineSeparator());
	}

	/*
	 * User.
	 */
	
	public String getUserName() {
		return getProperty(USER_NAME);
	}

	public String getUserHome() {
		return getProperty(USER_HOME);
	}

	public String getUserDir() {
		return getProperty(USER_DIR);
	}
	
	public String getUser() {
		return String.format("User : Name [%s] Home Folder [%s] Working Folder [%s]", getUserName(), getUserHome(), getUserDir());
	}

	/*
	 * Convenience.
	 */
	
	@Override
	public synchronized String toString() {
		return String.format("%s\n%s\n%s", getOS(), getJava(), getUser());
	}	
}
