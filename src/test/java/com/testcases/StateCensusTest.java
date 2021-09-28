package com.testcases;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.census.CsvStateCensus;
import com.census.CsvStateCode;
import com.census.MyException;
import com.census.StateCensusAnalyser;

public class StateCensusTest {

	/**
	 * This test case pass when a file with 8 rows is read.
	 */
	@Test
	public void givenCsvFile_with8rowscomparingwith8_returnstrue() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/jayeshkumar/learning_path/BATCH51/git_part/statecensusanalysis/data/StateCensusData.csv");
			Assert.assertEquals(8, analyser.readStateRecord(CsvStateCensus.class));
		} catch (MyException e) {
			System.out.println(e);
		}
	}

	/**
	 * If given wrong csv file anticipates exception
	 */
	@Test
	public void givenCsvFile_whichIsIncorrect_expectsException() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/jayeshkumar/learning_path/BATCH51/git_part/statecensusanalysis/data/StateCensusData1.csv");
			analyser.readStateRecord(CsvStateCensus.class);
		} catch (MyException e) {
			Assert.assertEquals("File not found", e.getMessage());
			System.out.println(e);
		}

	}

	/**
	 * If given file other than csv type. Anticipates custom exception
	 */
	@Test
	public void givenCsvFile_whichIsWrongType_expectsException() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/jayeshkumar/learning_path/BATCH51/git_part/statecensusanalysis/data/book1.txt");
			analyser.readStateRecord(CsvStateCensus.class);
		} catch (MyException e) {
			Assert.assertEquals("Incorrect File Type", e.getMessage());
			System.out.println(e);
		}
	}

	/**
	 * If csv file with invalid delimiter if given Anticipates custom exception
	 */
	@Test
	public void givenCsvFile_withwrongdelimiter_expectsException() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/jayeshkumar/learning_path/BATCH51/git_part/statecensusanalysis/data/InvalidDelimeter.csv");
			analyser.readStateRecord(CsvStateCensus.class);
		} catch (MyException e) {

			System.out.println(e);
			Assert.assertEquals("Incorrect Delimiter", e.getMessage());
		}
	}

	/**
	 * If csv file with invalid header if given Anticipates custom exception
	 */
	@Test
	public void givenCsvFile_withwrongdHeader_expectsException() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/jayeshkumar/learning_path/BATCH51/git_part/statecensusanalysis/data/IncorrectHeader.csv");
			analyser.readStateRecord(CsvStateCensus.class);
		} catch (MyException e) {

			System.out.println(e);
			Assert.assertEquals("Header doesn't match", e.getMessage());
		}
	}

	/**
	 * Test case passes when a csv state code file with 3 rows of data is read
	 */
	@Test
	public void givenStateCodeCsvFile_with3rowscomparingwith3_returnstrue() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/jayeshkumar/learning_path/BATCH51/git_part/statecensusanalysis/data/stateCode.csv");
			Assert.assertEquals(3, analyser.readStateRecord(CsvStateCode.class));
		} catch (MyException e) {
			System.out.println(e);
		}
	}

	/**
	 * Given wrong csv file Anticipates custom exception
	 */
	@Test
	public void givenStateCodeCsvFile_whichIsIncorrect_expectsException() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/jayeshkumar/learning_path/BATCH51/git_part/statecensusanalysis/data/StateCode1.csv");
			analyser.readStateRecord(CsvStateCode.class);
		} catch (MyException e) {
			Assert.assertEquals("File not found", e.getMessage());
			System.out.println(e);
		}

	}

	/**
	 * If given file other than csv type. Anticipates custom exception
	 */
	@Test
	public void givenStateCodeCsvFile_whichIsWrongType_expectsException() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/jayeshkumar/learning_path/BATCH51/git_part/statecensusanalysis/data/book1.txt");
			analyser.readStateRecord(CsvStateCode.class);
		} catch (MyException e) {
			Assert.assertEquals("Incorrect File Type", e.getMessage());
			System.out.println(e);
		}
	}

	/**
	 * If csv file with invalid delimiter if given Anticipates custom exception
	 */
	@Test
	public void givenStateCodeCsvFile_withwrongDelimeter_expectsException() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/jayeshkumar/learning_path/BATCH51/git_part/statecensusanalysis/data/DelimeterCode.csv");
			analyser.readStateRecord(CsvStateCode.class);
		} catch (MyException e) {

			System.out.println(e);
			Assert.assertEquals("Incorrect Delimiter", e.getMessage());
		}
	}

	/**
	 * If csv file with invalid header if given Anticipates custom exception
	 */
	@Test
	public void givenStateCodeCsvFile_withwrongdHeader_expectsException() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/jayeshkumar/learning_path/BATCH51/git_part/statecensusanalysis/data/IncorrectHeaderStateCode.csv");
			analyser.readStateRecord(CsvStateCode.class);
		} catch (MyException e) {

			System.out.println(e);
			Assert.assertEquals("Header doesn't match", e.getMessage());
		}
	}

}
