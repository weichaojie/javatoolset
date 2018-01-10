
package org.android.settingsparser.settings.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import junit.framework.TestCase;

import com.huawei.android.xml.settings.SettingsXMLHandler;
import com.huawei.android.xml.settings.SettingsXMLParser;
import com.huawei.android.xml.settings.domain.Array;
import com.huawei.android.xml.settings.domain.Data;
import com.huawei.android.xml.settings.domain.Dict;
import com.huawei.android.xml.settings.domain.False;
import com.huawei.android.xml.settings.domain.Settings;
import com.huawei.android.xml.settings.domain.Real;
import com.huawei.android.xml.settings.domain.True;

/**
 * Tests {@link SettingsXMLParser} with various XML string fixtures.
 * 
 * @author fbeachler
 * 
 */
public class SettingsXMLParserTest extends TestCase {

	public static final String INVALID_PLIST = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<!DOCTYPE settings PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.ultra.com/DTDs/PropertyList-1.0.dtd\">"
			+ "<settings version=\"1.0\">"
			+ "<foo>"
			+ "<bar>Galaxy Zoo Hubble primary classification workflow</bar>"
			+ "<string>1.0</string>" + "</foo>" + "</settings>";
	public static final String VALID_WORKFLOW_VERSION_PLIST = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<!DOCTYPE settings PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.ultra.com/DTDs/PropertyList-1.0.dtd\">"
			+ "<settings version=\"1.0\">"
			+ "<dict>"
			+ "<key>Galaxy Zoo Hubble primary classification workflow</key>"
			+ "<string>1.0</string>" + "</dict>" + "</settings>";
	public static final String VALID_WORKFLOW_PLIST = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<!DOCTYPE settings PUBLIC \"-//Apple Computer//DTD PLIST 1.0//EN\" \"http://www.ultra.com/DTDs/PropertyList-1.0.dtd\">\n"
			+ "<settings version=\"1.0\">\n"
			+ "<dict>\n"
			+ "	<key>workflow_answers</key>\n"
			+ "	<array>\n"
			+ "		<dict>\n"
			+ "			<key>answer_id</key>\n"
			+ "			<integer>1</integer>\n"
			+ "			<key>image_url</key>\n"
			+ "			<string>http://www.galaxyzoo.org/images/buttons/1_button.gif</string>\n"
			+ "			<key>next_workflow_task_id</key>\n"
			+ "			<integer>2</integer>\n"
			+ "			<key>value</key>\n"
			+ "			<string>Smooth</string>\n"
			+ "			<key>workflow_answer_id</key>\n"
			+ "			<integer>1</integer>\n"
			+ "			<key>workflow_task_id</key>\n"
			+ "			<integer>1</integer>\n"
			+ "		</dict>\n"
			+ "		<dict>\n"
			+ "			<key>answer_id</key>\n"
			+ "			<integer>2</integer>\n"
			+ "			<key>image_url</key>\n"
			+ "			<string>http://www.galaxyzoo.org/images/buttons/2_button.gif</string>\n"
			+ "			<key>next_workflow_task_id</key>\n"
			+ "			<integer>30</integer>\n"
			+ "			<key>value</key>\n"
			+ "			<string>Features or disk</string>\n"
			+ "			<key>workflow_answer_id</key>\n"
			+ "			<integer>2</integer>\n"
			+ "			<key>workflow_task_id</key>\n"
			+ "			<integer>1</integer>\n"
			+ "		</dict>\n"
			+ "		<dict>\n"
			+ "			<key>answer_id</key>\n"
			+ "			<integer>3</integer>\n"
			+ "			<key>image_url</key>\n"
			+ "			<string>http://www.galaxyzoo.org/images/buttons/3_button.gif</string>\n"
			+ "			<key>next_workflow_task_id</key>\n"
			+ "			<integer>0</integer>\n"
			+ "			<key>value</key>\n"
			+ "			<string>Star or artifact</string>\n"
			+ "			<key>workflow_answer_id</key>\n"
			+ "			<integer>3</integer>\n"
			+ "			<key>workflow_task_id</key>\n"
			+ "			<integer>1</integer>\n"
			+ "		</dict>\n"
			+ "		<dict>\n"
			+ "			<key>answer_id</key>\n"
			+ "			<integer>16</integer>\n"
			+ "			<key>image_url</key>\n"
			+ "			<string>http://www.galaxyzoo.org/images/buttons/16_button.gif</string>\n"
			+ "			<key>next_workflow_task_id</key>\n"
			+ "			<integer>3</integer>\n"
			+ "			<key>value</key>\n"
			+ "			<string>Completely round</string>\n"
			+ "			<key>workflow_answer_id</key>\n"
			+ "			<integer>4</integer>\n"
			+ "			<key>workflow_task_id</key>\n"
			+ "			<integer>2</integer>\n"
			+ "		</dict>\n"
			+ "		<dict>\n"
			+ "			<key>answer_id</key>\n"
			+ "			<integer>20</integer>\n"
			+ "			<key>image_url</key>\n"
			+ "			<string>http://www.galaxyzoo.org/images/buttons/20_button.gif</string>\n"
			+ "			<key>next_workflow_task_id</key>\n"
			+ "			<integer>0</integer>\n"
			+ "			<key>value</key>\n"
			+ "			<string>Lens or arc</string>\n"
			+ "			<key>workflow_answer_id</key>\n"
			+ "			<integer>10</integer>\n"
			+ "			<key>workflow_task_id</key>\n"
			+ "			<integer>4</integer>\n"
			+ "		</dict>\n"
			+ "		<dict>\n"
			+ "			<key>answer_id</key>\n"
			+ "			<integer>21</integer>\n"
			+ "			<key>image_url</key>\n"
			+ "			<string>http://www.galaxyzoo.org/images/buttons/21_button.gif</string>\n"
			+ "			<key>next_workflow_task_id</key>\n"
			+ "			<integer>0</integer>\n"
			+ "			<key>value</key>\n"
			+ "			<string>Disturbed</string>\n"
			+ "			<key>workflow_answer_id</key>\n"
			+ "			<integer>11</integer>\n"
			+ "			<key>workflow_task_id</key>\n"
			+ "			<integer>4</integer>\n"
			+ "		</dict>\n"
			+ "	</array>\n"
			+ "	<key>workflow_tasks</key>\n"
			+ "	<array>\n"
			+ "		<dict>\n"
			+ "			<key>name</key>\n"
			+ "			<string>Is the galaxy simply smooth and rounded, with no sign of a disk?</string>\n"
			+ "			<key>parent_id</key>\n"
			+ "			<integer>-1</integer>\n"
			+ "			<key>task_id</key>\n"
			+ "			<integer>1</integer>\n"
			+ "			<key>workflow_answers</key>\n"
			+ "			<array>\n"
			+ "				<integer>1</integer>\n"
			+ "				<integer>2</integer>\n"
			+ "				<integer>3</integer>\n"
			+ "			</array>\n"
			+ "			<key>workflow_task_id</key>\n"
			+ "			<integer>1</integer>\n"
			+ "		</dict>\n"
			+ "		<dict>\n"
			+ "			<key>name</key>\n"
			+ "			<string>How rounded is it?</string>\n"
			+ "			<key>parent_id</key>\n"
			+ "			<integer>1</integer>\n"
			+ "			<key>task_id</key>\n"
			+ "			<integer>7</integer>\n"
			+ "			<key>workflow_answers</key>\n"
			+ "			<array>\n"
			+ "				<integer>4</integer>\n"
			+ "				<integer>5</integer>\n"
			+ "				<integer>6</integer>\n"
			+ "			</array>\n"
			+ "			<key>workflow_task_id</key>\n"
			+ "			<integer>2</integer>\n"
			+ "		</dict>\n"
			+ "		<dict>\n"
			+ "			<key>name</key>\n"
			+ "			<string>Is there anything odd?</string>\n"
			+ "			<key>parent_id</key>\n"
			+ "			<integer>2</integer>\n"
			+ "			<key>task_id</key>\n"
			+ "			<integer>6</integer>\n"
			+ "			<key>workflow_answers</key>\n"
			+ "			<array>\n"
			+ "				<integer>7</integer>\n"
			+ "				<integer>8</integer>\n"
			+ "			</array>\n"
			+ "			<key>workflow_task_id</key>\n"
			+ "			<integer>3</integer>\n"
			+ "		</dict>\n"
			+ "	</array>\n"
			+ "</dict>\n" + "</settings>\n" + "";
	public static final String VALID_PLIST_ARRAY_ROOT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<!DOCTYPE settings PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.ultra.com/DTDs/PropertyList-1.0.dtd\">"
			+ "<settings version=\"1.0\">"
			+ "<array>"
			+ "<dict>"
			+ "<key>foo</key>"
			+ "<string>1.0</string>"
			+ "</dict>"
			+ "<dict>"
			+ "<key>bar</key>"
			+ "<string>1.1</string>"
			+ "</dict>"
			+ "</array>" + "</settings>";
	public static final String VALID_PLIST_ARRAY_ROOT_NESTED_ARRAY = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<!DOCTYPE settings PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.ultra.com/DTDs/PropertyList-1.0.dtd\">"
			+ "<settings version=\"1.0\">"
			+ "<array>"
			+ "<array>"
			+ "<string>foo</string>"
			+ "<string>bar</string>"
			+ "</array>"
			+ "<array>"
			+ "<string>baz</string>"
			+ "<string>quux</string>"
			+ "</array>" + "</array>" + "</settings>";
	public static final String VALID_PLIST_ARRAY_ROOT_NESTED_DICT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<!DOCTYPE settings PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.ultra.com/DTDs/PropertyList-1.0.dtd\">"
			+ "<settings version=\"1.0\">"
			+ "<array>"
			+ "		<dict>\n"
			+ "			<key>name</key>\n"
			+ "			<string>How rounded is it?</string>\n"
			+ "			<key>parent_id</key>\n"
			+ "			<integer>1</integer>\n"
			+ "			<key>task_id</key>\n"
			+ "			<integer>7</integer>\n"
			+ "			<key>workflow_answers</key>\n"
			+ "			<array>\n"
			+ "				<integer>4</integer>\n"
			+ "				<integer>5</integer>\n"
			+ "				<integer>6</integer>\n"
			+ "			</array>\n"
			+ "			<key>workflow_task_id</key>\n"
			+ "			<integer>2</integer>\n"
			+ "		</dict>\n" + "</array>" + "</settings>";
	public static final String VALID_PLIST_DICT_ROOT_NESTED_DICT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<!DOCTYPE settings PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.ultra.com/DTDs/PropertyList-1.0.dtd\">"
			+ "<settings version=\"1.0\">"
			+ "<key>cat</key>"
			+ "<dict>"
			+ "<key>ID</key>"
			+ "<string>901</string>"
			+ "<key>title</key>"
			+ "<string>Title</string>"
			+ "<key>thumb</key>"
			+ "<dict>"
			+ "<key>ID</key>"
			+ "<integer>152</integer>"
			+ "<key>uri</key>"
			+ "<string>http://www.google.com</string>"
			+ "</dict>"
			+ "<key>order</key>"
			+ "<integer>2</integer>"
			+ "<key>type</key>"
			+ "<integer>5</integer>" + "</dict>" + "</settings>";
	public static final String VALID_PLIST_STRING_ROOT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<!DOCTYPE settings PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.ultra.com/DTDs/PropertyList-1.0.dtd\">"
			+ "<settings version=\"1.0\">"
			+ "<string>"
			+ "foobar"
			+ "</string>"
			+ "</settings>";
	public static final String VALID_PLIST_DATA_ROOT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<!DOCTYPE settings PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.ultra.com/DTDs/PropertyList-1.0.dtd\">"
			+ "<settings version=\"1.0\">"
			+ "<data>"
			+ "Zm9vYmFy"
			+ "</data>"
			+ "</settings>";
	public static final String VALID_PLIST_DATE_ROOT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<!DOCTYPE settings PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.ultra.com/DTDs/PropertyList-1.0.dtd\">"
			+ "<settings version=\"1.0\">"
			+ "<date>"
			+ "Sun, 13 Feb 2011 12:01:00 GMT-0500" + "</date>" + "</settings>";
	public static final String VALID_PLIST_ISO8601_DATE_ROOT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<!DOCTYPE settings PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.ultra.com/DTDs/PropertyList-1.0.dtd\">"
			+ "<settings version=\"1.0\">"
			+ "<date>"
			+ "2012-02-24T10:10:00Z"
			+ "</date>" + "</settings>";
	public static final String VALID_PLIST_REAL_ROOT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<!DOCTYPE settings PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.ultra.com/DTDs/PropertyList-1.0.dtd\">"
			+ "<settings version=\"1.0\">"
			+ "<real>"
			+ "3.1417"
			+ "</real>"
			+ "</settings>";
	public static final String VALID_PLIST_INTEGER_ROOT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<!DOCTYPE settings PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.ultra.com/DTDs/PropertyList-1.0.dtd\">"
			+ "<settings version=\"1.0\">"
			+ "<integer>"
			+ "1"
			+ "</integer>"
			+ "</settings>";
	public static final String VALID_PLIST_TRUE_ROOT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<!DOCTYPE settings PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.ultra.com/DTDs/PropertyList-1.0.dtd\">"
			+ "<settings version=\"1.0\">" + "<true />" + "</settings>";
	public static final String VALID_PLIST_FALSE_ROOT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<!DOCTYPE settings PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.ultra.com/DTDs/PropertyList-1.0.dtd\">"
			+ "<settings version=\"1.0\">" + "<false />" + "</settings>";

