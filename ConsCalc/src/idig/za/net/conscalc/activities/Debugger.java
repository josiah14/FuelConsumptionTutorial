/**
 * 
 */
package idig.za.net.conscalc.activities;

import android.util.Log;

/**
 * @author josiah
 *
 */
// Debugging Variables
public class Debugger {
	/*************************************************************************************************************
	 * Class Members                                                                                             *
	 *************************************************************************************************************/
	protected String TAG = "Default";
	private String DEBUG_ERR_TAG = "Debugger";
	protected DebugLevels[] mDebugLevel = {DebugLevels.VERBOSE};
	private TagGenerator mTagGenerator;
	
	/*************************************************************************************************************
	 * Enum Definitions                                                                                          *
	 *************************************************************************************************************/
	public static enum DebugLevels { OFF, VERBOSE, ERROR, WARN, INFO, DEBUG, WTF };
	
	/*************************************************************************************************************
	 * Internal Anonymous Classes                                                                                *
	 *************************************************************************************************************/
	public static abstract class MethodNameExtractor {
		protected int mDepth = 0;
		
		public MethodNameExtractor() {
			// Default constructor - do nothing
		}
		
		public MethodNameExtractor(int depth) {
			if (depth > 0) {
				mDepth = depth;
			}
		}
		
		public void setDepth(int depth) {
			mDepth = depth;
		}
		
		public abstract String getExecutingMethodName();
	}
	
	private static class DefaultMethodNameExtractor extends MethodNameExtractor {
		
		public DefaultMethodNameExtractor() {
			super();
		}

		public DefaultMethodNameExtractor(int depth) {
			super(depth);
		}

		@Override
		public String getExecutingMethodName() {
			final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
			return ste[ste.length - 1 - mDepth].getMethodName();
		}
		
	}
	
	public static abstract class ClassNameExtractor {
		protected int mDepth = 0;
		
		public ClassNameExtractor() {
			//Do nothing - default constructor
		}
		
		public ClassNameExtractor(int depth) {
			if (depth > 0) {
				mDepth = depth;
			}
		}
		public abstract String getExecutingClassName();
	}
	
	private static class DefaultClassNameExtractor extends ClassNameExtractor {
		public DefaultClassNameExtractor() {
			super();
		}
		
		public DefaultClassNameExtractor(int depth) {
			super(depth);
		}
		
		public String getExecutingClassName() {
			final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
			return ste[ste.length - 1 - mDepth].getClassName();
		}
	}
	
	public interface ITagGenerator {
		public String getExecutingMethod();
		public String getExecutingClass();
		public void generateTag();
		public void generateTag(int classDepth, int methodDepth);
		public void generateTag(int classDepth, MethodNameExtractor argMethodNameExtractor);
		public void generateTag(ClassNameExtractor argClassNameExtractor, int methodDepth);
		public void generateTag(ClassNameExtractor argClassNameExtractor, MethodNameExtractor argMethodNameExtractor);
		public void generateTag(ClassNameExtractor argClassNameExtractor);
		public void generateTag(MethodNameExtractor argMethodNameExtractor);
	}
	
	private class TagGenerator implements ITagGenerator {
		private ClassNameExtractor mClassNameExtractor;
		private MethodNameExtractor mMethodNameExtractor;
		
		public TagGenerator() {
			setExtractors(new DefaultClassNameExtractor(), new DefaultMethodNameExtractor());
		}
		
		public TagGenerator(int classDepth, int methodDepth) {
			setExtractors(new DefaultClassNameExtractor(classDepth), new DefaultMethodNameExtractor(methodDepth));
		}
		
		public TagGenerator(ClassNameExtractor argClassNameExtractor, MethodNameExtractor argMethodNameExtractor) {
			setExtractors(argClassNameExtractor, argMethodNameExtractor);
		}
		
		public TagGenerator(int classDepth, MethodNameExtractor argMethodNameExtractor) {
			setExtractors(new DefaultClassNameExtractor(classDepth), argMethodNameExtractor);	
		}
		
