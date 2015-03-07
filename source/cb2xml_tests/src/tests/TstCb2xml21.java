package tests;

//import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.cb2xml.Cb2Xml;
import net.sf.cb2xml.Cb2Xml2;
import net.sf.cb2xml.sablecc.lexer.LexerException;
import net.sf.cb2xml.sablecc.parser.ParserException;
import net.sf.cb2xml.util.XmlUtils;






import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import common.Code;

public class TstCb2xml21 {
	
	private static int COPYBOOK_COUNT1 = 112;
	private static int COPYBOOK_COUNT2 = 204;
	private static String COPBOOK_PREF = "cobolCopybook/cbl2xml_Test";
	private static String XML_FILE_PREF = "xmlCopybook/cb2xml_Output";
	
	
	private String[] COPBOOK_LIST1 = {
			"cpyComp.cbl",
			"cpyComp3.cbl",
			"cpyComp5.cbl",
			"cpyCompP.cbl",
			"cpyComp3P.cbl",
			"cpyComp5P.cbl",
	};

	private String[] COPBOOK_LIST2 = {
			"cpyCompSync.cbl",
			"cpyComp3Sync.cbl",
			"cpyComp5Sync.cbl",
	};

	private String[] COPBOOK_LIST3 = {
			"cpyUnxdetail.cbl",
			"cpyOccurs.cbl",
			"cpyBitOfEverything.cbl",
	};

	
	@Test
	public void test1() throws IOException, SAXException, ParserConfigurationException {
		doTest(101, COPYBOOK_COUNT1);
	}
	
	@Test
	public void test2() throws IOException, SAXException, ParserConfigurationException {
		doTest(201, COPYBOOK_COUNT2);
	}

	private void doTest(int start, int end) throws IOException, SAXException, ParserConfigurationException{
		String cblFilename, xmlFilename;
		for (int i = start; i <= end; i++) {
			System.out.println("Test: " + i);
			cblFilename = Code.getFullName(COPBOOK_PREF + i + ".cbl");
			
			Document doc = Cb2Xml.convertToXMLDOM(new File(cblFilename));
			
			System.out.println(i + ":");
			System.out.println(XmlUtils.domToString(doc));
			System.out.println();
		
			
			xmlFilename = Code.getFullName(XML_FILE_PREF + i + ".xml");
			common.Code.compare("File: " + cblFilename, xmlFilename, doc);
		}
		//fail("Not yet implemented");
	}

	@Test
	public void testArray1() throws IOException, SAXException, ParserConfigurationException, ParserException, LexerException {
		tstArray(COPBOOK_LIST1);
	}

	@Test
	public void testArray2() throws IOException, SAXException, ParserConfigurationException, ParserException, LexerException {
		tstArray(COPBOOK_LIST2);
	}

	@Test
	public void testArray3() throws IOException, SAXException, ParserConfigurationException, ParserException, LexerException {
		tstArray(COPBOOK_LIST3);
	}

	public void tstArray(String[] copybooks) throws IOException, SAXException, ParserConfigurationException, ParserException, LexerException  {
		String cblFilename, xmlFilename;
		for (String c : copybooks) {
			xmlFilename = "xmlCpy" + c.substring(3, c.length() - 3) + "Xml";
			System.out.println("Test: " + c + " " + xmlFilename);
			cblFilename = Code.getFullName("cobolCopybook/" + c);
			
			Document doc = Cb2Xml2.convertToXMLDOM(new File(cblFilename));
			
			System.out.println(c + " --> " + xmlFilename + ":");
			System.out.println(XmlUtils.domToString(doc));
			System.out.println();
		
			
			xmlFilename = Code.getFullName("xmlCopybook/" + xmlFilename);
			common.Code.compare("File: " + cblFilename, doc, xmlFilename);
			
		}
		//fail("Not yet implemented");
	}

}