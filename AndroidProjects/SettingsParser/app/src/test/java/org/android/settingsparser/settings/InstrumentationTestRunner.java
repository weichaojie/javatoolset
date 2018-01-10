
package org.android.settingsparser.settings;

import junit.framework.TestSuite;
import android.test.suitebuilder.TestSuiteBuilder;

/**
 * A instrumentation runner that contains all tests for the settings parser.
 */
public class InstrumentationTestRunner extends
		android.test.InstrumentationTestRunner {

	@Override
	public TestSuite getAllTests() {
		return new TestSuiteBuilder(InstrumentationTestRunner.class)
				.includePackages(
						"com.huawei.android.test.settings.xml")
				.build();
	}

	@Override
	public ClassLoader getLoader() {
		return InstrumentationTestRunner.class.getClassLoader();
	}

}