		public TagGenerator(ClassNameExtractor argClassNameExtractor, int methodDepth) {
			setExtractors(argClassNameExtractor, new DefaultMethodNameExtractor(methodDepth));
		}
		
		public TagGenerator(MethodNameExtractor argMethodNameExtractor) {
			setExtractors(new DefaultClassNameExtractor(), argMethodNameExtractor);	
		}
		
		public TagGenerator(ClassNameExtractor argClassNameExtractor) {
			setExtractors(argClassNameExtractor, new DefaultMethodNameExtractor());
		}
		
		private void setExtractors(ClassNameExtractor argClassNameExtractor, MethodNameExtractor argMethodNameExtractor) {
			setMethodNameExtractor(argMethodNameExtractor);
			setClassNameExtractor(argClassNameExtractor);
			generateTag();
		}
		
		private void setMethodNameExtractor(MethodNameExtractor argMethodNameExtractor) {
			if (argMethodNameExtractor != null) {
				mMethodNameExtractor = argMethodNameExtractor;
			} else {
				Log.e(Debugger.this.DEBUG_ERR_TAG, "MethodNameExtractor is null.");
			}
		}
		
		private void setClassNameExtractor(ClassNameExtractor argClassNameExtractor) {
			if (argClassNameExtractor != null) {
				mClassNameExtractor = argClassNameExtractor;
			} else {
				Log.e(Debugger.this.DEBUG_ERR_TAG, "ClassNameExtractor is null.");
			}
		}
		
		public void generateTag() {
			String methodName = mMethodNameExtractor.getExecutingMethodName();
			String className = mClassNameExtractor.getExecutingClassName();
			
			if (className != "" && className != null
				&& methodName != "" && methodName != null) {
				Debugger.this.TAG = className + "." + methodName;
			}
		}
		
		public String getExecutingMethod() {
			return mMethodNameExtractor.getExecutingMethodName();
		}
		
		public String getExecutingClass() {
			return mClassNameExtractor.getExecutingClassName();
		}

		@Override
		public void generateTag(int classDepth, int methodDepth) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void generateTag(int classDepth,
				MethodNameExtractor argMethodNameExtractor) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void generateTag(ClassNameExtractor argClassNameExtractor,
				int methodDepth) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void generateTag(ClassNameExtractor argClassNameExtractor,
				MethodNameExtractor argMethodNameExtractor) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void generateTag(ClassNameExtractor argClassNameExtractor) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void generateTag(MethodNameExtractor argMethodNameExtractor) {
			// TODO Auto-generated method stub
			
		}
	}
	
	/*************************************************************************************************************
	 * Method Implementations                                                                                    *
	 *************************************************************************************************************/
	public Debugger() {
		this.mTagGenerator = new TagGenerator();
	}
	
	public Debugger(int classDepth, int methodDepth) {
		this.mTagGenerator = new TagGenerator(classDepth, methodDepth);
	}
	
	public Debugger(ClassNameExtractor argClassNameExtractor, MethodNameExtractor argMethodNameExtractor) {
		this.mTagGenerator = new TagGenerator(argClassNameExtractor, argMethodNameExtractor);
	}
	
	public Debugger(int classDepth, MethodNameExtractor argMethodNameExtractor) {
		this.mTagGenerator = new TagGenerator(classDepth, argMethodNameExtractor);	
	}
	
	public Debugger(ClassNameExtractor argClassNameExtractor, int methodDepth) {
		this.mTagGenerator = new TagGenerator(argClassNameExtractor, methodDepth);
	}
	
	public Debugger(MethodNameExtractor argMethodNameExtractor) {
		this.mTagGenerator = new TagGenerator(argMethodNameExtractor);	
	}
	
	public Debugger(ClassNameExtractor argClassNameExtractor) {
		this.mTagGenerator = new TagGenerator(argClassNameExtractor);
	}
	
	public DebugLevels[] getDebugLevel() {
		return mDebugLevel;
	}
	
	public void setDebugLevel(DebugLevels[] newDebugLevel) {
		mDebugLevel = newDebugLevel;
	}
	
}