	/**
	 * The class under test.
	 */
	protected SettingsXMLParser parser;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		parser = new SettingsXMLParser();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		parser = null;
		super.tearDown();
	}

	/**
	 * Test method for
	 * {@link com.huawei.android.settings.xml.SettingsXMLParser#getHandler()}
	 * and
	 * {@link com.huawei.android.settings.xml.SettingsXMLParser#setHandler(com.huawei.android.test.settings.xml.SettingsXMLHandler)}
	 * .
	 */
	public void testHandlerGetterSetter() {
		assertNull(parser.getHandler());
		SettingsXMLHandler expected = new SettingsXMLHandler();
		parser.setHandler(expected);
		assertEquals(expected, parser.getHandler());
	}

	/**
	 * Test method for
	 * {@link com.huawei.android.settings.xml.SettingsXMLParser#parse(java.lang.String)}
	 * .
	 */
	public void testParseWithNoHandler() {
		try {
			parser.parse("");
		} catch (Exception e) {
			assertEquals(IllegalStateException.class.getName(), e.getClass()
					.getName());
			return;
		}
		fail("expected exception not thrown");
	}

	/**
	 * Test method for
	 * {@link com.huawei.android.settings.xml.SettingsXMLParser#parse(java.lang.String)}
	 * .
	 */
	public void testParseNull() {
		SettingsXMLHandler handler = new SettingsXMLHandler();
		parser.setHandler(handler);
		parser.parse("");
		assertNull(((SettingsXMLHandler) parser.getHandler()).getPlist());
	}

	/**
	 * Test method for
	 * {@link com.huawei.android.settings.xml.SettingsXMLParser#parse(java.lang.String)}
	 * .
	 */
	public void testParseValidXMLInvalidSettings() {
		SettingsXMLHandler handler = new SettingsXMLHandler();
		parser.setHandler(handler);
		parser.parse(INVALID_PLIST);
		assertNotNull(((SettingsXMLHandler) parser.getHandler()).getPlist());
		assertNull(((SettingsXMLHandler) parser.getHandler()).getPlist()
				.getRootElement());
	}

	/**
	 * Test method for
	 * {@link com.huawei.android.settings.xml.SettingsXMLParser#parse(java.lang.String)}
	 * .
	 */
	public void testParseValidXMLWorkflowVersion() {
		SettingsXMLHandler handler = new SettingsXMLHandler();
		parser.setHandler(handler);
		parser.parse(VALID_WORKFLOW_VERSION_PLIST);
		assertNotNull(((SettingsXMLHandler) parser.getHandler()).getPlist());
	}

	/**
	 * Test method for
	 * {@link com.huawei.android.settings.xml.SettingsXMLParser#parse(java.lang.String)}
	 * .
	 */
	public void testParseValidXMLWorkflow() {
		SettingsXMLHandler handler = new SettingsXMLHandler();
		parser.setHandler(handler);
		parser.parse(VALID_WORKFLOW_PLIST);
		Settings actualSettings = ((SettingsXMLHandler) parser.getHandler()).getPlist();
		assertNotNull(actualSettings);
		assertEquals(6, ((Dict) actualSettings.getRootElement())
				.getConfigurationArray("workflow_answers").size());
		assertEquals(3, ((Dict) actualSettings.getRootElement())
				.getConfigurationArray("workflow_tasks").size());
	}

	/**
	 * Test method for
	 * {@link com.huawei.android.settings.xml.SettingsXMLParser#parse(java.lang.String)}
	 * .
	 */
	public void testParseValidSettingsDictRootNestedDict() {
		SettingsXMLHandler handler = new SettingsXMLHandler();
		parser.setHandler(handler);
		parser.parse(VALID_PLIST_DICT_ROOT_NESTED_DICT);
		Settings actualSettings = ((SettingsXMLHandler) parser.getHandler()).getPlist();
		assertNotNull(actualSettings);
		Dict cat = (Dict) actualSettings.getRootElement();
		assertEquals("901", cat.getConfiguration("ID").getValue());
		assertEquals("Title", cat.getConfiguration("title").getValue());
		assertEquals(new Integer(152), cat.getConfigurationInteger("thumb.ID")
				.getValue());
		assertEquals("http://www.google.com", cat.getConfiguration("thumb.uri")
				.getValue());
		assertEquals(new Integer(2), cat.getConfigurationInteger("order")
				.getValue());
		assertEquals(new Integer(5), cat.getConfigurationInteger("type")
				.getValue());
	}

	/**
	 * Test method for
	 * {@link com.huawei.android.settings.xml.SettingsXMLParser#parse(java.lang.String)}
	 * .
	 */
	public void testParseValidSettingsArrayRoot() {
		SettingsXMLHandler handler = new SettingsXMLHandler();
		parser.setHandler(handler);
		parser.parse(VALID_PLIST_ARRAY_ROOT);
		Settings actualSettings = ((SettingsXMLHandler) parser.getHandler()).getPlist();
		assertNotNull(actualSettings);
		assertEquals(2, ((Array) actualSettings.getRootElement()).size());
	}

	/**
	 * Test method for
	 * {@link com.huawei.android.settings.xml.SettingsXMLParser#parse(java.lang.String)}
	 * .
	 */
	public void testParseValidSettingsArrayRootNestedArray() {
		SettingsXMLHandler handler = new SettingsXMLHandler();
		parser.setHandler(handler);
		parser.parse(VALID_PLIST_ARRAY_ROOT_NESTED_ARRAY);
		Settings actualSettings = ((SettingsXMLHandler) parser.getHandler()).getPlist();
		assertNotNull(actualSettings);
		assertEquals(2, ((Array) actualSettings.getRootElement()).size());
		assertEquals(2,
				((Array) ((Array) actualSettings.getRootElement()).get(0)).size());
		assertEquals(2,
				((Array) ((Array) actualSettings.getRootElement()).get(1)).size());
	}

	/**
	 * Test method for
	 * {@link com.huawei.android.settings.xml.SettingsXMLParser#parse(java.lang.String)}
	 * .
	 */
	public void testParseValidSettingsStringRoot() {
		SettingsXMLHandler handler = new SettingsXMLHandler();
		parser.setHandler(handler);
		parser.parse(VALID_PLIST_STRING_ROOT);
		Settings actualSettings = ((SettingsXMLHandler) parser.getHandler()).getPlist();
		assertNotNull(actualSettings);
		assertNotNull(actualSettings.getRootElement());
		assertEquals(
				"foobar",
				((com.huawei.android.xml.settings.domain.String) actualSettings
						.getRootElement()).getValue());
	}

	/**
	 * Test method for
	 * {@link com.huawei.android.settings.xml.SettingsXMLParser#parse(java.lang.String)}
	 * .
	 */
	public void testParseValidSettingsDataRoot() {
		SettingsXMLHandler handler = new SettingsXMLHandler();
		parser.setHandler(handler);
		parser.parse(VALID_PLIST_DATA_ROOT);
		Settings actualSettings = ((SettingsXMLHandler) parser.getHandler()).getPlist();
		assertNotNull(actualSettings);
		assertEquals("foobar", ((Data) actualSettings.getRootElement()).getValue());
	}

	/**
	 * Test method for
	 * {@link com.huawei.android.settings.xml.SettingsXMLParser#parse(java.lang.String)}
	 * .
	 */
	public void testParseValidSettingsISO8601DateRoot() {
		SettingsXMLHandler handler = new SettingsXMLHandler();
		parser.setHandler(handler);
		parser.parse(VALID_PLIST_ISO8601_DATE_ROOT);
		Settings actualSettings = ((SettingsXMLHandler) parser.getHandler()).getPlist();
		assertNotNull(actualSettings);
		// 2012-02-24T10:10:00Z
		assertEquals(new Date("Fri, 24 Feb 2012 10:10:00 GMT-0700"),
				((com.huawei.android.xml.settings.domain.Date) actualSettings
						.getRootElement()).getValue());
	}

	/**
	 * Test method for
	 * {@link com.huawei.android.settings.xml.SettingsXMLParser#parse(java.lang.String)}
	 * .
	 */
	public void testParseValidSettingsDateRoot() {
		SettingsXMLHandler handler = new SettingsXMLHandler();
		parser.setHandler(handler);
		parser.parse(VALID_PLIST_DATE_ROOT);
		Settings actualSettings = ((SettingsXMLHandler) parser.getHandler()).getPlist();
		assertNotNull(actualSettings);
		assertEquals(new Date("Sun, 13 Feb 2011 12:01:00 GMT-0500"),
				((com.huawei.android.xml.settings.domain.Date) actualSettings
						.getRootElement()).getValue());
	}

	/**
	 * Test method for
	 * {@link com.huawei.android.settings.xml.SettingsXMLParser#parse(java.lang.String)}
	 * .
	 */
	public void testParseValidSettingsRealRoot() {
		SettingsXMLHandler handler = new SettingsXMLHandler();
		parser.setHandler(handler);
		parser.parse(VALID_PLIST_REAL_ROOT);
		Settings actualSettings = ((SettingsXMLHandler) parser.getHandler()).getPlist();
		assertNotNull(actualSettings);
		assertEquals(new Float(3.1417),
				((Real) actualSettings.getRootElement()).getValue());
	}

	/**
	 * Test method for
	 * {@link com.huawei.android.settings.xml.SettingsXMLParser#parse(java.lang.String)}
	 * .
	 */
	public void testParseValidSettingsIntegerRoot() {
		SettingsXMLHandler handler = new SettingsXMLHandler();
		parser.setHandler(handler);
		parser.parse(VALID_PLIST_INTEGER_ROOT);
		Settings actualSettings = ((SettingsXMLHandler) parser.getHandler()).getPlist();
		assertNotNull(actualSettings);
		assertEquals(
				new Integer(1).intValue(),
				((com.huawei.android.xml.settings.domain.Integer) actualSettings
						.getRootElement()).getValue().intValue());
	}

	/**
	 * Test method for
	 * {@link com.huawei.android.settings.xml.SettingsXMLParser#parse(java.lang.String)}
	 * .
	 */
	public void testParseValidSettingsTrueRoot() {
		SettingsXMLHandler handler = new SettingsXMLHandler();
		parser.setHandler(handler);
		parser.parse(VALID_PLIST_TRUE_ROOT);
		Settings actualSettings = ((SettingsXMLHandler) parser.getHandler()).getPlist();
		assertNotNull(actualSettings);
		assertTrue(((True) actualSettings.getRootElement()).getValue());
	}

	/**
	 * Test method for
	 * {@link com.huawei.android.settings.xml.SettingsXMLParser#parse(java.lang.String)}
	 * .
	 */
	public void testParseValidSettingsFalseRoot() {
		SettingsXMLHandler handler = new SettingsXMLHandler();
		parser.setHandler(handler);
		parser.parse(VALID_PLIST_FALSE_ROOT);
		Settings actualSettings = ((SettingsXMLHandler) parser.getHandler()).getPlist();
		assertNotNull(actualSettings);
		assertFalse(((False) actualSettings.getRootElement()).getValue());
	}

	/**
	 * Test method for
	 * {@link com.huawei.android.settings.xml.SettingsXMLParser#parse(java.io.InputStream)}
	 * .
	 * 
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public void testParseValidSettingsArrayRootAsInputStream()
			throws IllegalStateException, IOException {
		SettingsXMLHandler handler = new SettingsXMLHandler();
		parser.setHandler(handler);
		InputStream bas = new ByteArrayInputStream(
				VALID_PLIST_ARRAY_ROOT.getBytes());
		parser.parse(bas);
		Settings actualSettings = ((SettingsXMLHandler) parser.getHandler()).getPlist();
		assertNotNull(actualSettings);
		assertEquals(2, ((Array) actualSettings.getRootElement()).size());
	}

}
